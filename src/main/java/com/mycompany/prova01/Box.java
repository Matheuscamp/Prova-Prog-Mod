/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prova01;

/**
 *
 * @author 1458094
 */
public class Box {

    private int numero;
    private String tipoServico;
    private int capacidadeMax;
    private String loc;
    private Mecanico mec;

    public Box(int numero, String tipoServico, int capacidadeMax, String loc) {
        this.numero = numero;
        this.tipoServico = tipoServico;
        this.capacidadeMax = capacidadeMax;
        this.loc = loc;
        this.mec = null;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public int getCapacidadeMax() {
        return capacidadeMax;
    }

    public String getLocalizacao() {
        return loc;
    }

    public Mecanico getMec() {
        return mec;
    }

    public void setMec(Mecanico mec) {
        this.mec = mec;
    }

    public String toString() {
        String nomeMecanico = "sem mecanico disponivel";
        if (mec != null) {
            nomeMecanico = mec.getNome();
        }
        return "Box " + numero + " | Tipo: " + tipoServico + " | Capacidade: " + capacidadeMax + " | Local: " + loc + " | Mecanico: " + nomeMecanico;
    }
}
