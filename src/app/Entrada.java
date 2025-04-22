package app;

import java.util.Scanner;

public class Entrada {

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Spotify falsificado!");
        System.out.println("1- Adicionar Música");
        System.out.println("3- Pesquisar Músicas");
        System.out.println("2- Remover Música");
        System.out.println("4- Ordenar Músicas");
        System.out.println("5- Sair");

        System.out.println("Escolha uma opção:");
        int opcao = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcao) {
            case 1:
                System.out.println("Adicionar Música");
                // falta implementar
                break;

            case 2:
                System.out.println("Remover Música");
                // falta implementar
                break;
            case 3:
                System.out.println("Pesquisar Músicas");
                //Falta implementar
                break;
            case 4:
                System.out.println("Ordenar Músicas");
                // Falta implementar
                break;
            case 5:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
        if (opcao != 5) {
            menu(); 
        }

        scanner.close();
        
    }
}
