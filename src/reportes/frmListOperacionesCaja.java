/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmReporteVentas.java
 *
 * Created on 10-jul-2012, 17:11:43
 */
package reportes;

import clases.clsCabecera;
import clases.clsCaja;
import clases.clsEgreso;
import clases.clsPago;
import clases.clsUtils;
import clases.javaMail;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CKaiser
 */
public class frmListOperacionesCaja extends javax.swing.JInternalFrame {
    clsUtils objUtils = new clsUtils();
    clsCabecera objCabecera = new clsCabecera();
    clsPago objPago = new clsPago();
    clsCaja objCaja = new clsCaja();
    clsEgreso objEgreso = new clsEgreso();
    MiModelo dtmData = new MiModelo();
    /** Creates new form frmReporteVentas */
    public frmListOperacionesCaja() {
        initComponents();
        
        dtmData.addColumn("N°");/*.setPreferredWidth(500)*/
        dtmData.addColumn("FECHA DE APERTURA");
        dtmData.addColumn("FECHA DE CIERRE");
        dtmData.addColumn("NOMBRE");
        dtmData.addColumn("VALOR APERTURA");
        dtmData.addColumn("VALOR FACTURADO");
        dtmData.addColumn("INGRESOS");
        dtmData.addColumn("ABONOS");
        dtmData.addColumn("RECIBOS/PAGO"); 
        dtmData.addColumn("ABONO/FACTURA");
        
        dtmData.addColumn("EGRESOS");   
        dtmData.addColumn("TOTAL SISTEMA");   
        dtmData.addColumn("TOTAL CONTADO");   
        dtmData.addColumn("DIFERENCIA");  
        dtmData.addColumn("OBSERVACION"); 
        
        dtmData.addColumn("ID");  
        dtmData.addColumn("REIMPRIMIR");  
        
        Date fechaActual = new Date();
        txtFechaInicio.setDate(fechaActual);
        txtFechaFin.setDate(fechaActual); 
        
        //ALINEAR COLUMNA
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblData.getColumnModel().getColumn(3).setCellRenderer(tcr);  
        tblData.getColumnModel().getColumn(4).setCellRenderer(tcr);  
        tblData.getColumnModel().getColumn(5).setCellRenderer(tcr);  
        tblData.getColumnModel().getColumn(6).setCellRenderer(tcr);  
        tblData.getColumnModel().getColumn(7).setCellRenderer(tcr);  
        tblData.getColumnModel().getColumn(8).setCellRenderer(tcr);  
        tblData.getColumnModel().getColumn(9).setCellRenderer(tcr);  
        tblData.getColumnModel().getColumn(10).setCellRenderer(tcr);  
        tblData.getColumnModel().getColumn(11).setCellRenderer(tcr);  
        tblData.getColumnModel().getColumn(12).setCellRenderer(tcr);  
        
        tblData.setAutoCreateRowSorter(true);  
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtFechaInicio = new com.toedter.calendar.JDateChooser();
        txtFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        chkFechaRegistro = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        btnImprimir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmListOperacionesCaja.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        txtFechaInicio.setDateFormatString(resourceMap.getString("txtFechaInicio.dateFormatString")); // NOI18N
        txtFechaInicio.setEnabled(false);
        txtFechaInicio.setName("txtFechaInicio"); // NOI18N
        txtFechaInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtFechaInicioPropertyChange(evt);
            }
        });

        txtFechaFin.setDateFormatString(resourceMap.getString("txtFechaFin.dateFormatString")); // NOI18N
        txtFechaFin.setEnabled(false);
        txtFechaFin.setName("txtFechaFin"); // NOI18N
        txtFechaFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtFechaFinPropertyChange(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        btnBuscar.setText(resourceMap.getString("btnBuscar.text")); // NOI18N
        btnBuscar.setName("btnBuscar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        chkFechaRegistro.setText(resourceMap.getString("chkFechaRegistro.text")); // NOI18N
        chkFechaRegistro.setName("chkFechaRegistro"); // NOI18N
        chkFechaRegistro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkFechaRegistroItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkFechaRegistro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 312, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkFechaRegistro)
                        .addComponent(jLabel2))
                    .addComponent(btnBuscar)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblData.setModel(dtmData);
        tblData.setName("tblData"); // NOI18N
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblData);

        btnImprimir.setText(resourceMap.getString("btnImprimir.text")); // NOI18N
        btnImprimir.setEnabled(false);
        btnImprimir.setName("btnImprimir"); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtTotal.setFont(resourceMap.getFont("txtTotal.font")); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText(resourceMap.getString("txtTotal.text")); // NOI18N
        txtTotal.setName("txtTotal"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnImprimir)
                        .addGap(472, 472, 472)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnImprimir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtFechaInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFechaInicioPropertyChange
    //buscar_informacion();
}//GEN-LAST:event_txtFechaInicioPropertyChange

