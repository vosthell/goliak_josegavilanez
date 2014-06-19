/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kaiser
 */
public class clsProducto {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String id_items;
    private String cod_item;
    private String des_item;
    private Double cant_stock;
    private Double costo;
    private String control_perecible;
    private String fecha_caducidad;
    private String control_existencia;
    private String control_minimo;
    private String cantidad_minima;
    private Double descuento; 
    private Integer id_grupo_producto;
    private String estado;
    private Double precio1; 
    private boolean inv_1; 
    private String imagen;
    private Double cant_bodega;
    
    public String getIdItems() {
        return id_items;
    }
    
    public void setIdItems(String id_items) {
        this.id_items = id_items;
    }
    
    public String getCodItem() {
        return cod_item;
    }
    
    public void setCodItem(String cod_item) {
        this.cod_item = cod_item;
    }
    
    public String getDesItem() {
        return des_item;
    }
    
    public void setDesItem(String des_item) {
        this.des_item = des_item;
    }
    
    public Double getCantStock() {
        return cant_stock;
    }
    
    public void setCantStock(Double cant_stock) {
        this.cant_stock = cant_stock;
    }
    
    public Double getCantBodega() {
        return cant_bodega;
    }
    
    public void setCantBodega(Double cant_bodega) {
        this.cant_bodega = cant_bodega;
    }
    
    public Double getCosto() {
        return costo;
    }
    
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    
    public String getControlPerecible() {
        return control_perecible;
    }
    
    public void setControlPerecible(String control_perecible) {
        this.control_perecible = control_perecible;
    }
    
    public String getFechaCaducidad() {
        return fecha_caducidad;
    }
    
    public void setFechaCaducidad(String fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }
    
    public String getControlExistencia() {
        return control_existencia;
    }
    
    public void setControlExistencia(String control_existencia) {
        this.control_existencia = control_existencia;
    }
    
     public String getCantidadMinima() {
        return cantidad_minima;
    }
    
    public void setCantidadMinima(String cantidad_minima) {
        this.cantidad_minima = cantidad_minima;
    }
    
    public String getControlMinimo() {
        return control_minimo;
    }
    
    public void setControlMinimo(String control_minimo) {
        this.control_minimo = control_minimo;
    }
    
    public Double getDescuento() {
        return descuento;
    }
    
    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
    
    public int getIdGrupoProducto() {
        return id_grupo_producto;
    }
    
