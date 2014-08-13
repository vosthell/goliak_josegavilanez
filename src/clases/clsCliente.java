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
public class clsCliente {
    clsConexion bd = new clsConexion(); 
    String sql;
     
    private int codigo;
    private String name_completo;
    private String cedula;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String tlf_convencional;
    private String tlf_celular;
    private Integer provincia;
    private String direccion;
    private Integer id_termino;
    private String creditomax;
    private Integer idCiudad;
    private String id_recinto;
    private String verificado_deudas;
    private String email;
    private String descripcion_incobrable;
    private String descripcion_tipo_incobrable;
    private String fecha_incobrable;
    private Integer facturas;
    private Double deuda_total;
    private String descripcion_plazo;
    private String fecha;
    private String diferencia;
    private Double valor_actual;
    private String descripcion_recinto;
    private Integer id_cabecera_movi;
    private Integer num_comentario;
    
    public int getIdCabeceraMovi() {
        return id_cabecera_movi;
    }
    
    public void setIdCabeceraMovi(int id_cabecera_movi) {
        this.id_cabecera_movi = id_cabecera_movi;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getNameCompleto() {
        return name_completo;
    }
    
    public void setNameCompleto(String name_completo) {
        this.name_completo = name_completo;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getNombre1() {
        return nombre1;
    }
    
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }
    
    public String getNombre2() {
        return nombre2;
    }
    
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
    
    public String getApellido1() {
        return apellido1;
    }
    
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    
    public String getApellido2() {
        return apellido2;
    }
    
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    
    public String getTlfConvencional() {
        return tlf_convencional;
    }
    
    public void setTlfConvencional(String tlf_convencional) {
        this.tlf_convencional = tlf_convencional;
    }
    
     public String getTlfCelular() {
        return tlf_celular;
    }
    
    public void setTlfCelular(String tlf_celular) {
        this.tlf_celular = tlf_celular;
    }
    
    public int getProvincia() {
        return provincia;
    }
    
    public void setProvincia(int provincia) {
        this.provincia = provincia;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public int getIdTermino() {
        return id_termino;
    }
    
    public void setIdTermino(int id_termino) {
        this.id_termino = id_termino;
    }
    
    public String getCreditoMax() {
        return creditomax;
    }
    
    public void setCreditoMax(String creditomax) {
        this.creditomax = creditomax;
    }
    
    public int getIdCiudad() {
        return idCiudad;
    }
    
    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }
    
    public String getIdRecinto() {
        return id_recinto;
    }
    
    public void setIdRecinto(String id_recinto) {
        this.id_recinto = id_recinto;
    }     
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getVerificadoDeudas() {
        return verificado_deudas;
    }
    
    public void setVerificadoDeudas(String verificado_deudas) {
        this.verificado_deudas = verificado_deudas;
    }
    
    public String getDescripcionIncobrable() {
        return descripcion_incobrable;
    }
    
    public void setDescripcionIncobrable(String descripcion_incobrable) {
        this.descripcion_incobrable = descripcion_incobrable;
    }
    
    public String getDescripcionTipoIncobrable() {
        return descripcion_tipo_incobrable;
    }
    
    public void setDescripcionTipoIncobrable(String descripcion_tipo_incobrable) {
        this.descripcion_tipo_incobrable = descripcion_tipo_incobrable;
    }
    
    public String getFechaIncobrable() {
        return fecha_incobrable;
    }
    
    public void setFechaIncobrable(String fecha_incobrable) {
        this.fecha_incobrable = fecha_incobrable;
    }
    
    public Integer getFacturas() {
        return facturas;
    }
    
    public void setFacturas(Integer facturas) {
        this.facturas = facturas;
    }
    
    public Double getDeudaTotal() {
        return deuda_total;
    }
    
    public void setDeudaTotal(Double deuda_total) {
        this.deuda_total = deuda_total;
    }
    
    public String getDescripcionPlazo() {
        return descripcion_plazo;
    }
    
    public void setDescripcionPlazo(String descripcion_plazo) {
        this.descripcion_plazo = descripcion_plazo;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getDiferencia() {
        return diferencia;
    }
    
    public void setDiferencia(String diferencia) {
        this.diferencia = diferencia;
    }
    
    public Double getValorActual() {
        return valor_actual;
    }
    
    public void setValorACtual(Double valor_actual) {
        this.valor_actual = valor_actual;
    }
    
    public String getDescripcionRecinto() {
        return descripcion_recinto;
    }
    
    public void setDescripcionRecinto(String descripcion_recinto) {
        this.descripcion_recinto = descripcion_recinto;
    }
    
    public int getNumComentario() {
        return num_comentario;
    }
    
    public void setNumComentario(int num_comentario) {
        this.num_comentario = num_comentario;
    }
    
    public ArrayList<clsComboBox>  consultarClientes(){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT cedula, name_completo"
                  + " FROM ck_cliente";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("cedula"),bd.resultado.getString("name_completo"));
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
     
    public ArrayList<clsCliente>  consultarDataCliente(String cedula){            
        ArrayList<clsCliente> data = new ArrayList<clsCliente>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT codigo, cedula, name_completo, verificado_deudas,"
                        + " direccion, tlf_convencional, tlf_celular, email"
                    + " FROM ck_cliente"
                    + " WHERE cedula = '" + cedula + "'"
                    + " AND estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsCliente oListaTemporal = new clsCliente();
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));
                oListaTemporal.setCodigo(bd.resultado.getInt("codigo"));
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setVerificadoDeudas(bd.resultado.getString("verificado_deudas"));
                oListaTemporal.setDireccion(bd.resultado.getString("direccion"));
                oListaTemporal.setTlfCelular(bd.resultado.getString("tlf_celular"));
                oListaTemporal.setTlfConvencional(bd.resultado.getString("tlf_convencional"));
                oListaTemporal.setEmail(bd.resultado.getString("email"));
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
    
    
     
    public List<String> consultarCedulas(){            
         List<String> data = new ArrayList<String>();  
         try{
            bd.conectarBaseDeDatos();
             sql = "SELECT cedula, name_completo"
                    + " FROM ck_cliente"
                    + " WHERE estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);            
            String line="";
            if(bd.resultado.next())
            {   
                do 
                { 
                    line = bd.resultado.getString("cedula");                   
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
    
    public boolean insertarRegistro(String cedula, String n1, String n2, String a1, 
            String a2, String convencional, String celular, String direccion, String ciudad, 
            String credito, String prov, String termino, String recinto, String email)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cliente"
                    + " (nombre1, nombre2,"
                    + " apellido1, apellido2, name_completo, tlf_convencional,"
                    + " tlf_celular, cedula, direccion, provincia, "
                    + " id_termino, creditoMax, id_ciudad, id_recinto, fecha_registro, email)"
                    + " VALUES('" + n1 + "', '" + n2 + "', '" + a1 + "', '" + a2 + "', '" + a1 +" "+a2+" "+n1+" "+n2+"', "
                    + " '"+convencional+"', '"+celular+"', '"+cedula+"', '"+direccion+"', '"+prov+"', " +termino
                    + ", "+credito+", '"+ciudad+"', "+recinto+", now(), '"+ email +"')";           
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
    
     public boolean insertarRegistroComentario(int id_cabecera_movi, String comentario)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cartera_comentario"
                    + " (id_cabecera_movi, comentario)"
                    + " VALUES(" + id_cabecera_movi + ", '" + comentario + "')";           
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
    
    public boolean comprobarCedula(String cedula)
    {
        boolean exito = false;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT codigo, cedula, name_completo"
                    + " FROM ck_cliente"
                    + " WHERE cedula = '" + cedula + "'";                  
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
    
    public ArrayList<clsCliente> consultaDataCliente(String cedula)
    {
        ArrayList <clsCliente> data = new ArrayList<clsCliente>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT codigo, nombre1, nombre2, apellido1, apellido2, tlf_convencional,"
                    + " tlf_celular, cedula, provincia, direccion, creditomax,"
                    + " id_ciudad, name_completo, id_recinto, estado, id_termino"
                    + " FROM ck_cliente"
                    + " WHERE cedula = '" + cedula + "'"
                    + " AND estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsCliente oListaTemporal = new clsCliente();
                oListaTemporal.setCodigo(bd.resultado.getInt("codigo")); 
                oListaTemporal.setNombre1(bd.resultado.getString("nombre1")); 
                oListaTemporal.setNombre2(bd.resultado.getString("nombre2")); 
                oListaTemporal.setApellido1(bd.resultado.getString("apellido1")); 
                oListaTemporal.setApellido2(bd.resultado.getString("apellido2")); 
                oListaTemporal.setTlfConvencional(bd.resultado.getString("tlf_convencional")); 
                oListaTemporal.setTlfCelular(bd.resultado.getString("tlf_celular")); 
                oListaTemporal.setProvincia(bd.resultado.getInt("provincia")); 
                oListaTemporal.setDireccion(bd.resultado.getString("direccion")); 
                oListaTemporal.setIdTermino(bd.resultado.getInt("id_termino"));  
                oListaTemporal.setCreditoMax(bd.resultado.getString("creditomax")); 
                oListaTemporal.setIdCiudad(bd.resultado.getInt("id_ciudad")); 
                oListaTemporal.setIdRecinto(bd.resultado.getString("id_recinto")); 
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo")); 
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
    
    public boolean modificarRegistro(int p_codigo, String p_cedula, String p_nombre1, String p_nombre2, String p_apellido1, String p_apellido2, String p_convencional, String p_celular, String p_direccion, String p_provincia, String p_ciudad, String p_terminos, String p_recinto, String p_credito, String p_email)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cliente"
                    + " SET cedula = '" + p_cedula + "',"                    
                        + " nombre1 = '" + p_nombre1 + "',"
                        + " nombre2 = '" + p_nombre2 + "',"
                        + " apellido1 = '" + p_apellido1 + "', "
                        + " apellido2 = '" + p_apellido2 + "', "
                        + " tlf_convencional = '" + p_convencional + "', "
                        + " tlf_celular = '" + p_celular + "', "
                        + " provincia = " + p_provincia + ", "
                        + " direccion = '" + p_direccion + "', "
                        + " id_termino = " + p_terminos + ", "
                        + " creditomax = " + p_credito + ", "
                        + " id_ciudad = " + p_ciudad + ", "
                        + " name_completo = '" + p_apellido1 + " " + p_apellido2 + " " + p_nombre2 + " " + p_nombre1 + "', "
                        + " id_recinto = " + p_recinto + ", "
                        + " email = '" + p_email + "'" 
                    + " WHERE codigo = " + p_codigo;      
           
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
    
    public boolean eliminarCliente(int p_codigo)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cliente"
                    + " SET estado = 'I'" 
                    + " WHERE codigo = " + p_codigo;      
           
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
    
    public ArrayList<clsCliente>  consultarDataClientePorNombre(String nombre){            
        ArrayList<clsCliente> data = new ArrayList<clsCliente>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT codigo, cedula, name_completo"
                    + " FROM ck_cliente"
                    + " WHERE upper(name_completo) like '%" + nombre + "%'"
                    + " AND estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsCliente oListaTemporal = new clsCliente();
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));
                oListaTemporal.setCodigo(bd.resultado.getInt("codigo"));
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
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
    
    public boolean actualizarVerificado(String idCliente, String estado_deuda)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "UPDATE ck_cliente"
                    + " SET verificado_deudas = '" + estado_deuda + "'"
                    + " WHERE codigo = " + idCliente;           
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
    
     public boolean registrarIncobrable(int codigoCliente, String tipoIncobrable, String descripcionIncobrable,
             String fechaTransaccion)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cliente_incobrable"
                    + " (codigo, id_tipo_incobrable, descripcion_incobrable, fecha_transaccion)"
                    + " VALUES(" + codigoCliente + ", " + tipoIncobrable + ", '" + descripcionIncobrable + "',"
                    + " '" + fechaTransaccion + "')";           
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
     
    public ArrayList<clsCliente>  consultaDataClienteIncobrable(){            
        ArrayList<clsCliente> data = new ArrayList<clsCliente>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT   a.codigo codigo, "
                        + " a.cedula cedula, "
                        + " a.name_completo name_completo, "
                        + " verificado_deudas,"
                        + " b.descripcion_incobrable descripcion_incobrable,"
                        + " b.fecha_transaccion fecha,"
                        + " c.descripcion descripcion_tipo_incobrable,"
                        + " count(e.valor_actual) AS facturas, "
                        + " sum(e.valor_actual) AS deuda_total"
                    + " FROM ck_cliente AS a "
                    + " JOIN ck_cliente_incobrable AS b ON a.codigo = b.codigo"
                    + " JOIN ck_tipo_incobrable AS c ON b.id_tipo_incobrable = c.id_tipo_incobrable"
                    + " INNER JOIN ck_notas_de_entrega AS d ON a.codigo = d.codigo "
                    + " INNER JOIN ck_cta_cobrar AS e ON d.id_cabecera_movi = e.id_cabecera_movi"
                    + " WHERE a.estado = 'A'"
                    + " GROUP BY a.codigo, a.cedula, a.name_completo, verificado_deudas, "
                    + " b.descripcion_incobrable, b.fecha_transaccion, c.descripcion";
            bd.resultado = bd.sentencia.executeQuery(sql);
            System.out.println(sql);
            while(bd.resultado.next()){
                clsCliente oListaTemporal = new clsCliente();
                oListaTemporal.setCodigo(bd.resultado.getInt("codigo"));
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setVerificadoDeudas(bd.resultado.getString("verificado_deudas"));
                oListaTemporal.setDescripcionIncobrable(bd.resultado.getString("descripcion_incobrable"));
                oListaTemporal.setDescripcionTipoIncobrable(bd.resultado.getString("descripcion_tipo_incobrable"));
                oListaTemporal.setFechaIncobrable(bd.resultado.getString("fecha"));
                oListaTemporal.setFacturas(bd.resultado.getInt("facturas"));
                oListaTemporal.setDeudaTotal(bd.resultado.getDouble("deuda_total"));
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
    
    public ArrayList<clsCliente>  consultarDataClienteCartera(String dias, String nombre){            
        ArrayList<clsCliente> data = new ArrayList<clsCliente>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.codigo codigo, cedula, name_completo, verificado_deudas,"
                        + " direccion, tlf_convencional, tlf_celular,"
                        + " f.descripcion descripcion_plazo, "
                        + " b.total total,"
                        + " b.fecha fecha,"
                        + " valor_actual, "
                        + " date_part('days'::text,  now()::timestamp without time zone-fecha_modificacion::timestamp without time zone ) AS diferencia,"
                        + " g.descripcion descripcion_recinto,"
                        + " b.id_cabecera_movi id_cabecera_movi, "
                        + "(SELECT count(*)"
                        + "     FROM ck_cartera_comentario x"
                        + "     WHERE x.id_cabecera_movi = b.id_cabecera_movi"
                        + "     AND x.estado = 'A') num_comentario"
                    + " FROM ck_cliente AS a"
                    + " INNER JOIN ck_notas_de_entrega AS b ON a.codigo = b.codigo"
                    + " inner join ck_cta_cobrar as ee on b.id_cabecera_movi = ee.id_cabecera_movi"
                    + " inner join ck_plazo as f on ee.id_plazo = f.id_plazo"
                    + " inner join ck_recinto as g ON a.id_recinto = g.id_recinto"
                    + " WHERE a.estado = 'A'"
                    + " AND date_part('days'::text,  now()::timestamp without time zone-fecha_modificacion::timestamp without time zone ) > " + dias
                    + " AND name_completo like '%" + nombre.toUpperCase() + "%'";
            bd.resultado = bd.sentencia.executeQuery(sql);
            System.out.println(sql);
            
            while(bd.resultado.next()){
                clsCliente oListaTemporal = new clsCliente();
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));
                oListaTemporal.setCodigo(bd.resultado.getInt("codigo"));
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setVerificadoDeudas(bd.resultado.getString("verificado_deudas"));
                oListaTemporal.setDireccion(bd.resultado.getString("direccion"));
                oListaTemporal.setTlfCelular(bd.resultado.getString("tlf_celular"));
                oListaTemporal.setTlfConvencional(bd.resultado.getString("tlf_convencional"));
                oListaTemporal.setDescripcionPlazo(bd.resultado.getString("descripcion_plazo"));
                oListaTemporal.setDeudaTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setDiferencia(bd.resultado.getString("diferencia"));
                oListaTemporal.setValorACtual(bd.resultado.getDouble("valor_actual"));
                oListaTemporal.setDescripcionRecinto(bd.resultado.getString("descripcion_recinto"));
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));
                oListaTemporal.setNumComentario(bd.resultado.getInt("num_comentario"));
                
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
}
