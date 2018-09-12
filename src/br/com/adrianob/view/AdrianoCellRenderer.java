//nome do pacote
package br.com.adrianob.view;

//importações
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author drink
 */
//Classe AdrianoCellRenderer que herda as características de um CellRenderer
public class AdrianoCellRenderer extends DefaultTableCellRenderer {

    //Sobrescrita de método que retona o componente cellrenderer
    @Override
     
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //mostra que a cor do fundo será branco
        setBackground(java.awt.Color.white);
        //a fonte será cinza escuro
        setForeground(Color.darkGray);
        //Se a linha for par..
        if (row % 2 == 0) {
            //Muda a cor de fundo da cell
            setBackground(java.awt.Color.getHSBColor(0.51f, 0.1f, 1f));
        }
        //Se a coluna tiver o index 3..
        if (column == 3) {
            //Uma string recebe a value (?)
            String vl = (String)value;
            //Se a string conter valor ativo...
            if (vl.contains("Ativo")) {
                //Muda a cor de fundo para verde
                setBackground(Color.GREEN);
            }  else {
                //senão muda para vermelho
                setBackground(Color.red);
            }
            //Muda a cor da fonte para branco
            setForeground(Color.white);
        }
        //retorna a cellRenderer
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

}
