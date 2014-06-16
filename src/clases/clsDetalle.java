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
public class clsDetalle {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private int id;
    private String codigo;
    private String descripcion;
    private Double cantidad;
    private Double precio;
    private Double deta_descuento;
    private Double deta_iva;
    private Double pvpPublico;
    private Double costo;
    private String nombre;
    private int id_contador;
    private Double stock_sistema;
    private Double stock_contado;
    
    public int getIdProducto() {
        return id;
    }
    
    public void setIdProducto(int id) {
        this.id = id;
    }
    
    public int getIdContador() {
        return id_contador;
    }
    
    public void setIdContador(int id_contador) {
        this.id_contador = id_contador;
    }
    
    public String getCodigoProducto() {
        return codigo;
    }
    
    public void setCodigoProducto(String codigo) {
        this.codigo = codigo;
    }
    
    public String getDescripcionProducto() {
        return descripcion;
    }
    
    public void setDescripcionProducto(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Double getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    
    public Double getPrecio() {
        return precio;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    public Double getCosto() {
        return costo;
    }
    
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    
    public Double getDescuento() {
        return deta_descuento;
    }
    
    public void setDescuento(Double deta_descuento) {
        this.deta_descuento = deta_descuento;
    }
    
    public Double getIVA() {
        return deta_iva;
    }
    
    public void setIVA(Double deta_iva) {
        this.deta_iva = deta_iva;
    }
    
    public Double getPvpPublico() {
        return pvpPublico;
    }
    
    public void setPvpPublico(Double pvpPublico) {
        this.pvpPublico = pvpPublico;
    }
    
    public Double getStockContado() {
        return stock_contado;
    }
    
    public void setStockContado(Double stock_contado) {
        this.stock_contado = stock_contado;
    }
    
    public Double getStockSistema() {
        return stock_sistema;
    }
    
    public void setStockSistema(Double stock_sistema) {
        this.stock_sistema = stock_sistema;
    }
    
    public boolean insertarDetalle(int ultFactura, int idItems, String cantidad, String precio, String descuento, String iva )
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_notas_de_entrega_detalle(id_cabecera_movi, id_items, cantidad, precio, deta_descuento, deta_iva)"                   
                    + " VALUES("+ultFactura+", "+idItems+", "+cantidad+", "+precio+", "+descuento+", "+iva+")";           
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
    
    public boolean insertarDetalleFactura(int ultFactura, int idItems, String cantidad, 
            String precio, String descuento, String iva, Double costo )
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_detalle_movi(id_cabecera_movi, id_items, "
                    + " cantidad, precio, deta_descuento, deta_iva, costo)"                   
                    + " VALUES("+ultFactura+", "+idItems+", "
                    + cantidad+", "+precio+", "+descuento+", "+iva+", " + costo + ")";           
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
    
    public boolean insertarDetalleInventario(int ultFactura, int idItems, String cantidad, 
            String precio, String descuento, String iva, Double costo, int id_contador, Double stockSistema, Double stockContado)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_detalle_movi_inventario(id_cabecera_movi, id_items, "
                    + " cantidad, precio, deta_descuento, deta_iva, costo, id_contador, stock_sistema, stock_contado)"                   
                    + " VALUES("+ultFactura+", "+idItems+", "
                    + cantidad+", "+precio+", "+descuento+", "+iva+", " + costo + ", " + id_contador + ", "
                    + stockSistema + ", " + stockContado + ")";           
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
    
    public boolean insertarDetalleNotasEntrega(int ultFactura, int idItems, String cantidad, 
            String precio, String descuento, String iva, Double costo )
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_notas_de_entrega_detalle(id_cabecera_movi, "
                    + "id_items, cantidad, precio, deta_descuento, deta_iva, costo)"                   
                    + " VALUES(" + ultFactura + ", " + idItems + ", " + cantidad + ", "
                    + precio + ", " + descuento + ", " + iva + ", " + costo + ")";           
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
    
    public boolean borrarProductosNotaEntrega(int ultFactura)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "UPDATE ck_notas_de_entrega_detalle "
                    + " SET estado = 'I' "
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
    
    public boolean insertarDetalleDevolucion(int ultFactura, int idItems, String cantidad, String precio, String descuento, String iva )
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_detalle_movi_devolucion(id_cabecera_movi_devolucion, id_items, cantidad, precio, deta_descuento, deta_iva)"                   
                    + " VALUES("+ultFactura+", "+idItems+", "+cantidad+", "+precio+", "+descuento+", "+iva+")";           
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
    
    public boolean insertarDetalleCompras(int ultFactura, int idItems, Double cantidad, Double precio, 
            String descuento, String iva, String pvp )
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_detalle_movi_compras(id_cabecera_movi_compras, "
                    + " id_items, cantidad, precio, deta_descuento, deta_iva, pvppublico)"                   
                    + " VALUES("+ultFactura+", "+idItems+", "+cantidad+", "+precio+", "
                    + descuento+", "+iva+", " + pvp + ")";           
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
    
    public ArrayList<clsDetalle>  consultarDataDetalleNotaEntrega(int idCabecera){            
        ArrayList<clsDetalle> data = new ArrayList<clsDetalle>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_detalle_movi, id_cabecera_movi, cantidad, "
                        + " precio, a.id_items id_items, des_item, cod_item,"
                        + " deta_descuento, deta_iva, a.costo costo"
                    + " FROM ck_notas_de_entrega_detalle AS a "
                    + " INNER JOIN ck_items AS b ON a.id_items = b.id_items"
                    + " WHERE id_cabecera_movi= " + idCabecera + ""
                    + " AND a.estado = 'A'";
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
                oListaTemporal.setCosto(bd.resultado.getDouble("costo"));   
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
    
    public ArrayList<clsDetalle>  consultarDataDetalleCompras(int idCabecera){            
        ArrayList<clsDetalle> data = new ArrayList<clsDetalle>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_detalle_movi, id_cabecera_movi_compras, cantidad, "
                    + " precio, a.id_items id_items, des_item, cod_item,"
                    + " deta_descuento, deta_iva, pvppublico"
                    + " FROM ck_detalle_movi_compras AS a INNER JOIN ck_items AS b ON a.id_items = b.id_items"
                    + " WHERE id_cabecera_movi_compras = " + idCabecera
                    + " AND a.estado = 'A'"
                    + " ORDER BY id_detalle_movi";
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
                oListaTemporal.setPvpPublico(bd.resultado.getDouble("pvppublico"));   
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
    
    public ArrayList<clsDetalle>  consultarDataDetalle(int idCabecera){            
        ArrayList<clsDetalle> data = new ArrayList<clsDetalle>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_detalle_movi, id_cabecera_movi, cantidad, "
                        + " precio, a.id_items id_items, des_item, cod_item, a.costo"
                        + " deta_descuento, deta_iva, a.costo costo"
                    + " FROM ck_detalle_movi AS a "
                    + " INNER JOIN ck_items AS b ON a.id_items = b.id_items"
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
                oListaTemporal.setCosto(bd.resultado.getDouble("costo"));   
                
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
    
    public boolean eliminarDetalleCompras(int idCabeceraCompra)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "UPDATE ck_detalle_movi_compras"
                    + " SET estado = 'I'"
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
    
    public ArrayList<clsDetalle>  consultarDataDetalleInv(String idCabecera){            
        ArrayList<clsDetalle> data = new ArrayList<clsDetalle>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_detalle_movi, id_cabecera_movi, cantidad, "
                        + " precio, a.id_items id_items, des_item, cod_item, a.costo, "
                        + " deta_descuento, deta_iva, a.costo costo, id_contador, "
                        + " apellido1 || ' ' || apellido2 || ' ' || nombre1 || ' ' || nombre2 nombre,"
                        + " stock_sistema, stock_contado"
                    + " FROM ck_detalle_movi_inventario AS a "
                    + " INNER JOIN ck_items AS b ON a.id_items = b.id_items"
                    + " INNER JOIN ck_personal AS c ON a.id_contador = c.id_personal"
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
                oListaTemporal.setCosto(bd.resultado.getDouble("costo"));   
                oListaTemporal.setNombre(bd.resultado.getString("nombre")); 
                oListaTemporal.setIdContador(bd.resultado.getInt("id_contador"));  
                oListaTemporal.setStockSistema(bd.resultado.getDouble("stock_sistema")); 
                oListaTemporal.setStockContado(bd.resultado.getDouble("stock_contado")); 
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
    
    public ArrayList<clsDetalle> consultaInvGrupos()
    {       
        ArrayList<clsDetalle> data = new ArrayList<clsDetalle>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_grupo_producto id_grupo, "
                        + " a.grupo_descripcion descripcion, "
                        + " count(b.id_items) items, "
                        + " sum(c.stock_sistema) sistema, "
                        + " sum(c.stock_contado) contado,"
                        + " sum(c.cantidad) diferencia, "
                        + " sum(c.cantidad * c.precio) diferencia_dinero"
                    + " FROM ck_grupo_producto AS a"
                    + " JOIN ck_items AS b ON a.id_grupo_producto = b.id_grupo_producto"
                    + " JOIN ck_detalle_movi_inventario AS c ON b.id_items = c.id_items"
                    + " GROUP BY a.id_grupo_producto, a.grupo_descripcion";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsDetalle oListaTemporal = new clsDetalle();                                                                        
                    oListaTemporal.setIdProducto(bd.resultado.getInt("id_grupo"));               
                    oListaTemporal.setDescripcionProducto(bd.resultado.getString("descripcion")); 
                    oListaTemporal.setIdContador(bd.resultado.getInt("items"));   
                    oListaTemporal.setStockSistema(bd.resultado.getDouble("sistema"));      
                    oListaTemporal.setStockContado(bd.resultado.getDouble("contado"));
                    oListaTemporal.setIVA(bd.resultado.getDouble("diferencia"));                    
                    oListaTemporal.setCosto(bd.resultado.getDouble("diferencia_dinero"));                    
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
    
    public ArrayList<clsDetalle> consultaInvGruposDetalle(String idGrupo)
    {       
        ArrayList<clsDetalle> data = new ArrayList<clsDetalle>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_grupo_producto id_grupo_producto, "
                        + " a.grupo_descripcion, "
                        + " c.id_cabecera_movi id_cabecera, "
                        + " b.id_items, "
                        + " b.cod_item, "
                        + " b.des_item, "
                        + " c.stock_sistema sistema, "
                        + " c.stock_contado contado,"
                        + " c.cantidad diferencia, "
                        + " c.cantidad * c.precio diferencia_dinero"
                    + " FROM ck_grupo_producto AS a"
                    + " JOIN ck_items AS b ON a.id_grupo_producto = b.id_grupo_producto"
                    + " JOIN ck_detalle_movi_inventario AS c ON b.id_items = c.id_items"
                    + " WHERE a.id_grupo_producto = " + idGrupo;
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsDetalle oListaTemporal = new clsDetalle();                                                                        
                    oListaTemporal.setIdProducto(bd.resultado.getInt("id_cabecera"));               
                    oListaTemporal.setCodigoProducto(bd.resultado.getString("cod_item")); 
                    oListaTemporal.setDescripcionProducto(bd.resultado.getString("des_item"));   
                    oListaTemporal.setStockSistema(bd.resultado.getDouble("sistema"));      
                    oListaTemporal.setStockContado(bd.resultado.getDouble("contado"));
                    oListaTemporal.setIVA(bd.resultado.getDouble("diferencia"));                    
                    oListaTemporal.setCosto(bd.resultado.getDouble("diferencia_dinero"));                    
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
    
    public ArrayList<clsDetalle> consultaInvGruposDetalleFecha(String idGrupo, String fechaInicio, String fechaFin)
    {       
        ArrayList<clsDetalle> data = new ArrayList<clsDetalle>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_grupo_producto id_grupo_producto, "
                        + " a.grupo_descripcion, "
                        + " c.id_cabecera_movi id_cabecera, "
                        + " b.id_items, "
                        + " b.cod_item, "
                        + " b.des_item, "
                        + " c.stock_sistema sistema, "
                        + " c.stock_contado contado,"
                        + " c.cantidad diferencia, "
                        + " c.cantidad * c.precio diferencia_dinero"
                    + " FROM ck_grupo_producto AS a"
                    + " JOIN ck_items AS b ON a.id_grupo_producto = b.id_grupo_producto"
                    + " JOIN ck_detalle_movi_inventario AS c ON b.id_items = c.id_items"
                    + " JOIN ck_cabecera_movi_inventario AS d ON c.id_cabecera_movi = d.id_cabecera_movi"
                    + " WHERE d.fecha::date >= '" + fechaInicio + "'"
                    + " AND d.fecha::date <= '" + fechaFin + "'"
                    + " AND a.id_grupo_producto = " + idGrupo;
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsDetalle oListaTemporal = new clsDetalle();                                                                        
                    oListaTemporal.setIdProducto(bd.resultado.getInt("id_cabecera"));               
                    oListaTemporal.setCodigoProducto(bd.resultado.getString("cod_item")); 
                    oListaTemporal.setDescripcionProducto(bd.resultado.getString("des_item"));   
                    oListaTemporal.setStockSistema(bd.resultado.getDouble("sistema"));      
                    oListaTemporal.setStockContado(bd.resultado.getDouble("contado"));
                    oListaTemporal.setIVA(bd.resultado.getDouble("diferencia"));                    
                    oListaTemporal.setCosto(bd.resultado.getDouble("diferencia_dinero"));                    
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
    
    public ArrayList<clsDetalle> consultaInvGruposFecha(String fechaInicio, String fechaFin)
    {       
        ArrayList<clsDetalle> data = new ArrayList<clsDetalle>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_grupo_producto id_grupo, "
                        + " a.grupo_descripcion descripcion, "
                        + " count(b.id_items) items, "
                        + " sum(c.stock_sistema) sistema, "
                        + " sum(c.stock_contado) contado,"
                        + " sum(c.cantidad) diferencia, "
                        + " sum(c.cantidad * c.precio) diferencia_dinero"
                    + " FROM ck_grupo_producto AS a"
                    + " JOIN ck_items AS b ON a.id_grupo_producto = b.id_grupo_producto"
                    + " JOIN ck_detalle_movi_inventario AS c ON b.id_items = c.id_items"
                    + " JOIN ck_cabecera_movi_inventario AS d ON c.id_cabecera_movi = d.id_cabecera_movi"
                    + " WHERE d.fecha::date >= '" + fechaInicio + "'"
                    + " AND d.fecha::date <= '" + fechaFin + "'"
                    + " GROUP BY a.id_grupo_producto, a.grupo_descripcion";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsDetalle oListaTemporal = new clsDetalle();                                                                        
                    oListaTemporal.setIdProducto(bd.resultado.getInt("id_grupo"));               
                    oListaTemporal.setDescripcionProducto(bd.resultado.getString("descripcion")); 
                    oListaTemporal.setIdContador(bd.resultado.getInt("items"));   
                    oListaTemporal.setStockSistema(bd.resultado.getDouble("sistema"));      
                    oListaTemporal.setStockContado(bd.resultado.getDouble("contado"));
                    oListaTemporal.setIVA(bd.resultado.getDouble("diferencia"));                    
                    oListaTemporal.setCosto(bd.resultado.getDouble("diferencia_dinero"));                    
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
