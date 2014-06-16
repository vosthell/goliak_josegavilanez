/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#sorting*/
/*
 * frmCompras.java
 *
 * Created on 17-feb-2012, 14:15:32
 */
package producto;

import clases.clsAuditoria;
import clases.clsCabecera;
import clases.clsComboBox;
import clases.clsDetalle;
import clases.clsEmail;
import clases.clsImpuestos;
import clases.clsParametros;
import clases.clsProducto;
import clases.clsProveedor;
import clases.clsProvincia;
import clases.clsUtils;
import clases.javaMail;
import com.jidesoft.hints.ListDataIntelliHints;
import com.jidesoft.swing.SelectAllUtils;
import index.main;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import pos.frmListProductos;
import pos.frmListProveedor;
import stinventario.frmPrincipal;

/**
 *
 * @author CKaiser
 */
public class frmCompras extends javax.swing.JInternalFrame {
    clsProveedor objProveedor = new clsProveedor();
    clsProducto objProducto = new clsProducto();
    clsImpuestos objImpuestos = new clsImpuestos();
    clsCabecera objCabecera = new clsCabecera();
    clsDetalle objDetalle = new clsDetalle();
    clsUtils objUtils = new clsUtils();
    clsAuditoria objAuditoria = new clsAuditoria();
    clsProvincia objProvincia = new clsProvincia();
    clsParametros objParametros = new clsParametros();
    clsEmail objEmail = new clsEmail();
      
