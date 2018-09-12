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
        //O código a seguir pode gerar exceptions, então está circundado com tryCatch
        try {
            //Objeto PreparedStatement (ps) recebe o retorno de objeto PreparedStatement
            //parametizado com a string SQL
            PreparedStatement ps = this.conn.prepareStatement(sql);
            //Aponta o paramametro na SQL onde o index é 4, passando o código
            ps.setInt(4, cod);
            //Aponta o parametro na SQL onde o index é 1, passando o nome
            ps.setString(1, nome);
            //Aponta o parametro na SQL onde o index é 2, passando o cnpj
            ps.setString(2, cnpj);
            //Aponta o parametro na SQL onde o index é 3, passando o status
            ps.setString(3, status);
            //Executa o SQL
            ps.executeUpdate();
        } catch (SQLException ex) {
            //Caso der uma exception, printa a mensagem de erro
            System.out.println(ex.getMessage());
        }
    }

    //Método para remover cliente
    public void removerCliente(Cliente c) {
        //String SQL para remover cliente
        String sql = "delete from tbl_cliente where codigo = ?";
        //O código a seguir pode gerar exceptions, então está circundado com tryCatch
        try {
            //Objeto PreparedStatement (ps) recebe o retorno de objeto PreparedStatement
            //parametizado com a string SQL
            PreparedStatement ps = this.conn.prepareStatement(sql);
            //Aponta o paramametro na SQL onde o index é 1, passando o código
            ps.setInt(1, c.getCodigo());
            //Executa a SQL
            ps.executeUpdate();
        } catch (SQLException ex) {
            //Caso der uma exception, printa a mensagem de erro
            System.out.println(ex.getMessage());
        }
    }

    //Método que retorna lista de cliente
    public ArrayList<Cliente> listarCliente(Cliente c) {
        //Cria o ArrayList de retorno
        ArrayList<Cliente> retorno = new ArrayList<>();
        //Cria uma string de suporte para o SQL
        String where = " where 1=1 ";
        //Se o cliente passado no parâmetro não for nulo...
        if (c != null) {
            // Se o código do cliente passado for maior que 0
            if (c.getCodigo() > 0) {
                // String de suporte SQL concatena com texto para realizar a consulta utilizando o código
                where += " AND codigo = " + c.getCodigo();
            }
            //Se o nome do cliente não for nulo/vazio
            if (c.getNome() != null && !c.getNome().isEmpty()) {
                //String de suporte SQL concatena com texto para realizar a consulta utilizando o nome
                where += " OR nome like '" + c.getNome() + "%' ";
            }
            //Se o CNPJ não for nulo/vazio
            if (c.getCnpj() != null && !c.getCnpj().isEmpty()) {
                //String de suporte SQL concatena com texto para realizar a consulta utilizando o CNPJ
                where += " OR cnpj like '%" + c.getCnpj() + "%' ";
            }
        }
        //O código a seguir pode gerar exceptions, então está circundado com tryCatch
        try {
            //Objeto PreparedStatement (ps) recebe o retorno de objeto PreparedStatement
            //parametizado com a constante SELECT ALL concatenando com a string where
            PreparedStatement ps
                    = this.conn.prepareStatement(DaoCliente.SELECT_ALL + where);
            //Instancia um ResultSet que recebe o retorno da execução da consulta SQL
            ResultSet rs = ps.executeQuery();
            //Enquanto o ResultSet tiver registros...
            while (rs.next()) {
                //Cria um objeto cliente
                Cliente cli = new Cliente();
                //Aponta o código para o objeto pegando o código da consulta
                cli.setCodigo(rs.getInt("codigo"));
                //Aponta o nome para o objeto pegando o nome da consulta
                cli.setNome(rs.getString("nome"));
                //Aponta o cnpj para o objeto pegando o cnpj da consulta
                cli.setCnpj(rs.getString("cnpj"));
                //Aponta o status para o objeto pegando o status da consulta
                cli.setStatus(rs.getString("status"));
                //Adiciona o cliente no ArrayList de reusltado
                retorno.add(cli);
            }
        } catch (SQLException ex) {
            //Caso der uma exception, printa a mensagem de erro
            System.out.println(ex.getMessage());
        }
        //Retorna o ArrayList
        return retorno;
    }

}