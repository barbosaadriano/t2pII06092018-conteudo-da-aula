//nome do pacote
package br.com.adrianob.view;

//importações
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
//Classe (tela) de edição, herdando de um JDialog
public class ClienteEditView extends JDialog {
    
    //variaveis no escopo da classe
    private ActionListener al;
    private JButton btnSalvar, btnVoltar;
    private JTextField txtCodigo, txtNome, txtCnpj;
    private JComboBox cbStatus;
    
    //construtor da clase
    public ClienteEditView(ActionListener al,Cliente c) {
        //Constrói a classe pai
        super();
        //Aponta o título da janela
        this.setTitle("Edição de  cliente");
        //aponta que o ActionListener desta classe recebe o ActionListener do parâmetro
        this.al = al;
        //Aciona o método init
        this.init();
         //Aciona o método setDadosCliente passando como parâmetro o cliente pasasdo no construtor
        this.setDadosCliente(c);
    }
    
    //metodo init
    private void init() {
        //mostra que o Layout padrão da tela será o BorderLayout
        this.setLayout(new BorderLayout());
        //Cria um novopanel recebendo apontando como Layout um FlowLayout
        JPanel pnControles = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //Adiciona o painel na tela, apontando-o para a região sul
        this.add(pnControles, BorderLayout.SOUTH);
        
         //Cria um novo panel com GridLayout de 2 colunas
        JPanel pnCampos = new JPanel(new GridLayout(0, 2));
        //Adiciona o painel na tela, apontando-o para a região central
        this.add(pnCampos, BorderLayout.CENTER);
        //Coloca uma borda no panel 'pnCampos' e com um título
        pnCampos.setBorder(new TitledBorder("Dados do Cliente"));
        
        //Inicializa uma variável de escopo de classe com um botão
        btnSalvar = new JButton("Salvar");
        //Aponta para ela um actionCommand
        btnSalvar.setActionCommand("cliente.salvar");
        //Adiciona à esse botão um action listener (o da própria classe)
        btnSalvar.addActionListener(al);
        
        //Inicializa uma variável de escopo de classe com um botão
        btnVoltar = new JButton("Voltar");
        //Aponta para ela um actionCommand
        btnVoltar.setActionCommand("cliente.voltar");
        //Adiciona à esse botão um action listener
        btnVoltar.addActionListener(new ActionListener() {
            //Sobrecrita de método
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ClienteEditView.this.al.actionPerformed(e);
            }
        });
        
        //Adiciona o botão salvar no panel de controles
        pnControles.add(btnSalvar);
        //Adiciona o botão voltar no panel de controles
        pnControles.add(btnVoltar);
        
        //Inicializa uma variável de escopo de classe com um TextField
        txtCodigo = new JTextField();
        //Aponta que ela não pode ser editada
        txtCodigo.setEditable(false);
        //Adiciona uma label no panel de campos
        pnCampos.add(new JLabel("Código"));
        //Adiciona o txtCodigo no panel de campos
        pnCampos.add(txtCodigo);
        
        //Inicializa uma variável de escopo de classe com um TextField
        txtNome = new JTextField();
        //Adicionar uma label no panel de campos
        pnCampos.add(new JLabel("Nome"));
         //Adiciona o txtNome do panel de campos
        pnCampos.add(txtNome);
        
        //Inicializa uma variável de escopo de classe com um TextField
        txtCnpj = new JTextField();
        //Adiciona uma label no panel de campos
        pnCampos.add(new JLabel("Cnpj"));
        //Adiciona o txtCnpj no panel de Campos
        pnCampos.add(txtCnpj);
        
        //Inicializa uma variável de escopo de classe com um ComboBox
        //com as opçoes de status validos
        cbStatus = new JComboBox(Cliente.statusValidos);
        //adiciona um label no panel campos
        pnCampos.add(new JLabel("Situação"));
        //adiciona o combobox no panel campos
        pnCampos.add(cbStatus);
        //arruma a tela
        this.pack();
        //centraliza a tela
        this.setLocationRelativeTo(null);
        //mostra que não fará nada quando fechar a tela
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    //método para apontar os dados do cliente para os cambos
    public void setDadosCliente(Cliente c) {
        //Campo de código recebe o código do objeto cliente
        txtCodigo.setText(Integer.toString(c.getCodigo()));
        //Campo de nome recebe o nome do objeto cliente
        txtNome.setText(c.getNome());
        //Campo de cnpj recebe o cnpj do objeto cliente
        txtCnpj.setText(c.getCnpj());
        //Campo de status recebe o status do objeto cliente
        cbStatus.setSelectedItem(c.getStatus());
    }
    
     //Método que retorna um objeto cliente com os dados dos campos
    public Cliente getDadosCliente() {
        //Cria um objeto cliente
        Cliente c = new Cliente();
         //Aponta o texto do campo código para a propriedade do objeto cliente
        c.setCodigo(Integer.parseInt(txtCodigo.getText()));
        //Aponta o texto do campo nome para a propriedade do objeto cliente
        c.setNome(txtNome.getText());
         //Aponta o texto do campo cnpj para a propriedade do objeto cliente
        c.setCnpj(txtCnpj.getText());
         //Se o combo box não estiver selecionado algo inválido
        if (cbStatus.getSelectedIndex() != -1) {
             //Aponta o texto do campo status para a propriedade do objeto cliente
            c.setStatus(Cliente.statusValidos[cbStatus.getSelectedIndex()]);
        }
        //retorna cliente
        return c;
    }
    
}
