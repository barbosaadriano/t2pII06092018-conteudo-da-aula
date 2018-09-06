package br.com.adrianob.view;

import br.com.adrianob.model.Cliente;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author drink
 */
public class ClienteTableModel extends AbstractTableModel {

    private ArrayList<Cliente> dados;
    private String[] colunas = {"Código", "Nome", "Cnpj", "Situação"};

    public ClienteTableModel(ArrayList<Cliente> dados) {
        super();
        this.dados = dados;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return this.colunas[column];
    }

    @Override
    public int getRowCount() {
        return this.dados.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

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
