/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmPrincipal.java
 *
 * Created on 23-oct-2011, 16:17:10
 */
package stinventario;

import clases.clsCaja;
import clases.clsComboBox;
import clases.clsFacturero;
import clases.clsParametros;
import clases.clsPermisos;
import producto.frmProductosAdd;
import clases.clsUtils;
import proveedores.frmProveedorAdd;
import proveedores.frmProveedorDel;
import proveedores.frmProveedorMenu;
import proveedores.frmProveedorMod;
import clientes.frmClienteMod;
import clientes.frmClienteDel;
import clientes.frmClienteAdd;
import clientes.frmReporteSector;
import index.main;
import java.awt.Dimension;
import org.jdesktop.application.Action;
import clases.clsReporte;
import clientes.frmClienteIncobrable;
import clientes.frmReporteCartera;
import javax.swing.ImageIcon;
import pos.frmMenu;
import javax.swing.JOptionPane;
import pago.frmPagoHistoricoAdd;
import pos.frmFactHisto;
import producto.frmKardex;
import producto.frmProductosDel;
import producto.frmProductosMod;
import usuarios.frmClaveCambiar;
import usuarios.frmUsuarioPermisos;
import index.frmAcerca;
import index.frmRespaldarDB;
import index.frmAuditoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import nomina.frmAdelantos;
import pago.frmEstadoDeCuenta;
import pago.frmPagoAdd;
import pago.frmPagoAddOtros;
import pago.frmPagoNuevoAdd;
import pago.frmReimpresionReciboPago;
import pago.frmReimprimirCupones;
import pos.frmAbrirCaja;
import pos.frmCerrarCaja;
import pos.frmCerrarCaja_;
import pos.frmCotizador;
import pos.frmDevolucionVenta;
import pos.frmEgresoDinero;
import pos.frmFacturaSeek;
import pos.frmFacturar;
import pos.frmFacturasRealizadas;
import pos.frmFacturero;
import pos.frmIngresoDinero;
import pos.frmInventario;
import pos.frmListNotasEntrega;
import pos.frmNotasEntrega;
import pos.frmNotasEntrega1;
import pos.frmRecibosPago;
import pos.frmRecibosPagoGenerados;
import producto.frmCompras;
import producto.frmGrupoAdd;
import producto.frmGuiaRemision;
import producto.frmListCompras;
import producto.frmListProductosDel;
import producto.frmListProductosInventario;
import reportes.frmListDescuentos;
import reportes.frmListIncobrables;
import reportes.frmListInventarioSecciones;
import reportes.frmListInventariosRealizados;
import reportes.frmListOperacionesCaja;
import reportes.frmListPagos;
import reportes.frmListPagosPendientes;
import reportes.frmListVentas;
import reportes.frmListVentasNE;
import reportes.frmListVentasNEVendedor;
import reportes.frmReporteSemanal;
import usuarios.frmNuevoUsuario;

/**
 *
 * @author Kaiser
 */
public class frmPrincipal extends javax.swing.JFrame {    
    clsReporte objReporte = new clsReporte();
    clsUtils objUtils = new clsUtils();
    clsPermisos objPermisos = new clsPermisos();
    clsCaja objCaja = new clsCaja();
    clsFacturero objFacturero = new clsFacturero();
    clsParametros objParametros = new clsParametros();
    
