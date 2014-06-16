/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

import clases.clsCliente;
import clases.clsComentario;
import clases.clsPago;
import clases.clsUtils;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import stinventario.frmPrincipal;

/**
 *
 * @author User
 */
public class frmReporteCartera extends javax.swing.JInternalFrame {
    MiModelo dtmData = new MiModelo();
    clsUtils objUtils = new clsUtils();
    clsCliente objCliente = new clsCliente();
    clsComentario objComentario = new clsComentario();
    //clsComentario
    /**
     * Creates new form frmReporteCartera
     */
    public frmReporteCartera() {
        initComponents();
        
        dtmData.addColumn("N°");/*.setPreferredWidth(500)*/
        dtmData.addColumn("CEDULA");
        dtmData.addColumn("NOMBRE");
        dtmData.addColumn("RECINTO");
        dtmData.addColumn("DIRECCION");
        dtmData.addColumn("TELEFONO");       
        dtmData.addColumn("FECHA CREDITO");  
        dtmData.addColumn("VALOR");
        dtmData.addColumn("PLAZO CREDITO");
        dtmData.addColumn("DIAS DE MORA");
        dtmData.addColumn("VALOR PENDIENTE");
        dtmData.addColumn("COMENTARIOS");
        dtmData.addColumn("IDCABECERA");       
                
        objUtils.setOcultarColumnasJTable(this.tblData, new int[]{12});  
        //ALINEAR COLUMNA
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblData.getColumnModel().getColumn(3).setCellRenderer(tcr);        
        
        tblData.setAutoCreateRowSorter(true); 
        
        objUtils.vaciarTabla(dtmData);  
        
        buscar_informacion();
    }
    
    public void buscar_informacion()
    {
        
        ArrayList<clsCliente> dataCliente = objCliente.consultarDataClienteCartera(txtDias.getText(), txtNombre.getText());
        if(!dataCliente.isEmpty())
        {
            llenarData(dataCliente);
        }
    }
    
    public void llenarData(ArrayList<clsCliente> dataCliente)
    {
        //Double totalEfectivo = 0.00;        
        
        DecimalFormat df1 = new DecimalFormat("##0.00"); 
       
            int maxData = dataCliente.size();  
            String fecha_realizada = "";
            String fecha_registro = "";
            int numero_comentarios = 0;
            int id_cabecera = 0;
            for(int i=0; i<maxData; i++)
            {                    
                id_cabecera = dataCliente.get(i).getIdCabeceraMovi();
                //numero_comentarios = objComentario.consultarNumeroComentarios(id_cabecera);
                numero_comentarios = dataCliente.get(i).getNumComentario();
                Object[] nuevaFila = {  i+1,                          
                                        dataCliente.get(i).getCedula(),
                                        dataCliente.get(i).getNameCompleto(),
                                        dataCliente.get(i).getDescripcionRecinto(), 
                                        dataCliente.get(i).getDireccion(),   
                                        dataCliente.get(i).getTlfCelular(),
                                        dataCliente.get(i).getFecha(),
                                        dataCliente.get(i).getDeudaTotal(),
                                        dataCliente.get(i).getDescripcionPlazo(),
                                        dataCliente.get(i).getDiferencia(),
                                        dataCliente.get(i).getValorActual(),
                                        numero_comentarios + " (VER...)",
                                        id_cabecera
                 };                    
                 

                 //totalEfectivo = totalEfectivo + dataPago.get(i).getValor();             
                 dtmData.addRow(nuevaFila); 
            }         
            //txtTotal.setText("" + (objUtils.redondear(totalEfectivo)));     
        
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
    
    public static void mostrarJInternalCentrado(javax.swing.JInternalFrame formulario)
    {
        Dimension desktopSize = frmPrincipal.jDesktopPane1.getSize();
        Dimension jInternalFrameSize = formulario.getSize();
        formulario.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
        (desktopSize.height- jInternalFrameSize.height)/2);

        frmPrincipal.jDesktopPane1.add(formulario);
        formulario.show(); 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtDias = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnMostrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmReporteCartera.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblData.setModel(dtmData);
        tblData.setName("tblData"); // NOI18N
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblData);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtDias.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDias.setText(resourceMap.getString("txtDias.text")); // NOI18N
        txtDias.setName("txtDias"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        btnMostrar.setText(resourceMap.getString("btnMostrar.text")); // NOI18N
        btnMostrar.setName("btnMostrar"); // NOI18N
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtNombre.setText(resourceMap.getString("txtNombre.text")); // NOI18N
        txtNombre.setName("txtNombre"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNombre))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(btnMostrar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnMostrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        int fila = tblData.rowAtPoint(evt.getPoint());
        int columna = tblData.columnAtPoint(evt.getPoint());
        /*int columna = 3;*/
        /*if ((fila > -1) && (columna > -1))*/
        int i = tblData.getSelectedRow();
        int idCabecera = Integer.parseInt("" + tblData.getValueAt(i, 12));
        String nombre = "" + tblData.getValueAt(i, 2);
        
        if (columna == 11)
        {
            frmComentario formulario = new frmComentario(idCabecera, nombre);
            mostrarJInternalCentrado(formulario);  
            //dispose();
        }
        
    }//GEN-LAST:event_tblDataMouseClicked

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        objUtils.vaciarTabla(dtmData);
        buscar_informacion();
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        objUtils.vaciarTabla(dtmData);
        buscar_informacion();       
    }//GEN-LAST:event_txtNombreKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtDias;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
