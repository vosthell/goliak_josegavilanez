/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

//import clases.clsConexion;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kaiser
 */
public class clsCaja {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private double valor_apertura;
    private int id_caja_operacion;
    private String nombre;
    private String fecha_apertura;
    private String fecha_cierre;
    private double ingresos;
    private double recibos_pago;
    private double abonos;
    private double egresos;
    private double total_facturado;
    private double valor_contado;
    private double valor_pagos_factura;
    private double diferencia;
    private String observacion;
    private String cierre;
    
    public String getCierre()
    {
        return cierre;
    }
    
    public void setCierre(String cierre) {
        this.cierre = cierre;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getObservacion()
    {
        return observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(double diferencia) {
        this.diferencia = diferencia;
    }
    
     public double getValorPagosFactura() {
        return valor_pagos_factura;
    }

    public void setValorPagosFactura(double valor_pagos_factura) {
        this.valor_pagos_factura = valor_pagos_factura;
    }
    
    public double getAbonos() {
        return abonos;
    }

    public void setAbonos(double abonos) {
        this.abonos = abonos;
    }
    
    public double getValorContado() {
        return valor_contado;
    }

    public void setValorContado(double valor_contado) {
        this.valor_contado = valor_contado;
    }
    
    public double getTotalFacturado() {
        return total_facturado;
    }

    public void setTotalFacturado(double total_facturado) {
        this.total_facturado = total_facturado;
    }
    
    public double getEgresos() {
        return egresos;
    }

    public void setEgresos(double egresos) {
        this.egresos = egresos;
    }
    
    public double getRecibosPago() {
        return recibos_pago;
    }

    public void setRecibosPago(double recibos_pago) {
        this.recibos_pago = recibos_pago;
    }
    
    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }
    
    public String getFechaApertura()
    {
        return fecha_apertura;
    }
    
    public void setFechaApertura(String fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }
    
    public String getFechaCierre()
    {
        return fecha_cierre;
    }
    
    public void setFechaCierre(String fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }
    
    public double getValorApertura() {
        return valor_apertura;
    }

    public void setValorApertura(double valor_apertura) {
        this.valor_apertura = valor_apertura;
    }
    
    public int getIdCajaOperacion()
    {
        return id_caja_operacion;
    }
    
    public void setIdCajaOperacion(int id_caja_operacion) {
        this.id_caja_operacion = id_caja_operacion;
    }
    
    public boolean consultarCajaAbierta(String idUsuario)
    {
        boolean exito = false;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_caja_operacion, id_usuario, apertura, fecha_apertura, cierre,"  
                    + " fecha_cierre, valor_apertura"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_usuario = " + idUsuario 
                    + " AND cierre='N'"
                    + " AND apertura='S'";
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
    
    public boolean registrarAperturaCaja(String idUsuario, String valor, String cajero, String facturacionManual, String idFacturero, String observacion)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "INSERT INTO ck_caja_operacion(id_usuario, apertura, fecha_apertura, valor_apertura, id_cajero, "
                    + "facturacion_manual, id_facturero, observacion)"
                    + " VALUES (" + idUsuario + ", 'S', now(), " + valor + ", " + cajero + ", "
                    + "'" + facturacionManual + "', "+idFacturero+", '" + observacion + "')";            
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
    
     public ArrayList<clsCaja> consultarDataCajaAbierta(String idUser)
     {       
        ArrayList<clsCaja> data = new ArrayList<clsCaja>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_caja_operacion, id_usuario, "
                    + " apertura, fecha_apertura, cierre, "
                    + " fecha_cierre, valor_apertura"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_usuario = " + idUser
                    + " AND cierre='N'"
                    + " AND apertura='S'";
            /*sql = "SELECT id_caja_operacion, id_usuario, "
                    + " apertura, fecha_apertura, cierre, "
                    + " fecha_cierre, valor_apertura"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_caja_operacion = 359";*/
                    //+ " AND cierre='N'"
                    //+ " AND apertura='S'";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCaja oListaTemporal = new clsCaja();
                    oListaTemporal.setValorApertura(bd.resultado.getDouble("valor_apertura"));
                    oListaTemporal.setIdCajaOperacion(bd.resultado.getInt("id_caja_operacion"));
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
     
     public ArrayList<clsCaja> consultarDataOperacionesCaja()
     {       
        ArrayList<clsCaja> data = new ArrayList<clsCaja>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_caja_operacion, a.id_usuario, b.name, apertura, cierre, "
                       + " valor_apertura, valor_contado, valor_facturado, "
                       + " diferencia, id_cajero, valor_pagos, facturacion_manual, primer_valor_manual, "
                       + " primera_vez_manual, id_facturero, valor_egresos, valor_recibos, "
                       + " fecha_apertura, fecha_cierre, observacion, valor_ingreso"
                  + " FROM ck_caja_operacion AS a"
                  + " JOIN ck_usuario AS b ON a.id_usuario = b.id_usuario"
                  + " WHERE a.id_usuario != 1"
                  + " AND fecha_apertura is not null"
                  ;
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCaja oListaTemporal = new clsCaja();
                    oListaTemporal.setValorApertura(bd.resultado.getDouble("valor_apertura"));
                    oListaTemporal.setIdCajaOperacion(bd.resultado.getInt("id_caja_operacion"));
                    oListaTemporal.setDiferencia(bd.resultado.getDouble("diferencia"));
                    oListaTemporal.setEgresos(bd.resultado.getDouble("valor_egresos"));
                    oListaTemporal.setFechaCierre(bd.resultado.getString("fecha_cierre"));
                    oListaTemporal.setFechaApertura(bd.resultado.getString("fecha_apertura"));
                    oListaTemporal.setIngresos(bd.resultado.getDouble("valor_ingreso"));
                    oListaTemporal.setObservacion(bd.resultado.getString("observacion"));
                    oListaTemporal.setRecibosPago(bd.resultado.getDouble("valor_recibos"));
                    oListaTemporal.setTotalFacturado(bd.resultado.getDouble("valor_facturado"));
                    oListaTemporal.setValorContado(bd.resultado.getDouble("valor_contado"));
                    oListaTemporal.setCierre(bd.resultado.getString("cierre"));
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
     
      public ArrayList<clsCaja> consultarDataOperacionesCajaID(int idCajaOperacion)
     {       
        ArrayList<clsCaja> data = new ArrayList<clsCaja>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_caja_operacion, a.id_usuario, b.name, apertura, cierre, "
                       + " valor_apertura, valor_contado, valor_facturado, "
                       + " diferencia, id_cajero, valor_pagos, facturacion_manual, primer_valor_manual, "
                       + " primera_vez_manual, id_facturero, valor_egresos, valor_recibos, "
                       + " fecha_apertura, fecha_cierre, observacion, valor_ingreso, valor_pagos_factura"
                  + " FROM ck_caja_operacion AS a"
                  + " JOIN ck_usuario AS b ON a.id_usuario = b.id_usuario"
                  + " WHERE id_caja_operacion = " + idCajaOperacion;
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCaja oListaTemporal = new clsCaja();
                    oListaTemporal.setValorApertura(bd.resultado.getDouble("valor_apertura"));
                    oListaTemporal.setIdCajaOperacion(bd.resultado.getInt("id_caja_operacion"));
                    oListaTemporal.setDiferencia(bd.resultado.getDouble("diferencia"));
                    oListaTemporal.setEgresos(bd.resultado.getDouble("valor_egresos"));
                    oListaTemporal.setFechaCierre(bd.resultado.getString("fecha_cierre"));
                    oListaTemporal.setFechaApertura(bd.resultado.getString("fecha_apertura"));
                    oListaTemporal.setIngresos(bd.resultado.getDouble("valor_ingreso"));
                    oListaTemporal.setObservacion(bd.resultado.getString("observacion"));
                    oListaTemporal.setRecibosPago(bd.resultado.getDouble("valor_recibos"));
                    oListaTemporal.setTotalFacturado(bd.resultado.getDouble("valor_facturado"));
                    oListaTemporal.setValorContado(bd.resultado.getDouble("valor_contado"));
                    oListaTemporal.setAbonos(bd.resultado.getDouble("valor_pagos"));
                    oListaTemporal.setValorPagosFactura(bd.resultado.getDouble("valor_pagos_factura"));
                    oListaTemporal.setCierre(bd.resultado.getString("cierre"));
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
     
      public ArrayList<clsCaja> consultarDataOperacionesCajaFecha(String fecha_inicio, String fecha_fin)
     {       
        ArrayList<clsCaja> data = new ArrayList<clsCaja>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_caja_operacion, a.id_usuario, b.name nombre, apertura, cierre, "
                       + " valor_apertura, valor_contado, valor_facturado, "
                       + " diferencia, id_cajero, valor_pagos, facturacion_manual, primer_valor_manual, "
                       + " primera_vez_manual, id_facturero, valor_egresos, valor_recibos, "
                       + " fecha_apertura, fecha_cierre, observacion, valor_ingreso,"
                       + " valor_pagos_factura"
                  + " FROM ck_caja_operacion AS a"
                  + " JOIN ck_usuario AS b ON a.id_usuario = b.id_usuario"
                  + " WHERE a.id_usuario != 1"
                  + " AND fecha_apertura is not null"
                  + " AND fecha_apertura::date >= '" + fecha_inicio + "'"
                  + " AND fecha_cierre::date <= '" + fecha_fin + "'";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            while(bd.resultado.next())
            {
                clsCaja oListaTemporal = new clsCaja();
                oListaTemporal.setValorApertura(bd.resultado.getDouble("valor_apertura"));
                oListaTemporal.setIdCajaOperacion(bd.resultado.getInt("id_caja_operacion"));
                oListaTemporal.setDiferencia(bd.resultado.getDouble("diferencia"));
                oListaTemporal.setEgresos(bd.resultado.getDouble("valor_egresos"));
                oListaTemporal.setFechaCierre(bd.resultado.getString("fecha_cierre"));
                oListaTemporal.setFechaApertura(bd.resultado.getString("fecha_apertura"));
                oListaTemporal.setIngresos(bd.resultado.getDouble("valor_ingreso"));
                oListaTemporal.setObservacion(bd.resultado.getString("observacion"));
                oListaTemporal.setRecibosPago(bd.resultado.getDouble("valor_recibos"));
                oListaTemporal.setTotalFacturado(bd.resultado.getDouble("valor_facturado"));
                oListaTemporal.setValorContado(bd.resultado.getDouble("valor_contado"));
                oListaTemporal.setAbonos(bd.resultado.getDouble("valor_pagos"));
                oListaTemporal.setCierre(bd.resultado.getString("cierre"));
                oListaTemporal.setNombre(bd.resultado.getString("nombre"));
                oListaTemporal.setValorPagosFactura(bd.resultado.getDouble("valor_pagos_factura"));
                data.add(oListaTemporal);
            }
            /*if(bd.resultado.next())
            {   
                do 
                { 
                    clsCaja oListaTemporal = new clsCaja();
                    oListaTemporal.setValorApertura(bd.resultado.getDouble("valor_apertura"));
                    oListaTemporal.setIdCajaOperacion(bd.resultado.getString("id_caja_operacion"));
                    oListaTemporal.setDiferencia(bd.resultado.getDouble("diferencia"));
                    oListaTemporal.setEgresos(bd.resultado.getDouble("valor_egresos"));
                    oListaTemporal.setFechaCierre(bd.resultado.getString("fecha_cierre"));
                    oListaTemporal.setFechaApertura(bd.resultado.getString("fecha_apertura"));
                    oListaTemporal.setIngresos(bd.resultado.getDouble("valor_ingreso"));
                    oListaTemporal.setObservacion(bd.resultado.getString("observacion"));
                    oListaTemporal.setRecibosPago(bd.resultado.getDouble("valor_recibos"));
                    oListaTemporal.setTotalFacturado(bd.resultado.getDouble("valor_facturado"));
                    oListaTemporal.setValorContado(bd.resultado.getDouble("valor_contado"));
                    oListaTemporal.setCierre(bd.resultado.getString("cierre"));
                    data.add(oListaTemporal);
                }
                while(bd.resultado.next());                 
            }
            else
            { 
                data = null;
            }    */        
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            data = null;
        } 
        bd.desconectarBaseDeDatos();
        return data;
     }
     
    public boolean registrarCierreCaja(String idUsuario, String facturado, String contado, String diferencia, 
                                        Double pagos, Double pagosFactura, Double egresos, Double recibosPago, Double ingresos)
    {
        boolean exito;
        try
        {
            String sql;
            bd.conectarBaseDeDatos();            
             sql = "UPDATE ck_caja_operacion"
                     + " SET cierre = 'S', "
                     + " valor_contado = " + contado + ","
                     + " valor_facturado = " + facturado + ","
                     + " valor_pagos = "+ pagos +","
                     + " valor_pagos_factura = "+ pagosFactura +","
                     + " diferencia = " + diferencia + ","
                     + " fecha_cierre = now(),"
                     + " valor_egresos = " + egresos + ","
                     + " valor_recibos = " + recibosPago + ","
                     + " valor_ingreso = " + ingresos
                     + " WHERE id_usuario = " + idUsuario
                     + " AND cierre = 'N'"
                     + " AND apertura = 'S'";
            
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
    /*public boolean registrarCierreCaja2(String idUsuario, String facturado, String contado, String diferencia, 
                                        Double pagos, Double pagosFactura, Double egresos, Double recibosPago, 
                                        Double ingresos, String cajaAbierta)
    {
        boolean exito;
        try
        {
            String sql;
            bd.conectarBaseDeDatos();            
             sql = "UPDATE ck_caja_operacion"
                     + " SET cierre = 'S', "
                     + " valor_contado = " + contado + ","
                     + " valor_facturado = " + facturado + ","
                     + " valor_pagos = "+ pagos +","
                     + " valor_pagos_factura = "+ pagosFactura +","
                     + " diferencia = " + diferencia + ","
                     //+ " fecha_cierre = now(),"
                     + " valor_egresos = " + egresos + ","
                     + " valor_recibos = " + recibosPago + ","
                     + " valor_ingreso = " + ingresos
                     + " WHERE id_usuario = " + idUsuario
                     //+ " AND cierre = 'N'"
                     //+ " AND apertura = 'S'"
                     + " AND id_caja_operacion = " + cajaAbierta;
            
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
    }*/
      
    public ArrayList<clsComboBox>  consultarCajero(){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cajero, nombre"
                    + " FROM ck_cajero"
                    + " WHERE estado = 'I' "
                    + " ORDER BY nombre";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_cajero"),bd.resultado.getString("nombre"));
                data.add(oListaTemporal);
            }
            bd.resultado.close();
              
            /*if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_cajero"),bd.resultado.getString("nombre"));
                    data.add(oListaTemporal);
                }
                while(bd.resultado.next()); 
                //return data;
            }
            else
            { 
                data = null;
            }     */       
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            data = null;
        }         
        bd.desconectarBaseDeDatos();
        return data;
    }   
    
    public String obtenerCajero(String idUser)
    {          
        String nombreCajero = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT b.nombre, a.id_cajero"
                    + " FROM ck_caja_operacion AS a, ck_cajero AS b"
                    + " WHERE a.id_usuario = '" + idUser + "'"
                    + " AND a.id_cajero = b.id_cajero"
                    + " AND apertura='S'";
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getString("nombre");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = null;
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public String obtenerIdCajero(String idUser)
    {          
        String nombreCajero=""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cajero"
                    + " FROM ck_caja_operacion AS a, ck_cajero AS b"
                    + " WHERE a.id_usuario = '" + idUser + "'"
                    + " AND a.id_cajero = b.id_cajero"
                    + " AND a.apertura='S'";
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getString("id_cajero");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = null;
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public String obtenerCajaAbierta(String idUser)
    {          
        String nombreCajero=""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_caja_operacion"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_usuario = '" + idUser + "'"
                    + " AND cierre = 'N'"
                    + " AND apertura = 'S'";
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getString("id_caja_operacion");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = null;
        } 
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public Double obtenerValorFacturado(int idCajaAbierta)
    {          
        Double valorFacturado= 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT sum(efectivo) AS efectivo"
                    + " FROM ck_cabecera_movi"
                    + " WHERE id_caja_operacion = "+idCajaAbierta
                    + " AND estado='A'";
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
    
    public Double obtenerValorDevolucionesVentas(int idCajaAbierta)
    {          
        Double valorFacturado= 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT sum(efectivo) AS efectivo"
                    + " FROM ck_cabecera_movi_devolucion"
                    + " WHERE id_caja_operacion = " + idCajaAbierta
                    + " AND estado='A'";
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
    
    public boolean modificarCaja(String idCajero, String estado)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cajero"
                    + " SET estado = '" + estado + "'"              
                    + " WHERE id_cajero = " + idCajero;      
           
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
    
    public Double obtenerValorPagos(int idCajaAbierta)
    {          
        Double valorFacturado = 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT sum(valor) AS efectivo"
                    + " FROM ck_pagos"
                    + " WHERE id_caja_operacion_cobra = "+idCajaAbierta
                    + " AND estado = 'A'";
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
    
    public Double obtenerValorPagosFactura(int idCajaAbierta)
    {          
        Double valorFacturado = 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT sum(valor) AS efectivo"
                    + " FROM ck_pagos_factura"
                    + " WHERE id_caja_operacion_cobra = "+idCajaAbierta
                    + " AND estado = 'A'";
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
    
    public Double obtenerValorRecibos(int idCajaAbierta)
    {          
        Double valorFacturado = 0.00; 
        try{
            bd.conectarBaseDeDatos();
            /*ANTES TENIA 'WHERE id_caja_operacion =' Y DEBE SER EL QUE COBRA*/
            sql = "SELECT sum(valor) AS efectivo"
                    + " FROM ck_pagos_recibo"
                    + " WHERE id_caja_operacion_cobra = "+idCajaAbierta
                    + " AND estado = 'A'";
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
    
    public String comprobarFacturacionManual(String idCajaAbierta)
    {          
        String dato = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT facturacion_manual"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_caja_operacion = " + idCajaAbierta;                    
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
               dato = bd.resultado.getString("facturacion_manual");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            dato = null;
        }     
        bd.desconectarBaseDeDatos();
        return dato;
    }
    
    /*public String comprobarPrimeraFacturacionManual(String idCajaAbierta)
    {          
        String dato = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT primera_vez_manual"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_caja_operacion = " + idCajaAbierta;                    
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
               dato = bd.resultado.getString("primera_vez_manual");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            dato = null;
        }     
        bd.desconectarBaseDeDatos();
        return dato;
    }*/
    
    /*public String obtenerPrimerValorFacturacionManual(String idCajaAbierta)
    {          
        String dato = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT primer_valor_manual"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_caja_operacion = " + idCajaAbierta;                    
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
               dato = bd.resultado.getString("primer_valor_manual");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            dato = null;
        }     
        bd.desconectarBaseDeDatos();
        return dato;
    }*/
    
    /*public boolean modificarPrimeraVezFactManual(String idCajaAbierta)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_caja_operacion"
                    + " SET primera_vez_manual = 'N'"                                     
                    + " WHERE id_caja_operacion = " + idCajaAbierta;            
            // System.out.println("SQL enviado:" + sql);
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
    }  */ 
   public int obtenerIdFacturero(String idCajaAbierta)
   {          
        int idFacturero = 0;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_facturero"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_caja_operacion = " + idCajaAbierta;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                idFacturero = bd.resultado.getInt("id_facturero");              
            }
            //return porcentaje;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            idFacturero = 0;
        }  
        bd.desconectarBaseDeDatos();
        return idFacturero;
    }
   
   public int obtenerReciboPagoActual()
   {          
        int idFacturero = 0;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT secuencia_actual"
                    + " FROM ck_facturero"
                    + " WHERE tipo = 'R'";
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                idFacturero = bd.resultado.getInt("secuencia_actual");              
            }
            //return porcentaje;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            idFacturero = 0;
        }  
        bd.desconectarBaseDeDatos();
        return idFacturero;
    }
   
   public boolean actualizarReciboPago()
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_facturero"
                    + " SET secuencia_actual = secuencia_actual + 1"                                     
                    + " WHERE tipo = 'R'";            
            // System.out.println("SQL enviado:" + sql);
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