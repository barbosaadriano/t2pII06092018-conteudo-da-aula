package br.com.adrianob;

import br.com.adrianob.controller.ClienteController;
import br.com.adrianob.service.Conexao;

/**
 *
 * @author drink
 */
public class AppExemplo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexao cnx = Conexao.getInstance();
        ClienteController cc = new ClienteController(cnx.getConn());
        cc.abrirLista();
    }
    
}
