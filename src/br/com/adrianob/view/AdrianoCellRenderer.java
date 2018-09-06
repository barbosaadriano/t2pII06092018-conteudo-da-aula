/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adrianob.view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author drink
 */
public class AdrianoCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(java.awt.Color.white);
        setForeground(Color.darkGray);
        if (row % 2 == 0) {
            setBackground(java.awt.Color.getHSBColor(0.51f, 0.1f, 1f));
        }
        if (column == 3) {
            String vl = (String)value;
            if (vl.contains("Ativo")) {
                setBackground(Color.GREEN);
            }  else {
                setBackground(Color.red);
            }
            setForeground(Color.white);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

}
