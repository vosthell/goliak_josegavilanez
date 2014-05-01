/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kaiser
 */
public class clsEmpleado {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    public boolean comprobarCedula(String cedula)
    {
        boolean exito = false;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT codigo, cedula, name_completo"
                    + " FROM ck_nomina"
                    + " WHERE cedula = '" + cedula + "'";                  
            //System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
            int x=0;
            if(bd.resultado.next())
            {   
                do 
                { 
                    x++;
                }
                while(bd.resultado.next()); 
                if(x==0){
                    exito = false;
                }
                else
                {
                    exito = true;
                }           
            }
            else
            { 
                exito = false;
            }               
        }         
        catch(Exception ex)
        {
           System.out.print(ex);
           exito = false;
        }  
        bd.desconectarBaseDeDatos();
        return exito;
    }
    
    public boolean insertarRegistro(String cedula, String n1, String n2, String a1, String a2, String convencional, String celular, String direccion, String ciudad, String credito, String prov, String recinto, String email)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cliente"
                    + " (nombre1, nombre2,"
                    + " apellido1, apellido2, name_completo, tlf_convencional,"
                    + " tlf_celular, cedula, direccion, provincia, "
                    + " creditoMax, ciudad, id_recinto, fecha_registro, email)"
                    + " VALUES('" + n1 + "', '" + n2 + "', '" + a1 + "', '" + a2 + "', '" + a1 +" "+a2+" "+n1+" "+n2+"', "
                    + " '"+convencional+"', '"+celular+"', '"+cedula+"', '"+direccion+"', '"+prov+"'"
                    + ", "+credito+", '"+ciudad+"', "+recinto+", now(), '"+ email +"')";           
            System.out.println("SQL enviado:" + sql);
            bd.sentencia.executeUpdate(sql);
            exito = true; 
        }
        catch(SQLException e) //Captura posible error de SQL
        {
            System.out.println("Error SQL:" + e);
            exito = false;
        } 
        bd.desconectarBaseDeDatos();
        return exito;
    }  
}
