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
import org.json.*;
@RestController
//@Controller
@SpringBootApplication


public class Mostrar {
    @RequestMapping("/mosMed")
    String mostrar() {

        return String.valueOf("<h3>MEDICOS<h3><br>"+Medico.leer());
    }

    @RequestMapping("/mosPac")
    String mostrarPac() {
                return String.valueOf("<h3>PACIENTES<h3><br>"+Paciente.leer());
    }

    @RequestMapping("/mosPag")
    String mostrarPag() {

        return String.valueOf("<h3>PAGOS<h3><br>"+Paciente.leerPago());
    }


}