    boolean permitido;
    
    
    /** Creates new form frmPrincipal */
    public frmPrincipal() {
        initComponents();
        
        this.setTitle(objUtils.nombreLargoSistema + " / " + objParametros.consultaNombreEmpresa() 
                + " - " + objParametros.consultaNombreCiudad());        
        lblUsuario.setText(main.nameUser);        
        jXTaskPane1.setTitle("Clientes");
        jXTaskPane2.setTitle("Proveedores");
        jXTaskPane3.setTitle("Productos");
        this.setIconImage(new ImageIcon(getClass().getResource("resources/iconoMain.png")).getImage()); 
        lblPendiente.setText("");
        boolean exito = objCaja.consultarCajaAbierta(index.main.idUser);
        if(exito)
        {
            lblPendiente.setText("TIENE PENDIENTE CERRAR CAJA");
            btnAbrir.setEnabled(false);  
            btnFacturar.setEnabled(true);
            btnPagos.setEnabled(true);
            btnCerrar.setEnabled(true); 
            btnEgreso.setEnabled(true); 
            btnIngreso.setEnabled(true); 
        }   
        btnCajaAntes.setVisible(false);
        
        //con esto si le quito eprmiso se actualiza de una
        /*Timer timer = new Timer (1000, new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                boolean exito = objCaja.consultarCajaAbierta(index.main.idUser);
                if(exito)
                {
                    lblPendiente.setText("TIENE PENDIENTE CERRAR CAJA");
                    btnAbrir.setEnabled(false);  
                    btnFacturar.setEnabled(true);
                    btnPagos.setEnabled(true);
                    btnCerrar.setEnabled(true); 
                    btnEgreso.setEnabled(true); 
                    btnIngreso.setEnabled(true); 
                    //btnRecibo.setEnabled(true);
                }   
                else
                {
                    lblPendiente.setText("");
                    btnAbrir.setEnabled(true);  
                    btnFacturar.setEnabled(false);
                    btnPagos.setEnabled(false);
                    btnCerrar.setEnabled(false); 
                    btnEgreso.setEnabled(false); 
                    btnIngreso.setEnabled(false); 
                    //btnRecibo.setEnabled(false);
                }
             }
        });


        timer.start();*/
        
        //consultar todos los permisos
        String nombreFormulario = "";
        ArrayList<clsPermisos> dataPermisos = objPermisos.consultarPermisosUsuario(Integer.parseInt(main.idUser));        
        
        //dejar todas invisibles
        mnFrmListVentas.setVisible(false);
        mnFrmListVentasNE.setVisible(false);
        mnFrmListVentasNEVendedor.setVisible(false); 
        mnFrmListDescuentos.setVisible(false);
        btnAbrir.setVisible(false);
        btnFacturar.setVisible(false);
        btnCerrar.setVisible(false);
        btnPagoNuevo.setVisible(false);
        btnIngreso.setVisible(false);
        btnPagos.setVisible(false);
        btnEgreso.setVisible(false);
        btnNE.setVisible(false);
        btnFacturarHistorico.setVisible(false);
        btnPagosHistoricos.setVisible(false);
        btnKardex.setVisible(false);
        prodMod.setVisible(false);
        mnProdMod.setVisible(false);
        prodDel.setVisible(false);
        mnProdDel.setVisible(false); 
        mnInventario.setVisible(false); 
        mnPermisos.setVisible(false);
        mnUsuario.setVisible(false);
        mnGrupoProductoAdd.setVisible(false);
        mnFlujoEfectivo.setVisible(false);
        mnEliminarProductos.setVisible(false);
        mnBuscarFacturasEliminar.setVisible(false);
        clienteMod.setVisible(false);
        mnCliMod.setVisible(false);
        clienteAdd.setVisible(false);
        mnClienteAdd.setVisible(false);
        clienteDel.setVisible(false);
        mnCliDel.setVisible(false);
        provMod.setVisible(false);
        mnProvMod.setVisible(false);
        provDel.setVisible(false);
        mnProvDel.setVisible(false);
        mnListadoProductos.setVisible(false);
        mnListPagos.setVisible(false);
        btnRecibo.setVisible(false);
        mnReimpresion.setVisible(false);
            
        //colocar visibles las q  tiene permisos
        for(int i=0;i<dataPermisos.size();i=i+1)
        {
            nombreFormulario = dataPermisos.get(i).getFormDescripcion();
            
            if(nombreFormulario.equals("frmListVentas"))            mnFrmListVentas.setVisible(true);                 
            if(nombreFormulario.equals("frmListVentasNE"))          mnFrmListVentasNE.setVisible(true);
            if(nombreFormulario.equals("frmListVentasNEVendedor"))  mnFrmListVentasNEVendedor.setVisible(true);
            if(nombreFormulario.equals("frmListDescuentos"))        mnFrmListDescuentos.setVisible(true);
            if(nombreFormulario.equals("frmAbrirCaja"))             btnAbrir.setVisible(true);
            if(nombreFormulario.equals("frmFacturar"))              btnFacturar.setVisible(true);
            if(nombreFormulario.equals("frmCerrarCaja"))            btnCerrar.setVisible(true);
            if(nombreFormulario.equals("frmPagoNuevoAdd"))          btnPagoNuevo.setVisible(true); 
            if(nombreFormulario.equals("frmCerrarCaja"))            btnIngreso.setVisible(true);
            if(nombreFormulario.equals("frmPagoAdd"))               btnPagos.setVisible(true);
            if(nombreFormulario.equals("frmCerrarCaja"))            btnEgreso.setVisible(true);
            if(nombreFormulario.equals("frmNotasEntrega"))          btnNE.setVisible(true);
            if(nombreFormulario.equals("frmFactHisto"))             btnFacturarHistorico.setVisible(true);
            if(nombreFormulario.equals("frmPagoHistoricoAdd"))      btnPagosHistoricos.setVisible(true);      
            if(nombreFormulario.equals("frmKardex"))                btnKardex.setVisible(true);
            if(nombreFormulario.equals("frmProductosMod")) {        prodMod.setVisible(true);
                                                                    mnProdMod.setVisible(true);}
            if(nombreFormulario.equals("frmProductosDel")) {        prodDel.setVisible(true);
                                                                    mnProdDel.setVisible(true);}
            if(nombreFormulario.equals("frmInventario"))            mnInventario.setVisible(true);
            if(nombreFormulario.equals("frmUsuarioPermisos"))       mnPermisos.setVisible(true);
            if(nombreFormulario.equals("frmNuevoUsuario"))          mnUsuario.setVisible(true);  
            if(nombreFormulario.equals("frmGrupoAdd"))              mnGrupoProductoAdd.setVisible(true);
            if(nombreFormulario.equals("frmCerrarCaja_"))           mnFlujoEfectivo.setVisible(true);
            if(nombreFormulario.equals("frmListProductosDel"))      mnEliminarProductos.setVisible(true);
            if(nombreFormulario.equals("frmFacturaSeek"))           mnBuscarFacturasEliminar.setVisible(true);
            if(nombreFormulario.equals("frmClienteMod"))       {    clienteMod.setVisible(true);
                                                                    mnCliMod.setVisible(true);}
            if(nombreFormulario.equals("frmClienteAdd"))       {    clienteAdd.setVisible(true);
                                                                    mnClienteAdd.setVisible(true);}
            if(nombreFormulario.equals("frmClienteDel"))       {    clienteDel.setVisible(true);
                                                                    mnCliDel.setVisible(true);}
            if(nombreFormulario.equals("frmProveedorMod"))       {  provMod.setVisible(true);
                                                                    mnProvMod.setVisible(true);}
            if(nombreFormulario.equals("frmProveedorDel")) {        provDel.setVisible(true);
                                                                    mnProvDel.setVisible(true);}
            if(nombreFormulario.equals("frmListProductosInventario"))mnListadoProductos.setVisible(true);
            if(nombreFormulario.equals("frmListPagos"))             mnListPagos.setVisible(true);
            if(nombreFormulario.equals("frmPagoAddOtros"))          btnRecibo.setVisible(true);
            if(nombreFormulario.equals("frmReimpresionReciboPago")) mnReimpresion.setVisible(true);         
     
        
        }
        
        /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmListVentas");
        if(permitido)
            mnFrmListVentas.setVisible(true);        
        else 
            mnFrmListVentas.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmListVentasNE");
        if(permitido)
            mnFrmListVentasNE.setVisible(true);
        else
            mnFrmListVentasNE.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmListVentasNEVendedor");
        if(permitido)
            mnFrmListVentasNEVendedor.setVisible(true);
        else
            mnFrmListVentasNEVendedor.setVisible(false);        
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmListDescuentos");
        if(permitido)
            mnFrmListDescuentos.setVisible(true);
        else
            mnFrmListDescuentos.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmAbrirCaja");
        if(permitido)
            btnAbrir.setVisible(true);
        else
            btnAbrir.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmFacturar");
        if(permitido)
            btnFacturar.setVisible(true);
        else
            btnFacturar.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmCerrarCaja");
        if(permitido)
            btnCerrar.setVisible(true);
        else
            btnCerrar.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmPagoNuevoAdd");
        if(permitido)
            btnPagoNuevo.setVisible(true);
        else
            btnPagoNuevo.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmCerrarCaja");
        if(permitido)
            btnIngreso.setVisible(true);
        else
            btnIngreso.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmPagoAdd");
        if(permitido)
            btnPagos.setVisible(true);
        else
            btnPagos.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmCerrarCaja");
        if(permitido)
            btnEgreso.setVisible(true);
        else
            btnEgreso.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmNotasEntrega");
        if(permitido)
            btnNE.setVisible(true);
        else
            btnNE.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmFactHisto");
        if(permitido)
            btnFacturarHistorico.setVisible(true);
        else
            btnFacturarHistorico.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmPagoHistoricoAdd");
        if(permitido)
            btnPagosHistoricos.setVisible(true);
        else
            btnPagosHistoricos.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmKardex");
        if(permitido)
            btnKardex.setVisible(true);
        else
            btnKardex.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmProductosMod");
        if(permitido)
        {
            prodMod.setVisible(true);
            mnProdMod.setVisible(true);
        }
        else
        {   
            prodMod.setVisible(false);
            mnProdMod.setVisible(false);
        }
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmProductosDel");
        if(permitido)
        {
            prodDel.setVisible(true);
            mnProdDel.setVisible(true);
        }
        else
        {
            prodDel.setVisible(false);
            mnProdDel.setVisible(false);            
        }
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmInventario");
        if(permitido)
        {
            mnInventario.setVisible(true);
        }
        else
        {
            
            mnInventario.setVisible(false);            
        }
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmUsuarioPermisos");
        if(permitido)
            mnPermisos.setVisible(true);
        else
            mnPermisos.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmNuevoUsuario");
        if(permitido)
            mnUsuario.setVisible(true);
        else
            mnUsuario.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmGrupoAdd");
        if(permitido)
            mnGrupoProductoAdd.setVisible(true);
        else
            mnGrupoProductoAdd.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmCerrarCaja_");
        if(permitido)
            mnFlujoEfectivo.setVisible(true);
        else
            mnFlujoEfectivo.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmListProductosDel");
        if(permitido)
            mnEliminarProductos.setVisible(true);
        else
            mnEliminarProductos.setVisible(false);
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmFacturaSeek");
        if(permitido)
            mnBuscarFacturasEliminar.setVisible(true);
        else
            mnBuscarFacturasEliminar.setVisible(false);
        
              
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmClienteMod");
        if(permitido)
        {
            clienteMod.setVisible(true);
            mnCliMod.setVisible(true);
        }
        else
        {
            clienteMod.setVisible(false);
            mnCliMod.setVisible(false);
        }
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmClienteAdd");
        if(permitido)
        {
            clienteAdd.setVisible(true);
            mnClienteAdd.setVisible(true);
        }
        else
        {
            clienteAdd.setVisible(false);
            mnClienteAdd.setVisible(false);
        }
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmClienteDel");
        if(permitido)
        {
            clienteDel.setVisible(true);
            mnCliDel.setVisible(true);
        }
        else
        {
            clienteDel.setVisible(false);
            mnCliDel.setVisible(false);
        }
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmProveedorMod");
        if(permitido)
        {
            provMod.setVisible(true);
            mnProvMod.setVisible(true);
        }
        else
        {
            provMod.setVisible(false);
            mnProvMod.setVisible(false);
        }
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmProveedorDel");
        if(permitido)
        {
            provDel.setVisible(true);
            mnProvDel.setVisible(true);
        }
        else
        {
            provDel.setVisible(false);
            mnProvDel.setVisible(false);
        }
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmListProductosInventario");
        if(permitido)
        {           
            mnListadoProductos.setVisible(true);
        }
        else
        {
            mnListadoProductos.setVisible(false);
        }
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmListPagos");
        if(permitido)
        {           
            mnListPagos.setVisible(true);
        }
        else
        {
            mnListPagos.setVisible(false);
        }
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmPagoAddOtros");
        if(permitido)
        {           
            btnRecibo.setVisible(true);
        }
        else
        {
            btnRecibo.setVisible(false);
        }
        
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmReimpresionReciboPago");
        if(permitido)
        {           
            mnReimpresion.setVisible(true);
        }
        else
        {
            mnReimpresion.setVisible(false);
        }*/
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
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnCajaAntes = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        btnIngreso = new javax.swing.JButton();
        btnPagos = new javax.swing.JButton();
        btnEgreso = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnNE = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btnFacturarHistorico = new javax.swing.JButton();
        btnPagosHistoricos = new javax.swing.JButton();
        btnRecibo = new javax.swing.JButton();
        btnPagoNuevo = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnKardex = new javax.swing.JButton();
        btnCompras = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        lblLogo = new javax.swing.JLabel();
        jXTaskPaneContainer2 = new org.jdesktop.swingx.JXTaskPaneContainer();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        clienteAdd = new org.jdesktop.swingx.JXHyperlink();
        clienteMod = new org.jdesktop.swingx.JXHyperlink();
        clienteDel = new org.jdesktop.swingx.JXHyperlink();
        jXTaskPane2 = new org.jdesktop.swingx.JXTaskPane();
        provAdd = new org.jdesktop.swingx.JXHyperlink();
        provMod = new org.jdesktop.swingx.JXHyperlink();
        provDel = new org.jdesktop.swingx.JXHyperlink();
        jXTaskPane3 = new org.jdesktop.swingx.JXTaskPane();
        prodAdd = new org.jdesktop.swingx.JXHyperlink();
        prodMod = new org.jdesktop.swingx.JXHyperlink();
        prodDel = new org.jdesktop.swingx.JXHyperlink();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblPendiente = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu15 = new javax.swing.JMenu();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        mnClienteAdd = new javax.swing.JMenuItem();
        mnCliMod = new javax.swing.JMenuItem();
        mnCliDel = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jMenu18 = new javax.swing.JMenu();
        jMenuItem47 = new javax.swing.JMenuItem();
        jMenuItem48 = new javax.swing.JMenuItem();
        mnReimpresion = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        mnProvMod = new javax.swing.JMenuItem();
        mnProvDel = new javax.swing.JMenuItem();
        jMenu14 = new javax.swing.JMenu();
        mnBuscarFacturasEliminar = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        mnFlujoEfectivo = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenu17 = new javax.swing.JMenu();
        mnFrmListVentas = new javax.swing.JMenuItem();
        mnFrmListVentasNE = new javax.swing.JMenuItem();
        mnFrmListVentasNEVendedor = new javax.swing.JMenuItem();
        mnFrmListDescuentos = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jMenu19 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        mnProdMod = new javax.swing.JMenuItem();
        mnProdDel = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        mnEliminarProductos = new javax.swing.JMenuItem();
        jMenu16 = new javax.swing.JMenu();
        mnListadoProductos = new javax.swing.JMenuItem();
        mnInventario = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        mnUsuario = new javax.swing.JMenuItem();
        mnPermisos = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem41 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem30 = new javax.swing.JMenuItem();
        mnGrupoProductoAdd = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        mnListPagos = new javax.swing.JMenuItem();
        mnOperacionesCaja = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem26 = new javax.swing.JMenuItem();

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel2.setName("jPanel2"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmPrincipal.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setLocationByPlatform(true);
        setName("Form"); // NOI18N

        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getActionMap(frmPrincipal.class, this);
        btnCajaAntes.setAction(actionMap.get("productos")); // NOI18N
        btnCajaAntes.setText(resourceMap.getString("btnCajaAntes.text")); // NOI18N
        btnCajaAntes.setFocusable(false);
        btnCajaAntes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCajaAntes.setName("btnCajaAntes"); // NOI18N
        btnCajaAntes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCajaAntes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCajaAntesActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCajaAntes);

        btnAbrir.setIcon(resourceMap.getIcon("btnAbrir.icon")); // NOI18N
        btnAbrir.setText(resourceMap.getString("btnAbrir.text")); // NOI18N
        btnAbrir.setFocusable(false);
        btnAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrir.setName("btnAbrir"); // NOI18N
        btnAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAbrir);

