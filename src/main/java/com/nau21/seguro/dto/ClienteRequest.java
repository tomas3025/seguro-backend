package com.nau21.seguro.dto;

public class ClienteRequest {

    private String nome;
    private int idade;

    public ClienteRequest() {
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}