//nome do pacote
package br.com.adrianob.service;

//importações
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author drink
 */
//Classe conexão
public class Conexao {

    //Variável de escopo de classe (da própria classe)
    private static Conexao instance = null;

    //Método de retorno de Connection (com objetivo de cria-la)
    private static Connection createConnection() {
        //Cria um objeto Properties
        Properties config = new Properties();
        //Insere valores de configuração
        config.put("user", "devel");
        //Insere valores de configuração
        config.put("password", "developer");
        //Crua uma Connection nula
        Connection conn = null;
         //O código a seguir pode gerar exceptions, então está circundado com tryCatch
        try {
            //Passa o driver de conexão
            Class.forName("com.mysql.jdbc.Driver");
            //A Connection tenta conectar utilizando o driver e as configurações passadas
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/singleton",
                    config
            );

        } catch (ClassNotFoundException | SQLException e) {
             //Caso der uma exception, printa a mensagem de erro
            System.out.println(e.getMessage());
        }
        //retorna a Connection
        return conn;
    }
    //Variável de escopo de classe
    private Connection conn;

    //Construtor da classe
    private Conexao() {
        //Variável de escopo de classe recebe o retorno do método createConnection
        this.conn = Conexao.createConnection();
    }

    //Método Getter da instance
    public static Conexao getInstance() {
        //Se a instância estiver nula...
        if (Conexao.instance == null) {
            //A instância recebe uma nova Conexao
            Conexao.instance = new Conexao();
        }
        //retorna a instância da classe Conexao
        return Conexao.instance;
    }

    //Método Getter da variável conn
    public Connection getConn() {
        //retorna a Connection
        return conn;
    }

}
