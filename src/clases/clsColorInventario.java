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
 public class clsColorInventario extends DefaultTableCellRenderer { 
    @Override 
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column ) 
    { 
        setEnabled(table == null || table.isEnabled());        
        
        if((Boolean)(table.getValueAt(row,8))==false) 
            setBackground(Color.red); 
        /*else if((Boolean)(table.getValueAt(row,8))==true) 
            setBackground(Color.GREEN); */
        else setBackground(null); 
        
        super.getTableCellRendererComponent(table, value, selected, focused, row, column); 
        return this; 
    } 
 }