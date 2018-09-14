//NOME DO PACOTE
package br.com.adrianob.model;

//IMPORTAÇÕES
import java.util.Arrays;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author drink
 */
//CLASSE CLIENTE
public class Cliente {
    //VARIÁVEIS DE ESCOPO DE CLASSE 
    //CONSTANTE ARRAY DE STRING COM OS STATUS VALIDOS
    public static final String[] statusValidos = {"Ativo","Inativo"};
    private int codigo;
    private String nome;
    private String cnpj;
    private String status;

    //CONSTRUTOR
    public Cliente() {
        //APONTA QUE A PROPRIEDADE STATUS DA CLASSE RECEBE O STATUS VALIDO DE INDEX 0
        this.status = Cliente.statusValidos[0];
    }

    
    //GETTER (CODIGO)
    public int getCodigo() {
        //RETORNA O CODIGO
        return codigo;
    }

    //SETTER (CODIGO)
    public void setCodigo(int codigo) {
        //APONTA O VALOR NO PARAMETRO PARA A VÁRIAVEL CODIGO (ESCOPO DE CLASSE)
        this.codigo = codigo;
    }

    //GETTER (NOME)
    public String getNome() {
        //RETORNA O NOME
        return nome;
    }

    //SETTER (NOME)
    public void setNome(String nome) {
         //APONTA O VALOR NO PARAMETRO PARA A VÁRIAVEL NOME (ESCOPO DE CLASSE)
        this.nome = nome;
    }

    //GETTER (CNPJ)
    public String getCnpj() {
        //RETORNA CNPJ
        return cnpj;
    }

    //SETTER (CNPJ)
    public void setCnpj(String cnpj) {
         //APONTA O VALOR NO PARAMETRO PARA A VÁRIAVEL CNPJ (ESCOPO DE CLASSE)
        this.cnpj = cnpj;
    }

    //GETTER (STATUS)
    public String getStatus() {
        //RETORNA STATUS
        return status;
    }

    //SETTER (STATUS)
    public void setStatus(String status) {
        //SE O PARAMETRO PASSADO PARA STATUS NÃO FOR NENHUM DOS DOIS VÁLIDOS
        if (Arrays.asList(statusValidos).indexOf(status) == -1) {
            //APRESENTA UMA EXCEPTION DE STATUS INVÁLIDO
            throw new RuntimeException("Status inválido!");
        } 
        //APONTA O VALOR NO PARAMETRO PARA A VÁRIAVEL STATUS (ESCOPO DE CLASSE)
        this.status = status;
    }

}