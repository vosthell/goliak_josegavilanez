/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmAbrirCaja.java
 *
 * Created on 23-oct-2011, 13:04:29
 */
package pos;

import clases.clsAuditoria;
import clases.clsCabecera;
import index.main;
import javax.swing.JOptionPane;
import clases.clsCaja;
import clases.clsComboBox;
import clases.clsFacturero;
import clases.clsParametros;
import clases.javaMail;
import java.util.ArrayList;
import stinventario.frmPrincipal;


/**
 *
 * @author Kaiser
 */
public class frmAbrirCaja extends javax.swing.JDialog {
    clsCaja objCaja = new clsCaja();
    clsAuditoria objAuditoria = new clsAuditoria();
    clsCabecera objCabecera = new clsCabecera();
    clsFacturero objFacturero = new clsFacturero();
    String facturacionManual;
    clsParametros objParametros = new clsParametros();
    //String primeraVezManual;
    //String primerValorManual;
    /** Creates new form frmAbrirCaja */
    public frmAbrirCaja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtUsuario.setText(main.nameUser);
        int ultmFactura = objCabecera.obtenerUltimaFacturaRegistrada();
        txtFacturaSistema.setText("" + ultmFactura);
        //CARGAR CAJERO
        ArrayList<clsComboBox> dataPrecio = objCaja.consultarCajero();        
        for(int i=0;i<dataPrecio.size();i=i+1)
        //if(dataPrecio.isEmpty())
        {
            clsComboBox oItem = new clsComboBox(dataPrecio.get(i).getCodigo(), dataPrecio.get(i).getDescripcion());
            cmbCajero.addItem(oItem);            
        }
        
        //CARGAR FACTURERO       
        ArrayList<clsComboBox> dataFacturero = objFacturero.consultarFactureros();

        for(int i=0;i<dataFacturero.size();i=i+1)
        {
            clsComboBox oItem = new clsComboBox(dataFacturero.get(i).getCodigo(), dataFacturero.get(i).getDescripcion());
            cmbFacturero.addItem(oItem);            
        }  
       
    }
    
    static boolean isDouble(String cadena){
        try {
            Double.parseDouble(cadena);
            return true;
        }
        catch (NumberFormatException nfe){
            return false;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbCajero = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        txtFacturaSistema = new javax.swing.JTextField();
        txtFacturaManual = new javax.swing.JTextField();
        rbtnSistema = new javax.swing.JRadioButton();
        rbtnManual = new javax.swing.JRadioButton();
        cmbFacturero = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        btnAbrirCaja = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmAbrirCaja.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtUsuario.setEditable(false);
        txtUsuario.setText(resourceMap.getString("txtUsuario.text")); // NOI18N
        txtUsuario.setName("txtUsuario"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtValor.setText(resourceMap.getString("txtValor.text")); // NOI18N
        txtValor.setName("txtValor"); // NOI18N
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorKeyReleased(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        cmbCajero.setName("cmbCajero"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        txtFacturaSistema.setEditable(false);
        txtFacturaSistema.setText(resourceMap.getString("txtFacturaSistema.text")); // NOI18N
        txtFacturaSistema.setName("txtFacturaSistema"); // NOI18N

        txtFacturaManual.setEditable(false);
        txtFacturaManual.setText(resourceMap.getString("txtFacturaManual.text")); // NOI18N
        txtFacturaManual.setName("txtFacturaManual"); // NOI18N

        buttonGroup1.add(rbtnSistema);
        rbtnSistema.setSelected(true);
        rbtnSistema.setText(resourceMap.getString("rbtnSistema.text")); // NOI18N
        rbtnSistema.setName("rbtnSistema"); // NOI18N

        buttonGroup1.add(rbtnManual);
        rbtnManual.setText(resourceMap.getString("rbtnManual.text")); // NOI18N
        rbtnManual.setName("rbtnManual"); // NOI18N

        cmbFacturero.setName("cmbFacturero"); // NOI18N
        cmbFacturero.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFactureroItemStateChanged(evt);
            }
        });

        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnManual)
                    .addComponent(rbtnSistema))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbFacturero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFacturaSistema, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                .addGap(70, 70, 70)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFacturaManual, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnSistema)
                    .addComponent(txtFacturaSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnManual)
                    .addComponent(txtFacturaManual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFacturero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        txtObservacion.setName("txtObservacion"); // NOI18N
        jScrollPane1.setViewportView(txtObservacion);

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbCajero, 0, 210, Short.MAX_VALUE)))
                .addGap(230, 230, 230))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnAbrirCaja.setIcon(resourceMap.getIcon("btnAbrirCaja.icon")); // NOI18N
        btnAbrirCaja.setText(resourceMap.getString("btnAbrirCaja.text")); // NOI18N
        btnAbrirCaja.setName("btnAbrirCaja"); // NOI18N
        btnAbrirCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(btnAbrirCaja)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrirCaja)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
    String x = txtValor.getText();
    if(!isDouble(x)){
        txtValor.setText("");
    }
}//GEN-LAST:event_txtValorKeyReleased

