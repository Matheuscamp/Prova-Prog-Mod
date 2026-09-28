/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prova01;

/**
 *
 * @author 1458094
 */
public class Mecanico {

    private String nome;
    private String cpf;
    private String especialidade;
    private String telefone;

    public Mecanico(String nome, String cpf, String especialidade, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.especialidade = especialidade;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String toString() {
        return nome + " | CPF: " + cpf + " | Especialidade: " + especialidade + " | Telefone: " + telefone;
    }
}
