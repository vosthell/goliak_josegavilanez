/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmPagosRealizados.java
 *
 * Created on 05-mar-2012, 9:46:37
 */
package producto;

import pos.*;
import clases.clsCaja;
import clases.clsCompras;
import clases.clsPago;
import clases.clsPermisos;
import clases.clsProveedor;
import clases.clsReporte;
import clases.clsUtils;
import com.jidesoft.hints.ListDataIntelliHints;
import com.jidesoft.swing.SelectAllUtils;
import index.main;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import stinventario.frmPrincipal;

/**
 *
 * @author CKaiser
 */
public class frmListCompras extends javax.swing.JInternalFrame {
    MiModelo dtmData = new MiModelo();
    
    clsReporte objReporte = new clsReporte(); 
    clsCaja objCaja = new clsCaja();
    clsPago objPago = new clsPago();
    clsUtils objUtils = new clsUtils();
    clsCompras objCompras = new clsCompras();
    clsProveedor objProveedor = new clsProveedor();
    clsPermisos objPermisos = new clsPermisos();
    String idCajaAbierta = "";
    
    /** Creates new form frmPagosRealizados */
    public frmListCompras() {
        initComponents();        
        
        dtmData.addColumn("id");
        dtmData.addColumn("N°");/*.setPreferredWidth(500)*/
        dtmData.addColumn("Proveedor");
        dtmData.addColumn("Total");
        dtmData.addColumn("ESTADO");
        dtmData.addColumn("MODIFICAR");
        dtmData.addColumn("RECIBIR");
        dtmData.addColumn("FECHA CREACION");
        dtmData.addColumn("FECHA RECIBE");
        dtmData.addColumn("ID");
        //dtmData.addColumn("Valor");
        //dtmData.addColumn("idCabeceraMovi");
        //ALINEAR COLUMNA
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblData.getColumnModel().getColumn(3).setCellRenderer(tcr);
        
        //OCULTAR
        objUtils.setOcultarColumnasJTable(this.tblData, new int[]{0});
        
        //idCajaAbierta
        //ArrayList<clases.clsCaja> dataCaja = objCaja.consultarDataCajaAbierta(main.idUser);
        //idCajaAbierta = dataCaja.get(0).getIdCajaOperacion(); 
        
        ArrayList<clsCompras> dataCompras = objCompras.consultaDataCompras(); 
        llenarData(dataCompras);
        
        Date fechaActual = new Date();
        txtFechaInicio.setDate(fechaActual);
        txtFechaFin.setDate(fechaActual);    
        txtFechaInicioRecibe.setDate(fechaActual);
        txtFechaFinRecibe.setDate(fechaActual); 
        
        tblData.setAutoCreateRowSorter(true);  
        
        List<String> dataCodigo = objProveedor.consultarCodigos(); 
        SelectAllUtils.install(txtCodigoProveedor);
        ListDataIntelliHints intellihints = new ListDataIntelliHints(txtCodigoProveedor, dataCodigo);        
        intellihints.setCaseSensitive(false);
    }
    
    public void llenarData(ArrayList<clsCompras> dataCompras)
    {
        Double total = 0.00;
        
        DecimalFormat df1 = new DecimalFormat("##0.00"); 
        int maxData = dataCompras.size();
        String etiqueta="";
        String etiqueta2="";
        String etiqueta3="";
        String fecha_realizada = "";
        String fecha_recibida = "";
        for(int i=0; i<maxData; i++)
        {
            if(dataCompras.get(i).getEstadoTramite().equals("S"))
            {    etiqueta = "RECIBIDA";
                etiqueta2 = "";
                etiqueta3 = "";
            }else
            {    etiqueta = "SIN RECIBIR";
                 etiqueta2 = "MODIFICAR";
                 etiqueta3 = "RECIBIR";
            }
            fecha_realizada = dataCompras.get(i).getFecha().substring(1, 16);
            fecha_recibida = dataCompras.get(i).getFechaRecibe();            
            Object[] nuevaFila = {dataCompras.get(i).getIdCompras(),
                                    i+1,                                     
                                    dataCompras.get(i).getNombreProveedor(),
                                    df1.format(dataCompras.get(i).getTotal()),
                                    etiqueta,
                                    etiqueta2,
                                    etiqueta3,
                                    fecha_realizada,
                                    fecha_recibida,
                                    dataCompras.get(i).getIdCompras()
             };       
             total = total + dataCompras.get(i).getTotal();
             dtmData.addRow(nuevaFila); 
        } 
        txtTotal.setText(""+objUtils.redondear(total));
        
        tblData.setDefaultRenderer (Object.class, new colorFilaTable());
    }
    
