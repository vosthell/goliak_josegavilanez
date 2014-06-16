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
public class clsPlazo {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    public ArrayList<clsComboBox>  consultarPlazo(){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_plazo, descripcion"
                    + " FROM ck_plazo"
                    + " WHERE estado = 'A' "
                    + " AND TRANSACCION = 'NOTA DE ENTREGA'"
                    + " ORDER BY id_plazo";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_plazo"),bd.resultado.getString("descripcion"));
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
    
     public ArrayList<clsComboBox>  consultarPlazoFacturas(){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_plazo, descripcion"
                    + " FROM ck_plazo"
                    + " WHERE estado = 'A' "
                    + " AND TRANSACCION = 'FACTURA'"
                    + " ORDER BY id_plazo";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_plazo"),bd.resultado.getString("descripcion"));
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
}
