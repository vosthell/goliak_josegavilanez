/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class clsCupones {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private int id_cupones;
    private String fecha_registro;
    private int numero_cupon;
    private int id_documento;
    private String tipo_documento;
    private String name_completo;
    private String cedula;
    
    public int getIdCupones() {
        return id_cupones;
    }
    
    public void setIdCupones(int idCupones) {
        this.id_cupones = idCupones;
    }
    
    public String getFechaRegistro() {
        return fecha_registro;
    }
    
    public void setFechaRegistro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
    public int getNumeroCupon() {
        return numero_cupon;
    }
    
    public void setNumeroCupon(int numero_cupon) {
        this.numero_cupon = numero_cupon;
    }
    
    public int getIdDocumento() {
        return id_documento;
    }
    
    public void setIdDocumento(int id_documento) {
        this.id_documento = id_documento;
    }
    
    public String getTipoDocumento() {
        return tipo_documento;
    }
    
    public void setTipoDocumento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }
    
    public String getNameCompleto() {
        return name_completo;
    }
    
    public void setNameCompleto(String name_completo) {
        this.name_completo = name_completo;
    }
    
     public String getCedula() {
        return cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public boolean insertarCupon(int numero_cupon, int id_cliente, int id_documento, String tipo_documento)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cupones"
                    + " (numero_cupon, codigo, id_documento, tipo_documento)"
                    + " VALUES(" + numero_cupon + ", " + id_cliente + ", " + id_documento + ", '" + tipo_documento + "')";           
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
    
    
     public ArrayList<clsCupones>  consultarDataCupones(int id_documento){            
        ArrayList<clsCupones> data = new ArrayList<clsCupones>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cupones id_cupones, "
                        + " a.fecha_registro fecha_registro,"
                        + " numero_cupon,"
                        + " id_documento, "
                        + " tipo_documento,"
                        + " name_completo,"
                        + " cedula"
                    + " FROM ck_cupones AS a"
                    + " JOIN ck_cliente AS b ON a.codigo = b.codigo "       
                    + " WHERE a.id_documento=" + id_documento
                    + " AND a.estado = 'A'";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCupones oListaTemporal = new clsCupones();
                
                oListaTemporal.setIdCupones(bd.resultado.getInt("id_cupones"));                
                oListaTemporal.setFechaRegistro(bd.resultado.getString("fecha_registro"));
                oListaTemporal.setNumeroCupon(bd.resultado.getInt("numero_cupon"));  
                oListaTemporal.setIdDocumento(bd.resultado.getInt("id_documento")); 
                oListaTemporal.setTipoDocumento(bd.resultado.getString("tipo_documento")); 
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo")); 
                oListaTemporal.setCedula(bd.resultado.getString("cedula")); 
                data.add(oListaTemporal);
            }
            //return data;
            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            data = null;
        }         
        bd.desconectarBaseDeDatos();
        return data;
    }
     
     public boolean registrarReimpresion(int idCupon, String id_user, String justificacion)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cupones"
                    + " SET reimpresion_estado = '1', "
                    + " reimpresion_id_usuario = " + id_user + ","
                    + " reimpresion_fecha = NOW(),"
                    + " reimpresion_justificacion = '" + justificacion+"'"
                    + " WHERE id_cupones = " + idCupon;      
           
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
     
    public String obtenerValorReimpresion(String idDocumento)
    {          
        String reimpresion = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT reimpresion_estado" 
                    + " FROM ck_cupones " 
                    + " WHERE id_documento = " + idDocumento;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                reimpresion = bd.resultado.getString("reimpresion_estado");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            reimpresion = "";
        }     
        bd.desconectarBaseDeDatos();
        return reimpresion;
    }
}