        btnFacturar.setIcon(resourceMap.getIcon("btnFacturar.icon")); // NOI18N
        btnFacturar.setText(resourceMap.getString("btnFacturar.text")); // NOI18N
        btnFacturar.setEnabled(false);
        btnFacturar.setFocusable(false);
        btnFacturar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFacturar.setName("btnFacturar"); // NOI18N
        btnFacturar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnFacturar);

        btnCerrar.setIcon(resourceMap.getIcon("btnCerrar.icon")); // NOI18N
        btnCerrar.setText(resourceMap.getString("btnCerrar.text")); // NOI18N
        btnCerrar.setEnabled(false);
        btnCerrar.setFocusable(false);
        btnCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrar.setName("btnCerrar"); // NOI18N
        btnCerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCerrar);

        jSeparator9.setName("jSeparator9"); // NOI18N
        jToolBar1.add(jSeparator9);

        btnIngreso.setIcon(resourceMap.getIcon("btnIngreso.icon")); // NOI18N
        btnIngreso.setText(resourceMap.getString("btnIngreso.text")); // NOI18N
        btnIngreso.setEnabled(false);
        btnIngreso.setFocusable(false);
        btnIngreso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIngreso.setName("btnIngreso"); // NOI18N
        btnIngreso.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnIngreso);

        btnPagos.setIcon(resourceMap.getIcon("btnPagos.icon")); // NOI18N
        btnPagos.setText(resourceMap.getString("btnPagos.text")); // NOI18N
        btnPagos.setEnabled(false);
        btnPagos.setFocusable(false);
        btnPagos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPagos.setName("btnPagos"); // NOI18N
        btnPagos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagosActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPagos);

        btnEgreso.setIcon(resourceMap.getIcon("btnEgreso.icon")); // NOI18N
        btnEgreso.setText(resourceMap.getString("btnEgreso.text")); // NOI18N
        btnEgreso.setEnabled(false);
        btnEgreso.setFocusable(false);
        btnEgreso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEgreso.setName("btnEgreso"); // NOI18N
        btnEgreso.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEgresoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEgreso);

        jButton7.setIcon(resourceMap.getIcon("jButton7.icon")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        btnNE.setIcon(resourceMap.getIcon("btnNE.icon")); // NOI18N
        btnNE.setText(resourceMap.getString("btnNE.text")); // NOI18N
        btnNE.setFocusable(false);
        btnNE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNE.setName("btnNE"); // NOI18N
        btnNE.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNEActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNE);

        jSeparator6.setName("jSeparator6"); // NOI18N
        jToolBar1.add(jSeparator6);

        btnFacturarHistorico.setIcon(resourceMap.getIcon("btnFacturarHistorico.icon")); // NOI18N
        btnFacturarHistorico.setText(resourceMap.getString("btnFacturarHistorico.text")); // NOI18N
        btnFacturarHistorico.setFocusable(false);
        btnFacturarHistorico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFacturarHistorico.setName("btnFacturarHistorico"); // NOI18N
        btnFacturarHistorico.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFacturarHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarHistoricoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnFacturarHistorico);

        btnPagosHistoricos.setIcon(resourceMap.getIcon("btnPagosHistoricos.icon")); // NOI18N
        btnPagosHistoricos.setText(resourceMap.getString("btnPagosHistoricos.text")); // NOI18N
        btnPagosHistoricos.setFocusable(false);
        btnPagosHistoricos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPagosHistoricos.setName("btnPagosHistoricos"); // NOI18N
        btnPagosHistoricos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPagosHistoricos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagosHistoricosActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPagosHistoricos);

        btnRecibo.setIcon(resourceMap.getIcon("btnRecibo.icon")); // NOI18N
        btnRecibo.setText(resourceMap.getString("btnRecibo.text")); // NOI18N
        btnRecibo.setFocusable(false);
        btnRecibo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRecibo.setName("btnRecibo"); // NOI18N
        btnRecibo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReciboActionPerformed(evt);
            }
        });
        jToolBar1.add(btnRecibo);

        btnPagoNuevo.setIcon(resourceMap.getIcon("btnPagoNuevo.icon")); // NOI18N
        btnPagoNuevo.setText(resourceMap.getString("btnPagoNuevo.text")); // NOI18N
        btnPagoNuevo.setFocusable(false);
        btnPagoNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPagoNuevo.setName("btnPagoNuevo"); // NOI18N
        btnPagoNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPagoNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoNuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPagoNuevo);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jToolBar1.add(jSeparator1);

        btnKardex.setIcon(resourceMap.getIcon("btnKardex.icon")); // NOI18N
        btnKardex.setText(resourceMap.getString("btnKardex.text")); // NOI18N
        btnKardex.setFocusable(false);
        btnKardex.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKardex.setName("btnKardex"); // NOI18N
        btnKardex.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKardex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKardexActionPerformed(evt);
            }
        });
        jToolBar1.add(btnKardex);

        btnCompras.setIcon(resourceMap.getIcon("btnCompras.icon")); // NOI18N
        btnCompras.setText(resourceMap.getString("btnCompras.text")); // NOI18N
        btnCompras.setFocusable(false);
        btnCompras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompras.setName("btnCompras"); // NOI18N
        btnCompras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprasActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCompras);

        jSeparator7.setName("jSeparator7"); // NOI18N
        jToolBar1.add(jSeparator7);

        jSeparator8.setName("jSeparator8"); // NOI18N
        jToolBar1.add(jSeparator8);

        jButton2.setAction(actionMap.get("quit")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jDesktopPane1.setBackground(resourceMap.getColor("jDesktopPane1.background")); // NOI18N
        jDesktopPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jDesktopPane1.setName("jDesktopPane1"); // NOI18N
        jDesktopPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jDesktopPane1ComponentResized(evt);
            }
        });

        filler1.setName("filler1"); // NOI18N
        filler1.setBounds(240, 110, 0, 0);
        jDesktopPane1.add(filler1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(resourceMap.getIcon("lblLogo.icon")); // NOI18N
        lblLogo.setText(resourceMap.getString("lblLogo.text")); // NOI18N
        lblLogo.setAlignmentX(javax.swing.SwingConstants.CENTER);
        lblLogo.setAlignmentY(javax.swing.SwingConstants.CENTER);
        lblLogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLogo.setName("lblLogo"); // NOI18N
        lblLogo.setBounds(10, -90, 640, 510);
        jDesktopPane1.add(lblLogo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jXTaskPaneContainer2.setName("jXTaskPaneContainer2"); // NOI18N
        org.jdesktop.swingx.VerticalLayout verticalLayout1 = new org.jdesktop.swingx.VerticalLayout();
        verticalLayout1.setGap(14);
        jXTaskPaneContainer2.setLayout(verticalLayout1);

        jXTaskPane1.setName("jXTaskPane1"); // NOI18N

        clienteAdd.setIcon(resourceMap.getIcon("clienteAdd.icon")); // NOI18N
        clienteAdd.setText(resourceMap.getString("clienteAdd.text")); // NOI18N
        clienteAdd.setName("clienteAdd"); // NOI18N
        clienteAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteAddActionPerformed(evt);
            }
        });
        jXTaskPane1.add(clienteAdd);

        clienteMod.setIcon(resourceMap.getIcon("clienteMod.icon")); // NOI18N
        clienteMod.setText(resourceMap.getString("clienteMod.text")); // NOI18N
        clienteMod.setName("clienteMod"); // NOI18N
        clienteMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteModActionPerformed(evt);
            }
        });
        jXTaskPane1.add(clienteMod);

        clienteDel.setIcon(resourceMap.getIcon("clienteDel.icon")); // NOI18N
        clienteDel.setText(resourceMap.getString("clienteDel.text")); // NOI18N
        clienteDel.setName("clienteDel"); // NOI18N
        clienteDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteDelActionPerformed(evt);
            }
        });
        jXTaskPane1.add(clienteDel);

        jXTaskPaneContainer2.add(jXTaskPane1);

        jXTaskPane2.setName("jXTaskPane2"); // NOI18N

        provAdd.setIcon(resourceMap.getIcon("provAdd.icon")); // NOI18N
        provAdd.setText(resourceMap.getString("provAdd.text")); // NOI18N
        provAdd.setName("provAdd"); // NOI18N
        provAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provAddActionPerformed(evt);
            }
        });
        jXTaskPane2.add(provAdd);

        provMod.setIcon(resourceMap.getIcon("provMod.icon")); // NOI18N
        provMod.setText(resourceMap.getString("provMod.text")); // NOI18N
        provMod.setName("provMod"); // NOI18N
        provMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provModActionPerformed(evt);
            }
        });
        jXTaskPane2.add(provMod);

        provDel.setIcon(resourceMap.getIcon("provDel.icon")); // NOI18N
        provDel.setText(resourceMap.getString("provDel.text")); // NOI18N
        provDel.setName("provDel"); // NOI18N
        provDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provDelActionPerformed(evt);
            }
        });
        jXTaskPane2.add(provDel);

        jXTaskPaneContainer2.add(jXTaskPane2);

        jXTaskPane3.setName("jXTaskPane3"); // NOI18N

        prodAdd.setIcon(resourceMap.getIcon("prodAdd.icon")); // NOI18N
        prodAdd.setText(resourceMap.getString("prodAdd.text")); // NOI18N
        prodAdd.setName("prodAdd"); // NOI18N
        prodAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prodAddActionPerformed(evt);
            }
        });
        jXTaskPane3.add(prodAdd);

        prodMod.setIcon(resourceMap.getIcon("prodMod.icon")); // NOI18N
        prodMod.setText(resourceMap.getString("prodMod.text")); // NOI18N
        prodMod.setName("prodMod"); // NOI18N
        prodMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prodModActionPerformed(evt);
            }
        });
        jXTaskPane3.add(prodMod);

        prodDel.setIcon(resourceMap.getIcon("prodDel.icon")); // NOI18N
        prodDel.setText(resourceMap.getString("prodDel.text")); // NOI18N
        prodDel.setName("prodDel"); // NOI18N
        prodDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prodDelActionPerformed(evt);
            }
        });
        jXTaskPane3.add(prodDel);

        jXTaskPaneContainer2.add(jXTaskPane3);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        lblUsuario.setText(resourceMap.getString("lblUsuario.text")); // NOI18N
        lblUsuario.setName("lblUsuario"); // NOI18N

        lblPendiente.setFont(resourceMap.getFont("lblPendiente.font")); // NOI18N
        lblPendiente.setForeground(resourceMap.getColor("lblPendiente.foreground")); // NOI18N
        lblPendiente.setIcon(resourceMap.getIcon("lblPendiente.icon")); // NOI18N
        lblPendiente.setText(resourceMap.getString("lblPendiente.text")); // NOI18N
        lblPendiente.setName("lblPendiente"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUsuario)
                .addGap(31, 31, 31)
                .addComponent(lblPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(642, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPendiente)
                    .addComponent(jLabel1)
                    .addComponent(lblUsuario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMenu15.setText(resourceMap.getString("jMenu15.text")); // NOI18N
        jMenu15.setName("jMenu15"); // NOI18N

        jMenuItem32.setText(resourceMap.getString("jMenuItem32.text")); // NOI18N
        jMenuItem32.setName("jMenuItem32"); // NOI18N
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem32);

        jMenu1.add(jMenu15);

        jMenuItem1.setIcon(resourceMap.getIcon("jMenuItem1.icon")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu5.setText(resourceMap.getString("jMenu5.text")); // NOI18N
        jMenu5.setName("jMenu5"); // NOI18N

        mnClienteAdd.setIcon(resourceMap.getIcon("mnClienteAdd.icon")); // NOI18N
        mnClienteAdd.setText(resourceMap.getString("mnClienteAdd.text")); // NOI18N
        mnClienteAdd.setName("mnClienteAdd"); // NOI18N
        mnClienteAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnClienteAddActionPerformed(evt);
            }
        });
        jMenu5.add(mnClienteAdd);

        mnCliMod.setIcon(resourceMap.getIcon("mnCliMod.icon")); // NOI18N
        mnCliMod.setText(resourceMap.getString("mnCliMod.text")); // NOI18N
        mnCliMod.setName("mnCliMod"); // NOI18N
        mnCliMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCliModActionPerformed(evt);
            }
        });
        jMenu5.add(mnCliMod);

        mnCliDel.setIcon(resourceMap.getIcon("mnCliDel.icon")); // NOI18N
        mnCliDel.setText(resourceMap.getString("mnCliDel.text")); // NOI18N
        mnCliDel.setName("mnCliDel"); // NOI18N
        mnCliDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCliDelActionPerformed(evt);
            }
        });
        jMenu5.add(mnCliDel);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jMenu5.add(jSeparator2);

        jMenu6.setIcon(resourceMap.getIcon("jMenu6.icon")); // NOI18N
        jMenu6.setText(resourceMap.getString("jMenu6.text")); // NOI18N
        jMenu6.setName("jMenu6"); // NOI18N

        jMenuItem10.setIcon(resourceMap.getIcon("jMenuItem10.icon")); // NOI18N
        jMenuItem10.setText(resourceMap.getString("jMenuItem10.text")); // NOI18N
        jMenuItem10.setName("jMenuItem10"); // NOI18N
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem10);

        jMenuItem13.setIcon(resourceMap.getIcon("jMenuItem13.icon")); // NOI18N
        jMenuItem13.setText(resourceMap.getString("jMenuItem13.text")); // NOI18N
        jMenuItem13.setName("jMenuItem13"); // NOI18N
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem13);

        jMenuItem42.setIcon(resourceMap.getIcon("jMenuItem42.icon")); // NOI18N
        jMenuItem42.setText(resourceMap.getString("jMenuItem42.text")); // NOI18N
        jMenuItem42.setName("jMenuItem42"); // NOI18N
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem42);

        jMenu5.add(jMenu6);

        jSeparator13.setName("jSeparator13"); // NOI18N
        jMenu5.add(jSeparator13);

        jMenu18.setText(resourceMap.getString("jMenu18.text")); // NOI18N
        jMenu18.setName("jMenu18"); // NOI18N

        jMenuItem47.setText(resourceMap.getString("jMenuItem47.text")); // NOI18N
        jMenuItem47.setName("jMenuItem47"); // NOI18N
        jMenuItem47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem47ActionPerformed(evt);
            }
        });
        jMenu18.add(jMenuItem47);

        jMenuItem48.setText(resourceMap.getString("jMenuItem48.text")); // NOI18N
        jMenuItem48.setName("jMenuItem48"); // NOI18N
        jMenuItem48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem48ActionPerformed(evt);
            }
        });
        jMenu18.add(jMenuItem48);

        jMenu5.add(jMenu18);

        mnReimpresion.setText(resourceMap.getString("mnReimpresion.text")); // NOI18N
        mnReimpresion.setName("mnReimpresion"); // NOI18N
        mnReimpresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnReimpresionActionPerformed(evt);
            }
        });
        jMenu5.add(mnReimpresion);

        jMenuBar1.add(jMenu5);

        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N

        jMenuItem2.setIcon(resourceMap.getIcon("jMenuItem2.icon")); // NOI18N
        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        mnProvMod.setIcon(resourceMap.getIcon("mnProvMod.icon")); // NOI18N
        mnProvMod.setText(resourceMap.getString("mnProvMod.text")); // NOI18N
        mnProvMod.setName("mnProvMod"); // NOI18N
        mnProvMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnProvModActionPerformed(evt);
            }
        });
        jMenu2.add(mnProvMod);

        mnProvDel.setIcon(resourceMap.getIcon("mnProvDel.icon")); // NOI18N
        mnProvDel.setText(resourceMap.getString("mnProvDel.text")); // NOI18N
        mnProvDel.setName("mnProvDel"); // NOI18N
        mnProvDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnProvDelActionPerformed(evt);
            }
        });
        jMenu2.add(mnProvDel);

        jMenuBar1.add(jMenu2);

        jMenu14.setText(resourceMap.getString("jMenu14.text")); // NOI18N
        jMenu14.setName("jMenu14"); // NOI18N
        jMenu14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu14ActionPerformed(evt);
            }
        });

        mnBuscarFacturasEliminar.setText(resourceMap.getString("mnBuscarFacturasEliminar.text")); // NOI18N
        mnBuscarFacturasEliminar.setName("mnBuscarFacturasEliminar"); // NOI18N
        mnBuscarFacturasEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBuscarFacturasEliminarActionPerformed(evt);
            }
        });
        jMenu14.add(mnBuscarFacturasEliminar);

        jMenuItem33.setText(resourceMap.getString("jMenuItem33.text")); // NOI18N
        jMenuItem33.setName("jMenuItem33"); // NOI18N
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem33);

        jMenuItem35.setText(resourceMap.getString("jMenuItem35.text")); // NOI18N
        jMenuItem35.setName("jMenuItem35"); // NOI18N
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem35);

        jMenuItem36.setText(resourceMap.getString("jMenuItem36.text")); // NOI18N
        jMenuItem36.setName("jMenuItem36"); // NOI18N
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem36);

        jSeparator10.setName("jSeparator10"); // NOI18N
        jMenu14.add(jSeparator10);

        mnFlujoEfectivo.setText(resourceMap.getString("mnFlujoEfectivo.text")); // NOI18N
        mnFlujoEfectivo.setName("mnFlujoEfectivo"); // NOI18N
        mnFlujoEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFlujoEfectivoActionPerformed(evt);
            }
        });
        jMenu14.add(mnFlujoEfectivo);

        jMenuItem38.setText(resourceMap.getString("jMenuItem38.text")); // NOI18N
        jMenuItem38.setName("jMenuItem38"); // NOI18N
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem38);

        jSeparator11.setName("jSeparator11"); // NOI18N
        jMenu14.add(jSeparator11);

        jMenu17.setText(resourceMap.getString("jMenu17.text")); // NOI18N
        jMenu17.setName("jMenu17"); // NOI18N

        mnFrmListVentas.setText(resourceMap.getString("mnFrmListVentas.text")); // NOI18N
        mnFrmListVentas.setName("mnFrmListVentas"); // NOI18N
        mnFrmListVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFrmListVentasActionPerformed(evt);
            }
        });
        jMenu17.add(mnFrmListVentas);

        mnFrmListVentasNE.setText(resourceMap.getString("mnFrmListVentasNE.text")); // NOI18N
        mnFrmListVentasNE.setName("mnFrmListVentasNE"); // NOI18N
        mnFrmListVentasNE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFrmListVentasNEActionPerformed(evt);
            }
        });
        jMenu17.add(mnFrmListVentasNE);

        mnFrmListVentasNEVendedor.setText(resourceMap.getString("mnFrmListVentasNEVendedor.text")); // NOI18N
        mnFrmListVentasNEVendedor.setName("mnFrmListVentasNEVendedor"); // NOI18N
        mnFrmListVentasNEVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFrmListVentasNEVendedorActionPerformed(evt);
            }
        });
        jMenu17.add(mnFrmListVentasNEVendedor);

        mnFrmListDescuentos.setText(resourceMap.getString("mnFrmListDescuentos.text")); // NOI18N
        mnFrmListDescuentos.setName("mnFrmListDescuentos"); // NOI18N
        mnFrmListDescuentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFrmListDescuentosActionPerformed(evt);
            }
        });
        jMenu17.add(mnFrmListDescuentos);

        jMenu14.add(jMenu17);

        jSeparator12.setName("jSeparator12"); // NOI18N
        jMenu14.add(jSeparator12);

        jMenu19.setText(resourceMap.getString("jMenu19.text")); // NOI18N
        jMenu19.setName("jMenu19"); // NOI18N

        jMenuItem7.setText(resourceMap.getString("jMenuItem7.text")); // NOI18N
        jMenuItem7.setName("jMenuItem7"); // NOI18N
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu19.add(jMenuItem7);

        jMenuItem8.setText(resourceMap.getString("jMenuItem8.text")); // NOI18N
        jMenuItem8.setName("jMenuItem8"); // NOI18N
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu19.add(jMenuItem8);

        jMenu14.add(jMenu19);

        jMenuBar1.add(jMenu14);

        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem3.setIcon(resourceMap.getIcon("jMenuItem3.icon")); // NOI18N
        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        mnProdMod.setIcon(resourceMap.getIcon("mnProdMod.icon")); // NOI18N
        mnProdMod.setText(resourceMap.getString("mnProdMod.text")); // NOI18N
        mnProdMod.setName("mnProdMod"); // NOI18N
        mnProdMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnProdModActionPerformed(evt);
            }
        });
        jMenu3.add(mnProdMod);

        mnProdDel.setIcon(resourceMap.getIcon("mnProdDel.icon")); // NOI18N
        mnProdDel.setText(resourceMap.getString("mnProdDel.text")); // NOI18N
        mnProdDel.setName("mnProdDel"); // NOI18N
        mnProdDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnProdDelActionPerformed(evt);
            }
        });
        jMenu3.add(mnProdDel);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jMenu3.add(jSeparator4);

        jMenuItem18.setText(resourceMap.getString("jMenuItem18.text")); // NOI18N
        jMenuItem18.setName("jMenuItem18"); // NOI18N
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem18);

        jMenuItem23.setText(resourceMap.getString("jMenuItem23.text")); // NOI18N
        jMenuItem23.setName("jMenuItem23"); // NOI18N
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem23);

        mnEliminarProductos.setText(resourceMap.getString("mnEliminarProductos.text")); // NOI18N
        mnEliminarProductos.setName("mnEliminarProductos"); // NOI18N
        mnEliminarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnEliminarProductosActionPerformed(evt);
            }
        });
        jMenu3.add(mnEliminarProductos);

        jMenu16.setText(resourceMap.getString("jMenu16.text")); // NOI18N
        jMenu16.setName("jMenu16"); // NOI18N

        mnListadoProductos.setText(resourceMap.getString("mnListadoProductos.text")); // NOI18N
        mnListadoProductos.setName("mnListadoProductos"); // NOI18N
        mnListadoProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnListadoProductosActionPerformed(evt);
            }
        });
        jMenu16.add(mnListadoProductos);

        mnInventario.setText(resourceMap.getString("mnInventario.text")); // NOI18N
        mnInventario.setName("mnInventario"); // NOI18N
        mnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnInventarioActionPerformed(evt);
            }
        });
        jMenu16.add(mnInventario);

        jMenuItem12.setText(resourceMap.getString("jMenuItem12.text")); // NOI18N
        jMenuItem12.setName("jMenuItem12"); // NOI18N
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem12);

        jMenuItem11.setText(resourceMap.getString("jMenuItem11.text")); // NOI18N
        jMenuItem11.setName("jMenuItem11"); // NOI18N
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem11);

        jMenu3.add(jMenu16);

        jMenuItem6.setText(resourceMap.getString("jMenuItem6.text")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        jMenu13.setText(resourceMap.getString("jMenu13.text")); // NOI18N
        jMenu13.setName("jMenu13"); // NOI18N

        jMenuItem28.setIcon(resourceMap.getIcon("jMenuItem28.icon")); // NOI18N
        jMenuItem28.setText(resourceMap.getString("jMenuItem28.text")); // NOI18N
        jMenuItem28.setName("jMenuItem28"); // NOI18N
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem28);

        jMenuItem39.setText(resourceMap.getString("jMenuItem39.text")); // NOI18N
        jMenuItem39.setName("jMenuItem39"); // NOI18N
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem39);

        jMenuBar1.add(jMenu13);

        jMenu7.setText(resourceMap.getString("jMenu7.text")); // NOI18N
        jMenu7.setName("jMenu7"); // NOI18N

        mnUsuario.setText(resourceMap.getString("mnUsuario.text")); // NOI18N
        mnUsuario.setName("mnUsuario"); // NOI18N
        mnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnUsuarioActionPerformed(evt);
            }
        });
        jMenu7.add(mnUsuario);

        mnPermisos.setText(resourceMap.getString("mnPermisos.text")); // NOI18N
        mnPermisos.setName("mnPermisos"); // NOI18N
        mnPermisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPermisosActionPerformed(evt);
            }
        });
        jMenu7.add(mnPermisos);

        jSeparator5.setName("jSeparator5"); // NOI18N
        jMenu7.add(jSeparator5);

        jMenuItem19.setIcon(resourceMap.getIcon("jMenuItem19.icon")); // NOI18N
        jMenuItem19.setText(resourceMap.getString("jMenuItem19.text")); // NOI18N
        jMenuItem19.setName("jMenuItem19"); // NOI18N
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem19);

        jMenuItem16.setText(resourceMap.getString("jMenuItem16.text")); // NOI18N
        jMenuItem16.setName("jMenuItem16"); // NOI18N
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem16);

        jMenuBar1.add(jMenu7);

        jMenu9.setText(resourceMap.getString("jMenu9.text")); // NOI18N
        jMenu9.setName("jMenu9"); // NOI18N

        jMenu8.setIcon(resourceMap.getIcon("jMenu8.icon")); // NOI18N
        jMenu8.setText(resourceMap.getString("jMenu8.text")); // NOI18N
        jMenu8.setName("jMenu8"); // NOI18N

        jMenuItem20.setText(resourceMap.getString("jMenuItem20.text")); // NOI18N
        jMenuItem20.setName("jMenuItem20"); // NOI18N
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem20);

        jMenuItem21.setText(resourceMap.getString("jMenuItem21.text")); // NOI18N
        jMenuItem21.setName("jMenuItem21"); // NOI18N
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem21);

        jMenuItem41.setText(resourceMap.getString("jMenuItem41.text")); // NOI18N
        jMenuItem41.setName("jMenuItem41"); // NOI18N
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem41);

        jMenu9.add(jMenu8);

        jMenuItem15.setText(resourceMap.getString("jMenuItem15.text")); // NOI18N
        jMenuItem15.setName("jMenuItem15"); // NOI18N
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem15);

        jMenuBar1.add(jMenu9);

        jMenu11.setText(resourceMap.getString("jMenu11.text")); // NOI18N
        jMenu11.setName("jMenu11"); // NOI18N

        jMenuItem25.setText(resourceMap.getString("jMenuItem25.text")); // NOI18N
        jMenuItem25.setName("jMenuItem25"); // NOI18N
        jMenu11.add(jMenuItem25);

        jMenuItem24.setText(resourceMap.getString("jMenuItem24.text")); // NOI18N
        jMenuItem24.setName("jMenuItem24"); // NOI18N
        jMenu11.add(jMenuItem24);

        jMenuItem17.setText(resourceMap.getString("jMenuItem17.text")); // NOI18N
        jMenuItem17.setName("jMenuItem17"); // NOI18N
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem17);

        jMenuBar1.add(jMenu11);

        jMenu12.setText(resourceMap.getString("jMenu12.text")); // NOI18N
        jMenu12.setName("jMenu12"); // NOI18N

        jMenuItem30.setText(resourceMap.getString("jMenuItem30.text")); // NOI18N
        jMenuItem30.setName("jMenuItem30"); // NOI18N
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem30);

        mnGrupoProductoAdd.setText(resourceMap.getString("mnGrupoProductoAdd.text")); // NOI18N
        mnGrupoProductoAdd.setName("mnGrupoProductoAdd"); // NOI18N
        mnGrupoProductoAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGrupoProductoAddActionPerformed(evt);
            }
        });
        jMenu12.add(mnGrupoProductoAdd);

        jMenuItem34.setText(resourceMap.getString("jMenuItem34.text")); // NOI18N
        jMenuItem34.setName("jMenuItem34"); // NOI18N
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem34);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jMenu12.add(jSeparator3);

        jMenuItem29.setIcon(resourceMap.getIcon("jMenuItem29.icon")); // NOI18N
        jMenuItem29.setText(resourceMap.getString("jMenuItem29.text")); // NOI18N
        jMenuItem29.setName("jMenuItem29"); // NOI18N
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem29);

        jMenuBar1.add(jMenu12);

        jMenu4.setText(resourceMap.getString("jMenu4.text")); // NOI18N
        jMenu4.setName("jMenu4"); // NOI18N

        jMenuItem4.setIcon(resourceMap.getIcon("jMenuItem4.icon")); // NOI18N
        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setIcon(resourceMap.getIcon("jMenuItem5.icon")); // NOI18N
        jMenuItem5.setText(resourceMap.getString("jMenuItem5.text")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem9.setIcon(resourceMap.getIcon("jMenuItem9.icon")); // NOI18N
        jMenuItem9.setText(resourceMap.getString("jMenuItem9.text")); // NOI18N
        jMenuItem9.setName("jMenuItem9"); // NOI18N
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuItem22.setText(resourceMap.getString("jMenuItem22.text")); // NOI18N
        jMenuItem22.setName("jMenuItem22"); // NOI18N
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem22);

        mnListPagos.setText(resourceMap.getString("mnListPagos.text")); // NOI18N
        mnListPagos.setName("mnListPagos"); // NOI18N
        mnListPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnListPagosActionPerformed(evt);
            }
        });
        jMenu4.add(mnListPagos);

        mnOperacionesCaja.setText(resourceMap.getString("mnOperacionesCaja.text")); // NOI18N
        mnOperacionesCaja.setName("mnOperacionesCaja"); // NOI18N
        mnOperacionesCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOperacionesCajaActionPerformed(evt);
            }
        });
        jMenu4.add(mnOperacionesCaja);

        jSeparator14.setName("jSeparator14"); // NOI18N
        jMenu4.add(jSeparator14);

        jMenuItem14.setText(resourceMap.getString("jMenuItem14.text")); // NOI18N
        jMenuItem14.setName("jMenuItem14"); // NOI18N
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem14);

        jMenuBar1.add(jMenu4);

        jMenu10.setText(resourceMap.getString("jMenu10.text")); // NOI18N
        jMenu10.setName("jMenu10"); // NOI18N

        jMenuItem26.setIcon(resourceMap.getIcon("jMenuItem26.icon")); // NOI18N
        jMenuItem26.setText(resourceMap.getString("jMenuItem26.text")); // NOI18N
        jMenuItem26.setName("jMenuItem26"); // NOI18N
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem26);

        jMenuBar1.add(jMenu10);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXTaskPaneContainer2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1233, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jXTaskPaneContainer2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    objReporte.ejecutarReporte("rptProveedores");
    
}//GEN-LAST:event_jMenuItem4ActionPerformed

    void mostrarIngresoCliente()
    {
        frmClienteAdd formulario = new frmClienteAdd();
        mostrarJInternalCentrado(formulario); 
    }

    void mostrarModificarCliente()
    {
        frmClienteMod formulario = new frmClienteMod();
        mostrarJInternalCentrado(formulario); 
    }

    void mostrarIngresoProveedores()
    {
        frmProveedorAdd formulario = new frmProveedorAdd();
        mostrarJInternalCentrado(formulario); 
    }  

    void mostrarIngresoProductos()
    {
        frmProductosAdd formulario = new frmProductosAdd();
        mostrarJInternalCentrado(formulario); 
    }

    void mostrarProductoMod()
    {
        /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmProductosMod");
        if(permitido)
        {  */ 
            frmProductosMod formulario = new frmProductosMod();    
            mostrarJInternalCentrado(formulario); 
        /*}
        else
        {
            JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
        }  */  
    }
    
    void mostrarCompras()
    {
        frmCompras formulario = new frmCompras();
        mostrarJInternalCentrado(formulario); 
    }
    
    void mostrarProductoDel()
    {
        /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmProductosDel");
        if(permitido)
        { */  
            frmProductosDel formulario = new frmProductosDel();    
            mostrarJInternalCentrado(formulario); 
        /*}
        else
        {
            JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
        } */   
    }

    void mostrarEliminarCliente()
    {
        frmClienteDel formulario = new frmClienteDel();
        mostrarJInternalCentrado(formulario); 
    }

    void mostrarEliminarProveedor()
    {
        frmProveedorDel formulario = new frmProveedorDel();    
        mostrarJInternalCentrado(formulario); 
    }

    void mostrarAsignacionPermisos()
    {
        /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmUsuarioPermisos");
        if(permitido)
        {  */ 
            frmUsuarioPermisos formulario = new frmUsuarioPermisos();
            mostrarJInternalCentrado(formulario);
        /*}
        else
        {
            JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
        }*/
    }
    
    void mostrarPagosHistorico()
    {
        /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmPagoHistoricoAdd");
        if(permitido)
        { */ 
            frmPagoHistoricoAdd formulario = new frmPagoHistoricoAdd();
            mostrarJInternalCentrado(formulario); 
        /*}
        else
        {
            JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
        }*/
    }
    
    void mostrarKardex()
    {
        /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmKardex");
        if(permitido)
        {  */
            frmKardex formulario = new frmKardex();
            mostrarJInternalCentrado(formulario); 
       /* }
        else
        {
            JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
        }*/
    }
     
    void mostrarInicioCaja()
    {
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmFactHisto");
        if(permitido)
        {  
            frmMenu formulario = new frmMenu();
            mostrarJInternalCentrado(formulario); 
        }
        else
        {
            JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
        }   
    }
    
    void mostrarFacturarHistorico()
    {
        /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmFactHisto");
        if(permitido)
        {  */
            /*frmFactHisto formulario = new frmFactHisto(this, true);
            formulario.setLocationRelativeTo(null);
            formulario.setVisible(true);*/
            frmFactHisto formulario = new frmFactHisto();
            mostrarJInternalCentrado(formulario); 
        /*}
        else
        {
            JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
        } */   
    }
    
    void mostrarReportePorSector()
    {
        frmReporteSector formulario = new frmReporteSector();
        mostrarJInternalCentrado(formulario);    
    }
    
    void mostrarCambioClave()
    {
        frmClaveCambiar formulario = new frmClaveCambiar();
        mostrarJInternalCentrado(formulario);    
    }

    public static void mostrarJInternalCentrado(javax.swing.JInternalFrame formulario)
    {
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = formulario.getSize();
        formulario.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
        (desktopSize.height- jInternalFrameSize.height)/2);

        jDesktopPane1.add(formulario);
        formulario.show(); 
    }

private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    objReporte.ejecutarReporte("rptProductos");
}//GEN-LAST:event_jMenuItem5ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    mostrarIngresoProductos();
}//GEN-LAST:event_jMenuItem3ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    mostrarIngresoProveedores();
}//GEN-LAST:event_jMenuItem2ActionPerformed

private void clienteAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteAddActionPerformed
     mostrarIngresoCliente();
}//GEN-LAST:event_clienteAddActionPerformed

    private void jDesktopPane1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDesktopPane1ComponentResized
        lblLogo.setLocation((jDesktopPane1.getWidth() - lblLogo.getWidth())/2, 
        (jDesktopPane1.getHeight() - lblLogo.getHeight())/2);
    }//GEN-LAST:event_jDesktopPane1ComponentResized

