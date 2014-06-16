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
public class clsEgreso {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String concepto;
    private Double cantidadEgreso;
    private String fecha;
    private Integer idEgreso;
    private String nombre;
    
    public Integer getIdEgreso() {
        return idEgreso;
    }
    
    public void setIdEgreso(int idEgreso) {
        this.idEgreso = idEgreso;
    }
    
    public String getConcepto() {
        return concepto;
    }
    
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
    public Double getCantidadEgreso() {
        return cantidadEgreso;
    }
    
    public void setCantidadEgreso(Double cantidadEgreso) {
        this.cantidadEgreso = cantidadEgreso;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean insertarValorCuota(String idCajaOperacion, String concepto, String cantidadEgreso, String tipo_transaccion)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_egreso(id_caja_operacion, concepto, cantidad_egreso, fecha, tipo_transaccion)"                   
                    + " VALUES(" + idCajaOperacion + ", '" + concepto + "', " + cantidadEgreso + ", now(), '" + tipo_transaccion+ "')";           
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
    
    public Double obtenerValorEgresos(int idCajaAbierta, String tipo_transaccion)
    {          
        Double valorFacturado = 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT sum(cantidad_egreso) AS efectivo"
                    + " FROM ck_egreso"
                    + " WHERE id_caja_operacion = "+idCajaAbierta
                    + " AND estado = 'A'"
                    + " AND tipo_transaccion = '" + tipo_transaccion+ "'";
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                valorFacturado = bd.resultado.getDouble("efectivo");              
            }
            //return valorFacturado;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            valorFacturado = null;
        } 
        bd.desconectarBaseDeDatos();
        return valorFacturado;        
    }
    
    public ArrayList<clsEgreso>  consultaEgresosRealizadas(int idCajaAbierta, String tipo_transaccion){            
        ArrayList<clsEgreso> data = new ArrayList<clsEgreso>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_egreso, id_caja_operacion, concepto, cantidad_egreso, fecha, estado "           
                    + " FROM ck_egreso "                                           
                    + " WHERE id_caja_operacion = "+idCajaAbierta
                    + " AND estado = 'A'"
                    + " AND tipo_transaccion = '" + tipo_transaccion + "'";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsEgreso oListaTemporal = new clsEgreso();
                oListaTemporal.setIdEgreso(bd.resultado.getInt("id_egreso")); 
                oListaTemporal.setConcepto(bd.resultado.getString("concepto"));   
                oListaTemporal.setCantidadEgreso(bd.resultado.getDouble("cantidad_egreso"));                
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                               
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
    
    public ArrayList<clsEgreso>  consultaAdelantos(){            
        ArrayList<clsEgreso> data = new ArrayList<clsEgreso>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_rol_ingreso, "        
                    + " apellido1 || ' ' || apellido2 || ' ' || nombre1 || ' ' || nombre2 nombre, "
                    + " a.id_personal, cantidad, concepto, fecha_registro "
                    + " FROM ck_rol_ingreso AS a"
                    + " JOIN ck_personal AS b"
                    + " ON a.id_personal = b.id_personal"
                    + " WHERE a.estado = 'A'"
                    + " AND tipo_transaccion ='A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsEgreso oListaTemporal = new clsEgreso();
                oListaTemporal.setIdEgreso(bd.resultado.getInt("id_rol_ingreso")); 
                oListaTemporal.setConcepto(bd.resultado.getString("concepto"));   
                oListaTemporal.setCantidadEgreso(bd.resultado.getDouble("cantidad"));                
                oListaTemporal.setFecha(bd.resultado.getString("fecha_registro"));
                oListaTemporal.setNombre(bd.resultado.getString("nombre"));
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
    
    public boolean eliminarEgreso(int p_idEgreso)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_egreso"
                    + " SET estado = 'I'" 
                    + " WHERE id_egreso = " + p_idEgreso;      
           
            //System.out.println("SQL enviado:" + sql);
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
