// Nome do Pacote
package br.com.adrianob.view;

// Importações
import br.com.adrianob.model.Cliente;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author drink
 */

// Classe ClienteTableModel extendendo AbstractTableModel
public class ClienteTableModel extends AbstractTableModel {

    // Declarações de variáveis de escopo da classe
    private ArrayList<Cliente> dados;
    private String[] colunas = {"Código", "Nome", "Cnpj", "Situação"};

    // Método para criação da Tela
    public ClienteTableModel(ArrayList<Cliente> dados) {
        super();
        this.dados = dados;
    }

    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    // Método para trazer as colunas de informações do banco de dados.
    @Override
    public String getColumnName(int column) {
        return this.colunas[column];
    }

    // Método para trazer as contagem das linhas do banco de dados.    
    @Override
    public int getRowCount() {
        return this.dados.size();
    }
    // Método para trazer as contagem das colunas do banco de dados.    
    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    // Método para pegar valores do banco de dados
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente get = this.dados.get(rowIndex);

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
