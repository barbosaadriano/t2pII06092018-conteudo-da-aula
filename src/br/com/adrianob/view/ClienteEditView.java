
// Nome do Pacote
package br.com.adrianob.view;

// Importações
import br.com.adrianob.model.Cliente;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author drink
 */

// Classe ClienteEditView extendendo  JDialog
public class ClienteEditView extends JDialog {
    
    // Declaração de variáveis de escopo da classe
    private ActionListener al;
    private JButton btnSalvar, btnVoltar;
    private JTextField txtCodigo, txtNome, txtCnpj;
    private JComboBox cbStatus;
    
    // Método para criação da tela "Edição de CLientes"
    public ClienteEditView(ActionListener al,Cliente c) {
        super();
        this.setTitle("Edição de  cliente");
        this.al = al;
        this.init();
        this.setDadosCliente(c);
    }
    
    // Método para incluir os Objetos (Botões, Labels, etc.), na tela "Edição de Clientes"
    private void init() {
        this.setLayout(new BorderLayout());
        JPanel pnControles = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.add(pnControles, BorderLayout.SOUTH);
        
        JPanel pnCampos = new JPanel(new GridLayout(0, 2));
        this.add(pnCampos, BorderLayout.CENTER);
        pnCampos.setBorder(new TitledBorder("Dados do Cliente"));
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setActionCommand("cliente.salvar");
        btnSalvar.addActionListener(al);
        
        btnVoltar = new JButton("Voltar");
        btnVoltar.setActionCommand("cliente.voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ClienteEditView.this.al.actionPerformed(e);
            }
        });
        
        pnControles.add(btnSalvar);
        pnControles.add(btnVoltar);
        
        txtCodigo = new JTextField();
        txtCodigo.setEditable(false);
        pnCampos.add(new JLabel("Código"));
        pnCampos.add(txtCodigo);
        
        txtNome = new JTextField();
        pnCampos.add(new JLabel("Nome"));
        pnCampos.add(txtNome);
        
        txtCnpj = new JTextField();
        pnCampos.add(new JLabel("Cnpj"));
        pnCampos.add(txtCnpj);
        
        cbStatus = new JComboBox(Cliente.statusValidos);
        pnCampos.add(new JLabel("Situação"));
        pnCampos.add(cbStatus);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    // Método para para Salvar os dados do cliente  cadastrado
    public void setDadosCliente(Cliente c) {
        txtCodigo.setText(Integer.toString(c.getCodigo()));
        txtNome.setText(c.getNome());
        txtCnpj.setText(c.getCnpj());
        cbStatus.setSelectedItem(c.getStatus());
    }
    
    // Método para trazer os dados dos clientes do Banco de Dados.
    public Cliente getDadosCliente() {
        Cliente c = new Cliente();
        c.setCodigo(Integer.parseInt(txtCodigo.getText()));
        c.setNome(txtNome.getText());
        c.setCnpj(txtCnpj.getText());
        if (cbStatus.getSelectedIndex() != -1) {
            c.setStatus(Cliente.statusValidos[cbStatus.getSelectedIndex()]);
        }
        return c;
    }
    
}
