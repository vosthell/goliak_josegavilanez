/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmProductos.java
 *
 * Created on 22-oct-2011, 21:44:57
 */
package producto;

import clases.clsAuditoria;
import clases.clsComboBox;
import clases.clsGrupo;
import clases.clsParametros;
import clases.clsPrecio;
import clases.clsProducto;
import clases.clsUtils;
import com.jidesoft.hints.ListDataIntelliHints;
import com.jidesoft.swing.SelectAllUtils;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXImagePanel.Style;
import pos.frmListProductos;

/**
 *
 * @author Kaiser
 */
public class frmProductosDel extends javax.swing.JInternalFrame {
    clsUtils objUtils = new clsUtils();
    clsProducto objProducto = new clsProducto();
    clsPrecio objPrecio = new clsPrecio();
    clsAuditoria objAuditoria = new clsAuditoria();
    clsGrupo objGrupo = new clsGrupo();
    clsParametros objParametros = new clsParametros();
        
    String x_ruta = objParametros.consultaRutaImagenes();
    MiModelo dtmData = new MiModelo();
    String imgBlanco = objParametros.consultaImgBlanco();     
    boolean exito;
    int codigoProducto =0;
    /** Creates new form frmProductos */
    public frmProductosDel() {
        initComponents();
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = new Date();
        txtFechaCaducidad.setDate(fechaActual);
                
        //CARGAR AUTOCOMPLETAR
        List<String> dataCodigo = objProducto.consultarCodigos();         
        SelectAllUtils.install(txtCodigo);
        ListDataIntelliHints intellihints = new ListDataIntelliHints(txtCodigo, dataCodigo);        
        intellihints.setCaseSensitive(false);
        
        dtmData.addColumn("Nº");
        dtmData.addColumn("Costo ($)");
        dtmData.addColumn("Utilidad (%)");
        dtmData.addColumn("Precio ($)");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCantidadInicial = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cmbGrupo = new javax.swing.JComboBox();
        btnGuardarCambios = new javax.swing.JButton();
        jideTabbedPane1 = new com.jidesoft.swing.JideTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDataPrecios = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        chkCantidadMinima = new javax.swing.JCheckBox();
        txtCantidadMinima = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rbtnSinExistencia = new javax.swing.JRadioButton();
        rbtnConExistencia = new javax.swing.JRadioButton();
        chkPerecible = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        txtFechaCaducidad = new com.toedter.calendar.JDateChooser();
        lblFechaCaducidad = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        imagepanel = new org.jdesktop.swingx.JXImagePanel();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmProductosDel.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtCodigo.setBackground(resourceMap.getColor("txtCodigo.background")); // NOI18N
        txtCodigo.setText(resourceMap.getString("txtCodigo.text")); // NOI18N
        txtCodigo.setName("txtCodigo"); // NOI18N
        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtDescripcion.setEditable(false);
        txtDescripcion.setText(resourceMap.getString("txtDescripcion.text")); // NOI18N
        txtDescripcion.setName("txtDescripcion"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtCosto.setBackground(resourceMap.getColor("txtCosto.background")); // NOI18N
        txtCosto.setText(resourceMap.getString("txtCosto.text")); // NOI18N
        txtCosto.setName("txtCosto"); // NOI18N
        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoKeyReleased(evt);
            }
        });

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtCantidadInicial.setEditable(false);
        txtCantidadInicial.setText(resourceMap.getString("txtCantidadInicial.text")); // NOI18N
        txtCantidadInicial.setName("txtCantidadInicial"); // NOI18N
        txtCantidadInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadInicialKeyReleased(evt);
            }
        });

        btnBuscar.setIcon(resourceMap.getIcon("btnBuscar.icon")); // NOI18N
        btnBuscar.setText(resourceMap.getString("btnBuscar.text")); // NOI18N
        btnBuscar.setName("btnBuscar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        cmbGrupo.setName("cmbGrupo"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar))
                    .addComponent(txtDescripcion)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCantidadInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCantidadInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cmbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnGuardarCambios.setIcon(resourceMap.getIcon("btnGuardarCambios.icon")); // NOI18N
        btnGuardarCambios.setText(resourceMap.getString("btnGuardarCambios.text")); // NOI18N
        btnGuardarCambios.setName("btnGuardarCambios"); // NOI18N
        btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosActionPerformed(evt);
            }
        });

        jideTabbedPane1.setName("jideTabbedPane1"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel8.setName("jLabel8"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblDataPrecios.setModel(dtmData);
        tblDataPrecios.setName("tblDataPrecios"); // NOI18N
        tblDataPrecios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblDataPreciosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblDataPrecios);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jideTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        chkCantidadMinima.setText(resourceMap.getString("chkCantidadMinima.text")); // NOI18N
        chkCantidadMinima.setName("chkCantidadMinima"); // NOI18N
        chkCantidadMinima.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkCantidadMinimaItemStateChanged(evt);
            }
        });

        txtCantidadMinima.setEditable(false);
        txtCantidadMinima.setText(resourceMap.getString("txtCantidadMinima.text")); // NOI18N
        txtCantidadMinima.setName("txtCantidadMinima"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        buttonGroup1.add(rbtnSinExistencia);
        rbtnSinExistencia.setText(resourceMap.getString("rbtnSinExistencia.text")); // NOI18N
        rbtnSinExistencia.setName("rbtnSinExistencia"); // NOI18N

        buttonGroup1.add(rbtnConExistencia);
        rbtnConExistencia.setSelected(true);
        rbtnConExistencia.setText(resourceMap.getString("rbtnConExistencia.text")); // NOI18N
        rbtnConExistencia.setName("rbtnConExistencia"); // NOI18N

        chkPerecible.setText(resourceMap.getString("chkPerecible.text")); // NOI18N
        chkPerecible.setName("chkPerecible"); // NOI18N
        chkPerecible.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkPerecibleItemStateChanged(evt);
            }
        });
        chkPerecible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPerecibleActionPerformed(evt);
            }
        });

        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        txtFechaCaducidad.setDateFormatString(resourceMap.getString("txtFechaCaducidad.dateFormatString")); // NOI18N
        txtFechaCaducidad.setEnabled(false);
        txtFechaCaducidad.setName("txtFechaCaducidad"); // NOI18N

        lblFechaCaducidad.setText(resourceMap.getString("lblFechaCaducidad.text")); // NOI18N
        lblFechaCaducidad.setEnabled(false);
        lblFechaCaducidad.setName("lblFechaCaducidad"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkPerecible)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(lblFechaCaducidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnSinExistencia)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnConExistencia))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkCantidadMinima)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidadMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidadMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCantidadMinima))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(rbtnSinExistencia))
                    .addComponent(rbtnConExistencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblFechaCaducidad)
                        .addComponent(txtFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkPerecible)
                        .addComponent(jLabel13)))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jideTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N

        imagepanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        imagepanel.setName("imagepanel"); // NOI18N

        javax.swing.GroupLayout imagepanelLayout = new javax.swing.GroupLayout(imagepanel);
        imagepanel.setLayout(imagepanelLayout);
        imagepanelLayout.setHorizontalGroup(
            imagepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );
        imagepanelLayout.setVerticalGroup(
            imagepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(110, 110, 110)
                    .addComponent(imagepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(111, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(imagepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jideTabbedPane1.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jideTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(260, Short.MAX_VALUE)
                .addComponent(btnGuardarCambios)
                .addGap(211, 211, 211))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jideTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardarCambios)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyReleased
    String x = txtCosto.getText();
    if(!objUtils.isDouble(x)){
        txtCosto.setText("");
    }
    
   
}//GEN-LAST:event_txtCostoKeyReleased

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
    
    
    private void chkPerecibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPerecibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPerecibleActionPerformed

    private void chkPerecibleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkPerecibleItemStateChanged
        if(this.chkPerecible.isSelected())        
        {    
            this.lblFechaCaducidad.setEnabled(true);  
            this.txtFechaCaducidad.setEnabled(true);
        }
        else
        {
            this.lblFechaCaducidad.setEnabled(false);  
            this.txtFechaCaducidad.setEnabled(false);
        }
    }//GEN-LAST:event_chkPerecibleItemStateChanged

    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        exito = objProducto.eliminarProducto(codigoProducto);
        if (exito)
        {
            JOptionPane.showMessageDialog(this, "Datos eliminados con éxito", "Atención!", JOptionPane.INFORMATION_MESSAGE);                
            objAuditoria.insertarAuditoria("frmProductosDel", "ELIMINO PRODUCTO: "
                                    + txtCodigo.getText().toUpperCase().toString()
                                    + " - " + txtDescripcion.getText().toUpperCase().toString(), "3");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Error al ingresar la información", "Atención!", JOptionPane.ERROR_MESSAGE);
            objAuditoria.insertarAuditoria("frmProductosDel", "INTENTÓ ELIMINAR PRODUCTO: "
                                    + txtCodigo.getText().toUpperCase().toString()
                                    + " - " + txtDescripcion.getText().toUpperCase().toString(), "3");
        }
        dispose();   
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
      
    }//GEN-LAST:event_txtCodigoFocusLost

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
      
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void chkCantidadMinimaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkCantidadMinimaItemStateChanged
        if(this.chkCantidadMinima.isSelected())        
        {    
            this.txtCantidadMinima.setEditable(true);
        }
        else
        {
            this.txtCantidadMinima.setEditable(false);
        }
    }//GEN-LAST:event_chkCantidadMinimaItemStateChanged

    private void txtCantidadInicialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadInicialKeyReleased
        String x = txtCantidadInicial.getText();
        if(!objUtils.isDouble(x)){
            txtCantidadInicial.setText("");
        }        
    }//GEN-LAST:event_txtCantidadInicialKeyReleased

