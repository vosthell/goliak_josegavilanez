/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmFacturar.java
 *
 * Created on 23-oct-2011, 15:34:09
 */
package pos;

import clases.clsCabecera;
import clases.clsCaja;
import clases.clsCliente;
import clases.clsComboBox;
import clases.clsCuota;
import clases.clsCupones;
import clases.clsDetalle;
import clases.clsEmail;
import clases.clsFacturero;
import clases.clsImpuestos;
import clases.clsKardex;
import clases.clsParametros;
import clases.clsPlazo;
import clases.clsPrecio;
import clases.clsProducto;
import clases.clsUtils;
import clases.javaMail;
import com.jidesoft.hints.ListDataIntelliHints;
import com.jidesoft.swing.SelectAllUtils;
import index.main;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

//import static pos.frmNotasEntregaConfirmar.txtNombreCliente;

/**
 *
 * @author Kaiser
 */
public class frmFacturar extends javax.swing.JDialog {
    clsCliente objCliente = new clsCliente();
    clsProducto objProducto = new clsProducto();
    clsPrecio objPrecio = new clsPrecio();
    clsUtils objUtils = new clsUtils();
    clsCaja objCaja = new clsCaja();
    clsCabecera objCabecera = new clsCabecera();
    clsDetalle objDetalle = new clsDetalle();
    clsCuota objCuota = new clsCuota();
    clsImpuestos objImpuestos = new clsImpuestos();
    clsFacturero objFacturero = new clsFacturero();
    clsPlazo objPlazo = new clsPlazo();
    clsKardex objKardex = new clsKardex();
    clsParametros objParametros = new clsParametros();
    clsEmail objEmail = new clsEmail(); 
    clsCupones objCupones = new clsCupones();
    
    MiModelo dtmData = new MiModelo();
    String idCajero = "";
    String idCajaAbierta = "";
    String factManual = "";
    Double baseTarifaIva = 0.00;
    Double baseTarifaCero = 0.00;
    public static int idUserCard = 0;
    public static int idUserCardCredito = 0;
    public static double vuelto = 0.00;
    int filas = 0;
    //CODIGO DEL CLIENTE SELECCIONADO
    public static int codigoCliente;
    //CODIGO DEL PRODUCTO SELECCIONADO 
    public static int codigoProducto;
    public static String controlExistencia;
    public static Double valorContado;
    String x_ruta = objParametros.consultaRutaImagenes();
    /** Creates new form frmFacturar */
    public frmFacturar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();  
        this.setTitle(objUtils.nombreSistema + "Facturar");
        
        txtCedula.requestFocus();
        
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
        //oculta
        dtmData.addColumn("Total-Descuento");
        //oculta
        dtmData.addColumn("Cantidad_sin_iva");
        //oculta
        dtmData.addColumn("costo");
        objUtils.setOcultarColumnasJTable(this.tblData, new int[]{9,10,11});        
        //ALINEAR COLUMNA
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblData.getColumnModel().getColumn(4).setCellRenderer(tcr); 
        tblData.getColumnModel().getColumn(5).setCellRenderer(tcr);    
        tblData.getColumnModel().getColumn(6).setCellRenderer(tcr);    
        tblData.getColumnModel().getColumn(7).setCellRenderer(tcr);    
        tblData.getColumnModel().getColumn(8).setCellRenderer(tcr);        
        
        List<String> dataCedula = objCliente.consultarCedulas(); 
        List<String> dataCodigo = objProducto.consultarCodigos(); 
        
        SelectAllUtils.install(txtCedula);
        ListDataIntelliHints intellihints = new ListDataIntelliHints(txtCedula, dataCedula);
        //((JList)intellihints.getDelegateComponent()).setFixedCellWidth(50);
        intellihints.setCaseSensitive(false);
        
        SelectAllUtils.install(txtCodigoProducto);
        ListDataIntelliHints intellihints2 = new ListDataIntelliHints(txtCodigoProducto, dataCodigo);
        //((JList)intellihints.getDelegateComponent()).setFixedCellWidth(50);
        intellihints2.setCaseSensitive(false);
        
        //OBTENER CAJERO
        String cajero = objCaja.obtenerCajero(main.idUser);
        lblCajero.setText(cajero);
        //NOMBRE USUARIO
        lblUsuario.setText(main.nameUser);
        //OBTENER IDCAJERO
        idCajero = objCaja.obtenerIdCajero(main.idUser);
        //OBTENER IDCAJAOPERACION ACTUAL, OSEA QUE  NO ESTA CERRADA
        idCajaAbierta = objCaja.obtenerCajaAbierta(main.idUser);
        
        //CARGAR CUOTAS
        ArrayList<clsComboBox> dataCuota = objCuota.consultarCuotasFacturas();        
        for(int i=0;i<dataCuota.size();i=i+1)
        {
            clsComboBox oItem = new clsComboBox(dataCuota.get(i).getCodigo(), dataCuota.get(i).getDescripcion());
            cmbCuota.addItem(oItem);            
        }
        
        //CARGAR PLazo
        ArrayList<clsComboBox> dataPlazo = objPlazo.consultarPlazoFacturas();        
        for(int i=0;i<dataPlazo.size();i=i+1)
        {
            clsComboBox oItem = new clsComboBox(dataPlazo.get(i).getCodigo(), dataPlazo.get(i).getDescripcion());
            cmbPlazo.addItem(oItem);            
        }
        
