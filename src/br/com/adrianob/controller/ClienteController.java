//Nome do pacote, a pasta onde a classe java está
package br.com.adrianob.controller;

//Importações
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
 //Classe ClienteController implementando ActionListener que é utilizado para criar uma interface
public class ClienteController implements ActionListener {
	
//Declaração dos atributos da classe como private, onde só a classe tem acesso a elas
    private ClienteListView telaLista;
    private ClienteEditView edicao;
    private DaoCliente dc;
	
//Construtor da classe com injeção de dependência, nesse caso de uma variável do tipo Connection
    public ClienteController(Connection conn) {
        dc = new DaoCliente(conn);
    }

//Método obrigatório do implemento ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
		//Um switch que utiliza o evento acionado para verificar qual método ativar
        switch (e.getActionCommand()) {
			//Se o evento passado é cliente.listar ele vai acionar esse evento e sair fora do escopo do switch, por causa do break;
            case "cliente.listar":
                listar();
                break;
			//Se o evento é cliente.novo ele vai acionar o método novo() e sair fora do escopo do switch
            case "cliente.novo":
                novo();
                break;
			//Se o evento é cliente.salvar ele vai acionar o método salvar() e sair fora do escopo do switch
            case "cliente.salvar":
                salvar();
                break;
			 //Se o evento é cliente.editar ele vai executar o método editar() e sair fora do escopo do switch
            case "cliente.editar":
                editar();
                break;
			//Se o evento é cliente.remover ele vai executar o método remover() e sair fora do escopo do switch
            case "cliente.remover":
                remover();
                break;
        }
    }

	//Método para abrir edição em uma tela
    public void abrirEdicao(Cliente c) {
		//Variável edicao recebe um objeto ClienteEditView
        edicao = new ClienteEditView(this, c);
		//
        edicao.setModal(true);
		//Torna a tela visível
        edicao.setVisible(true);
    }

	//Método para abrir tela de listagem
    public void abrirLista() {
		//Verifica se a tela é igual a nula
        if (this.telaLista == null) {
			//A variável telaLista recebe um objeto ClienteListView
            this.telaLista = new ClienteListView(this);
			//Indica que ao fechar essa tela o programa fecha por completo
            this.telaLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
		//Torna a tela visivel
        this.telaLista.setVisible(true);
    }

    
     //Método para listar
    private void listar() {
		//Cria um ArrayList de um objeto Cliente
        ArrayList<Cliente> listarCliente = new ArrayList<>();
        //Verifica se a variável telaLista é diferente de vazio
        if (!telaLista.getTxtPesquisa().getText().isEmpty()) {
            //Cria uma variável c do tipo Cliente
            Cliente c = new Cliente();
            //Passa ao objeto C um nome do objeto telaLista
            c.setNome(telaLista.getTxtPesquisa().getText());
			//
            listarCliente = this.dc.listarCliente(c);
        } else {
			//
            listarCliente = this.dc.listarCliente(null);
        }
        //
        telaLista.getTabela().setModel(new ClienteTableModel(listarCliente));
    }

    //Método para novo cadastro
    private void novo() {
		//Abre tela de cadastro/edição de cliente
        this.abrirEdicao(new Cliente());
    }
	
	//Método para salvar
    private void salvar() {
		//Verifica se a variável edicao é diferente de nulo
        if (edicao != null) {
			//Cria uma variável do tipo Cliente que recebe os dados da edicao
            Cliente cli = edicao.getDadosCliente();
			//Verifica se o objeto é diferente de nulo
            if (cli != null) {
                //Salva o cliente pelo DaoCliente
                dc.salvarCliente(cli);
                //Aciona o método listar()
                listar();
                //Edição recebe nulo, assim a tela e os dados é fechada
                edicao = null;
            }
        }
    }

    //Método para editar
    private void editar() {
        //Abre a tela de edicao utilizando o cliente selecionado
        abrirEdicao(getClientSelecionado());
    }

    //Método para pegar o cliente selecionado
    private Cliente getClientSelecionado() {
        //Verifica se a telaLista é diferente de nulo
        if (this.telaLista != null) {
            //Verifica se a contagem de linhas é igual a 1
            if (telaLista.getTabela().getSelectedRowCount() == 1) {
				//A variável recebe a quantidade de linhas
                int idx = telaLista.getTabela().getSelectedRow();
				//A variável recebe o código da linha selecionado
                int codigo = (int) telaLista.getTabela().getValueAt(idx, 0);
				//Cria um objeto do tipo cliente
                Cliente c = new Cliente();
				//Passa o código para o objeto cliente
                c.setCodigo(codigo);
				//
                ArrayList<Cliente> lc = dc.listarCliente(c);
                //Verifica se é diferente de nulo
                if (!lc.isEmpty()) {
                    //Retorna a linha 1 da ArrayList
                    return lc.get(0);
                }
            }
        }
        //Retorna nulo
        return null;
    }

    //Método para remover cliente
    private void remover() {
		//Mostra um mensagem para confirmar a remoção
        if (JOptionPane.showConfirmDialog(edicao, "Deseja Remover?", "confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION) {
			//Cria um objeto do tipo Cliente que seleciona um cliente
            Cliente cli = getClientSelecionado();
            //Verifica se o cliente é diferente de nulo
            if (cli != null) {
				//Aciona o método de remover no DaoCliente
                dc.removerCliente(cli);
                //Aciona o método listar()
                listar();
            }
        }
    }

}
