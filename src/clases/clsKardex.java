/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.SQLException;
import java.util.ArrayList;
import index.main;

/**
 *
 * @author Kaiser
 */
public class clsKardex {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private int id_kardex;
    private String fecha;
    private String descripcion;
    private double unidades;
    private String estado;
    private String id_empresa;
    private String nombre_empresa;
    private double valor;
    private double costo;
    private String tipo_mov;
    private String name;
    private int id_documento;
    private int id_localidad;
    
    public int getIdKardex() {
        return id_kardex;
    }
    
    public void setIdKardex(int id_kardex) {
        this.id_kardex = id_kardex;
    }
    
    public int getIdDocumento() {
        return id_documento;
    }
    
    public void setIdDocumento(int id_documento) {
        this.id_documento = id_documento;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public double getUnidades() {
        return unidades;
    }
    
    public void setUnidades(double unidades) {
        this.unidades = unidades;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getIdEmpresa() {
        return id_empresa;
    }
    
    public void setIdEmpresa(String id_empresa) {
        this.id_empresa = id_empresa;
    }
    
    public String getNombreEmpresa() {
        return nombre_empresa;
    }
    
    public void setNombreEmpresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public double getCosto() {
        return costo;
    }
    
    public void setCosto(double costo) {
        this.costo = costo;
    }   
    
    public String getTipoMov() {
        return tipo_mov;
    }
    
    public void setTipoMov(String tipo_mov) {
        this.tipo_mov = tipo_mov;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getIdLocalidad() {
        return id_localidad;
    }
    
    public void setIdLocalidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }
    
   public ArrayList<clsKardex>  consultarMovimientos(String id_items){            
        ArrayList<clsKardex> data = new ArrayList<clsKardex>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_kardex,fecha, descripcion, unidades, a.estado estado, id_items,"
                        + " id_empresa, nombre_empresa, valor, costo, tipo_mov, name, id_documento, id_localidad"
                    + " FROM ck_kardex AS a"
                    + " JOIN ck_usuario AS b ON a.id_usuario = b.id_usuario"
                    + " WHERE id_items =" + id_items
                    + " AND a.estado = 'A' "
                    + " ORDER BY fecha";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);              
            while(bd.resultado.next()){
                clsKardex oListaTemporal = new clsKardex();
                oListaTemporal.setIdKardex(bd.resultado.getInt("id_kardex"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));
                oListaTemporal.setUnidades(bd.resultado.getDouble("unidades"));
                oListaTemporal.setIdEmpresa(bd.resultado.getString("id_empresa"));
                oListaTemporal.setNombreEmpresa(bd.resultado.getString("nombre_empresa"));
                oListaTemporal.setValor(bd.resultado.getDouble("valor"));
                oListaTemporal.setCosto(bd.resultado.getDouble("costo"));
                oListaTemporal.setTipoMov(bd.resultado.getString("tipo_mov"));
                oListaTemporal.setName(bd.resultado.getString("name"));
                oListaTemporal.setIdDocumento(bd.resultado.getInt("id_documento"));
                oListaTemporal.setIdLocalidad(bd.resultado.getInt("id_localidad"));
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
    
    public boolean insertarRegistro(String fecha, String descripcion, Double unidades, 
            int idItems, String tipo_mov, int id_documento, String localidad)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_kardex"
                    + " (fecha, descripcion, unidades, id_items, "
                    + " id_empresa, nombre_empresa, valor, tipo_mov, id_documento, id_usuario, id_localidad)"
                    + " VALUES('" + fecha + "', '" + descripcion + "', " + unidades + ", " + idItems 
                    + " , '-', '-', 0.00, '" + tipo_mov + "', " + id_documento + ", " + main.idUser + ", " + localidad + ")";           
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
    
    public boolean insertarKardex(int idItem, String descripcion, String unidades, 
            String id_empresa, String nombre_empresa, String valor, 
            Double costo, String tipo_mov, Integer id_documento, Integer localidad)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_kardex"
                    + " (fecha, descripcion, unidades, id_items,"
                    + " id_empresa, nombre_empresa, valor, costo, tipo_mov, "
                    + " id_documento, id_usuario, id_localidad)"
                    + " VALUES(now(), '"+descripcion+"', "+unidades+", "+idItem+","
                    + " '" + id_empresa+ "', '" + nombre_empresa + "', " + valor + ", "
                    + costo + ", '" + tipo_mov+ "', " + id_documento + ", "+ main.idUser+", "
                    + localidad + ")";           
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
