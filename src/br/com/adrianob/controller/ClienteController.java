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
//CLASSE CLIENTECONTROLLER COM INTERFACE DE ACTIONLISTENER
public class ClienteController implements ActionListener {

    //DECLARAÇÃO DE VARIAVEIS DE ESCOPO DE CLASSE
    private ClienteListView telaLista;
    private ClienteEditView edicao;
    private DaoCliente dc;

    //CONSTRUTOR DA CLASSE COM INJEÇÃO DE DEPEDENCI
    public ClienteController(Connection conn) {
        dc = new DaoCliente(conn);
    }

    //METODO DA INTERFACE DE ACTIONLISTENER	
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //CONDIÇÃO PARA COMANDO PASSADO PARA O ACTIONPERFORMED
        switch (e.getActionCommand()){
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
        //se a variável (escopo de classe) for nula...
        if (this.telaLista == null) {
            //Faz com que a variável receba um objeto do tipo ClienteListView.
            this.telaLista = new ClienteListView(this);
            //Aponta que a tela criada fechara o sistema completo quando for fechada
            this.telaLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        //mostra que a a tela ficará visível
        this.telaLista.setVisible(true);
    }
    
   //metodo para listar
    private void listar() {
        //Cria um ArrayList de objeto Cliente 
        ArrayList<Cliente> listarCliente = new ArrayList<>();
        //se o input de pesquisa não estiver vazio
        if (!telaLista.getTxtPesquisa().getText().isEmpty()) {
            //Cria um novo objeto cliente
            Cliente c = new Cliente();
            //Aciona o método para mudar a propriedade nome passando o input de pesquisa
            c.setNome(telaLista.getTxtPesquisa().getText());
            //Retorna o cliente utilizando o método do DaoCliente
            listarCliente = this.dc.listarCliente(c);
        } else {  //se estiver vazio o input de pesquisa, retorna todos clientes
            listarCliente = this.dc.listarCliente(null);
        }
        //Atualiza o modelo da tabela para a consulta
        telaLista.getTabela().setModel(new ClienteTableModel(listarCliente));
    }

    //metodo para um cadastro novo
    private void novo() {
        //Abre a tela de edição para o cadastro de um novo cliente
        this.abrirEdicao(new Cliente());
    }

    //metodo para salvar
    private void salvar() {
        //se a tela de edição não esteja inicializada
        if (edicao != null) {
            //Objeto cliente recebe os dados da tela de edição
            Cliente cli = edicao.getDadosCliente();
            //Se o objeto cliente não estiver nulo
            if (cli != null) {
                //Salva o cliente utilizando o DAO
                dc.salvarCliente(cli);
                listar();
                //Tela de edição é finalizada
                edicao = null;
            }
        }
    }

    //metodo editar
    private void editar() {
        //Abre a tela de edição usando o cliente selecionado na tabela.
        abrirEdicao(getClientSelecionado());
    }

    //Método para pegar o cliente selecionado
    private Cliente getClientSelecionado() {
        //se a tela de listagem esteja inicializada
        if (this.telaLista != null) {
            //se a contagem de linhas selecionadas seja igual à 1
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
                    //retorna o resultado
                    return lc.get(0);
                }
            }
        }
        //senao retorna nulo
        return null;
    }

    //metodo remover
    private void remover() {
        //se confirmar a remoção
        if (JOptionPane.showConfirmDialog(edicao, "Deseja Remover?", "confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION) {
            //Objeto cliente recebe o cliente selecionado
            Cliente cli = getClientSelecionado();
            //se nao for nulo
            if (cli != null) {
                ////Ativa o método de remoção do DAO
                dc.removerCliente(cli);
                //lista novamente
                listar();
            }
        }
    }

}