private void mnClienteAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnClienteAddActionPerformed
    mostrarIngresoCliente();
}//GEN-LAST:event_mnClienteAddActionPerformed

    private void mnCliModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCliModActionPerformed
        mostrarModificarCliente();
    }//GEN-LAST:event_mnCliModActionPerformed

private void clienteModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteModActionPerformed
        mostrarModificarCliente();
}//GEN-LAST:event_clienteModActionPerformed

private void provAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provAddActionPerformed
        mostrarIngresoProveedores();
}//GEN-LAST:event_provAddActionPerformed

private void provModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provModActionPerformed
        mostrarModificarProveedores();
}//GEN-LAST:event_provModActionPerformed

    private void mnProvModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnProvModActionPerformed
        mostrarModificarProveedores();
    }//GEN-LAST:event_mnProvModActionPerformed

private void prodAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prodAddActionPerformed
        mostrarIngresoProductos();
}//GEN-LAST:event_prodAddActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        objReporte.ejecutarReporte("rptClientes");
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void mnCliDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCliDelActionPerformed
        mostrarEliminarCliente();
    }//GEN-LAST:event_mnCliDelActionPerformed

private void clienteDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteDelActionPerformed
        mostrarEliminarCliente();
}//GEN-LAST:event_clienteDelActionPerformed

