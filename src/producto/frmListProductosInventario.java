/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmListProductosDel.java
 *
 * Created on 04-jun-2012, 10:14:09
 */
package producto;

import clases.clsAuditoria;
import clases.clsColorInventario;
import clases.clsProducto;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author CKaiser
 */
public class frmListProductosInventario extends javax.swing.JInternalFrame {
    clsProducto objProducto = new clsProducto();
    clsAuditoria objAuditoria = new clsAuditoria();
    
    MiModelo dtmData = new MiModelo();
    /** Creates new form frmListProductosDel */
    public frmListProductosInventario() {
        initComponents();
        
        dtmData.addColumn("Nº");
        dtmData.addColumn("Codigo");
        dtmData.addColumn("Nombre");
        dtmData.addColumn("id_producto"); 
        dtmData.addColumn("stock"); 
        dtmData.addColumn("descuento");
        dtmData.addColumn("Control Existencia");
        dtmData.addColumn("PRECIO");
        dtmData.addColumn("VERIFICADO");
        
        //ALINEAR COLUMNA
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblData.getColumnModel().getColumn(4).setCellRenderer(tcr); 
        tblData.getColumnModel().getColumn(5).setCellRenderer(tcr); 
        tblData.getColumnModel().getColumn(7).setCellRenderer(tcr); 
        
        //PARA ORDENAR FILAS
        tblData.setAutoCreateRowSorter(true);  
        
        llenarDatosProductos("N");
        tblData.setDefaultRenderer (Object.class, new clsColorInventario());
        
    }
    
    private void llenarDatosProductos(String noInventariados)
    {
        ArrayList<clsProducto> dataProducto = objProducto.consultarDataProductosAllConStock(noInventariados); 
        int max = 0;
        for(int i=0;i<dataProducto.size();i=i+1)
        {
             /*      
             ArrayList<clsComboBox> dataPrecios = objPrecio.consultarPrecios(dataProducto.get(i).getIdItems()); 
             if(dataPrecios.isEmpty())
                primerPrecio = "0";
             else
                primerPrecio = dataPrecios.get(0).getDescripcion();
             */    
            
             Object[] nuevaFila = {i+1, 
                                    dataProducto.get(i).getCodItem(), 
                                    dataProducto.get(i).getDesItem(),
                                    dataProducto.get(i).getIdItems(), 
                                    dataProducto.get(i).getCantStock(), 
                                    dataProducto.get(i).getDescuento(),
                                    dataProducto.get(i).getControlExistencia(),
                                    "$ " + dataProducto.get(i).getPrecio1(),
                                    dataProducto.get(i).getInv1()
                                    //dataProducto.get(i).getTipoMov()
             };               
            dtmData.addRow(nuevaFila); 
            max = i+1;
        }
        this.lblTotalRegistros.setText("" + max);
    }
    
    private void llenarDatosProductos2(String inventariados)
    {
        ArrayList<clsProducto> dataProducto = objProducto.consultarDataProductosAllConStock2(inventariados); 
        int max = 0;
        for(int i=0;i<dataProducto.size();i=i+1)
        {
             /*      
             ArrayList<clsComboBox> dataPrecios = objPrecio.consultarPrecios(dataProducto.get(i).getIdItems()); 
             if(dataPrecios.isEmpty())
                primerPrecio = "0";
             else
                primerPrecio = dataPrecios.get(0).getDescripcion();
             */    
            
             Object[] nuevaFila = {i+1, 
                                    dataProducto.get(i).getCodItem(), 
                                    dataProducto.get(i).getDesItem(),
                                    dataProducto.get(i).getIdItems(), 
                                    dataProducto.get(i).getCantStock(), 
                                    dataProducto.get(i).getDescuento(),
                                    dataProducto.get(i).getControlExistencia(),
                                    "$ " + dataProducto.get(i).getPrecio1(),
                                    dataProducto.get(i).getInv1()
                                    //dataProducto.get(i).getTipoMov()
             };               
            dtmData.addRow(nuevaFila); 
            max = i+1;
        }
        this.lblTotalRegistros.setText("" + max);
    }
    