        //CARGAR VALOR DE LA FACTURA
        obtenerFacturaQueToca();
    }   
    
    public void obtenerFacturaQueToca()
    {
        //obtener si es manual
        System.out.println("llega a la 153");    
        factManual = objCaja.comprobarFacturacionManual(idCajaAbierta); 
        if(factManual.equals("S"))
        {            
            System.out.println("llega  ala 156");    
            int idFacturero = objCaja.obtenerIdFacturero(idCajaAbierta);
            //VERIFICAR SI SE ACABO FACTURERO
            if(objFacturero.consultarEstado(idFacturero).equals("A"))
            {
                txtFactura.setText(objFacturero.seleccionarFacturaActual(idFacturero));
            }
            else if(objFacturero.consultarEstado(idFacturero).equals("T"))
            {    
                JOptionPane.showMessageDialog(this, "Facturero ya terminado, utilice otro", "Atención!", JOptionPane.ERROR_MESSAGE); 
                dispose();
            }
        }
        else
        {
            System.out.println("llega  ala 170");              
                
            try{
                int ultmFact = objCabecera.obtenerUltimaFactura(idCajero);
                txtFactura.setText(""+(ultmFact+1));
            }
            catch(Exception ex)
            {
                System.out.println("error linea 176");
                int ultmFact = Integer.parseInt(objCabecera.obtenerUltimaFacturaSinCajero());
                txtFactura.setText(""+(ultmFact+1));
            }
        }
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnMostrarArturo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtComentario = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        txtFactura = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        chkSinRegistrar = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnMostrarProductos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbPrecio = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        chkCredito = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        cmbCuota = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        txtCuota = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtFechaCancelacion = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        cmbPlazo = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        txtTarifaCero = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtDescuentoUnidad = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTarifaIVA = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnDescuento = new javax.swing.JButton();
        txtCosto = new javax.swing.JTextField();
        lblCredito = new javax.swing.JLabel();
        btnImagen = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblCajero = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        setResizable(false);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmFacturar.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        btnMostrarArturo.setIcon(resourceMap.getIcon("btnMostrarArturo.icon")); // NOI18N
        btnMostrarArturo.setText(resourceMap.getString("btnMostrarArturo.text")); // NOI18N
        btnMostrarArturo.setName("btnMostrarArturo"); // NOI18N
        btnMostrarArturo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarArturoActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtCedula.setText(resourceMap.getString("txtCedula.text")); // NOI18N
        txtCedula.setName("txtCedula"); // NOI18N
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        txtNombreCliente.setEditable(false);
        txtNombreCliente.setText(resourceMap.getString("txtNombreCliente.text")); // NOI18N
        txtNombreCliente.setName("txtNombreCliente"); // NOI18N
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        btnBuscar.setText(resourceMap.getString("btnBuscar.text")); // NOI18N
        btnBuscar.setName("btnBuscar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtComentario.setColumns(20);
        txtComentario.setRows(5);
        txtComentario.setName("txtComentario"); // NOI18N
        jScrollPane2.setViewportView(txtComentario);

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        txtFactura.setEditable(false);
        txtFactura.setText(resourceMap.getString("txtFactura.text")); // NOI18N
        txtFactura.setName("txtFactura"); // NOI18N

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        chkSinRegistrar.setText(resourceMap.getString("chkSinRegistrar.text")); // NOI18N
        chkSinRegistrar.setName("chkSinRegistrar"); // NOI18N
        chkSinRegistrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkSinRegistrarItemStateChanged(evt);
            }
        });
        chkSinRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSinRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMostrarArturo)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkSinRegistrar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreCliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMostrarArturo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)
                            .addComponent(chkSinRegistrar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel2)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtCodigoProducto.setBackground(resourceMap.getColor("txtCodigoProducto.background")); // NOI18N
        txtCodigoProducto.setFont(resourceMap.getFont("txtCodigoProducto.font")); // NOI18N
        txtCodigoProducto.setForeground(resourceMap.getColor("txtCodigoProducto.foreground")); // NOI18N
        txtCodigoProducto.setText(resourceMap.getString("txtCodigoProducto.text")); // NOI18N
        txtCodigoProducto.setName("txtCodigoProducto"); // NOI18N
        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyTyped(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtCantidad.setBackground(resourceMap.getColor("txtCodigoProducto.background")); // NOI18N
        txtCantidad.setEditable(false);
        txtCantidad.setFont(resourceMap.getFont("txtCodigoProducto.font")); // NOI18N
        txtCantidad.setForeground(resourceMap.getColor("txtCodigoProducto.foreground")); // NOI18N
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

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtPrecio.setBackground(resourceMap.getColor("txtCodigoProducto.background")); // NOI18N
        txtPrecio.setEditable(false);
        txtPrecio.setFont(resourceMap.getFont("txtCodigoProducto.font")); // NOI18N
        txtPrecio.setForeground(resourceMap.getColor("txtCodigoProducto.foreground")); // NOI18N
        txtPrecio.setText(resourceMap.getString("txtPrecio.text")); // NOI18N
        txtPrecio.setName("txtPrecio"); // NOI18N

        btnAgregar.setIcon(resourceMap.getIcon("btnAgregar.icon")); // NOI18N
        btnAgregar.setText(resourceMap.getString("btnAgregar.text")); // NOI18N
        btnAgregar.setName("btnAgregar"); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        btnAgregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnAgregarKeyTyped(evt);
            }
        });

        btnMostrarProductos.setIcon(resourceMap.getIcon("btnMostrarProductos.icon")); // NOI18N
        btnMostrarProductos.setText(resourceMap.getString("btnMostrarProductos.text")); // NOI18N
        btnMostrarProductos.setName("btnMostrarProductos"); // NOI18N
        btnMostrarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarProductosActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblData.setModel(dtmData);
        tblData.setName("tblData"); // NOI18N
        tblData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblDataKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblData);

        txtNombreProducto.setEditable(false);
        txtNombreProducto.setText(resourceMap.getString("txtNombreProducto.text")); // NOI18N
        txtNombreProducto.setName("txtNombreProducto"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        cmbPrecio.setName("cmbPrecio"); // NOI18N
        cmbPrecio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPrecioItemStateChanged(evt);
            }
        });

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtTotal.setEditable(false);
        txtTotal.setFont(resourceMap.getFont("txtTotal.font")); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText(resourceMap.getString("txtTotal.text")); // NOI18N
        txtTotal.setDragEnabled(true);
        txtTotal.setName("txtTotal"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        txtStock.setEditable(false);
        txtStock.setText(resourceMap.getString("txtStock.text")); // NOI18N
        txtStock.setName("txtStock"); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setName("jPanel3"); // NOI18N

        chkCredito.setText(resourceMap.getString("chkCredito.text")); // NOI18N
        chkCredito.setEnabled(false);
        chkCredito.setName("chkCredito"); // NOI18N
        chkCredito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkCreditoItemStateChanged(evt);
            }
        });

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        cmbCuota.setEnabled(false);
        cmbCuota.setName("cmbCuota"); // NOI18N
        cmbCuota.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCuotaItemStateChanged(evt);
            }
        });

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        txtCuota.setEditable(false);
        txtCuota.setName("txtCuota"); // NOI18N
        txtCuota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuotaKeyReleased(evt);
            }
        });

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        txtEfectivo.setEditable(false);
        txtEfectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEfectivo.setText(resourceMap.getString("txtEfectivo.text")); // NOI18N
        txtEfectivo.setName("txtEfectivo"); // NOI18N
        txtEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyReleased(evt);
            }
        });

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        txtSaldo.setEditable(false);
        txtSaldo.setName("txtSaldo"); // NOI18N

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        txtFechaCancelacion.setEditable(false);
        txtFechaCancelacion.setForeground(resourceMap.getColor("txtFechaCancelacion.foreground")); // NOI18N
        txtFechaCancelacion.setText(resourceMap.getString("txtFechaCancelacion.text")); // NOI18N
        txtFechaCancelacion.setDisabledTextColor(resourceMap.getColor("txtFechaCancelacion.disabledTextColor")); // NOI18N
        txtFechaCancelacion.setName("txtFechaCancelacion"); // NOI18N

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        cmbPlazo.setEnabled(false);
        cmbPlazo.setName("cmbPlazo"); // NOI18N
        cmbPlazo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPlazoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkCredito)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel25)
                        .addGap(5, 5, 5)
                        .addComponent(cmbPlazo, 0, 158, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(5, 5, 5))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbCuota, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel24)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtFechaCancelacion, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSaldo)
                    .addComponent(txtEfectivo, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)
                        .addComponent(cmbPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkCredito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cmbCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFechaCancelacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setFont(resourceMap.getFont("jLabel16.font")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        txtTarifaCero.setEditable(false);
        txtTarifaCero.setFont(resourceMap.getFont("txtTarifaCero.font")); // NOI18N
        txtTarifaCero.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTarifaCero.setText(resourceMap.getString("txtTarifaCero.text")); // NOI18N
        txtTarifaCero.setName("txtTarifaCero"); // NOI18N

        txtIVA.setEditable(false);
        txtIVA.setFont(resourceMap.getFont("txtIVA.font")); // NOI18N
        txtIVA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIVA.setText(resourceMap.getString("txtIVA.text")); // NOI18N
        txtIVA.setName("txtIVA"); // NOI18N

        jLabel17.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        txtDescuento.setEditable(false);
        txtDescuento.setFont(resourceMap.getFont("txtDescuento.font")); // NOI18N
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDescuento.setText(resourceMap.getString("txtDescuento.text")); // NOI18N
        txtDescuento.setName("txtDescuento"); // NOI18N
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyPressed(evt);
            }
        });

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        txtDescuentoUnidad.setBackground(resourceMap.getColor("txtDescuentoUnidad.background")); // NOI18N
        txtDescuentoUnidad.setEditable(false);
        txtDescuentoUnidad.setFont(resourceMap.getFont("txtDescuentoUnidad.font")); // NOI18N
        txtDescuentoUnidad.setText(resourceMap.getString("txtDescuentoUnidad.text")); // NOI18N
        txtDescuentoUnidad.setName("txtDescuentoUnidad"); // NOI18N
        txtDescuentoUnidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescuentoUnidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoUnidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoUnidadKeyTyped(evt);
            }
        });

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        txtTarifaIVA.setEditable(false);
        txtTarifaIVA.setFont(resourceMap.getFont("txtTarifaIVA.font")); // NOI18N
        txtTarifaIVA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTarifaIVA.setText(resourceMap.getString("txtTarifaIVA.text")); // NOI18N
        txtTarifaIVA.setName("txtTarifaIVA"); // NOI18N

        jLabel21.setFont(resourceMap.getFont("jLabel21.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        btnDescuento.setText(resourceMap.getString("btnDescuento.text")); // NOI18N
        btnDescuento.setEnabled(false);
        btnDescuento.setName("btnDescuento"); // NOI18N
        btnDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescuentoActionPerformed(evt);
            }
        });

        txtCosto.setEditable(false);
        txtCosto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCosto.setText(resourceMap.getString("txtCosto.text")); // NOI18N
        txtCosto.setName("txtCosto"); // NOI18N

        lblCredito.setForeground(resourceMap.getColor("lblCredito.foreground")); // NOI18N
        lblCredito.setText(resourceMap.getString("lblCredito.text")); // NOI18N
        lblCredito.setName("lblCredito"); // NOI18N

        btnImagen.setIcon(resourceMap.getIcon("btnImagen.icon")); // NOI18N
        btnImagen.setText(resourceMap.getString("btnImagen.text")); // NOI18N
        btnImagen.setName("btnImagen"); // NOI18N
        btnImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnMostrarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(14, 14, 14))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel19))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStock)
                            .addComponent(txtDescuentoUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel21)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal)
                            .addComponent(txtTarifaIVA, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTarifaCero, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtIVA, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addGap(6, 6, 6)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19)
                                .addComponent(txtDescuentoUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)))
                        .addComponent(btnMostrarProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnImagen)
                            .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCredito))
                    .addGroup(jPanel2Layout.createSequentialGroup()
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
                            .addComponent(jLabel18)
                            .addComponent(btnDescuento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGap(28, 28, 28))
        );

        btnImprimir.setIcon(resourceMap.getIcon("btnImprimir.icon")); // NOI18N
        btnImprimir.setText(resourceMap.getString("btnImprimir.text")); // NOI18N
        btnImprimir.setName("btnImprimir"); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(resourceMap.getIcon("btnGuardar.icon")); // NOI18N
        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        lblCajero.setFont(resourceMap.getFont("lblCajero.font")); // NOI18N
        lblCajero.setText(resourceMap.getString("lblCajero.text")); // NOI18N
        lblCajero.setName("lblCajero"); // NOI18N

        jLabel22.setFont(resourceMap.getFont("jLabel23.font")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        lblUsuario.setFont(resourceMap.getFont("lblUsuario.font")); // NOI18N
        lblUsuario.setText(resourceMap.getString("lblUsuario.text")); // NOI18N
        lblUsuario.setName("lblUsuario"); // NOI18N

        lblDescuento.setForeground(resourceMap.getColor("lblDescuento.foreground")); // NOI18N
        lblDescuento.setText(resourceMap.getString("lblDescuento.text")); // NOI18N
        lblDescuento.setName("lblDescuento"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCajero)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(lblUsuario)
                        .addGap(39, 39, 39)
                        .addComponent(lblDescuento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImprimir))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnImprimir)
                    .addComponent(jLabel9)
                    .addComponent(lblCajero)
                    .addComponent(jLabel22)
                    .addComponent(lblUsuario)
                    .addComponent(lblDescuento))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    //pongo descuento en cero por si acaso
    //txt
    cargarDataCliente();  
}//GEN-LAST:event_btnBuscarActionPerformed

    public void cargarDataCliente()
    {
        ArrayList<clsCliente> dataCliente = objCliente.consultarDataCliente(txtCedula.getText().toString());
        if(dataCliente.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Cédula no existe!!!", "Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            //vaciarDatos();
            txtCedula.setText(dataCliente.get(0).getCedula());
            codigoCliente = dataCliente.get(0).getCodigo(); 
            //cliente sin registrar
            this.txtNombreCliente.setText(dataCliente.get(0).getNameCompleto());
            //CONSUMIDOR FINAL
            if((codigoCliente!=2546)&&(codigoCliente!=514)&&(codigoCliente!=520)&&(codigoCliente!=2775))
            {
                btnDescuento.setEnabled(true);
                chkCredito.setEnabled(true);
            }
            else
            {
                btnDescuento.setEnabled(false);
                chkCredito.setEnabled(false);
            }
            txtCodigoProducto.requestFocus();
        }
    }
     
    public void vaciarDatos()
    {
        /*VACIAR DATOS*/
        txtNombreProducto.setText("");        
        txtCodigoProducto.setText("");
        txtCantidad.setText("");
        txtCantidad.setEditable(false);
        txtPrecio.setText("");
        txtPrecio.setEditable(false);
        codigoProducto = 0;
        txtCedula.setText("");
        txtNombreCliente.setText("");
        txtTarifaCero.setText("0.00");
        txtTarifaIVA.setText("0.00");
        txtDescuento.setText("0.00");
        txtIVA.setText("0.00");
        txtTotal.setText("0.00");
        txtComentario.setText("");
        chkCredito.setSelected(false);   
        chkSinRegistrar.setSelected(false);   
        cmbPrecio.removeAllItems();
        objUtils.vaciarTabla(dtmData); 
        txtDescuento.setEditable(false);
        btnDescuento.setEnabled(false);
        idUserCard = 0;
    }
