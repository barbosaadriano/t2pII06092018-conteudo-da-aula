//NOME DO PACOTE
package br.com.adrianob.controller;

//IMPORTAÇÕES
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
 //CLASSE CLIENTECONTROLLER COM INTERFACE DE ACTIONLISTENER
public class ClienteController implements ActionListener {
	
//DECLARAÇÃO DE VARIAVEIS DE ESCOPO DE CLASSE
    private ClienteListView telaLista;
    private ClienteEditView edicao;
    private DaoCliente dc;
	
//CONSTRUTOR DA CLASSE COM INJEÇÃO DE DEPEDENCIA
    public ClienteController(Connection conn) {
        dc = new DaoCliente(conn);
    }

//METODO DA INTERFACE DE ACTIONLISTENER	
    @Override
    public void actionPerformed(ActionEvent e) {
		//CONDIÇÃO PARA COMANDO PASSADO PARA O ACTIONPERFORMED
        switch (e.getActionCommand()) {
			//PEDIR O COMANDO DE LISTAR, ACIONA O METODO DE LISTAGEM
            case "cliente.listar":
                listar();
                break;
			//PEDIR O COMANDO DE NOVO, ACIONA O METODO DE NOVO	
            case "cliente.novo":
                novo();
                break;
			//PEDIR O COMANDO DE SALVAR, ACIONA O METODO SALVAR	
            case "cliente.salvar":
                salvar();
                break;
			 //PEDIR O COMANDO DE EDITAR, ACIONA O MÉTODO DE EDITAR	
            case "cliente.editar":
                editar();
                break;
			//PEDIR O COMANDO DE REMOVER, ACIONA O MÉTODO DE REMOVER
            case "cliente.remover":
                remover();
                break;
        }
    }

    //METODO PARA ABRIR TELA DE EDIÇÃO
    public void abrirEdicao(Cliente c) {
        //VARIÁVEL (ESCOPO DE CLASSE) RECEBE UM OBJETO CLIENTEDITVIEW
        edicao = new ClienteEditView(this, c);
        //APONTA QUE APENAS ESSA TELA PODE SER ACESSADA ENQUANTO EXISTIR
        edicao.setModal(true);
        //APONTA QUE A TELA FICARÁ VISÍVEL
        edicao.setVisible(true);
    }

    //METODO TODO PARA ABRIR TELA DE LISTAGEM
    public void abrirLista() {
        //CASO A VARIÁVEL (ESCOPO DE CLASSE) ESTIVER NULA...
        if (this.telaLista == null) {
            //FAZ COM QUE A VARIÁVEL RECEBA UM OBJETO DO TIPO CLIENTELISTVIEW
            this.telaLista = new ClienteListView(this);
            //APONTA QUE A TELA CRIADA FECHARA O SISTEMA COMPLETO QUANDO FOR FECHADA
            this.telaLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        //APONTA QUE A A TELA FICARÁ VISÍVEL
        this.telaLista.setVisible(true);
    }

    
     //MÉTODO PARA LISTAR
    private void listar() {
        //CRIA UM ARRAYLIST DE OBJETO CLIENTE 
        ArrayList<Cliente> listarCliente = new ArrayList<>();
        //CASO O INPUT DE PESQUISA NÃO ESTIVER VAZIO
        if (!telaLista.getTxtPesquisa().getText().isEmpty()) {
            //CRIA UM NOVO OBJETO CLIENTE
            Cliente c = new Cliente();
            //ACIONA O MÉTODO PARA MUDAR A PROPRIEDADE NOME PASSANDO O INPUT DE PESQUISA
            c.setNome(telaLista.getTxtPesquisa().getText());
            //RETORNA O CLIENTE UTILIZANDO O MÉTODO DO DAOCLIENTE
            listarCliente = this.dc.listarCliente(c);
        } else {
            //CASO ESTIVER VAZIO O INPUT DE PESQUISA, RETORNA TODOS CLIENTES
            listarCliente = this.dc.listarCliente(null);
        }
        //ATUALIZA O MODELO DA TABELA PARA A CONSULTA
        telaLista.getTabela().setModel(new ClienteTableModel(listarCliente));
    }

    //MÉTODO PARA NOVO CADASTRO
    private void novo() {
        //ABRE A TELA DE EDIÇÃO PARA O CADASTRO DE UM NOVO CLIENTE
        this.abrirEdicao(new Cliente());
    }

    //MÉTODO PARA SALVAR
    private void salvar() {
        //CASO A TELA DE EDIÇÃO NÃO ESTEJA INICIALIZADA
        if (edicao != null) {
            //OBJETO CLIENTE RECEBE OS DADOS DA TELA DE EDIÇÃO
            Cliente cli = edicao.getDadosCliente();
            //OBJETO CLIENTE NÃO ESTIVER NULO
            if (cli != null) {
                //SALVA O CLIENTE UTILIZANDO O DAO
                dc.salvarCliente(cli);
                //RELISTA
                listar();
                //TELA DE EDIÇÃO É FINALIZADA
                edicao = null;
            }
        }
    }

    //MÉTODO PARA EDITAR
    private void editar() {
        //ABRE A TELA DE EDIÇÃO USANDO O CLIENTE SELECIONADO NA TABELA
        abrirEdicao(getClientSelecionado());
    }

    //MÉTODO PARA PEGAR O CLIENTE SELECIONADO
    private Cliente getClientSelecionado() {
        //CASO A TELA DE LISTAGEM ESTEJA INICIALIZADA
        if (this.telaLista != null) {
            //CASO A CONTAGEM DE LINHAS SELECIONADAS SEJA IGUAL À 1
            if (telaLista.getTabela().getSelectedRowCount() == 1) {
                //VARIAVEL RECEBE VALOR DE INDEX DA LINHA SELECIONADA
                int idx = telaLista.getTabela().getSelectedRow();
                //VARIAVEL RECEBE O ID DA LINHA SELECIONADA
                int codigo = (int) telaLista.getTabela().getValueAt(idx, 0);
                //CRIA UM NOVO OBJETO CLIENTE
                Cliente c = new Cliente();
                //PASSA O VALOR ID PARA O OBJETO CLIENTE
                c.setCodigo(codigo);
                //OBJETO ARRAYLIST RECEBE O RETORNO PELA CONSULTA DESTE ID
                ArrayList<Cliente> lc = dc.listarCliente(c);
                //SE O RETORNO NÃO FOR VAZIO
                if (!lc.isEmpty()) {
                    //RETORNA A LINHA DE RESULTADO
                    return lc.get(0);
                }
            }
        }
        // RETORNA NULO
        return null;
    }

    //MÉTODO PARA REMOVER
    private void remover() {
        //CASO CONFIRME A REMOÇÃO
        if (JOptionPane.showConfirmDialog(edicao, "Deseja Remover?", "confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION) {
            //OBJETO CLIENTE RECEBE O CLIENTE SELECIONADO
            Cliente cli = getClientSelecionado();
            //SE NÃO FOR NULO
            if (cli != null) {
                //ATIVA O MÉTODO DE REMOÇÃO DO DAO
                dc.removerCliente(cli);
                //RELISTA
                listar();
            }
        }
    }

}
