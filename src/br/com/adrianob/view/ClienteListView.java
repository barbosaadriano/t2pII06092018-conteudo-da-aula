// Nome do Pacote
package br.com.adrianob.view;

//Importações
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

//Classe ClienteListView extendendo JFrame
public class ClienteListView extends JFrame {

    // Declaração das variáveis de escopo da classe
    private JTable tabela;
    private JButton btnListar;
    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnRemover;
    private JTextField txtPesquisa;
    private ActionListener al;

    // Método para criação da tela "Listar Clientes"
    public ClienteListView(ActionListener al) throws HeadlessException {
        super("Listar clientes");
        this.al = al;
        this.init();
    }

    // Método para incluir os Objetos (Botões, Labels, etc.), na tela "Listar Clientes"
    private void init() {
        setLayout(new BorderLayout());
        JPanel pnControles = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.add(pnControles, BorderLayout.NORTH);
        this.tabela = new JTable();
        this.tabela.setDefaultRenderer(Object.class, new AdrianoCellRenderer());
        JScrollPane scroll = new JScrollPane(this.tabela);
        this.add(scroll, BorderLayout.CENTER);

        btnListar = new JButton("Listar");
        btnListar.setActionCommand("cliente.listar");
        btnListar.addActionListener(al);

        btnEditar = new JButton("Editar");
        btnEditar.setActionCommand("cliente.editar");
        btnEditar.addActionListener(al);

        btnNovo = new JButton("Novo");
        btnNovo.setActionCommand("cliente.novo");
        btnNovo.addActionListener(al);

        btnRemover = new JButton("Remover");
        btnRemover.setActionCommand("cliente.remover");
        btnRemover.addActionListener(al);

        txtPesquisa = new JTextField();
        txtPesquisa.setPreferredSize(new Dimension(150, 30));

        pnControles.add(new JLabel("Filtro: "));
        pnControles.add(txtPesquisa);
        pnControles.add(btnListar);
        pnControles.add(btnNovo);
        pnControles.add(btnEditar);
        pnControles.add(btnRemover);

        this.pack();
        this.setLocationRelativeTo(null);
    }

    // Método para ~mostrar as informações treazidas em uma tabela
    public JTable getTabela() {
        return tabela;
    }
    // Método para realizar a pesquisa.
    public JTextField getTxtPesquisa() {
        return txtPesquisa;
    }

    
}