private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
    agregarProducto();
    txtCodigoProducto.requestFocus();    
}//GEN-LAST:event_btnAgregarActionPerformed
    
    void agregarProducto()
    {
        if(txtDescuentoUnidad.getText().equals(""))
            txtDescuentoUnidad.setText("0");
        if(txtCodigoProducto.getText().equals("")||txtNombreProducto.getText().equals("")||txtCantidad.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Verificar datos", "Atención!", JOptionPane.ERROR_MESSAGE);    
        }
        else
        {
            String codeProducto = this.txtCodigoProducto.getText();
            String descripcion = this.txtNombreProducto.getText();
            double cantidad = Double.parseDouble(txtCantidad.getText());

            //VERIFICAR SI EL PRODUCTO TIENE VERIFICADO EL STOCK
            //boolean verificado = objProducto.comprobarVerificado(codigoProducto); 
            //if(verificado)
            //{
                Double minuendo = Double.parseDouble(txtStock.getText());
                Double sustraendo = Double.parseDouble(txtCantidad.getText());
                Double diferencia = minuendo - sustraendo;
                //System.out.println("ddif:" +diferencia + ", codigo Prod: " + codigoProducto);
                if((diferencia<0)&&(controlExistencia.equals("N")))
                {
                     JOptionPane.showMessageDialog(this, "Este producto no permite vender sin stock", "Atención!", JOptionPane.ERROR_MESSAGE);    
                }
                else
                {
                    /*buscar si esta facturado un producto igual,
                    * y sumar su cantidad
                    */        
                    boolean encontrado = busquedaProducto(codigoProducto);
                    //System.out.println("Encontrado:"+encontrado);
                    if(encontrado)
                    {
                        //AUMENTO EN 1 LA CANTIDAD DEL ARTICULO REPETIDO
                        agregarCantidad(codigoProducto, cantidad);
                    }    
                    else
                    {    
                        double iva = 0.00;
                        double descuento=0.00;
                        double subTotalParaCalcularIva=0.00;
                        double precio = Double.parseDouble(txtPrecio.getText().toString());
                        double subTotal = cantidad*precio; 
                        descuento = subTotal*Double.parseDouble(txtDescuentoUnidad.getText().toString())/100;
                        subTotalParaCalcularIva = subTotal - descuento;
                        
                        //COMPROBAR SI SE  LE SUMA IVA
                        boolean verificarIVA = objImpuestos.comprobarImpuesto(codigoProducto, "1");
                        baseTarifaIva = 0.00;
                        if(verificarIVA)
                        {    
                            iva = subTotalParaCalcularIva-(subTotalParaCalcularIva/(1+(objImpuestos.obtenerPorcentajeIVA()/100))); 
                            baseTarifaIva = baseTarifaIva+subTotalParaCalcularIva/(1+(objImpuestos.obtenerPorcentajeIVA()/100));
                            //txtTarifaIVA.setText(""+objUtils.redondear(baseTarifaIva));
                        }
                        else
                        {   
                            iva = 0.00;
                            baseTarifaCero = baseTarifaCero + subTotalParaCalcularIva;
                            //baseTarifaCero = subTotalParaCalcularIva;
                            //txtTarifaCero.setText(""+objUtils.redondear(baseTarifaCero));                            
                        }
                        /*Object[] nuevaFila = {codigoProducto, 0, codeProducto, descripcion, cantidad, 
                            objUtils.redondear(precio), objUtils.redondear(subTotal), 
                            objUtils.redondear(iva), descuento, subTotalParaCalcularIva, 
                            baseTarifaIva, txtCosto.getText()};*/
                        Object[] nuevaFila = {codigoProducto, 0, codeProducto, descripcion, cantidad, 
                            objUtils.redondearCuatroDec(precio), objUtils.redondearCuatroDec(subTotal), 
                            objUtils.redondearCuatroDec(iva), descuento, subTotalParaCalcularIva, 
                            baseTarifaIva, txtCosto.getText()};
                        dtmData.addRow(nuevaFila);
                        filas++;
                    }
                    objUtils.enumerarFilas(dtmData, 1);
                    calcularTotal();

                    /*VACIAR DATOS*/
                    txtNombreProducto.setText("");
                    cmbPrecio.removeAllItems();
                    txtCodigoProducto.setText("");
                    txtCantidad.setText("");
                    txtCantidad.setEditable(false);
                    txtPrecio.setText("");
                    txtStock.setText("");
                    txtPrecio.setEditable(false);
                    codigoProducto=0;
                    txtDescuentoUnidad.setText("");

                    txtCodigoProducto.getFocusListeners();
                }
            //}
            //else
            //    JOptionPane.showMessageDialog(this, "No se ha verificado el STOCK del producto", "Atención!", JOptionPane.ERROR_MESSAGE); 
        }
    }
   
    
    boolean busquedaProducto(int codProducto)
    {
        boolean bandera=false;
        int maxData = dtmData.getRowCount();
        
        for(int i=0; i<maxData; i++)
        {
            //System.out.println("1: "+dtmData.getValueAt(i, 2)+ "2:"+codProducto);
            if(dtmData.getValueAt(i, 0).equals(codProducto))
            {
                bandera = true;
            }           
        }
        return bandera;
    }
    
    private boolean registrarVenta()
    {
        boolean p_exito = false;
        boolean exito = false;
        int idProducto = 0;
        String cantidad = "";
        String precio = "";   
        String descuento = "";
        String iva = "";
        Double costo = 0.00;
        
        String descuentoF = txtDescuento.getText().toString();
        String ivaF = txtIVA.getText().toString();
        
        if(chkCredito.isSelected())        
        {                
            exito = objCabecera.insertarRegistroCredito(codigoCliente, main.idUser, idCajero, 
                    txtTotal.getText(), main.idEmpresa, 
                    this.idCajaAbierta, txtComentario.getText(), 
                    txtSaldo.getText(), txtEfectivo.getText(), 
                    descuentoF, ivaF, txtFactura.getText(), 
                    txtTarifaIVA.getText(), txtTarifaCero.getText(),
                    idUserCard,
                    idUserCardCredito);   
            String sistema_cupon = objParametros.consultaValor("cupones_habilitado");
            if(sistema_cupon.equals("1"))
                imprimir_cupones(txtEfectivo.getText(), Integer.parseInt(txtFactura.getText()));
        }
        else
        {
            //VENTA AL CONTADO
            if(chkSinRegistrar.isSelected())
            {
                exito = objCabecera.insertarRegistroSinRegistrar(2775, main.idUser, 
                    idCajero, txtTotal.getText(), main.idEmpresa, 
                    this.idCajaAbierta, descuentoF, ivaF, txtFactura.getText(), 
                    txtTarifaIVA.getText(), txtTarifaCero.getText(),
                    idUserCard, txtCedula.getText(), txtNombreCliente.getText());
            }
            else
            {
                exito = objCabecera.insertarRegistro(codigoCliente, main.idUser, 
                    idCajero, txtTotal.getText(), main.idEmpresa, 
                    this.idCajaAbierta, descuentoF, ivaF, txtFactura.getText(), 
                    txtTarifaIVA.getText(), txtTarifaCero.getText(),
                    idUserCard);
                
                String sistema_cupon = objParametros.consultaValor("cupones_habilitado");
                if(sistema_cupon.equals("1"))
                    imprimir_cupones(txtTotal.getText(),  Integer.parseInt(txtFactura.getText()));
            }
        }

        if(exito)
        {
            try
            {                          
                int maxData = dtmData.getRowCount();
                int ultmFactura = objCabecera.obtenerUltimaFactura(idCajero);
                //System.out.println("S: " + ultmFactura);
                if(this.chkCredito.isSelected())        
                {
                    clsComboBox objCuotaSelect = (clsComboBox)cmbCuota.getSelectedItem();
                    clsComboBox objPlazoSelect = (clsComboBox)cmbPlazo.getSelectedItem();
                    
                    objCabecera.insertarCtaCobrar(ultmFactura, txtComentario.getText(), 
                                                txtSaldo.getText(), txtFechaCancelacion.getText(),
                                                objPlazoSelect.getCodigo());
                    
                    objCabecera.insertarValorCuota(ultmFactura, objCuotaSelect.getCodigo(), 
                                                txtCuota.getText());
                }
                for(int i=0; i<maxData; i++)
                {                       
                    //factura, idProducto
                    idProducto = Integer.parseInt(dtmData.getValueAt(i, 0).toString());
                    cantidad = ""+dtmData.getValueAt(i, 4);
                    precio = ""+dtmData.getValueAt(i, 5);                    
                    iva = ""+dtmData.getValueAt(i, 7);
                    descuento = ""+dtmData.getValueAt(i, 8);
                    costo = Double.parseDouble(""+dtmData.getValueAt(i, 11));
                    
                    exito = objDetalle.insertarDetalleFactura(ultmFactura, idProducto, cantidad, 
                                            precio, 
                                            descuento, 
                                            iva,
                                            costo);
                    objKardex.insertarKardex(idProducto, "FACTURACION, ID FACTURA:"+ultmFactura, "-"+cantidad, 
                                                txtCedula.getText(),
                                                txtNombreCliente.getText(),
                                                precio,
                                                costo,
                                                "EGRESO",
                                                ultmFactura,
                                                1);
                    objProducto.disminuirStockAlmacen(idProducto, cantidad);
                }          
                //SI ES MANUAL MODIFICO EL PARAMETRO PRIMERA VEZ A "N"
                factManual = objCaja.comprobarFacturacionManual(idCajaAbierta); 
                if(factManual.equals("S"))
                {
                    //obtener si es la priemra  vez
                    //String primeraFactManual = objCaja.comprobarPrimeraFacturacionManual(idCajaAbierta); 
                    //if(primeraFactManual.equals("S"))
                    //{
                        //MODIFICAR A "N"
                        //objCaja.modificarPrimeraVezFactManual(idCajaAbierta);
                    //}
                    int idFacturero = objCaja.obtenerIdFacturero(idCajaAbierta);
                    //actualizar facturero
                    objFacturero.actualizarFacturero(idFacturero);
                    objFacturero.modificarEstado(idFacturero);
                }
                JOptionPane.showMessageDialog(this, "Factura guardada con éxito", "Atención!", JOptionPane.INFORMATION_MESSAGE);        
                vaciarDatos();
                //System.out.println("LLego a la 1155");
                p_exito = true;
                obtenerFacturaQueToca();
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
        return p_exito;     
    }
    
    public void imprimir_cupones(String total, int id_documento)
    {
        //CUPONES
        //REGISTRAR CUPONES
        Double valor = Double.parseDouble(total);
        double numero_cupones_double = valor / Double.parseDouble(objParametros.consultaValor("valor_minimo_cupones"));
        int numero_cupones = (int)numero_cupones_double;

        for (int i=0; i<numero_cupones; i++ )
        {
            objCupones.insertarCupon(i+1,codigoCliente, 
                    id_documento, "FACTURA");
        }
        //IMPRIMIR CUPONES
        ArrayList<clsCupones> dataCupones = objCupones.consultarDataCupones(id_documento); 
        int maxData = dataCupones.size();

        //dataCompras.get(i).getEstadoTramite()
       FileWriter fichero = null;
       PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(objUtils.HostSystem + "file00003.txt");

            pw = new PrintWriter(fichero);
            for(int i=0; i<maxData; i++)
            {
                pw.println(objParametros.consultaValor("print_factura_linea1"));
                pw.println("CODIGO CUPON: " + dataCupones.get(i).getIdCupones());
                pw.println("TIPO DOC: " + dataCupones.get(i).getTipoDocumento());
                pw.println("SERIE DOC: " + dataCupones.get(i).getIdDocumento());
                pw.println("VALOR: $ " + valor);
                pw.println("FECHA: " + dataCupones.get(i).getFechaRegistro().substring(1, 16));

                String detalle = "";
                if(dataCupones.get(i).getNameCompleto().length()>25)
                    detalle = dataCupones.get(i).getNameCompleto().substring(0, 25);
                else
                {
                    detalle = dataCupones.get(i).getNameCompleto();
                    do{
                        detalle = detalle + " ";
                    }while(detalle.length()<25);
                }
                pw.println("CEDULA: " + dataCupones.get(i).getCedula());
                pw.println("CLIENTE: " + detalle);
                pw.println(dataCupones.get(i).getNumeroCupon() + " de " + maxData);
                pw.println("");
                pw.println("");
                pw.println("");
                pw.println("----------------------------------------");
                pw.println("");
                pw.println("");
            }
            Runtime aplicacion = Runtime.getRuntime(); 
            aplicacion.exec("cmd.exe /K "+ objUtils.HostSystem + objUtils.archivoImprimirCupones);           

        }
        catch (Exception e) 
        {
            System.out.println(e.toString());
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
              System.out.println(e2.toString());
              //e2.printStackTrace();
           }
        }
         //CUPONES -CERRADO  
    }
    
    boolean agregarCantidad(int codProducto, double cantidad)
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
                if(dtmData.getValueAt(i,0).equals(codProducto))
                {
                    //AGREGO CANTIDAD
                    dtmData.setValueAt(Double.parseDouble("" + dtmData.getValueAt(i,4)) + cantidad, i, 4);
                    //CANTIDAD
                    Double mult1 = Double.parseDouble("" + dtmData.getValueAt(i,4));
                    //PRECIO
                    Double mult2 = Double.parseDouble("" + dtmData.getValueAt(i,5));
                    //SUBTOTAL
                    subTotal = mult1 * mult2;
                    dtmData.setValueAt(subTotal, i, 6);
                    //CALCULAR DESCUENTO
                    descuento = subTotal * Double.parseDouble(txtDescuentoUnidad.getText().toString())/100;
                    subTotalParaCalcularIva = subTotal - descuento;
                    
                    //COMPROBAR SI SE  LE SUMA IVA
                    boolean verificarIVA = objImpuestos.comprobarImpuesto(codigoProducto, "1");                    
                    baseTarifaIva= 0.00;
                    if(verificarIVA)
                    {    
                        iva = subTotalParaCalcularIva-(subTotalParaCalcularIva/(1+(objImpuestos.obtenerPorcentajeIVA()/100))); 
                        //baseTarifaIva = baseTarifaIva+(subTotalParaCalcularIva/1.12);
                        baseTarifaIva = subTotalParaCalcularIva/(1+(objImpuestos.obtenerPorcentajeIVA()/100));
                        //txtTarifaIVA.setText(""+objUtils.redondear(baseTarifaIva));
                        dtmData.setValueAt(baseTarifaIva, i, 10);
                    }
                    else
                    {   
                        iva = 0.00;
                        dtmData.setValueAt(subTotalParaCalcularIva, i, 9);
                        //2012-03-03 baseTarifaCero = baseTarifaCero + subTotalParaCalcularIva;
                        //baseTarifaCero = subTotalParaCalcularIva;
                        //txtTarifaCero.setText("" + objUtils.redondear(baseTarifaCero));                          
                    }                 
                    dtmData.setValueAt(objUtils.redondear(iva), i, 7);
                    dtmData.setValueAt(objUtils.redondear(descuento), i, 8);
                    calcularTotal();
                    /*double iva = 0.00;
                        double descuento=0.00;
                        double subTotalParaCalcularIva=0.00;
                        double precio = Double.parseDouble(txtPrecio.getText().toString());
                        double subTotal = cantidad*precio; 
                        descuento = subTotal*Double.parseDouble(txtDescuentoUnidad.getText().toString())/100;
                        subTotalParaCalcularIva = subTotal - descuento;*/
                }           
            }
            baseTarifaCero = 0.00;
            for(int i=0; i<maxData; i++)
            {
                
                boolean verificarIVA = objImpuestos.comprobarImpuesto(Integer.parseInt(""+dtmData.getValueAt(i,0)), "1"); 
                System.out.println(dtmData.getValueAt(i,0) + " " + verificarIVA);
                if(!verificarIVA)
                {    
                    baseTarifaCero = baseTarifaCero + Double.parseDouble(""+dtmData.getValueAt(i,9));                 
                }               
            }
            txtTarifaCero.setText("" + objUtils.redondear(baseTarifaCero)); 
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    void calcularTotalCredito()
    {
        
    }
    
    void calcularTotal()
    {
        Double total = 0.00;
        Double impIVA = 0.00;
        Double descuento = 0.00;
        baseTarifaCero = 0.00;
        baseTarifaIva = 0.00;
        int maxData = dtmData.getRowCount();
        for(int i=0; i<maxData; i++)
        {
            total = total + Double.parseDouble(dtmData.getValueAt(i, 6).toString());
            impIVA = impIVA + Double.parseDouble(dtmData.getValueAt(i, 7).toString());
            descuento = descuento + Double.parseDouble(dtmData.getValueAt(i, 8).toString());
            
            boolean verificarIVA = objImpuestos.comprobarImpuesto(Integer.parseInt(""+dtmData.getValueAt(i,0)), "1"); 
            if(!verificarIVA)
                baseTarifaCero = baseTarifaCero + Double.parseDouble(""+dtmData.getValueAt(i,9));
            else
                baseTarifaIva = baseTarifaIva + Double.parseDouble(""+dtmData.getValueAt(i,10));
        }
        txtTarifaCero.setText("" + objUtils.redondear(baseTarifaCero)); 
        txtTarifaIVA.setText("" + objUtils.redondear(baseTarifaIva)); 
       // txtTarifaCero.setText(""+objUtils.redondear(total));
        txtDescuento.setText(""+objUtils.redondear(descuento));
        txtIVA.setText(""+objUtils.redondear(impIVA));
        //txtTotal.setText(""+objUtils.redondear(total-descuento+impIVA));   
        txtTotal.setText(""+objUtils.redondear(total-descuento));  
        //baseTarifaCero = 0.00;
        
    }

    private void btnMostrarArturoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarArturoActionPerformed
        txtCedula.setText("");
        txtNombreCliente.setText("");
        btnDescuento.setEnabled(false);
        
        frmListClientes ventana = new frmListClientes(this, true, "1");
        //new inventariopdf.JDEscoger(this, true);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }//GEN-LAST:event_btnMostrarArturoActionPerformed

    private void btnMostrarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarProductosActionPerformed
        frmListProductos ventana = new frmListProductos(this, true, "1", 0);        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }//GEN-LAST:event_btnMostrarProductosActionPerformed