    public class colorFilaTable extends DefaultTableCellRenderer { 
        @Override 
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column ) 
        { 
            setEnabled(table == null || table.isEnabled());        

            if(String.valueOf(table.getValueAt(row, 4)).equals("RECIBIDA")) 
                setBackground(Color.green); 
            else if(String.valueOf(table.getValueAt(row, 4)).equals("SIN RECIBIR")) 
                setBackground(Color.RED); 
            else setBackground(null); 

            super.getTableCellRendererComponent(table, value, selected, focused, row, column); 
            return this; 
        } 
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
        chkFechaRecibe = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        txtFechaInicioRecibe = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtFechaFinRecibe = new com.toedter.calendar.JDateChooser();
        chkProveedor = new javax.swing.JCheckBox();
        txtCodigoProveedor = new javax.swing.JTextField();
        btnBuscarProveedor = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmListCompras.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(802, 523));

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

        btnBuscar.setIcon(resourceMap.getIcon("btnBuscar.icon")); // NOI18N
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

        chkFechaRecibe.setText(resourceMap.getString("chkFechaRecibe.text")); // NOI18N
        chkFechaRecibe.setName("chkFechaRecibe"); // NOI18N
        chkFechaRecibe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkFechaRecibeItemStateChanged(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtFechaInicioRecibe.setDateFormatString(resourceMap.getString("txtFechaInicioRecibe.dateFormatString")); // NOI18N
        txtFechaInicioRecibe.setEnabled(false);
        txtFechaInicioRecibe.setName("txtFechaInicioRecibe"); // NOI18N
        txtFechaInicioRecibe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtFechaInicioRecibePropertyChange(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtFechaFinRecibe.setDateFormatString(resourceMap.getString("txtFechaFinRecibe.dateFormatString")); // NOI18N
        txtFechaFinRecibe.setEnabled(false);
        txtFechaFinRecibe.setName("txtFechaFinRecibe"); // NOI18N
        txtFechaFinRecibe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtFechaFinRecibePropertyChange(evt);
            }
        });

        chkProveedor.setText(resourceMap.getString("chkProveedor.text")); // NOI18N
        chkProveedor.setName("chkProveedor"); // NOI18N
        chkProveedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkProveedorItemStateChanged(evt);
            }
        });

        txtCodigoProveedor.setText(resourceMap.getString("txtCodigoProveedor.text")); // NOI18N
        txtCodigoProveedor.setEnabled(false);
        txtCodigoProveedor.setName("txtCodigoProveedor"); // NOI18N

        btnBuscarProveedor.setText(resourceMap.getString("btnBuscarProveedor.text")); // NOI18N
        btnBuscarProveedor.setEnabled(false);
        btnBuscarProveedor.setName("btnBuscarProveedor"); // NOI18N
        btnBuscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkFechaRegistro)
                    .addComponent(chkFechaRecibe)
                    .addComponent(chkProveedor))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBuscarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCodigoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtFechaInicioRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaFinRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addGap(152, 152, 152))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chkFechaRegistro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkFechaRecibe))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFechaInicioRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaFinRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkProveedor)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnBuscarProveedor)
                                .addComponent(txtCodigoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(6, 6, 6))
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

        txtTotal.setFont(resourceMap.getFont("txtTotal.font")); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText(resourceMap.getString("txtTotal.text")); // NOI18N
        txtTotal.setName("txtTotal"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 482, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(570, 570, 570))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
    int fila = tblData.rowAtPoint(evt.getPoint());
    int columna = tblData.columnAtPoint(evt.getPoint());
    /*int columna = 3;*/
    /*if ((fila > -1) && (columna > -1))*/
    int i = tblData.getSelectedRow();
    int idCabecera = Integer.parseInt(""+tblData.getValueAt(i, 0));
    System.out.println("x:" + idCabecera + " " + fila + " " + columna);
    //objReporte.ejecutarReporteParametroInt(idCabecera, "rptPagosFactura"); 
    /*frmComprasShow ventana = new frmComprasShow(null, true, idCabecera);
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);*/
    if(tblData.getValueAt(i, 4).equals("RECIBIDA"))
    {   
        frmComprasShow formulario = new frmComprasShow(idCabecera);
        mostrarJInternalCentrado(formulario);  
    }
    else
    {
        if (columna == 6)
        {
            boolean permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmComprasRecibir");
            if(permitido)
            {           
                frmComprasRecibir formulario = new frmComprasRecibir(idCabecera);
                mostrarJInternalCentrado(formulario);                 
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No tiene permisos para recibir compras", "Atención!", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        }
        
        if (columna == 5)
        {
            frmComprasModificar formulario = new frmComprasModificar(idCabecera);
            mostrarJInternalCentrado(formulario);
            dispose();
        }
    }
}//GEN-LAST:event_tblDataMouseClicked

private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    buscar_informacion();
    
}//GEN-LAST:event_btnBuscarActionPerformed

    public void buscar_informacion()
    {
        objUtils.vaciarTabla(dtmData);
        txtTotal.setText("");

        String fechaInicio = "";
        String fechaFin = "";
        String soloProveedor = "";
        
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        
        if(this.chkProveedor.isSelected())
        {
            soloProveedor = "S";
        }
        else
        {
            soloProveedor = "N";
        }

        if(this.chkFechaRegistro.isSelected())
        {
            Date date1 = txtFechaInicio.getDate();
            fechaInicio = df1.format(date1);

            Date date2 = txtFechaFin.getDate();
            fechaFin = df1.format(date2);

            ArrayList<clsCompras> dataCompras = objCompras.consultaDataComprasRangoRegistro(fechaInicio, fechaFin, soloProveedor, txtCodigoProveedor.getText()); 
            llenarData(dataCompras);
        }
        else if(this.chkFechaRecibe.isSelected())
        {
            Date date1 = txtFechaInicioRecibe.getDate();
            fechaInicio = df1.format(date1);

            Date date2 = txtFechaFinRecibe.getDate();
            fechaFin = df1.format(date2);

            ArrayList<clsCompras> dataCompras = objCompras.consultaDataComprasRangoRecibe(fechaInicio, fechaFin, soloProveedor, txtCodigoProveedor.getText()); 
            llenarData(dataCompras);
        }
        else
        {
            ArrayList<clsCompras> dataCompras = objCompras.consultaDataComprasProv(soloProveedor, txtCodigoProveedor.getText()); 
            llenarData(dataCompras);    
        }
    }        
