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
public class clsCabecera {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String cedula;
    private String name_completo;
    private String fact_referencia;
    private String comentario;
    private Double efectivo;
    private Double total;
    private Double saldo;
    private String descripcion;
    private String descripcion_plazo;
    private String nombre_vendedor;
    private String nombre_descuento;
    private double valor;
    private String fecha;
    private String fecha_registro;
    private Double descuento;
    private Double iva;
    private Double tarifaCero;
    private Double tarifaIVA;
    private int idCabeceraMovi;
    private int idVendedor;
    private int codigo;
    private String estado;
    private Double tarifaIVA1;
    private Double tarifaCero1;
    private Double descuento1;
    private Double iva1;
    private Double total1;
    private String cedula_sin_registrar;
    private String nombre_sin_registrar;
    private String fecha_cancelacion_sistema;
    private int numero_total_notas_entrega;
    private int vendedor;
    private double porcentaje_interes;
    
    public int getIdCabeceraMovi() {
        return idCabeceraMovi;
    }
    
    public void setIdCabeceraMovi(int idCabeceraMovi) {
        this.idCabeceraMovi = idCabeceraMovi;
    }
    
    public int getIdVendedor() {
        return idVendedor;
    }
    
    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getNameCompleto() {
        return name_completo;
    }
    
    public void setNameCompleto(String name_completo) {
        this.name_completo = name_completo;
    }
    
    public String getFactReferencia() {
        return fact_referencia;
    }
    
    public void setFactReferencia(String fact_referencia) {
        this.fact_referencia = fact_referencia;
    }
    
