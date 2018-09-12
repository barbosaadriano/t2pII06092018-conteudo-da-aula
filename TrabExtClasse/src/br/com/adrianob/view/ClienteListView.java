//nome do pacote
package br.com.adrianob.view;

//importações
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
//Classe (tela) ClienteListView, herdando de um JFrame
public class ClienteListView extends JFrame {

    //Variáveis de escopo de classe
    private JTable tabela;
    private JButton btnListar;
    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnRemover;
    private JTextField txtPesquisa;
    private ActionListener al;

    //Construtor 
    public ClienteListView(ActionListener al) throws HeadlessException {
        //Aciona o construtor da classe pai
        super("Listar clientes");
        //Aponta para a variável(ActionListener) de escopo de classe o 
        //ActionListener passado como parâmetro no construtor
        this.al = al;
        //Aciona o método init
        this.init();
    }

    //Método para inicializar componentes
    private void init() {
        //Aponta o layout da tela como BorderLayout
        setLayout(new BorderLayout());
        //Cria um panel e aponta um FlowLayout
        JPanel pnControles = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //Adiciona o panel de controles na tela na região norte
        this.add(pnControles, BorderLayout.NORTH);
        //inicializa a variável de escopo de classe com uma tabela
        this.tabela = new JTable();
        //Aponta que o renderizado de células padrão é o AdrianoCellRenderer 
        this.tabela.setDefaultRenderer(Object.class, new AdrianoCellRenderer());
        //Cria um objeto JScrollPanel para a variável de escopo de classe (tabela)
        JScrollPane scroll = new JScrollPane(this.tabela);
        //Adiciona o scroll na tela
        this.add(scroll, BorderLayout.CENTER);

        //Inicializa uma variável de escopo de classe com um botão
        btnListar = new JButton("Listar");
        //Aponta um ActionCommand
        btnListar.setActionCommand("cliente.listar");
        //Adiciona ao botão um actionListener(o do escopo da classe)
        btnListar.addActionListener(al);

        //Inicializa uma variável de escopo de classe com um botão
        btnEditar = new JButton("Editar");
        //Aponta um ActionCommand
        btnEditar.setActionCommand("cliente.editar");
        //Adiciona ao botão um actionListener(o do escopo da classe)
        btnEditar.addActionListener(al);

        //Inicializa uma variável de escopo de classe com um botão
        btnNovo = new JButton("Novo");
        //Aponta um ActionCommand
        btnNovo.setActionCommand("cliente.novo");
        //Adiciona ao botão um actionListener(o do escopo da classe)
        btnNovo.addActionListener(al);

        //Inicializa uma variável de escopo de classe com um botão
        btnRemover = new JButton("Remover");
        //Aponta um ActionCommand
        btnRemover.setActionCommand("cliente.remover");
        //Adiciona ao botão um actionListener(o do escopo da classe)
        btnRemover.addActionListener(al);

        //Inicializa uma variável de escopo de classe com um TextField
        txtPesquisa = new JTextField();
        //Aponta uma dimensão para o campo de texto
        txtPesquisa.setPreferredSize(new Dimension(150, 30));

        //Adiciona os campos para o panel
        pnControles.add(new JLabel("Filtro: "));
        pnControles.add(txtPesquisa);
        pnControles.add(btnListar);
        pnControles.add(btnNovo);
        pnControles.add(btnEditar);
        pnControles.add(btnRemover);

        //Empacota
        this.pack();
        //Centraliza
        this.setLocationRelativeTo(null);
    }

    //Getter da tabela
    public JTable getTabela() {
        return tabela;
    }

    //Getter do campo de texto de pesquisa
    public JTextField getTxtPesquisa() {
        return txtPesquisa;
    }

}
