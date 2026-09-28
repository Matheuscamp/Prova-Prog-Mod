/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.prova01;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 1458094
 */
public class Prova01 {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Mecanico> mecs = new ArrayList<>();
    static ArrayList<Box> boxes = new ArrayList<>();
    static ArrayList<OrdemServico> ordens = new ArrayList<>();

    public static void main(String[] args) {
        criarDadosIniciais();

        int opcao;
        do {
            mostrarMenu();
            opcao = lerInt("Escolha uma opcao: ");
            if (opcao == 1) {
                cadastrarOrdem();
            } else if (opcao == 2) {
                associarMecanicoBox();
            } else if (opcao == 3) {
                atribuirOrdemBox();
            } else if (opcao == 4) {
                exibirOrdensDoBox();
            } else if (opcao == 5) {
                totalFinalizadasPorBox();
            } else if (opcao == 6) {
                buscarPorStatus();
            } else if (opcao == 7) {
                exibirDetalhesOrdem();
            } else if (opcao == 8) {
                finalizarOrdem();
            } else if (opcao == 0) {
                System.out.println("fechando sistema!");
            } else {
                System.out.println("Opção não disponivel!");
            }
        } while (opcao != 0);
    }

    public static void criarDadosIniciais() {
        mecs.add(new Mecanico("Padre Kelmon", "971.782.846-32", "Mec geral", "(31) 99758-4625"));
        mecs.add(new Mecanico("Zezin da Eletrica", "234.765.345-77", "Eletrica", "(31) 97412-3698"));
        mecs.add(new Mecanico("Fatima Bernardes", "987.654.321-00", "Funilaria", "(31) 99999-3333"));

        boxes.add(new Box(1, "Mecanica", 2, "Patio A"));
        boxes.add(new Box(2, "Eletrica", 3, "Patio B"));
        boxes.add(new Box(3, "Funilaria", 2, "Patio C"));
    }

    public static void mostrarMenu() {
        System.out.println();
        System.out.println("-*-*-* Mecanica Do Serjão -*-*-*");
        System.out.println("1 - cadastrar ordem servico");
        System.out.println("2 - associar mecanico ao box");
        System.out.println("3 - atribuir ordem servico ao box");
        System.out.println("4 - mostrar ordens de um box");
        System.out.println("5 - total de ordens finalizadas por box");
        System.out.println("6 - buscar ordens por status");
        System.out.println("7 - exibir detalhes de uma ordem");
        System.out.println("8 - finalizar ordem de servico");
        System.out.println("0 - finalizar sessão");
    }

    public static void cadastrarOrdem() {
        int codigo = lerInt("Codigo da ordem: ");

        if (buscarOrdem(codigo) != null) {
            System.out.println("Ja existe uma ordem com esse codigo!");
            return;
        }

        String cliente = lerTexto("Nome do cliente: ");
        String modelo = lerTexto("Modelo do veiculo: ");
        String placa = lerTexto("Placa do veiculo: ");
        String data = lerTexto("Data (dd/mm/aaaa): ");
        double valorEstimado = lerDouble("Valor estimado: ");

        System.out.println("Dados do servico:");
        String nomeServico = lerTexto("Nome do servico: ");
        int tempo = lerInt("Tempo estimado (minutos): ");
        double valorServico = lerDouble("Valor do servico: ");
        String categoria = lerTexto("Categoria (Mecanica, Eletrica ou Funilaria): ");

        Servico servico = new Servico(nomeServico, tempo, valorServico, categoria);
        OrdemServico ordem = new OrdemServico(codigo, cliente, modelo, placa, data, valorEstimado, servico);
        ordens.add(ordem);

        System.out.println("Ordem cadastrada com sucesso! Status: aberta");
    }

