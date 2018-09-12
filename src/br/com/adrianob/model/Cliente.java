//Nome do pacote
package br.com.adrianob.model;

//Importações
import java.util.Arrays;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author drink
 */
//Classe cliente
public class Cliente {
    //Variáveis de escopo de classe 
    //Constante array de String com os status validos
    public static final String[] statusValidos = {"Ativo","Inativo"};
    private int codigo;
    private String nome;
    private String cnpj;
    private String status;

    //Construtor
    public Cliente() {
        //Aponta que a propriedade status da classe recebe o status valido de index 0
        this.status = Cliente.statusValidos[0];
    }

    
    //Getter (codigo)
    public int getCodigo() {
        //retorna o codigo
        return codigo;
    }

    //Setter (codigo)
    public void setCodigo(int codigo) {
        //Aponta o valor no parametro para a váriavel codigo (escopo de classe)
        this.codigo = codigo;
    }

    //Getter (nome)
    public String getNome() {
        //retorna o nome
        return nome;
    }

    //Setter (nome)
    public void setNome(String nome) {
         //Aponta o valor no parametro para a váriavel nome (escopo de classe)
        this.nome = nome;
    }

    //Getter (cnpj)
    public String getCnpj() {
        //Retorna CNPJ
        return cnpj;
    }

    //Setter (cnpj)
    public void setCnpj(String cnpj) {
         //Aponta o valor no parametro para a váriavel cnpj (escopo de classe)
        this.cnpj = cnpj;
    }

    //Getter (status)
    public String getStatus() {
        //retorna status
        return status;
    }

    //Setter (status)
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