private void chkFechaRegistroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkFechaRegistroItemStateChanged
    if(this.chkFechaRegistro.isSelected())
    {
        txtFechaInicio.setEnabled(true);  
        txtFechaFin.setEnabled(true); 
        chkFechaRecibe.setEnabled(false);
    }
    else
    {
        txtFechaInicio.setEnabled(false);  
        txtFechaFin.setEnabled(false);
        chkFechaRecibe.setEnabled(true);
    } 
}//GEN-LAST:event_chkFechaRegistroItemStateChanged

private void chkFechaRecibeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkFechaRecibeItemStateChanged
    if(this.chkFechaRecibe.isSelected())
    {
        txtFechaInicioRecibe.setEnabled(true);  
        txtFechaFinRecibe.setEnabled(true); 
        chkFechaRegistro.setEnabled(false);
    }
    else
    {
        txtFechaInicioRecibe.setEnabled(false);  
        txtFechaFinRecibe.setEnabled(false); 
        chkFechaRegistro.setEnabled(true);
    } 
}//GEN-LAST:event_chkFechaRecibeItemStateChanged

private void txtFechaInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFechaInicioPropertyChange
    //buscar_informacion();
}//GEN-LAST:event_txtFechaInicioPropertyChange

private void txtFechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFechaFinPropertyChange
    //buscar_informacion();
}//GEN-LAST:event_txtFechaFinPropertyChange

