//Nome do Pacote
package br.com.adrianob.model;

//Importações
import java.util.Arrays;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author drink
 */

//Classe Cliente
public class Cliente {

    // Declaração de Váriaveis  de escopo da classe.
    public static final String[] statusValidos = {"Ativo","Inativo"};
    private int codigo;
    private String nome;
    private String cnpj;
    private String status;
    
    // Método para 
    public Cliente() {
        this.status = Cliente.statusValidos[0];
    }

    
    // Método para trazer o código do cliente.
    public int getCodigo() {
        return codigo;
    }

    // Método para incluir código no cliente.
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Método para trazer o nome
    public String getNome() {
        return nome;
    }

        // Método para incluir o nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método para trazer o CNPJ.
    public String getCnpj() {
        return cnpj;
    }

    // Método para incluir o CNPJ.
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    // Método para trazer o Status.
    public String getStatus() {
        return status;
    }
    
    // Método para Incluir o Status.
    public void setStatus(String status) {
        if (Arrays.asList(statusValidos).indexOf(status) == -1) {
            throw new RuntimeException("Status inválido!");
        } 
        this.status = status;
    }

}