private void btnAbrirCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCajaActionPerformed
    if(txtValor.getText().equals("")||txtUsuario.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this, "Ingrese correctamente la información", "Atención!", JOptionPane.ERROR_MESSAGE);
    }
    else
    {
        //FACTURAR MANUAL
        if(this.rbtnManual.isSelected())               
        {
           facturacionManual = "S";
           //primeraVezManual = "S";
           //primerValorManual = txtFacturaManual.getText(); 
        }
        else if(this.rbtnSistema.isSelected())               
        {            
             facturacionManual = "N";
             //primeraVezManual = "X";
             //primerValorManual = "X"; 
        } 
        clsComboBox objCajeroSelect = (clsComboBox)cmbCajero.getSelectedItem();
        clsComboBox objFactureroSelect = (clsComboBox)cmbFacturero.getSelectedItem();
        boolean exito = objCaja.registrarAperturaCaja(index.main.idUser, 
                                    this.txtValor.getText().toString(), 
                                    objCajeroSelect.getCodigo(), 
                                    facturacionManual, 
                                    objFactureroSelect.getCodigo(),
                                    txtObservacion.getText().toString());
        if(exito)
        {
            //MARCAR CAJERO USADO
            objCaja.modificarCaja(objCajeroSelect.getCodigo(), "A");
            JOptionPane.showMessageDialog(this, "Datos almacenados con éxito", "Atención!", JOptionPane.INFORMATION_MESSAGE);
            objAuditoria.insertarAuditoria("frmAbrirCaja", "ABRIO CAJA CON: $ "+                                            
                                                txtValor.getText().toString(), "1");
            /*javaMail mail = new javaMail();
            mail.send("vosthell@hotmail.com","APERTURA DE CAJA", "EL USUARIO: " 
                    + txtUsuario.getText().toString()
                    + ", ABRIO CAJA CON: $ " + txtValor.getText().toString() + "</BR>"
                    + " OBSERVACION: " + txtObservacion.getText().toString());*/
            
            //ENVIAR CORREO
            String email_habilitado = objParametros.consultaValor("email_habilitado");
            if(email_habilitado.equals("1"))
            {    
                frmEnviarCorreoAbrir ventana = new frmEnviarCorreoAbrir(null, true, txtValor.getText().toString(), txtObservacion.getText().toString());
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            }           
            //ENVIAR CORREO FIN
            //********************//
            exito = objCaja.consultarCajaAbierta(index.main.idUser);
            if(exito)
            {
                frmPrincipal.lblPendiente.setText("TIENE PENDIENTE CERRAR CAJA");
                frmPrincipal.btnAbrir.setEnabled(false);  
                frmPrincipal.btnFacturar.setEnabled(true);
                frmPrincipal.btnPagos.setEnabled(true);
                frmPrincipal.btnCerrar.setEnabled(true); 
                frmPrincipal.btnEgreso.setEnabled(true); 
                frmPrincipal.btnIngreso.setEnabled(true); 
            }   
            frmPrincipal.btnCajaAntes.setVisible(false);
             //********************//
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Error al registrar", "Atención!", JOptionPane.ERROR_MESSAGE);
            objAuditoria.insertarAuditoria("frmAbrirCaja", "INTENTO ABRIR CAJA SIN EXITO CON: $ "+                                            
                                                txtValor.getText().toString(), "1");
        }
        
    }  
}//GEN-LAST:event_btnAbrirCajaActionPerformed

private void cmbFactureroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFactureroItemStateChanged
    clsComboBox objFactureroSelect = (clsComboBox)cmbFacturero.getSelectedItem();
    String factActual = objFacturero.seleccionarFacturaActual(Integer.parseInt(objFactureroSelect.getCodigo()));
    txtFacturaManual.setText(factActual);
}//GEN-LAST:event_cmbFactureroItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmAbrirCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAbrirCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAbrirCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAbrirCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                frmAbrirCaja dialog = new frmAbrirCaja(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirCaja;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbCajero;
    private javax.swing.JComboBox cmbFacturero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtnManual;
    private javax.swing.JRadioButton rbtnSistema;
    private javax.swing.JTextField txtFacturaManual;
    private javax.swing.JTextField txtFacturaSistema;
    private javax.swing.JTextArea txtObservacion;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
