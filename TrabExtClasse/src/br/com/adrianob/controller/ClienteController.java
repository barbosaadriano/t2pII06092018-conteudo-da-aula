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

    //Método para listar
    private void listar() {
        //Cria um ArrayList de objeto Cliente 
        ArrayList<Cliente> listarCliente = new ArrayList<>();
        //Caso o input de pesquisa não estiver vazio
        if (!telaLista.getTxtPesquisa().getText().isEmpty()) {
            //Cria um novo objeto cliente
            Cliente c = new Cliente();
            //Aciona o método para mudar a propriedade nome passando o input de pesquisa
            c.setNome(telaLista.getTxtPesquisa().getText());
            //Retorna o cliente utilizando o método do DaoCliente
            listarCliente = this.dc.listarCliente(c);
        } else {
            //Caso estiver vazio o input de pesquisa, retorna todos clientes
            listarCliente = this.dc.listarCliente(null);
        }
        //Atualiza o modelo da tabela para a consulta
        telaLista.getTabela().setModel(new ClienteTableModel(listarCliente));
    }

    //Método para novo cadastro
    private void novo() {
        //Abre a tela de edição para o cadastro de um novo cliente
        this.abrirEdicao(new Cliente());
    }

    //Método para salvar
    private void salvar() {
        //Caso a tela de edição não esteja inicializada
        if (edicao != null) {
            //Objeto cliente recebe os dados da tela de edição
            Cliente cli = edicao.getDadosCliente();
            //Se o objeto cliente não estiver nulo
            if (cli != null) {
                //Salva o cliente utilizando o DAO
                dc.salvarCliente(cli);
                //Relista
                listar();
                //Tela de edição é finalizada
                edicao = null;
            }
        }
    }

    //Método para editar
    private void editar() {
        //Abre a tela de edição usando o cliente selecionado na tabela.
        abrirEdicao(getClientSelecionado());
    }

    //Método para pegar o cliente selecionado
    private Cliente getClientSelecionado() {
        //Caso a tela de listagem esteja inicializada
        if (this.telaLista != null) {
            //Caso a contagem de linhas selecionadas seja igual à 1
            if (telaLista.getTabela().getSelectedRowCount() == 1) {
                //Variavel recebe valor de index da linha selecionada
                int idx = telaLista.getTabela().getSelectedRow();
                //Variavel recebe o id da linha selecionada
                int codigo = (int) telaLista.getTabela().getValueAt(idx, 0);
                //Cria um novo objeto cliente
                Cliente c = new Cliente();
                //Passa o valor id para o objeto cliente
                c.setCodigo(codigo);
                //Um objeto ArrayList recebe o retorno pela consulta deste id
                ArrayList<Cliente> lc = dc.listarCliente(c);
                //Se o retorno não for vazio
                if (!lc.isEmpty()) {
                    //Retorna a linha de resultado
                    return lc.get(0);
                }
            }
        }
        //Se não, retorna nulo
        return null;
    }

    //Método para remover
    private void remover() {
        //Caso confirme a remoção
        if (JOptionPane.showConfirmDialog(edicao, "Deseja Remover?", "confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION) {
            //Objeto cliente recebe o cliente selecionado
            Cliente cli = getClientSelecionado();
            //Se não for nulo
            if (cli != null) {
                //Ativa o método de remoção do DAO
                dc.removerCliente(cli);
                //Relista
                listar();
            }
        }
    }

}
