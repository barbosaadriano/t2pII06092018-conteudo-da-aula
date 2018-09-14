//NOME DO PACOTE
package br.com.adrianob.service;

//IMPORTAÇÕES
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author drink
 */
//CLASSE CONEXÃO
public class Conexao {

    //VARIÁVEL DE ESCOPO DE CLASSE (DA PRÓPRIA CLASSE)
    private static Conexao instance = null;

    //MÉTODO DE RETORNO DE CONNECTION (COM OBJETIVO DE CRIA-LA)
    private static Connection createConnection() {
        //CRIA UM OBJETO PROPERTIES
        Properties config = new Properties();
        //INSERE VALORES DE CONFIGURAÇÃO
        config.put("user", "devel");
        //INSERE VALORES DE CONFIGURAÇÃO
        config.put("password", "developer");
        //CRIA UMA CONNECTION NULA
        Connection conn = null;
         //O CÓDIGO A SEGUIR PODE GERAR EXCEPTIONS, ENTÃO ESTÁ CIRCUNDADO COM TRYCATCH
        try {
            //PASSA O DRIVER DE CONEXÃO
            Class.forName("com.mysql.jdbc.Driver");
            //A CONNECTION TENTA CONECTAR UTILIZANDO O DRIVER E AS CONFIGURAÇÕES PASSADAS
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/singleton",
                    config
            );

        } catch (ClassNotFoundException | SQLException e) {
             //CASO DER UMA EXCEPTION, PRINTA A MENSAGEM DE ERRO
            System.out.println(e.getMessage());
        }
        //RETORNA A CONNECTION
        return conn;
    }
    //VARIÁVEL DE ESCOPO DE CLASSE
    private Connection conn;

    //CONSTRUTOR DA CLASSE
    private Conexao() {
        //VARIÁVEL DE ESCOPO DE CLASSE RECEBE O RETORNO DO MÉTODO CREATECONNECTION
        this.conn = Conexao.createConnection();
    }

    //MÉTODO GETTER DA INSTANCE
    public static Conexao getInstance() {
        //SE A INSTÂNCIA ESTIVER NULA
        if (Conexao.instance == null) {
            //A INSTÂNCIA RECEBE UMA NOVA CONEXAO
            Conexao.instance = new Conexao();
        }
        //RETORNA A INSTÂNCIA DA CLASSE CONEXAO
        return Conexao.instance;
    }

    //MÉTODO GETTER DA VARIÁVEL CONN
    public Connection getConn() {
        //RETORNA A CONNECTION
        return conn;
    }

}