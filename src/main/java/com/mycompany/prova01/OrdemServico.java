/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prova01;

/**
 *
 * @author 1458094
 */
public class OrdemServico {

    private int codigo;
    private String nomeCliente;
    private String modeloVeiculo;
    private String placa;
    private String data;
    private String status;
    private double valorEstimado;
    private Servico servico;
    private Box box;

    public OrdemServico(int codigo, String nomeCliente, String modeloVeiculo, String placa, String data, double valorEstimado, Servico servico) {
        this.codigo = codigo;
        this.nomeCliente = nomeCliente;
        this.modeloVeiculo = modeloVeiculo;
        this.placa = placa;
        this.data = data;
        this.valorEstimado = valorEstimado;
        this.servico = servico;
        this.status = "aberta";
        this.box = null;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getStatus() {
        return status;
    }

    public Servico getServico() {
        return servico;
    }

    public Box getBox() {
        return box;
    }

    public void atribuirBox(Box box) {
        this.box = box;
        this.status = "em execucao";
    }

    public void finalizar() {
        this.status = "finalizada";
    }

    public String toString() {
        String texto = "-----------------//-----------------\n";
        texto += "Codigo: " + codigo + "\n";
        texto += "Cliente: " + nomeCliente + "\n";
        texto += "Veiculo: " + modeloVeiculo + " | Placa: " + placa + "\n";
        texto += "Data: " + data + "\n";
        texto += "Status: " + status + "\n";
        texto += "Valor estimado: R$ " + valorEstimado + "\n";
        texto += "Servico: " + servico + "\n";
        if (box == null) {
            texto += "Box: inválido\n";
        } else {
            texto += "Box: " + box + "\n";
            if (box.getMec() != null) {
                texto += "Mecanico: " + box.getMec() + "\n";
            }
        }
        texto += "-----------------//-----------------";
        return texto;
    }
}
