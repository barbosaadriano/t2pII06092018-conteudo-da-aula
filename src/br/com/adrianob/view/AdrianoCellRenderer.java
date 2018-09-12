//NOME DO PACOTE
package br.com.adrianob.view;

//IMPORTAÇÕES
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author drink
 */
//CLASSE ADRIANOCELLRENDERER QUE HERDA AS CARACTERÍSTICAS DE UM CELLRENDERER
public class AdrianoCellRenderer extends DefaultTableCellRenderer {

    //SOBRESCRITA DE MÉTODO QUE RETONA O COMPONENTE CELLRENDERER
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //APONTA QUE O FUNDO FICA COM COR BRANCA
        setBackground(java.awt.Color.white);
        //MUDA A FONTE PARA COR CINZA ESCURO
        setForeground(Color.darkGray);
        //SE A LINHA FOR PAR
        if (row % 2 == 0) {
            //MUDA A COR DE FUNDO DA CELL
            setBackground(java.awt.Color.getHSBColor(0.51f, 0.1f, 1f));
        }
        //SE A COLUNA TIVER O INDEX 3
        if (column == 3) {
            //UMA STRING RECEBE A VALUE 
            String vl = (String) value;
            //SE A STRING CONTER ATIVO
            if (vl.contains("Ativo")) {
                //MUDA A COR DE FUNDO PARA VERDE
                setBackground(Color.GREEN);
            } else {
                //SENÃO MUDA PARA VERMELHO
                setBackground(Color.red);
            }
            //MUDA A COR DA FONTE PARA BRANCO
            setForeground(Color.white);
        }
        //RETORNA A CELLRENDERER
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

}