private void txtFechaInicioRecibePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFechaInicioRecibePropertyChange
    //buscar_informacion();
}//GEN-LAST:event_txtFechaInicioRecibePropertyChange

private void txtFechaFinRecibePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFechaFinRecibePropertyChange
    //buscar_informacion();
}//GEN-LAST:event_txtFechaFinRecibePropertyChange

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      //String fechaInicio = "";
    //String fechaFin = "";
    //DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    
    if(this.chkFechaRegistro.isSelected())
    {
        Date date1 = txtFechaInicio.getDate();
        //fechaInicio = df1.format(date1);

        Date date2 = txtFechaFin.getDate();
        //fechaFin = df1.format(date2);

        /*ArrayList<clsCompras> dataCompras = objCompras.consultaDataComprasRangoRegistro(fechaInicio, fechaFin); 
        llenarData(dataCompras);*/
        objReporte.ejecutarReporte2ParametrosDate(date1, date2, "rptComprasTotalesFechaCompra");
    }
    else if(this.chkFechaRecibe.isSelected())
    {
        Date date1 = txtFechaInicioRecibe.getDate();
        //fechaInicio = df1.format(date1);

        Date date2 = txtFechaFinRecibe.getDate();
        //fechaFin = df1.format(date2);

        /*ArrayList<clsCompras> dataCompras = objCompras.consultaDataComprasRangoRecibe(fechaInicio, fechaFin); 
        llenarData(dataCompras);*/
        objReporte.ejecutarReporte2ParametrosDate(date1, date2, "rptComprasTotalesFechaRecibe");
    }
    else
    {
        objReporte.ejecutarReporte("rptComprasTotales"); 
        /*ArrayList<clsCompras> dataCompras = objCompras.consultaDataCompras(); 
        llenarData(dataCompras); */   
    }
}//GEN-LAST:event_jButton1ActionPerformed

private void btnBuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProveedorActionPerformed
    frmListProveedor ventana = new frmListProveedor(null, true, "5");
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
}//GEN-LAST:event_btnBuscarProveedorActionPerformed

private void chkProveedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkProveedorItemStateChanged
    txtCodigoProveedor.setText("");
    if(this.chkProveedor.isSelected())
    {
        txtCodigoProveedor.setEnabled(true);
        btnBuscarProveedor.setEnabled(true);
    }
    else
    {
        txtCodigoProveedor.setEnabled(false);
        btnBuscarProveedor.setEnabled(false);
    }
    
}//GEN-LAST:event_chkProveedorItemStateChanged
    public static void mostrarJInternalCentrado(javax.swing.JInternalFrame formulario)
    {
        Dimension desktopSize = frmPrincipal.jDesktopPane1.getSize();
        Dimension jInternalFrameSize = formulario.getSize();
        formulario.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
        (desktopSize.height- jInternalFrameSize.height)/2);

        frmPrincipal.jDesktopPane1.add(formulario);
        formulario.show(); 
    }
    
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarProveedor;
    private javax.swing.JCheckBox chkFechaRecibe;
    private javax.swing.JCheckBox chkFechaRegistro;
    private javax.swing.JCheckBox chkProveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblData;
    public static javax.swing.JTextField txtCodigoProveedor;
    private com.toedter.calendar.JDateChooser txtFechaFin;
    private com.toedter.calendar.JDateChooser txtFechaFinRecibe;
    private com.toedter.calendar.JDateChooser txtFechaInicio;
    private com.toedter.calendar.JDateChooser txtFechaInicioRecibe;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
