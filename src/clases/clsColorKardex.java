/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author CKaiser
 */
 public class clsColorKardex extends DefaultTableCellRenderer { 
    @Override 
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column ) 
    { 
        setEnabled(table == null || table.isEnabled());        
        
        if(String.valueOf(table.getValueAt(row,8)).equals("EGRESO")) 
            setBackground(Color.red); 
        else if(String.valueOf(table.getValueAt(row,8)).equals("INGRESO")) 
            setBackground(Color.GREEN); 
        else if(String.valueOf(table.getValueAt(row,8)).equals("INV - INGRESO")) 
            setBackground(Color.magenta);
        else if(String.valueOf(table.getValueAt(row,8)).equals("INV - EGRESO")) 
            setBackground(Color.magenta);
        else setBackground(null); 
        
        super.getTableCellRendererComponent(table, value, selected, focused, row, column); 
        return this; 
    } 
 }