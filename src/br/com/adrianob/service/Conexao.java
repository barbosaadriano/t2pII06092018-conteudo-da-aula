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
//classe conexao
public class Conexao {

    //Variável de escopo de classe 
    private static Conexao instance = null;

    //Método de retorno de Connection (com objetivo de cria-la)
    private static Connection createConnection() {
        //Cria um objeto Properties
        Properties config = new Properties();
        //Insere valores de configuração
        config.put("user", "devel");
        //Insere valores de configuração
        config.put("password", "developer");
        //Cria uma Connection nula
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

        //Caso der uma exception, printa a mensagem de erro
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        //retorna conexão 
        return conn;
    }
    //Variável de escopo de classe
    private Connection conn;

    //construtor da classe
    private Conexao() {
        //Variável de escopo de classe recebe o retorno do método createConnection
        this.conn = Conexao.createConnection();
    }

    //Método Getter da instance
    public static Conexao getInstance() {
         //Se a instância estiver nula..
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