private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    mostrarImagen(x_ruta + imgBlanco); 
    ArrayList<clsProducto> dataProducto = objProducto.consultarDataProductoPorCodigo(txtCodigo.getText().toString()); 
     codigoProducto = Integer.parseInt(dataProducto.get(0).getIdItems());
     txtDescripcion.setText(dataProducto.get(0).getDesItem());
     txtCosto.setText(""+dataProducto.get(0).getCosto());
     txtCantidadInicial.setText(""+dataProducto.get(0).getCantStock());
     if(dataProducto.get(0).getControlPerecible().equals("S"))
     {
         chkPerecible.setSelected(true);
         String fecha = dataProducto.get(0).getFechaCaducidad();      
         java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("yyyy-MM-dd");
         try {
            Date fechaActual = formato.parse(fecha);
            txtFechaCaducidad.setDate(fechaActual);
         }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Revisar datos " + ex );
         }         
     }    
     if(dataProducto.get(0).getControlExistencia().equals("S"))
     {
        rbtnSinExistencia.setSelected(true);         
     }
     if(dataProducto.get(0).getControlMinimo().equals("S"))
     {
         this.chkCantidadMinima.setSelected(true);
         txtCantidadMinima.setText(dataProducto.get(0).getCantidadMinima());
         
     }
     String nombreRandomico = "";
     try{
        if(dataProducto.get(0).getImagen().isEmpty()||
                dataProducto.get(0).getImagen()==null||
                dataProducto.get(0).getImagen().equals(""))
        {}
        else
        {
            nombreRandomico = dataProducto.get(0).getImagen();
            String ruta = x_ruta + nombreRandomico;
            //rutaActual = x_ruta + nombreRandomico + ".jpg";
            mostrarImagen(ruta);

        }      
    }
    catch(Exception e){
      //JOptionPane.showMessageDialog(null, e);
    }
     
     //CARGAR GRUPO
    cmbGrupo.removeAllItems();
    ArrayList<clsComboBox> dataGrupo = objGrupo.consultarGrupos();        
    for(int i=0;i<dataGrupo.size();i=i+1)
    {
        clsComboBox oItem = new clsComboBox(dataGrupo.get(i).getCodigo(), dataGrupo.get(i).getDescripcion());
        cmbGrupo.addItem(oItem);
        if(Integer.parseInt(dataGrupo.get(i).getCodigo())==(dataProducto.get(0).getIdGrupoProducto()))
        {    
            cmbGrupo.setSelectedItem(oItem);
        }    
    }
     //CONSULTAR LOS PRECIOS
     objUtils.vaciarTabla(dtmData);
     ArrayList<clsPrecio> dataPrecio = objPrecio.consultarDataPrecios(codigoProducto);
     for(int i=0;i<dataPrecio.size();i=i+1)
     {
        Double costo = objUtils.redondear(Double.parseDouble(""+dataPrecio.get(i).getCosto()));
        Double utilidad = objUtils.redondear(Double.parseDouble(""+dataPrecio.get(i).getUtilidad()));
        Double precio = objUtils.redondear(Double.parseDouble(""+dataPrecio.get(i).getPrecio()));
        Object[] nuevaFila = {"0", costo , utilidad, precio};               
        dtmData.addRow(nuevaFila); 
     }
     objUtils.enumerarFilas(dtmData, 0);
}//GEN-LAST:event_btnBuscarActionPerformed

    public void mostrarImagen(String ruta)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(ruta));
        } catch (IOException e) {
        }
        
        //JLabel etiqueta = new JLabel(imagen);
        //jPanel5.add(etiqueta);
        int ancho =imagepanel.getWidth();
        int alto = imagepanel.getHeight();
        Image x = redimensionarImagen(img, ancho, alto);
        imagepanel.setImage(x);
        imagepanel.setStyle(Style.CENTERED);  
        //imagepanel.setStyle(Style.SCALED);  
        imagepanel.setStyle(Style.SCALED_KEEP_ASPECT_RATIO);  
        imagepanel.setStyle(Style.TILED);  
        //etiqueta.setBounds(0,0,300,200);
    }
    
    /*Retornar una imagen tipo Image, redimensionada, apartir de los parametros -
    alto y ancho proporcionados. */
    public Image redimensionarImagen(Image imagen, int ancho, int alto) {
        return imagen.getScaledInstance(ancho, alto, Image.SCALE_AREA_AVERAGING);
    }//FIN: redimensionarImagen.

