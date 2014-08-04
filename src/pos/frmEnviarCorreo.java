/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pos;

import clases.clsCaja;
import clases.clsEgreso;
import clases.clsEmail;
import clases.clsParametros;
import clases.clsUtils;
import clases.javaMail;
import index.main;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ckaiser
 */
public class frmEnviarCorreo extends javax.swing.JDialog {
    clsUtils objUtils = new clsUtils();
    clsCaja objCaja = new clsCaja();
    clsEmail objEmail = new clsEmail();
    clsEgreso objEgreso = new clsEgreso();
    clsParametros objParametros = new clsParametros();
    /**
     * Creates new form frmEnviarCorreo
     */
    public frmEnviarCorreo(java.awt.Frame parent, boolean modal, int idCajaAbierta) {
        super(parent, modal);
        initComponents();
        ArrayList<clsCaja> dataCaja = objCaja.consultarDataOperacionesCajaID(idCajaAbierta); 
        javaMail mail = new javaMail();
        try{   
            double apertura = objUtils.redondear(dataCaja.get(0).getValorApertura());
            double facturado = objUtils.redondear(dataCaja.get(0).getTotalFacturado());
            double abonos = objUtils.redondear(dataCaja.get(0).getAbonos());
            double abonos_factura = objUtils.redondear(dataCaja.get(0).getValorPagosFactura());
            double abonos_entrada = objUtils.redondear(dataCaja.get(0).getRecibosPago());
            double ingresos = objUtils.redondear(dataCaja.get(0).getIngresos());
            double egresos = objUtils.redondear(dataCaja.get(0).getEgresos());
            double total_sistema = apertura + facturado + abonos + abonos_factura + abonos_entrada + ingresos - egresos;
            double total_ingresos = facturado + abonos + abonos_factura + abonos_entrada + ingresos;
            String texto = "EL USUARIO: " 
                        + main.nameUser
                        + ", CERRO CAJA CON DINERO EN EFECTIVO: $ " + dataCaja.get(0).getValorContado() + "</BR>"
                        + " SISTEMA: $ " + total_sistema + "</BR></BR>"
                        + " OBSERVACION: DIFERENCIA: " +  dataCaja.get(0).getDiferencia() + "</BR></BR></BR>"
                        + "<TABLE BORDER=\"1\">"
                            + "<TR><TD>DESCRIPCION</TD><TD>VALOR</TD></TR>"
                            + "<TR><TD>APERTURA CAJA:</TD><TD>" + apertura + "</TD></TR>"
                            + "<TR><TD>FACTURADO:</TD><TD>" + facturado + "</TD></TR>"
                            + "<TR><TD>ABONOS:</TD><TD>" + abonos + "</TD></TR>"
                            + "<TR><TD>ABONOS/FACT:</TD><TD>" + abonos_factura+ "</TD></TR>"
                            + "<TR><TD>ABONOS/ENTRADA:</TD><TD>" + abonos_entrada + "</TD></TR>"
                            + "<TR><TD>OTROS INGRESOS:</TD><TD>" + ingresos + "</TD></TR>"
                        + "</TABLE></BR>"
                        + "<TABLE BORDER=\"1\">"
                            + "<TR><TD>TOTAL INGRESOS:</TD><TD>" + total_ingresos + "</TD></TR>"  
                            + "<TR><TD>TOTAL EGRESOS:</TD><TD>" + egresos + "</TD></TR>"                
                        + "</TABLE></BR>";
            //INGRESOS
            ArrayList<clsEgreso> dataIngresos = objEgreso.consultaEgresosRealizadas(idCajaAbierta, "I"); 
            int maxDataIngresos = dataIngresos.size();
            String concepto = "";
            double totalIngresos = 0.00;
            if(maxDataIngresos>0)
            { 
                texto = texto + "<TABLE BORDER=\"1\">"
                + "<TR><TD>DESCRIPCION INGRESOS</TD><TD>VALOR</TD></TR>";
                
                for(int i=0;i<maxDataIngresos;i++)
                {                
                    //concepto = dataEgresos.get(i).getConcepto() + "                                         "; 
                    concepto = dataIngresos.get(i).getConcepto(); 
                    //texto = texto + "<TR><TD>" + concepto.substring(0, 28) + "</TD>" 
                    texto = texto + "<TR><TD>" + concepto + "</TD>" 
                            + "<TD>" + dataIngresos.get(i).getCantidadEgreso() + "</TD></TR>";
                                    
                    totalIngresos = totalIngresos + dataIngresos.get(i).getCantidadEgreso();                
                }
                texto = texto +"</TABLE>"
                        + "TOTAL INGRESOS: " + objUtils.redondear(totalIngresos) + "</BR></BR>";  
                
                
            }
            
            //EGRESOS
            ArrayList<clsEgreso> dataEgresos = objEgreso.consultaEgresosRealizadas(idCajaAbierta, "E"); 
            int maxDataEgresos = dataEgresos.size();
            //String concepto = "";
            concepto = "";
            double totalEgresos = 0.00;
            if(maxDataEgresos>0)
            { 
                texto = texto + "<TABLE BORDER=\"1\">"
                + "<TR><TD>DESCRIPCION EGRESOS</TD><TD>VALOR</TD></TR>";
                
                for(int i=0;i<maxDataEgresos;i++)
                {                
                    //concepto = dataEgresos.get(i).getConcepto() + "                                         "; 
                    concepto = dataEgresos.get(i).getConcepto(); 
                    //texto = texto + "<TR><TD>" + concepto.substring(0, 28) + "</TD>" 
                    texto = texto + "<TR><TD>" + concepto + "</TD>" 
                            + "<TD>" + dataEgresos.get(i).getCantidadEgreso() + "</TD></TR>";
                                    
                    totalEgresos = totalEgresos + dataEgresos.get(i).getCantidadEgreso();                
                }
                texto = texto +"</TABLE>"
                        + "TOTAL EGRESOS: " + objUtils.redondear(totalEgresos); 
                
            }
            texto = texto + objParametros.consultaValor("email_html_foot_kolozzus");
            ArrayList<clsEmail> dataEmail = objEmail.consultarEmails("2");        
            for(int i=0;i<dataEmail.size();i=i+1)
            {
                mail.send(dataEmail.get(i).getEmail(),"CIERRE DE CAJA", texto);
                System.out.println("SE ENVIO CORREO A: " + dataEmail.get(i).getEmail());
            }
            /*mail.send("vosthell@hotmail.com","CIERRE DE CAJA", texto);
            mail.send("c.kaiser.a@hotmail.com","CIERRE DE CAJA", texto);
            mail.send("betsuka@hotmail.com","CIERRE DE CAJA", texto);
            mail.send("jrmsupertodo@gmail.com","CIERRE DE CAJA", texto);
            mail.send("karl02@hotmail.es","CIERRE DE CAJA", texto);   
            
            mail.send("betsy.rizzo@comisariatosupertodo.com","CIERRE DE CAJA", texto);
            mail.send("jorge.rizzo@comisariatosupertodo.com","CIERRE DE CAJA", texto);  
            mail.send("betty.rodas@comisariatosupertodo.com","CIERRE DE CAJA", texto);
            mail.send("webmaster@comisariatosupertodo.com","CIERRE DE CAJA", texto);  */
            
        }
        catch(Exception e){
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error al imprimir", JOptionPane.ERROR_MESSAGE);
        }
        //this.dispose();
        btnOK.setEnabled(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setName("Form"); // NOI18N
        setResizable(false);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmEnviarCorreo.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        btnOK.setText(resourceMap.getString("btnOK.text")); // NOI18N
        btnOK.setEnabled(false);
        btnOK.setName("btnOK"); // NOI18N
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jLabel2)))
                .addContainerGap(224, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnOK)
                .addGap(131, 131, 131))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(btnOK)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnOKActionPerformed

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
            java.util.logging.Logger.getLogger(frmEnviarCorreo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEnviarCorreo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEnviarCorreo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEnviarCorreo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmEnviarCorreo dialog = new frmEnviarCorreo(new javax.swing.JFrame(), true,0);
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
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
