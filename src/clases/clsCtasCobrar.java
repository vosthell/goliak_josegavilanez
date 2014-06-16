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
public class clsCtasCobrar {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String id_cta_cobrar;
    private String fact_referencia;
    private String descripcion;
    private String valor;
    private String valor_actual;
    private String id_cabecera_movi;
    private Double porcentaje_interes;
    
    public String getIdCtaCobrar() {
        return id_cta_cobrar;
    }
    
    public void setIdCtaCobrar(String id_cta_cobrar) {
        this.id_cta_cobrar = id_cta_cobrar;
    }
    
    public String getFactReferencia() {
        return fact_referencia;
    }
    
    public void setFactReferencia(String fact_referencia) {
        this.fact_referencia = fact_referencia;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getValor() {
        return valor;
    }
    
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public String getValorActual() {
        return valor_actual;
    }
    
    public void setValorActual(String valor_actual) {
        this.valor_actual = valor_actual;
    }
    
    public String getIdCabeceraMovi() {
        return id_cabecera_movi;
    }
    
    public void setIdCabeceraMovi(String id_cabecera_movi) {
        this.id_cabecera_movi = id_cabecera_movi;
    }
    
    public Double getPorcentajeInteres() {
        return porcentaje_interes;
    }
    
    public void setPorcentajeInteres(Double porcentaje_interes) {
        this.porcentaje_interes = porcentaje_interes;
    }
    
    public ArrayList<clsCtasCobrar> consultaCtasCobrarNotasEntrega(String idCliente)
    {
        ArrayList <clsCtasCobrar> data=new ArrayList<clsCtasCobrar>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "select ck_cta_cobrar.id_cta_cobrar, fact_referencia, ck_cta_cobrar.descripcion, "
                        + " ck_cta_cobrar.valor valor, ck_cta_cobrar.valor_actual valor_actual, "
                        + " ck_cta_cobrar.id_cabecera_movi, ck_notas_de_entrega.codigo, "
                        + " ck_notas_de_entrega.porcentaje_interes porc"
                    + " FROM ck_cta_cobrar  "
                    + " INNER JOIN ck_notas_de_entrega "
                    + " ON ck_cta_cobrar.id_cabecera_movi = ck_notas_de_entrega.id_cabecera_movi "
                    + " WHERE ck_notas_de_entrega.codigo = " + idCliente
                    + " AND id_caja_operacion=0"
                    + " AND ck_notas_de_entrega.estado = 'A'"
                    + " AND (ck_cta_cobrar.estado = 'A' OR ck_cta_cobrar.estado = 'C')"
                    + " AND ck_notas_de_entrega.estado_tramite = 'S'"
                    + " ORDER BY valor_actual DESC";
                    //+ " --and valor_actual>0"
                    
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsCtasCobrar oListaTemporal = new clsCtasCobrar();
                oListaTemporal.setIdCtaCobrar(bd.resultado.getString("id_cta_cobrar")); 
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));
                oListaTemporal.setValor(bd.resultado.getString("valor"));
                oListaTemporal.setValorActual(bd.resultado.getString("valor_actual"));
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getString("id_cabecera_movi"));
                oListaTemporal.setPorcentajeInteres(bd.resultado.getDouble("porc"));
                        
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
    
    public ArrayList<clsCtasCobrar> consultaNotasEntregaContado(String idCliente)
    {
        ArrayList <clsCtasCobrar> data=new ArrayList<clsCtasCobrar>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT 'CONTADO' id_cta_cobrar, "
                        + " a.fact_referencia, a.comentario, "
                        + " a.total valor, a.saldo valor_actual, "
                        + " a.id_cabecera_movi, a.codigo"
                    + " FROM  ck_notas_de_entrega AS a"
                    + " WHERE a.codigo = " + idCliente
                    + " AND a.id_caja_operacion = 0"
                    + " AND a.estado = 'A'"
                    + " AND a.id_cabecera_movi NOT IN (SELECT d.id_cabecera_movi "
                    + "                                 FROM ck_notas_de_entrega AS d "
                    + "                                 JOIN ck_cta_cobrar AS e ON d.id_cabecera_movi = e.id_cabecera_movi)"                 
                    + " ORDER BY a.saldo DESC";
                    //+ " --and valor_actual>0"
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsCtasCobrar oListaTemporal = new clsCtasCobrar();
                oListaTemporal.setIdCtaCobrar(bd.resultado.getString("id_cta_cobrar")); 
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setDescripcion(bd.resultado.getString("comentario"));
                oListaTemporal.setValor(bd.resultado.getString("valor"));
                oListaTemporal.setValorActual(bd.resultado.getString("valor_actual"));
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getString("id_cabecera_movi"));
                
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
    
    public ArrayList<clsCtasCobrar> consultaCtasCobrarNotasEntregaConSaldo(String idCliente)
    {
        ArrayList <clsCtasCobrar> data=new ArrayList<clsCtasCobrar>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "select ck_cta_cobrar.id_cta_cobrar, fact_referencia, ck_cta_cobrar.descripcion, "
                    + " ck_cta_cobrar.valor valor, ck_cta_cobrar.valor_actual valor_actual, "
                    + " ck_cta_cobrar.id_cabecera_movi, ck_notas_de_entrega.codigo, ck_notas_de_entrega.porcentaje_interes porc"
                    + " FROM ck_cta_cobrar  "
                    + " INNER JOIN ck_notas_de_entrega "
                    + " ON ck_cta_cobrar.id_cabecera_movi = ck_notas_de_entrega.id_cabecera_movi "
                    + " WHERE ck_notas_de_entrega.codigo = " + idCliente
                    + " AND id_caja_operacion=0"
                    + " AND ck_notas_de_entrega.estado = 'A'"
                    + " AND ck_cta_cobrar.estado = 'A'"
                    + " AND valor_actual>0"
                    + " AND ck_notas_de_entrega.estado_tramite = 'S'"
                    + " ORDER BY valor_actual DESC";
                    
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsCtasCobrar oListaTemporal = new clsCtasCobrar();
                oListaTemporal.setIdCtaCobrar(bd.resultado.getString("id_cta_cobrar")); 
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));
                oListaTemporal.setValor(bd.resultado.getString("valor"));
                oListaTemporal.setValorActual(bd.resultado.getString("valor_actual"));
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getString("id_cabecera_movi"));
                oListaTemporal.setPorcentajeInteres(bd.resultado.getDouble("porc"));
                
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
    
    public ArrayList<clsCtasCobrar> consultaCtasCobrarFacturas(String idCliente)
    {
        ArrayList <clsCtasCobrar> data=new ArrayList<clsCtasCobrar>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "select ck_cta_cobrar_facturas.id_cta_cobrar, fact_referencia, ck_cta_cobrar_facturas.descripcion, "
                    + " ck_cta_cobrar_facturas.valor valor, ck_cta_cobrar_facturas.valor_actual valor_actual, "
                    + " ck_cta_cobrar_facturas.id_cabecera_movi, ck_cabecera_movi.codigo"
                    + " FROM ck_cta_cobrar_facturas  "
                    + " INNER JOIN ck_cabecera_movi ON ck_cta_cobrar_facturas.id_cabecera_movi = ck_cabecera_movi.id_cabecera_movi "
                    + " WHERE ck_cabecera_movi.codigo = " + idCliente
                    + " AND id_caja_operacion<>0"
                    + " AND ck_cabecera_movi.estado = 'A'"
                    + " AND (ck_cta_cobrar_facturas.estado = 'A' OR ck_cta_cobrar_facturas.estado = 'C')"
                    + " ORDER BY valor_actual DESC";
                    //+ " --and valor_actual>0"
            System.out.println("SQL enviado:" + sql);       
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsCtasCobrar oListaTemporal = new clsCtasCobrar();
                oListaTemporal.setIdCtaCobrar(bd.resultado.getString("id_cta_cobrar")); 
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));
                oListaTemporal.setValor(bd.resultado.getString("valor"));
                oListaTemporal.setValorActual(bd.resultado.getString("valor_actual"));
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getString("id_cabecera_movi"));
                oListaTemporal.setPorcentajeInteres(0.00);
                
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
    
    public ArrayList<clsCtasCobrar> consultaCtasCobrarFacturasConSaldo(String idCliente)
    {
        ArrayList <clsCtasCobrar> data=new ArrayList<clsCtasCobrar>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "select ck_cta_cobrar_facturas.id_cta_cobrar, fact_referencia, ck_cta_cobrar_facturas.descripcion, "
                    + " ck_cta_cobrar_facturas.valor valor, ck_cta_cobrar_facturas.valor_actual valor_actual, "
                    + " ck_cta_cobrar_facturas.id_cabecera_movi, ck_cabecera_movi.codigo"
                    + " FROM ck_cta_cobrar_facturas  "
                    + " INNER JOIN ck_cabecera_movi ON ck_cta_cobrar_facturas.id_cabecera_movi = ck_cabecera_movi.id_cabecera_movi "
                    + " WHERE ck_cabecera_movi.codigo = " + idCliente
                    + " AND id_caja_operacion<>0"
                    + " AND ck_cabecera_movi.estado = 'A'"
                    + " AND ck_cta_cobrar_facturas.estado = 'A'"
                    + " AND valor_actual>0"
                    + " ORDER BY valor_actual DESC";
                    
            System.out.println("SQL enviado:" + sql);       
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsCtasCobrar oListaTemporal = new clsCtasCobrar();
                oListaTemporal.setIdCtaCobrar(bd.resultado.getString("id_cta_cobrar")); 
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));
                oListaTemporal.setValor(bd.resultado.getString("valor"));
                oListaTemporal.setValorActual(bd.resultado.getString("valor_actual"));
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getString("id_cabecera_movi"));
                oListaTemporal.setPorcentajeInteres(0.00);
                
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
    
    public ArrayList<clsCtasCobrar> consultaCtasCobrar(String idCliente)
    {
        ArrayList <clsCtasCobrar> data=new ArrayList<clsCtasCobrar>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "select ck_cta_cobrar.id_cta_cobrar, fact_referencia, ck_cta_cobrar.descripcion, "
                    + " ck_cta_cobrar.valor valor, ck_cta_cobrar.valor_actual valor_actual, "
                    + " ck_cta_cobrar.id_cabecera_movi, ck_cabecera_movi.codigo"
                    + " FROM ck_cta_cobrar "
                    + " INNER JOIN ck_cabecera_movi "
                    + " ON ck_cta_cobrar.id_cabecera_movi = ck_cabecera_movi.id_cabecera_movi "
                    + " WHERE ck_cabecera_movi.codigo = " + idCliente
                    + " --AND id_caja_operacion<>0"
                    + " ORDER BY valor_actual DESC";
                    //+ " --and valor_actual>0"
            System.out.println("SQL enviado:" + sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsCtasCobrar oListaTemporal = new clsCtasCobrar();
                oListaTemporal.setIdCtaCobrar(bd.resultado.getString("id_cta_cobrar")); 
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));
                oListaTemporal.setValor(bd.resultado.getString("valor"));
                oListaTemporal.setValorActual(bd.resultado.getString("valor_actual"));
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getString("id_cabecera_movi"));
                
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
    
    public boolean actualizarSaldoHisto(String idCtaCobrar, double valor, String fechaPago)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar "
                    + " SET valor_actual = valor_actual-" + valor
                    + " , fecha_modificacion = '"+fechaPago+"'"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
    public boolean actualizarSaldoNuevo(int idCtaCobrar, double valor)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar "
                    + " SET valor_actual = valor_actual-" + valor
                    + " , fecha_modificacion = NOW()"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
    public boolean actualizarSaldoFacturas(String idCtaCobrar, double valor, String fechaPago)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar_facturas "
                    + " SET valor_actual = valor_actual-" + valor
                    + " , fecha_modificacion = '"+fechaPago+"'"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
    public boolean actualizarSaldoFacturasNuevo(int idCtaCobrar, double valor)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar_facturas "
                    + " SET valor_actual = valor_actual-" + valor
                    + " , fecha_modificacion = NOW()"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
     public boolean actualizarSaldo(String idCtaCobrar, double valor)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar "
                    + " SET valor_actual = valor_actual-" + valor
                    + " , fecha_modificacion = now()"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
    public boolean registrarCancelacionHistorico(String idCtaCobrar, String fecha)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar "
                    + " SET fecha_cancelacion = '" + fecha + "',"
                    + " estado = 'C'"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
    public boolean registrarCancelacionNuevo(int idCtaCobrar)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar "
                    + " SET fecha_cancelacion = NOW(),"
                    + " estado = 'C'"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
    public boolean registrarCancelacionDeudaFactura(String idCtaCobrar, String fecha)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar_facturas "
                    + " SET fecha_cancelacion = '" + fecha + "',"
                    + " estado = 'C'"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
    public boolean registrarCancelacionDeudaFacturaNuevo(int idCtaCobrar)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar_facturas "
                    + " SET fecha_cancelacion = NOW(),"
                    + " estado = 'C'"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
        
    public boolean registrarCancelacion(String idCtaCobrar)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar "
                    + " SET fecha_cancelacion = now(),"
                    + " estado = 'C'"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
    public Double consultarSaldoCta(String idCtaCobrar)
    {          
        Double valorFacturado = 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT valor_actual "
                    + " FROM ck_cta_cobrar " 
                    + " WHERE id_cta_cobrar = " + idCtaCobrar;
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                valorFacturado = bd.resultado.getDouble("valor_actual");              
            }
            //return valorFacturado;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            valorFacturado = null;
        }   
        System.out.println("saldo:" + valor);
        return valorFacturado;
    }
    
    public Double consultarSaldoCtaFactura(String idCtaCobrar)
    {          
        Double valorFacturado = 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT valor_actual "
                    + " FROM ck_cta_cobrar_facturas " 
                    + " WHERE id_cta_cobrar = " + idCtaCobrar;
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                valorFacturado = bd.resultado.getDouble("valor_actual");              
            }
            //return valorFacturado;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            valorFacturado = null;
        }   
        return valorFacturado;
    }
    
    public boolean actualizarSaldoHistoSumar(int idCabeceraMovi, double valor)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar "
                    + " SET valor_actual = valor_actual + " + valor                    
                    + " WHERE id_cabecera_movi= " + idCabeceraMovi;
                   
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
    
    public boolean actualizarSaldoHistoSumar_factura(int idCabeceraMovi, double valor)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar_facturas "
                    + " SET valor_actual = valor_actual + " + valor                    
                    + " WHERE id_cabecera_movi= " + idCabeceraMovi;
                   
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
