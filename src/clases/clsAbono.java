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
public class clsAbono {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String fecha_abono;
    private double valor_abono;
    
    public String getFechaAbono()
    {
        return fecha_abono;
    }
    
    public void setFechaAbono(String fecha_abono)
    {
        this.fecha_abono = fecha_abono;
    }
    
    public double getValorAbono()
    {
        return valor_abono;
    }
    
    public void setValorAbono(double valor_abono)
    {
        this.valor_abono = valor_abono;
    }
    
    public ArrayList<clsAbono> obtenerDataPagoPendiente(int idNotaEntrega)
    {       
        ArrayList<clsAbono> data = new ArrayList<clsAbono>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT fecha_abono, valor_abono "
                    + " FROM CK_ABONO" 
                    + " WHERE ID_CABECERA_MOVI = " + idNotaEntrega 
                    + " AND estado_pago = 'N'" 
                    + " ORDER BY numero_abono ASC" 
                    + " limit 1";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);               
             
            while(bd.resultado.next())
            { 
                clsAbono oListaTemporal = new clsAbono();
                oListaTemporal.setFechaAbono(bd.resultado.getString("fecha_abono"));
                oListaTemporal.setValorAbono(bd.resultado.getDouble("valor_abono"));

                //oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                data.add(oListaTemporal);
            }           
            bd.resultado.close();
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            //data = null;
        } 
        bd.desconectarBaseDeDatos();
        return data;
     }
    
    public boolean insertarAbono(int num, String fecha_abono, double valor, int id_cabecera_movi)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_abono"
                    + " (numero_abono, fecha_abono, valor_abono, id_cabecera_movi)"
                    + " VALUES(" + num +", '" + fecha_abono + "', " + valor+ ", " + id_cabecera_movi + ")";           
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
    
     public boolean borrarAbonos(int id_cabecera_movi)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "UPDATE ck_abono "
                    + "SET estado = 'I'"
                    + "WHERE id_cabecera_movi = " + id_cabecera_movi;
                           
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


/*select id_cabecera_movi, total_interes,efectivo, total-efectivo, round(CAST( ((total_interes-efectivo)-(total-efectivo))*100/(total-efectivo) AS NUMERIC ),2 )
from ck_notas_de_entrega
where tipo='C'
and (total-efectivo)>0


UPDATE ck_notas_de_entrega
set porcentaje_interes = round(CAST( ((total_interes-efectivo)-(total-efectivo))*100/(total-efectivo) AS NUMERIC ),2 )
where tipo='C'
and (total-efectivo)>0

UPDATE ck_notas_de_entrega
set porcentaje_interes = 0.00
where porcentaje_interes is null*/
