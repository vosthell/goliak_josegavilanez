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
import clases.clsComboBox;
import clases.clsDetalle;
import clases.clsGrupo;
import clases.clsUtils;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import pos.frmInventarioView;
import stinventario.frmPrincipal;

/**
 *
 * @author CKaiser
 */
public class frmListInventarioSecciones extends javax.swing.JInternalFrame {
    clsUtils objUtils = new clsUtils();
    clsDetalle objDetalle = new clsDetalle();
    MiModelo dtmData = new MiModelo();
    clsGrupo objGrupo = new clsGrupo();
    /** Creates new form frmReporteVentas */
    public frmListInventarioSecciones() {
        initComponents();
        
        dtmData.addColumn("N°");/*.setPreferredWidth(500)*/        
        dtmData.addColumn("ID_GRUPO");
        dtmData.addColumn("GRUPO");
        dtmData.addColumn("ITEMS");
        dtmData.addColumn("STOCK SISTEMA");
        dtmData.addColumn("STOCK CONTADO");
        dtmData.addColumn("DIFERENCIA UNIDADES");
        dtmData.addColumn("DIFERENCIA DINERO");
                
        Date fechaActual = new Date();
        txtFechaInicio.setDate(fechaActual);
        txtFechaFin.setDate(fechaActual); 
        
        //ALINEAR COLUMNA
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);       
        
        tblData.getColumnModel().getColumn(0).setPreferredWidth(8);  
        tblData.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tblData.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tblData.getColumnModel().getColumn(6).setCellRenderer(tcr);
        tblData.getColumnModel().getColumn(7).setCellRenderer(tcr);
        //tblData.getColumnModel().getColumn(8).setCellRenderer(tcr);
        
        //objUtils.setOcultarColumnasJTable(this.tblData, new int[]{6});
        
        tblData.setAutoCreateRowSorter(true); 
        
        //CARGAR GRUPOS
        /*ArrayList<clsComboBox> dataGrupo = objGrupo.consultarGrupos();        
        for(int i=0;i<dataGrupo.size();i=i+1)
        {
            clsComboBox oItem = new clsComboBox(dataGrupo.get(i).getCodigo(), dataGrupo.get(i).getDescripcion());
            cmbGrupo.addItem(oItem);            
        }*/
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
        chkFechaRegistro = new javax.swing.JCheckBox();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmListInventarioSecciones.class);
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

        chkFechaRegistro.setText(resourceMap.getString("chkFechaRegistro.text")); // NOI18N
        chkFechaRegistro.setName("chkFechaRegistro"); // NOI18N
        chkFechaRegistro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkFechaRegistroItemStateChanged(evt);
            }
        });

        btnBuscar.setText(resourceMap.getString("btnBuscar.text")); // NOI18N
        btnBuscar.setName("btnBuscar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkFechaRegistro)
                        .addComponent(jLabel2))
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnBuscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtTotal.setFont(resourceMap.getFont("txtTotal.font")); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText(resourceMap.getString("txtTotal.text")); // NOI18N
        txtTotal.setName("txtTotal"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
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

    public void llenarData(ArrayList<clsDetalle> dataCabecera)
    {
        //Double total = 0.00;
        Double totalEfectivo = 0.00;
        //Double totalSaldo = 0.00;        
        //DecimalFormat df1 = new DecimalFormat("##0.00"); 
        NumberFormat nf =  NumberFormat.getCurrencyInstance(Locale.US);
        int maxData = dataCabecera.size();        
        
        for(int i=0; i<maxData; i++)
        {                    
            Object[] nuevaFila = {  i+1,        
                                    dataCabecera.get(i).getIdProducto(),
                                    dataCabecera.get(i).getDescripcionProducto(),
                                    dataCabecera.get(i).getIdContador(),                              
                                    dataCabecera.get(i).getStockSistema(),
                                    dataCabecera.get(i).getStockContado(),
                                    dataCabecera.get(i).getIVA(),
                                    dataCabecera.get(i).getCosto()   
             };       
             //total = total + dataCabecera.get(i).getTotal();
             totalEfectivo = totalEfectivo + dataCabecera.get(i).getCosto();
             //totalSaldo = totalSaldo + dataCabecera.get(i).getSaldo();
             dtmData.addRow(nuevaFila); 
        } 
        //txtEfectivo.setText(""+objUtils.redondear(totalEfectivo));
        //txtCredito.setText(""+objUtils.redondear(totalSaldo));
        txtTotal.setText("" + (objUtils.redondear(totalEfectivo)));
        //tblData.setDefaultRenderer (Object.class, new colorFilaTable());
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

            ArrayList<clsDetalle> dataCompras = objDetalle.consultaInvGruposFecha(fechaInicio, fechaFin); 
            llenarData(dataCompras);
        }       
        else
        {
            ArrayList<clsDetalle> dataCompras = objDetalle.consultaInvGrupos(); 
            llenarData(dataCompras);    
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
            
        /*@Override
       public Class<?> getColumnClass(int colNum) {
                switch (colNum) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Double.class;
                    case 3:
                        return Double.class;
                    case 4:
                        return Double.class;
                    case 5:
                        return Integer.class;
                   
                    default:
                        return String.class;
                }
            } */
    } 

private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
    int fila = tblData.rowAtPoint(evt.getPoint());
    int columna = tblData.columnAtPoint(evt.getPoint());
     
    String idGrupo = "" + tblData.getValueAt(fila,1);
    String nombreGrupo = "" + tblData.getValueAt(fila,2);
    String fecha = "";
    String fechaInicio = "";
    String fechaFin = "";

    DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");   
     
     if(this.chkFechaRegistro.isSelected())
     {
        fecha = "SI";
        Date date1 = txtFechaInicio.getDate();
        fechaInicio = df1.format(date1);

        Date date2 = txtFechaFin.getDate();
        fechaFin = df1.format(date2);
     }
     else
     {
         fecha = "NO";
     }
     
    frmListInvSeccionDetalle formulario = new frmListInvSeccionDetalle(idGrupo, nombreGrupo, fecha, fechaInicio, fechaFin);
    mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_tblDataMouseClicked

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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JCheckBox chkFechaRegistro;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblData;
    private com.toedter.calendar.JDateChooser txtFechaFin;
    private com.toedter.calendar.JDateChooser txtFechaInicio;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}