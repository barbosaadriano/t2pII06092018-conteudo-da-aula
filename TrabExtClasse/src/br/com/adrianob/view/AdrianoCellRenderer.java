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
// classe AdrianoCellRenderer com herança 
public class AdrianoCellRenderer extends DefaultTableCellRenderer {

    //método de construção das células da tabela
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //determina as cores das letras e das células da tabela
        setBackground(java.awt.Color.white); 
        setForeground(Color.darkGray); 
        // determina quais as células(linhas) serão de cores diferente
        if (row % 2 == 0) {
            setBackground(java.awt.Color.getHSBColor(0.51f, 0.1f, 1f));
        }
        
        // condição para a coluna 3
        if (column == 3) {
            //atribui um objeto a ser usado 
            String vl = (String)value;
            if (vl.contains("Ativo")) {
                //se o valor for ativo a cor correspondente das letras será verde
                setBackground(Color.GREEN);
            }  else {
                // se o valor for inativo a cor será vermelha
                setBackground(Color.red);
            }
            // se estiver em branco atribui uma nova cor
            setForeground(Color.white);
        }
        //retorna a tabela criada
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

}
