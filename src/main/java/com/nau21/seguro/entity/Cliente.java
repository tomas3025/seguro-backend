package com.nau21.seguro.entity;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int idade;
    private String risco;

    public Cliente() {
        // obrigatório para JPA
    }

    public Cliente(String nome, int idade, String risco) {
        this.nome = nome;
        this.idade = idade;
        this.risco = risco;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getRisco() { return risco; }

    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setRisco(String risco) { this.risco = risco; }
}