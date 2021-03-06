/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmCerrarCaja.java
 *
 * Created on 23-oct-2011, 13:56:24
 */
package pos;

import clases.clsAuditoria;
import clases.clsCabecera;
import index.main;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import clases.clsCaja;
import clases.clsEgreso;
import clases.clsPago;
import clases.clsUtils;
import java.awt.Dimension;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import stinventario.frmPrincipal;


/**
 *
 * @author Kaiser
 */
public class frmCerrarCaja_ extends javax.swing.JInternalFrame {
    clsCaja objCaja = new clsCaja();
    clsUtils objUtils = new clsUtils();
    clsAuditoria objAuditoria = new clsAuditoria();
    clsEgreso objEgreso = new clsEgreso(); 
    clsCabecera objCabecera = new clsCabecera();
    clsPago objPago = new clsPago();
    int idCajaAbierta = 0;
    String txtDevoluciones = "";
    /** Creates new form frmCerrarCaja */
    
    public frmCerrarCaja_() {
        
        
        initComponents();
       
        txtUsuario.setText(main.nameUser);
        ArrayList<clases.clsCaja> dataCaja = objCaja.consultarDataCajaAbierta(main.idUser);
        
        txtValorApertura.setText(""+dataCaja.get(0).getValorApertura());
        idCajaAbierta = dataCaja.get(0).getIdCajaOperacion();        
        
        //OBTENER VALOR FACTURADO
        txtFacturado.setText(""+objUtils.redondear(objCaja.obtenerValorFacturado(idCajaAbierta)));
        
        //OBTENER VALOR DEVOLUCIONES
        txtDevoluciones = ""+objCaja.obtenerValorDevolucionesVentas(idCajaAbierta);
        
        //OBTENER VALOR DE PAGOS/NE
        txtPagos.setText(""+objUtils.redondear(objCaja.obtenerValorPagos(idCajaAbierta)));
        
        //OBTENER VALOR DE PAGOS/FACTURA
        txtPagosFactura.setText(""+objUtils.redondear(objCaja.obtenerValorPagosFactura(idCajaAbierta)));
        
        //OBTENER VALOR DE RECIBOS
        txtRecibos.setText(""+objUtils.redondear(objCaja.obtenerValorRecibos(idCajaAbierta)));
        
        //OBTENER VALOR DE INGRESOS
        txtIngresos.setText(""+objUtils.redondear(objEgreso.obtenerValorEgresos(idCajaAbierta, "I")));
        
        //OBTENER VALOR DE EGRESOS
        txtEgresos.setText(""+objUtils.redondear(objEgreso.obtenerValorEgresos(idCajaAbierta, "E")));
        
        //OBTENER CAJERO
        String cajero = objCaja.obtenerCajero(main.idUser);
        lblCaja.setText(cajero); 
        Double valorx = 0.00 - (Double.parseDouble(txtValorApertura.getText())+             
                objCaja.obtenerValorFacturado(idCajaAbierta)-
                 objCaja.obtenerValorDevolucionesVentas(idCajaAbierta)+
                 objCaja.obtenerValorRecibos(idCajaAbierta)+
                objCaja.obtenerValorPagos(idCajaAbierta) +
                objEgreso.obtenerValorEgresos(idCajaAbierta, "I")- 
                objEgreso.obtenerValorEgresos(idCajaAbierta, "E"));
        txtDiferencia.setText(""+objUtils.redondear(valorx));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    static boolean isDouble(String cadena){
        try {
            Double.parseDouble(cadena);
            return true;
        }
        catch (NumberFormatException nfe){
            return false;
        } 
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtValorApertura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtValorContado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDiferencia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtFacturado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPagos = new javax.swing.JTextField();
        btnMostrarFacturas = new javax.swing.JButton();
        btnMostrarPagos = new javax.swing.JButton();
        btnMostrarPagos1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtPagosFactura = new javax.swing.JTextField();
        txtRecibos = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnMostrarPagos2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtEgresos = new javax.swing.JTextField();
        btnEgresos = new javax.swing.JButton();
        lblCaja = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtIngresos = new javax.swing.JTextField();
        btnIngresos = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmCerrarCaja_.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtUsuario.setEditable(false);
        txtUsuario.setText(resourceMap.getString("txtUsuario.text")); // NOI18N
        txtUsuario.setName("txtUsuario"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtValorApertura.setEditable(false);
        txtValorApertura.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorApertura.setText(resourceMap.getString("txtValorApertura.text")); // NOI18N
        txtValorApertura.setName("txtValorApertura"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtValorContado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorContado.setText(resourceMap.getString("txtValorContado.text")); // NOI18N
        txtValorContado.setName("txtValorContado"); // NOI18N
        txtValorContado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorContadoKeyReleased(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtDiferencia.setEditable(false);
        txtDiferencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiferencia.setText(resourceMap.getString("txtDiferencia.text")); // NOI18N
        txtDiferencia.setName("txtDiferencia"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtFacturado.setEditable(false);
        txtFacturado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtFacturado.setText(resourceMap.getString("txtFacturado.text")); // NOI18N
        txtFacturado.setName("txtFacturado"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtPagos.setEditable(false);
        txtPagos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPagos.setText(resourceMap.getString("txtPagos.text")); // NOI18N
        txtPagos.setName("txtPagos"); // NOI18N

        btnMostrarFacturas.setIcon(resourceMap.getIcon("btnMostrarFacturas.icon")); // NOI18N
        btnMostrarFacturas.setText(resourceMap.getString("btnMostrarFacturas.text")); // NOI18N
        btnMostrarFacturas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMostrarFacturas.setName("btnMostrarFacturas"); // NOI18N
        btnMostrarFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarFacturasActionPerformed(evt);
            }
        });

        btnMostrarPagos.setIcon(resourceMap.getIcon("btnMostrarPagos.icon")); // NOI18N
        btnMostrarPagos.setText(resourceMap.getString("btnMostrarPagos.text")); // NOI18N
        btnMostrarPagos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMostrarPagos.setName("btnMostrarPagos"); // NOI18N
        btnMostrarPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarPagosActionPerformed(evt);
            }
        });

        btnMostrarPagos1.setIcon(resourceMap.getIcon("btnMostrarPagos1.icon")); // NOI18N
        btnMostrarPagos1.setText(resourceMap.getString("btnMostrarPagos1.text")); // NOI18N
        btnMostrarPagos1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMostrarPagos1.setName("btnMostrarPagos1"); // NOI18N
        btnMostrarPagos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarPagos1ActionPerformed(evt);
            }
        });

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        txtPagosFactura.setEditable(false);
        txtPagosFactura.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPagosFactura.setText(resourceMap.getString("txtPagosFactura.text")); // NOI18N
        txtPagosFactura.setName("txtPagosFactura"); // NOI18N

        txtRecibos.setEditable(false);
        txtRecibos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtRecibos.setText(resourceMap.getString("txtRecibos.text")); // NOI18N
        txtRecibos.setName("txtRecibos"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        btnMostrarPagos2.setIcon(resourceMap.getIcon("btnMostrarPagos2.icon")); // NOI18N
        btnMostrarPagos2.setText(resourceMap.getString("btnMostrarPagos2.text")); // NOI18N
        btnMostrarPagos2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMostrarPagos2.setName("btnMostrarPagos2"); // NOI18N
        btnMostrarPagos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarPagos2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtRecibos, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtPagos)
                        .addComponent(txtFacturado, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                    .addComponent(txtPagosFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnMostrarPagos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMostrarFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnMostrarPagos1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(btnMostrarPagos2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFacturado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarFacturas))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarPagos)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPagosFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(btnMostrarPagos2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRecibos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(btnMostrarPagos1)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel3.border.title"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txtEgresos.setEditable(false);
        txtEgresos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEgresos.setText(resourceMap.getString("txtEgresos.text")); // NOI18N
        txtEgresos.setName("txtEgresos"); // NOI18N

        btnEgresos.setIcon(resourceMap.getIcon("btnEgresos.icon")); // NOI18N
        btnEgresos.setText(resourceMap.getString("btnEgresos.text")); // NOI18N
        btnEgresos.setName("btnEgresos"); // NOI18N
        btnEgresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEgresosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEgresos)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtEgresos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(167, 167, 167))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtEgresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnEgresos))
        );

        lblCaja.setEditable(false);
        lblCaja.setText(resourceMap.getString("lblCaja.text")); // NOI18N
        lblCaja.setName("lblCaja"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel4.border.title"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        txtIngresos.setEditable(false);
        txtIngresos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIngresos.setText(resourceMap.getString("txtIngresos.text")); // NOI18N
        txtIngresos.setName("txtIngresos"); // NOI18N

        btnIngresos.setIcon(resourceMap.getIcon("btnIngresos.icon")); // NOI18N
        btnIngresos.setText(resourceMap.getString("btnIngresos.text")); // NOI18N
        btnIngresos.setName("btnIngresos"); // NOI18N
        btnIngresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnIngresos)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(167, 167, 167))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnIngresos))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtValorContado)
                            .addComponent(txtDiferencia, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(txtValorApertura, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(lblCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(298, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorApertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorContado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDiferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnImprimir.setIcon(resourceMap.getIcon("btnImprimir.icon")); // NOI18N
        btnImprimir.setText(resourceMap.getString("btnImprimir.text")); // NOI18N
        btnImprimir.setName("btnImprimir"); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImprimir)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtValorContadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorContadoKeyReleased
    String x = txtValorContado.getText();
    /*if(x.isEmpty())
    {
        txtValorContado.setText("0");
    }*/
    
    if(!isDouble(x)){
        txtValorContado.setText("");
        txtDiferencia.setText("");
    }
    else
    {
        Double minuendo = Double.parseDouble(this.txtValorContado.getText());
        //Double minuendo2 = Double.parseDouble(this.txtPagos.getText());
        Double sustraendo = Double.parseDouble(this.txtValorApertura.getText())+
                            Double.parseDouble(this.txtFacturado.getText())-
                            Double.parseDouble(this.txtDevoluciones)+
                            Double.parseDouble(this.txtRecibos.getText())+
                            Double.parseDouble(this.txtPagos.getText())+
                            Double.parseDouble(this.txtIngresos.getText())-
                            Double.parseDouble(this.txtEgresos.getText());
        Double diferencia = minuendo-sustraendo;
        this.txtDiferencia.setText(""+objUtils.redondear(diferencia));     
        //this.txtDiferencia.setText(""+diferencia);
        if(this.txtDiferencia.getText().equals("0.0")||this.txtDiferencia.getText().equals("-0.0"))
                this.txtDiferencia.setBackground(Color.green);
        else
                this.txtDiferencia.setBackground(Color.red);
    }
}//GEN-LAST:event_txtValorContadoKeyReleased

private void btnMostrarFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarFacturasActionPerformed
     frmFacturasRealizadas formulario = new frmFacturasRealizadas();
     mostrarJInternalCentrado(formulario);
      
}//GEN-LAST:event_btnMostrarFacturasActionPerformed

private void btnMostrarPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarPagosActionPerformed
    frmPagosRealizados formulario = new frmPagosRealizados();   
    mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_btnMostrarPagosActionPerformed

private void btnEgresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEgresosActionPerformed
    frmEgresosRealizados formulario = new frmEgresosRealizados(this);    
    mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_btnEgresosActionPerformed

private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
    //Calendar calendario = Calendar.getInstance();
    Calendar calendario = new GregorianCalendar();
    int hora, minutos, segundos, dia, mes,annio;
    hora =calendario.get(Calendar.HOUR_OF_DAY);
    minutos = calendario.get(Calendar.MINUTE);
    segundos = calendario.get(Calendar.SECOND);
    dia = calendario.get(Calendar.DATE);
    mes = calendario.get(Calendar.MONTH) +1;
    annio = calendario.get(Calendar.YEAR);
    
    Double totalFacturas = 0.00;
    Double totalPagos = 0.00;
    Double totalEgresos = 0.00;
    Double totalIngresos = 0.00;
    FileWriter fichero = null;
    PrintWriter pw = null;
    try
    {
        fichero = new FileWriter(objUtils.HostSystem + "file00001.txt");
        pw = new PrintWriter(fichero);
        /*40COLUMNAS*/
        pw.println(annio + "/" + mes + "/" + dia + " " +hora + ":" + minutos + ":" + segundos);
        pw.println("VALOR APERTURA: " + txtValorApertura.getText());
        
        //FACTURAS
        ArrayList<clsCabecera> dataFacturas = objCabecera.consultaFacturasRealizadas(idCajaAbierta); 
        int maxData = dataFacturas.size();
        String factReferencia = "";
        //SI HAY FACTURAS LAS PRESNETA
        if(maxData>0)
        {    
            pw.println("FACTURAS");
            pw.println("----------------------------------------");
            for(int i=0;i<maxData;i++)
            {
                 factReferencia = dataFacturas.get(i).getFactReferencia() + "                                         "; 
                 pw.println((i+1) + " " + factReferencia.substring(0, 28) + " " + dataFacturas.get(i).getEfectivo());
                 totalFacturas = totalFacturas + dataFacturas.get(i).getEfectivo();                 
            } 
            pw.println(" ");
        }
        //PAGOS        
        ArrayList<clsPago> dataPagos = objPago.consultaPagosRealizadas(idCajaAbierta); 
        maxData = dataPagos.size();
        String referencia = "";
        if(maxData>0)
        { 
            pw.println("PAGOS");
            pw.println("----------------------------------------");
            for(int i=0; i<maxData;i++)
            {
                referencia = dataPagos.get(i).getReferencia()+ "                                         "; 
                pw.println((i+1) +  " " + referencia.substring(0, 28) + " " + dataPagos.get(i).getValor());                                  
                 totalPagos = totalPagos + dataPagos.get(i).getValor();                
            } 
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
                pw.println((i+1) +  " " + concepto.substring(0, 28) + " " + dataIngresos.get(i).getCantidadEgreso());
                totalIngresos = totalIngresos + dataIngresos.get(i).getCantidadEgreso();                
            } 
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
                pw.println((i+1) +  " " + concepto.substring(0, 28) + " " + dataEgresos.get(i).getCantidadEgreso());
                totalEgresos = totalEgresos + dataEgresos.get(i).getCantidadEgreso();                
            } 
            pw.println(" ");
        }
       
        Double totalCierre = Double.parseDouble(txtValorApertura.getText()) + totalFacturas + totalPagos + totalIngresos - totalEgresos;
        pw.println("TOTAL EFECTIVO: "+ objUtils.redondear(totalCierre));
        Runtime aplicacion = Runtime.getRuntime(); 
        try{
            //IMPRIMIR 2 VECES
            for(int x=0; x<1; x++)
                aplicacion.exec("cmd.exe /K "+ objUtils.HostSystem + "printFile.bat"); 
        }
        catch(Exception e)
        {
            System.out.println(e);
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
}//GEN-LAST:event_btnImprimirActionPerformed

