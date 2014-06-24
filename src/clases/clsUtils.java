/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author CKaiser
 */
public class clsUtils {
    public String versionID = "1.20";
    public String versionFecha = " (2014.06.24.11.42)";
    public String version = versionID + versionFecha;
    //MENSAJE CUANDO NO TIENE PERMISOS PARA ABRIR FORMULARIO
    public String msgSinPermisosFormulario = "USTED NO TIENE PERMISOS PARA ABRIR ESTE FORMULARIO.";
    public String msgTitleSinPermisos = "Atención!";
    
    //HOSPEDAJE DE ARCHIVOS
    public String HostSystem = "C:/goliak/";
     //HOSPEDAJE DE REPORTES
    public String HostSystemReportes = HostSystem + "reportes/";
    //NOMBRE DEL SISTEMA
    public String nombreSistema = "Goliak - ";
    public String nombreLargoSistema = "Goliak - Sistema Integrado de Información Empresarial";
    //USUARIO
    public String userBD = "postgres";
    //CLAVE
    public String passBD = "majcp071102kaiser";
    //BD
    public String nameBD = "goliak_jg";
    //TITULO DE  LA VENTANA
    public String tituloVentanaMensaje = "Atención!";
    //ERROR TEXT VACIOS
    public String camposVacios = "Ingrese correctamente la información";
    //Datos Guardados con exito
    public String exitoGuardar = "Datos almacenados con éxito";
    //Error al guardar información
    public String errorGuardar = "Error al guardar información";
    //ARCHIVO RESPALDAR
    public String batRespaldar = "respaldarGoliak";
    //archivo DE IMPRESION LPT1
    public String archivoImprimir1 = "printFile.bat";
    
    public String archivoImprimirCupones = "printFileCupones.bat";
    //archivo DE IMPRESION LPT3
    public String archivoImprimir3 = "printFile3.bat";
    //archivo DE IMPRESION abrircaja
    public String abrirCaja = "abrirCaja.bat";
    //archivoq  se genera para imprimir
    public String archivoPrint = "file00001.txt";
    //archivoq  se genera para abrirCaja
    public String archivoPrint2 = "file00002.txt";
    
    //VERIFICAR SI ES DOUBLE
    public boolean isDouble(String cadena){
        try {
            Double.parseDouble(cadena);
            return true;
        }
        catch (NumberFormatException nfe){
            return false;
        } 
    }
    
    //VERIFICAR SI ES INTEGER
    public boolean isEntero(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        }
        catch (NumberFormatException nfe){
            return false;
        } 
    }
    
    //REDONDEAR 1 2 DECIMALES UN NUMERO
    public double redondear(double numero)
    {
        /*numero = numero*100;
        numero = (int) numero;
        numero = (numero/100);*/        
        Locale.setDefault(Locale.ENGLISH);       
        DecimalFormat formateador = new DecimalFormat("####.##");
        
        return Double.parseDouble(formateador.format(numero));            
    } 
    
    public double redondearCincoDec(double numero)
    {
        /*numero = numero*100;
        numero = (int) numero;
        numero = (numero/100);*/
        
        Locale.setDefault(Locale.ENGLISH);       
        DecimalFormat formateador = new DecimalFormat("####.#####");
        
        return Double.parseDouble(formateador.format(numero));            
    } 
    
    public double redondearCuatroDec(double numero)
    {
        /*numero = numero*100;
        numero = (int) numero;
        numero = (numero/100);*/
        
        Locale.setDefault(Locale.ENGLISH);       
        DecimalFormat formateador = new DecimalFormat("####.####");
        
        return Double.parseDouble(formateador.format(numero));            
    } 
    //LIMPIAR JTABLE
    public void limpiarJTable(DefaultTableModel dtmData){       
         int contRows = dtmData.getRowCount();
            for (int i = 0; i < contRows; i++){
                dtmData.removeRow(0);
            }   
    }
    
    //OCULTAR COLUMNAS DE UNA TABLA
    public void setOcultarColumnasJTable(JTable tbl, int columna[])
    {
        for(int i=0;i<columna.length;i++)
        {
            tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }
    
    //ENUMERAR LAS FILAS  DE UNA TABLA CON SU MODELO
    public void enumerarFilas(DefaultTableModel p_dtmData, int columna)
    {
        int maxData = p_dtmData.getRowCount();
        for(int i=0; i<maxData; i++)
        {
            p_dtmData.setValueAt(i+1, i, columna);
        }
    }
    
    //VACIAR DATOS DE UNA TABLA
    public void vaciarTabla(DefaultTableModel p_dtmData)
    {
        int contRows = p_dtmData.getRowCount();
        for (int i = 0; i < contRows; i++)
        {
            p_dtmData.removeRow(0);
        }  
    }
    
     public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        }else{
            return false;
        }
    }   
     
      //Me ayuda a alinear texto de valores a la derecha de la impresion en el POS
    public String rellenar(String data)
    {
        String data2 = "";
        if(data.length()<5)
        {    data2 = "   " + data;}
        if(data.length()==5)
        {     data2 = "  " + data;}
        if(data.length()==6)
        {    data2 = " " + data;}
        if(data.length()>6)
        {    data2 = data;}
        return data2;
    }
    
    public String generarRandom(int longitud)
    {
        int cont=0;
        String nombrearchivorandom = "";
        String [] mazoparadesorden= {"A","B","C","D","E","F","G","H","I","J" ,"K","L","M", "N","O","P","Q","R","S","T","U","V","W", "X","Y","Z", "1","2","3","4","5","6","7","8","9","0", "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        //contador para hacer el procedimiento la misma cantidad de veces que la cantidad de cartas que hay
        while (cont<longitud){
            //se aplica el random para desordenar el mazo
            int numRandon = (int) Math.round(Math.random() * 61 ) ;
            //para que los valores vallan concatenados
            nombrearchivorandom = nombrearchivorandom + mazoparadesorden[numRandon];
            //contador del while
            cont++;
        }//fin while
        return nombrearchivorandom;
    }
}
