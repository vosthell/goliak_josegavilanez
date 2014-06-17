/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmCerrarCaja.java
 *
 * Created on 23-oct-2011, 13:56:24
 */
package pos;

import clases.clsAuditoria;
import clases.clsCabecera;
import index.main;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import clases.clsCaja;
import clases.clsEgreso;
import clases.clsPago;
import clases.clsParametros;
import clases.clsUtils;
import clases.javaMail;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import pruebas.pdf;
import stinventario.frmPrincipal;


/**
 *
 * @author Kaiser
 */
public class frmCerrarCaja extends javax.swing.JInternalFrame {
    clsCaja objCaja = new clsCaja();
    clsUtils objUtils = new clsUtils();
    clsAuditoria objAuditoria = new clsAuditoria();
    clsEgreso objEgreso = new clsEgreso(); 
    clsCabecera objCabecera = new clsCabecera();
    clsPago objPago = new clsPago();
    clsParametros objParametros = new clsParametros();
    
    int idCajaAbierta = 0;
    String txtIngresos = "";
    String txtEgresos = "";
    String txtFacturado = "";
    String txtDevoluciones = "";
    String txtPagos = "";
    String txtPagosFactura = "";
    String txtRecibosPago = "";
    String txtDiferencia = "";
    /** Creates new form frmCerrarCaja */
    