    MiModelo dtmData = new MiModelo();
    //NOMBRE USUARIO
    public static int codigoProveedor = 0;
    public static int codigoProducto = 0;
    Double imp_ice = objImpuestos.obtenerPorcentajeICE();
    Double imp_irbp = objImpuestos.obtenerValorIRBP();
    Double imp_iva = objImpuestos.obtenerPorcentajeIVA();
    //Double baseTarifaIva = 0.00;
    //Double baseTarifaCero = 0.00;
    int filas=0;
    /** Creates new form frmCompras */
    public frmCompras() {
        initComponents();
        int ultmFactura = objCabecera.obtenerUltimaFacturaCompras(main.idUser);
        txtCodigoFactura.setText(""+ (ultmFactura+1));
        //Autocompletar Codigo de Proveedor
        List<String> dataCodigo = objProveedor.consultarCodigos(); 
        SelectAllUtils.install(txtCodigoProveedor);
        ListDataIntelliHints intellihints = new ListDataIntelliHints(txtCodigoProveedor, dataCodigo);        
        intellihints.setCaseSensitive(false);
        
        List<String> dataCodigoProducto = objProducto.consultarCodigos(); 
        SelectAllUtils.install(txtCodigoProducto);
        ListDataIntelliHints intellihints2 = new ListDataIntelliHints(txtCodigoProducto, dataCodigoProducto);
        //((JList)intellihints.getDelegateComponent()).setFixedCellWidth(50);
        intellihints2.setCaseSensitive(false);
        
        lblUsuario.setText(main.nameUser);
        
        //COLUMNA OCULTA
        dtmData.addColumn("idProducto");
        //objUtils.setOcultarColumnasJTable(tblData, new int[]{6});
        dtmData.addColumn("N°");/*.setPreferredWidth(500)*/
        dtmData.addColumn("Codigo");
        dtmData.addColumn("Descripción");
        dtmData.addColumn("Cantidad");
        dtmData.addColumn("Precio Unitario");        
        dtmData.addColumn("Total");
        dtmData.addColumn("IVA");
        dtmData.addColumn("Descuento ($)");
        dtmData.addColumn("porcentaje_descuento");
        dtmData.addColumn("precioPublico");
        dtmData.addColumn("imp_irbp");
        dtmData.addColumn("imp_ice");
        objUtils.setOcultarColumnasJTable(this.tblData, new int[]{9, 10, 11,12});
        
        Date fechaActual = new Date();
        txtFecha.setDate(fechaActual);
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblData.getColumnModel().getColumn(6).setCellRenderer(tcr);
        tblData.getColumnModel().getColumn(7).setCellRenderer(tcr);
        tblData.getColumnModel().getColumn(8).setCellRenderer(tcr);
        
        tblData.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                dtmData.removeTableModelListener(this);
                recalcular();                
                dtmData.addTableModelListener(this);
            }  
        });
        
        //CARGAR TIPOS DE GASTOS
        ArrayList<clsComboBox> dataProvincia = objProvincia.consultarGastos();        
        for(int i=0;i<dataProvincia.size();i=i+1)
        {
            clsComboBox oItem = new clsComboBox(dataProvincia.get(i).getCodigo(), dataProvincia.get(i).getDescripcion());
            cmbTipoGasto.addItem(oItem);            
        }
        //cmbTipoGasto.setSelectedIndex(12);
    }
    
    public void recalcular()
    {
        Double subTotal = 0.00;
        Double descuento = 0.00;
        Double subTotalParaCalcularIva = 0.00;
        Double iva = 0.00;
        
        int maxData = dtmData.getRowCount();
        for(int i=0; i<maxData; i++)
        {
            //CANTIDAD
            Double mult1 = Double.parseDouble("" + dtmData.getValueAt(i,4));
            //PRECIO
            Double mult2 = Double.parseDouble("" + dtmData.getValueAt(i,5));
            //SUBTOTAL
            subTotal = mult1 * mult2;
            dtmData.setValueAt(objUtils.redondearCincoDec(subTotal),i,6);
            //DESCUENTO
            descuento = subTotal * Double.parseDouble("" + dtmData.getValueAt(i,9))/100;
            subTotalParaCalcularIva = subTotal - descuento;
            //COMPROBAR SI SE  LE SUMA IVA
            boolean verificarIVA = objImpuestos.comprobarImpuesto(Integer.parseInt("" + dtmData.getValueAt(i,0)), "1");                    
            if(verificarIVA)
            {    
                iva = subTotalParaCalcularIva*(imp_iva/100);                    
            }
            else
            {   
                iva = 0.00;                                    
            }
            dtmData.setValueAt(objUtils.redondearCincoDec(iva), i, 7);
            dtmData.setValueAt(objUtils.redondearCincoDec(descuento), i, 8);
            calcularTotal();                  
        }
        calcularTotal();
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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoProveedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtCodigoFactura = new javax.swing.JTextField();
        txtFacturaReferencia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        txtAutorizacion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cmbTipoGasto = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDescuentoUnidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        txtBaseTarifaIVA = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtBaseTarifaCero = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        chkNotaVenta = new javax.swing.JCheckBox();
        chkIvaIncluido = new javax.swing.JCheckBox();
        txtPrecioVirgen = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtIRBP = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        txtBaseICE = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtICE = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmCompras.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setClosable(true);
        setIconifiable(true);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtCodigoProveedor.setBackground(resourceMap.getColor("txtCodigoProveedor.background")); // NOI18N
        txtCodigoProveedor.setText(resourceMap.getString("txtCodigoProveedor.text")); // NOI18N
        txtCodigoProveedor.setName("txtCodigoProveedor"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtNombreProveedor.setEditable(false);
        txtNombreProveedor.setText(resourceMap.getString("txtNombreProveedor.text")); // NOI18N
        txtNombreProveedor.setName("txtNombreProveedor"); // NOI18N

        btnBuscar.setText(resourceMap.getString("btnBuscar.text")); // NOI18N
        btnBuscar.setName("btnBuscar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        txtCodigoFactura.setEditable(false);
        txtCodigoFactura.setText(resourceMap.getString("txtCodigoFactura.text")); // NOI18N
        txtCodigoFactura.setName("txtCodigoFactura"); // NOI18N

        txtFacturaReferencia.setText(resourceMap.getString("txtFacturaReferencia.text")); // NOI18N
        txtFacturaReferencia.setName("txtFacturaReferencia"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        txtFecha.setDateFormatString(resourceMap.getString("txtFecha.dateFormatString")); // NOI18N
        txtFecha.setName("txtFecha"); // NOI18N
        txtFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtFechaPropertyChange(evt);
            }
        });

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        txtAutorizacion.setText(resourceMap.getString("txtAutorizacion.text")); // NOI18N
        txtAutorizacion.setName("txtAutorizacion"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        cmbTipoGasto.setName("cmbTipoGasto"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel13))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCodigoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addComponent(txtNombreProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(txtAutorizacion))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigoFactura)
                            .addComponent(txtFacturaReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbTipoGasto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCodigoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(btnBuscar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtAutorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(txtFacturaReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCodigoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(cmbTipoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtCodigoProducto.setBackground(resourceMap.getColor("txtPrecio.background")); // NOI18N
        txtCodigoProducto.setText(resourceMap.getString("txtCodigoProducto.text")); // NOI18N
        txtCodigoProducto.setName("txtCodigoProducto"); // NOI18N
        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyTyped(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtNombreProducto.setEditable(false);
        txtNombreProducto.setText(resourceMap.getString("txtNombreProducto.text")); // NOI18N
        txtNombreProducto.setName("txtNombreProducto"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtPrecio.setEditable(false);
        txtPrecio.setBackground(resourceMap.getColor("txtPrecio.background")); // NOI18N
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecio.setText(resourceMap.getString("txtPrecio.text")); // NOI18N
        txtPrecio.setName("txtPrecio"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtCantidad.setEditable(false);
        txtCantidad.setBackground(resourceMap.getColor("txtPrecio.background")); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCantidad.setText(resourceMap.getString("txtCantidad.text")); // NOI18N
        txtCantidad.setName("txtCantidad"); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtDescuentoUnidad.setEditable(false);
        txtDescuentoUnidad.setBackground(resourceMap.getColor("txtPrecio.background")); // NOI18N
        txtDescuentoUnidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDescuentoUnidad.setText(resourceMap.getString("txtDescuentoUnidad.text")); // NOI18N
        txtDescuentoUnidad.setName("txtDescuentoUnidad"); // NOI18N
        txtDescuentoUnidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoUnidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoUnidadKeyTyped(evt);
            }
        });

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        btnAgregar.setIcon(resourceMap.getIcon("btnAgregar.icon")); // NOI18N
        btnAgregar.setText(resourceMap.getString("btnAgregar.text")); // NOI18N
        btnAgregar.setName("btnAgregar"); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblData.setModel(dtmData);
        tblData.setName("tblData"); // NOI18N
        tblData.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tblDataInputMethodTextChanged(evt);
            }
        });
        tblData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblDataKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblData);

        jLabel21.setFont(resourceMap.getFont("jLabel21.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        txtBaseTarifaIVA.setEditable(false);
        txtBaseTarifaIVA.setFont(resourceMap.getFont("txtBaseTarifaIVA.font")); // NOI18N
        txtBaseTarifaIVA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBaseTarifaIVA.setText(resourceMap.getString("txtBaseTarifaIVA.text")); // NOI18N
        txtBaseTarifaIVA.setAutoscrolls(false);
        txtBaseTarifaIVA.setName("txtBaseTarifaIVA"); // NOI18N

        jLabel17.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        txtBaseTarifaCero.setEditable(false);
        txtBaseTarifaCero.setFont(resourceMap.getFont("txtBaseTarifaCero.font")); // NOI18N
        txtBaseTarifaCero.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBaseTarifaCero.setText(resourceMap.getString("txtBaseTarifaCero.text")); // NOI18N
        txtBaseTarifaCero.setName("txtBaseTarifaCero"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        txtDescuento.setEditable(false);
        txtDescuento.setFont(resourceMap.getFont("txtDescuento.font")); // NOI18N
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDescuento.setText(resourceMap.getString("txtDescuento.text")); // NOI18N
        txtDescuento.setName("txtDescuento"); // NOI18N

        jLabel16.setFont(resourceMap.getFont("jLabel16.font")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        txtIVA.setEditable(false);
        txtIVA.setFont(resourceMap.getFont("txtIVA.font")); // NOI18N
        txtIVA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIVA.setText(resourceMap.getString("txtIVA.text")); // NOI18N
        txtIVA.setAutoscrolls(false);
        txtIVA.setName("txtIVA"); // NOI18N

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtTotal.setEditable(false);
        txtTotal.setFont(resourceMap.getFont("txtTotal.font")); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText(resourceMap.getString("txtTotal.text")); // NOI18N
        txtTotal.setDragEnabled(true);
        txtTotal.setName("txtTotal"); // NOI18N

        jLabel22.setFont(resourceMap.getFont("jLabel22.font")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        lblUsuario.setFont(resourceMap.getFont("lblUsuario.font")); // NOI18N
        lblUsuario.setText(resourceMap.getString("lblUsuario.text")); // NOI18N
        lblUsuario.setName("lblUsuario"); // NOI18N

        chkNotaVenta.setText(resourceMap.getString("chkNotaVenta.text")); // NOI18N
        chkNotaVenta.setName("chkNotaVenta"); // NOI18N

        chkIvaIncluido.setText(resourceMap.getString("chkIvaIncluido.text")); // NOI18N
        chkIvaIncluido.setName("chkIvaIncluido"); // NOI18N
        chkIvaIncluido.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkIvaIncluidoItemStateChanged(evt);
            }
        });

        txtPrecioVirgen.setEditable(false);
        txtPrecioVirgen.setText(resourceMap.getString("txtPrecioVirgen.text")); // NOI18N
        txtPrecioVirgen.setName("txtPrecioVirgen"); // NOI18N
        txtPrecioVirgen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVirgenKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVirgenKeyTyped(evt);
            }
        });

        txtPrecioVenta.setEditable(false);
        txtPrecioVenta.setText(resourceMap.getString("txtPrecioVenta.text")); // NOI18N
        txtPrecioVenta.setName("txtPrecioVenta"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        txtIRBP.setEditable(false);
        txtIRBP.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        txtIRBP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIRBP.setText(resourceMap.getString("txtIRBP.text")); // NOI18N
        txtIRBP.setName("txtIRBP"); // NOI18N

        jTextField2.setEditable(false);
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        txtBaseICE.setEditable(false);
        txtBaseICE.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        txtBaseICE.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBaseICE.setText(resourceMap.getString("txtBaseICE.text")); // NOI18N
        txtBaseICE.setName("txtBaseICE"); // NOI18N

        jLabel15.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel19.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        txtICE.setEditable(false);
        txtICE.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        txtICE.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtICE.setText(resourceMap.getString("txtICE.text")); // NOI18N
        txtICE.setName("txtICE"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidad))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecioVirgen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDescuentoUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(4, 4, 4))
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar)
                            .addComponent(chkIvaIncluido)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(chkNotaVenta)
                                .addGap(172, 172, 172)
                                .addComponent(jLabel15))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(lblUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19))
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtICE)
                            .addComponent(txtBaseICE)
                            .addComponent(txtIRBP, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel21)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDescuento)
                            .addComponent(txtBaseTarifaCero, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBaseTarifaIVA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(389, 389, 389)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField2, 0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField1, 0, 0, Short.MAX_VALUE))
                    .addContainerGap(406, Short.MAX_VALUE)))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtBaseICE, txtBaseTarifaCero, txtBaseTarifaIVA, txtDescuento, txtICE, txtIRBP, txtIVA, txtTotal});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(txtPrecioVirgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkIvaIncluido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar)
                            .addComponent(jLabel8)
                            .addComponent(txtDescuentoUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtBaseTarifaCero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtBaseICE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(chkNotaVenta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(txtICE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel19)
                                        .addComponent(lblUsuario)
                                        .addComponent(jLabel22))
                                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtTotal)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBaseTarifaIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)
                        .addComponent(txtIRBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addGap(71, 71, 71))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(161, 161, 161)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(161, Short.MAX_VALUE)))
        );

        btnGuardar.setIcon(resourceMap.getIcon("btnGuardar.icon")); // NOI18N
        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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
                        .addGap(366, 366, 366)
                        .addComponent(btnGuardar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    /*if(tblData.isEditing()){
		tblData.getCellEditor().stopCellEditing();
    }*/
    if(tblData.isEditing())
        JOptionPane.showMessageDialog(this, "Se esta editando la tabla", "Atención!", JOptionPane.INFORMATION_MESSAGE);
    else
        guardar();
}//GEN-LAST:event_btnGuardarActionPerformed
    
    public class MiModelo extends DefaultTableModel
    {
       @Override
       public boolean isCellEditable (int row, int column)
       {
           // Aquí devolvemos true o false según queramos que una celda
           // identificada por fila,columna (row,column), sea o no editable
           if ((column == 4)||(column == 5))
           {
               return true;
           }           
           return false;
       }
             
       @Override
       public Class getColumnClass(int columna)
       {
            if (columna == 4) return Double.class;
            if (columna == 5) return Double.class;
            return Object.class;          
       }
            
      /* public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            switch(columnIndex){
                case 4:
                {    //int nuevaCantidad = Integer.valueOf(aValue.toString());
                    int nuevaCantidad = 700;
                    setValueAt(nuevaCantidad, rowIndex, 6);
                }
            }
            // Disparamos el Evento TableDataChanged (La tabla ha cambiado)
            fireTableDataChanged();

        } */
    } 
     
    public boolean guardar()
    {
        boolean exito=false;
        int contRows = tblData.getRowCount();
       
        if(txtNombreProveedor.getText().equals("")||(contRows==0))
        {
           JOptionPane.showMessageDialog(this, "Ingrese correctamente la información", "Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
           if(registrarVenta())
                exito = true;
            else
                exito = false; 
        }
       
        return exito;
    }
    
    private boolean registrarVenta()
    {
        boolean p_exito = false;
        boolean exito = false;
        int idProducto = 0;
        Double cantidad = 0.00;
        Double precio = 0.00;   
        String descuento = "";
        String iva = "";
        String documento = "";
        String pvp = "";
        
        String descuentoF = txtDescuento.getText().toString();
        String ivaF = txtIVA.getText().toString();
        
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1= txtFecha.getDate();
        String fechaCompra = df1.format(date1);
        
        if(this.chkNotaVenta.isSelected())
        {
            documento = "V";               
        }
        else
        {
            documento = "F";
        } 
        clsComboBox objGastoSelect = (clsComboBox)cmbTipoGasto.getSelectedItem();
        
        exito = objCabecera.insertarRegistroCompras(codigoProveedor, main.idUser, 
                txtTotal.getText(), main.idEmpresa, descuentoF, ivaF, txtBaseTarifaIVA.getText(), 
                txtBaseTarifaCero.getText(), txtFacturaReferencia.getText(), documento, 
                txtIRBP.getText(), txtBaseICE.getText(), txtICE.getText(), fechaCompra,
                txtAutorizacion.getText(), objGastoSelect.getCodigo());
        int maxData = 0;
        if(exito)
        {
            try
            {                          
                maxData = dtmData.getRowCount();
                int ultmFactura = objCabecera.obtenerUltimaFacturaCompras(main.idUser);
                //System.out.println("S: " + ultmFactura);
                
                for(int i=0; i<maxData; i++)
                {                       
                    //factura, idProducto
                    idProducto = Integer.parseInt(dtmData.getValueAt(i, 0).toString());
                    cantidad = Double.parseDouble(""+dtmData.getValueAt(i, 4));
                    precio = Double.parseDouble(""+dtmData.getValueAt(i, 5));
                    iva = ""+dtmData.getValueAt(i, 7);
                    descuento = ""+dtmData.getValueAt(i, 8);
                    pvp = ""+dtmData.getValueAt(i, 10);
                    
                    exito = objDetalle.insertarDetalleCompras(ultmFactura, idProducto, cantidad, precio, descuento, iva, pvp);
                    /*if(this.chkNotaVenta.isSelected())
                    {
                        //NOTA DE VENTA
                        objProducto.insertarKardex(idProducto, "COMPRA, ID NOTA DE VENTA: " + ultmFactura + ", CODIGO REFERENCIA: " +txtFacturaReferencia.getText(), "+"+cantidad);
                    }
                    else
                    {
                        //FACTURA
                        objProducto.insertarKardex(idProducto, "COMPRA, ID FACTURA: " + ultmFactura + ", CODIGO REFERENCIA: " +txtFacturaReferencia.getText(), "+"+cantidad);
                    } */
                    //objProducto.aumentarStock(idProducto, cantidad);
                    //COMPROBAR SI SE  LE SUMA IVA
                    /*boolean verificarIVA = objImpuestos.comprobarImpuesto(idProducto, "1");
                    if(verificarIVA)
                    {    
                        objProducto.actualizarCosto(idProducto, (precio*1.12));
                    }
                    else
                    {
                        objProducto.actualizarCosto(idProducto, precio);
                    }  */                           
                }                        
                JOptionPane.showMessageDialog(this, "Compra registrada con éxito", "Atención!", JOptionPane.INFORMATION_MESSAGE);        
                objAuditoria.insertarAuditoria("frmCompras", "INGRESO DE LA COMPRA CODIGO: "+ultmFactura+ ", CON VALOR: " + txtTotal.getText(), "3");
                /*VACIAR DATOS*/
                txtNombreProducto.setText("");
                txtCodigoProducto.setText("");
                txtCantidad.setText("");
                txtCantidad.setEditable(false);
                txtPrecio.setText("");
                txtPrecio.setEditable(false);
                codigoProducto = 0;
                txtNombreProveedor.setText("");
                txtCodigoProveedor.setText("");
                txtBaseTarifaCero.setText("");
                txtBaseTarifaIVA.setText("");
                txtDescuento.setText("");
                txtIVA.setText("");
                txtTotal.setText("");
                /**/
                ultmFactura = objCabecera.obtenerUltimaFacturaCompras(main.idUser);
                txtCodigoFactura.setText("" + (ultmFactura+1));
                /**/
                objUtils.vaciarTabla(dtmData);
                p_exito = true;
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
                p_exito = false;
            }
            //Vaciar Datos para facturar de nuevo
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Error al ingresar la información", "Atención!", JOptionPane.ERROR_MESSAGE);      
            p_exito = false;
        }
        
        //ENVIAR EMAIL
        String email_habilitado = objParametros.consultaValor("email_habilitado");
        if(email_habilitado.equals("1"))
        {    
            try{
                String texto = "EL USUARIO: " 
                + main.nameUser+ ", REGISTRÓ LA COMPRA: " + txtCodigoFactura.getText() + "</BR>"
                            //+ "COMENTARIO: " + txtComentario.getText() + "</BR></BR>"
                            + "<TABLE BORDER=\"1\">"
                                + "<TR><TD>DESCRIPCION</TD><TD>VALOR</TD></TR>"                        
                                + "<TR><TD>FECHA DE COMPRA:</TD><TD>" + fechaCompra + "</TD></TR>"
                                + "<TR><TD>PROVEEDOR:</TD><TD>" + txtNombreProveedor.getText() + "</TD></TR>"                        
                                //+ "<TR><TD>DESCUENTO:</TD><TD>" + txtDescuento1.getText() + "</TD></TR>";               
                                //+ "<TR><TD>CREDITO:</TD><TD>NO</TD></TR>"
                                + "<TR><TD>TOTAL:</TD><TD>" + txtTotal.getText() + "</TD></TR>"
                                //texto = texto + "<TR><TD>VENDEDOR:</TD><TD>" + objVendedorSelect.getDescripcion() + "</TD></TR>"
                            + "</TABLE></BR></BR>"
                            + "<TABLE BORDER=\"1\">"
                            + "<TR><TD>PRODUCTO</TD><TD>CANTIDAD</TD></TR>" ;

                String descripcion = "";
                for(int i=0; i<maxData; i++)
                {                       
                    //**************DETALLE*******************//
                    //factura, idProducto
                    //idProducto = Integer.parseInt(dtmData.getValueAt(i, 0).toString());
                    descripcion = "" + dtmData.getValueAt(i, 3);
                    cantidad = Double.parseDouble(""+dtmData.getValueAt(i, 4));
                    //precio = "" + dtmData.getValueAt(i, 5);                        
                    //iva = "" + dtmData.getValueAt(i, 7);
                    //descuento = "" + dtmData.getValueAt(i, 8);
                    //costo = Double.parseDouble("" + dtmData.getValueAt(i, 11));


                    //exito = objDetalle.insertarDetalleNotasEntrega(ultmFactura, idProducto, cantidad, 
                    //        precio, descuento, iva, costo);
                    texto = texto + "<TR><TD>" + descripcion + "</TD><TD>" + cantidad + "</TD></TR>";

                }  
                texto = texto + "</TABLE>";

                javaMail mail = new javaMail();
                ArrayList<clsEmail> dataEmail = objEmail.consultarEmails("8");        
                for(int i=0;i<dataEmail.size();i=i+1)
                {
                    mail.send(dataEmail.get(i).getEmail(), "REGISTRO COMPRA", texto);
                }
            }
            catch(Exception e){
                //e.printStackTrace();
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error al enviar correo", JOptionPane.ERROR_MESSAGE);
            }
        }
        //ENVIAR EMAIL - FIN
        return p_exito;     
    }
    
    
private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
     obtenerDatosProveedor();
}//GEN-LAST:event_btnBuscarActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    frmListProveedor ventana = new frmListProveedor(null, true, "1");
    //new inventariopdf.JDEscoger(this, true);
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
}//GEN-LAST:event_jButton1ActionPerformed

