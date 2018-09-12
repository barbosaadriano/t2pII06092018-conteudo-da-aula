//nome do pacote
package br.com.adrianob.model;

//importações
import java.util.Arrays;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author drink
 */
//classe cliente
public class Cliente {
    //Variáveis de escopo de classe 
    //Constante array de String com os status validos
    public static final String[] statusValidos = {"Ativo","Inativo"};
    private int codigo;
    private String nome;
    private String cnpj;
    private String status;

    //construtor
    public Cliente() {
        //mostra que a propriedade status da classe recebe o status valido de index 0
        this.status = Cliente.statusValidos[0];
    }

    
    //getter
    public int getCodigo() {
        //retorna o codigo
        return codigo;
    }
    
    //setter
    public void setCodigo(int codigo) {
        //Aponta o valor no parametro para a váriavel codigo (escopo de classe)
        this.codigo = codigo;
    }
    
    //getter nome
    public String getNome() {
       //retorna nome
        return nome;
    }
    
    //setter nome
    public void setNome(String nome) {
       //mostra o valor no parametro para a váriavel nome (escopo de classe)
        this.nome = nome;
    }

    //getter cnpj
    public String getCnpj() {
        //retorna cnpj
        return cnpj;
    }
    
    //setter cnpj
    public void setCnpj(String cnpj) {
        //mostra o valor no parametro para a váriavel cnpj (escopo de classe)
        this.cnpj = cnpj;
    }
    
    //getter status
    public String getStatus() {
        //retorna status
        return status;
    }

    //setter status
    public void setStatus(String status) {
        //Se o parametro passado para status não for nenhum dos dois válidos
        if (Arrays.asList(statusValidos).indexOf(status) == -1) {
            //Apresenta uma exception de status inválido
            throw new RuntimeException("Status inválido!");
        } 
        //Aponta o valor no parametro para a váriavel status (escopo de classe)
        this.status = status;
    }

}
