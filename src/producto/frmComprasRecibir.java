/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmCompras.java
 *
 * Created on 17-feb-2012, 14:15:32
 */
package producto;

import clases.clsAuditoria;
import clases.clsCabecera;
import clases.clsComboBox;
import clases.clsCompras;
import clases.clsDetalle;
import clases.clsEmail;
import clases.clsImpuestos;
import clases.clsKardex;
import clases.clsParametros;
import clases.clsProducto;
import clases.clsProveedor;
import clases.clsProvincia;
import clases.clsUtils;
import clases.javaMail;
import index.main;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import stinventario.frmPrincipal;

/**
 *
 * @author CKaiser
 */
public class frmComprasRecibir extends javax.swing.JInternalFrame {
    clsProveedor objProveedor = new clsProveedor();
    clsProducto objProducto = new clsProducto();
    clsImpuestos objImpuestos = new clsImpuestos();
    clsCabecera objCabecera = new clsCabecera();
    clsDetalle objDetalle = new clsDetalle();
    clsUtils objUtils = new clsUtils();
    clsAuditoria objAuditoria = new clsAuditoria();
    clsCompras objCompras = new clsCompras();
    clsKardex objKardex = new clsKardex();
    clsProvincia objProvincia = new clsProvincia();
    clsParametros objParametros = new clsParametros();
    clsEmail objEmail = new clsEmail();
    
    int idCabeceraCompra = 0;
      
