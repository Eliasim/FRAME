package com.example.primerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import org.json.*;
//@RestController
@Controller
@SpringBootApplication
public class Test {

    @RequestMapping("/index")
    String home(ModelMap map) {
        map.addAttribute("saludo", "HOLA");
        return "index";
    }

    @PostMapping("/result")
    String sumbit(Model modelo,

    @RequestParam String nombre,
    @RequestParam String apellido_Pat,
    @RequestParam String apellido_Mat,
    @RequestParam String fecha_Nacimiento,
    @RequestParam String telefono,
    @RequestParam String correo,
    @RequestParam String contrasena,
    @RequestParam String tipo,
    @RequestParam String delegacion,
    @RequestParam String colonia,
    @RequestParam String calle,
    @RequestParam String numExt,
    @RequestParam String numInt
    ) 
    {
        return "result";
    }
	public static void main(String[] args) {
		SpringApplication.run(Test.class, args);
	}


    @RequestMapping("/registroMedico")
    String registrarMedico() {
        return "medicoRegistrar";
    }

    @PostMapping("/registroMedico")
    String registrarMedicoP(Model modelo,@RequestParam String Nombre,@RequestParam String Apellido,@RequestParam String Esp){
        Medico.agregarMedico(Nombre,Apellido,Esp);
        return "resultado";
    }


    @RequestMapping("/registroPaciente")
    String registrarPaciente() {
        return "pacienteRegistrar";

    }

    @PostMapping("/registroPaciente")
    String registrarPacienteP(Model modelo, 
    @RequestParam String Nombre,
    @RequestParam String Apellido,
    @RequestParam String fecha,
    @RequestParam String Dom,
    @RequestParam String Tel,
    @RequestParam String Pais,
    @RequestParam String Mail,
    @RequestParam String Obs,
    @RequestParam String pago,
    @RequestParam String turno) {

        Paciente.agregarPaciente(Nombre, Apellido, fecha, Dom, Tel, Mail, Obs, Pais, pago, turno);
        return "resultado";
    }

    @GetMapping("/borrarMedico")
    String borrarMedico(@RequestParam String id) {
        Medico.borrar(id);
        return "redirect:/mostrarMedico";
    }

    
    @GetMapping("/editarMedico")
    String editarMedico(Model model,@RequestParam String id) {
        model.addAttribute("id",id);
        model.addAttribute("idEd","/editarMedicoo");
        return "medicoEditar";
    }

    @PostMapping("/editarMedicoo")
    String editarMedicoo(Model modelo,@RequestParam String id,@RequestParam String Nombre,@RequestParam String Apellido){           
        return "redirect:/mostrarMedico";
    }

    @GetMapping("/borrarPaciente")
    String borrarPaciente(@RequestParam String id) {
        Paciente.borrar(id);
        return "redirect:/mostrarPaciente";
    }
    
    @GetMapping("/editarPaciente")
    String editarPaciente(Model model,@RequestParam String id) {

        model.addAttribute("id",id);
        model.addAttribute("idEd","/editarPacientee");
        return "pacienteEditar";
    }

    @PostMapping("/editarPacientee")
        String editarPacientee(Model modelo,@RequestParam String id,@RequestParam String Nombre,@RequestParam String Apellido,@RequestParam String fecha,@RequestParam String Dom,@RequestParam String Pais,@RequestParam String Tel,@RequestParam String Mail){
        System.out.println(id);
        System.out.println(Nombre);
        System.out.println(Apellido);
        System.out.println(fecha);
        System.out.println(Dom);
        System.out.println(Pais);
        System.out.println(Tel);
        System.out.println(Mail);
        Paciente.cambiar(id, Nombre, Apellido, fecha, Dom, Pais, Tel, Mail);    
        return "redirect:/mostrarPaciente";
    }   
}
