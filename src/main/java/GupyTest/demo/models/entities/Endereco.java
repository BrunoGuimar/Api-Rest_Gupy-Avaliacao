package GupyTest.demo.models.entities;

import jakarta.persistence.*;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String logradouro;
    private String cep;
    private int numero;
    private String cidade;
    private boolean ifPrincipal;

    public Endereco() {
    }

    public Endereco(String logradouro, String cep, int numero, String cidade) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.ifPrincipal = false;
    }

    public Endereco(String logradouro, String cep, int numero, String cidade, boolean ifPrincipal) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.ifPrincipal = ifPrincipal;
    }

    public boolean isIfPrincipal() {
        return ifPrincipal;
    }

    public void setIfPrincipal(boolean ifPrincipal) {
        this.ifPrincipal = ifPrincipal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
