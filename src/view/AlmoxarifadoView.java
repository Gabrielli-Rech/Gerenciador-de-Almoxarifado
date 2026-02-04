package view;

import java.util.Scanner;

public class AlmoxarifadoView {
    private final Scanner scanner = new Scanner(System.in);
    
    // Cores ANSI
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";

    public void exibirMensagem(String msg) {
        System.out.println(GREEN + "[INFO]: " + RESET + msg);
    }

    public void exibirErro(String msg) {
        System.out.println(RED + "[ERRO]: " + RESET + msg);
    }

    public void exibirAlerta(String nome, int qtd) {
        System.out.println(YELLOW + "⚠️  ALERTA: " + nome + " está com estoque baixo (" + qtd + ")!" + RESET);
    }

    public String lerInput(String rotulo) {
        System.out.print(rotulo + ": ");
        return scanner.nextLine();
    }

    public void exibirCabecalho() {
        System.out.println("\n========================================");
        System.out.println("      GERENCIADOR DE ALMOXARIFADO       ");
        System.out.println("========================================");
    }
}