private void txtCodigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyTyped
       if(evt.getKeyChar() == KeyEvent.VK_ENTER){           
            String codeProducto = txtCodigoProducto.getText().toString();
            ArrayList<clsProducto> dataProducto = objProducto.consultarDataProductoPorCodigo(codeProducto); 
            if(dataProducto.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Código incorrecto del producto", "Atención!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                codigoProducto = Integer.parseInt(dataProducto.get(0).getIdItems());
                txtNombreProducto.setText(dataProducto.get(0).getDesItem().trim());
                txtStock.setText(""+dataProducto.get(0).getCantStock());
                controlExistencia = dataProducto.get(0).getControlExistencia();
                txtDescuentoUnidad.setText(dataProducto.get(0).getDescuento().toString());
                txtCosto.setText("" + dataProducto.get(0).getCosto());
                //CARGAR PRECIOS DEL PRODUCTO
                cmbPrecio.removeAllItems();                
                ArrayList<clsComboBox> dataPrecios = objPrecio.consultarPrecios(""+codigoProducto);        
                if(dataPrecios.isEmpty())
                {}
                else
                {
                    for(int i=0;i<dataPrecios.size();i=i+1)
                    {
                        clsComboBox oItem = new clsComboBox(dataPrecios.get(i).getDescripcion(), dataPrecios.get(i).getCodigo() + " - " + dataPrecios.get(i).getDescripcion());
                        cmbPrecio.addItem(oItem);            
                    }
                }
                txtCantidad.setEditable(true);
                txtCantidad.requestFocus();
            }  
       }
}//GEN-LAST:event_txtCodigoProductoKeyTyped

