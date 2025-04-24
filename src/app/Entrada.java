package app;

import java.io.IOException;
import java.util.Scanner;
import lib.Arvore;

public class Entrada {

    public void menu() {
        Scanner s = new Scanner(System.in);
        System.out.println("Bem-vindo ao Spotify falsificado!");
        System.out.println("1- Adicionar Música");
        System.out.println("3- Pesquisar Músicas");
        System.out.println("2- Remover Música");
        System.out.println("4- Imprimir Músicas em Ordem");
        System.out.println("5- Sair");

        System.out.println("Escolha uma opção:");
        int opcao = s.nextInt();
        s.nextLine(); 

        switch (opcao) {
            case 1:
                limpartela();
                PrimaryComparator comparator = new PrimaryComparator();
                Arvore<Musica> arvore = new Arvore<Musica>(comparator);
            
                System.out.println("---- Adicionar Música ----");
                System.out.println("Digite o nome da música:");
                String nomeMusica = s.nextLine();
                System.out.println("Digite o nome do autor:");
                String nomeArtista = s.nextLine();
                System.out.println("Digite a quantidade de visualizações:");
                int views = s.nextInt();
                System.out.println("Digite a duracao:");
                int duracao = s.nextInt();
                int index = arvore.quantidadeNos() + 1;

                Musica musica = new Musica(index, nomeMusica, nomeArtista, views, duracao);
                arvore.adicionar(musica);

                limpartela();

                System.out.println("Música adicionada com sucesso!\n");
                System.out.println("---- Detalhes da Música ----");
                System.out.println("Música: " + musica.getNome());
                System.out.println("Autor: " + musica.getAutor());
                System.out.println("Index: " + musica.getIndex());
                System.out.println("Visualizações: " + musica.getViews());
                break;

            case 2:
                System.out.println("Remover Música");
                // falta implementar
                break;
            case 3:
                System.out.println("Pesquisar Músicas");
                System.out.println("Escolha um método de comparação:");
            
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

        s.close();
        
    }

    public void limpartela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (InterruptedException | IOException e) {
            System.out.println("Erro ao limpar tela: " + e.getMessage());
        }
    }
}
