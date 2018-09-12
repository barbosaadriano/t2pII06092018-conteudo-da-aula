//NOME DO PACOTE
package br.com.adrianob.dao;

//IMPORTAÇÕES
import br.com.adrianob.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author drink
 */
 //CLASSE DAOCLIENTE
public class DaoCliente {
	
	//VARIÁVEL DE ESCOPO DE CLASSE DE OBJETO CONEXÃO
    private Connection conn;
	//CONSTANTE SQL DE CONSULTA (SELECT)
    private static final String SELECT_ALL = "select * from tbl_cliente ";
	//CONSTRUTOR DA CLASSE COM INJEÇÃO DE DEPENDÊNCIA DE UMA CONEXÃO
    public DaoCliente(Connection cnx) {
        this.conn = cnx;
        criarTabela();
    }

	 //VARIÁVEL DE ESCOPO DE CLASSE RECEBE A CONEXÃO PASSADA NO PARÂMETRO DO CONTRUTOR
        this.conn = cnx;
        //ACIONA O MÉTODO CRIARTABELA
        criarTabela();
    }

    //MÉTODO DE CRIAÇÃO DE TABELA (SQL)
    private void criarTabela() {
        //UMA STRING COM O CÓDIGO SQL PARA CRIAÇÃO DA TABELA CLIENTE CASO ELA NÃO EXISTA
        String sql = "CREATE TABLE IF NOT EXISTS tbl_cliente ";
        sql += "( codigo integer primary key not null auto_increment, ";
        sql += " nome varchar(60), cnpj varchar(20) unique, `status` ";
        sql += " varchar(8) not null default '" + Cliente.statusValidos[0] + "')";
        
        //O CÓDIGO A SEGUIR PODE GERAR EXCEPTIONS, POR ISSO ESTÁ DENTRO DE UM TRYCATCH
        try {
            //OBJETO PREPAREDSTATEMENT (PS) RECEBE O RETORNO DE OBJETO PREPAREDSTATEMENT
            //PARAMETIZADO COM A STRING SQL
            PreparedStatement ps = this.conn.prepareStatement(sql);
            //EXECUTA O CÓDIGO SQL
            ps.execute();
        } catch (SQLException ex) {
            //CASO DER UMA EXCEPTION, PRINTA A MENSAGEM DE ERRO
            System.out.println(ex.getMessage());
        }

    }

    //Método para salvar o cliente
    public void salvarCliente(Cliente c) {
        //STRING SQL PARA UPDATE
        String sql = "update tbl_cliente set nome = ? , ";
        sql += " cnpj = ?, `status` = ? where codigo = ? ";
        //VARIÁVEL PARA RECEBER O CÓDIGO DO CLIENTE PASSADO NO PARAMÂMETRO DO MÉTODO
        int cod = c.getCodigo();
        //VARIÁVEL RECEBE O NOME DO CLIENTE
        String nome = c.getNome();
        //VARIÁVEL RECEBE O CNPJ DO CLIENTE
        String cnpj = c.getCnpj();
        //VARIÁVEL RECEBE O STATUS DO CLIENTE
        String status = c.getStatus();
        //SE A VARIÁVEL CÓDIGO FOR MENOR QUE 1
        if (cod < 1) {
            //VARIÁVEL CÓDIGO RECEBE VALOR 0
            cod = 0;
            //STRING SQL RECEBE CÓDIGO DE INSERÇÃO DE CLIENTE
            sql = "insert into tbl_cliente (nome, cnpj, `status`, codigo) ";
            sql += "values (?, ?, ?, ?) ";
			
        //O CÓDIGO A SEGUIR PODE GERAR EXCEPTIONS, ENTÃO ESTÁ CIRCUNDADO COM TRYCATCH
        try {
            //OBJETO PREPAREDSTATEMENT (PS) RECEBE O RETORNO DE OBJETO PREPAREDSTATEMENT
            //PARAMETIZADO COM A STRING SQL
            PreparedStatement ps = this.conn.prepareStatement(sql);
            //APONTA O PARAMAMETRO NA SQL ONDE O INDEX É 4, PASSANDO O CÓDIGO
            ps.setInt(4, cod);
            //APONTA O PARAMETRO NA SQL ONDE O INDEX É 1, PASSANDO O NOME
            ps.setString(1, nome);
            //APONTA O PARAMETRO NA SQL ONDE O INDEX É 2, PASSANDO O CNPJ
            ps.setString(2, cnpj);
            //APONTA O PARAMETRO NA SQL ONDE O INDEX É 3, PASSANDO O STATUS
            ps.setString(3, status);
            //EXECUTA O SQL
            ps.executeUpdate();
        } catch (SQLException ex) {
            //CASO DER UMA EXCEPTION, PRINTA A MENSAGEM DE ERRO
            System.out.println(ex.getMessage());
        }
    }

    //MÉTODO PARA REMOVER CLIENTE
    public void removerCliente(Cliente c) {
        //STRING SQL PARA REMOVER CLIENTE
        String sql = "delete from tbl_cliente where codigo = ?";
        //O CÓDIGO A SEGUIR PODE GERAR EXCEPTIONS, ENTÃO ESTÁ CIRCUNDADO COM TRYCATCH
        try {
            //OBJETO PREPAREDSTATEMENT (PS) RECEBE O RETORNO DE OBJETO PREPAREDSTATEMENT
            //PARAMETIZADO COM A STRING SQL
            PreparedStatement ps = this.conn.prepareStatement(sql);
            //APONTA O PARAMAMETRO NA SQL ONDE O INDEX É 1, PASSANDO O CÓDIGO
            ps.setInt(1, c.getCodigo());
            //EXECUTA A SQL
            ps.executeUpdate();
        } catch (SQLException ex) {
            //CASO DER UMA EXCEPTION, PRINTA A MENSAGEM DE ERRO
            System.out.println(ex.getMessage());
        }
    }

    //MÉTODO QUE RETORNA LISTA DE CLIENTE
    public ArrayList<Cliente> listarCliente(Cliente c) {
        //CRIA O ARRAYLIST DE RETORNO
        ArrayList<Cliente> retorno = new ArrayList<>();
        //CRIA UMA STRING DE SUPORTE PARA O SQL
        String where = " where 1=1 ";
        //SE O CLIENTE PASSADO NO PARÂMETRO NÃO FOR NULO
        if (c != null) {
            // SE O CÓDIGO DO CLIENTE PASSADO FOR MAIOR QUE 0
            if (c.getCodigo() > 0) {
                // STRING DE SUPORTE SQL CONCATENA COM TEXTO PARA REALIZAR A CONSULTA UTILIZANDO O CÓDIGO
                where += " AND codigo = " + c.getCodigo();
            }
            //SE O NOME DO CLIENTE NÃO FOR NULO/VAZIO
            if (c.getNome() != null && !c.getNome().isEmpty()) {
                //STRING DE SUPORTE SQL CONCATENA COM TEXTO PARA REALIZAR A CONSULTA UTILIZANDO O NOME
                where += " OR nome like '" + c.getNome() + "%' ";
            }
            //SE O CNPJ NÃO FOR NULO/VAZIO
            if (c.getCnpj() != null && !c.getCnpj().isEmpty()) {
                //STRING DE SUPORTE SQL CONCATENA COM TEXTO PARA REALIZAR A CONSULTA UTILIZANDO O CNPJ
                where += " OR cnpj like '%" + c.getCnpj() + "%' ";
            }
        }
        //O CÓDIGO A SEGUIR PODE GERAR EXCEPTIONS, ENTÃO ESTÁ CIRCUNDADO COM TRYCATCH
        try {
            //OBJETO PREPAREDSTATEMENT (PS) RECEBE O RETORNO DE OBJETO PREPAREDSTATEMENT
            //PARAMETIZADO COM A CONSTANTE SELECT ALL CONCATENANDO COM A STRING WHERE
            PreparedStatement ps
                    = this.conn.prepareStatement(DaoCliente.SELECT_ALL + where);
            //INSTANCIA UM RESULTSET QUE RECEBE O RETORNO DA EXECUÇÃO DA CONSULTA SQL
            ResultSet rs = ps.executeQuery();
            //ENQUANTO O RESULTSET TIVER REGISTROS
            while (rs.next()) {
                //CRIA UM OBJETO CLIENTE
                Cliente cli = new Cliente();
                //APONTA O CÓDIGO PARA O OBJETO PEGANDO O CÓDIGO DA CONSULTA
                cli.setCodigo(rs.getInt("codigo"));
                //APONTA O NOME PARA O OBJETO PEGANDO O NOME DA CONSULTA
                cli.setNome(rs.getString("nome"));
                //APONTA O CNPJ PARA O OBJETO PEGANDO O CNPJ DA CONSULTA
                cli.setCnpj(rs.getString("cnpj"));
                //APONTA O STATUS PARA O OBJETO PEGANDO O STATUS DA CONSULTA
                cli.setStatus(rs.getString("status"));
                //ADICIONA O CLIENTE NO ARRAYLIST DE REUSLTADO
                retorno.add(cli);
            }
        } catch (SQLException ex) {
            //CASO DER UMA EXCEPTION, PRINTA A MENSAGEM DE ERRO
            System.out.println(ex.getMessage());
        }
        //RETORNA O ARRAYLIST
        return retorno;
    }

}