    public frmCerrarCaja() {       
        //paar reimprimir
        //main.idUser = "16";
        //idCajaAbierta = "490";
        //COMENTAR LA LINEA 70
        
        initComponents();
       
        txtUsuario.setText(main.nameUser);    
        
        /*Esta cambie para reimprimir*/
        ArrayList<clases.clsCaja> dataCaja = objCaja.consultarDataCajaAbierta(main.idUser);
               
        txtValorApertura.setText(""+dataCaja.get(0).getValorApertura());
        idCajaAbierta = dataCaja.get(0).getIdCajaOperacion();        
        
        //OBTENER VALOR FACTURADO
        txtFacturado = ""+objCaja.obtenerValorFacturado(idCajaAbierta);
        
        //OBTENER VALOR DEVOLUCIONES
        txtDevoluciones = ""+objCaja.obtenerValorDevolucionesVentas(idCajaAbierta);
        
        //OBTENER VALOR DE ABONOS
        txtPagos=""+objUtils.redondear(objCaja.obtenerValorPagos(idCajaAbierta));
        
        //OBTENER VALOR DE PAGOS/FACTURA
        txtPagosFactura = ""+objUtils.redondear(objCaja.obtenerValorPagosFactura(idCajaAbierta));
        
        //OBTENER VALOR DE RECIBOS/COUTAS INICIALES/ARRIENDOS
        txtRecibosPago =""+objUtils.redondear(objCaja.obtenerValorRecibos(idCajaAbierta));
        
        //OBTENER VALOR DE INGRESOS
        txtIngresos = ""+objUtils.redondear(objEgreso.obtenerValorEgresos(idCajaAbierta, "I"));
        
        //OBTENER VALOR DE EGRESOS
        txtEgresos = ""+objUtils.redondear(objEgreso.obtenerValorEgresos(idCajaAbierta, "E"));
        
        //OBTENER CAJERO
        String cajero = objCaja.obtenerCajero(main.idUser);
        lblCaja.setText(cajero); 
        Double valorx = 0.00 - (Double.parseDouble(txtValorApertura.getText())+             
                objCaja.obtenerValorFacturado(idCajaAbierta)-
                objCaja.obtenerValorDevolucionesVentas(idCajaAbierta)+
                objCaja.obtenerValorRecibos(idCajaAbierta)+
                objCaja.obtenerValorPagos(idCajaAbierta) +
                objCaja.obtenerValorPagosFactura(idCajaAbierta) +
                objEgreso.obtenerValorEgresos(idCajaAbierta, "I") - 
                objEgreso.obtenerValorEgresos(idCajaAbierta, "E"));
        txtDiferencia=""+objUtils.redondear(valorx);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    static boolean isDouble(String cadena){
        try {
            Double.parseDouble(cadena);
            return true;
        }
        catch (NumberFormatException nfe){
            return false;
        } 
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtValorApertura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtValorContado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblCaja = new javax.swing.JTextField();
        btnCerrar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmCerrarCaja.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtUsuario.setEditable(false);
        txtUsuario.setText(resourceMap.getString("txtUsuario.text")); // NOI18N
        txtUsuario.setName("txtUsuario"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtValorApertura.setEditable(false);
        txtValorApertura.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorApertura.setText(resourceMap.getString("txtValorApertura.text")); // NOI18N
        txtValorApertura.setName("txtValorApertura"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtValorContado.setFont(resourceMap.getFont("txtValorContado.font")); // NOI18N
        txtValorContado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorContado.setText(resourceMap.getString("txtValorContado.text")); // NOI18N
        txtValorContado.setName("txtValorContado"); // NOI18N
        txtValorContado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorContadoKeyReleased(evt);
            }
        });

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        lblCaja.setEditable(false);
        lblCaja.setText(resourceMap.getString("lblCaja.text")); // NOI18N
        lblCaja.setName("lblCaja"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtValorContado)
                    .addComponent(txtValorApertura)
                    .addComponent(lblCaja)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtValorApertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtValorContado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        btnCerrar.setIcon(resourceMap.getIcon("btnCerrar.icon")); // NOI18N
        btnCerrar.setText(resourceMap.getString("btnCerrar.text")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(resourceMap.getIcon("btnImprimir.icon")); // NOI18N
        btnImprimir.setText(resourceMap.getString("btnImprimir.text")); // NOI18N
        btnImprimir.setName("btnImprimir"); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnImprimir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtValorContadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorContadoKeyReleased
    String x = txtValorContado.getText();
    /*if(x.isEmpty())
    {
        txtValorContado.setText("0");
    }*/
    
    if(!isDouble(x)){
        txtValorContado.setText("");
       
    }
    else
    {
        Double minuendo = Double.parseDouble(this.txtValorContado.getText());
        //Double minuendo2 = Double.parseDouble(this.txtPagos.getText());
        Double sustraendo = Double.parseDouble(this.txtValorApertura.getText())+
                            Double.parseDouble(this.txtFacturado)-
                            Double.parseDouble(this.txtDevoluciones)+
                            Double.parseDouble(this.txtRecibosPago)+
                            Double.parseDouble(this.txtPagos) +
                            Double.parseDouble(this.txtPagosFactura) +
                            Double.parseDouble(this.txtIngresos)-
                            Double.parseDouble(this.txtEgresos);
        Double diferencia = minuendo-sustraendo;
        this.txtDiferencia=""+objUtils.redondear(diferencia);     
        //this.txtDiferencia.setText(""+diferencia);
        /*if(this.txtDiferencia.equals("0.0")||this.txtDiferencia.equals("-0.0"))
                this.txtDiferencia.setBackground(Color.green);
        else
                this.txtDiferencia.setBackground(Color.red);*/
    }
}//GEN-LAST:event_txtValorContadoKeyReleased

private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
    if(txtValorContado.getText().equals(""))
    {    
        JOptionPane.showMessageDialog(this, "Ingrese un valor correcto", "Atención!", JOptionPane.ERROR_MESSAGE);
    }
    else
    {
        /*BORRO ESTA PORQUE SOLOE STA VALIDANDO LA CAJA ABIERTA POR EL USUARIO, 
         PERO MEJOR SERIA YA ENVIARLE EL ID DE CAJA BIERTA O LA Q SE ESTA UTILIZANDO*/
        boolean exito = objCaja.registrarCierreCaja(index.main.idUser, 
                                                        ""+(Double.parseDouble(txtFacturado) - Double.parseDouble(txtDevoluciones)), 
                                                        this.txtValorContado.getText().toString(), 
                                                        this.txtDiferencia, 
                                                        Double.parseDouble(txtPagos),
                                                        Double.parseDouble(txtPagosFactura),
                                                        Double.parseDouble(txtEgresos),
                                                        Double.parseDouble(txtRecibosPago),
                                                        Double.parseDouble(txtIngresos));
        /*boolean exito = objCaja.registrarCierreCaja2(index.main.idUser, 
                                                        ""+(Double.parseDouble(txtFacturado) - Double.parseDouble(txtDevoluciones)), 
                                                        this.txtValorContado.getText().toString(), 
                                                        this.txtDiferencia, 
                                                        Double.parseDouble(this.txtPagos),
                                                        Double.parseDouble(this.txtPagosFactura),
                                                        Double.parseDouble(txtEgresos),
                                                        Double.parseDouble(txtRecibosPago),
                                                        Double.parseDouble(txtIngresos),
                                                        this.idCajaAbierta);*/
        if(exito)
        {
            //OBTENER IDCAJERO
            String idCajero = objCaja.obtenerIdCajero(main.idUser);
            
            //MARCAR CAJERO USADO
            objCaja.modificarCaja(idCajero, "I");
            JOptionPane.showMessageDialog(this, "Datos almacenados con éxito", "Atención!", JOptionPane.INFORMATION_MESSAGE);
            objAuditoria.insertarAuditoria("frmCerrarCaja", "CERRO CAJA CON: $ "+                                            
                                            this.txtValorContado.getText().toString() +
                                            ", TUVO DIFERENCIA: $ " +
                                            this.txtDiferencia, "1");
            //********************//            
            frmPrincipal.lblPendiente.setText("");
            frmPrincipal.btnAbrir.setEnabled(true);  
            frmPrincipal.btnFacturar.setEnabled(false);
            frmPrincipal.btnPagos.setEnabled(false);
            frmPrincipal.btnCerrar.setEnabled(false); 
            frmPrincipal.btnEgreso.setEnabled(false); 
            frmPrincipal.btnIngreso.setEnabled(false);             
            frmPrincipal.btnCajaAntes.setVisible(false);
             //********************//
            imprimir();
            
            /*frmEnviarCorreo formulario = new frmEnviarCorreo();
            mostrarJInternalCentrado(formulario); */
            //ENVIAR CORREO
            String email_habilitado = objParametros.consultaValor("email_habilitado");
            if(email_habilitado.equals("1"))
            {    
                frmEnviarCorreo ventana = new frmEnviarCorreo(null, true, idCajaAbierta);
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            }
            //ENVIAR CORREO - FIN
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Error al registrar", "Atención!", JOptionPane.ERROR_MESSAGE);
            objAuditoria.insertarAuditoria("frmCerrarCaja", "INTENTÓ CERRAR CAJA CON: $ "+                                            
                                            this.txtValorContado.getText().toString() +
                                            ", TUVO DIFERENCIA: $ " +
                                            this.txtDiferencia, "1");
        }
    }
    dispose();
        
}//GEN-LAST:event_btnCerrarActionPerformed

private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
    imprimir();
}//GEN-LAST:event_btnImprimirActionPerformed