    public String getComentario() {
        return comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public Double getEfectivo() {
        return efectivo;
    }
    
    public void setEfectivo(Double efectivo) {
        this.efectivo = efectivo;
    }
    
    public Double getTotal() {
        return total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
    
    public Double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcionPlazo() {
        return descripcion_plazo;
    }
    
    public void setDescripcionPlazo(String descripcion_plazo) {
        this.descripcion_plazo = descripcion_plazo;
    }
    
    public Double getValor() {
        return valor;
    }
    
    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getFechaRegistro() {
        return fecha_registro;
    }
    
    public void setFechaRegistro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
    public Double getDescuento() {
        return descuento;
    }
    
    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
    
    public Double getIVA() {
        return iva;
    }
    
    public void setIVA(Double iva) {
        this.iva = iva;
    }  
    
    public Double getTarifaCero() {
        return tarifaCero;
    }
    
    public void setTarifaCero(Double tarifaCero) {
        this.tarifaCero = tarifaCero;
    }
    
    public Double getTarifaIVA() {
        return tarifaIVA;
    }
    
    public void setTarifaIVA(Double tarifaIVA) {
        this.tarifaIVA = tarifaIVA;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getNombreVendedor() {
        return nombre_vendedor;
    }
    
    public void setNombreVendedor(String nombre_vendedor) {
        this.nombre_vendedor = nombre_vendedor;
    }
    
    public Double getTarifaIVA1() {
        return tarifaIVA1;
    }
    
    public void setTarifaIVA1(Double tarifaIVA1) {
        this.tarifaIVA1 = tarifaIVA1;
    }
    
    public Double getTarifaCero1() {
        return tarifaCero1;
    }
    
    public void setTarifaCero1(Double tarifaCero1) {
        this.tarifaCero1 = tarifaCero1;
    }
    
    public Double getDescuento1() {
        return descuento1;
    }
    
    public void setDescuento1(Double descuento1) {
        this.descuento1 = descuento1;
    }
    
    public Double getIVA1() {
        return iva1;
    }
    
    public void setIVA1(Double iva1) {
        this.iva1 = iva1;
    }  
    
    public Double getTotal1() {
        return total1;
    }
    
    public void setTotal1(Double total1) {
        this.total1 = total1;
    }
    
    public String getNombreDescuento() {
        return nombre_descuento;
    }
    
    public void setNombreDescuento(String nombre_descuento) {
        this.nombre_descuento = nombre_descuento;
    }
    
    public String getCedulaSinRegistrar() {
        return cedula_sin_registrar;
    }
    
    public void setCedulaSinRegistrar(String cedula_sin_registrar) {
        this.cedula_sin_registrar = cedula_sin_registrar;
    }
    
    public String getNombreSinRegistrar() {
        return nombre_sin_registrar;
    }
    
    public void setNombreSinRegistrar(String nombre_sin_registrar) {
        this.nombre_sin_registrar = nombre_sin_registrar;
    }
    
    public String getFechaCancelacionSistema() {
        return fecha_cancelacion_sistema;
    }
    
    public void setFechaCancelacionSistema(String fecha_cancelacion_sistema) {
        this.fecha_cancelacion_sistema = fecha_cancelacion_sistema;
    }
    
    public int getNumeroTotalNE() {
        return numero_total_notas_entrega;
    }
    
    public void setNumeroTotalNE(int numero_total_notas_entrega) {
        this.numero_total_notas_entrega = numero_total_notas_entrega;
    }
    
    public int getVendedor() {
        return vendedor;
    }
    
    public void setVendedor(int vendedor) {
        this.vendedor = vendedor;
    }
    
    public Double getPorcentajeInteres() {
        return porcentaje_interes;
    }
    
    public void setPorcentajeInteres(Double porcentaje_interes) {
        this.porcentaje_interes = porcentaje_interes;
    }
    
    
    public boolean insertarRegistro(int idCliente, String idUser, String idCajero, String total, String idEmpresa, 
            String cajaAbierta, String descuento, String iva, String factura, String tarifa_iva, 
            String tarifa_cero, int idUserCard)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cabecera_movi(codigo, id_usuario, id_cajero, "
                    + " total, efectivo, fecha, id_empresa, id_caja_operacion, descuento, iva, "
                    + " fact_referencia, base_tarifa_0, base_tarifa_iva, saldo, autorizacion_descuento)"                   
                    + " VALUES(" + idCliente + ", " + idUser + ", " + idCajero + ", "
                    + total + ", " + total + ", now(), " + idEmpresa + ", " + cajaAbierta 
                    + ", " + descuento + ", " + iva + ", '" + factura + "', "
                    + tarifa_cero + ", " + tarifa_iva + ", 0.00, " + idUserCard + ")";           
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
    
    public boolean insertarRegistroInventario(String idUser, String total, String idEmpresa, 
            String cajaAbierta, String iva, String factura, String tarifa_iva, 
            String tarifa_cero, int idUserCard, String p_comentario)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cabecera_movi_inventario(id_usuario, "
                    + " total, efectivo, fecha, id_empresa, iva, "
                    + " base_tarifa_0, base_tarifa_iva, saldo, comentario)"                   
                    + " VALUES(" + idUser + ", "
                    + total + ", " + total + ", now(), " + idEmpresa + ", "
                    + iva + ", "
                    + tarifa_cero + ", " + tarifa_iva + ", 0.00, '" + p_comentario + "')";           
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
    
    public boolean insertarRegistroSinRegistrar(int idCliente, String idUser, String idCajero, String total, String idEmpresa, 
            String cajaAbierta, String descuento, String iva, String factura, String tarifa_iva, 
            String tarifa_cero, int idUserCard, String cedula, String nombre)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cabecera_movi(codigo, id_usuario, id_cajero, "
                    + " total, efectivo, fecha, id_empresa, id_caja_operacion, descuento, iva, "
                    + " fact_referencia, base_tarifa_0, base_tarifa_iva, saldo, autorizacion_descuento,"
                    + " nombre_cliente_sin_registrar, cedula_cliente_sin_registrar)"                   
                    + " VALUES(" + idCliente + ", " + idUser + ", " + idCajero + ", "
                    + total + ", " + total + ", now(), " + idEmpresa + ", " + cajaAbierta 
                    + ", " + descuento + ", " + iva + ", '" + factura + "', "
                    + tarifa_cero + ", " + tarifa_iva + ", 0.00, " + idUserCard + ","
                    + " '" + nombre + "', '" + cedula + "')";           
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
    
    public boolean insertarRegistroNotaEntrega(int idCliente, String idUser, String idCajero, String total, String idEmpresa, String cajaAbierta, String descuento, String iva, String factura, String tarifa_iva, String tarifa_cero)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_notas_de_entrega(codigo, id_usuario, id_cajero, "
                    + " total, efectivo, fecha, id_empresa, id_caja_operacion, descuento, iva, "
                    + " fact_referencia, base_tarifa_0, base_tarifa_iva, saldo)"                   
                    + " VALUES(" + idCliente + ", "+idUser+", "+idCajero+", "
                    + total+", "+total+", now(), "+idEmpresa+", "+cajaAbierta+", "+descuento+", "+iva+", '" + factura + "', "
                    + tarifa_cero + ", "+tarifa_iva+", 0.00)";           
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
    
     public boolean insertarRegistroDevolucion(int idCliente, String idUser, String idCajero, String total, String idEmpresa, String cajaAbierta, String descuento, String iva, String tarifa_iva, String tarifa_cero)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cabecera_movi_devolucion(codigo, id_usuario, id_cajero, "
                    + " total, efectivo, fecha, id_empresa, id_caja_operacion, descuento, iva, "
                    + " base_tarifa_0, base_tarifa_iva, saldo)"                   
                    + " VALUES(" + idCliente + ", "+idUser+", "+idCajero+", "
                    + total+", "+total+", now(), "+idEmpresa+", "+cajaAbierta+", "+descuento+", "+iva+", "
                    + tarifa_cero + ", "+tarifa_iva+", 0.00)";           
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
    
    public boolean insertarRegistroCompras(int idProveedor, String idUser, 
            String total, String idEmpresa, String descuento, String iva, 
            String tarifa_iva, String tarifa_cero, String factura_referencia, String documento,
            String irbp, String baseIce, String ice, String fechaCompra, String autorizacion, String idGasto)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cabecera_movi_compras(id_proveedor, id_usuario, "
                    + " total, efectivo, fecha, id_empresa, descuento, iva, "
                    + " base_tarifa_0, base_tarifa_iva, fact_referencia, tipo_documento,"
                    + " irbp, baseice, ice, autorizacion, id_cuenta)"                   
                    + " VALUES(" + idProveedor + ", " + idUser + ", "
                    + total + ", " + total + ", '" + fechaCompra + "', " + idEmpresa + ", " + descuento + ", " + iva + ", "
                    + tarifa_cero + ", " + tarifa_iva + ", '" + factura_referencia + "', '" + documento + "',"
                    + irbp + ", " + baseIce + ", " + ice + ", '" + autorizacion + "', " + idGasto + ")";           
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
    
    public boolean actualizarRegistroCompras(int idCabeceraCompra, int idProveedor,  
            String total, String idEmpresa, String descuento, String iva, 
            String tarifa_iva, String tarifa_cero, String factura_referencia, String documento,
            String fechaCompra, String irbp, String baseIce, String ice, String autorizacion,
            String idCuenta)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "UPDATE ck_cabecera_movi_compras"
                    + " SET id_proveedor = " +idProveedor +","
                        + " total = " + total+ ","
                        + " efectivo = " + total + ","
                        + " id_empresa = " + idEmpresa + ","
                        + " descuento = " + descuento + ","
                        + " iva =  " + iva + ","
                        + " base_tarifa_0 = " + tarifa_cero + ","
                        + " base_tarifa_iva =  " + tarifa_iva + ","
                        + " fact_referencia = '" + factura_referencia + "',"
                        + " tipo_documento = '"+ documento + "',"
                        + " fecha = '" + fechaCompra + "',"
                        + " irbp = " + irbp + ", "
                        + " baseice = " + baseIce + ", "
                        + " ice = " + ice + ", "
                        + " autorizacion = '" + autorizacion + "',"
                        + " id_cuenta = " + idCuenta
                    + " WHERE id_cabecera_movi_compras = " + idCabeceraCompra;           
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
      
    public int obtenerUltimaFactura(String idCajero)
    {          
        int nombreCajero = 0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_cabecera_movi) AS maximo"
                    + " FROM ck_cabecera_movi"
                    + " WHERE id_cajero = " + idCajero;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getInt("maximo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = 0;
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public int obtenerUltimoInventario()
    {          
        int nombreCajero = 0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_cabecera_movi) AS maximo"
                    + " FROM ck_cabecera_movi_inventario";
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getInt("maximo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = 0;
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public String obtenerUltimoInventarioDelProducto(int idItems)
    {          
        String nombreCajero = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(a.id_cabecera_movi) AS maximo, b.fecha fecha"
                    + " FROM ck_detalle_movi_inventario AS a "
                    + " JOIN ck_cabecera_movi_inventario AS b ON a.id_cabecera_movi = b.id_cabecera_movi"
                    + " WHERE a.id_items = " + idItems
                    + " GROUP BY b.fecha";
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = "INVENTARIO # " + bd.resultado.getString("maximo") + " (" + bd.resultado.getString("fecha").substring(0, 16) + ")";                  
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = "";
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public int obtenerUltimaNotaDeEntrega()
    {          
        int nombreCajero = 0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_cabecera_movi) AS maximo"
                    + " FROM ck_notas_de_entrega";
                   
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getInt("maximo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = 0;
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public int obtenerUltimaDevolucion(String idCajero)
    {          
        int nombreCajero = 0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_cabecera_movi_devolucion) AS maximo"
                    + " FROM ck_cabecera_movi_devolucion"
                    + " WHERE id_cajero = " + idCajero;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getInt("maximo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = 0;
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
     public String obtenerUltimaFacturaSinCajero()
    {          
        String nombreCajero=""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_cabecera_movi) AS maximo"
                    + " FROM ck_cabecera_movi";
                    //+ " WHERE id_cajero = " + idCajero;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getString("maximo");              
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
    
    public int obtenerUltimaFacturaReferencia(String idCabecera)
    {          
        int dato = 0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT fact_referencia"
                    + " FROM ck_cabecera_movi"
                    + " WHERE id_cabecera_movi = " + idCabecera;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                dato = bd.resultado.getInt("fact_referencia");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            dato = 0;
        }     
        bd.desconectarBaseDeDatos();
        return dato+1;
    }
    
    public int obtenerUltimaFacturaRegistrada()
    {          
        int ultmFacturaUsada=0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_cabecera_movi) AS maximo"
                    + " FROM ck_cabecera_movi";                    
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
               ultmFacturaUsada = bd.resultado.getInt("maximo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            ultmFacturaUsada = 0;
        }     
        bd.desconectarBaseDeDatos();
        return ultmFacturaUsada+1;
    }
    
    public int obtenerUltimaFacturaCompras(String idUser)
    {          
        int ultimaFactura = 0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_cabecera_movi_compras) AS maximo"
                    + " FROM ck_cabecera_movi_compras"
                    + " --WHERE id_usuario = " + idUser;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                ultimaFactura = bd.resultado.getInt("maximo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            ultimaFactura = 0;
        }     
        bd.desconectarBaseDeDatos();
        return ultimaFactura;
    }
    
    public boolean insertarRegistroCreditoMonica(int idCliente, String idUser, String idCajero, String total, String idEmpresa, String cajaAbierta, String factReferencia, String comentario, String saldo, String efectivo, String fecha)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_notas_de_entrega(codigo, id_usuario, id_cajero, total, "
                    + " fecha, id_empresa, id_caja_operacion, fact_referencia,"
                    + " comentario, saldo, efectivo, tipo, estado_tramite, total_interes)"                   
                    + " VALUES(" + idCliente + ", " + idUser + ", " + idCajero + ", " + total
                    + " , '" + fecha + "', " + idEmpresa + ", " + cajaAbierta + ", '" + factReferencia + "',"
                    + " '" + comentario + "', " + saldo + ", " + efectivo + ", 'C', 'S', " + total + ")";           
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
    
    public boolean insertarRegistroCredito(int idCliente, String idUser, String idCajero, String total, 
            String idEmpresa, String cajaAbierta, String comentario, String saldo, 
            String efectivo, String descuento, String iva, String factura, String tarifaIva, 
            String tarifaCero, int idUserCard, int idUserCardCredito)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cabecera_movi(codigo, id_usuario, id_cajero, total, "
                    + " fecha, id_empresa, id_caja_operacion, "
                    + " comentario, saldo, efectivo, descuento, iva, fact_referencia, base_tarifa_0, "
                    + " base_tarifa_iva, autorizacion_descuento, autorizacion_credito)"                   
                    + " VALUES(" + idCliente + ", " + idUser + ", " + idCajero + ", " + total
                    + " , now(), " + idEmpresa + ", " + cajaAbierta + ", "
                    + " '" + comentario + "', " + saldo + ", " + efectivo + ", " + descuento + ", " + iva + ", "
                    + " '" + factura + "', " + tarifaCero + ", " + tarifaIva + ", " + idUserCard + ", "
                    + idUserCardCredito+ ")";           
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
    
    public boolean insertarRegistroNotaDeEntrega(int idCliente, String idUser, String idCajero, 
            String total, String idEmpresa, String cajaAbierta, String comentario, 
            Double saldo, String efectivo, String descuento, String iva, String factura, 
            String tarifaIva, String tarifaCero, 
            String tarifaIva2, String iva2, String total2, String codigo_vendedor,
            String fechaVenta, String tipo, String porcentaje_interes, String baseTarifaCero_Interes)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_notas_de_entrega(codigo, id_usuario, id_cajero, total, "
                    + " fecha, id_empresa, id_caja_operacion, "
                    + " comentario, saldo, efectivo, descuento, iva, fact_referencia, base_tarifa_0, base_tarifa_iva, "
                    + " total_interes, iva_interes, base_tarifa_iva_interes, vendedor, tipo, porcentaje_interes,"
                    + " base_tarifa_0_interes)"                   
                    + " VALUES(" + idCliente + ", "+idUser+", "+idCajero+", "+total
                    + " , '" + fechaVenta + "', "+idEmpresa+", "+cajaAbierta+", "
                    + " '"+comentario+"', "+saldo+", "+efectivo+", "+descuento+", "+iva+", '"+factura+"', "+tarifaCero+", "+tarifaIva+","
                    + " "+total2+" , " + iva2+ ", " + tarifaIva2+", " + codigo_vendedor + ", '" + tipo + "', " + porcentaje_interes + ", " + baseTarifaCero_Interes + ")";           
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
    
    public boolean modificarRegistroNotaDeEntrega(int idCabecera, int idCliente, String idUser, String idCajero, 
            String total, String idEmpresa, String cajaAbierta, String comentario, 
            Double saldo, String efectivo, String descuento, String iva, String factura, 
            String tarifaIva, String tarifaCero, 
            String tarifaIva2, String iva2, String total2, String codigo_vendedor,
            String fechaVenta, String tipo, String porcentaje_interes, String baseTarifaCero_interes)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "UPDATE ck_notas_de_entrega"
                    + " SET id_usuario = "+idUser+", "
                        + " codigo = " + idCliente + ", "
                        + " id_cajero = "+idCajero+", "
                        + " total = "+total + ", "
                        + " fecha = '" + fechaVenta + "', "
                        + " id_empresa = "+idEmpresa+", "
                        + " id_caja_operacion = "+cajaAbierta+", "
                        + " comentario = '"+comentario+"',"
                        + " saldo = "+saldo+", "
                        + " efectivo = "+efectivo+", "
                        + " descuento = "+descuento+", "
                        + " iva = "+iva+", "
                        + " fact_referencia = '"+factura+"', "
                        + " base_tarifa_0 = "+tarifaCero+", "
                        + " base_tarifa_iva = "+tarifaIva+", "
                        + " total_interes = "+total2+", "
                        + " iva_interes = " + iva2+ ", "
                        + " base_tarifa_iva_interes = " + tarifaIva2+", "
                        + " vendedor = " + codigo_vendedor + ", "
                        + " tipo = '" + tipo + "',"
                        + " porcentaje_interes = " + porcentaje_interes + ", "
                        + " base_tarifa_0_interes = " + baseTarifaCero_interes
                    + " WHERE  id_cabecera_movi = " + idCabecera;
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
    
    public boolean insertarRegistroNotaEntregaAnulada(String idUser, 
            String idCajero, 
            String idEmpresa, 
            String cajaAbierta, 
            String comentario, 
            String factura,            
            String fechaVenta)
    {       
        //EL ESTADO "N" ES DE ANULADA
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_notas_de_entrega(id_usuario, id_cajero,"
                    + " fecha, id_empresa, id_caja_operacion, "
                    + " comentario, fact_referencia, estado, codigo, estado_tramite)"                   
                    + " VALUES("+idUser+", " + idCajero + ", "
                    + " '" + fechaVenta + "', " + idEmpresa + ", " + cajaAbierta + ", "
                    + " '" + comentario + "', '" + factura + "', 'N', 0, 'S')";           
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
    
     public boolean modificarRegistroNotaEntregaAnulada(String idUser, 
            String idCajero, 
            String idEmpresa, 
            String cajaAbierta, 
            String comentario, 
            String factura,            
            String fechaVenta,
            int idCabecera)
    {       
        //EL ESTADO "N" ES DE ANULADA
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "UPDATE ck_notas_de_entrega"
                    + " SET id_usuario = " + idUser + ", "
                        + " id_cajero = " + idCajero + ", "
                        + " fecha = '" + fechaVenta + "', "
                        + " id_empresa = " + idEmpresa + ", "
                        + " id_caja_operacion = " + cajaAbierta + ", "
                        + " comentario = '" + comentario + "', "
                       // + " fact_referencia = '"+factura+"', "
                        + " estado = 'N'"
                    + " WHERE id_cabecera_movi = " + idCabecera;           
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
        
    public int obtenerUltimaFacturaCreditoMonica(String idUser)
    {          
        int nombreCajero = 0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_cabecera_movi) AS maximo"
                    + " FROM ck_notas_de_entrega"
                    + " WHERE id_usuario = " + idUser;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getInt("maximo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = 0;
        }
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public boolean insertarCtaCobrarMonica(int ultFactura, String comentario, String saldo, String p_fecha, String p_fecha_cancelacion)
    {       
        boolean exito = false;
        try
        {           
            System.out.println(ultFactura +" "+ comentario +" "+ saldo +" "+ p_fecha + " "+ p_fecha_cancelacion);
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cta_cobrar(id_cabecera_movi, descripcion, valor, "
                    + " fecha_creacion, valor_actual, fecha_modificacion, "
                    + " fecha_cancelacion_sistema)"                   
                    + " VALUES("+ultFactura+", '"+comentario+"', "+saldo+", '"+p_fecha+"', "
                    + saldo+", '"+p_fecha+"', '" + p_fecha_cancelacion + "')";           
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
    
    public boolean insertarCtaCobrar(int ultFactura, String comentario, String saldo, String p_fecha_cancelacion, String idPlazo)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cta_cobrar_facturas(id_cabecera_movi, descripcion, valor, fecha_creacion, "
                    + " valor_actual, fecha_modificacion, fecha_cancelacion_sistema, id_plazo)"                   
                    + " VALUES("+ultFactura+", '"+comentario+"', "+saldo+", now(), "+saldo+", "
                    + " now(), '" + p_fecha_cancelacion + "', " + idPlazo + ")";           
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
    
    public boolean insertarCtaCobrarNotaEntrega(int ultFactura, String comentario, Double saldo, String p_fecha_cancelacion, String idPlazo)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cta_cobrar(id_cabecera_movi, descripcion, valor, fecha_creacion, "
                    + " valor_actual, fecha_modificacion, fecha_cancelacion_sistema, id_plazo)"                   
                    + " VALUES("+ultFactura+", '"+comentario+"', "+saldo+", now(), "+saldo+", "
                    + " now(), '" + p_fecha_cancelacion + "', " + idPlazo +")";           
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
    
    public boolean borrarCtaCobrarNotaEntrega(int ultFactura)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "UPDATE ck_cta_cobrar "
                    + "SET estado = 'I'"
                    + " WHERE id_cabecera_movi = " + ultFactura;          
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
    
    public boolean insertarValorCuota(int ultFactura, String idCuota, String valor)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_rel_cabecera_cuota_facturas(id_cabecera_movi , id_cuota, valor)"                   
                    + " VALUES("+ultFactura+", "+idCuota+", "+valor+")";           
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
    
    public boolean insertarValorCuotaEntrega(String ultFactura, String idCuota, String valor)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_rel_cabecera_cuota(id_cabecera_movi , id_cuota, valor)"                   
                    + " VALUES("+ultFactura+", "+idCuota+", "+valor+")";           
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
    
    public boolean insertarValorCuotaNotaEntrega(int ultFactura, String idCuota, Double valor)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_rel_cabecera_cuota(id_cabecera_movi , id_cuota, valor)"                   
                    + " VALUES("+ultFactura+", "+idCuota+", "+valor+")";           
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
    
    public boolean borrarValorCuotaNotaEntrega(int ultFactura)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "UPDATE ck_rel_cabecera_cuota "
                    + " SET estado = 'I'"
                    + " WHERE id_cabecera_movi = " + ultFactura;
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
    
    public ArrayList<clsCabecera>  consultarDataCabeceraCredito(int idCabecera){            
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cabecera_movi id_cabecera_movi, a.codigo codigo, b.name_completo, "
                        + " b.cedula, a.id_usuario, c.name,  "
                        + " a.estado, total, saldo, efectivo,  "
                        + " fecha, a.fecha_registro fecha_registro, fact_referencia, comentario,  "
                        + " id_cajero, id_empresa, id_caja_operacion, "
                        + " d.valor valor, e.descripcion descripcion, "
                        + " base_tarifa_0, base_tarifa_iva, descuento, iva, "
                        + " total_interes, base_tarifa_0_interes, base_tarifa_iva_interes, iva_interes,"
                        + " f.descripcion descripcion_plazo, fecha_cancelacion_sistema, "
                        + " g.apellido1 || ' ' || g.nombre1 nombre_vendedor, a.vendedor id_vendedor, "
                        + " porcentaje_interes"
                    + " FROM ck_notas_de_entrega AS a inner join ck_cliente AS b on a.codigo = b.codigo "
                    + " inner join ck_usuario AS c on a.id_usuario = c.id_usuario "
                    + " inner join ck_rel_cabecera_cuota AS d on a.id_cabecera_movi = d.id_cabecera_movi"
                    + " inner join ck_cuota as e on d.id_cuota = e.id_cuota"
                    + " inner join ck_cta_cobrar as ee on a.id_cabecera_movi = ee.id_cabecera_movi"
                    + " inner join ck_plazo as f on ee.id_plazo = f.id_plazo"
                    + " inner join ck_personal as g on a.vendedor = g.id_personal"
                    + " WHERE a.id_cabecera_movi=" + idCabecera;
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCabecera oListaTemporal = new clsCabecera();
                
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));                
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));
                oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));
                oListaTemporal.setValor(bd.resultado.getDouble("valor"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setFechaRegistro(bd.resultado.getString("fecha_registro"));
                oListaTemporal.setTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                oListaTemporal.setTarifaIVA(bd.resultado.getDouble("base_tarifa_iva"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIVA(bd.resultado.getDouble("iva"));
                oListaTemporal.setDescripcionPlazo(bd.resultado.getString("descripcion_plazo"));
                oListaTemporal.setNombreVendedor(bd.resultado.getString("nombre_vendedor"));
                oListaTemporal.setIdVendedor(bd.resultado.getInt("id_vendedor"));
                
                oListaTemporal.setTarifaCero1(bd.resultado.getDouble("base_tarifa_0_interes"));
                oListaTemporal.setTarifaIVA1(bd.resultado.getDouble("base_tarifa_iva_interes"));                
                oListaTemporal.setIVA1(bd.resultado.getDouble("iva_interes"));
                oListaTemporal.setTotal1(bd.resultado.getDouble("total_interes"));
                oListaTemporal.setFechaCancelacionSistema(bd.resultado.getString("fecha_cancelacion_sistema"));
                
                oListaTemporal.setCodigo(bd.resultado.getInt("codigo"));
                oListaTemporal.setPorcentajeInteres(bd.resultado.getDouble("porcentaje_interes"));
                 
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
    
    public ArrayList<clsCabecera>  consultarDataCabeceraCreditoFactura(int idCabecera){            
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cabecera_movi id_cabecera_movi, a.codigo, b.name_completo, "
                        + " b.cedula, a.id_usuario, c.name,  "
                        + " a.estado, total, saldo, efectivo,  "
                        + " fecha, fact_referencia, comentario,  "
                        + " id_cajero, id_empresa, id_caja_operacion, "
                        + " d.valor valor, e.descripcion descripcion, "
                        + " base_tarifa_0, base_tarifa_iva, descuento, iva,"
                        + " fecha_cancelacion_sistema, "                       
                        + " f.descripcion descripcion_plazo, b.name_completo nombre_vendedor"
                    + " FROM ck_cabecera_movi AS a inner join ck_cliente AS b on a.codigo = b.codigo "
                    + " inner join ck_usuario AS c on a.id_usuario = c.id_usuario "
                    + " inner join ck_rel_cabecera_cuota_facturas AS d on a.id_cabecera_movi = d.id_cabecera_movi"
                    + " inner join ck_cuota as e on d.id_cuota = e.id_cuota"
                    + " inner join ck_cta_cobrar_facturas as ee on a.id_cabecera_movi = ee.id_cabecera_movi"
                    + " inner join ck_plazo as f on ee.id_plazo = f.id_plazo"                    
                    + " WHERE a.id_cabecera_movi=" + idCabecera;
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCabecera oListaTemporal = new clsCabecera();
                
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));                
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));
                oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));
                oListaTemporal.setValor(bd.resultado.getDouble("valor"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                oListaTemporal.setTarifaIVA(bd.resultado.getDouble("base_tarifa_iva"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIVA(bd.resultado.getDouble("iva"));
                oListaTemporal.setDescripcionPlazo(bd.resultado.getString("descripcion_plazo"));
                oListaTemporal.setNombreVendedor(bd.resultado.getString("nombre_vendedor"));
                oListaTemporal.setFechaCancelacionSistema(bd.resultado.getString("fecha_cancelacion_sistema"));
                
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
    
    public ArrayList<clsCabecera>  consultarDataCabecera(int idCabecera){            
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cabecera_movi id_cabecera_movi, a.codigo codigo, b.name_completo, "
                        + " b.cedula, a.id_usuario, c.name,  "
                        + " a.estado, total, saldo, efectivo,  "
                        + " fecha, fact_referencia, comentario,  "
                        + " id_cajero, id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, base_tarifa_iva,"
                        + " cedula_cliente_sin_registrar, nombre_cliente_sin_registrar "              
                    + " FROM ck_cabecera_movi AS a "
                    + " INNER JOIN ck_cliente AS b on a.codigo = b.codigo "
                    + " INNER JOIN ck_usuario AS c on a.id_usuario = c.id_usuario    "               
                    + " WHERE a.id_cabecera_movi=" + idCabecera;
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCabecera oListaTemporal = new clsCabecera();
                
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));                
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));
                oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIVA(bd.resultado.getDouble("iva"));
                oListaTemporal.setTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                oListaTemporal.setTarifaIVA(bd.resultado.getDouble("base_tarifa_iva")); 
                oListaTemporal.setCodigo(bd.resultado.getInt("codigo"));
                oListaTemporal.setCedulaSinRegistrar(bd.resultado.getString("cedula_cliente_sin_registrar"));
                oListaTemporal.setNombreSinRegistrar(bd.resultado.getString("nombre_cliente_sin_registrar"));
                
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
    
     public ArrayList<clsCabecera>  consultaFacturasRealizadas(int idCajaAbierta){            
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cabecera_movi id_cabecera_movi, a.codigo, b.name_completo, "
                    + " b.cedula, a.id_usuario, "
                    + " a.estado, total, saldo, efectivo,  "
                    + " fecha, fact_referencia, comentario,  "
                    + " id_cajero, id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, base_tarifa_iva "              
                    + " FROM ck_cabecera_movi AS a inner join ck_cliente AS b on a.codigo = b.codigo "                                
                    + " WHERE id_caja_operacion = "+idCajaAbierta
                    + " AND a.estado='A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCabecera oListaTemporal = new clsCabecera();
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));   
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));                
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));
                oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIVA(bd.resultado.getDouble("iva"));
                oListaTemporal.setTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                oListaTemporal.setTarifaIVA(bd.resultado.getDouble("base_tarifa_iva"));            
                
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
     
     public ArrayList<clsCabecera>  consultaDevolucionesRealizadas(int idCajaAbierta){            
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cabecera_movi_devolucion id_cabecera_movi, a.codigo, b.name_completo, "
                    + " b.cedula, a.id_usuario, "
                    + " a.estado, total, saldo, efectivo,  "
                    + " fecha, fact_referencia, comentario,  "
                    + " id_cajero, id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, base_tarifa_iva "              
                    + " FROM ck_cabecera_movi_devolucion AS a inner join ck_cliente AS b on a.codigo = b.codigo "                                
                    + " WHERE id_caja_operacion = "+idCajaAbierta
                    + " AND a.estado='A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCabecera oListaTemporal = new clsCabecera();
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));   
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));                
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));
                oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIVA(bd.resultado.getDouble("iva"));
                oListaTemporal.setTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                oListaTemporal.setTarifaIVA(bd.resultado.getDouble("base_tarifa_iva"));            
                
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
     
    public boolean eliminarCabecera(int idCabecera)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "UPDATE ck_cabecera_movi"
                    + " SET estado = 'I'"
                    + " WHERE id_cabecera_movi = " + idCabecera;           
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
    
    public boolean confirmarNotaEntrega(int idCabecera)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "UPDATE ck_notas_de_entrega"
                    + " SET estado_tramite = 'S',"
                    + " fecha_confirmacion = now()"
                    + " WHERE id_cabecera_movi = " + idCabecera;           
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
    
    public ArrayList<clsCabecera>  consultarDataCabeceraFactura(String codeFactura, String hoy){            
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cabecera_movi id_cabecera_movi, a.codigo, b.name_completo, "
                        + " b.cedula, a.id_usuario, c.name,  "
                        + " a.estado, total, saldo, efectivo,  "
                        + " fecha::date || ' ' || fecha::time fecha, fact_referencia, comentario,  "
                        + " id_cajero, id_empresa, id_caja_operacion, descuento, iva, "
                        + " base_tarifa_0, base_tarifa_iva, a.estado estado"              
                    + " FROM ck_cabecera_movi AS a "
                    + " inner join ck_cliente AS b on a.codigo = b.codigo "
                    + " inner join ck_usuario AS c on a.id_usuario = c.id_usuario    "               
                    + " WHERE a.fact_referencia = '" + codeFactura + "'";
            if(hoy.equals("S"))
            {    
                sql = sql + " AND fecha::date = now()::date";
            }
            else if(hoy.equals("N"))
            {
                
            }
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCabecera oListaTemporal = new clsCabecera();
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));   
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));                
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));
                oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIVA(bd.resultado.getDouble("iva"));
                oListaTemporal.setTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                oListaTemporal.setTarifaIVA(bd.resultado.getDouble("base_tarifa_iva"));  
                oListaTemporal.setEstado(bd.resultado.getString("estado"));
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
    
        public ArrayList<clsCabecera>  consultarDataCabeceraNotaEntrega(int idCabecera){            
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cabecera_movi id_cabecera_movi, a.codigo codigo, b.name_completo, "
                            + " b.cedula, a.id_usuario, c.name,  "
                            + " a.estado, total, saldo, efectivo,  "
                            + " fecha, fact_referencia, comentario,  "
                            + " id_cajero, id_empresa, id_caja_operacion, "
                            + " descuento, iva, base_tarifa_0, base_tarifa_iva, porcentaje_interes, vendedor,"
                            + " a.fecha_registro fecha_registro, "   
                            + " total_interes, base_tarifa_0_interes, base_tarifa_iva_interes, iva_interes"
                    + " FROM ck_notas_de_entrega AS a inner join ck_cliente AS b on a.codigo = b.codigo "
                    + " inner join ck_usuario AS c on a.id_usuario = c.id_usuario    "               
                    + " WHERE a.id_cabecera_movi=" + idCabecera;
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCabecera oListaTemporal = new clsCabecera();
                
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));                
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));
                oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setFechaRegistro(bd.resultado.getString("fecha_registro"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIVA(bd.resultado.getDouble("iva"));
                oListaTemporal.setTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                oListaTemporal.setTarifaIVA(bd.resultado.getDouble("base_tarifa_iva"));      
                oListaTemporal.setCodigo(bd.resultado.getInt("codigo"));       
                oListaTemporal.setPorcentajeInteres(bd.resultado.getDouble("porcentaje_interes"));              
                oListaTemporal.setIdVendedor(bd.resultado.getInt("vendedor"));
                
                oListaTemporal.setTarifaCero1(bd.resultado.getDouble("base_tarifa_0_interes"));
                oListaTemporal.setTarifaIVA1(bd.resultado.getDouble("base_tarifa_iva_interes"));                
                oListaTemporal.setIVA1(bd.resultado.getDouble("iva_interes"));
                oListaTemporal.setTotal1(bd.resultado.getDouble("total_interes"));
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
        
    public ArrayList<clsCabecera> consultaDataVentasRangoRegistro(String fechaInicio, String fechaFin, String soloCredito)
    {       
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi, a.codigo, id_usuario, a.estado, total, saldo, efectivo, " 
                       + " fecha, fact_referencia, comentario, id_cajero, id_empresa, id_caja_operacion, " 
                       + " descuento, iva, base_tarifa_0, base_tarifa_iva, "
                       + " b.name_completo name_completo"                    
                    + " FROM ck_cabecera_movi AS a"
                    + " JOIN ck_cliente AS b ON a.codigo = b.codigo" 
                    + " WHERE fecha::date >= '" + fechaInicio + "'" 
                    + " AND fecha::date <= '" + fechaFin + "'" 
                    + " AND a.estado = 'A'" 
                    + " AND id_usuario <> 1 ";
            if (soloCredito.equals("S"))
            {
                sql = sql + " AND saldo > 0";
            }
            
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCabecera oListaTemporal = new clsCabecera();
                    oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                    oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));  
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
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
    
    public ArrayList<clsCabecera> consultaDataVentas(String soloCredito)
    {       
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi, a.codigo, id_usuario, a.estado, total, saldo, efectivo, " 
                       + " fecha, fact_referencia, comentario, id_cajero, id_empresa, id_caja_operacion, " 
                       + " descuento, iva, base_tarifa_0, base_tarifa_iva, " 
                       + " b.name_completo name_completo"    
                  + " FROM ck_cabecera_movi AS a"
                  + " JOIN ck_cliente AS b ON a.codigo = b.codigo" 
                  + " WHERE a.estado = 'A' " 
                  + " And id_usuario <> 1";
            if (soloCredito.equals("S"))
            {
                sql = sql + " AND saldo > 0";
            }
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCabecera oListaTemporal = new clsCabecera();
                    oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                    oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));                   
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
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
    
    public ArrayList<clsCabecera> consultaDataVentasRangoRegistroNE(String fechaInicio, String fechaFin, String soloCredito)
    {       
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi, a.codigo, id_usuario, a.estado, total_interes, saldo, efectivo, " 
                       + " fecha, fact_referencia, comentario, id_cajero, id_empresa, id_caja_operacion, " 
                       + " descuento, iva, base_tarifa_0, base_tarifa_iva, "
                       + " b.name_completo name_completo, "
                       + " c.apellido1 || ' ' || c.nombre1 nombre_vendedor"                      
                    + " FROM ck_notas_de_entrega AS a"
                    + " JOIN ck_cliente AS b ON a.codigo = b.codigo" 
                    + " JOIN ck_personal AS c ON vendedor = id_personal" 
                    + " WHERE fecha::date >= '" + fechaInicio + "'" 
                    + " AND fecha::date <= '" + fechaFin + "'" 
                    + " AND a.estado = 'A'" 
                    + " AND id_usuario <> 1 ";
            if (soloCredito.equals("S"))
            {
                sql = sql + " AND saldo > 0";
            }
            
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCabecera oListaTemporal = new clsCabecera();
                    oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total_interes"));
                    oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                    oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));  
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                    oListaTemporal.setNombreVendedor(bd.resultado.getString("nombre_vendedor"));
                    oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
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
    
    public ArrayList<clsCabecera> consultaDataVentasNE(String soloCredito)
    {       
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi, a.codigo, id_usuario, a.estado, total_interes, saldo, efectivo, " 
                       + " fecha, fact_referencia, comentario, id_cajero, id_empresa, id_caja_operacion, " 
                       + " descuento, iva, base_tarifa_0, base_tarifa_iva, " 
                       + " b.name_completo name_completo,"
                       + " c.apellido1 || ' ' || c.nombre1 nombre_vendedor"    
                  + " FROM ck_notas_de_entrega AS a"
                  + " JOIN ck_cliente AS b ON a.codigo = b.codigo"
                  + " JOIN ck_personal AS c ON vendedor = id_personal" 
                  + " WHERE a.estado = 'A' " 
                  + " And id_usuario <> 1";
            if (soloCredito.equals("S"))
            {
                sql = sql + " AND saldo > 0";
            }
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCabecera oListaTemporal = new clsCabecera();
                    oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total_interes"));
                    oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                    oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));                   
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                    oListaTemporal.setNombreVendedor(bd.resultado.getString("nombre_vendedor"));
                    oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
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
    
    public ArrayList<clsCabecera> consultaDataVentasRangoRegistroNEVendedor(String fechaInicio, String fechaFin)
    {       
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT sum(total_interes) total, "
                        + " sum(saldo) saldo, "
                        + " sum(efectivo) efectivo, "
                        + " c.apellido1 || ' ' || c.nombre1 nombre_vendedor, "
                        + " count(a.*) numero_total_notas_entrega,"
                        + " vendedor"
                    + " FROM ck_notas_de_entrega AS a"
                    + " JOIN ck_cliente AS b ON a.codigo = b.codigo"
                    + " JOIN ck_personal AS c ON vendedor = id_personal"
                    + " WHERE a.estado = 'A' "
                    + " AND id_usuario <> 1"
                    + " AND fecha::date >= '" + fechaInicio + "'" 
                    + " AND fecha::date <= '" + fechaFin + "'" 
                    + " GROUP BY vendedor, c.apellido1, c.nombre1"
                    + " ORDER BY sum(total) DESC";              
            
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCabecera oListaTemporal = new clsCabecera();
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                    oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));                   
                    oListaTemporal.setNombreVendedor(bd.resultado.getString("nombre_vendedor"));
                    oListaTemporal.setNumeroTotalNE(bd.resultado.getInt("numero_total_notas_entrega"));
                    oListaTemporal.setVendedor(bd.resultado.getInt("vendedor"));
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
    
    public ArrayList<clsCabecera> consultaInventariosRealizados()
    {       
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.fecha::date fecha, "
                        + " a.comentario comentario, "
                        + " count(b.*) items, "
                        + " sum(b.stock_contado) contado, "
                        + " sum(b.stock_sistema) sistema, "
                        + " sum(b.cantidad) diferencia, "
                        + " a.total total,"
                        + " a.id_cabecera_movi cabecera,"
                        + " c.name nombre"
                    + " FROM ck_cabecera_movi_inventario as a"
                    + " JOIN ck_detalle_movi_inventario as b ON a.id_cabecera_movi = b.id_cabecera_movi"
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario"
                    + " AND a.estado = 'A'"
                    + " GROUP BY a.fecha, a.comentario, a.total, a.id_cabecera_movi, c.name";              
            
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCabecera oListaTemporal = new clsCabecera();
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("items"));                   
                    oListaTemporal.setIVA(bd.resultado.getDouble("contado"));
                    oListaTemporal.setTarifaCero(bd.resultado.getDouble("sistema"));
                    oListaTemporal.setTarifaCero1(bd.resultado.getDouble("diferencia"));
                    oListaTemporal.setDescuento1(bd.resultado.getDouble("total"));
                    oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("cabecera"));
                    oListaTemporal.setNameCompleto(bd.resultado.getString("nombre"));
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
    
    public ArrayList<clsCabecera> consultaInventariosRealizadosFecha(String fechaInicio, String fechaFin)
    {       
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.fecha::date fecha, "
                        + " a.comentario comentario, "
                        + " count(b.*) items, "
                        + " sum(b.stock_contado) contado, "
                        + " sum(b.stock_sistema) sistema, "
                        + " sum(b.cantidad) diferencia, "
                        + " a.total total,"
                        + " a.id_cabecera_movi cabecera,"
                        + " c.name nombre"
                    + " FROM ck_cabecera_movi_inventario as a"
                    + " JOIN ck_detalle_movi_inventario as b ON a.id_cabecera_movi = b.id_cabecera_movi "
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario"
                    + " WHERE a.fecha::date >= '" + fechaInicio + "'"
                    + " AND a.fecha::date <= '" + fechaFin + "'"
                    + " AND a.estado = 'A'"
                    + " GROUP BY a.fecha, a.comentario, a.total, a.id_cabecera_movi, c.name";              
            
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCabecera oListaTemporal = new clsCabecera();
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("items"));                   
                    oListaTemporal.setIVA(bd.resultado.getDouble("contado"));
                    oListaTemporal.setTarifaCero(bd.resultado.getDouble("sistema"));
                    oListaTemporal.setTarifaCero1(bd.resultado.getDouble("diferencia"));
                    oListaTemporal.setDescuento1(bd.resultado.getDouble("total"));
                    oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("cabecera"));
                     oListaTemporal.setNameCompleto(bd.resultado.getString("nombre"));
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
    
    public ArrayList<clsCabecera> consultaDataVentasNEVendedor()
    {       
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT sum(total_interes) total, "
                        + " sum(saldo) saldo, "
                        + " sum(efectivo) efectivo, "
                        + " c.apellido1 || ' ' || c.nombre1 nombre_vendedor,"
                        + " count(a.*) numero_total_notas_entrega,"
                        + " vendedor"
                    + " FROM ck_notas_de_entrega AS a"
                    + " JOIN ck_cliente AS b ON a.codigo = b.codigo"
                    + " JOIN ck_personal AS c ON vendedor = id_personal"
                    + " WHERE a.estado = 'A' "
                    + " And id_usuario <> 1"
                    + " GROUP BY vendedor, c.apellido1, c.nombre1"
                    + " ORDER BY sum(total) DESC";            
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCabecera oListaTemporal = new clsCabecera();
                    
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                    oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));                   
                    oListaTemporal.setNombreVendedor(bd.resultado.getString("nombre_vendedor"));
                    oListaTemporal.setNumeroTotalNE(bd.resultado.getInt("numero_total_notas_entrega"));
                    oListaTemporal.setVendedor(bd.resultado.getInt("vendedor"));
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
    
    public ArrayList<clsCabecera> consultaDataDescuentos(String fechaInicio, String fechaFin)
    {       
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = " SELECT id_cabecera_movi, a.codigo, a.id_usuario, a.estado, total, saldo, efectivo, " 
                       + " fecha, fact_referencia, comentario, id_cajero, id_empresa, id_caja_operacion, " 
                       + " descuento, iva, base_tarifa_0, base_tarifa_iva, "
                       + " b.name_completo name_completo,"
                       + " c.name nombre_cajero,"
                       + " d.nombre nombre_descuento"                     
                    + " FROM ck_cabecera_movi AS a"
                    + " JOIN ck_cliente AS b ON a.codigo = b.codigo " 
                    + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario " 
                    + " JOIN ck_tarjeta_pass AS d ON a.autorizacion_descuento = d.id_tarjeta_pass"
                    + " WHERE fecha::date >= '" + fechaInicio + "'" 
                    + " AND fecha::date <= '" + fechaFin + "'" 
                    + " AND a.estado = 'A'" 
                    + " AND a.id_usuario <> 1 "
                    + " AND descuento > 0";       
            
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCabecera oListaTemporal = new clsCabecera();
                    oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                    oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));  
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                    oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                    oListaTemporal.setNombreVendedor(bd.resultado.getString("nombre_cajero"));
                    oListaTemporal.setNombreDescuento(bd.resultado.getString("nombre_descuento"));
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
    
    public ArrayList<clsCabecera> consultaDataDescuentos2()
    {       
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
             sql = " SELECT id_cabecera_movi, a.codigo, a.id_usuario, a.estado, total, saldo, efectivo, " 
                       + " fecha, fact_referencia, comentario, id_cajero, id_empresa, id_caja_operacion, " 
                       + " descuento, iva, base_tarifa_0, base_tarifa_iva, "
                       + " b.name_completo name_completo,"
                       + " c.name nombre_cajero,"   
                       + " d.nombre nombre_descuento"                       
                  + " FROM ck_cabecera_movi AS a "
                  + " JOIN ck_cliente AS b ON a.codigo = b.codigo "
                  + " JOIN ck_usuario AS c ON a.id_usuario = c.id_usuario " 
                  + " JOIN ck_tarjeta_pass AS d ON a.autorizacion_descuento = d.id_tarjeta_pass"
                  + " WHERE a.estado = 'A' " 
                  + " And a.id_usuario <> 1"
                  + " AND descuento > 0";   
            
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCabecera oListaTemporal = new clsCabecera();
                    oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                    oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                    oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));                   
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                    oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                    oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                    oListaTemporal.setNombreVendedor(bd.resultado.getString("nombre_cajero"));
                    oListaTemporal.setNombreDescuento(bd.resultado.getString("nombre_descuento"));
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
    
    public ArrayList<clsCabecera> consultaDataVentasRangoNE(String idVendedor, String fechaInicio, String fechaFin, String soloCredito)
    {       
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi, a.codigo, id_usuario, a.estado, total_interes, saldo, efectivo, " 
                       + " fecha, fact_referencia, comentario, id_cajero, id_empresa, id_caja_operacion, " 
                       + " descuento, iva, base_tarifa_0, base_tarifa_iva, "
                       + " b.name_completo name_completo, "
                       + " c.apellido1 || ' ' || c.nombre1 nombre_vendedor"                      
                    + " FROM ck_notas_de_entrega AS a"
                    + " JOIN ck_cliente AS b ON a.codigo = b.codigo" 
                    + " JOIN ck_personal AS c ON vendedor = id_personal" 
                    + " WHERE fecha::date >= '" + fechaInicio + "'" 
                    + " AND fecha::date <= '" + fechaFin + "'" 
                    + " AND a.estado = 'A'" 
                    + " AND id_usuario <> 1 "
                    + " AND vendedor = " + idVendedor;
            if (soloCredito.equals("S"))
            {
                sql = sql + " AND saldo > 0";
            }
            
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCabecera oListaTemporal = new clsCabecera();
                    oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total_interes"));
                    oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                    oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));  
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                    oListaTemporal.setNombreVendedor(bd.resultado.getString("nombre_vendedor"));
                    oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
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
    
    public ArrayList<clsCabecera> consultaDataVentasNEVende(String idVendedor, String soloCredito)
    {       
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cabecera_movi, a.codigo, id_usuario, a.estado, total_interes, saldo, efectivo, " 
                       + " fecha, fact_referencia, comentario, id_cajero, id_empresa, id_caja_operacion, " 
                       + " descuento, iva, base_tarifa_0, base_tarifa_iva, " 
                       + " b.name_completo name_completo,"
                       + " c.apellido1 || ' ' || c.nombre1 nombre_vendedor"    
                  + " FROM ck_notas_de_entrega AS a"
                  + " JOIN ck_cliente AS b ON a.codigo = b.codigo"
                  + " JOIN ck_personal AS c ON vendedor = id_personal" 
                  + " WHERE a.estado = 'A' " 
                  + " And id_usuario <> 1"
                  + " AND vendedor = " + idVendedor;;
            if (soloCredito.equals("S"))
            {
                sql = sql + " AND saldo > 0";
            }
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCabecera oListaTemporal = new clsCabecera();
                    oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));
                    oListaTemporal.setTotal(bd.resultado.getDouble("total_interes"));
                    oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                    oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));                   
                    oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                    oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                    oListaTemporal.setNombreVendedor(bd.resultado.getString("nombre_vendedor"));
                    oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
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
    
    public ArrayList<clsCabecera>  consultarDataCabeceraInv(String idCabecera){            
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cabecera_movi id_cabecera_movi, "
                        + " a.id_usuario, c.name nombre,  "
                        + " a.estado, total, saldo, efectivo,  "
                        + " fecha, comentario,  "
                        + " id_empresa, iva, base_tarifa_0, base_tarifa_iva "                                  
                    + " FROM ck_cabecera_movi_inventario AS a "                    
                    + " INNER JOIN ck_usuario AS c on a.id_usuario = c.id_usuario    "               
                    + " WHERE a.id_cabecera_movi=" + idCabecera;
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCabecera oListaTemporal = new clsCabecera();                
                
                oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));
                oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setIVA(bd.resultado.getDouble("iva"));
                oListaTemporal.setTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                oListaTemporal.setTarifaIVA(bd.resultado.getDouble("base_tarifa_iva"));                
                oListaTemporal.setNameCompleto(bd.resultado.getString("nombre"));
                
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
    
    public double obtenerValorFacturado(String fecha)
    {          
        double nombreCajero = 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT SUM(total) suma" 
                    + " FROM ck_cabecera_movi " 
                    + " WHERE fecha::date = '" + fecha + "'" 
                    + " AND estado ='A'" 
                    + " AND id_empresa = 1";
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getDouble("suma");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = 0;
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public double obtenerValorEntradas(String fecha)
    {          
        double nombreCajero = 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT SUM(valor) suma " 
                    + " FROM ck_pagos_recibo " 
                    + " WHERE fecha_cobro::date = '" + fecha + "' " 
                    + " AND cuota_inicial = 'S' " 
                    + " AND estado = 'A'";
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getDouble("suma");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = 0;
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public double obtenerValorAbonos(String fecha)
    {          
        double nombreCajero = 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT SUM(valor) suma " 
                    + " FROM ck_pagos " 
                    + " WHERE fecha_cobro::date = '" + fecha + "' " 
                    + " AND estado = 'A'";
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getDouble("suma");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = 0;
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    public double obtenerValorAbonosFactura(String fecha)
    {          
        double nombreCajero = 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT SUM(valor) suma " 
                    + " FROM ck_pagos_factura " 
                    + " WHERE fecha_cobro::date = '" + fecha + "' " 
                    + " AND estado = 'A'";
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getDouble("suma");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = 0;
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
}
