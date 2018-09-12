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

    //variaveis do escopo da classe
    private ArrayList<Cliente> dados;
    private String[] colunas = {"Código", "Nome", "Cnpj", "Situação"};

    //contrutor
    public ClienteTableModel(ArrayList<Cliente> dados) {
         //Aciona método construtor da classe pai
        super();
        //mostra para a variável de escopo de classe o valor do parâmetro passado
        this.dados = dados;
    }

    //sobrescrita de metodo
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //retorna falso
        return false;
    }
    
    //sobrescrita de metodo
    @Override
    public String getColumnName(int column) {
        //retorna o nome da coluna
        return this.colunas[column];
    }
    
    //sobrescrita de motodo
    @Override
    public int getRowCount() {
        //retorna o numero de linhas
        return this.dados.size();
    }

    //sobrescrita de metodo
    @Override
    public int getColumnCount() {
        //retorna o numero de colunas
        return this.colunas.length;
    }

    //sobrescrita de metodos
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
