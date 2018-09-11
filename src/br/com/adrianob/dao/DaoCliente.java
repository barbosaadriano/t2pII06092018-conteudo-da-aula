//Nome do pacote
package br.com.adrianob.dao;

//importações
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
//Clase DAOCliente
public class DaoCliente {
    
    //Variável de escopo de classe de objeto Conexão
    private Connection conn;
    //Constante SQL de consulta (select)
    private static final String SELECT_ALL = "select * from tbl_cliente ";

    //Construtor da classe com injeção de dependência de uma conexão
    public DaoCliente(Connection cnx) {
        //Variável de escopo de classe recebe a conexão passada no parâmetro do contrutor
        this.conn = cnx;
        //Aciona o método criarTabela
        criarTabela();
    }

    //Método de criação de tabela (SQL)
    private void criarTabela() {
        //Uma string com o código SQL para criação da tabela cliente caso ela não exista
        String sql = "CREATE TABLE IF NOT EXISTS tbl_cliente ";
        sql += "( codigo integer primary key not null auto_increment, ";
        sql += " nome varchar(60), cnpj varchar(20) unique, `status` ";
        sql += " varchar(8) not null default '" + Cliente.statusValidos[0] + "')";
        
        //O código a seguir pode gerar exceptions, por isso está dentro de um TryCatch
        try {
            //Objeto PreparedStatement (ps) recebe o retorno de objeto PreparedStatement
            //parametizado com a string SQL
            PreparedStatement ps = this.conn.prepareStatement(sql);
            //Executa o código SQL
            ps.execute();
        } catch (SQLException ex) {
            //Caso der uma exception, printa a mensagem de erro
            System.out.println(ex.getMessage());
        }

    }

    //Método para salvar o cliente
    public void salvarCliente(Cliente c) {
        //String SQL para update
        String sql = "update tbl_cliente set nome = ? , ";
        sql += " cnpj = ?, `status` = ? where codigo = ? ";
        //variável para receber o código do cliente passado no paramâmetro do método
        int cod = c.getCodigo();
        //variável recebe o nome do cliente
        String nome = c.getNome();
        //variável recebe o cnpj do cliente
        String cnpj = c.getCnpj();
        //variável recebe o status do cliente
        String status = c.getStatus();
        //se a variável código for menor que 1
        if (cod < 1) {
            //variável código recebe valor 0
            cod = 0;
            //String SQL recebe código de inserção de cliente
            sql = "insert into tbl_cliente (nome, cnpj, `status`, codigo) ";
            sql += "values (?, ?, ?, ?) ";
        }
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(4, cod);
            ps.setString(1, nome);
            ps.setString(2, cnpj);
            ps.setString(3, status);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void removerCliente(Cliente c) {
        String sql = "delete from tbl_cliente where codigo = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, c.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Cliente> listarCliente(Cliente c) {
        ArrayList<Cliente> retorno = new ArrayList<>();
        String where = " where 1=1 ";
        if (c != null) {
            if (c.getCodigo() > 0) {
                where += " AND codigo = " + c.getCodigo();
            }
            if ( c.getNome()!=null && !c.getNome().isEmpty()) {
                where += " OR nome like '" + c.getNome() + "%' ";
            }
            if ( c.getCnpj()!=null && !c.getCnpj().isEmpty()) {
                where += " OR cnpj like '%" + c.getCnpj() + "%' ";
            }
        }
        try {
            PreparedStatement ps
                    = this.conn.prepareStatement(DaoCliente.SELECT_ALL + where);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setCodigo(rs.getInt("codigo"));
                cli.setNome(rs.getString("nome"));
                cli.setCnpj(rs.getString("cnpj"));
                cli.setStatus(rs.getString("status"));
                retorno.add(cli);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

}
