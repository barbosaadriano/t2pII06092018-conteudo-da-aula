package br.com.adrianob.model;

import java.util.Arrays;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author drink
 */
public class Cliente {

    public static final String[] statusValidos = {"Ativo","Inativo"};
    private int codigo;
    private String nome;
    private String cnpj;
    private String status;

    public Cliente() {
        this.status = Cliente.statusValidos[0];
    }

    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (Arrays.asList(statusValidos).indexOf(status) == -1) {
            throw new RuntimeException("Status inválido!");
        } 
        this.status = status;
    }

}
