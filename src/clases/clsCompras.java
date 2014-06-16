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
public class clsCompras {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String nombre_proveedor;
    private double total;
    private String estado_tramite;
    private int id_compras;
    private String ruc;
    private String tipo_documento;
    private String nombre_elaborador;
    private String nombre_recibe;
    private String factura_referencia;    
    private double base_tarifa_iva;
    private double base_tarifa_0;
    private double descuento;
    private double iva;
    private String fecha;   
    private String fecha_registro;  
    private String fecha_recibe;  
    private double irbp;
    private double baseIce;
    private double ice;
    private String tipo; 
    private String autorizacion; 
    private int id_cuenta;
        
    public int getIdCompras()
    {
        return id_compras;
    }
    
    public void setIdCompras(int id_compras)
    {
        this.id_compras = id_compras;
    }
    
    public String getNombreProveedor()
    {
        return nombre_proveedor;
    }
    
    public void setNombreProveedor(String nombre_proveedor)
    {
        this.nombre_proveedor = nombre_proveedor;
    }
    
    public String getNombreElaborador()
    {
        return nombre_elaborador;
    }
    
    public void setNombreElaborador(String nombre_elaborador)
    {
        this.nombre_elaborador = nombre_elaborador;
    }
    
    public String getNombreRecibe()
    {
        return nombre_recibe;
    }
    
    public void setNombreRecibe(String nombre_recibe)
    {
        this.nombre_recibe = nombre_recibe;
    }
    
    public String getRUC()
    {
        return ruc;
    }
    
    public void setRUC(String ruc)
    {
        this.ruc = ruc;
    }
    
    public String getTipo()
    {
        return tipo;
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
    
    public Double getTotal()
    {
        return total;
    }
    
    public void setTotal(Double total)
    {
        this.total = total;
    }
    
    public String getEstadoTramite()
    {
        return estado_tramite;
    }
    
    public void setEstadoTramite(String estado_tramite)
    {
        this.estado_tramite = estado_tramite;
    }
    
    public Double getBaseTarifaIva()
    {
        return base_tarifa_iva;
    }
    
    public void setBaseTarifaIva(Double base_tarifa_iva)
    {
        this.base_tarifa_iva = base_tarifa_iva;
    }
    
    public Double getBaseTarifaCero()
    {
        return base_tarifa_0;
    }
    
    public void setBaseTarifaCero(Double base_tarifa_0)
    {
        this.base_tarifa_0 = base_tarifa_0;
    }
    
    public Double getDescuento()
    {
        return descuento;
    }
    
    public void setDescuento(Double descuento)
    {
        this.descuento = descuento;
    }
    
    public Double getIva()
    {
        return iva;
    }
    
    public void setIva(Double iva)
    {
        this.iva = iva;
    }
    
    public String getTipoDocumento()
    {
        return tipo_documento;
    }
    
    public void setTipoDocumento(String tipo_documento)
    {
        this.tipo_documento = tipo_documento;
    }
    
    public String getFacturaReferencia()
    {
        return factura_referencia;
    }
    
    public void setFacturaReferencia(String factura_referencia)
    {
        this.factura_referencia = factura_referencia;
    }
    
    public String getFecha()
    {
        return fecha;
    }
    
    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }
    
    public String getFechaRegistro()
    {
        return fecha_registro;
    }
    
    public void setFechaRegistro(String fecha_registro)
    {
        this.fecha_registro = fecha_registro;
    }
    
    public String getFechaRecibe()
    {
        return fecha_recibe;
    }
    
    public void setFechaRecibe(String fecha_recibe)
    {
        this.fecha_recibe = fecha_recibe;
    }
    
    public String getAutorizacion()
    {
        return autorizacion;
    }
    
    public void setAutorizacion(String autorizacion)
    {
        this.autorizacion = autorizacion;
    }
    
    public Double getIrbp()
    {
        return irbp;
    }
    
