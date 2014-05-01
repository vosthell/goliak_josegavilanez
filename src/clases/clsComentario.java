/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ckaiser
 */
public class clsComentario {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String comentario;
    private String fecha_registro;
    
    public String getComentario() {
        return comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public String getFechaRegistro() {
        return fecha_registro;
    }
    
    public void setFechaRegistro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
    public ArrayList<clsComentario> consultaDataComentarios(int id_cabecera_movi)
    {
        ArrayList <clsComentario> data = new ArrayList<clsComentario>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT comentario, fecha_registro"
                    + " FROM ck_cartera_comentario"
                    + " WHERE id_cabecera_movi = " + id_cabecera_movi
                    + " AND estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsComentario oListaTemporal = new clsComentario();
                
                oListaTemporal.setComentario(bd.resultado.getString("comentario")); 
                oListaTemporal.setFechaRegistro(bd.resultado.getString("fecha_registro")); 
                
                data.add(oListaTemporal);
            }
            bd.resultado.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        bd.desconectarBaseDeDatos();
        return data;        
    }
    
   public int consultarNumeroComentarios(int id_cabecera_movi)
    {       
         int cantidad = 0;
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT count(*) valor "
                    + "  FROM ck_cartera_comentario"
                    + " WHERE id_cabecera_movi = " + id_cabecera_movi
                    + " AND estado = 'A'";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
             
            if(bd.resultado.next())
            {               
                cantidad = bd.resultado.getInt("valor");               
            }
            else
            { 
                cantidad = 0;
            }            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            cantidad = 0;
        }           
        bd.desconectarBaseDeDatos();
        return cantidad;
    } 
    
    
}