void imprimir()
{
    //Calendar calendario = Calendar.getInstance();
    DecimalFormat df1 = new DecimalFormat("###0.00"); 
    Calendar calendario = new GregorianCalendar();
    int hora, minutos, segundos, dia, mes,annio;
    hora =calendario.get(Calendar.HOUR_OF_DAY);
    minutos = calendario.get(Calendar.MINUTE);
    segundos = calendario.get(Calendar.SECOND);
    dia = calendario.get(Calendar.DATE);
    mes = calendario.get(Calendar.MONTH) +1;
    annio = calendario.get(Calendar.YEAR);
    
    Double totalFacturas = 0.00;
    Double totalDevoluciones = 0.00;
    Double totalPagos = 0.00;
    Double totalPagosFactura = 0.00;
    Double totalPagosRecibo = 0.00;
    Double totalEgresos = 0.00;
    Double totalIngresos = 0.00;
    FileWriter fichero = null;
    PrintWriter pw = null;
    
    String RESULT   = objParametros.consultaValor("ruta_pdf_cierre_caja") + annio + "_" + mes + "_" + dia + ".pdf";
    Document document = new Document();
    try {
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
    } catch (FileNotFoundException ex) {
        Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
    } catch (DocumentException ex) {
        Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
    }
    // step 3
    document.open();
    
    try
    {
        fichero = new FileWriter(objUtils.HostSystem + "file00001.txt");
        pw = new PrintWriter(fichero);
        /*40COLUMNAS*/
        
        pw.println(annio + "/" + mes + "/" + dia + " " +hora + ":" + minutos + ":" + segundos);
        document.add(new Paragraph(annio + "/" + mes + "/" + dia + " " +hora + ":" + minutos + ":" + segundos));
        pw.println("VALOR APERTURA: " + txtValorApertura.getText());
        document.add(new Paragraph("VALOR APERTURA: " + txtValorApertura.getText()));
        //FACTURAS
        ArrayList<clsCabecera> dataFacturas = objCabecera.consultaFacturasRealizadas(idCajaAbierta); 
        ArrayList<clsCabecera> dataDevoluciones = objCabecera.consultaDevolucionesRealizadas(idCajaAbierta); 
        
        int maxData = dataFacturas.size();
        int maxDevoluciones = dataDevoluciones.size();
        
        String factReferencia = "";
        //SI HAY FACTURAS LAS PRESNETA
        if(maxData>0)
        {    
            pw.println("FACTURAS");
            document.add(new Paragraph("FACTURAS"));
            
            pw.println("----------------------------------------");
            document.add(new Paragraph("----------------------------------------"));
            
            for(int i=0;i<maxData;i++)
            {
                 factReferencia = dataFacturas.get(i).getFactReferencia() + "                                         "; 
                 pw.println((i+1) + " " + factReferencia.substring(0, 28) + " " + 
                                objUtils.rellenar(""+df1.format(dataFacturas.get(i).getEfectivo())));
                 document.add(new Paragraph(((i+1) + " " + factReferencia.substring(0, 28) + " " + 
                                objUtils.rellenar(""+df1.format(dataFacturas.get(i).getEfectivo())))));
                 totalFacturas = totalFacturas + dataFacturas.get(i).getEfectivo();                 
            } 
            if(maxDevoluciones>0)
            {    
                for(int i=0;i<maxDevoluciones;i++)
                {
                     factReferencia = dataDevoluciones.get(i).getIdCabeceraMovi() + "                                         "; 
                     pw.println((i+1) + " DEVOLUCION:" + factReferencia.substring(0, 16) + " -" + 
                                    objUtils.rellenar(""+df1.format(dataDevoluciones.get(i).getEfectivo())));
                     document.add(new Paragraph(((i+1) + " DEVOLUCION:" + factReferencia.substring(0, 16) + " -" + 
                                    objUtils.rellenar(""+df1.format(dataDevoluciones.get(i).getEfectivo())))));
                     totalDevoluciones = totalDevoluciones + dataDevoluciones.get(i).getEfectivo();                 
                } 
                /*pw.println("TOTAL FACTURADO: " + objUtils.redondear(totalFacturas));
                pw.println(" ");*/
            }
            totalFacturas = totalFacturas - totalDevoluciones;
            pw.println("TOTAL FACTURADO: " + objUtils.redondear(totalFacturas));
            document.add(new Paragraph("TOTAL FACTURADO: " + objUtils.redondear(totalFacturas)));
            pw.println(" ");
            document.add(new Paragraph(""));
        }
       
        //PAGOS        
        ArrayList<clsPago> dataPagos = objPago.consultaPagosRealizadas(idCajaAbierta); 
        maxData = dataPagos.size();
        String referencia = "";
        if(maxData>0)
        { 
            //pw.println("PAGOS");
            pw.println("ABONOS");
            document.add(new Paragraph("ABONOS"));
            pw.println("----------------------------------------");
            document.add(new Paragraph("----------------------------------------"));
            
            for(int i=0; i<maxData;i++)
            {
                //referencia = dataPagos.get(i).getReferencia()+ "                                         "; 
                referencia = dataPagos.get(i).getReferencia()+ "                                         "; 
                pw.println((i+1) +  " " + referencia.substring(0, 28) + " " + 
                                objUtils.rellenar(""+df1.format(dataPagos.get(i).getValor())));   
                document.add(new Paragraph((i+1) +  " " + referencia.substring(0, 28) + " " + 
                                objUtils.rellenar(""+df1.format(dataPagos.get(i).getValor()))));
                totalPagos = totalPagos + dataPagos.get(i).getValor();                
            } 
            pw.println("TOTAL ABONOS: " + objUtils.redondear(totalPagos));
            document.add(new Paragraph("TOTAL ABONOS: " + objUtils.redondear(totalPagos)));
            pw.println(" ");
            document.add(new Paragraph(" "));
        }
        
        //PAGOS   FACTURA     
        ArrayList<clsPago> dataPagosFactura = objPago.consultaPagosFacturaRealizadas(idCajaAbierta); 
        maxData = dataPagosFactura.size();
        //String referencia = "";
        if(maxData>0)
        { 
            pw.println("ABONOS/FACTURA");
            document.add(new Paragraph("ABONOS/FACTURA"));
            pw.println("----------------------------------------");
            document.add(new Paragraph("----------------------------------------"));
            for(int i=0; i<maxData;i++)
            {
                referencia = dataPagosFactura.get(i).getNombreCliente()+ "                                         "; 
                pw.println((i+1) +  " " + referencia.substring(0, 28) + " " + 
                                objUtils.rellenar(""+df1.format(dataPagosFactura.get(i).getValor())));  
                document.add(new Paragraph((i+1) +  " " + referencia.substring(0, 28) + " " + 
                                objUtils.rellenar(""+df1.format(dataPagosFactura.get(i).getValor()))));  
                 totalPagosFactura = totalPagosFactura + dataPagosFactura.get(i).getValor();                
            } 
            pw.println("TOTAL ABONOS/FACT: " + objUtils.redondear(totalPagosFactura));
            document.add(new Paragraph("TOTAL ABONOS/FACT: " + objUtils.redondear(totalPagosFactura)));
            pw.println(" ");
            document.add(new Paragraph(" "));
        }
        
        //PAGOS RECIBO     
        ArrayList<clsPago> dataPagosRecibo = objPago.consultaPagosRecibo(idCajaAbierta); 
        maxData = dataPagosRecibo.size();
        //String referencia = "";
        if(maxData>0)
        { 
            pw.println("ABONOS/ENTRADA (RECIBO)");
            document.add(new Paragraph("ABONOS/ENTRADA (RECIBO)"));
            pw.println("----------------------------------------");
            document.add(new Paragraph("----------------------------------------"));
            for(int i=0; i<maxData;i++)
            {
                referencia = dataPagosRecibo.get(i).getNombreCliente()+ "                                         "; 
                pw.println((i+1) +  " " + referencia.substring(0, 28) + " " + 
                                objUtils.rellenar(""+df1.format(dataPagosRecibo.get(i).getValor())));   
                document.add(new Paragraph((i+1) +  " " + referencia.substring(0, 28) + " " + 
                                objUtils.rellenar(""+df1.format(dataPagosRecibo.get(i).getValor()))));   
                totalPagosRecibo = totalPagosRecibo + dataPagosRecibo.get(i).getValor();                
            } 
            pw.println("TOTAL ABONOS/ENTRADA: " + objUtils.redondear(totalPagosRecibo));
            document.add(new Paragraph("TOTAL ABONOS/ENTRADA: " + objUtils.redondear(totalPagosRecibo)));
            pw.println(" ");
            document.add(new Paragraph(" "));
        }
        
        //INGRESOS
        ArrayList<clsEgreso> dataIngresos = objEgreso.consultaEgresosRealizadas(idCajaAbierta, "I"); 
        maxData = dataIngresos.size();
        String concepto = "";
        if(maxData>0)
        { 
            pw.println("INGRESOS");
            document.add(new Paragraph("INGRESOS"));
            
            pw.println("----------------------------------------");
            document.add(new Paragraph("----------------------------------------"));
            
            for(int i=0;i<maxData;i++)
            {                
                concepto = dataIngresos.get(i).getConcepto() + "                                         "; 
                pw.println((i+1) +  " " + concepto.substring(0, 28) + " " + 
                                objUtils.rellenar(""+df1.format(dataIngresos.get(i).getCantidadEgreso())));
                document.add(new Paragraph((i+1) +  " " + concepto.substring(0, 28) + " " + 
                                objUtils.rellenar(""+df1.format(dataIngresos.get(i).getCantidadEgreso()))));
                totalIngresos = totalIngresos + dataIngresos.get(i).getCantidadEgreso();                
            }
            pw.println("TOTAL INGRESOS: " + objUtils.redondear(totalIngresos));
            document.add(new Paragraph("TOTAL INGRESOS: " + objUtils.redondear(totalIngresos)));
            pw.println(" ");
            document.add(new Paragraph(" "));
        }
        
        //EGRESOS
        ArrayList<clsEgreso> dataEgresos = objEgreso.consultaEgresosRealizadas(idCajaAbierta, "E"); 
        maxData = dataEgresos.size();
        concepto = "";
        if(maxData>0)
        { 
            pw.println("EGRESOS");
            document.add(new Paragraph("EGRESOS"));
            pw.println("----------------------------------------");
            document.add(new Paragraph("----------------------------------------"));
            for(int i=0;i<maxData;i++)
            {                
                concepto = dataEgresos.get(i).getConcepto() + "                                         "; 
                pw.println((i+1) +  " " + concepto.substring(0, 28) + " " + 
                                objUtils.rellenar(""+df1.format(dataEgresos.get(i).getCantidadEgreso())));
                document.add(new Paragraph((i+1) +  " " + concepto.substring(0, 28) + " " + 
                                objUtils.rellenar(""+df1.format(dataEgresos.get(i).getCantidadEgreso()))));
                totalEgresos = totalEgresos + dataEgresos.get(i).getCantidadEgreso();                
            }
            pw.println("TOTAL EGRESOS: " + objUtils.redondear(totalEgresos));
            document.add(new Paragraph("TOTAL EGRESOS: " + objUtils.redondear(totalEgresos)));
            pw.println(" ");
            document.add(new Paragraph(" "));
        }
       
        Double totalCierre = Double.parseDouble(txtValorApertura.getText()) + 
                totalFacturas + totalPagos + totalPagosFactura + totalPagosRecibo + totalIngresos - totalEgresos;
        pw.println("TOTAL:                          $"+ objUtils.rellenar(""+objUtils.redondear(totalCierre)));
        document.add(new Paragraph("TOTAL:                          $"+ objUtils.rellenar(""+objUtils.redondear(totalCierre))));
        
        pw.println("CONTADO EN DINERO:              $"+ objUtils.rellenar(txtValorContado.getText()));
        document.add(new Paragraph("CONTADO EN DINERO:              $"+ objUtils.rellenar(txtValorContado.getText())));
        
        double diferencia = objUtils.redondear(Double.parseDouble(txtValorContado.getText()) - totalCierre);
        String mensajeEmail =""; 
        if(diferencia == 0.0||diferencia ==-0.0)
        {        
            pw.println("VALORES CUADRADOS");
            document.add(new Paragraph("VALORES CUADRADOS"));
            mensajeEmail = "VALORES CUADRADOS";
        }
        else if(diferencia>0)
        {        
            pw.println("SOBRANTE:                       $" + objUtils.rellenar(""+diferencia));
            document.add(new Paragraph("SOBRANTE:                       $" + objUtils.rellenar(""+diferencia)));
            mensajeEmail = "SOBRANTE: $" + objUtils.rellenar(""+diferencia);
        }
        else if(diferencia<0)
        {       
            pw.println("FALTANTE:                       $" + objUtils.rellenar(""+diferencia));
            document.add(new Paragraph("FALTANTE:                       $" + objUtils.rellenar(""+diferencia)));
            mensajeEmail = "FALTANTE: $" + objUtils.rellenar(""+diferencia);
        }       
        
        Runtime aplicacion = Runtime.getRuntime(); 
        try{
            //IMPRIMIR 2 VECES
            for(int x=0; x<1; x++)
                aplicacion.exec("cmd.exe /K "+ objUtils.HostSystem + "printFile.bat"); 
            
            //ENVIAR CORREO
            /*javaMail mail = new javaMail();
            mail.send("vosthell@hotmail.com","CIERRE DE CAJA", "EL USUARIO: " 
                        + txtUsuario.getText().toString()
                        + ", CERRO CAJA CON DINERO EN EFECTIVO: $ " + txtValorContado.getText() + "</BR>"
                        + " SISTEMA: $ " + objUtils.redondear(totalCierre) + "</BR>"
                        + " OBSERVACION: " +  mensajeEmail + "</BR>"
                        + "<TABLE>"
                            + "<tr><td>DESCRIPCION</td><td>VALOR</td></tr>"
                            + "<TR><TD>TOTAL FACTURADO:</TD><TD>" + objUtils.redondear(totalFacturas)+ "</TD></TR>"
                            + "<TR><TD>TOTAL ABONOS:</TD><TD>" + objUtils.redondear(totalPagos)+ "</TD></TR>"
                            + "<TR><TD>TOTAL ABONOS/FACT:</TD><TD>" + objUtils.redondear(totalPagosFactura)+ "</TD></TR>"
                            + "<TR><TD>TOTAL ABONOS/ENTRADA:</TD><TD>" + objUtils.redondear(totalPagosRecibo)+ "</TD></TR>"
                            + "<TR><TD>TOTAL INGRESOS:</TD><TD>" + objUtils.redondear(totalIngresos)+ "</TD></TR>"
                            + "<TR><TD>TOTAL EGRESOS:</TD><TD>" + objUtils.redondear(totalEgresos)+ "</TD></TR>"                
                        + "</TABLE>");*/
        }
        catch(Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error al imprimir y enviar correo", JOptionPane.ERROR_MESSAGE);
  
        }
        
        
    } 
    catch (Exception e) 
    {
        e.printStackTrace();
    }          
    finally 
    {
       try {
       // Nuevamente aprovechamos el finally para 
       // asegurarnos que se cierra el fichero.
       if (null != fichero)
          fichero.close();
       } catch (Exception e2) {
          e2.printStackTrace();
       }
    }
    // step 5
    document.close();
    
    try {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+RESULT);
        //System.out.println("Final");
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

public static void mostrarJInternalCentrado(javax.swing.JInternalFrame formulario)
    {
        Dimension desktopSize = frmPrincipal.jDesktopPane1.getSize();
        Dimension jInternalFrameSize = formulario.getSize();
        formulario.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
        (desktopSize.height- jInternalFrameSize.height)/2);

        frmPrincipal.jDesktopPane1.add(formulario);
        formulario.show(); 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lblCaja;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtValorApertura;
    private javax.swing.JTextField txtValorContado;
    // End of variables declaration//GEN-END:variables
}