/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmPagoDetalle.java
 *
 * Created on 26/01/2013, 11:40:11 AM
 */
package pago;

import clases.clsCaja;
import clases.clsCtasCobrar;
import clases.clsCupones;
import clases.clsPago;
import clases.clsParametros;
import clases.clsUtils;
import index.main;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author User
 */
public class frmPagoDetalleFactura extends javax.swing.JInternalFrame {
    clsPago objPago = new clsPago();
    clsUtils objUtils = new clsUtils();
    clsCaja objCaja = new clsCaja();
    clsCtasCobrar objCtasCobrar = new clsCtasCobrar();
    clsParametros objParametros = new clsParametros();
    clsCupones objCupones = new clsCupones();
    
    int idPago_publica = 0;
    int codigo = 0;
    /** Creates new form frmPagoDetalle */
    public frmPagoDetalleFactura(int idPago) {
        idPago_publica = idPago;
        initComponents();
        ArrayList<clsPago> dataPago = objPago.consultaDataPagoDetalleFactura(idPago);
        
        txtID.setText("" + dataPago.get(0).getIdPago());
        txtDescripcion.setText(dataPago.get(0).getDescripcionDeuda());
        txtReferencia.setText(dataPago.get(0).getReferencia());
        txtCliente.setText(dataPago.get(0).getNombreCliente());
        txtValor.setText("" + dataPago.get(0).getValor());
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        btnImprimir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtReferencia = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmPagoDetalleFactura.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtID.setEditable(false);
        txtID.setText(resourceMap.getString("txtID.text")); // NOI18N
        txtID.setName("txtID"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtValor.setEditable(false);
        txtValor.setFont(resourceMap.getFont("txtValor.font")); // NOI18N
        txtValor.setForeground(resourceMap.getColor("txtValor.foreground")); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValor.setText(resourceMap.getString("txtValor.text")); // NOI18N
        txtValor.setCaretColor(resourceMap.getColor("txtValor.caretColor")); // NOI18N
        txtValor.setName("txtValor"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtDescripcion.setColumns(20);
        txtDescripcion.setEditable(false);
        txtDescripcion.setRows(5);
        txtDescripcion.setName("txtDescripcion"); // NOI18N
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtCliente.setEditable(false);
        txtCliente.setText(resourceMap.getString("txtCliente.text")); // NOI18N
        txtCliente.setName("txtCliente"); // NOI18N

        btnImprimir.setText(resourceMap.getString("btnImprimir.text")); // NOI18N
        btnImprimir.setName("btnImprimir"); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtReferencia.setColumns(20);
        txtReferencia.setEditable(false);
        txtReferencia.setRows(5);
        txtReferencia.setName("txtReferencia"); // NOI18N
        jScrollPane2.setViewportView(txtReferencia);

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        btnEliminar.setIcon(resourceMap.getIcon("btnEliminar.icon")); // NOI18N
        btnEliminar.setText(resourceMap.getString("btnEliminar.text")); // NOI18N
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnEliminar)
                                .addGap(52, 52, 52)
                                .addComponent(btnImprimir))
                            .addComponent(txtCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addGap(237, 237, 237)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimir)
                    .addComponent(btnEliminar))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
    
