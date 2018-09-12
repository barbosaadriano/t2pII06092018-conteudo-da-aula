//NOME DO PACOTE
package br.com.adrianob.view;

//IMPORTAÇÕES
import br.com.adrianob.model.Cliente;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author drink
 */
//CLASSE CLIENTETABLEMODEL QUE HERDA DA ABSTRACTTABLEMODEL
public class ClienteTableModel extends AbstractTableModel {

    //VARIÁVEIS DE ESCOPO DE CLASSE
    private ArrayList<Cliente> dados;
    private String[] colunas = {"Código", "Nome", "Cnpj", "Situação"};

    //CONSTRUTOR
    public ClienteTableModel(ArrayList<Cliente> dados) {
        //ACIONA MÉTODO CONSTRUTOR DA CLASSE PAI
        super();
        //APONTA PARA A VARIÁVEL DE ESCOPO DE CLASSE O VALOR DO PARÂMETRO PASSADO
        this.dados = dados;
    }

    //SOBRESCRITA DE MÉTODO
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //RETORNA FALSO :D
        return false;
    }

    //SOBRESCRITA DE MÉTODO
    @Override
    public String getColumnName(int column) {
        //RETORNA O NOME DA COLUNA
        return this.colunas[column];
    }

    //SOBRESCRITA DE MÉTODO
    @Override
    public int getRowCount() {
        //RETORNA O NÚMERO DE LINHA
        return this.dados.size();
    }

    //SOBRESCRITA DE MÉTODO
    @Override
    public int getColumnCount() {
        //RETORNA O NÚMERO DE COLUNAS
        return this.colunas.length;
    }

    //SOBRESCRITA DE MÉTODO
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //CRIA UM OBJETO CLIENTE E RECEBE OS VALORES BASEADO NO ARRAYLIST DE LINHAS
        Cliente get = this.dados.get(rowIndex);

        //RETORNA O DADO DA COLUNA ESPECIFICADA
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