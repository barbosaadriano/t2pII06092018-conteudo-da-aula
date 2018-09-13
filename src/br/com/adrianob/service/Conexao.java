// Nome do Pacote
package br.com.adrianob.service;

// Importações
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author drink
 */

//Classe Conexão
public class Conexao {

    // Declaração de váriaveis de escopo da classe
    private static Conexao instance = null;

    // Método para criação de conexão com o banco de dados
    private static Connection createConnection() {
        Properties config = new Properties();
        config.put("user", "devel");
        config.put("password", "developer");
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/singleton",
                    config
            );

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    private Connection conn;

    // Método de conexão com o Banco de Dados.
    private Conexao() {
        this.conn = Conexao.createConnection();
    }

    // Método para trazer a instância de acesso ao banco de dados
    public static Conexao getInstance() {
        if (Conexao.instance == null) {
            Conexao.instance = new Conexao();
        }
        return Conexao.instance;
    }

    // Método para Inicializar a Conexão com o Banco de Dados
    public Connection getConn() {
        return conn;
    }

}