    public void setIrbp(Double irbp)
    {
        this.irbp = irbp;
    }
    
    public Double getBaseIce()
    {
        return baseIce;
    }
    
    public void setBaseIce(Double baseIce)
    {
        this.baseIce = baseIce;
    }
    
    public Double getIce()
    {
        return ice;
    }
    
    public void setIce(Double ice)
    {
        this.ice = ice;
    }
    
    public int getIdCuenta()
    {
        return id_cuenta;
    }
    
    public void setIdCuenta(int id_cuenta)
    {
        this.id_cuenta = id_cuenta;
    }
    
    public ArrayList<clsCompras> consultaDataCompras()
    {       
        ArrayList<clsCompras> data = new ArrayList<clsCompras>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi_compras, a.id_proveedor, b.nombre nombre_proveedor, "
                        + " a.id_usuario, c.name, a.estado, total, "
                        + " saldo, efectivo, fecha, fact_referencia, comentario, id_cajero, "
                        + " id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, "
                        + " base_tarifa_iva, tipo_documento, estado_tramite, fecha_recibe"
                    + " FROM ck_cabecera_movi_compras AS a JOIN ck_proveedor AS b"
                    + " ON a.id_proveedor = b.id_proveedor"
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario "
                    + " WHERE a.estado = 'A'"
                    + " ORDER BY fecha DESC";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCompras oListaTemporal = new clsCompras();
                    oListaTemporal.setNombreProveedor(bd.resultado.getString("nombre_proveedor"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setEstadoTramite(bd.resultado.getString("estado_tramite"));
                    oListaTemporal.setIdCompras(bd.resultado.getInt("id_cabecera_movi_compras"));
                    
                    oListaTemporal.setBaseTarifaIva(bd.resultado.getDouble("base_tarifa_iva"));
                    oListaTemporal.setBaseTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                    oListaTemporal.setIva(bd.resultado.getDouble("iva"));
                    
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setFechaRecibe(bd.resultado.getString("fecha_recibe"));
                    
                    //oListaTemporal.setTotal(bd.resultado.getDouble("total"));
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
    
    public ArrayList<clsCompras> consultaDataComprasRangoRegistro(String fechaInicio, String fechaFin, String soloProveedor, String ruc)
    {       
        ArrayList<clsCompras> data = new ArrayList<clsCompras>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi_compras, a.id_proveedor, b.nombre nombre_proveedor, "
                        + " a.id_usuario, c.name, a.estado, total, "
                        + " saldo, efectivo, fecha, fact_referencia, comentario, id_cajero, "
                        + " id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, "
                        + " base_tarifa_iva, tipo_documento, estado_tramite, fecha_recibe"
                    + " FROM ck_cabecera_movi_compras AS a "
                    + " JOIN ck_proveedor AS b ON a.id_proveedor = b.id_proveedor"
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario "
                    + " WHERE a.estado = 'A'"
                    + " AND fecha::date >= '" + fechaInicio + "'"
                    + " AND fecha::date <= '" + fechaFin + "'";
            if(soloProveedor.equals("S"))
            {
              sql = sql + " AND b.ruc = '" + ruc + "'";  
            }
            sql = sql + " ORDER BY fecha DESC";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCompras oListaTemporal = new clsCompras();
                    oListaTemporal.setNombreProveedor(bd.resultado.getString("nombre_proveedor"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setEstadoTramite(bd.resultado.getString("estado_tramite"));
                    oListaTemporal.setIdCompras(bd.resultado.getInt("id_cabecera_movi_compras"));
                    
                    oListaTemporal.setBaseTarifaIva(bd.resultado.getDouble("base_tarifa_iva"));
                    oListaTemporal.setBaseTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                    oListaTemporal.setIva(bd.resultado.getDouble("iva"));
                    
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setFechaRecibe(bd.resultado.getString("fecha_recibe"));
                    
                    //oListaTemporal.setTotal(bd.resultado.getDouble("total"));
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

    public ArrayList<clsCompras> consultaDataNotasEntregaRangoRegistro(String fechaInicio, String fechaFin, String soloCliente, String cedula, String anuladas)
    {       
        ArrayList<clsCompras> data = new ArrayList<clsCompras>(); 
        try{
            bd.conectarBaseDeDatos();
                        
            sql = "SELECT id_cabecera_movi, a.codigo, b.name_completo nombre_proveedor, "
                        + " a.id_usuario, c.name, a.estado, total_interes, "
                        + " saldo, efectivo, fecha, fact_referencia, comentario, id_cajero, "
                        + " id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, "
                        + " base_tarifa_iva, estado_tramite, fecha_confirmacion, tipo"
                    + " FROM ck_notas_de_entrega AS a "
                    + " JOIN ck_cliente AS b ON a.codigo = b.codigo"
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario "
                    + " WHERE fecha::date >= '" + fechaInicio + "'"
                    + " AND fecha::date <= '" + fechaFin + "'";
            if(anuladas.equals("T"))//MOSTRAR TODAS
            {
                sql = sql + " AND a.estado = 'A' OR a.estado='N'";
            }
            else if(anuladas.equals("N"))
            {
                sql = sql + " AND a.estado='N'";
            }
            else if(anuladas.equals("A"))
            {
                sql = sql + " AND a.estado = 'A'";
            }
            
            
            if(soloCliente.equals("S"))
            {
              sql = sql + " AND b.cedula = '" + cedula + "'";  
            }
            sql = sql + " ORDER BY fecha DESC";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCompras oListaTemporal = new clsCompras();
                    oListaTemporal.setNombreProveedor(bd.resultado.getString("nombre_proveedor"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total_interes"));
                    oListaTemporal.setEstadoTramite(bd.resultado.getString("estado_tramite"));
                    oListaTemporal.setIdCompras(bd.resultado.getInt("id_cabecera_movi"));
                    oListaTemporal.setFacturaReferencia(bd.resultado.getString("fact_referencia"));
                    oListaTemporal.setBaseTarifaIva(bd.resultado.getDouble("base_tarifa_iva"));
                    oListaTemporal.setBaseTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                    oListaTemporal.setIva(bd.resultado.getDouble("iva"));
                    
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setFechaRecibe(bd.resultado.getString("fecha_confirmacion"));
                    oListaTemporal.setTipo(bd.resultado.getString("tipo"));
                    
                    //oListaTemporal.setTotal(bd.resultado.getDouble("total"));
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
    
    public ArrayList<clsCompras> consultaDataNotasEntregaRangoConfirmacion(String fechaInicio, String fechaFin, String soloCliente, String cedula, String anuladas)
    {       
        ArrayList<clsCompras> data = new ArrayList<clsCompras>(); 
        try{
            bd.conectarBaseDeDatos();
                        
             sql = "SELECT id_cabecera_movi, a.codigo, b.name_completo nombre_proveedor, "
                        + " a.id_usuario, c.name, a.estado, total_interes, "
                        + " saldo, efectivo, fecha, fact_referencia, comentario, id_cajero, "
                        + " id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, "
                        + " base_tarifa_iva, estado_tramite, fecha_confirmacion, tipo"
                    + " FROM ck_notas_de_entrega AS a "
                    + " JOIN ck_cliente AS b ON a.codigo = b.codigo"
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario "
                    + " WHERE fecha_confirmacion::date >= '" + fechaInicio + "'"
                    + " AND fecha_confirmacion::date <= '" + fechaFin + "'";
             if(anuladas.equals("T"))//MOSTRAR TODAS
            {
                sql = sql + " AND a.estado = 'A' OR a.estado='N'";
            }
            else if(anuladas.equals("N"))
            {
                sql = sql + " AND a.estado='N'";
            }
            else if(anuladas.equals("A"))
            {
                sql = sql + " AND a.estado = 'A'";
            }
             
            if(soloCliente.equals("S"))
            {
              sql = sql + " AND b.cedula = '" + cedula + "'";  
            }
            sql = sql + " ORDER BY fecha DESC";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCompras oListaTemporal = new clsCompras();
                    oListaTemporal.setNombreProveedor(bd.resultado.getString("nombre_proveedor"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total_interes"));
                    oListaTemporal.setEstadoTramite(bd.resultado.getString("estado_tramite"));
                    oListaTemporal.setIdCompras(bd.resultado.getInt("id_cabecera_movi"));
                    oListaTemporal.setFacturaReferencia(bd.resultado.getString("fact_referencia"));
                    oListaTemporal.setBaseTarifaIva(bd.resultado.getDouble("base_tarifa_iva"));
                    oListaTemporal.setBaseTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                    oListaTemporal.setIva(bd.resultado.getDouble("iva"));
                    
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setFechaRecibe(bd.resultado.getString("fecha_confirmacion"));
                    oListaTemporal.setTipo(bd.resultado.getString("tipo"));
                    
                    //oListaTemporal.setTotal(bd.resultado.getDouble("total"));
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
    
     public ArrayList<clsCompras> consultaDataNotasEntregaCliente(String soloCliente, String cedula, String anuladas)
    {       
        ArrayList<clsCompras> data = new ArrayList<clsCompras>(); 
        try{
            bd.conectarBaseDeDatos();
                        
             sql = "SELECT id_cabecera_movi, a.codigo, b.name_completo nombre_proveedor, "
                        + " a.id_usuario, c.name, a.estado, total_interes, "
                        + " saldo, efectivo, fecha, fact_referencia, comentario, id_cajero, "
                        + " id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, "
                        + " base_tarifa_iva, estado_tramite, fecha_confirmacion, tipo"
                    + " FROM ck_notas_de_entrega AS a "
                    + " JOIN ck_cliente AS b ON a.codigo = b.codigo"
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario ";
                   
            
            if(anuladas.equals("T"))//MOSTRAR TODAS
            {
                sql = sql + " WHERE a.estado = 'A' OR a.estado='N'";
            }
            else if(anuladas.equals("N"))
            {
                sql = sql + " WHERE a.estado='N'";
            }
            else if(anuladas.equals("A"))
            {
                sql = sql + " WHERE a.estado = 'A'";
            }
             
            if(soloCliente.equals("S"))
            {
              sql = sql + " AND b.cedula = '" + cedula + "'";  
            }
            sql = sql + " ORDER BY fecha DESC";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCompras oListaTemporal = new clsCompras();
                    oListaTemporal.setNombreProveedor(bd.resultado.getString("nombre_proveedor"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total_interes"));
                    oListaTemporal.setEstadoTramite(bd.resultado.getString("estado_tramite"));
                    oListaTemporal.setIdCompras(bd.resultado.getInt("id_cabecera_movi"));
                    oListaTemporal.setFacturaReferencia(bd.resultado.getString("fact_referencia"));
                    oListaTemporal.setBaseTarifaIva(bd.resultado.getDouble("base_tarifa_iva"));
                    oListaTemporal.setBaseTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                    oListaTemporal.setIva(bd.resultado.getDouble("iva"));
                    
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setFechaRecibe(bd.resultado.getString("fecha_confirmacion"));
                    oListaTemporal.setTipo(bd.resultado.getString("tipo"));
                    //oListaTemporal.setTotal(bd.resultado.getDouble("total"));
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
    
    public ArrayList<clsCompras> consultaDataComprasRangoRecibe(String fechaInicio, String fechaFin, String soloProveedor, String ruc)
    {       
        ArrayList<clsCompras> data = new ArrayList<clsCompras>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi_compras, a.id_proveedor, b.nombre nombre_proveedor, "
                        + " a.id_usuario, c.name, a.estado, total, "
                        + " saldo, efectivo, fecha, fact_referencia, comentario, id_cajero, "
                        + " id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, "
                        + " base_tarifa_iva, tipo_documento, estado_tramite, fecha_recibe"
                    + " FROM ck_cabecera_movi_compras AS a "
                    + " JOIN ck_proveedor AS b ON a.id_proveedor = b.id_proveedor"
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario "
                    + " WHERE a.estado = 'A'"
                    + " AND fecha_recibe::date >= '" + fechaInicio + "'"
                    + " AND fecha_recibe::date <= '" + fechaFin + "'";
            if(soloProveedor.equals("S"))
            {
              sql = sql + " AND b.ruc = '" + ruc + "'";  
            }
            sql = sql + " ORDER BY fecha DESC";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCompras oListaTemporal = new clsCompras();
                    oListaTemporal.setNombreProveedor(bd.resultado.getString("nombre_proveedor"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setEstadoTramite(bd.resultado.getString("estado_tramite"));
                    oListaTemporal.setIdCompras(bd.resultado.getInt("id_cabecera_movi_compras"));
                    
                    oListaTemporal.setBaseTarifaIva(bd.resultado.getDouble("base_tarifa_iva"));
                    oListaTemporal.setBaseTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                    oListaTemporal.setIva(bd.resultado.getDouble("iva"));
                    
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setFechaRecibe(bd.resultado.getString("fecha_recibe"));
                    
                    //oListaTemporal.setTotal(bd.resultado.getDouble("total"));
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
   
   public ArrayList<clsCompras> consultarDataCompra(int idCabecera)
     {       
        ArrayList<clsCompras> data = new ArrayList<clsCompras>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi_compras, "
                    + "a.id_proveedor, "
                    + "b.ruc ruc, "
                    + " b.nombre nombre_proveedor, "
                    + "a.id_usuario, "
                    + "c.name nombre_elaborador, "
                    + "a.estado, "
                    + "total, "
                    + " saldo, "
                    + "efectivo, "
                    + "fecha, "
                    + "fact_referencia, "
                    + "comentario, "
                    + "id_cajero, "
                    + " id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, "
                    + " base_tarifa_iva, "
                    + "tipo_documento, "
                    + "estado_tramite, "
                    + "id_usuario_recibe, "
                    + "d.name nombre_recibe, "
                    + " irbp, baseice, ice, autorizacion, id_cuenta "
                    + " FROM ck_cabecera_movi_compras AS a "
                    + " JOIN ck_proveedor AS b ON a.id_proveedor = b.id_proveedor"
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario "
                     + " JOIN ck_usuario AS d ON a.id_usuario_recibe = d.id_usuario "
                    + " WHERE a.estado = 'A'"
                    + " AND id_cabecera_movi_compras = " + idCabecera;
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCompras oListaTemporal = new clsCompras();
                    oListaTemporal.setRUC(bd.resultado.getString("ruc"));
                    oListaTemporal.setNombreProveedor(bd.resultado.getString("nombre_proveedor"));
                    oListaTemporal.setNombreElaborador(bd.resultado.getString("nombre_elaborador"));
                    oListaTemporal.setNombreRecibe(bd.resultado.getString("nombre_recibe"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setEstadoTramite(bd.resultado.getString("estado_tramite"));
                    oListaTemporal.setIdCompras(bd.resultado.getInt("id_cabecera_movi_compras"));
                    oListaTemporal.setBaseTarifaIva(bd.resultado.getDouble("base_tarifa_iva"));
                    oListaTemporal.setBaseTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                    oListaTemporal.setIva(bd.resultado.getDouble("iva"));
                    oListaTemporal.setTipoDocumento(bd.resultado.getString("tipo_documento"));
                    oListaTemporal.setFacturaReferencia(bd.resultado.getString("fact_referencia"));
                    oListaTemporal.setIrbp(bd.resultado.getDouble("irbp"));
                    oListaTemporal.setBaseIce(bd.resultado.getDouble("baseice"));
                    oListaTemporal.setIce(bd.resultado.getDouble("ice"));
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    
                    oListaTemporal.setAutorizacion(bd.resultado.getString("autorizacion"));
                    oListaTemporal.setIdCuenta(bd.resultado.getInt("id_cuenta"));
                    
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
   
   public ArrayList<clsCompras> consultarDataCompraPendiente(int idCabecera)
     {       
        ArrayList<clsCompras> data = new ArrayList<clsCompras>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi_compras, a.id_proveedor, b.ruc ruc, "
                        + " b.nombre nombre_proveedor, a.id_usuario, c.name nombre_elaborador, a.estado, total, "
                        + " saldo, efectivo, fecha::date fecha, fact_referencia, comentario, id_cajero, "
                        + " id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, "
                        + " base_tarifa_iva, tipo_documento, estado_tramite,"
                        + " irbp, baseice, ice, autorizacion, id_cuenta, fecha_registro "
                    + " FROM ck_cabecera_movi_compras AS a "
                    + " JOIN ck_proveedor AS b ON a.id_proveedor = b.id_proveedor"
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario "
                    + " WHERE a.estado = 'A'"
                    + " AND id_cabecera_movi_compras = " + idCabecera;
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCompras oListaTemporal = new clsCompras();
                    oListaTemporal.setRUC(bd.resultado.getString("ruc"));
                    oListaTemporal.setNombreProveedor(bd.resultado.getString("nombre_proveedor"));
                    oListaTemporal.setNombreElaborador(bd.resultado.getString("nombre_elaborador"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setEstadoTramite(bd.resultado.getString("estado_tramite"));
                    oListaTemporal.setIdCompras(bd.resultado.getInt("id_cabecera_movi_compras"));
                    oListaTemporal.setBaseTarifaIva(bd.resultado.getDouble("base_tarifa_iva"));
                    oListaTemporal.setBaseTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                    oListaTemporal.setIva(bd.resultado.getDouble("iva"));
                    oListaTemporal.setTipoDocumento(bd.resultado.getString("tipo_documento"));
                    oListaTemporal.setFacturaReferencia(bd.resultado.getString("fact_referencia"));
                    oListaTemporal.setIrbp(bd.resultado.getDouble("irbp"));
                    oListaTemporal.setBaseIce(bd.resultado.getDouble("baseice"));
                    oListaTemporal.setIce(bd.resultado.getDouble("ice"));
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setFechaRegistro(bd.resultado.getString("fecha_registro"));
                    oListaTemporal.setAutorizacion(bd.resultado.getString("autorizacion"));
                    oListaTemporal.setIdCuenta(bd.resultado.getInt("id_cuenta"));
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
   
   /*public ArrayList<clsDetalle>  consultarDataDetalle(int idCabecera){            
        ArrayList<clsDetalle> data = new ArrayList<clsDetalle>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_detalle_movi, id_cabecera_movi, cantidad, "
                    + " precio, a.id_items id_items, des_item, cod_item,"
                    + " deta_descuento, deta_iva"
                    + " FROM ck_detalle_movi_compras AS a INNER JOIN ck_items AS b ON a.id_items = b.id_items"
                    + " WHERE id_cabecera_movi= " + idCabecera;
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsDetalle oListaTemporal = new clsDetalle();
                
                oListaTemporal.setIdProducto(bd.resultado.getInt("id_items"));  
                oListaTemporal.setCodigoProducto(bd.resultado.getString("cod_item"));                
                oListaTemporal.setDescripcionProducto(bd.resultado.getString("des_item"));  
                oListaTemporal.setCantidad(bd.resultado.getDouble("cantidad"));   
                oListaTemporal.setPrecio(bd.resultado.getDouble("precio"));   
                oListaTemporal.setDescuento(bd.resultado.getDouble("deta_descuento"));   
                oListaTemporal.setIVA(bd.resultado.getDouble("deta_iva"));   
                
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
    }  */ 
   
   public boolean recibirMercaderia(int idCabeceraCompra, String idUser)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cabecera_movi_compras"
                    + " SET estado_tramite = 'S',"
                    + " fecha_recibe = now(),"
                    + " id_usuario_recibe = " + idUser 
                    + " WHERE id_cabecera_movi_compras = " + idCabeceraCompra;      
           
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
   
   public ArrayList<clsCompras> consultaDataComprasProv(String soloProveedor, String ruc)
    {       
        ArrayList<clsCompras> data = new ArrayList<clsCompras>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi_compras, a.id_proveedor, b.nombre nombre_proveedor, "
                        + " a.id_usuario, c.name, a.estado, total, "
                        + " saldo, efectivo, fecha, fact_referencia, comentario, id_cajero, "
                        + " id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, "
                        + " base_tarifa_iva, tipo_documento, estado_tramite, fecha_recibe"
                    + " FROM ck_cabecera_movi_compras AS a "
                    + " JOIN ck_proveedor AS b ON a.id_proveedor = b.id_proveedor"
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario "
                    + " WHERE a.estado = 'A'";
            if(soloProveedor.equals("S"))
            {
              sql = sql + " AND b.ruc = '" + ruc + "'";  
            }
            sql = sql + " ORDER BY fecha DESC";
                   
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCompras oListaTemporal = new clsCompras();
                    oListaTemporal.setNombreProveedor(bd.resultado.getString("nombre_proveedor"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setEstadoTramite(bd.resultado.getString("estado_tramite"));
                    oListaTemporal.setIdCompras(bd.resultado.getInt("id_cabecera_movi_compras"));
                    
                    oListaTemporal.setBaseTarifaIva(bd.resultado.getDouble("base_tarifa_iva"));
                    oListaTemporal.setBaseTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                    oListaTemporal.setIva(bd.resultado.getDouble("iva"));
                    
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setFechaRecibe(bd.resultado.getString("fecha_recibe"));
                    
                    //oListaTemporal.setTotal(bd.resultado.getDouble("total"));
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
   
   public ArrayList<clsCompras> consultaDataNotasEntrega()
    {       
        ArrayList<clsCompras> data = new ArrayList<clsCompras>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi, a.codigo, b.name_completo nombre_proveedor, "
                        + " a.id_usuario, c.name, a.estado, total_interes total, "
                        + " saldo, efectivo, fecha, fact_referencia, comentario, id_cajero, "
                        + " id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, "
                        + " base_tarifa_iva, estado_tramite, fecha_confirmacion, tipo"
                    + " FROM ck_notas_de_entrega AS a JOIN ck_cliente AS b"
                    + " ON a.codigo = b.codigo"
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario "
                    + " WHERE a.estado = 'A'"
                    + " ORDER BY fecha DESC";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCompras oListaTemporal = new clsCompras();
                    oListaTemporal.setNombreProveedor(bd.resultado.getString("nombre_proveedor"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setEstadoTramite(bd.resultado.getString("estado_tramite"));
                    oListaTemporal.setIdCompras(bd.resultado.getInt("id_cabecera_movi"));
                    oListaTemporal.setFacturaReferencia(bd.resultado.getString("fact_referencia"));
                    oListaTemporal.setBaseTarifaIva(bd.resultado.getDouble("base_tarifa_iva"));
                    oListaTemporal.setBaseTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                    oListaTemporal.setIva(bd.resultado.getDouble("iva"));
                    
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setFechaRecibe(bd.resultado.getString("fecha_confirmacion"));
                    oListaTemporal.setTipo(bd.resultado.getString("tipo"));
                    
                    //oListaTemporal.setTotal(bd.resultado.getDouble("total"));
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