private void cmbPrecioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPrecioItemStateChanged
    if(cmbPrecio.getItemCount()!=0)
    {
        clsComboBox objPrecioSelect = (clsComboBox)cmbPrecio.getSelectedItem();
        Double primerPrecio = Double.parseDouble(objPrecioSelect.getCodigo());
        //txtPrecio.setText(""+objUtils.redondear(primerPrecio));
        txtPrecio.setText(""+primerPrecio);
    }
}//GEN-LAST:event_cmbPrecioItemStateChanged

private void tblDataKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDataKeyTyped
    DefaultTableModel dtm = (DefaultTableModel) tblData.getModel();  
    if(evt.getKeyChar() == KeyEvent.VK_DELETE){
        dtm.removeRow(tblData.getSelectedRow()); 
    }    
    objUtils.enumerarFilas(dtm, 1);
    this.txtTarifaCero.setText("0.00");
    this.txtTarifaIVA.setText("0.00");
    this.calcularTotal();
}//GEN-LAST:event_tblDataKeyTyped

private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        String x = txtCantidad.getText();
        if(!objUtils.isDouble(x)){
            txtCantidad.setText("");
        }  
}//GEN-LAST:event_txtCantidadKeyReleased

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
   facturar();
}//GEN-LAST:event_btnGuardarActionPerformed
    
    public boolean facturar()
    {
        boolean exito=false;
        //Comprobar si descuento es mayor al porcentaje indicado en la base de datos    
        Double total = Double.parseDouble(txtTotal.getText());
        Double descuento = Double.parseDouble(txtDescuento.getText());
        Double porcentajeDescuento = objParametros.consultaPorcentajeDescuentoMaximoFactura();

        Double porcentajeValor = total * porcentajeDescuento/100;
       
        if(descuento>porcentajeValor)
        {    
            JOptionPane.showMessageDialog(this, "El valor del descuento es mayor al permitido (" + porcentajeDescuento + ")", "Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            
            int contRows = tblData.getRowCount();
            if(this.chkCredito.isSelected())        
            {
                if(txtNombreCliente.getText().equals("")||(contRows==0)||
                        txtCuota.getText().equals("")||txtSaldo.getText().equals("")||
                        txtFactura.getText().equals("")||txtComentario.getText().equals(""))
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
            }
            else
            {
               
                    if(txtNombreCliente.getText().equals("")||(contRows==0)||txtFactura.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(this, "Ingrese correctamente la información", "Atención!", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        if(descuento>0)
                        {    
                            if(txtComentario.getText().equals(""))
                            {    
                                JOptionPane.showMessageDialog(this, "Esta factura tiene descuento, se necesita registrar un comentario", "Atención!", JOptionPane.ERROR_MESSAGE);
                            }
                            else
                            {
                                if(registrarVenta())
                                    exito = true;
                                else
                                    exito = false; 
                            }
                        }
                        else
                        {
                             if(registrarVenta())
                                    exito = true;
                                else
                                    exito = false; 
                        }
                    }
                 
               
            }
        }
        return exito;
    }
    
private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
    Double descuento = 0.00;
    Double saldo = 0.00;
    Double totalFactura = 0.00;
    String nombre = "";
    String comentario = "";
    if(facturar())
    {  
        String totalEfectivo = "";
        DecimalFormat df1 = new DecimalFormat("###0.00"); 
        DecimalFormat df_4decimales = new DecimalFormat("###0.0000"); 
        int idCabecera = objCabecera.obtenerUltimaFactura(idCajero);
        System.out.println("cabecera:" + idCabecera);
        ArrayList <clsCabecera> dataCabecera = objCabecera.consultarDataCabecera(idCabecera);

        ArrayList <clsDetalle> dataDetalle = objDetalle.consultarDataDetalle(idCabecera);
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(objUtils.HostSystem + "file00001.txt");
            pw = new PrintWriter(fichero);
            /*pw.println("           S U P E R  T O D O");
            pw.println("           RUC: 1200012613001");
            pw.println("ROCAFUERTE 617 ENTRE 9 DE OCTUBRE y SUCR");
            pw.println("           BABA - ECUADOR");*/
            pw.println(objParametros.consultaValor("print_factura_linea1"));
            pw.println(objParametros.consultaValor("print_factura_linea2"));
            pw.println(objParametros.consultaValor("print_factura_linea3"));
            pw.println(objParametros.consultaValor("print_factura_linea4"));
            pw.println("     Fact #"+dataCabecera.get(0).getFactReferencia());
            //pw.println("");
            //pw.println("");
            //35 lineas
            /********************CABECERA**********/
            //pw.println("");
            if(dataCabecera.get(0).getCodigo()==2775)
            {
                pw.println("CEDULA: "+dataCabecera.get(0).getCedulaSinRegistrar());
                nombre = dataCabecera.get(0).getNombreSinRegistrar().toString();
                if(nombre.length()>30)
                    pw.println("NOMBRE: "+nombre.substring(0, 30));
                else
                    pw.println("NOMBRE: "+nombre);
            }
            else
            {
                pw.println("CEDULA: "+dataCabecera.get(0).getCedula());
                nombre = dataCabecera.get(0).getNameCompleto().toString();
                if(nombre.length()>30)
                    pw.println("NOMBRE: "+nombre.substring(0, 30));
                else
                    pw.println("NOMBRE: "+nombre);
            }
            pw.println("FECHA:  "+dataCabecera.get(0).getFecha().substring(0, 16));
            
            comentario = dataCabecera.get(0).getComentario();
            //TIPO DE  COMPRA
            saldo = dataCabecera.get(0).getSaldo();
            if(saldo==0)
                pw.println("TIPO DE COMPRA: CONTADO");
            else
                pw.println("TIPO DE COMPRA: CREDITO");
            //***************//
            pw.println("");//6
            /**************************************/
            /*************DETALLE******************/
            pw.println("CANT   DESCRIPCION              SUBTOTAL");//7
            for(int i=0; i<dataDetalle.size(); i++)
            {
                //COMPROBAR SI SE  LE SUMA IVA
                boolean verificarIVA = objImpuestos.comprobarImpuesto(dataDetalle.get(i).getIdProducto(), "1");                    
                
                String detalle = "";
                //if(dataDetalle.get(i).getDescripcionProducto().length()>24)
                if(dataDetalle.get(i).getDescripcionProducto().length()>22)
                {
                    //detalle = dataDetalle.get(i).getDescripcionProducto().substring(0, 24);
                    detalle = dataDetalle.get(i).getDescripcionProducto().substring(0, 22);
                }
                else
                {
                    detalle = dataDetalle.get(i).getDescripcionProducto();
                    do{
                        detalle = detalle + " ";
                    }while(detalle.length()<22);
                    //}while(detalle.length()<24);
                }
                
                if(verificarIVA)
                {                      
                    //LE PONGO ASTERISCO PARA RESALTAR PRODUCTO CON IVA
                    //pw.println(dataDetalle.get(i).getCantidad() + "   *" +
                    pw.println(objUtils.rellenar_cantidad("" +dataDetalle.get(i).getCantidad()) 
                            + "  " 
                            + detalle                     
                            + "$" 
                            + objUtils.rellenar_4decimales("" + df_4decimales.format(dataDetalle.get(i).getCantidad()*(dataDetalle.get(i).getPrecio()/(1+(objImpuestos.obtenerPorcentajeIVA()/100))))));  
                }
                else
                {                    
                    pw.println(objUtils.rellenar_cantidad("" +dataDetalle.get(i).getCantidad()) 
                            + "  " 
                            + detalle                     
                            + "$" 
                            + objUtils.rellenar_4decimales("" + df_4decimales.format(dataDetalle.get(i).getCantidad()*dataDetalle.get(i).getPrecio()))); 
                }
            }
            /*COMPLETAR LOS ESPACIOS EN BLANCO
             * for(int i=0; i<20-dataDetalle.size(); i++)
            {
                pw.println("");
            }*/
            pw.println("");
                       
            double tarifaIva = dataCabecera.get(0).getTarifaIVA();
            double tarifaZero = dataCabecera.get(0).getTarifaCero();
            double subtotal = tarifaIva + tarifaZero;
            totalFactura = dataCabecera.get(0).getTotal();
            System.out.println("iva: "+tarifaIva +" cero: "+tarifaZero+ "subtotal: " +subtotal);
            descuento = dataCabecera.get(0).getDescuento();            
            pw.println("SUBTOTAL:                       $" + objUtils.rellenar(""+df1.format(subtotal)));
            //pw.println("VENTA TARIFA 12%:               $" + objUtils.rellenar(""+df1.format(tarifaIva)));
            //pw.println("VENTA TARIFA 0%:                $" + objUtils.rellenar(""+df1.format(tarifaZero)));
            pw.println("DESCUENTO:                      $" + objUtils.rellenar(""+df1.format(descuento)));
            pw.println("I.V.A.:                         $" + objUtils.rellenar(""+df1.format(dataCabecera.get(0).getIVA())));
            totalEfectivo = df1.format(objUtils.redondear(dataCabecera.get(0).getEfectivo()));
            //pw.println("TOTAL:                          $" + objUtils.rellenar(""+objUtils.redondear(dataCabecera.get(0).getEfectivo()))); 
            pw.println("TOTAL:                          $" + objUtils.rellenar(""+objUtils.redondear(totalFactura))); 
            pw.println("------------------------------------");
            
            /******ABRIR VENTANA******/
            frmCambio ventana = new frmCambio(null, true, totalEfectivo);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
            
            pw.println("VUELTO:                         $" + objUtils.rellenar(""+vuelto)); 
            //pw.println("");   
            if(main.nameUser.length()>30)
                pw.println("CAJERO: " + main.nameUser.substring(0, 30));
            else
                pw.println("CAJERO: " + main.nameUser);
            //pw.println("");   
            pw.println(objParametros.consultaValor("print_factura_linea_final"));
            /**************************************/       
            
            pw.println("");   
            pw.println("");   
            pw.println("");   
            pw.println("");   
            pw.println("");   
            pw.println("");   
            pw.println("");   
            pw.println("");   
            pw.println("");  
            pw.println(""); 
            /******ABRIR VENTANA******/
            /*frmCambio ventana = new frmCambio(null, true, totalEfectivo);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);*/
        
            /******ABRIR CAJON*****/
            byte[] bit = new byte[1];
            bit[0] = (byte)27;
            String a = new String(bit);
            //byte[] bit = new byte[1];
            bit[0] = (byte)112;
            String b = new String(bit);            
            pw.println(a + b + 0);
            /*******ABRIR CAJON****/
            Runtime aplicacion = Runtime.getRuntime(); 
            //try{
                //IMPRIMIR 2 VECES
                //for(int x=0; x<2; x++)
                aplicacion.exec("cmd.exe /K "+ objUtils.HostSystem + objUtils.archivoImprimir1);     
            //}
            //catch(Exception e)
            //{
            //    System.out.println(e);
            //}
        } 
        catch (Exception e) 
        {
            System.out.println(e.toString());
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
              System.out.println(e2.toString());
              //e2.printStackTrace();
           }
        }
        
        //ENVIAR SUPER CORREO SI HUBO DESCUENTO        
        String xcredito ="";
        if(saldo==0)
            xcredito = "NO";
        else
            xcredito = "SI";
        
        String strDescuento = "";
        String strCredito = "";
        if(descuento>0)
            strDescuento = "DESCUENTO";
        if(saldo>0)
            strCredito = "CREDITO";
        //ENVIO DE CORREO
        String email_habilitado = objParametros.consultaValor("email_habilitado");
        if(email_habilitado.equals("1"))
        {    
            try{
               String texto = "EL USUARIO: " 
                       + main.nameUser+ ", REGISTRÓ UN " + strDescuento + " " + strCredito + ".</BR></BR>"
                       + "COMENTARIO: " + comentario + "</BR>"
                       + "<TABLE BORDER=\"1\">"
                               + "<TR><TD>DESCRIPCION</TD><TD>VALOR</TD></TR>"                        
                               + "<TR><TD>CLIENTE:</TD><TD>" + nombre + "</TD></TR>"
                               + "<TR><TD>CREDITO:</TD><TD>" + xcredito + "</TD></TR>"
                               + "<TR><TD>DESCUENTO:</TD><TD>" + descuento + "</TD></TR>"
                               + "<TR><TD>TOTAL DE FACTURA:</TD><TD>" + totalFactura + "</TD></TR>"           
                        + "</TABLE></BR>"; 

               javaMail mail = new javaMail();
               //DESCUENTO
               if(descuento>0)
               {
                   ArrayList<clsEmail> dataEmail = objEmail.consultarEmails("6");        
                   for(int i=0;i<dataEmail.size();i=i+1)
                   {
                       mail.send(dataEmail.get(i).getEmail(), "DESCUENTO - FACTURA", texto);
                   }
               }
               //CREDITO 
               if(saldo>0)
               {   
                   ArrayList<clsEmail> dataEmail2 = objEmail.consultarEmails("7");        
                   for(int i=0;i<dataEmail2.size();i=i+1)
                   {
                       mail.send(dataEmail2.get(i).getEmail(), "CREDITO - FACTURA", texto);
                   }
               }
               
               //FACTURA MAYOR A 100               
               if(totalFactura>100)
               {   
                   ArrayList<clsEmail> dataEmail3 = objEmail.consultarEmails("10");        
                   for(int i=0;i<dataEmail3.size();i=i+1)
                   {
                       mail.send(dataEmail3.get(i).getEmail(), "VENTA MAYOR", texto);
                   }
               }
           }
           catch(Exception e){
               //e.printStackTrace();
               JOptionPane.showMessageDialog(this, e.getMessage(), "Error al enviar por correo", JOptionPane.ERROR_MESSAGE);
           }       
        }
        //ENVIO DE CORREO - FIN
    }
}//GEN-LAST:event_btnImprimirActionPerformed

