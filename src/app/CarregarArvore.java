package app;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lib.AbstractArvore;

public class CarregarArvore {
    public static void carregarMusicasArquivo(AbstractArvore<Musica> arvore) {
        try (Scanner scanner = new Scanner(new File("musicas.txt"), StandardCharsets.UTF_8)) {
            int quantidade = Integer.parseInt(scanner.nextLine()); // Lê a primeira linha

            for (int i = 0; i < quantidade; i++) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(";");

                // Formato: posicao;index;nome;autor;views
                Long index = Long.parseLong(dados[1]);
                String nome = dados[2];
                String autor = dados[3];
                Long views = Long.parseLong(dados[4]);
                Long duracao = Long.parseLong(dados[5]);

                Musica musica = new Musica(index, nome, autor, views, duracao);
                arvore.adicionar(musica);
            }

            System.out.println("Músicas carregadas na árvore com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao carregar músicas: " + e.getMessage());
        }
    }
}
