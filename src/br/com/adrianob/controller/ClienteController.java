//nome do pacote
package br.com.adrianob.controller;

//importações
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
//classe ClienteController com interface de ActionListener
public class ClienteController implements ActionListener {

    //declaração de variáveis de escopo de classe
    private ClienteListView telaLista;
    private ClienteEditView edicao;
    private DaoCliente dc;

    //Método construtor da classe com injeção de dependência
    public ClienteController(Connection conn) {
        //Variável (escopo de classe) recebendo um objeto DaoCliente passando o
        //objeto de conexão passado no construtor do ClienteController
        dc = new DaoCliente(conn);
    }

    //Método da interface de ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        //estrutura de condição para comando passado para o ActionPerformed
        switch (e.getActionCommand()) {
            //Caso pedir o comando de listar, aciona o método de listagem.
            case "cliente.listar":
                listar();
                break;
            //Caso pedir o comando de novo, aciona o método de novo.
            case "cliente.novo":
                novo();
                break;
            //Caso pedir o comando de salvar, aciona o método de salvar.
            case "cliente.salvar":
                salvar();
                break;
            //Caso pedir o comando de editar, aciona o método de editar.
            case "cliente.editar":
                editar();
                break;
            //Caso pedir o comando de remover, aciona o método de remover
            case "cliente.remover":
                remover();
                break;
        }
    }

    //método para abrir tela de edição
    public void abrirEdicao(Cliente c) {
        //variável (escopo de classe) recebe um objeto ClientEditView.
        edicao = new ClienteEditView(this, c);
        //Aponta que apenas essa tela pode ser acessada enquanto existir.
        edicao.setModal(true);
        //Aponta que a tela ficará visível.
        edicao.setVisible(true);
    }

    //Método para abrir tela de Listagem
    public void abrirLista() {
        //Caso a variável (escopo de classe) estiver nula...
        if (this.telaLista == null) {
            //Faz com que a variável receba um objeto do tipo ClienteListView.
            this.telaLista = new ClienteListView(this);
            //Aponta que a tela criada fechara o sistema completo quando for fechada
            this.telaLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        //Aponta que a a tela ficará visível
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
