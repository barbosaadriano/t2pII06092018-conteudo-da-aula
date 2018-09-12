//NOME DO PACOTE
package br.com.adrianob.view;

//IMPORTAÇÕES
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
//CLASSE (TELA) DE EDIÇÃO, HERDANDO DE UM JDIALOG
public class ClienteEditView extends JDialog {

    //VARIÁVEIS DE ESCOPO DE CLASSE
    private ActionListener al;
    private JButton btnSalvar, btnVoltar;
    private JTextField txtCodigo, txtNome, txtCnpj;
    private JComboBox cbStatus;

    //CONSTRUTOR DA CLASSE
    public ClienteEditView(ActionListener al, Cliente c) {
        //CONSTRÓI A CLASSE PAI
        super();
        //APONTA O TÍTULO DA JANELA
        this.setTitle("Edição de  cliente");
        //APONTA QUE O ACTIONLISTENER DESTA CLASSE RECEBE O ACTIONLISTENER DO PARÂMETRO
        this.al = al;
        //ACIONA O MÉTODO INIT
        this.init();
        //ACIONA O MÉTODO SETDADOSCLIENTE PASSANDO COMO PARÂMETRO O CLIENTE PASASDO NO CONSTRUTOR
        this.setDadosCliente(c);
    }

    //MÉTODO INIT
    private void init() {
        //APONTA QUE O LAYOUT PADRÃO DA TELA SERÁ O BORDERLAYOUT
        this.setLayout(new BorderLayout());
        //CRIA UM NOVOPANEL RECEBENDO APONTANDO COMO LAYOUT UM FLOWLAYOUT
        JPanel pnControles = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //ADICIONA O PAINEL NA TELA, APONTANDO-O PARA A REGIÃO SUL
        this.add(pnControles, BorderLayout.SOUTH);
        //CRIA UM NOVO PANEL COM GRIDLAYOUT DE 2 COLUNAS
        JPanel pnCampos = new JPanel(new GridLayout(0, 2));
        //ADICIONA O PAINEL NA TELA, APONTANDO-O PARA A REGIÃO CENTRAL
        this.add(pnCampos, BorderLayout.CENTER);
        //COLOCA UMA BORDA NO PANEL 'PNCAMPOS' E COM UM TÍTULO
        pnCampos.setBorder(new TitledBorder("Dados do Cliente"));

        //INICIALIZA UMA VARIÁVEL DE ESCOPO DE CLASSE COM UM BOTÃO
        btnSalvar = new JButton("Salvar");
        //APONTA PARA ELA UM ACTIONCOMMAND
        btnSalvar.setActionCommand("cliente.salvar");
        //ADICIONA À ESSE BOTÃO UM ACTION LISTENER (O DA PRÓPRIA CLASSE)
        btnSalvar.addActionListener(al);

        //INICIALIZA UMA VARIÁVEL DE ESCOPO DE CLASSE COM UM BOTÃO
        btnVoltar = new JButton("Voltar");
        //APONTA PARA ELA UM ACTIONCOMMAND
        btnVoltar.setActionCommand("cliente.voltar");
        //ADICIONA À ESSE BOTÃO UM ACTION LISTENER
        btnVoltar.addActionListener(new ActionListener() {
            //SOBRECRITA DE MÉTODO
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ClienteEditView.this.al.actionPerformed(e);
            }
        });

        //ADICIONA O BOTÃO SALVAR NO PANEL DE CONTROLES
        pnControles.add(btnSalvar);
        //ADICIONA O BOTÃO VOLTAR NO PANEL DE CONTROLES
        pnControles.add(btnVoltar);

        //INICIALIZA UMA VARIÁVEL DE ESCOPO DE CLASSE COM UM TEXTFIELD
        txtCodigo = new JTextField();
        //APONTA QUE ELA NÃO PODE SER EDITADA
        txtCodigo.setEditable(false);
        //ADICIONA UMA LABEL NO PANEL DE CAMPOS
        pnCampos.add(new JLabel("Código"));
        //ADICIONA O TXTCODIGO NO PANEL DE CAMPOS
        pnCampos.add(txtCodigo);

        //INICIALIZA UMA VARIÁVEL DE ESCOPO DE CLASSE COM UM TEXTFIELD
        txtNome = new JTextField();
        //ADICIONAR UMA LABEL NO PANEL DE CAMPOS
        pnCampos.add(new JLabel("Nome"));
        //ADICIONA O TXTNOME DO PANEL DE CAMPOS
        pnCampos.add(txtNome);

        //INICIALIZA UMA VARIÁVEL DE ESCOPO DE CLASSE COM UM TEXTFIELD
        txtCnpj = new JTextField();
        //ADICIONA UMA LABEL NO PANEL DE CAMPOS
        pnCampos.add(new JLabel("Cnpj"));
        //ADICIONA O TXTCNPJ NO PANEL DE CAMPOS
        pnCampos.add(txtCnpj);

        //INICIALIZA UMA VARIÁVEL DE ESCOPO DE CLASSE COM UM COMBOBOX
        //COM AS OPÇOES DE STATUS VALIDOS
        cbStatus = new JComboBox(Cliente.statusValidos);
        //ADICIONA UM LABEL NO PANEL CAMPOS
        pnCampos.add(new JLabel("Situação"));
        //ADICIONA O COMBOBOX NO PANEL CAMPOS
        pnCampos.add(cbStatus);
        //AJEITA A TELA
        this.pack();
        //CENTRALIZA
        this.setLocationRelativeTo(null);
        //APONTA QUE NÃO FARÁ NADA QUANDO FECHAR A TELA
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    //MÉTODO PARA APONTAR OS DADOS DO CLIENTE PARA OS CAMPOS
    public void setDadosCliente(Cliente c) {
        //CAMPO DE CÓDIGO RECEBE O CÓDIGO DO OBJETO CLIENTE
        txtCodigo.setText(Integer.toString(c.getCodigo()));
        //CAMPO DE NOME RECEBE O NOME DO OBJETO CLIENTE
        txtNome.setText(c.getNome());
        //CAMPO DE CNPJ RECEBE O CNPJ DO OBJETO CLIENTE
        txtCnpj.setText(c.getCnpj());
        //CAMPO DE STATUS RECEBE O STATUS DO OBJETO CLIENTE
        cbStatus.setSelectedItem(c.getStatus());
    }

    //MÉTODO QUE RETORNA UM OBJETO CLIENTE COM OS DADOS DOS CAMPOS
    public Cliente getDadosCliente() {
        //CRIA UM OBJETO CLIENTE
        Cliente c = new Cliente();
        //APONTA O TEXTO DO CAMPO CÓDIGO PARA A PROPRIEDADE DO OBJETO CLIENTE
        c.setCodigo(Integer.parseInt(txtCodigo.getText()));
        //APONTA O TEXTO DO CAMPO NOME PARA A PROPRIEDADE DO OBJETO CLIENTE
        c.setNome(txtNome.getText());
        //APONTA O TEXTO DO CAMPO CNPJ PARA A PROPRIEDADE DO OBJETO CLIENTE
        c.setCnpj(txtCnpj.getText());
        //SE O COMBO BOX NÃO ESTIVER SELECIONADO ALGO INVÁLIDO
        if (cbStatus.getSelectedIndex() != -1) {
            //APONTA O TEXTO DO CAMPO STATUS PARA A PROPRIEDADE DO OBJETO CLIENTE
            c.setStatus(Cliente.statusValidos[cbStatus.getSelectedIndex()]);
        }
        //RETORNA O OBJETO CLIENTE
        return c;
    }

}