private void txtCuotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuotaKeyReleased
    String x = txtCuota.getText();
    if(!objUtils.isDouble(x)){
        txtCuota.setText("");
    }  
    txtFechaCancelacion.setText(calcular_fecha_cancelacion());
}//GEN-LAST:event_txtCuotaKeyReleased

private void txtEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyReleased
    String x = txtEfectivo.getText();
    if(!objUtils.isDouble(x)){
        txtEfectivo.setText("0");
    }  
    Double minuendo = Double.parseDouble(txtTotal.getText().toString());
    Double sustraendo = Double.parseDouble(txtEfectivo.getText().toString());
    Double diferencia = minuendo - sustraendo;
    if(diferencia<0)
    {
        txtEfectivo.setText("0");
        txtSaldo.setText(this.txtTotal.getText());
    }
    txtSaldo.setText(""+objUtils.redondear(diferencia));
    
    this.calcularCuotas();
    
    txtFechaCancelacion.setText(calcular_fecha_cancelacion());
}//GEN-LAST:event_txtEfectivoKeyReleased

public String calcular_fecha_cancelacion()
{
    //CALCULAR FECHA DE CANCELACION
    Date date1= new Date();
    clsComboBox objetoPagoSeleccionado = (clsComboBox)cmbCuota.getSelectedItem();
    String tipoPago = objetoPagoSeleccionado.getCodigo();
    Calendar fechaCarta2 = Calendar.getInstance();
    fechaCarta2.setTime(date1); 
    double numeroPagos = 0.00;
    
    numeroPagos = Double.parseDouble(txtSaldo.getText())/Double.parseDouble(txtCuota.getText());
    Locale.setDefault(Locale.ENGLISH);       
    DecimalFormat formateador = new DecimalFormat("####");
    Integer numeroPagosRedondeado = Integer.parseInt(formateador.format(numeroPagos)); 
    if(tipoPago.equals("1"))       
    {
        //DIARIO
        fechaCarta2.add(Calendar.DAY_OF_MONTH, numeroPagosRedondeado); 
    }
    else if(tipoPago.equals("2"))  
    {
        //SEMANAL
        fechaCarta2.add(Calendar.DAY_OF_MONTH, numeroPagosRedondeado*7); 
    }
    else if(tipoPago.equals("3"))  
    {
        //QUINCENAL
        fechaCarta2.add(Calendar.DAY_OF_MONTH, numeroPagosRedondeado*15); 
    }
    else if(tipoPago.equals("4"))  
    {
        //MENSUAL
        fechaCarta2.add(Calendar.MONTH, numeroPagosRedondeado);  
    }
    Date fecSegCarta = fechaCarta2.getTime();
    date1 = fecSegCarta;
    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
    String fechaCancelacion = df2.format(fecSegCarta);
    return fechaCancelacion;
}        