private void provDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provDelActionPerformed
        mostrarEliminarProveedor();
}//GEN-LAST:event_provDelActionPerformed

    private void mnProvDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnProvDelActionPerformed
        mostrarEliminarProveedor();
    }//GEN-LAST:event_mnProvDelActionPerformed

    private void mnPermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPermisosActionPerformed
        mostrarAsignacionPermisos();
    }//GEN-LAST:event_mnPermisosActionPerformed

private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
    objReporte.ejecutarReporte("rptClientes");
}//GEN-LAST:event_jMenuItem9ActionPerformed

private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
    mostrarReportePorSector();
}//GEN-LAST:event_jMenuItem13ActionPerformed

private void prodModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prodModActionPerformed
    mostrarProductoMod();
}//GEN-LAST:event_prodModActionPerformed

private void mnProdModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnProdModActionPerformed
   mostrarProductoMod();
}//GEN-LAST:event_mnProdModActionPerformed

private void btnFacturarHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarHistoricoActionPerformed
   mostrarFacturarHistorico();
}//GEN-LAST:event_btnFacturarHistoricoActionPerformed

private void btnPagosHistoricosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagosHistoricosActionPerformed
    mostrarPagosHistorico();
}//GEN-LAST:event_btnPagosHistoricosActionPerformed

