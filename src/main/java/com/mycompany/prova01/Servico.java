/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prova01;

/**
 *
 * @author 1458094
 */
public class Servico {

    private String nome;
    private int tempo;
    private double valor;
    private String categoria;

    public Servico(String nome, int tempo, double valor, String categoria) {
        this.nome = nome;
        this.tempo = tempo;
        this.valor = valor;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public int getTempo() {
        return tempo;
    }

    public double getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public String toString() {
        return nome + " | Categoria: " + categoria + " | Tempo estimado: " + tempo + " min | Valor: R$ " + valor;
    }
}
