package app;

import java.io.IOException;
import java.util.Scanner;
import lib.Arvore;

public class Entrada {
    Arvore<Musica> arvore = new Arvore<>(new PrimaryComparator()); 

    public void menu() {
        Scanner s = new Scanner(System.in);

        limpartela();
        System.out.println("Bem-vindo ao Spotify falsificado!");
        System.out.println("1- Adicionar Música");
        System.out.println("2- Pesquisar Músicas");
        System.out.println("3- Remover Música");
        System.out.println("4- Listar Músicas");
        System.out.println("5- Sair");

        System.out.println("Escolha uma opção:");
        int opcao = s.nextInt();
        s.nextLine(); 

        switch (opcao) {
            case 1:
                limpartela();
            
                System.out.println("---- Adicionar Música ----");
                System.out.println("Digite o nome da música:");
                String nomeMusica = s.nextLine();
                System.out.println("Digite o nome do autor:");
                String nomeArtista = s.nextLine();
                System.out.println("Digite a quantidade de visualizações:");
                Long views = s.nextLong();
                s.nextLine(); // Limpar o buffer

                Musica musica = new Musica(nomeMusica, nomeArtista, views);
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
            System.out.println("----- Pesquisar Músicas -----");
            System.out.println("Escolha um método de comparação:");
            System.out.println("1- Comparação padrão (por index)");
            System.out.println("2- Comparação aprimorada (por nome e autor)");
            System.out.println("3- Sair");
            int tipoComparacao = s.nextInt();
            s.nextLine(); 
        
            switch (tipoComparacao) {
                case 1:
                    System.out.println("Digite o index da música:");
                    Long indexParaBusca = s.nextLong();
                    s.nextLine();

                    Musica musicaParaBusca = new Musica();
                    musicaParaBusca.setIndex(indexParaBusca);
                    Musica resultado = arvore.pesquisar(musicaParaBusca);

                    if (resultado != null) {
                        System.out.println("Música encontrada:");
                        System.out.println("Música: " + resultado.getNome());
                        System.out.println("Autor: " + resultado.getAutor());
                        System.out.println("Index: " + resultado.getIndex());
                        System.out.println("Visualizações: " + resultado.getViews());
                    } 
                    
                    else {
                        System.out.println("Música não encontrada.");
                    }
                    break;

                case 2:
                    System.out.println("Digite o nome da música:");
                    String nomeParaBusca = s.nextLine();
                    System.out.println("Digite o nome do autor:");
                    String autorParaBusca = s.nextLine();
                    
                    Musica musicaParaBusca2 = new Musica(nomeParaBusca, autorParaBusca, null);
                    
                    Musica resultado2 = arvore.pesquisar(musicaParaBusca2, new SecondaryComparator());

                    if (resultado2 != null) {
                        System.out.println("Música encontrada:");
                        System.out.println("Música: " + resultado2.getNome());
                        System.out.println("Autor: " + resultado2.getAutor());
                        System.out.println("Index: " + resultado2.getIndex());
                        System.out.println("Visualizações: " + resultado2.getViews());
                    } 
                    
                    else {
                        System.out.println("Música não encontrada.");
                    }
                    break;

                case 3:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
            break;

            case 3:
                System.out.println("----- Remover Música (por Index) -----");
                System.out.println("Digite o index da música:");
                Long indexParaRemocao = s.nextLong();
                s.nextLine();

                Musica musicaParaRemocao = new Musica();
                musicaParaRemocao.setIndex(indexParaRemocao);

                Musica resultadoRemocao = arvore.remover(musicaParaRemocao);

                if (resultadoRemocao != null) {
                    System.out.println("Música removida com sucesso:");
                    System.out.println("Música: " + resultadoRemocao.getNome());
                    System.out.println("Autor: " + resultadoRemocao.getAutor());
                    System.out.println("Index: " + resultadoRemocao.getIndex());
                    System.out.println("Visualizações: " + resultadoRemocao.getViews());
                } else {
                    System.out.println("Música não encontrada.");
                }
                break;

            case 4:
                System.out.println("Listar Músicas:");
                System.out.println("---- Sua biblioteca de músicas ----");
                System.out.println(arvore.caminharEmOrdem());
                System.out.println("---- Fim da lista ----");
                System.out.println("Total de músicas: " + arvore.quantidadeNos());
                break;

            case 5:
                System.out.println("Saindo...");
                s.close();
                break;
            
                default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }

        System.out.println("Deseja voltar ao menu? (S/N)");
        String resposta = s.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            limpartela();
            menu(); 
        } 
        else {
            System.out.println("Programa finalizado. Até mais!");
            s.close();
        }
    }

    public void inicializar() {
        GeradorDeMusicas.gerar(); 
        CarregarArvore.carregarMusicasArquivo(arvore);
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