    FileWriter fichero = null;
    PrintWriter pw = null;
    ArrayList<clsPago> dataPago = objPago.consultaDataPagoDetalleFactura(idPago_publica);
    try
    {
        //ACTUALIZAR DATOS DEL PAGO
        //OBTENER IDCAJAOPERACION ACTUAL, OSEA QUE  NO ESTA CERRADA
        String idCajaAbierta = objCaja.obtenerCajaAbierta(main.idUser);
        objPago.actualizarDataPagoFactura(idPago_publica, idCajaAbierta, main.idUser);
        dataPago = objPago.consultaDataPagoDetalleFactura(idPago_publica);
        //RESTAR SALDO
        //ACTUALIZAR SALDO
        int idCtaCobrar = dataPago.get(0).getIdCtaCobrar();    
        Double valor = dataPago.get(0).getValor();
        String fecha_cobro = dataPago.get(0).getFechaCobro().substring(0, 16);   
        codigo = dataPago.get(0).getCodigo();
        
        objCtasCobrar.actualizarSaldoFacturasNuevo(idCtaCobrar, objUtils.redondear(valor));
        //OBTENER SALDO SI SALDO ES  CERO CANCELO DEUDA
        Double saldo = objCtasCobrar.consultarSaldoCtaFactura("" + idCtaCobrar);
        
        //OBTENER DEUDAS NOTAS ENTREGA y facturas
        double totalNE = objPago.consultaTotalDeudaNE(dataPago.get(0).getCodigo());
        double totalFacturas = objPago.consultaTotalDeudaFacturas(dataPago.get(0).getCodigo());
        double totalNEFacturas = 0.00;
        totalNEFacturas = totalNE + totalFacturas;
        
        if((saldo == 0)||(saldo==0.00)||(saldo<0))
        {
            //REGISTRAR QUE FECHA QUEDO CANCELADO O EN CERO
            objCtasCobrar.registrarCancelacionDeudaFacturaNuevo(idCtaCobrar);
        }       
        System.out.println(valor);
        fichero = new FileWriter(objUtils.HostSystem + objUtils.archivoPrint);
        /******************************************************************************************/
        pw = new PrintWriter(fichero);                                                           //*  
        /*pw.println("            RIZZO MUÑOZ JORGE EUCLIDES                   RECIBO DE COBRO/FACTURA");
        pw.println("              COMISARIATO SUPER TODO		        N° " + idPago_publica);
        pw.println("              R.U.C.: 1200012613-001");
        pw.println("   MATRIZ: JOSE ALAVEDRA SL. 1 Y MIGUEL ALCIVAR");
        pw.println("ESTABLECIMIENTO: ROCAFUERTE # 617 Y NUEVE DE OCTUBRE");
        pw.println("        TELF.: 099-242-4196 * LOS RIOS - ECUADOR");*/
        pw.println(objParametros.consultaValor("print_pago_linea1") + "RECIBO DE COBRO/FACTURA");
        pw.println(objParametros.consultaValor("print_pago_linea2") + "N° " + idPago_publica);
        pw.println(objParametros.consultaValor("print_pago_linea3"));
        pw.println(objParametros.consultaValor("print_pago_linea4"));        
        pw.println(objParametros.consultaValor("print_pago_linea5"));
        pw.println(objParametros.consultaValor("print_pago_linea6"));
        //35 lineas
        /********************CABECERA**********/
        pw.println("");
        pw.println("");
        
        pw.println("RECIBI DE : " + dataPago.get(0).getNombreCliente());
        pw.println("LA CANTIDAD DE: $ " + valor + " ***DOLARES");
        pw.println("POR CONCEPTO DE: " + dataPago.get(0).getReferencia());
        pw.println("CODIGO FACTURA: " + dataPago.get(0).getIdCabeceraMovi());
        pw.println("CODIGO DEUDA: " + dataPago.get(0).getIdCtaCobrar());
        if (saldo<0)
            saldo = 0.00;
        pw.println("VALOR PENDIENTE: $ " + objUtils.redondear(saldo)+ ", TOTAL DEUDAS PENDIENTES: $ " + objUtils.redondear(totalNEFacturas));
        pw.println("");
        /******************************************************************************************/
        //pw.println("LUGAR/FECHA/HORA: BABA, " + fecha_cobro);
        pw.println(objParametros.consultaValor("print_pago_linea7") + " " + fecha_cobro);
        //pw.println("");
        //pw.println("");
        pw.println("");
        pw.println("");
        pw.println("");
             
        //cuadrarlo a cierto numero de caracteres
        String nombre = "";
        if(dataPago.get(0).getNombreCliente().toString().length()>30)
            nombre = dataPago.get(0).getNombreCliente().toString().substring(0, 30);
        else
        {
            nombre = dataPago.get(0).getNombreCliente();
            do{
                nombre = nombre + " ";
            }while(nombre.length()<30);
           
        }       
        //NOMBRE DEL COBRADOR
        String nombreUsuario = "";
        if(dataPago.get(0).getNombreCobrador().toString().length()>30)
            nombreUsuario = dataPago.get(0).getNombreCobrador().toString().substring(0, 30);
        else
        {
            nombreUsuario = dataPago.get(0).getNombreCobrador();
            do{
                nombreUsuario = nombreUsuario + " ";
            }while(nombreUsuario.length()<30);
           
        }       
        /******************************************************************************************/
        pw.println("------------------------------              -----------------------------");
        pw.println("      FIRMA DEL CLIENTE                         FIRMA DEL RECAUDADOR");
                    /****************************/              /****************************/
        pw.println(nombre + "              " + nombreUsuario);
        pw.println("");
        pw.println("Generado por: " + dataPago.get(0).getNombreUsuario());
        pw.println("");
        pw.println("IMPORTANTE: Estimado usuario, por favor guarde este recibo, es el unico ");
        pw.println("comprobante que Ud. tiene para confirmar su pago realizado.");
        pw.println("");
        Runtime aplicacion = Runtime.getRuntime(); 
        aplicacion.exec("cmd.exe /K "+ objUtils.HostSystem + objUtils.archivoImprimir3); 
        
        
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
    
    /*ABRIR LA CAJA, MANDO A IMPRIMIR CODIGO ESPECIAL A LA IMPRESORA TMU*/
        FileWriter fichero2 = null;
        PrintWriter pw2 = null;
        try
        {
            
            fichero2 = new FileWriter(objUtils.HostSystem + objUtils.archivoPrint2);
            pw2 = new PrintWriter(fichero2);
            byte[] bit = new byte[1];
            bit[0] = (byte)27;
            String a = new String(bit);
            //byte[] bit = new byte[1];
            bit[0] = (byte)112;
            String b = new String(bit);            
            pw2.println(a + b + 0);
            /*******ABRIR CAJON****/
            Runtime aplicacion2 = Runtime.getRuntime();             
            aplicacion2.exec("cmd.exe /K "+ objUtils.HostSystem + objUtils.abrirCaja); 
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
           if (null != fichero2)
              fichero2.close();
           } catch (Exception e2) {
              System.out.println(e2.toString());
              //e2.printStackTrace();
           }
        }
        /*FIN - ABRIR LA CAJA, MANDO A IMPRIMIR CODIGO ESPECIAL A LA IMPRESORA TMU*/
    String sistema_cupon = objParametros.consultaValor("cupones_habilitado");
    if(sistema_cupon.equals("1"))
    {       
        //CUPONES
        //REGISTRAR CUPONES
        Double valor = Double.parseDouble(txtValor.getText());
        double numero_cupones_double = valor / Double.parseDouble(objParametros.consultaValor("valor_minimo_cupones"));
        int numero_cupones = (int)numero_cupones_double;

        for (int i=0; i<numero_cupones; i++ )
        {
            objCupones.insertarCupon(i+1, codigo, 
                    idPago_publica, "ABONO FACTURA");
        }
        //IMPRIMIR CUPONES
        ArrayList<clsCupones> dataCupones = objCupones.consultarDataCupones(idPago_publica); 
        int maxData = dataCupones.size();

        //dataCompras.get(i).getEstadoTramite()
        //FileWriter fichero2 = null;
       // PrintWriter pw2 = null;
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
    JOptionPane.showMessageDialog(this, "Pago cobrado con éxito", "Atención!", JOptionPane.INFORMATION_MESSAGE);
    dispose();
    // TODO add your handling code here:
}//GEN-LAST:event_btnImprimirActionPerformed

private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    if(objPago.eliminarPagoFactura(idPago_publica))
        JOptionPane.showMessageDialog(this, "Pago eliminado con éxito", "Atención!", JOptionPane.INFORMATION_MESSAGE);
    else
        JOptionPane.showMessageDialog(this, "Error al eliminar pago", "Atención!", JOptionPane.INFORMATION_MESSAGE);
    dispose();
}//GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextArea txtReferencia;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