private void txtFechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFechaFinPropertyChange
    //buscar_informacion();
}//GEN-LAST:event_txtFechaFinPropertyChange

private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    buscar_informacion();
    
}//GEN-LAST:event_btnBuscarActionPerformed
    public void llenarData(ArrayList<clsCaja> dataCaja)
    {
        Double totalEfectivo = 0.00;        
        
        DecimalFormat df1 = new DecimalFormat("##0.00"); 
        /*dtmData.addColumn("FECHA DE APERTURA");
        dtmData.addColumn("FECHA DE CIERRE");
        dtmData.addColumn("VALOR APERTURA");
        dtmData.addColumn("VALOR FACTURADO");
        dtmData.addColumn("INGRESOS");
        dtmData.addColumn("RECIBOS/PAGO");   
        dtmData.addColumn("EGRESOS");   
        dtmData.addColumn("TOTAL SISTEMA");   
        dtmData.addColumn("TOTAL CONTADO");   
        dtmData.addColumn("DIFERENCIA");  
        dtmData.addColumn("OBSERVACION");   */
       
        int maxData = dataCaja.size();  
        String fecha_apertura = "";
        String fecha_cierre = "";

        for(int i=0; i<maxData; i++)
        {                    
             //fecha_realizada = dataPago.get(i).getFechaPago().substring(0, 16);    
             fecha_apertura = dataCaja.get(i).getFechaApertura().substring(0, 16);    
             fecha_cierre = dataCaja.get(i).getFechaCierre().substring(0, 16);    
             /*Object[] nuevaFila = {};
             if(dataCaja.get(i).getCierre().equals("N"))
             {
                 Object[] nuevaFila = {  i+1,                          
                                        fecha_apertura,
                                        fecha_cierre,
                                        "$ " + df1.format(dataCaja.get(i).getValorApertura()), 
                                        "CAJA NO CERRADA", 
                                        "CAJA NO CERRADA",
                                        "CAJA NO CERRADA",
                                        "CAJA NO CERRADA",
                                        "CAJA NO CERRADA",
                                        "CAJA NO CERRADA",
                                        dataCaja.get(i).getObservacion()
                 };    
             }
             else
             {*/
                 Object[] nuevaFila = {  i+1,                          
                                        fecha_apertura,
                                        fecha_cierre,
                                        dataCaja.get(i).getNombre(),
                                        "$ " + df1.format(dataCaja.get(i).getValorApertura()), 
                                        "$ " + df1.format(dataCaja.get(i).getTotalFacturado()), 
                                        "$ " + df1.format(dataCaja.get(i).getIngresos()), 
                                        "$ " + df1.format(dataCaja.get(i).getRecibosPago()), 
                                        "$ " + df1.format(dataCaja.get(i).getAbonos()), 
                                        "$ " + df1.format(dataCaja.get(i).getValorPagosFactura()), 
                                        "$ " + df1.format(dataCaja.get(i).getEgresos()), 
                                        "$ " + df1.format(dataCaja.get(i).getValorApertura() + 
                                                dataCaja.get(i).getTotalFacturado() + 
                                                dataCaja.get(i).getIngresos() + 
                                                dataCaja.get(i).getRecibosPago() +
                                                dataCaja.get(i).getAbonos() +
                                                dataCaja.get(i).getValorPagosFactura() -
                                                dataCaja.get(i).getEgresos()), 
                                        "$ " + df1.format(dataCaja.get(i).getValorContado()),
                                        "$ " + df1.format(dataCaja.get(i).getDiferencia()),
                                        dataCaja.get(i).getObservacion(),
                                        dataCaja.get(i).getIdCajaOperacion(),
                                        "IMPRIMIR"
                 };    
             //}

             totalEfectivo = totalEfectivo + dataCaja.get(i).getValorContado();             
             dtmData.addRow(nuevaFila); 
        }         
        txtTotal.setText("" + (objUtils.redondear(totalEfectivo)));     

    }
     
    public void buscar_informacion()
    {
        objUtils.vaciarTabla(dtmData);        

        String fechaInicio = "";
        String fechaFin = "";
        
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        
        if(this.chkFechaRegistro.isSelected())
        {
            Date date1 = txtFechaInicio.getDate();
            fechaInicio = df1.format(date1);

            Date date2 = txtFechaFin.getDate();
            fechaFin = df1.format(date2);

            ArrayList<clsCaja> dataCaja = objCaja.consultarDataOperacionesCajaFecha(fechaInicio, fechaFin); 
            if(dataCaja.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "No existen datos", "Atención!", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                llenarData(dataCaja);
            }
        }       
        else
        {
            ArrayList<clsCaja> dataCaja = objCaja.consultarDataOperacionesCaja();
            if(dataCaja.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "No existen datos", "Atención!", JOptionPane.WARNING_MESSAGE);
            }
            else
            {                
                llenarData(dataCaja);    
            }             
        }
    }  
    