private void txtCodigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyTyped
    // TODO add your handling code here:
     if(evt.getKeyChar() == KeyEvent.VK_ENTER){           
            String codeProducto = txtCodigoProducto.getText().toString();
            ArrayList<clsProducto> dataProducto = objProducto.consultarDataProductoPorCodigo(codeProducto); 
            if(dataProducto.isEmpty())
            {
                //JOptionPane.showMessageDialog(this, "Código incorrecto del producto", "Atención!", JOptionPane.ERROR_MESSAGE);
                int seleccion = JOptionPane.showOptionDialog(
                                    this, // Componente padre
                                    "Codigo de producto no existe ¿Desea crear producto con este código?", //Mensaje
                                    "Seleccione una opción", // Título
                                    JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,    // null para icono por defecto.
                                    new Object[] { "Si", "No"},    // null para YES, NO y CANCEL
                                    "Si");
                if (seleccion != -1)
                {
                    if((seleccion + 1)==1)
                    {
                        frmProductosAddCompras formulario = new frmProductosAddCompras(codeProducto);
                        mostrarJInternalCentrado(formulario);
                    }
                    else
                    {
                        //JOptionPane.showMessageDialog(this, "NO", "Atención!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else
            {
                codigoProducto = Integer.parseInt(dataProducto.get(0).getIdItems());
                txtNombreProducto.setText(dataProducto.get(0).getDesItem().trim());
                txtPrecioVenta.setText(""+dataProducto.get(0).getPrecio1());
                //txtPrecio.setEditable(true);
                txtPrecioVirgen.setEditable(true);
                txtCantidad.setEditable(true);
                txtDescuentoUnidad.setEditable(true);
                
                txtCantidad.requestFocus();
            }  
       }
}//GEN-LAST:event_txtCodigoProductoKeyTyped

    public static void mostrarJInternalCentrado(javax.swing.JInternalFrame formulario)
    {
        Dimension desktopSize = frmPrincipal.jDesktopPane1.getSize();
        Dimension jInternalFrameSize = formulario.getSize();
        formulario.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
        (desktopSize.height- jInternalFrameSize.height)/2);

        frmPrincipal.jDesktopPane1.add(formulario);
        formulario.show(); 
    }

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    frmListProductos ventana = new frmListProductos(null, true, "4", 0);        
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
}//GEN-LAST:event_jButton2ActionPerformed

