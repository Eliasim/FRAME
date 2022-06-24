package com.example.primerApp;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.Random;



public class sql {
    public static String generarId(){
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
        String hecho="AGREGAR ";
        String medicoId=generarId();
        String espId=generarId();
        try{
            Connection conn = Conexion.getConnection();
            PreparedStatement pst= conn.prepareStatement("INSERT INTO Medico VALUES(?,?,?)");
            pst.setString(1, medicoId);
            pst.setString(2, nombre);
            pst.setString(3, apellido);
            pst.execute();
            pst.close();
            conn.close();
            hecho+="si";

            Connection conn2 = Conexion.getConnection();
            PreparedStatement pst2= conn2.prepareStatement("INSERT INTO Especialidad VALUES(?,?)");
            pst2.setString(1, espId);
            pst2.setString(2, esp);
            pst2.execute();
            pst2.close();
            conn2.close();
            hecho+="si";

            Connection conn3 = Conexion.getConnection();
            PreparedStatement pst3= conn3.prepareStatement("INSERT INTO MedicoEspecialidad VALUES(?,?)");
            pst3.setString(1, medicoId);
            pst3.setString(2, espId);
            pst3.execute();
            pst3.close();
            conn3.close();
            hecho+="si";
        }
        catch(Exception e){
            System.out.println(e);
            hecho+="no";
        }
        return hecho;
    }

    public static String borrar(){
        String hecho="BORRAR";
        try{
            Connection conn = Conexion.getConnection();
            PreparedStatement pst= conn.prepareStatement("delete from Fecha limit 1");
            pst.execute();
            pst.close();
            conn.close();
            hecho+="si";
        }
        catch(Exception e){
            System.out.println(e);
            hecho+="no";
        }
        return hecho;
    }
    
    public static String cambiar(){
        String hecho="CAMBIAR";
        try{
            Connection conn = Conexion.getConnection();
            PreparedStatement pst= conn.prepareStatement("update Fecha set dias='1000' limit 1");
            pst.execute();
            pst.close();
            conn.close();
            hecho+="si";
        }
        catch(Exception e){
            System.out.println(e);
            hecho+="no";
        }
        return hecho;
    }
    public static String[] leer(){
        String[] hecho=new String[30];
        try{
            Connection conn = Conexion.getConnection();
            Statement pst= conn.createStatement();
            ResultSet resultado= pst.executeQuery("select * from Fecha");
            int contar=1;
            while (resultado.next()) {
                //System.out.println(resultado.getString("dias"));
                hecho[contar]=resultado.getString("dias");
                contar++;
            }
            pst.close();
            conn.close();
            hecho[0]="Leer si";
        }
        catch(Exception e){
            System.out.println(e);
            hecho[0]="Leer si";
        }
        return hecho;
    }
}
