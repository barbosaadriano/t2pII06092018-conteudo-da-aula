//Nome do pacote, a pasta onde a classe está
package br.com.adrianob.dao;

//Importações
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

//Classe DaoCliente, utilizado para se comunicar com o banco
public class DaoCliente {
    
    //Declaração dos atributos da classe como private
    private Connection conn;
    private static final String SELECT_ALL = "select * from tbl_cliente ";

    public DaoCliente(Connection cnx) {
        this.conn = cnx;
        criarTabela();
    }
    //Método para criação de Tabela
    private void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_cliente ";
        sql += "( codigo integer primary key not null auto_increment, ";
        sql += " nome varchar(60), cnpj varchar(20) unique, `status` ";
        sql += " varchar(8) not null default '" + Cliente.statusValidos[0] + "')";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    //Método para salvar informações na tabela e trazendo um objeto do tipo Cliente
    public void salvarCliente(Cliente c) {
		//SQL para dar update na tabela cliente
        String sql = "update tbl_cliente set nome = ? , ";
		//A segunda parte do SQL update
        sql += " cnpj = ?, `status` = ? where codigo = ? ";
		//Os dados que serão alterados na tabela, e o código do cliente que vai ser alterado
        int cod = c.getCodigo();
        String nome = c.getNome();
        String cnpj = c.getCnpj();
        String status = c.getStatus();
		//Se não possuir cliente cadastrado ele vai inserir um novo
        if (cod < 1) {
            cod = 0;
            sql = "insert into tbl_cliente (nome, cnpj, `status`, codigo) ";
            sql += "values (?, ?, ?, ?) ";
        }
        try {
			//Vai preparar o SQL, colocando os dados nos pontos certos do SQL
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(4, cod);
            ps.setString(1, nome);
            ps.setString(2, cnpj);
            ps.setString(3, status);
            ps.executeUpdate();
        } catch (SQLException ex) {
			//Caso ocorra algum erro ele cai aqui
            System.out.println(ex.getMessage());
        }
    }

    //Método para remoção de informações da Tabela
    public void removerCliente(Cliente c) {
		//SQL de remoção de cliente
        String sql = "delete from tbl_cliente where codigo = ?";
        try {
			//Vai preparar o SQL, colocando os dados nos pontos certos do SQL
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, c.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
			//Se der error vai cair aqui
            System.out.println(ex.getMessage());
        }
    }

    // Método para listagem das informações contidas na tabela
    public ArrayList<Cliente> listarCliente(Cliente c) {
        ArrayList<Cliente> retorno = new ArrayList<>();
        String where = " where 1=1 ";
		//Verifica se a variável c é diferente de nulo
        if (c != null) {
			//Se o código é maior que 0 ele vai concatenar esse sql no where
            if (c.getCodigo() > 0) {
                where += " AND codigo = " + c.getCodigo();
            }
			//se o nome é diferente de nulo e diferente de vazio ele vai concatenar esse sql no where+
            if ( c.getNome()!=null && !c.getNome().isEmpty()) {
                where += " OR nome like '" + c.getNome() + "%' ";
            }
			//se o cnph é diferente de nulo e diferente de vazio ele vai concatenar esse sql no where
            if ( c.getCnpj()!=null && !c.getCnpj().isEmpty()) {
                where += " OR cnpj like '%" + c.getCnpj() + "%' ";
            }
        }
        try {
			//Vai preparar o SQL, utilizando a variável select_all com o where proposto pelo if anterior
            PreparedStatement ps
                    = this.conn.prepareStatement(DaoCliente.SELECT_ALL + where);
			//Executa o SQL
            ResultSet rs = ps.executeQuery();
			//Lista a consulta feita
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setCodigo(rs.getInt("codigo"));
                cli.setNome(rs.getString("nome"));
                cli.setCnpj(rs.getString("cnpj"));
                cli.setStatus(rs.getString("status"));
                retorno.add(cli);
            }
        } catch (SQLException ex) {
			//Cai aqui se der error
            System.out.println(ex.getMessage());
        }
		//Retorna retorno
        return retorno;
    }

}