private void chkFechaRegistroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkFechaRegistroItemStateChanged
    if(this.chkFechaRegistro.isSelected())
    {
        txtFechaInicio.setEnabled(true);  
        txtFechaFin.setEnabled(true);         
    }
    else
    {
        txtFechaInicio.setEnabled(false);  
        txtFechaFin.setEnabled(false);        
    } 
}//GEN-LAST:event_chkFechaRegistroItemStateChanged

    public class MiModelo extends DefaultTableModel
    {
            @Override
       public boolean isCellEditable (int row, int column)
       {
           // Aquí devolvemos true o false según queramos que una celda
           // identificada por fila,columna (row,column), sea o no editable
          /* if (column == 3)
              return true;*/
           
           return false;
       }
    } 

private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
   
}//GEN-LAST:event_btnImprimirActionPerformed

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        int fila = tblData.rowAtPoint(evt.getPoint());
        int columna = tblData.columnAtPoint(evt.getPoint());
        
        int z = tblData.getSelectedRow();
        int idCajaAbierta = Integer.parseInt(""+tblData.getValueAt(z, 15));
        if (columna == 16)
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
            try
            {
                ArrayList<clsCaja> dataCaja = objCaja.consultarDataOperacionesCajaID(idCajaAbierta); 
                String fecha = dataCaja.get(0).getFechaCierre();
                fichero = new FileWriter(objUtils.HostSystem + "file00001.txt");
                pw = new PrintWriter(fichero);
                /*40COLUMNAS*/
                //pw.println(annio + "/" + mes + "/" + dia + " " +hora + ":" + minutos + ":" + segundos);
                pw.println(fecha.substring(0, 16) + "(AAAA-MM-DD HH:MM)");
                
                
                double valor_apertura = dataCaja.get(0).getValorApertura();
                double valor_contado = dataCaja.get(0).getValorContado();
                
                pw.println("VALOR APERTURA: " + valor_apertura);

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
                    pw.println("----------------------------------------");
                    for(int i=0;i<maxData;i++)
                    {
                         factReferencia = dataFacturas.get(i).getFactReferencia() + "                                         "; 
                         pw.println((i+1) + " " + factReferencia.substring(0, 28) + " " + 
                                        objUtils.rellenar(""+df1.format(dataFacturas.get(i).getEfectivo())));
                         totalFacturas = totalFacturas + dataFacturas.get(i).getEfectivo();                 
                    } 
                    if(maxDevoluciones>0)
                    {    
                        for(int i=0;i<maxDevoluciones;i++)
                        {
                             factReferencia = dataDevoluciones.get(i).getIdCabeceraMovi() + "                                         "; 
                             pw.println((i+1) + " DEVOLUCION:" + factReferencia.substring(0, 16) + " -" + 
                                            objUtils.rellenar(""+df1.format(dataDevoluciones.get(i).getEfectivo())));
                             totalDevoluciones = totalDevoluciones + dataDevoluciones.get(i).getEfectivo();                 
                        } 
                        /*pw.println("TOTAL FACTURADO: " + objUtils.redondear(totalFacturas));
                        pw.println(" ");*/
                    }
                    totalFacturas = totalFacturas - totalDevoluciones;
                    pw.println("TOTAL FACTURADO: " + objUtils.redondear(totalFacturas));
                    pw.println(" ");
                }

                //PAGOS        
                ArrayList<clsPago> dataPagos = objPago.consultaPagosRealizadas(idCajaAbierta); 
                maxData = dataPagos.size();
                String referencia = "";
                if(maxData>0)
                { 
                    //pw.println("PAGOS");
                    pw.println("ABONOS");
                    pw.println("----------------------------------------");
                    for(int i=0; i<maxData;i++)
                    {
                        //referencia = dataPagos.get(i).getReferencia()+ "                                         "; 
                        referencia = dataPagos.get(i).getReferencia()+ "                                         "; 
                        pw.println((i+1) +  " " + referencia.substring(0, 28) + " " + 
                                        objUtils.rellenar(""+df1.format(dataPagos.get(i).getValor())));                                  
                        totalPagos = totalPagos + dataPagos.get(i).getValor();                
                    } 
                    pw.println("TOTAL ABONOS: " + objUtils.redondear(totalPagos));
                    pw.println(" ");
                }

                //PAGOS   FACTURA     
                ArrayList<clsPago> dataPagosFactura = objPago.consultaPagosFacturaRealizadas(idCajaAbierta); 
                maxData = dataPagosFactura.size();
                //String referencia = "";
                if(maxData>0)
                { 
                    pw.println("ABONOS/FACTURA");
                    pw.println("----------------------------------------");
                    for(int i=0; i<maxData;i++)
                    {
                        referencia = dataPagosFactura.get(i).getNombreCliente()+ "                                         "; 
                        pw.println((i+1) +  " " + referencia.substring(0, 28) + " " + 
                                        objUtils.rellenar(""+df1.format(dataPagosFactura.get(i).getValor())));                                  
                         totalPagosFactura = totalPagosFactura + dataPagosFactura.get(i).getValor();                
                    } 
                    pw.println("TOTAL ABONOS/FACT: " + objUtils.redondear(totalPagosFactura));
                    pw.println(" ");
                }

                //PAGOS RECIBO     
                ArrayList<clsPago> dataPagosRecibo = objPago.consultaPagosRecibo(idCajaAbierta); 
                maxData = dataPagosRecibo.size();
                //String referencia = "";
                if(maxData>0)
                { 
                    pw.println("ABONOS/ENTRADA (RECIBO)");
                    pw.println("----------------------------------------");
                    for(int i=0; i<maxData;i++)
                    {
                        referencia = dataPagosRecibo.get(i).getNombreCliente()+ "                                         "; 
                        pw.println((i+1) +  " " + referencia.substring(0, 28) + " " + 
                                        objUtils.rellenar(""+df1.format(dataPagosRecibo.get(i).getValor())));                                  
                         totalPagosRecibo = totalPagosRecibo + dataPagosRecibo.get(i).getValor();                
                    } 
                    pw.println("TOTAL ABONOS/ENTRADA: " + objUtils.redondear(totalPagosRecibo));
                    pw.println(" ");
                }

                //INGRESOS
                ArrayList<clsEgreso> dataIngresos = objEgreso.consultaEgresosRealizadas(idCajaAbierta, "I"); 
                maxData = dataIngresos.size();
                String concepto = "";
                if(maxData>0)
                { 
                    pw.println("INGRESOS");
                    pw.println("----------------------------------------");
                    for(int i=0;i<maxData;i++)
                    {                
                        concepto = dataIngresos.get(i).getConcepto() + "                                         "; 
                        pw.println((i+1) +  " " + concepto.substring(0, 28) + " " + 
                                        objUtils.rellenar(""+df1.format(dataIngresos.get(i).getCantidadEgreso())));
                        totalIngresos = totalIngresos + dataIngresos.get(i).getCantidadEgreso();                
                    }
                    pw.println("TOTAL INGRESOS: " + objUtils.redondear(totalIngresos));
                    pw.println(" ");
                }

                //EGRESOS
                ArrayList<clsEgreso> dataEgresos = objEgreso.consultaEgresosRealizadas(idCajaAbierta, "E"); 
                maxData = dataEgresos.size();
                concepto = "";
                if(maxData>0)
                { 
                    pw.println("EGRESOS");
                    pw.println("----------------------------------------");
                    for(int i=0;i<maxData;i++)
                    {                
                        concepto = dataEgresos.get(i).getConcepto() + "                                         "; 
                        pw.println((i+1) +  " " + concepto.substring(0, 28) + " " + 
                                        objUtils.rellenar(""+df1.format(dataEgresos.get(i).getCantidadEgreso())));
                        totalEgresos = totalEgresos + dataEgresos.get(i).getCantidadEgreso();                
                    }
                    pw.println("TOTAL EGRESOS: " + objUtils.redondear(totalEgresos));
                    pw.println(" ");
                }

                Double totalCierre = valor_apertura + 
                        totalFacturas + totalPagos + totalPagosFactura + totalPagosRecibo + totalIngresos - totalEgresos;
                pw.println("TOTAL:                          $"+ objUtils.rellenar(""+objUtils.redondear(totalCierre)));
                pw.println("CONTADO EN DINERO:              $"+ objUtils.rellenar("" + valor_contado));

                double diferencia = objUtils.redondear(Double.parseDouble("" + valor_contado) - totalCierre);
                String mensajeEmail =""; 
                if(diferencia == 0.0||diferencia ==-0.0)
                {        
                    pw.println("VALORES CUADRADOS");
                    mensajeEmail = "VALORES CUADRADOS";
                }
                else if(diferencia>0)
                {        
                    pw.println("SOBRANTE:                       $" + objUtils.rellenar(""+diferencia));
                    mensajeEmail = "SOBRANTE: $" + objUtils.rellenar(""+diferencia);
                }
                else if(diferencia<0)
                {       
                    pw.println("FALTANTE:                       $" + objUtils.rellenar(""+diferencia));
                    mensajeEmail = "FALTANTE: $" + objUtils.rellenar(""+diferencia);
                }       

                Runtime aplicacion = Runtime.getRuntime(); 
                try{
                    //IMPRIMIR 2 VECES
                    for(int x=0; x<1; x++)
                        aplicacion.exec("cmd.exe /K "+ objUtils.HostSystem + "printFile.bat"); 
                    JOptionPane.showMessageDialog(this, "Impresión realizada con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error al imprimir", JOptionPane.ERROR_MESSAGE);
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
            
        }
        
    }//GEN-LAST:event_tblDataMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JCheckBox chkFechaRegistro;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblData;
    private com.toedter.calendar.JDateChooser txtFechaFin;
    private com.toedter.calendar.JDateChooser txtFechaInicio;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
