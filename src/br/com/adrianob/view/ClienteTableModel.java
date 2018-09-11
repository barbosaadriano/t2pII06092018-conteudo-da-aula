//nome do pacote
package br.com.adrianob.view;

//importações
import br.com.adrianob.model.Cliente;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author drink
 */
//Classe ClienteTableModel que herda da AbstractTableModel
public class ClienteTableModel extends AbstractTableModel {

    //Variáveis de escopo de classe
    private ArrayList<Cliente> dados;
    private String[] colunas = {"Código", "Nome", "Cnpj", "Situação"};

    //Construtor
    public ClienteTableModel(ArrayList<Cliente> dados) {
        //Aciona método construtor da classe pai
        super();
        //Aponta para a variável de escopo de classe o valor do parâmetro passado
        this.dados = dados;
    }

    //Sobrescrita de método
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //retorna falso :D
        return false;
    }

    //Sobrescrita de método
    @Override
    public String getColumnName(int column) {
        //retorna o nome da coluna
        return this.colunas[column];
    }

    //Sobrescrita de método
    @Override
    public int getRowCount() {
        //retorna o número de linha
        return this.dados.size();
    }

    //Sobrescrita de método
    @Override
    public int getColumnCount() {
        //retorna o número de colunas
        return this.colunas.length;
    }

    //Sobrescrita de método
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Cria um objeto Cliente e recebe os valores baseado no ArrayList de linhas
        Cliente get = this.dados.get(rowIndex);

        //Retorna o dado da coluna especificada
        switch (columnIndex) {
            case 0:
                return get.getCodigo();
            case 1:
                return get.getNome();
            case 2:
                return get.getCnpj();
            case 3:
                return get.getStatus();
        }
        return null;
    }

}
