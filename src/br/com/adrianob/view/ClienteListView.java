//NOME DO PACOTE
package br.com.adrianob.view;

//IMPORTAÇÕES
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author drink
 */
//CLASSE (TELA) CLIENTELISTVIEW, HERDANDO DE UM JFRAME
public class ClienteListView extends JFrame {

    //VARIÁVEIS DE ESCOPO DE CLASSE
    private JTable tabela;
    private JButton btnListar;
    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnRemover;
    private JTextField txtPesquisa;
    private ActionListener al;

    //CONSTRUTOR 
    public ClienteListView(ActionListener al) throws HeadlessException {
        //ACIONA O CONSTRUTOR DA CLASSE PAI
        super("Listar clientes");
        //APONTA PARA A VARIÁVEL(ACTIONLISTENER) DE ESCOPO DE CLASSE O 
        //ACTIONLISTENER PASSADO COMO PARÂMETRO NO CONSTRUTOR
        this.al = al;
        //ACIONA O MÉTODO INIT
        this.init();
    }

    //MÉTODO PARA INICIALIZAR COMPONENTES
    private void init() {
        //APONTA O LAYOUT DA TELA COMO BORDERLAYOUT
        setLayout(new BorderLayout());
        //CRIA UM PANEL E APONTA UM FLOWLAYOUT
        JPanel pnControles = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //ADICIONA O PANEL DE CONTROLES NA TELA NA REGIÃO NORTE
        this.add(pnControles, BorderLayout.NORTH);
        //INICIALIZA A VARIÁVEL DE ESCOPO DE CLASSE COM UMA TABELA
        this.tabela = new JTable();
        //APONTA QUE O RENDERIZADO DE CÉLULAS PADRÃO É O ADRIANOCELLRENDERER 
        this.tabela.setDefaultRenderer(Object.class, new AdrianoCellRenderer());
        //CRIA UM OBJETO JSCROLLPANEL PARA A VARIÁVEL DE ESCOPO DE CLASSE (TABELA)
        JScrollPane scroll = new JScrollPane(this.tabela);
        //ADICIONA O SCROLL NA TELA
        this.add(scroll, BorderLayout.CENTER);

        //INICIALIZA UMA VARIÁVEL DE ESCOPO DE CLASSE COM UM BOTÃO
        btnListar = new JButton("Listar");
        //APONTA UM ACTIONCOMMAND
        btnListar.setActionCommand("cliente.listar");
        //ADICIONA AO BOTÃO UM ACTIONLISTENER(O DO ESCOPO DA CLASSE)
        btnListar.addActionListener(al);

        //INICIALIZA UMA VARIÁVEL DE ESCOPO DE CLASSE COM UM BOTÃO
        btnEditar = new JButton("Editar");
        //APONTA UM ACTIONCOMMAND
        btnEditar.setActionCommand("cliente.editar");
        //ADICIONA AO BOTÃO UM ACTIONLISTENER(O DO ESCOPO DA CLASSE)
        btnEditar.addActionListener(al);

        //INICIALIZA UMA VARIÁVEL DE ESCOPO DE CLASSE COM UM BOTÃO
        btnNovo = new JButton("Novo");
        //APONTA UM ACTIONCOMMAND
        btnNovo.setActionCommand("cliente.novo");
        //ADICIONA AO BOTÃO UM ACTIONLISTENER(O DO ESCOPO DA CLASSE)
        btnNovo.addActionListener(al);

        //INICIALIZA UMA VARIÁVEL DE ESCOPO DE CLASSE COM UM BOTÃO
        btnRemover = new JButton("Remover");
        //APONTA UM ACTIONCOMMAND
        btnRemover.setActionCommand("cliente.remover");
        //ADICIONA AO BOTÃO UM ACTIONLISTENER(O DO ESCOPO DA CLASSE)
        btnRemover.addActionListener(al);

        //INICIALIZA UMA VARIÁVEL DE ESCOPO DE CLASSE COM UM TEXTFIELD
        txtPesquisa = new JTextField();
        //APONTA UMA DIMENSÃO PARA O CAMPO DE TEXTO
        txtPesquisa.setPreferredSize(new Dimension(150, 30));

        //ADICIONA OS CAMPOS PARA O PANEL
        pnControles.add(new JLabel("Filtro: "));
        pnControles.add(txtPesquisa);
        pnControles.add(btnListar);
        pnControles.add(btnNovo);
        pnControles.add(btnEditar);
        pnControles.add(btnRemover);

        //EMPACOTA
        this.pack();
        //CENTRALIZA
        this.setLocationRelativeTo(null);
    }

    //GETTER DA TABELA
    public JTable getTabela() {
        return tabela;
    }

    //GETTER DO CAMPO DE TEXTO DE PESQUISA
    public JTextField getTxtPesquisa() {
        return txtPesquisa;
    }

}