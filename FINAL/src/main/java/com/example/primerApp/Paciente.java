package com.example.primerApp;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.Random;



public class Paciente {
    public static String randomrId(){
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

    public static String agregarPaciente(
        String Nombre,
        String Apellido,
        String Fecha,
        String Domicilio,
        String Telefono,
        String Correo,
        String Observaciones,
        String Pais,
        String Pago,
        String Turno
    )
    
    {
        String hecho="Añadir";
        String pacienteId=randomrId();
        try{
            Connection connect = Conexion.getConnection();
            PreparedStatement sentencia1= connect.prepareStatement("INSERT INTO Paciente VALUES(?,?,?,?,?,?,?,?,?)");
            sentencia1.setString(1, pacienteId);
            sentencia1.setString(2, Nombre);
            sentencia1.setString(3, Apellido);
            sentencia1.setString(4, Fecha);
            sentencia1.setString(5, Domicilio);
            sentencia1.setString(6, randomrId());
            sentencia1.setString(7, Telefono);
            sentencia1.setString(8, Correo);
            sentencia1.setString(9, Observaciones);
            sentencia1.execute();
            sentencia1.close();
            connect.close();
            hecho+="si";

            Connection connect2 = Conexion.getConnection();
            PreparedStatement sentencia2= connect2.prepareStatement("INSERT INTO Pais VALUES(?,?)");
            sentencia2.setString(1, randomrId());
            sentencia2.setString(2, Pais);
            sentencia2.execute();
            sentencia2.close();
            connect2.close();
            hecho+="si";

            Connection connect3 = Conexion.getConnection();
            PreparedStatement sentencia3= connect3.prepareStatement("INSERT INTO TurnoPaciente VALUES(?,?,?)");
            sentencia3.setString(1, randomrId());
            sentencia3.setString(2, randomrId());
            sentencia3.setString(3, randomrId());
            sentencia3.execute();
            sentencia3.close();
            connect3.close();
            hecho+="si";

            Connection connect4 = Conexion.getConnection();
            PreparedStatement sentencia4= connect4.prepareStatement("INSERT INTO PacientePagoRef VALUES(?,?,?)");
            sentencia4.setString(1, randomrId());
            sentencia4.setString(2, randomrId());
            sentencia4.setString(3, "123");
            sentencia4.execute();
            sentencia4.close();
            connect4.close();
            hecho+="si";

            Connection connect5 = Conexion.getConnection();
            PreparedStatement sentencia5= connect5.prepareStatement("INSERT INTO Turno VALUES(?,?,?,?)");
            sentencia5.setString(1, randomrId());
            sentencia5.setString(2, Turno);
            sentencia5.setString(3, "PENDIENTE");
            sentencia5.setString(4, "PENDIENTE");

            sentencia5.execute();
            sentencia5.close();
            connect5.close();
            hecho+="si";

            Connection connect6 = Conexion.getConnection();
            PreparedStatement sentencia6= connect6.prepareStatement("INSERT INTO TurnoEstado VALUES(?,?)");
            sentencia6.setString(1, randomrId());
            sentencia6.setString(2, "PENDIENTE");
            sentencia6.execute();
            sentencia6.close();
            connect6.close();
            hecho+="si";

            Connection connect7 = Conexion.getConnection();
            PreparedStatement sentencia7= connect7.prepareStatement("INSERT INTO Pago VALUES(?,?,?,?,?,?)");
            sentencia7.setString(1, randomrId());
            sentencia7.setString(2, "DONATIVO");
            sentencia7.setString(3, "06/2022");
            sentencia7.setString(4, Pago);
            sentencia7.setString(5, "PAGADO");
            sentencia7.setString(6, "PAGADO");
            sentencia7.execute();
            sentencia7.close();
            connect7.close();
            hecho+="si";

            Connection connect8 = Conexion.getConnection();
            PreparedStatement sentencia8= connect8.prepareStatement("INSERT INTO Concepto VALUES(?,?)");
            sentencia8.setString(1, randomrId());
            sentencia8.setString(2, "DONATIVO");
            sentencia8.execute();
            sentencia8.close();
            connect8.close();
            hecho+="si";        

        }
        catch(Exception e){
            System.out.println(e);
            hecho+="no";
        }
        return hecho;
    }



    public static String borrar(String id){
        String hecho="Borrar ";
        try{
            Connection connect = Conexion.getConnection();
            PreparedStatement sentencia1= connect.prepareStatement("delete from Paciente where idPaciente=?");
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
    public static String cambiar(String id, String Nombre, String Apellido, String fecha, String Dom, String Pais, String Tel, String Mail){
        String hecho="Cambiar ";
        try{
            Connection connect = Conexion.getConnection();
            PreparedStatement sentencia1= connect.prepareStatement("update Paciente set nombre=?, apellidos=?, fNacimiento=?, domicilio=?, telefono=?, email=? where idPaciente = ?");
            sentencia1.setString(1, Nombre);
            sentencia1.setString(2, Apellido);
            sentencia1.setString(3, fecha);
            sentencia1.setString(4, Dom);
            sentencia1.setString(5, Tel);
            sentencia1.setString(6, Mail);
            sentencia1.setString(7, id);            
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
        String leido="<table border='1'><tr><td>idPaciente</td><td>nombre</td><td>apellidos</td>    <td>fNacimiento</td> <td>fNacimiento</td> <td>domicilio</td> <td>telefono</td>  <td>email</td>    <td></td><td></td></tr>";
      
        try{
            Connection connect = Conexion.getConnection();
            Statement sentencia1= connect.createStatement();
            ResultSet resultado= sentencia1.executeQuery("select * from Paciente");

            while (resultado.next()) {
                leido+="<tr><td>"+resultado.getString("idPaciente")+"</td><td>"+resultado.getString("nombre")+"</td><td>"+resultado.getString("apellidos")+"</td><td>"+resultado.getString("fNacimiento")+"</td><td>"+resultado.getString("domicilio")+"</td><td>"+resultado.getString("telefono")+"</td><td>"+resultado.getString("email")+"</td>                                 <td><a href='/editarPac?id="+resultado.getString("idPaciente")+"'>Editar</a></td><td><a href='/borrarPac?id="+resultado.getString("idPaciente")+"'><font color='red'>Borar</font></a></td></tr>";              
 
            }
            leido+="</table>";
            sentencia1.close();
            connect.close();
            
        }
        catch(Exception e){
            System.out.println(e);

        }
        return leido;
    }
    public static String leerPago(){
        String leido="<table border='1'><tr><td>idPago</td><td>concepto</td><td>fecha</td>    <td>monto</td> <td>estado</td> <td>observacion</td>";
      
        try{
            Connection connect = Conexion.getConnection();
            Statement sentencia= connect.createStatement();
            ResultSet resultado= sentencia.executeQuery("select * from Pago");

            while (resultado.next()) {
                leido+="<tr><td>"+resultado.getString("idPago")+"</td><td>"+resultado.getString("concepto")+"</td><td>"+resultado.getString("fecha")+"</td><td>"+resultado.getString("monto")+"</td><td>"+resultado.getString("estado")+"</td><td>"+resultado.getString("observacion")+"</td></tr>";
  
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