private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
    agregarProducto();
}//GEN-LAST:event_btnAgregarActionPerformed

private void tblDataKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDataKeyTyped
    //SI ES LA TECLA DELETE BORRO LA FILA
    DefaultTableModel dtm = (DefaultTableModel) tblData.getModel();  
    if(evt.getKeyChar() == KeyEvent.VK_DELETE){
        dtm.removeRow(tblData.getSelectedRow()); 
    }   
    //ENUMERO FILAS NUEVAMENTE
    objUtils.enumerarFilas(dtm, 1);
    //CALCULO DATOS NUEVAMENTE
    this.txtBaseTarifaCero.setText("0.00");
    this.txtBaseTarifaIVA.setText("0.00");
    this.txtIRBP.setText("0.00");
    this.calcularTotal();
    /*dtmData.addColumn("Cantidad");
        dtmData.addColumn("Precio Unitario");        
        dtmData.addColumn("Total");
        dtmData.addColumn("IVA");
        dtmData.addColumn("Descuento");
        dtmData.addColumn("porcentaje_descuento");*/
    int fila = tblData.getEditingRow();
    int columna = tblData.getEditingColumn();
    Double cantidad = 0.00;
    if(columna==4)
    {    
        cantidad = Double.parseDouble(""+tblData.getValueAt(fila ,columna));
        tblData.setValueAt("8", fila, 6);
    }
    //tblData.getCellEditor().getClass().
    if (tblData.isEditing())
    {
        System.out.println("Editandose");
    }
}//GEN-LAST:event_tblDataKeyTyped

