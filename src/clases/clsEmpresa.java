/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author Kaiser
 */
public class clsEmpresa {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    public ArrayList<clsComboBox>  consultarEmpresas(){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>(); 
        try{
            bd.conectarBaseDeDatos2();
            sql = "SELECT id_empresa, nombre_empresa, ciudad_empresa"
                    + " FROM ck_empresa"
                    + " WHERE estado = 'A' "
                    + " ORDER BY id_empresa";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_empresa"), bd.resultado.getString("nombre_empresa") + "(" + bd.resultado.getString("ciudad_empresa") + ")");
                    data.add(oListaTemporal);
                }
                while(bd.resultado.next()); 
                //return data;
            }
            else
            { 
                data = null;
            }            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            data = null;
        } 
        bd.desconectarBaseDeDatos();
        return data;
    }
    
    public int consultaEmpresaDefault()
    {       
         int cantidad = 0;
         try{
            bd.conectarBaseDeDatos2();
            sql = "SELECT id_empresa "
                    + "  FROM ck_empresa"
                    + " WHERE principal = 'S'";
            //System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
             
            if(bd.resultado.next())
            {               
                cantidad = bd.resultado.getInt("id_empresa");               
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
    
    public String consultaIPEmpresaDefault()
    {       
         String cantidad = "";
         try{
            bd.conectarBaseDeDatos2();
            sql = "SELECT ip "
                    + "  FROM ck_empresa"
                    + " WHERE principal = 'S'";
            //System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
             
            if(bd.resultado.next())
            {               
                cantidad = bd.resultado.getString("ip");               
            }
            else
            { 
                cantidad = "";
            }            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            cantidad = "";
        }           
        bd.desconectarBaseDeDatos();
        return cantidad;
    } 
    
    public String consultaIPEmpresaSeleccionada(String idEmpresa)
    {       
         String cantidad = "";
         try{
            bd.conectarBaseDeDatos2();
            sql = "SELECT ip "
                    + "  FROM ck_empresa"
                    + " WHERE id_empresa = " + idEmpresa;
            //System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
             
            if(bd.resultado.next())
            {               
                cantidad = bd.resultado.getString("ip");               
            }
            else
            { 
                cantidad = "";
            }            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            cantidad = "";
        }           
        bd.desconectarBaseDeDatos();
        return cantidad;
    } 
    
   
}
