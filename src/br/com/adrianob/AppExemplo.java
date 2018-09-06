//nome do pacote
package br.com.adrianob;
//importações
import br.com.adrianob.controller.ClienteController;
import br.com.adrianob.service.Conexao;
//classe principal
public class AppExemplo {
// método principal, inicia o programa
    public static void main(String[] args) {
        //instancia da classe Conexao
        Conexao cnx = Conexao.getInstance();
        //Declaração e atribuição da classe ClienteController
        ClienteController cc = new ClienteController(cnx.getConn());
        //Execução da classe Controller criada anteriormente
        cc.abrirLista();
    }
    
}
