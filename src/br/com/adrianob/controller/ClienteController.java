package br.com.adrianob.controller;

import br.com.adrianob.dao.DaoCliente;
import br.com.adrianob.model.Cliente;
import br.com.adrianob.view.ClienteEditView;
import br.com.adrianob.view.ClienteListView;
import br.com.adrianob.view.ClienteTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author drink
 */
public class ClienteController implements ActionListener {

    private ClienteListView telaLista;
    private ClienteEditView edicao;
    private DaoCliente dc;

    public ClienteController(Connection conn) {
        dc = new DaoCliente(conn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "cliente.listar":
                listar();
                break;
            case "cliente.novo":
                novo();
                break;
            case "cliente.salvar":
                salvar();
                break;
            case "cliente.editar":
                editar();
                break;
            case "cliente.remover":
                remover();
                break;
        }
    }

    public void abrirEdicao(Cliente c) {

        edicao = new ClienteEditView(this, c);
        edicao.setModal(true);
        edicao.setVisible(true);
    }

    public void abrirLista() {
        if (this.telaLista == null) {
            this.telaLista = new ClienteListView(this);
            this.telaLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        this.telaLista.setVisible(true);
    }

    private void listar() {
        ArrayList<Cliente> listarCliente = new ArrayList<>();
        if (!telaLista.getTxtPesquisa().getText().isEmpty()) {
            Cliente c = new Cliente();
            c.setNome(telaLista.getTxtPesquisa().getText());
            listarCliente = this.dc.listarCliente(c);
        } else {
            listarCliente = this.dc.listarCliente(null);
        }
        telaLista.getTabela().setModel(new ClienteTableModel(listarCliente));
    }

    private void novo() {
        this.abrirEdicao(new Cliente());
    }

    private void salvar() {
        if (edicao != null) {
            Cliente cli = edicao.getDadosCliente();
            if (cli != null) {
                dc.salvarCliente(cli);
                listar();
                edicao = null;
            }
        }
    }

    private void editar() {
        abrirEdicao(getClientSelecionado());
    }

    private Cliente getClientSelecionado() {
        if (this.telaLista != null) {
            if (telaLista.getTabela().getSelectedRowCount() == 1) {
                int idx = telaLista.getTabela().getSelectedRow();
                int codigo = (int) telaLista.getTabela().getValueAt(idx, 0);
                Cliente c = new Cliente();
                c.setCodigo(codigo);
                ArrayList<Cliente> lc = dc.listarCliente(c);
                if (!lc.isEmpty()) {
                    return lc.get(0);
                }
            }
        }
        return null;
    }

    private void remover() {
        if (JOptionPane.showConfirmDialog(edicao, "Deseja Remover?", "confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION) {
            Cliente cli = getClientSelecionado();
            if (cli != null) {
                dc.removerCliente(cli);
                listar();
            }
        }
    }

}