private void chkCreditoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkCreditoItemStateChanged
    valorContado = Double.parseDouble(txtTotal.getText().toString());
    btnAgregar.setEnabled(false);
    txtCuota.setText("");
    txtEfectivo.setText("0");
    txtSaldo.setText("");
    if(this.chkCredito.isSelected())        
    {
        frmCreditoLogin ventana = new frmCreditoLogin(this, true);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
        /*cmbCuota.setEnabled(true);
        //txtCuota.setEditable(true);
        txtEfectivo.setEditable(true);
        cmbPlazo.setEnabled(true);
        //txtSaldo.setEditable(true);  */     
    } 
    else
    {
        cmbCuota.setEnabled(false);
        txtCuota.setEditable(false);
        txtEfectivo.setEditable(false);
        cmbPlazo.setEnabled(false);
        lblCredito.setText("");
        //txtSaldo.setEditable(false);     
    }
     
    if(valorContado>0)
        calcularCuotas();
    //txtEfectivo.setText(""+cuotaInicial);
   
}//GEN-LAST:event_chkCreditoItemStateChanged

void calcularCuotas()
{
    Double cuotaInicial = Double.parseDouble(txtEfectivo.getText());
    //txtEfectivo.setText(""+cuotaInicial);
    //CALCULAR EL VALOR DE  DEUDA CON INTERES SEGUN LOS PLAZOS
    clsComboBox objPlazoSelect = (clsComboBox)cmbPlazo.getSelectedItem();
    String idPlazo = objPlazoSelect.getCodigo();
    //plazos 1(3 meses), 2 (6 meses), 3(9 meses), 4(12 meses)
    int semanas = 0;
    if(idPlazo.equals("5"))
    {        
        semanas = 1;
    }
    else if(idPlazo.equals("6"))
    {
        semanas = 2;
    }
    else if(idPlazo.equals("7"))
    {
        semanas = 3;
    }
    else if(idPlazo.equals("8"))
    {
        semanas = 4;
    }
    double cantidadAdeudada = valorContado - cuotaInicial;
    
    txtSaldo.setText("" + objUtils.redondear(cantidadAdeudada));
    //AHORA DIVIDO PARA LOS TIEMPOS  Q ME DESEA PAGAR
    //MENSUAL QUINCENAL SEMANAL
    clsComboBox objCuotaSelect = (clsComboBox)cmbCuota.getSelectedItem();
    String idCuota = objCuotaSelect.getCodigo();
    Double cuotaIndividual= 0.00;
    //2 semanal, 3 quincenal, 4 mensual
    if(idCuota.equals("2"))
    {
        cuotaIndividual = cantidadAdeudada/semanas;
    }
    txtCuota.setText(""+objUtils.redondear(cuotaIndividual));
    txtFechaCancelacion.setText(calcular_fecha_cancelacion());
}