    public class MiModelo extends DefaultTableModel
    {
            @Override
       public boolean isCellEditable (int row, int column)
       {
           // Aquí devolvemos true o false según queramos que una celda
           // identificada por fila,columna (row,column), sea o no editable
           if (column == 1)
           {
               return true;
           }   
           
           return false;
       }
            
             @Override
       public Class getColumnClass(int columna)
       {
          if (columna == 8) return Boolean.class;
          //if (columna == 1) return Integer.class;
          return Object.class;
       }
    } 
    
    /*public class colorFilaTable extends DefaultTableCellRenderer { 
    @Override 
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column ) 
    { 
        setEnabled(table == null || table.isEnabled());        
        
        if(String.valueOf(table.getValueAt(row,9)).equals("EGRESO")) 
            setBackground(Color.red); 
        else if(String.valueOf(table.getValueAt(row,9)).equals("INGRESO")) 
            setBackground(Color.GREEN); 
        else setBackground(null); 
        
        super.getTableCellRendererComponent(table, value, selected, focused, row, column); 
        return this; 
    } */
  

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        chkNoInv = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        lblTotalRegistros = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        chkInv = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmListProductosInventario.class);
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

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtNombreProducto.setName("txtNombreProducto"); // NOI18N
        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyReleased(evt);
            }
        });

        chkNoInv.setText(resourceMap.getString("chkNoInv.text")); // NOI18N
        chkNoInv.setName("chkNoInv"); // NOI18N
        chkNoInv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkNoInvItemStateChanged(evt);
            }
        });

        jLabel1.setName("jLabel1"); // NOI18N

        lblTotalRegistros.setName("lblTotalRegistros"); // NOI18N

        txtCodigo.setName("txtCodigo"); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        jLabel3.setName("jLabel3"); // NOI18N

        chkInv.setText(resourceMap.getString("chkInv.text")); // NOI18N
        chkInv.setName("chkInv"); // NOI18N
        chkInv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkInvItemStateChanged(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36)
                        .addComponent(lblTotalRegistros))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkInv)
                            .addComponent(chkNoInv))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(chkInv)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkNoInv)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotalRegistros))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        boolean estado = false;
        int codigo;
        int fila = tblData.rowAtPoint(evt.getPoint());
        int columna = tblData.columnAtPoint(evt.getPoint());        
        if ((fila > -1) && (columna == 8)) 
        {
            codigo = Integer.parseInt(""+tblData.getValueAt(fila, 3));
            estado = (Boolean)(tblData.getValueAt(fila, 8));
            if(estado)
                objProducto.actualizarEstado(codigo, false); 
            else
                objProducto.actualizarEstado(codigo, true); 
           
            //rellenar los datos
            int contRows = dtmData.getRowCount();
            for (int i = 0; i < contRows; i++)
            {
                dtmData.removeRow(0);
            }   
            buscarProductosPorNombre(txtNombreProducto.getText().toString());
            //llenarDatosProductos();
            this.txtNombreProducto.setText(txtNombreProducto.getText().toString());
        }
}//GEN-LAST:event_tblDataMouseClicked

private void txtNombreProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyReleased
    txtCodigo.setText("");
    buscarProductosPorNombre(txtNombreProducto.getText().toString());        
}//GEN-LAST:event_txtNombreProductoKeyReleased

private void chkNoInvItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkNoInvItemStateChanged
    chkInv.setSelected(false);
    int contRows = dtmData.getRowCount();
    for (int i = 0; i < contRows; i++)
    {
        dtmData.removeRow(0);
    }    
    if(chkNoInv.isSelected())
    {
        llenarDatosProductos("S");
    }
    else
    {
        llenarDatosProductos("N");
    }    
}//GEN-LAST:event_chkNoInvItemStateChanged

