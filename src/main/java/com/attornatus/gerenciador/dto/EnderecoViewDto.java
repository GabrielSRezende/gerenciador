package com.attornatus.gerenciador.dto;

import com.attornatus.gerenciador.model.Pessoa;

public class EnderecoViewDto {

    private long id;

    private String logradouro;

    private String cep;

    private int numero;

    private boolean principal;

    private Pessoa pessoa;

    //Constructor
    public EnderecoViewDto(long id, String logradouro, String cep, int numero,boolean Principal, Pessoa pessoa) {
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.principal = isPrincipal();
        this.pessoa = pessoa;
    }

    //Getters e setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
}
