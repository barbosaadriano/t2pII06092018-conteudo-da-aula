//Nome do Pacote
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

//Classe de Controle
public class DaoCliente {
    
    //Declaração de Váriaveis e Escopo da Classe
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
    
    //Método para salvar informações na tabela
    public void salvarCliente(Cliente c) {
        String sql = "update tbl_cliente set nome = ? , ";
        sql += " cnpj = ?, `status` = ? where codigo = ? ";
        int cod = c.getCodigo();
        String nome = c.getNome();
        String cnpj = c.getCnpj();
        String status = c.getStatus();
        if (cod < 1) {
            cod = 0;
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

    //Método para remoção de informações da Tabela
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

    // Método para listagem das informações contidas na tabela.
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