    public void setIdGrupoProducto(int id_grupo_producto) {
        this.id_grupo_producto = id_grupo_producto;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Double getPrecio1() {
        return precio1;
    }
    
    public void setPrecio1(Double precio1) {
        this.precio1 = precio1;
    }
    
    public Boolean getInv1() {
        return inv_1;
    }
    
    public void setInv1(boolean inv_1) {
        this.inv_1 = inv_1;
    }
    
    public String getImagen() {
        return imagen;
    }
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public List<String> consultarCodigos(){            
        List<String> data = new ArrayList<String>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT trim(cod_item) cod_item"
                  + " FROM ck_items"
                    + " WHERE estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
             
            String line = "";
            if(bd.resultado.next())
            {   
                do 
                { 
                    line = bd.resultado.getString("cod_item");                   
                    data.add(line);
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
    
    public boolean insertarProducto(String codigo, String descripcion, String costo, 
            String cantidad, char controlMinimo, String cantidadMinima, 
            String controlExistencia, char controlPerecible, String fechaCaducidad, 
            String descuento, String idGrupo, Double primerPrecio,
            String nombreArchivoRandom, String bodega)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_items"
                    + "(cod_item, des_item, costo, cant_stock, fecha_caducidad,"
                    + " control_minimo, cantidad_minima, "
                    + " control_existencia, control_perecible, descuento, id_grupo_producto, "
                    + " precio1, imagen, cant_bodega)"
                    + " VALUES('" + codigo + "', '" + descripcion + "', " + costo 
                    + " , " + cantidad + ", '" + fechaCaducidad + "',"
                    + " '" + controlMinimo + "', " + cantidadMinima+","
                    + " '"+ controlExistencia+"', '" + controlPerecible + "', " + descuento + ", " + idGrupo + ", "
                    + primerPrecio+ ", '" + nombreArchivoRandom + "', " + bodega + ")";           
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
    
    public boolean comprobarCodigo(String codigo)
    {
        boolean exito;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT cod_item, des_item "    
                    + " FROM ck_items"
                    + " WHERE cod_item = '" + codigo + "'";               
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
    
    public ArrayList<clsProducto>  consultarDataProductoPorNombre(String nombre){            
         ArrayList<clsProducto> data = new ArrayList<clsProducto>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_items, cod_item, des_item, cant_stock, descuento, "
                    + " control_existencia, precio1, costo"
                    + " FROM ck_items"
                    + " WHERE upper(des_item) like '%" + nombre + "%'"
                    + " AND estado='A'"
                    + " ORDER BY des_item";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsProducto oListaTemporal = new clsProducto();
                oListaTemporal.setIdItems(bd.resultado.getString("id_items"));
                oListaTemporal.setCodItem(bd.resultado.getString("cod_item"));
                oListaTemporal.setDesItem(bd.resultado.getString("des_item"));
                oListaTemporal.setCantStock(bd.resultado.getDouble("cant_stock"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setControlExistencia(bd.resultado.getString("control_existencia"));
                oListaTemporal.setPrecio1(bd.resultado.getDouble("precio1"));
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
    
    public ArrayList<clsProducto>  consultarDataProductoPorNombreConStock(String nombre, String soloNoInventariados){            
         ArrayList<clsProducto> data = new ArrayList<clsProducto>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT c.id_items, cod_item, des_item, cant_stock, descuento, "
                    + " control_existencia, precio1, costo, inv_1"
                    + " FROM ck_items AS c"
                    + " WHERE upper(des_item) like '%" + nombre + "%'"
                    + " AND c.estado='A'";
            if(soloNoInventariados.equals("S"))
            {
                sql = sql + " AND c.id_items NOT IN (SELECT a.id_items "
                     + " FROM ck_detalle_movi_inventario AS a"
                     + " JOIN ck_cabecera_movi_inventario AS b "
                     + " ON a.id_cabecera_movi = b.id_cabecera_movi"
                     + " AND b.estado = 'A'"
                     + " GROUP BY a.id_items)";
            }
                    //+ " AND cant_stock<>0"
            sql = sql + " ORDER BY des_item"; 
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsProducto oListaTemporal = new clsProducto();
                oListaTemporal.setIdItems(bd.resultado.getString("id_items"));
                oListaTemporal.setCodItem(bd.resultado.getString("cod_item"));
                oListaTemporal.setDesItem(bd.resultado.getString("des_item"));
                oListaTemporal.setCantStock(bd.resultado.getDouble("cant_stock"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setControlExistencia(bd.resultado.getString("control_existencia"));
                oListaTemporal.setPrecio1(bd.resultado.getDouble("precio1"));
                oListaTemporal.setCosto(bd.resultado.getDouble("costo"));
                oListaTemporal.setInv1(bd.resultado.getBoolean("inv_1"));
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
    
    public ArrayList<clsProducto>  consultarDataProductoPorCodigoConStock(String codigo, String soloNoInventariados){            
         ArrayList<clsProducto> data = new ArrayList<clsProducto>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT c.id_items, cod_item, des_item, cant_stock, descuento, "
                    + " control_existencia, precio1, costo, inv_1"
                    + " FROM ck_items AS c"
                    + " WHERE upper(cod_item) like '%" + codigo + "%'"
                    + " AND c.estado='A'";
            if(soloNoInventariados.equals("S"))
            {
                sql = sql + " AND c.id_items NOT IN (SELECT a.id_items "
                     + " FROM ck_detalle_movi_inventario AS a"
                     + " JOIN ck_cabecera_movi_inventario AS b "
                     + " ON a.id_cabecera_movi = b.id_cabecera_movi"
                     + " AND b.estado = 'A'"
                     + " GROUP BY a.id_items)";
            }
                    //+ " AND cant_stock<>0"
            sql = sql + " ORDER BY des_item";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsProducto oListaTemporal = new clsProducto();
                oListaTemporal.setIdItems(bd.resultado.getString("id_items"));
                oListaTemporal.setCodItem(bd.resultado.getString("cod_item"));
                oListaTemporal.setDesItem(bd.resultado.getString("des_item"));
                oListaTemporal.setCantStock(bd.resultado.getDouble("cant_stock"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setControlExistencia(bd.resultado.getString("control_existencia"));
                oListaTemporal.setPrecio1(bd.resultado.getDouble("precio1"));
                oListaTemporal.setCosto(bd.resultado.getDouble("costo"));
                oListaTemporal.setInv1(bd.resultado.getBoolean("inv_1"));
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
    
    public ArrayList<clsProducto>  consultarDataProductoPorPrecio(String precio){            
         ArrayList<clsProducto> data = new ArrayList<clsProducto>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_items, cod_item, des_item, cant_stock, descuento, "
                    + " control_existencia, precio1, costo"
                    + " FROM ck_items"
                    + " WHERE (precio1 = " + precio + ")"
                    + " AND estado='A'"
                    + " ORDER BY des_item";
            bd.resultado = bd.sentencia.executeQuery(sql);
            System.out.println(sql);
            while(bd.resultado.next()){
                clsProducto oListaTemporal = new clsProducto();
                oListaTemporal.setIdItems(bd.resultado.getString("id_items"));
                oListaTemporal.setCodItem(bd.resultado.getString("cod_item"));
                oListaTemporal.setDesItem(bd.resultado.getString("des_item"));
                oListaTemporal.setCantStock(bd.resultado.getDouble("cant_stock"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setControlExistencia(bd.resultado.getString("control_existencia"));
                oListaTemporal.setPrecio1(bd.resultado.getDouble("precio1"));
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
    
    public ArrayList<clsProducto>  consultarDataProductosAll(){            
         ArrayList<clsProducto> data = new ArrayList<clsProducto>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_items, cod_item, des_item, cant_stock, descuento, control_existencia, precio1"
                    + " FROM ck_items"
                    + " WHERE estado='A'"
                    + " ORDER BY des_item";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsProducto oListaTemporal = new clsProducto();
                oListaTemporal.setIdItems(bd.resultado.getString("id_items"));
                oListaTemporal.setCodItem(bd.resultado.getString("cod_item"));
                oListaTemporal.setDesItem(bd.resultado.getString("des_item"));
                oListaTemporal.setCantStock(bd.resultado.getDouble("cant_stock"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setControlExistencia(bd.resultado.getString("control_existencia"));
                oListaTemporal.setPrecio1(bd.resultado.getDouble("precio1"));
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
    
    public ArrayList<clsProducto>  consultarDataProductosAllConStock(String soloNoInventariados){            
         ArrayList<clsProducto> data = new ArrayList<clsProducto>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT c.id_items, cod_item, des_item, cant_stock, descuento, "
                    + " control_existencia, precio1, inv_1"
                    + " FROM ck_items AS c"
                    + " WHERE c.estado='A'";
            if(soloNoInventariados.equals("S"))
            {
                sql = sql + " AND c.id_items NOT IN (SELECT a.id_items "
                     + " FROM ck_detalle_movi_inventario AS a"
                     + " JOIN ck_cabecera_movi_inventario AS b "
                     + " ON a.id_cabecera_movi = b.id_cabecera_movi"
                     + " AND b.estado = 'A'"
                     + " GROUP BY a.id_items)";
            }
            //+ " AND cant_stock <> 0"
            sql = sql + " ORDER BY des_item";
                    
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsProducto oListaTemporal = new clsProducto();
                oListaTemporal.setIdItems(bd.resultado.getString("id_items"));
                oListaTemporal.setCodItem(bd.resultado.getString("cod_item"));
                oListaTemporal.setDesItem(bd.resultado.getString("des_item"));
                oListaTemporal.setCantStock(bd.resultado.getDouble("cant_stock"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setControlExistencia(bd.resultado.getString("control_existencia"));
                oListaTemporal.setPrecio1(bd.resultado.getDouble("precio1"));
                oListaTemporal.setInv1(bd.resultado.getBoolean("inv_1"));
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
    
     public ArrayList<clsProducto>  consultarDataProductosAllConStock2(String soloInventariados){            
         ArrayList<clsProducto> data = new ArrayList<clsProducto>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT c.id_items, cod_item, des_item, cant_stock, descuento, "
                    + " control_existencia, precio1, inv_1"
                    + " FROM ck_items AS c"
                    + " WHERE c.estado='A'";
            if(soloInventariados.equals("S"))
            {
                sql = sql + " AND c.id_items IN (SELECT a.id_items "
                     + " FROM ck_detalle_movi_inventario AS a"
                     + " JOIN ck_cabecera_movi_inventario AS b "
                     + " ON a.id_cabecera_movi = b.id_cabecera_movi"
                     + " AND b.estado = 'A'"
                     + " GROUP BY a.id_items)";
            }
            //+ " AND cant_stock <> 0"
            sql = sql + " ORDER BY des_item";
                    
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsProducto oListaTemporal = new clsProducto();
                oListaTemporal.setIdItems(bd.resultado.getString("id_items"));
                oListaTemporal.setCodItem(bd.resultado.getString("cod_item"));
                oListaTemporal.setDesItem(bd.resultado.getString("des_item"));
                oListaTemporal.setCantStock(bd.resultado.getDouble("cant_stock"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setControlExistencia(bd.resultado.getString("control_existencia"));
                oListaTemporal.setPrecio1(bd.resultado.getDouble("precio1"));
                oListaTemporal.setInv1(bd.resultado.getBoolean("inv_1"));
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
    
    public ArrayList<clsProducto>  consultarDataProductoPorCodigo(String codigo){            
        ArrayList<clsProducto> data = new ArrayList<clsProducto>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_items, cod_item, des_item, cant_stock, costo, "
                        + " control_perecible, fecha_caducidad, control_existencia, "
                        + " control_minimo, cantidad_minima, descuento, id_grupo_producto, "
                        + " precio1, imagen, cant_bodega"
                    + " FROM ck_items"
                    + " WHERE cod_item = '" + codigo + "'"
                    + " AND estado='A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsProducto oListaTemporal = new clsProducto();
                oListaTemporal.setIdItems(bd.resultado.getString("id_items"));
                oListaTemporal.setCodItem(bd.resultado.getString("cod_item"));
                oListaTemporal.setDesItem(bd.resultado.getString("des_item"));
                oListaTemporal.setCantStock(bd.resultado.getDouble("cant_stock"));
                oListaTemporal.setCosto(bd.resultado.getDouble("costo"));
                oListaTemporal.setControlPerecible(bd.resultado.getString("control_perecible"));
                oListaTemporal.setFechaCaducidad(bd.resultado.getString("fecha_caducidad"));
                oListaTemporal.setControlExistencia(bd.resultado.getString("control_existencia"));
                oListaTemporal.setControlMinimo(bd.resultado.getString("control_minimo"));
                oListaTemporal.setCantidadMinima(bd.resultado.getString("cantidad_minima"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIdGrupoProducto(bd.resultado.getInt("id_grupo_producto"));
                oListaTemporal.setPrecio1(bd.resultado.getDouble("precio1"));
                oListaTemporal.setImagen(bd.resultado.getString("imagen"));
                oListaTemporal.setCantBodega(bd.resultado.getDouble("cant_bodega"));
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
    
    public boolean modificarDataProducto(int p_id, String codigo, String descripcion, String stock, String costo, 
            char controlMinimo, String cantidadMinima, String controlExistencia, 
            char controlPerecible, String fechaCaducidad, String descuento, String idGrupo, 
            String estado, Double primerPrecio, String nombreRandom)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_items"
                    + " SET cod_item = '" + codigo + "',"
                    + " des_item = '"+descripcion+"',"
                    + " costo = "+costo+","
                    + " cant_stock = "+stock+","
                    + " control_minimo = '"+controlMinimo+"',"
                    + " cantidad_minima = "+cantidadMinima+","
                    + " control_existencia='"+controlExistencia+"',"
                    + " control_perecible='"+controlPerecible+"',"
                    + " fecha_caducidad='"+fechaCaducidad+"',"
                    + " descuento = " + descuento +","
                    + " id_grupo_producto = " + idGrupo +","
                    + " estado = '" + estado + "',"
                    + " precio1 = " + primerPrecio + ","
                    + " imagen = '" + nombreRandom+ "'"         
                    + " WHERE id_items = " + p_id;      
           
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
    
    public boolean actualizarEstado(int p_id, boolean var)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_items"
                    + " SET inv_1 = " + var
                    + " WHERE id_items = " + p_id;      
           
            System.out.println("SQL enviado:" + sql);
            bd.sentencia.executeUpdate(sql);
            exito = true;
        }
        catch(SQLException e)
        {
            System.out.println("Error SQL:" + e);
            exito = false;
        } 
        bd.desconectarBaseDeDatos();
        return exito;
    }     
    
    public Integer obtenerUltimoProducto()
    {          
        int ultimo = 0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_items) AS maximo"
                    + " FROM ck_items";
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                ultimo = bd.resultado.getInt("maximo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            ultimo = 0;
        } 
        bd.desconectarBaseDeDatos();
        return ultimo;
    }
    
    public boolean insertarVerificado(int idItem)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_tmp_verificado"
                    + " (id_items)"
                    + " VALUES("+idItem+")";           
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
    
    public boolean disminuirStockAlmacen(int p_id, String cantidad)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_items"
                    + " SET cant_stock = cant_stock - " + cantidad                                     
                    + " WHERE id_items = " + p_id;            
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
    
    public boolean disminuirStockBodega(int p_id, String cantidad)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_items"
                    + " SET cant_bodega = cant_bodega - " + cantidad                                     
                    + " WHERE id_items = " + p_id;            
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
    
    public boolean aumentarStockAlmacen(int p_id, double cantidad)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_items"
                    + " SET cant_stock = cant_stock + (" + cantidad + ")"                                     
                    + " WHERE id_items = " + p_id;            
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
    
    public boolean aumentarStockBodega(int p_id, double cantidad)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_items"
                    + " SET cant_bodega = cant_bodega + (" + cantidad + ")"                                     
                    + " WHERE id_items = " + p_id;            
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
    
    public boolean actualizarCosto(int p_id, Double precio)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_items"
                    + " SET costo = " + precio                                     
                    + " WHERE id_items = " + p_id;            
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
    
    public boolean comprobarVerificado(int id_items)
    {
        boolean exito;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_items "    
                    + " FROM ck_tmp_verificado"
                    + " WHERE id_items = " + id_items;               
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
    
    public boolean eliminarProducto(int p_codigo)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_items"
                    + " SET estado = 'I'" 
                    + " WHERE id_items = " + p_codigo;      
           
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
    
    public ArrayList<clsProducto>  consultarDataProductoPorCodigoTodosAI(String codigo){            
        ArrayList<clsProducto> data = new ArrayList<clsProducto>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_items, cod_item, des_item, cant_stock, costo, "
                    + " control_perecible, fecha_caducidad, control_existencia, "
                    + " control_minimo, cantidad_minima, descuento, id_grupo_producto, estado, "
                    + " imagen"
                    + " FROM ck_items"
                    + " WHERE cod_item = '" + codigo + "'";                    
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsProducto oListaTemporal = new clsProducto();
                oListaTemporal.setIdItems(bd.resultado.getString("id_items"));
                oListaTemporal.setCodItem(bd.resultado.getString("cod_item"));
                oListaTemporal.setDesItem(bd.resultado.getString("des_item"));
                oListaTemporal.setCantStock(bd.resultado.getDouble("cant_stock"));
                oListaTemporal.setCosto(bd.resultado.getDouble("costo"));
                oListaTemporal.setControlPerecible(bd.resultado.getString("control_perecible"));
                oListaTemporal.setFechaCaducidad(bd.resultado.getString("fecha_caducidad"));
                oListaTemporal.setControlExistencia(bd.resultado.getString("control_existencia"));
                oListaTemporal.setControlMinimo(bd.resultado.getString("control_minimo"));
                oListaTemporal.setCantidadMinima(bd.resultado.getString("cantidad_minima"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIdGrupoProducto(bd.resultado.getInt("id_grupo_producto"));
                oListaTemporal.setEstado(bd.resultado.getString("estado"));
                oListaTemporal.setImagen(bd.resultado.getString("imagen"));
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
    
    public String consultarImagenProducto(int idProducto)
    {          
        String imagenx = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT imagen"
                    + " FROM ck_items"
                    + " WHERE id_items = " + idProducto;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                imagenx = bd.resultado.getString("imagen");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            imagenx = "";
        } 
        bd.desconectarBaseDeDatos();
        return imagenx;
    }
    
}