private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
    mostrarCambioClave();
}//GEN-LAST:event_jMenuItem19ActionPerformed

private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
    objReporte.ejecutarReporte("rptClienteLapsoUltimoPago");
}//GEN-LAST:event_jMenuItem20ActionPerformed

private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
    
    objReporte.ejecutarReporte("rptClienteDeudaTotal");        
}//GEN-LAST:event_jMenuItem21ActionPerformed

private void prodDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prodDelActionPerformed
    mostrarProductoDel();
}//GEN-LAST:event_prodDelActionPerformed

private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
    mostrarKardex();// TODO add your handling code here:
}//GEN-LAST:event_jMenuItem18ActionPerformed

private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
    objReporte.ejecutarReporte("rptCCAtrasadas");
}//GEN-LAST:event_jMenuItem22ActionPerformed

private void btnCajaAntesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCajaAntesActionPerformed
    //mostrarInicioCaja();
}//GEN-LAST:event_btnCajaAntesActionPerformed

private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
// TODO add your handling code here:
    /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmAbrirCaja");
    if(permitido)
    { */  
       //try{
            ArrayList<clsComboBox> dataFacturero = objFacturero.consultarFactureros();
          
            /*for(int i=0;i<dataFacturero.size();i++)
            {  
                
            }  */
            if(dataFacturero.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "No existen factureros activos, ingresar uno para poder abrir caja." , "Atención!", JOptionPane.ERROR_MESSAGE);                 
            }
            else
            {
                frmAbrirCaja ventana = new frmAbrirCaja(null, true);
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
                //dispose();
            }            
        /*}
        catch(Exception ex)
        {       
             JOptionPane.showMessageDialog(null, "No existen factureros activos, ingresar uno para poder abrir caja." + ex.getMessage(), "Atención!", JOptionPane.ERROR_MESSAGE);                 
        } */
   /* }
    else
    {
        JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
    }*/
}//GEN-LAST:event_btnAbrirActionPerformed