private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
     if(evt.getKeyChar() == KeyEvent.VK_ENTER){  
         txtPrecioVirgen.requestFocus();
     }
}//GEN-LAST:event_txtCantidadKeyTyped

private void txtDescuentoUnidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoUnidadKeyTyped
     if(evt.getKeyChar() == KeyEvent.VK_ENTER){  
         btnAgregar.requestFocus();
     }
}//GEN-LAST:event_txtDescuentoUnidadKeyTyped

private void txtPrecioVirgenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVirgenKeyReleased
    String x = txtPrecioVirgen.getText();
    if(!objUtils.isDouble(x)){
        txtPrecioVirgen.setText("");
        txtPrecio.setText("");
    }  
    else
    {    
        if(chkIvaIncluido.isSelected())
        {    
            //le saco el iva
            Double precio = Double.parseDouble(txtPrecioVirgen.getText().toString()); 
            Double precioSinIva = precio/(1+(imp_iva/100));
            txtPrecio.setText(""+objUtils.redondearCincoDec(precioSinIva));
        }
        else
            txtPrecio.setText(txtPrecioVirgen.getText().toString());
    }
}//GEN-LAST:event_txtPrecioVirgenKeyReleased

private void txtPrecioVirgenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVirgenKeyTyped
    if(evt.getKeyChar() == KeyEvent.VK_ENTER){  
         txtDescuentoUnidad.requestFocus();
     }
}//GEN-LAST:event_txtPrecioVirgenKeyTyped

