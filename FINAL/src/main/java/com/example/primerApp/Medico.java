package com.example.primerApp;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.Random;


public class Medico {
    public static String randomId(){
        String cadena="";
        int min = 0;
        int max = 9;
        Random random = new Random();

        for(int i=0;i<8;i++){
            int value = random.nextInt(max + min) + min;
            cadena=cadena+String.valueOf(value);
        }
        return (cadena);
    }

    public static String agregarMedico(String nombre,String apellido, String esp){
        String hecho="AGREGAR";
        String medicoId=randomId();
        String especialistaId=randomId();
        try{
            Connection connect = Conexion.getConnection();
            PreparedStatement sentencia1= connect.prepareStatement("INSERT INTO Medico VALUES(?,?,?)");
            sentencia1.setString(1, medicoId);
            sentencia1.setString(2, nombre);
            sentencia1.setString(3, apellido);
            sentencia1.execute();
            sentencia1.close();
            connect.close();
            hecho+="si";

            Connection connect2 = Conexion.getConnection();
            PreparedStatement sentencia2= connect2.prepareStatement("INSERT INTO Especialidad VALUES(?,?)");
            sentencia2.setString(1, especialistaId);
            sentencia2.setString(2, esp);
            sentencia2.execute();
            sentencia2.close();
            connect2.close();
            hecho+="si";
            Connection connect3 = Conexion.getConnection();
            PreparedStatement sentencia3= connect3.prepareStatement("INSERT INTO MedicoEspecialidad VALUES(?,?)");
            sentencia3.setString(1, medicoId);
            sentencia3.setString(2, especialistaId);
            sentencia3.execute();
            sentencia3.close();
            connect3.close();
            hecho+="si";
        }
        catch(Exception e){
            System.out.println(e);
            hecho+="no";
        }
        return hecho;
    }

    public static String borrar(String id){
        String hecho="BORRAR";
        try{
            Connection connect = Conexion.getConnection();
            PreparedStatement sentencia1= connect.prepareStatement("delete from Medico where idMedico=?");
            sentencia1.setString(1, id);
            sentencia1.execute();
            sentencia1.close();
            connect.close();
            hecho+="si";
        }
        catch(Exception e){
            System.out.println(e);
            hecho+="no";
        }
        return hecho;
    }

    public static String cambiar(String nombre,String apellido,String id){
        String hecho="CAMBIAR";
        try{
            Connection connect = Conexion.getConnection();
            PreparedStatement sentencia1= connect.prepareStatement("update Medico set nombreMedico=?, apellidosMedico=? where idMedico = ?");
            sentencia1.setString(1, nombre);
            sentencia1.setString(2, apellido);
            sentencia1.setString(3, id);
            sentencia1.execute();
            sentencia1.close();
            connect.close();
            hecho+="si";
        }
        catch(Exception e){
            System.out.println(e);
            hecho+="no";
        }
        return hecho;
    }

    public static String leer(){
        String leido="<table border='1'><tr><td>idMedico</td><td>nombreMedico</td><td>apellidosMedico</td><td></td><td></td></tr>";
        try{
            Connection connect = Conexion.getConnection();
            Statement sentencia= connect.createStatement();
            ResultSet resultado= sentencia.executeQuery("select * from Medico");

            while (resultado.next()) {
                leido+="<tr><td>"+resultado.getString("idMedico")+"</td><td>"+resultado.getString("nombreMedico")+"</td><td>"+resultado.getString("apellidosMedico")+"</td><td><a href='/editarMed?id="+resultado.getString("idMedico")+"'>Editar</a></td><td><a href='/borrarMed?id="+resultado.getString("idMedico")+"'><font color='red'>Borar</font></a></td></tr>";            
            }
            leido+="</table>";
            sentencia.close();
            connect.close();            
        }
        catch(Exception e){
            System.out.println(e);
        }
        return leido;
    }
}