private void btnMostrarPagos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarPagos1ActionPerformed
     frmRecibosPagoGenerados formulario = new frmRecibosPagoGenerados();
     mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_btnMostrarPagos1ActionPerformed

private void btnIngresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresosActionPerformed
     frmIngresosRealizados formulario = new frmIngresosRealizados(this);    
     mostrarJInternalCentrado(formulario);
     
}//GEN-LAST:event_btnIngresosActionPerformed

private void btnMostrarPagos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarPagos2ActionPerformed
    frmPagosFacturaRealizados formulario = new frmPagosFacturaRealizados();   
    mostrarJInternalCentrado(formulario); 
}//GEN-LAST:event_btnMostrarPagos2ActionPerformed
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
    private javax.swing.JButton btnEgresos;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnIngresos;
    private javax.swing.JButton btnMostrarFacturas;
    private javax.swing.JButton btnMostrarPagos;
    private javax.swing.JButton btnMostrarPagos1;
    private javax.swing.JButton btnMostrarPagos2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField lblCaja;
    private javax.swing.JTextField txtDiferencia;
    private javax.swing.JTextField txtEgresos;
    private javax.swing.JTextField txtFacturado;
    private javax.swing.JTextField txtIngresos;
    private javax.swing.JTextField txtPagos;
    private javax.swing.JTextField txtPagosFactura;
    private javax.swing.JTextField txtRecibos;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtValorApertura;
    private javax.swing.JTextField txtValorContado;
    // End of variables declaration//GEN-END:variables
}