private void chkIvaIncluidoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkIvaIncluidoItemStateChanged
    if(!txtPrecioVirgen.getText().equals(""))
    {
        if(chkIvaIncluido.isSelected())
        {    
            //le saco el iva
            Double precio = Double.parseDouble(txtPrecioVirgen.getText().toString()); 
            Double precioSinIva = precio/(1+(imp_iva/100));
            txtPrecio.setText(""+objUtils.redondearCincoDec(precioSinIva));
        }
        else
        {    
            txtPrecio.setText(txtPrecioVirgen.getText().toString());        
        }
    }
}//GEN-LAST:event_chkIvaIncluidoItemStateChanged

private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
    String x = txtCantidad.getText();
    if(!objUtils.isDouble(x)){
        txtCantidad.setText("");        
    }  
}//GEN-LAST:event_txtCantidadKeyReleased

private void txtDescuentoUnidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoUnidadKeyReleased
    String x = txtDescuento.getText();
    if(!objUtils.isDouble(x)){
        txtDescuento.setText("");        
    }  
}//GEN-LAST:event_txtDescuentoUnidadKeyReleased

private void tblDataInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblDataInputMethodTextChanged
    System.out.println("8");
}//GEN-LAST:event_tblDataInputMethodTextChanged

private void txtFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFechaPropertyChange
    
}//GEN-LAST:event_txtFechaPropertyChange
    void agregarProducto()
    {
        boolean verificarIRBP = false;
        boolean verificarICE = false;
        if(txtDescuentoUnidad.getText().equals(""))
            txtDescuentoUnidad.setText("0");
        
        if(txtCodigoProducto.getText().equals("")||txtNombreProducto.getText().equals("")||txtCantidad.getText().equals("")||txtPrecio.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Verificar datos", "Atención!", JOptionPane.ERROR_MESSAGE);    
        }
        else
        {
            String codeProducto = this.txtCodigoProducto.getText();
            String descripcion = this.txtNombreProducto.getText();
            double cantidad = Double.parseDouble(txtCantidad.getText());
                          
            /*buscar si esta facturado un producto igual,
            * y sumar su cantidad
            */        
            boolean encontrado = busquedaProducto(codigoProducto,
                    Double.parseDouble(txtPrecio.getText().toString()), 
                    Double.parseDouble(txtDescuentoUnidad.getText().toString()));
            //System.out.println("Encontrado:"+encontrado);
            if(encontrado)
            {
                //AUMENTO EN 1 LA CANTIDAD DEL ARTICULO REPETIDO
                agregarCantidad(codigoProducto, cantidad,
                        Double.parseDouble(txtPrecio.getText().toString()), 
                        Double.parseDouble(txtDescuentoUnidad.getText().toString()));
            }    
            else
            {    
                double iva = 0.00;
                double descuento=0.00;
                double subTotalParaCalcularIva=0.00;
                double precio = Double.parseDouble(txtPrecio.getText().toString());
                double subTotal = cantidad*precio; 
                descuento = subTotal * Double.parseDouble(txtDescuentoUnidad.getText().toString())/100;
                subTotalParaCalcularIva = subTotal - descuento;
                //COMPROBAR SI SE  LE SUMA IVA
                boolean verificarIVA = objImpuestos.comprobarImpuesto(codigoProducto, "1");
                if(verificarIVA)
                {    
                    iva = subTotalParaCalcularIva*(imp_iva/100); 
                    //baseTarifaIva = baseTarifaIva + subTotalParaCalcularIva;
                    //txtTarifaIVA.setText(""+objUtils.redondear(baseTarifaIva)); 
                }
                else
                {   
                    iva = 0.00;
                    //baseTarifaCero = baseTarifaCero+subTotalParaCalcularIva;
                    //txtBaseTarifaCero.setText(""+objUtils.redondearCincoDec(baseTarifaCero));                            
                }
                //COMPROBAR SI TIENE IRBP
                /*boolean verificarIRBP = objImpuestos.comprobarImpuesto(codigoProducto, "2");
                if(verificarIRBP)
                {   
                    Double irbpActual = objImpuestos.obtenerValorIRBP() * cantidad;
                    Double irbpGuardado = Double.parseDouble(txtIRBP.getText());                    
                    
                    txtIRBP.setText(""+(irbpActual+irbpGuardado));
                }*/
                //VERIFICAR SI TIENE IRBP
                String irbp = "";
                verificarIRBP = objImpuestos.comprobarImpuesto(codigoProducto, "2");
                if(verificarIRBP)
                    irbp = "S";  
                else
                    irbp = "N";
                //VERIFICAR SI TIENE ICE
                String ice = "";
                verificarICE = objImpuestos.comprobarImpuesto(codigoProducto, "3");
                if(verificarICE)
                    ice = "S";  
                else
                    ice = "N";
                Object[] nuevaFila = {codigoProducto, 0, codeProducto, descripcion, cantidad, 
                    objUtils.redondearCincoDec(precio), objUtils.redondearCincoDec(subTotal), objUtils.redondearCincoDec(iva), 
                    descuento, txtDescuentoUnidad.getText(), txtPrecioVenta.getText(),
                    irbp, ice
                };
                dtmData.addRow(nuevaFila);
                filas++;
            }
            objUtils.enumerarFilas(dtmData, 1);
            calcularTotal();

            /*VACIAR DATOS*/
            txtNombreProducto.setText("");
            txtCodigoProducto.setText("");
            txtCantidad.setText("");
            //txtPrecio.setText("");
            //txtPrecio.setEditable(true);
            txtPrecioVirgen.setText("");
            txtPrecio.setText("");
            txtPrecioVenta.setText("");
            txtPrecioVirgen.setEditable(false);
            txtCantidad.setEditable(false);
            txtDescuentoUnidad.setEditable(false);
                
            codigoProducto = 0;
            txtDescuentoUnidad.setText("0");

            txtCodigoProducto.getFocusListeners();           
        }
    }
    public void obtenerDatosProveedor()
    {
        txtCodigoProveedor.setEditable(false);
        ArrayList<clsProveedor> dataProveedor = objProveedor.consultaDataProveedor(txtCodigoProveedor.getText().toString()); 
        
        codigoProveedor = dataProveedor.get(0).getIdProveedor();
        txtNombreProveedor.setText(dataProveedor.get(0).getNombre());
        
        //txtDireccion.setText(dataProveedor.get(0).getDireccion());
        //txtTelefono.setText(dataProveedor.get(0).getTelefono());

    }
    
    boolean agregarCantidad(int codProducto, double cantidad, double precioUnitario, double porcentajeDescuento)
    {
        try
        {
            //boolean bandera=false;
            int maxData = dtmData.getRowCount();
            double iva = 0.00;
            double subTotal = 0.00;
            double descuento = 0.00;
            double subTotalParaCalcularIva=0.00;
            for(int i=0; i<maxData; i++)
            {
                
                Double valor2 = Double.parseDouble(dtmData.getValueAt(i, 5).toString());
                Double valor3 = Double.parseDouble(dtmData.getValueAt(i, 9).toString());
                if(dtmData.getValueAt(i,0).equals(codProducto)&&
                    (valor2.equals(precioUnitario)&&
                            (valor3.equals(porcentajeDescuento))))
                {
                    //AGREGO CANTIDAD
                    dtmData.setValueAt(Double.parseDouble("" + dtmData.getValueAt(i,4)) + cantidad, i, 4);
                    //CANTIDAD
                    Double mult1 = Double.parseDouble("" + dtmData.getValueAt(i,4));
                    //PRECIO
                    Double mult2 = Double.parseDouble("" + dtmData.getValueAt(i,5));
                    //SUBTOTAL
                    subTotal = mult1 * mult2;
                    dtmData.setValueAt(subTotal,i,6);
                    //DESCUENTO
                    descuento = subTotal*Double.parseDouble(txtDescuentoUnidad.getText().toString())/100;
                    subTotalParaCalcularIva = subTotal - descuento;
                    //COMPROBAR SI SE  LE SUMA IVA
                    boolean verificarIVA = objImpuestos.comprobarImpuesto(codigoProducto, "1");                    
                    if(verificarIVA)
                    {    
                        iva = subTotalParaCalcularIva * (imp_iva/100);                         
                        //baseTarifaIva = baseTarifaIva + subTotalParaCalcularIva;                        
                    }
                    else
                    {   
                        iva = 0.00;
                        //baseTarifaCero = baseTarifaCero+subTotalParaCalcularIva;                      
                    }                    
                    dtmData.setValueAt(iva, i, 7);
                    dtmData.setValueAt(descuento, i, 8);
                    calcularTotal();                  
                }           
            }
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
     
    void calcularTotal()
    {
        //Double total = 0.00;
        Double impIVA = 0.00;
        Double descuento = 0.00;
        Double baseIva = 0.00;
        Double baseCero = 0.00;
        Double iva = 0.00;
        Double irbp = 0.00;
        Double subto = 0.00;
        Double baseIce = 0.00;
        Double ice = 0.00;
        int maxData = dtmData.getRowCount();
        boolean verificarIRBP = false;
        boolean verificarICE = false;
        
        for(int i=0; i<maxData; i++)
        {
            iva = Double.parseDouble(dtmData.getValueAt(i, 7).toString());
            //total mas iva
            //total = total + Double.parseDouble(dtmData.getValueAt(i, 6).toString()) + Double.parseDouble(dtmData.getValueAt(i, 7).toString());
            //totalFact = total + Double.parseDouble(dtmData.getValueAt(i, 6).toString());
            impIVA = impIVA + iva;
            descuento = descuento + Double.parseDouble(dtmData.getValueAt(i, 8).toString());
            if(iva.equals(0.00))
            {    
                baseCero = baseCero + Double.parseDouble(dtmData.getValueAt(i, 6).toString()) - 
                        Double.parseDouble(dtmData.getValueAt(i, 8).toString());                             
            }
            else
            {
                baseIva = baseIva + Double.parseDouble(dtmData.getValueAt(i, 6).toString()) - 
                        Double.parseDouble(dtmData.getValueAt(i, 8).toString());             
            }
            //CALCULAR IRBP
            //verificarIRBP = objImpuestos.comprobarImpuesto(Integer.parseInt(dtmData.getValueAt(i, 0).toString()), "2");
            //if(verificarIRBP)
            if(dtmData.getValueAt(i, 11).toString().equals("S"))
            {
                irbp = irbp + Double.parseDouble(dtmData.getValueAt(i, 4).toString()) * imp_irbp;
            }
            //CALCULAR ICE
            //verificarICE = objImpuestos.comprobarImpuesto(Integer.parseInt(dtmData.getValueAt(i, 0).toString()), "3");
            //if(verificarICE)
            if(dtmData.getValueAt(i, 12).toString().equals("S"))
            {
                //PVP * cantidad
                subto = subto + Double.parseDouble(dtmData.getValueAt(i, 10).toString())* Double.parseDouble(dtmData.getValueAt(i, 4).toString());
                //irbp = irbp + Double.parseDouble(dtmData.getValueAt(i, 4).toString()) * objImpuestos.obtenerValorIRBP();
            }
        }
        baseIce = subto / ((1+(imp_iva/100)) * (1+(imp_ice/100)));
        txtBaseICE.setText("" + objUtils.redondearCincoDec(baseIce));
        ice = baseIce * (imp_ice/100);
        txtICE.setText("" + objUtils.redondearCincoDec(ice));
        // txtTarifaCero.setText(""+objUtils.redondear(total));
        txtDescuento.setText("" + objUtils.redondearCincoDec(descuento));
        //txtIVA.setText(""+objUtils.redondear(impIVA));
        //txtTotal.setText(""+objUtils.redondear(total-descuento+impIVA));        
        txtBaseTarifaIVA.setText("" + objUtils.redondearCincoDec(baseIva + ice));
        impIVA = (baseIva + ice)*(imp_iva/100);
        txtIVA.setText("" + objUtils.redondear(impIVA));
        txtBaseTarifaCero.setText("" + objUtils.redondearCincoDec(baseCero));
        txtIRBP.setText("" + objUtils.redondearCincoDec(irbp));
        
        //txtTotal.setText(""+objUtils.redondear(total - descuento + irbp));  
        System.out.print(baseIva + " + " + ice + " + " + baseCero + " - " + descuento + " + " + impIVA + " + " + irbp);
        //txtTotal.setText(""+objUtils.redondear(baseIva + ice + baseCero - descuento + impIVA + irbp));  
        txtTotal.setText(""+objUtils.redondear(baseIva + ice + baseCero + impIVA + irbp)); 
    }
    
    boolean busquedaProducto(int codProducto, double precioUnitario, double porcentajeDescuento)
    {
        boolean bandera = false;
        int maxData = dtmData.getRowCount();
        
        for(int i=0; i<maxData; i++)
        {
            Double valor2 = Double.parseDouble(dtmData.getValueAt(i, 5).toString());
            Double valor3 = Double.parseDouble(dtmData.getValueAt(i, 9).toString());
            if((dtmData.getValueAt(i, 0).equals(codProducto))&&
                    (valor2.equals(precioUnitario)&&
                            (valor3.equals(porcentajeDescuento))))
            {
                bandera = true;
            }           
        }
        return bandera;
    }
    /*public void tableChanged(javax.swing.event.TableModelEvent source)
    {
                 String msg="";
                 TableModel tabMod = (TableModel)source.getSource();
                  switch (source.getType())
                   {
                       case TableModelEvent.UPDATE:
                           msg="Table Value Updated for cell "+table.getSelectedRow()+","+table.getSelectedColumn()+"\nWhich is "+table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()).toString();
                           JOptionPane.showMessageDialog(null,msg,"Table Example",JOptionPane.INFORMATION_MESSAGE);
                       break;

                   }*
        //System.out.println("Editandose");

    }*/
    
    /*public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        System.out.println("rrR"+data);
        // Do something with the data...
    }*/
    
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox chkIvaIncluido;
    private javax.swing.JCheckBox chkNotaVenta;
    private javax.swing.JComboBox cmbTipoGasto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtAutorizacion;
    private javax.swing.JTextField txtBaseICE;
    private javax.swing.JTextField txtBaseTarifaCero;
    private javax.swing.JTextField txtBaseTarifaIVA;
    public static javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigoFactura;
    public static javax.swing.JTextField txtCodigoProducto;
    public static javax.swing.JTextField txtCodigoProveedor;
    private javax.swing.JTextField txtDescuento;
    public static javax.swing.JTextField txtDescuentoUnidad;
    private javax.swing.JTextField txtFacturaReferencia;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtICE;
    private javax.swing.JTextField txtIRBP;
    private javax.swing.JTextField txtIVA;
    public static javax.swing.JTextField txtNombreProducto;
    public static javax.swing.JTextField txtNombreProveedor;
    public static javax.swing.JTextField txtPrecio;
    public static javax.swing.JTextField txtPrecioVenta;
    public static javax.swing.JTextField txtPrecioVirgen;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