    public static void associarMecanicoBox() {
        System.out.println("Mecanicos:");
        for (int i = 0; i < mecs.size(); i++) {
            System.out.println((i + 1) + " - " + mecs.get(i));
        }
        int posicao = lerInt("Escolha o mecanico: ") - 1;

        if (posicao < 0 || posicao >= mecs.size()) {
            System.out.println("Mecanico invalido!");
            return;
        }
        Mecanico mecanico = mecs.get(posicao);

        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i).getMec() == mecanico) {
                System.out.println("Esse mecanico ja e responsavel pelo box " + boxes.get(i).getNumero());
                return;
            }
        }

        mostrarBoxes();
        int numero = lerInt("Numero do box: ");
        Box box = buscarBox(numero);

        if (box == null) {
            System.out.println("Box nao encontrado!");
            return;
        }

        box.setMec(mecanico);
        System.out.println("Mecanico " + mecanico.getNome() + " associado ao box " + box.getNumero());
    }

    public static void atribuirOrdemBox() {
        int codigo = lerInt("Codigo da ordem: ");
        OrdemServico ordem = buscarOrdem(codigo);

        if (ordem == null) {
            System.out.println("Ordem nao encontrada!");
            return;
        }
        if (!ordem.getStatus().equals("aberta")) {
            System.out.println("So e possivel atribuir ordens que estao abertas!");
            return;
        }

        mostrarBoxes();
        int numero = lerInt("Numero do box: ");
        Box box = buscarBox(numero);

        if (box == null) {
            System.out.println("Box nao encontrado!");
            return;
        }
        if (box.getMec() == null) {
            System.out.println("Esse box nao tem mecanico responsavel!");
            return;
        }
        if (!box.getTipoServico().equalsIgnoreCase(ordem.getServico().getCategoria())) {
            System.out.println("Esse box so aceita servicos do tipo " + box.getTipoServico());
            return;
        }
        if (contarEmExecucao(box) >= box.getCapacidadeMax()) {
            System.out.println("O box ja esta na capacidade maxima!");
            return;
        }

        ordem.atribuirBox(box);
        System.out.println("Ordem " + ordem.getCodigo() + " atribuida ao box " + box.getNumero());
    }

    public static void exibirOrdensDoBox() {
        int numero = lerInt("Numero do box: ");
        Box box = buscarBox(numero);

        if (box == null) {
            System.out.println("Box nao encontrado!");
            return;
        }

        int total = 0;
        for (int i = 0; i < ordens.size(); i++) {
            if (ordens.get(i).getBox() == box) {
                System.out.println(ordens.get(i));
                total++;
            }
        }
        System.out.println("Total de ordens do box " + numero + ": " + total);
    }

    public static void totalFinalizadasPorBox() {
        for (int i = 0; i < boxes.size(); i++) {
            Box box = boxes.get(i);
            int total = 0;

            for (int j = 0; j < ordens.size(); j++) {
                OrdemServico ordem = ordens.get(j);
                if (ordem.getBox() == box && ordem.getStatus().equals("finalizada")) {
                    total++;
                }
            }
            System.out.println("Box " + box.getNumero() + ": " + total + " ordens finalizadas");
        }
    }

    public static void buscarPorStatus() {
        System.out.println("1 - aberta");
        System.out.println("2 - em execucao");
        System.out.println("3 - finalizada");
        int opcao = lerInt("Escolha o status: ");

        String status;
        if (opcao == 1) {
            status = "aberta";
        } else if (opcao == 2) {
            status = "em execucao";
        } else if (opcao == 3) {
            status = "finalizada";
        } else {
            System.out.println("Opcao invalida!");
            return;
        }

        int total = 0;
        for (int i = 0; i < ordens.size(); i++) {
            if (ordens.get(i).getStatus().equals(status)) {
                System.out.println(ordens.get(i));
                total++;
            }
        }

        if (total == 0) {
            System.out.println("Nenhuma ordem encontrada com esse status.");
        }
    }

    public static void exibirDetalhesOrdem() {
        int codigo = lerInt("Codigo da ordem: ");
        OrdemServico ordem = buscarOrdem(codigo);

        if (ordem == null) {
            System.out.println("Ordem nao encontrada!");
        } else {
            System.out.println(ordem);
        }
    }

    public static void finalizarOrdem() {
        int codigo = lerInt("Codigo da ordem: ");
        OrdemServico ordem = buscarOrdem(codigo);

        if (ordem == null) {
            System.out.println("Ordem nao encontrada!");
            return;
        }
        if (!ordem.getStatus().equals("em execucao")) {
            System.out.println("So da para finalizar ordens que estao em execucao!");
            return;
        }

        ordem.finalizar();
        System.out.println("Ordem " + codigo + " finalizada!");
    }

    public static OrdemServico buscarOrdem(int codigo) {
        for (int i = 0; i < ordens.size(); i++) {
            if (ordens.get(i).getCodigo() == codigo) {
                return ordens.get(i);
            }
        }
        return null;
    }

    public static Box buscarBox(int numero) {
        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i).getNumero() == numero) {
                return boxes.get(i);
            }
        }
        return null;
    }

    public static int contarEmExecucao(Box box) {
        int total = 0;
        for (int i = 0; i < ordens.size(); i++) {
            if (ordens.get(i).getBox() == box && ordens.get(i).getStatus().equals("em execucao")) {
                total++;
            }
        }
        return total;
    }

    public static void mostrarBoxes() {
        System.out.println("Boxes:");
        for (int i = 0; i < boxes.size(); i++) {
            System.out.println(boxes.get(i));
        }
    }

    public static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine();
    }

    public static int lerInt(String mensagem) {
        System.out.print(mensagem);
        return Integer.parseInt(sc.nextLine());
    }

    public static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        return Double.parseDouble(sc.nextLine().replace(",", "."));
    }
}