    MiModelo dtmData = new MiModelo();
    //NOMBRE USUARIO
    //public static int codigoProveedor = 0;
    //public static int codigoProducto = 0;
    //Double baseTarifaIva = 0.00;
    //Double baseTarifaCero = 0.00;
    int filas=0;
    /** Creates new form frmCompras */
    public frmComprasRecibir(int idCabecera) {
        initComponents();
        idCabeceraCompra = idCabecera;
        DecimalFormat df1 = new DecimalFormat("#,###0.00"); 
        txtCodigoFactura.setText(""+ idCabecera);
        ArrayList <clsCompras> dataCompra = objCompras.consultarDataCompraPendiente(idCabecera);
        
        txtCodigoProveedor.setText(dataCompra.get(0).getRUC());
        txtNombreProveedor.setText(dataCompra.get(0).getNombreProveedor());
        
        txtTarifaIVA.setText("" + dataCompra.get(0).getBaseTarifaIva());
        txtTarifaCero.setText("" + dataCompra.get(0).getBaseTarifaCero());
        txtDescuento.setText("" + dataCompra.get(0).getDescuento());
        txtIVA.setText("" + dataCompra.get(0).getIva());
        txtTotal.setText("" + df1.format(dataCompra.get(0).getTotal()));
        txtFacturaReferencia.setText(dataCompra.get(0).getFacturaReferencia());
        txtIRBP.setText("" + dataCompra.get(0).getIrbp());
        txtBaseICE.setText("" + dataCompra.get(0).getBaseIce());
        txtICE.setText("" + dataCompra.get(0).getIce());
        txtFecha.setText(dataCompra.get(0).getFecha());  
        txtFechaRegistro.setText(dataCompra.get(0).getFechaRegistro()); 
        txtAutorizacion.setText(dataCompra.get(0).getAutorizacion());
        
        if(dataCompra.get(0).getTipoDocumento().equals("V"))
        {
            this.chkNotaVenta.setSelected(true);
        }
        else
        {
            this.chkNotaVenta.setSelected(false);
        }
                
        lblUsuario.setText(dataCompra.get(0).getNombreElaborador());
        
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
        dtmData.addColumn("Descuento");
        //dtmData.addColumn("porcentaje_descuento");
        //objUtils.setOcultarColumnasJTable(this.tblData, new int[]{9});
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblData.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tblData.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tblData.getColumnModel().getColumn(6).setCellRenderer(tcr);
        tblData.getColumnModel().getColumn(7).setCellRenderer(tcr);
        tblData.getColumnModel().getColumn(8).setCellRenderer(tcr);
        
        
        
        ArrayList <clsDetalle> dataDetalle = objDetalle.consultarDataDetalleCompras(idCabecera);
        objUtils.vaciarTabla(dtmData);
        
        for(int i=0; i<dataDetalle.size(); i++)
        {
            
            Double cantidad = dataDetalle.get(i).getCantidad();
            Double precio = dataDetalle.get(i).getPrecio();
            Double descuento = dataDetalle.get(i).getDescuento();
            Double iva = dataDetalle.get(i).getIVA();
            
            Object[] nuevaFila = {dataDetalle.get(i).getIdProducto()
                        , i+1 
                        , dataDetalle.get(i).getCodigoProducto()
                        , dataDetalle.get(i).getDescripcionProducto()
                        , cantidad
                        , df1.format(objUtils.redondear(precio))
                        , df1.format(objUtils.redondear(dataDetalle.get(i).getCantidad()*dataDetalle.get(i).getPrecio()))
                        , iva
                        , descuento};
            dtmData.addRow(nuevaFila); 
        }
         //CARGAR TIPOS DE GASTOS
        ArrayList<clsComboBox> dataGasto = objProvincia.consultarGastos();        
        for(int i=0;i<dataGasto.size();i=i+1)
        {
            clsComboBox oItem = new clsComboBox(dataGasto.get(i).getCodigo(), dataGasto.get(i).getDescripcion());
            cmbTipoGasto.addItem(oItem);  
            if(Integer.parseInt(dataGasto.get(i).getCodigo())==(dataCompra.get(0).getIdCuenta()))
                {    
                    cmbTipoGasto.setSelectedItem(oItem);
                }    
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
        jLabel1 = new javax.swing.JLabel();
        txtCodigoProveedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCodigoFactura = new javax.swing.JTextField();
        txtFacturaReferencia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAutorizacion = new javax.swing.JTextField();
        cmbTipoGasto = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        txtTarifaIVA = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTarifaCero = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        chkNotaVenta = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtICE = new javax.swing.JTextField();
        txtBaseICE = new javax.swing.JTextField();
        txtIRBP = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtFechaRegistro = new javax.swing.JTextField();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmComprasRecibir.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setClosable(true);
        setIconifiable(true);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtCodigoProveedor.setEditable(false);
        txtCodigoProveedor.setBackground(resourceMap.getColor("txtCodigoProveedor.background")); // NOI18N
        txtCodigoProveedor.setText(resourceMap.getString("txtCodigoProveedor.text")); // NOI18N
        txtCodigoProveedor.setName("txtCodigoProveedor"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtNombreProveedor.setEditable(false);
        txtNombreProveedor.setText(resourceMap.getString("txtNombreProveedor.text")); // NOI18N
        txtNombreProveedor.setName("txtNombreProveedor"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        txtCodigoFactura.setEditable(false);
        txtCodigoFactura.setText(resourceMap.getString("txtCodigoFactura.text")); // NOI18N
        txtCodigoFactura.setName("txtCodigoFactura"); // NOI18N

        txtFacturaReferencia.setEditable(false);
        txtFacturaReferencia.setText(resourceMap.getString("txtFacturaReferencia.text")); // NOI18N
        txtFacturaReferencia.setName("txtFacturaReferencia"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        txtFecha.setEditable(false);
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtFecha.setText(resourceMap.getString("txtFecha.text")); // NOI18N
        txtFecha.setName("txtFecha"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtAutorizacion.setEditable(false);
        txtAutorizacion.setText(resourceMap.getString("txtAutorizacion.text")); // NOI18N
        txtAutorizacion.setName("txtAutorizacion"); // NOI18N

        cmbTipoGasto.setName("cmbTipoGasto"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreProveedor)
                    .addComponent(txtAutorizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(txtCodigoProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel10))
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoGasto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigoFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(txtFacturaReferencia))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFacturaReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 15, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtAutorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cmbTipoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblData.setModel(dtmData);
        tblData.setName("tblData"); // NOI18N
        tblData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblDataKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblData);

        jLabel21.setFont(resourceMap.getFont("jLabel21.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        txtTarifaIVA.setEditable(false);
        txtTarifaIVA.setFont(resourceMap.getFont("txtTarifaIVA.font")); // NOI18N
        txtTarifaIVA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTarifaIVA.setText(resourceMap.getString("txtTarifaIVA.text")); // NOI18N
        txtTarifaIVA.setName("txtTarifaIVA"); // NOI18N

        jLabel17.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        txtTarifaCero.setEditable(false);
        txtTarifaCero.setFont(resourceMap.getFont("txtTarifaCero.font")); // NOI18N
        txtTarifaCero.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTarifaCero.setText(resourceMap.getString("txtTarifaCero.text")); // NOI18N
        txtTarifaCero.setName("txtTarifaCero"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        txtDescuento.setEditable(false);
        txtDescuento.setFont(resourceMap.getFont("txtTarifaIVA.font")); // NOI18N
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

        jLabel12.setFont(resourceMap.getFont("txtIRBP.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setFont(resourceMap.getFont("txtIRBP.font")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel14.setFont(resourceMap.getFont("txtIRBP.font")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        txtICE.setEditable(false);
        txtICE.setFont(resourceMap.getFont("txtIRBP.font")); // NOI18N
        txtICE.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtICE.setText(resourceMap.getString("txtICE.text")); // NOI18N
        txtICE.setName("txtICE"); // NOI18N

        txtBaseICE.setEditable(false);
        txtBaseICE.setFont(resourceMap.getFont("txtIRBP.font")); // NOI18N
        txtBaseICE.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBaseICE.setText(resourceMap.getString("txtBaseICE.text")); // NOI18N
        txtBaseICE.setName("txtBaseICE"); // NOI18N

        txtIRBP.setEditable(false);
        txtIRBP.setFont(resourceMap.getFont("txtIRBP.font")); // NOI18N
        txtIRBP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIRBP.setText(resourceMap.getString("txtIRBP.text")); // NOI18N
        txtIRBP.setName("txtIRBP"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(chkNotaVenta)
                                .addGap(101, 101, 101)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtICE, 0, 1, Short.MAX_VALUE)
                                    .addComponent(txtBaseICE, 0, 1, Short.MAX_VALUE)
                                    .addComponent(txtIRBP, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18)))
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDescuento)
                                    .addComponent(txtTarifaCero)
                                    .addComponent(txtTarifaIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel16)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(lblUsuario)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(txtIVA, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(chkNotaVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsuario)
                            .addComponent(jLabel22)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIRBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBaseICE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtICE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(jLabel9))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTarifaIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTarifaCero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))))
                        .addContainerGap(23, Short.MAX_VALUE))))
        );

        btnGuardar.setIcon(resourceMap.getIcon("btnGuardar.icon")); // NOI18N
        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtFechaRegistro.setText(resourceMap.getString("txtFechaRegistro.text")); // NOI18N
        txtFechaRegistro.setName("txtFechaRegistro"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      int maxData = 0; 
      double cantidad = 0.00;
      try
        {             
             
            if(objCompras.recibirMercaderia(idCabeceraCompra, main.idUser))
            {
                maxData = dtmData.getRowCount();
                //int ultmFactura = objCabecera.obtenerUltimaFacturaCompras(main.idUser);
                //System.out.println("S: " + ultmFactura);
                for(int i=0; i<maxData; i++)
                {                       
                    //factura, idProducto
                    int idProducto = Integer.parseInt(dtmData.getValueAt(i, 0).toString());
                    cantidad = Double.parseDouble(""+dtmData.getValueAt(i, 4));
                    double precio = Double.parseDouble(""+dtmData.getValueAt(i, 5));
                    //String iva = ""+dtmData.getValueAt(i, 7);
                    //String descuento = ""+dtmData.getValueAt(i, 8);

                    //exito = objDetalle.insertarDetalleCompras(ultmFactura, idProducto, cantidad, precio, descuento, iva);
                    if(this.chkNotaVenta.isSelected())
                    {
                        //NOTA DE VENTA
                        objKardex.insertarKardex(idProducto, 
                                "COMPRA, ID NOTA DE VENTA: " + idCabeceraCompra + ", CODIGO REFERENCIA: " +txtFacturaReferencia.getText(), 
                                "+"+cantidad,
                                txtCodigoProveedor.getText(),
                                txtNombreProveedor.getText(),
                                "" + precio,
                                precio,
                                "INGRESO",
                                idCabeceraCompra,
                                1);
                    }
                    else
                    {
                        //FACTURA
                        objKardex.insertarKardex(idProducto, 
                                "COMPRA, ID FACTURA: " + idCabeceraCompra + ", CODIGO REFERENCIA: " +txtFacturaReferencia.getText(), 
                                "+"+cantidad,
                                txtCodigoProveedor.getText(),
                                txtNombreProveedor.getText(),
                                "" + precio,
                                precio,
                                "INGRESO",
                                idCabeceraCompra,
                                1);
                    } 
                    objProducto.aumentarStockAlmacen(idProducto, cantidad);
                    //COMPROBAR SI SE  LE SUMA IVA
                    boolean verificarIVA = objImpuestos.comprobarImpuesto(idProducto, "1");
                    if(verificarIVA)
                    {    
                        objProducto.actualizarCosto(idProducto, (precio*(1+(objImpuestos.obtenerPorcentajeIVA()/100))));
                    }
                    else
                    {
                        objProducto.actualizarCosto(idProducto, precio);
                    }                             
                }                        
                JOptionPane.showMessageDialog(this, "Compra recibida con éxito", "Atención!", JOptionPane.INFORMATION_MESSAGE);        
                objAuditoria.insertarAuditoria("frmComprasRecibir", "COMPRA RECIBIDA,  CODIGO: " + idCabeceraCompra + ", CON VALOR: " + txtTotal.getText(), "3");                 
                dispose();
                frmListCompras formulario = new frmListCompras();
                mostrarJInternalCentrado(formulario); 

            }//if
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());

        }
        
        //ENVIO DE CORREO
        String email_habilitado = objParametros.consultaValor("email_habilitado");
        if(email_habilitado.equals("1"))
        {
             try{
                  String texto = "EL USUARIO: " 
                  + main.nameUser+ ", CONFIRMO LA COMPRA: " + txtCodigoFactura.getText() + "</BR></BR>"
                          //+ "COMENTARIO: " + txtComentario.getText() + "</BR>"
                          + "<TABLE BORDER=\"1\">"
                                  + "<TR><TD>DESCRIPCION</TD><TD>VALOR</TD></TR>"
                                  + "<TR><TD>FECHA DE REGISTRO:</TD><TD>" + txtFechaRegistro.getText() + "</TD></TR>"
                                  + "<TR><TD>FECHA DE FACTURA:</TD><TD>" + txtFecha.getText() + "</TD></TR>"
                                  + "<TR><TD>PROVEEDOR:</TD><TD>" + txtNombreProveedor.getText() + "</TD></TR>"
                                  //+ "<TR><TD>CREDITO:</TD><TD>" + txtDatoCredito.getText() + "</TD></TR>"
                                  //+ "<TR><TD>DESCUENTO:</TD><TD>" + txtDescuento1.getText() + "</TD></TR>";                 
                                  + "<TR><TD>TOTAL:</TD><TD>" + txtTotal.getText() + "</TD></TR>"                  
                  //texto = texto  + "<TR><TD>VENDEDOR:</TD><TD>" + txtVendedor.getText() + "</TD></TR>"
                              + "</TABLE></BR></BR>"
                              + "<TABLE BORDER=\"1\">"
                              + "<TR><TD>PRODUCTO</TD><TD>CANTIDAD</TD></TR>" ;

                  String descripcion = "";
                  for(int i=0; i<maxData; i++)        
                  {     
                      descripcion = "" + dtmData.getValueAt(i, 3);
                      cantidad = Double.parseDouble(""+dtmData.getValueAt(i, 4));      
                      texto = texto + "<TR><TD>" + descripcion + "</TD><TD>" + cantidad + "</TD></TR>";
                  }  
                  texto = texto + "</TABLE>";

                  javaMail mail = new javaMail();
                  ArrayList<clsEmail> dataEmail = objEmail.consultarEmails("9");        
                  for(int i=0;i<dataEmail.size();i=i+1)
                  {
                      mail.send(dataEmail.get(i).getEmail(), "COMPRA - CONFIRMACION", texto);
                  }
              }
              catch(Exception e){
                  //e.printStackTrace();
                  JOptionPane.showMessageDialog(this, e.getMessage(), "Error al enviar por correo", JOptionPane.ERROR_MESSAGE);
              }
        }
        //ENVIO DE CORREO - FIN
}//GEN-LAST:event_btnGuardarActionPerformed

    public static void mostrarJInternalCentrado(javax.swing.JInternalFrame formulario)
    {
        Dimension desktopSize = frmPrincipal.jDesktopPane1.getSize();
        Dimension jInternalFrameSize = formulario.getSize();
        formulario.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
        (desktopSize.height- jInternalFrameSize.height)/2);

        frmPrincipal.jDesktopPane1.add(formulario);
        formulario.show(); 
    }
     
    
    
private void tblDataKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDataKeyTyped
    
   
}//GEN-LAST:event_tblDataKeyTyped
   
    
    public void obtenerDatosProveedor()
    {
        //txtCodigoProveedor.setEditable(false);
        //ArrayList<clsProveedor> dataProveedor = objProveedor.consultaDataProveedor(txtCodigoProveedor.getText().toString()); 
        
        //codigoProveedor = dataProveedor.get(0).getIdProveedor();
        //txtNombreProveedor.setText(dataProveedor.get(0).getNombre());
        
        //txtDireccion.setText(dataProveedor.get(0).getDireccion());
        //txtTelefono.setText(dataProveedor.get(0).getTelefono());

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
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox chkNotaVenta;
    private javax.swing.JComboBox cmbTipoGasto;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtAutorizacion;
    private javax.swing.JTextField txtBaseICE;
    private javax.swing.JTextField txtCodigoFactura;
    public static javax.swing.JTextField txtCodigoProveedor;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtFacturaReferencia;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFechaRegistro;
    private javax.swing.JTextField txtICE;
    private javax.swing.JTextField txtIRBP;
    private javax.swing.JTextField txtIVA;
    public static javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtTarifaCero;
    private javax.swing.JTextField txtTarifaIVA;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