private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
    /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmFacturar");
    if(permitido)
    {   */
        //revisar facturero
        String idCajaAbierta = objCaja.obtenerCajaAbierta(main.idUser);
        String factManual = objCaja.comprobarFacturacionManual(idCajaAbierta); 
        if(factManual.equals("S"))
        {            
            int idFacturero = objCaja.obtenerIdFacturero(idCajaAbierta);
            //VERIFICAR SI SE ACABO FACTURERO
            if(objFacturero.consultarEstado(idFacturero).equals("A"))
            {
                frmFacturar ventana = new frmFacturar(null, true);
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            }
            else if(objFacturero.consultarEstado(idFacturero).equals("T"))
            {    
                JOptionPane.showMessageDialog(this, "Facturero ya terminado, utilice otro", "Atención!", JOptionPane.ERROR_MESSAGE);                 
            }
        }
        else
        {
            frmFacturar ventana = new frmFacturar(null, true);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        }
   /* }
    else
    {
        JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
    }  */ 
}//GEN-LAST:event_btnFacturarActionPerformed

private void btnPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagosActionPerformed
    /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmPagoAdd");
    if(permitido)
    {   */
        //frmPagoAdd ventana = new frmPagoAdd(null, true);
        //ventana.setLocationRelativeTo(null);
        //ventana.setVisible(true);
        //dispose(); 
        frmListPagosPendientes formulario = new frmListPagosPendientes();
        mostrarJInternalCentrado(formulario); 
    /*}
    else
    {
        JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
    } */     
}//GEN-LAST:event_btnPagosActionPerformed

private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
// TODO add your handling code here:
    /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmCerrarCaja");
    if(permitido)
    { */  
        //frmCerrarCaja ventana = new frmCerrarCaja(null, true);
        //ventana.setLocationRelativeTo(null);
        //ventana.setVisible(true);
        //dispose();
        //if(frmPagoAdd.)
        frmCerrarCaja formulario = new frmCerrarCaja();
        mostrarJInternalCentrado(formulario);   
    /*}
    else
    {
        JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
    } */  
}//GEN-LAST:event_btnCerrarActionPerformed

private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
    mostrarCompras();
}//GEN-LAST:event_jMenuItem23ActionPerformed

private void btnEgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEgresoActionPerformed
    /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmCerrarCaja");
    if(permitido)
    {   */
        frmEgresoDinero formulario = new frmEgresoDinero();
        mostrarJInternalCentrado(formulario);   
    /*}
    else
    {
        JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
    }   */
}//GEN-LAST:event_btnEgresoActionPerformed

private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
    try
    {  
        frmAcerca ventana = new frmAcerca();
        ventana.setLocationRelativeTo(null);
        //ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setVisible(true);
    }catch (Exception e)
    {    
        System.out.println(e);
    }// TODO add your handling code here:
}//GEN-LAST:event_jMenuItem26ActionPerformed

private void mnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnUsuarioActionPerformed
    /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmNuevoUsuario");
    if(permitido)
    {   */
        frmNuevoUsuario formulario = new frmNuevoUsuario();
        mostrarJInternalCentrado(formulario);   
    /*}
    else
    {
        JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
    } */  
}//GEN-LAST:event_mnUsuarioActionPerformed

private void mnGrupoProductoAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGrupoProductoAddActionPerformed
    /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmGrupoAdd");
    if(permitido)
    {  */ 
        frmGrupoAdd formulario = new frmGrupoAdd();
        mostrarJInternalCentrado(formulario);   
    /*}
    else
    {
        JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
    } */  
}//GEN-LAST:event_mnGrupoProductoAddActionPerformed

private void btnKardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKardexActionPerformed
    mostrarKardex();
}//GEN-LAST:event_btnKardexActionPerformed

private void btnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasActionPerformed
    mostrarCompras();
}//GEN-LAST:event_btnComprasActionPerformed

private void mnBuscarFacturasEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBuscarFacturasEliminarActionPerformed
    mostrarBuscarFacturas();
}//GEN-LAST:event_mnBuscarFacturasEliminarActionPerformed

private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
    mostrarCompras();
}//GEN-LAST:event_jMenuItem28ActionPerformed

private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
    frmFacturero formulario = new frmFacturero();
    mostrarJInternalCentrado(formulario);   
}//GEN-LAST:event_jMenuItem30ActionPerformed

private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
     frmRespaldarDB formulario = new frmRespaldarDB();
     mostrarJInternalCentrado(formulario);       
}//GEN-LAST:event_jMenuItem29ActionPerformed

private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
    mostrarAuditoria();
}//GEN-LAST:event_jMenuItem32ActionPerformed

private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
    frmCotizador formulario = new frmCotizador();
    mostrarJInternalCentrado(formulario);  
}//GEN-LAST:event_jMenuItem33ActionPerformed

private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
    frmRecibosPago formulario = new frmRecibosPago();
    mostrarJInternalCentrado(formulario);  
}//GEN-LAST:event_jMenuItem34ActionPerformed

private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
     frmFacturasRealizadas formulario = new frmFacturasRealizadas();
     mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_jMenuItem35ActionPerformed

private void btnNEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNEActionPerformed
    /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmNotasEntrega");
    if(permitido)
    {  */ 
        frmNotasEntrega1 ventana = new frmNotasEntrega1();
        mostrarJInternalCentrado(ventana);
        //ventana.setLocationRelativeTo(null);
        //ventana.setVisible(true);
    /*}
    else
    {
        JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
    } */    
}//GEN-LAST:event_btnNEActionPerformed

private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
     frmRecibosPagoGenerados formulario = new frmRecibosPagoGenerados();
     mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_jMenuItem36ActionPerformed

private void jMenu14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu14ActionPerformed
    permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmCerrarCaja_");
    if(permitido)
    {   
        frmCerrarCaja_ formulario = new frmCerrarCaja_();
        mostrarJInternalCentrado(formulario);  
    }
    else
    {
        JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
    }   
}//GEN-LAST:event_jMenu14ActionPerformed

private void mnFlujoEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFlujoEfectivoActionPerformed
    /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmCerrarCaja_");
    if(permitido)
    {  
        frmFactHisto formulario = new frmFactHisto(this, true);
        formulario.setLocationRelativeTo(null);
        formulario.setVisible(true);*/
        frmCerrarCaja_ formulario = new frmCerrarCaja_();
        mostrarJInternalCentrado(formulario); 
    /*}
    else
    {
        JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
    }  */ 
     
}//GEN-LAST:event_mnFlujoEfectivoActionPerformed

private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
    frmDevolucionVenta ventana = new frmDevolucionVenta(null, true);
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
}//GEN-LAST:event_jMenuItem38ActionPerformed