private void tblDataPreciosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDataPreciosKeyTyped
    DefaultTableModel dtm = (DefaultTableModel) tblDataPrecios.getModel();  
    if(evt.getKeyChar() == KeyEvent.VK_DELETE){
        dtm.removeRow(tblDataPrecios.getSelectedRow()); 
    }    
    objUtils.enumerarFilas(dtm, 0);
}//GEN-LAST:event_tblDataPreciosKeyTyped

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    frmListProductos ventana = new frmListProductos(null, true, "6", 0);        
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
}//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkCantidadMinima;
    private javax.swing.JCheckBox chkPerecible;
    private javax.swing.JComboBox cmbGrupo;
    private org.jdesktop.swingx.JXImagePanel imagepanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.jidesoft.swing.JideTabbedPane jideTabbedPane1;
    private javax.swing.JLabel lblFechaCaducidad;
    private javax.swing.JRadioButton rbtnConExistencia;
    private javax.swing.JRadioButton rbtnSinExistencia;
    private javax.swing.JTable tblDataPrecios;
    private javax.swing.JTextField txtCantidadInicial;
    private javax.swing.JTextField txtCantidadMinima;
    public static javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtDescripcion;
    private com.toedter.calendar.JDateChooser txtFechaCaducidad;
    // End of variables declaration//GEN-END:variables

   
}