private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
    if(evt.getKeyChar() == KeyEvent.VK_ENTER){
        //KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        //manager.focusNextComponent();
        btnAgregar.requestFocus();
    }
}//GEN-LAST:event_txtCantidadKeyTyped

private void btnAgregarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAgregarKeyTyped
   
}//GEN-LAST:event_btnAgregarKeyTyped

private void txtDescuentoUnidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoUnidadKeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_txtDescuentoUnidadKeyPressed

private void txtDescuentoUnidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoUnidadKeyReleased
    String x = txtDescuentoUnidad.getText();
    if(!objUtils.isDouble(x)){
        txtDescuentoUnidad.setText("");
    } 
}//GEN-LAST:event_txtDescuentoUnidadKeyReleased

private void txtDescuentoUnidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoUnidadKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_txtDescuentoUnidadKeyTyped

private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
    if(evt.getKeyChar() == KeyEvent.VK_ENTER){  
        cargarDataCliente();   
    } 
}//GEN-LAST:event_txtCedulaKeyTyped

private void cmbPlazoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPlazoItemStateChanged
    if(cmbPlazo.isEnabled())
        calcularCuotas();    // TODO add your handling code here:
}//GEN-LAST:event_cmbPlazoItemStateChanged

private void cmbCuotaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCuotaItemStateChanged
     if(cmbCuota.isEnabled())
        calcularCuotas(); // TODO add your handling code here:
}//GEN-LAST:event_cmbCuotaItemStateChanged

private void btnDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescuentoActionPerformed
    calcularTotal();
    
    frmDescuentoLogin ventana = new frmDescuentoLogin(this, true);
    //new inventariopdf.JDEscoger(this, true);
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
}//GEN-LAST:event_btnDescuentoActionPerformed

private void txtDescuentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyPressed
   String x = txtDescuento.getText();
   
    if(!objUtils.isDouble(x)){
        txtDescuento.setText("");
    }  
    else
    {
        Double descuento = Double.parseDouble(txtDescuento.getText());
        if(evt.getKeyChar() == KeyEvent.VK_ENTER){ 
            if(descuento<=0)
            {
                calcularTotal();
            }
            else
            {
                btnAgregar.setEnabled(false);

                //txtTarifaCero.setText("" + objUtils.redondear(baseTarifaCero)); 
                //txtTarifaIVA.setText("" + objUtils.redondear(baseTarifaIva)); 
                //txtDescuento.setText(""+objUtils.redondear(descuento));
                //txtIVA.setText(""+objUtils.redondear(impIVA));
                Double ventaSinIva = Double.parseDouble(txtTarifaCero.getText());
                Double ventaConIva = Double.parseDouble(txtTarifaIVA.getText());
                

                //txtIVA.setText("" + ((ventaConIva-descuento)*(objImpuestos.obtenerPorcentajeIVA()/100)));
                //Double iva = Double.parseDouble(txtIVA.getText());

                //txtTotal.setText(""+objUtils.redondear(ventaConIva + ventaSinIva - descuento + iva));
                Double total = Double.parseDouble(txtTotal.getText());
                Double nuevoTotal = (total-descuento);
                Double nuevoBaseIVA = nuevoTotal/(1+(objImpuestos.obtenerPorcentajeIVA()/100)); 
                txtTotal.setText("" + nuevoTotal);
                txtTarifaIVA.setText("" + objUtils.redondear(nuevoBaseIVA));
                txtIVA.setText("" + objUtils.redondear(nuevoTotal-nuevoBaseIVA));

                txtDescuento.setEditable(false);
                btnGuardar.requestFocus();
            }
        }
    }   
}//GEN-LAST:event_txtDescuentoKeyPressed

private void chkSinRegistrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkSinRegistrarItemStateChanged
    txtCedula.setText("");
    txtNombreCliente.setText("");
    
    if(chkSinRegistrar.isSelected())
    {
        /**chkCredito.setEnabled(false);
        btnDescuento.setEnabled(true);
        txtNombreCliente.setEditable(true);*/
        chkCredito.setEnabled(false);
        btnDescuento.setEnabled(false);
        txtNombreCliente.setEditable(true);
        
    }
    else
    {
        btnDescuento.setEnabled(false);
        txtNombreCliente.setEditable(false);
        chkCredito.setEnabled(false);
    }
}//GEN-LAST:event_chkSinRegistrarItemStateChanged

private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
    if(evt.getKeyChar() == KeyEvent.VK_ENTER){  
        txtCodigoProducto.requestFocus();
    } 
}//GEN-LAST:event_txtNombreClienteKeyTyped

private void btnImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagenActionPerformed
    String imagen = objProducto.consultarImagenProducto(codigoProducto); 
    String rutaActual = x_ruta + imagen;
    File f = new File(rutaActual);
    if(f.exists()){
    try {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + rutaActual);
    }
    catch(Exception e){}
    }
    else
    JOptionPane.showMessageDialog(this, "No existe\n" + rutaActual, "ERROR", JOptionPane.ERROR_MESSAGE);
}//GEN-LAST:event_btnImagenActionPerformed

private void chkSinRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSinRegistrarActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_chkSinRegistrarActionPerformed

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
            java.util.logging.Logger.getLogger(frmFacturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmFacturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmFacturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmFacturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                frmFacturar dialog = new frmFacturar(new javax.swing.JFrame(), true);
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
    public javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDescuento;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImagen;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnMostrarArturo;
    public static javax.swing.JButton btnMostrarProductos;
    public static javax.swing.JCheckBox chkCredito;
    private javax.swing.JCheckBox chkSinRegistrar;
    public static javax.swing.JComboBox cmbCuota;
    public static javax.swing.JComboBox cmbPlazo;
    public static javax.swing.JComboBox cmbPrecio;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCajero;
    public static javax.swing.JLabel lblCredito;
    public static javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblData;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtCedula;
    public static javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextArea txtComentario;
    public static javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtCuota;
    public static javax.swing.JTextField txtDescuento;
    public static javax.swing.JTextField txtDescuentoUnidad;
    public static javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtFactura;
    private javax.swing.JTextField txtFechaCancelacion;
    private javax.swing.JTextField txtIVA;
    public static javax.swing.JTextField txtNombreCliente;
    public static javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSaldo;
    public static javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTarifaCero;
    private javax.swing.JTextField txtTarifaIVA;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