private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
    frmListCompras formulario = new frmListCompras();
    mostrarJInternalCentrado(formulario);  
}//GEN-LAST:event_jMenuItem39ActionPerformed

private void btnIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresoActionPerformed
    /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmCerrarCaja");
    if(permitido)
    {  */ 
        frmIngresoDinero formulario = new frmIngresoDinero();
        mostrarJInternalCentrado(formulario);   
    /*}
    else
    {
        JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
    } */  
}//GEN-LAST:event_btnIngresoActionPerformed

private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    frmEstadoDeCuenta formulario = new frmEstadoDeCuenta();
    mostrarJInternalCentrado(formulario); 
}//GEN-LAST:event_jButton7ActionPerformed

private void mnEliminarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnEliminarProductosActionPerformed
    /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmListProductosDel");
    if(permitido)
    {  */ 
        frmListProductosDel formulario = new frmListProductosDel();
        mostrarJInternalCentrado(formulario);   
    /*}
    else
    {
        JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
    } */    
}//GEN-LAST:event_mnEliminarProductosActionPerformed

private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed
    objReporte.ejecutarReporte("rptClienteDeudaTotalFact"); 
}//GEN-LAST:event_jMenuItem41ActionPerformed

private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
    //mostrarReportePorSector();
    objReporte.ejecutarReporte("rptClientesSinDeudas"); 
}//GEN-LAST:event_jMenuItem42ActionPerformed

private void mnFrmListVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFrmListVentasActionPerformed
    frmListVentas formulario = new frmListVentas();
    mostrarJInternalCentrado(formulario);  
}//GEN-LAST:event_mnFrmListVentasActionPerformed

private void mnFrmListVentasNEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFrmListVentasNEActionPerformed
    frmListVentasNE formulario = new frmListVentasNE();
    mostrarJInternalCentrado(formulario);  
}//GEN-LAST:event_mnFrmListVentasNEActionPerformed

private void mnFrmListVentasNEVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFrmListVentasNEVendedorActionPerformed
    frmListVentasNEVendedor formulario = new frmListVentasNEVendedor();
    mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_mnFrmListVentasNEVendedorActionPerformed

private void mnFrmListDescuentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFrmListDescuentosActionPerformed
    frmListDescuentos formulario = new frmListDescuentos();
    mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_mnFrmListDescuentosActionPerformed

private void jMenuItem47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem47ActionPerformed
    frmClienteIncobrable formulario = new frmClienteIncobrable();
    mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_jMenuItem47ActionPerformed

private void jMenuItem48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem48ActionPerformed
    frmListIncobrables formulario = new frmListIncobrables();
    mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_jMenuItem48ActionPerformed

private void mnProdDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnProdDelActionPerformed
    mostrarProductoDel(); 
}//GEN-LAST:event_mnProdDelActionPerformed

private void mnListadoProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnListadoProductosActionPerformed
    frmListProductosInventario formulario = new frmListProductosInventario();
    mostrarJInternalCentrado(formulario); 
}//GEN-LAST:event_mnListadoProductosActionPerformed

private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
    frmListNotasEntrega formulario = new frmListNotasEntrega();
    mostrarJInternalCentrado(formulario); 
}//GEN-LAST:event_jMenuItem8ActionPerformed

private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
    frmNotasEntrega1 formulario = new frmNotasEntrega1();
    mostrarJInternalCentrado(formulario); 
}//GEN-LAST:event_jMenuItem7ActionPerformed

private void mnListPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnListPagosActionPerformed
    frmListPagos formulario = new frmListPagos();
    mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_mnListPagosActionPerformed

private void mnOperacionesCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOperacionesCajaActionPerformed
    frmListOperacionesCaja formulario = new frmListOperacionesCaja();
    mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_mnOperacionesCajaActionPerformed

private void mnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnInventarioActionPerformed
    /*frmInventario ventana = new frmInventario(null, true);
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);*/
    frmInventario formulario = new frmInventario();
    mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_mnInventarioActionPerformed

private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
    frmListInventariosRealizados formulario = new frmListInventariosRealizados();
    mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_jMenuItem12ActionPerformed

private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
    frmListInventarioSecciones formulario = new frmListInventarioSecciones();
    mostrarJInternalCentrado(formulario);
}//GEN-LAST:event_jMenuItem11ActionPerformed

private void btnPagoNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoNuevoActionPerformed
     frmPagoNuevoAdd formulario = new frmPagoNuevoAdd();
     mostrarJInternalCentrado(formulario); 
}//GEN-LAST:event_btnPagoNuevoActionPerformed

private void btnReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReciboActionPerformed
    frmPagoAddOtros ventana = new frmPagoAddOtros(null, true);
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
}//GEN-LAST:event_btnReciboActionPerformed

private void mnReimpresionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnReimpresionActionPerformed
     frmReimpresionReciboPago formulario = new frmReimpresionReciboPago();
     mostrarJInternalCentrado(formulario); 
}//GEN-LAST:event_mnReimpresionActionPerformed

private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
    frmGuiaRemision formulario = new frmGuiaRemision();
    mostrarJInternalCentrado(formulario); 
}//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        frmReporteSemanal formulario = new frmReporteSemanal();
        mostrarJInternalCentrado(formulario);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        frmReporteCartera formulario = new frmReporteCartera();
        mostrarJInternalCentrado(formulario);          // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        frmReimprimirCupones formulario = new frmReimprimirCupones();
        mostrarJInternalCentrado(formulario);  
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        frmAdelantos formulario = new frmAdelantos();
        mostrarJInternalCentrado(formulario);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

   void mostrarAuditoria()
   {
        permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmAuditoria");
        if(permitido)
        {   
            frmAuditoria formulario = new frmAuditoria();
            mostrarJInternalCentrado(formulario);    
        }
        else
        {
            JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
        }        
   }
   
   void mostrarBuscarFacturas()
   {
       /*permitido = objPermisos.comprobarPermisoFormulario(main.idUser, "frmFacturaSeek");
       if(permitido)
       {  */ 
           frmFacturaSeek formulario = new frmFacturaSeek();
           mostrarJInternalCentrado(formulario);   
       /*}
       else
       {
           JOptionPane.showMessageDialog(this, objUtils.msgSinPermisosFormulario, objUtils.msgTitleSinPermisos, JOptionPane.WARNING_MESSAGE);            
       } */    
   }
    
    void mostrarModificarProveedores()
    {
        frmProveedorMod formulario = new frmProveedorMod();
        mostrarJInternalCentrado(formulario); 
        /*Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = formulario.getSize();
        formulario.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
        (desktopSize.height- jInternalFrameSize.height)/2);

        jDesktopPane1.add(formulario);
        formulario.show();*/
    }     
    
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
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    @Action
    public void proveedores() {
        frmProveedorMenu formulario = new frmProveedorMenu();    
        mostrarJInternalCentrado(formulario); 
    }

    @Action
    public void reportes() {
    }

    @Action
    public void productos() {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAbrir;
    public static javax.swing.JButton btnCajaAntes;
    public static javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCompras;
    public static javax.swing.JButton btnEgreso;
    public static javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnFacturarHistorico;
    public static javax.swing.JButton btnIngreso;
    private javax.swing.JButton btnKardex;
    private javax.swing.JButton btnNE;
    private javax.swing.JButton btnPagoNuevo;
    public static javax.swing.JButton btnPagos;
    private javax.swing.JButton btnPagosHistoricos;
    private javax.swing.JButton btnRecibo;
    private org.jdesktop.swingx.JXHyperlink clienteAdd;
    private org.jdesktop.swingx.JXHyperlink clienteDel;
    private org.jdesktop.swingx.JXHyperlink clienteMod;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenu jMenu19;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem48;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JToolBar jToolBar1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane2;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane3;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer2;
    private javax.swing.JLabel lblLogo;
    public static javax.swing.JLabel lblPendiente;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuItem mnBuscarFacturasEliminar;
    private javax.swing.JMenuItem mnCliDel;
    private javax.swing.JMenuItem mnCliMod;
    private javax.swing.JMenuItem mnClienteAdd;
    private javax.swing.JMenuItem mnEliminarProductos;
    private javax.swing.JMenuItem mnFlujoEfectivo;
    private javax.swing.JMenuItem mnFrmListDescuentos;
    private javax.swing.JMenuItem mnFrmListVentas;
    private javax.swing.JMenuItem mnFrmListVentasNE;
    private javax.swing.JMenuItem mnFrmListVentasNEVendedor;
    private javax.swing.JMenuItem mnGrupoProductoAdd;
    private javax.swing.JMenuItem mnInventario;
    private javax.swing.JMenuItem mnListPagos;
    private javax.swing.JMenuItem mnListadoProductos;
    private javax.swing.JMenuItem mnOperacionesCaja;
    private javax.swing.JMenuItem mnPermisos;
    private javax.swing.JMenuItem mnProdDel;
    private javax.swing.JMenuItem mnProdMod;
    private javax.swing.JMenuItem mnProvDel;
    private javax.swing.JMenuItem mnProvMod;
    private javax.swing.JMenuItem mnReimpresion;
    private javax.swing.JMenuItem mnUsuario;
    private org.jdesktop.swingx.JXHyperlink prodAdd;
    private org.jdesktop.swingx.JXHyperlink prodDel;
    private org.jdesktop.swingx.JXHyperlink prodMod;
    private org.jdesktop.swingx.JXHyperlink provAdd;
    private org.jdesktop.swingx.JXHyperlink provDel;
    private org.jdesktop.swingx.JXHyperlink provMod;
    // End of variables declaration//GEN-END:variables
}
