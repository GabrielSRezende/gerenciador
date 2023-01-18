package com.attornatus.gerenciador.dto;

import java.time.LocalDate;
import java.util.Date;

public class PessoaFormDto {

    private long id;

    private String nome;

    private LocalDate dataNascimento;

    //Getters e setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