private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
    this.txtNombreProducto.setText("");
    buscarProductosPorCodigo(txtCodigo.getText().toString()); 
}//GEN-LAST:event_txtCodigoKeyReleased

private void chkInvItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkInvItemStateChanged
    chkNoInv.setSelected(false);
    int contRows = dtmData.getRowCount();
    for (int i = 0; i < contRows; i++)
    {
        dtmData.removeRow(0);
    }    
    if(chkInv.isSelected())
    {
        llenarDatosProductos2("S");
    }
    else
    {
        llenarDatosProductos2("N");
    }    
}//GEN-LAST:event_chkInvItemStateChanged
    
    void buscarProductosPorNombre(String texto){
        int contRows = dtmData.getRowCount();
        for (int i = 0; i < contRows; i++)
        {
            dtmData.removeRow(0);
        }  
        int max = 0;
        String noInv = "";
        //OBTENER SI ES CON INVENTARIADOS O NO
        if(chkNoInv.isSelected())
        {
            noInv = "S";
        }
        else
        {
            noInv = "N";
        }
        ArrayList<clsProducto> dataProducto = objProducto.consultarDataProductoPorNombreConStock(texto.toUpperCase(), noInv); 
        for(int i=0;i<dataProducto.size();i=i+1)
        {
             /*      
             ArrayList<clsComboBox> dataPrecios = objPrecio.consultarPrecios(dataProducto.get(i).getIdItems()); 
             if(dataPrecios.isEmpty())
                primerPrecio = "0";
             else
                primerPrecio = dataPrecios.get(0).getDescripcion();
             */     
             Object[] nuevaFila = {i+1, 
                                    dataProducto.get(i).getCodItem(), 
                                    dataProducto.get(i).getDesItem(),
                                    dataProducto.get(i).getIdItems(), 
                                    dataProducto.get(i).getCantStock(), 
                                    dataProducto.get(i).getDescuento(),
                                    dataProducto.get(i).getControlExistencia(),
                                    "$ " + dataProducto.get(i).getPrecio1(),
                                     dataProducto.get(i).getInv1()};               
            dtmData.addRow(nuevaFila); 
            max = i + 1;
        }
        this.lblTotalRegistros.setText("" + max);
    }
    
    void buscarProductosPorCodigo(String codigo){
        int contRows = dtmData.getRowCount();
        for (int i = 0; i < contRows; i++)
        {
            dtmData.removeRow(0);
        }  
        int max = 0;
        String noInv = "";
        //OBTENER SI ES CON INVENTARIADOS O NO
        if(chkNoInv.isSelected())
        {
            noInv = "S";
        }
        else
        {
            noInv = "N";
        }
        ArrayList<clsProducto> dataProducto = objProducto.consultarDataProductoPorCodigoConStock(codigo.toUpperCase(), noInv); 
        for(int i=0;i<dataProducto.size();i=i+1)
        {
             /*      
             ArrayList<clsComboBox> dataPrecios = objPrecio.consultarPrecios(dataProducto.get(i).getIdItems()); 
             if(dataPrecios.isEmpty())
                primerPrecio = "0";
             else
                primerPrecio = dataPrecios.get(0).getDescripcion();
             */     
             Object[] nuevaFila = {i+1, 
                                    dataProducto.get(i).getCodItem(), 
                                    dataProducto.get(i).getDesItem(),
                                    dataProducto.get(i).getIdItems(), 
                                    dataProducto.get(i).getCantStock(), 
                                    dataProducto.get(i).getDescuento(),
                                    dataProducto.get(i).getControlExistencia(),
                                    "$ " + dataProducto.get(i).getPrecio1(),
                                     dataProducto.get(i).getInv1()};               
            dtmData.addRow(nuevaFila); 
            max = i + 1;
        }
        this.lblTotalRegistros.setText("" + max);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkInv;
    private javax.swing.JCheckBox chkNoInv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombreProducto;
    // End of variables declaration//GEN-END:variables
}
