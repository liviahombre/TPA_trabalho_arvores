package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class GeradorDeMusicas {
    private static final int NUM_REGISTROS = 1000; // Você pode ajustar para 100000 se quiser
    private static final String NOME_ARQUIVO = "musicas.txt";

    private static final String[] NOMES_MUSICAS = {
        "Reflexo da Alma", "Som do Mar", "Dança da Lua", "Pôr do Sol", "Luz na Estrada", 
        "Tempestade de Emoções", "No Coração da Noite", "Harmonia do Universo", "Caminho das Estrelas", 
        "Ventos do Destino", "Ecos da Liberdade", "Tarde Dourada", "O Último Abraço", 
        "Fuga para a Realidade", "Sombras no Horizonte", "Canção da Esperança", "Ritmo do Coração", 
        "Estrela Solitária", "Canção dos Ventos", "O Que Restou de Nós",
        "Caminhos Cruzados", "Reflexões do Amanhã", "Amanhecer Radiante", "Noite de Inverno",
        "Cores do Outono", "Sussurros do Passado", "A Dança da Vida", "Entre Sombras e Luz",
        "Caminho da Esperança", "Ecos do Amanhã", "A Magia do Amor", "Noite de Verão"
    };

    private static final String[] ARTISTAS = {
        "Lucas Martins", "Lara Oliveira", "Roberta Souza", "Eduardo Costa", "Laura Oliveira", 
        "Marcos Silva", "Aline Pereira", "Gustavo Rodrigues", "Carolina Lima", "Thiago Santana", 
        "Mariana Costa", "João Almeida", "Fabiana Silva", "Rafael Gomes", "Denise Oliveira", 
        "Pedro Henrique", "Julia Santos", "Fernanda Carvalho", "Roberto Ferreira", "Ingrid Santana"
        , "Felipe Almeida", "Tatiane Lima", "André Costa", "Juliana Martins", "Ricardo Santos",
        "Camila Almeida", "Bruno Oliveira", "Patrícia Lima", "Vinícius Ferreira", "Ana Paula Santos",
        "Thiago Almeida", "Juliana Ferreira", "Gabriel Santos", "Fernanda Almeida", "Lucas Ferreira",
        "Mariana Almeida", "Rafael Santos", "Tatiane Ferreira", "André Almeida", "Juliana Costa",
        "Ricardo Ferreira", "Camila Santos", "Bruno Almeida", "Patrícia Ferreira", "Vinícius Santos"
    };

    public static void gerar() {
        gerarArquivo();
    }

    private static void gerarArquivo() {
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO, StandardCharsets.UTF_8))) {
            writer.write(NUM_REGISTROS + "\n");

            for (int i = 1; i <= NUM_REGISTROS; i++) {
                String nome = gerarNomeMusicaAleatorio(random);
                String artista = gerarArtistaAleatorio(random);
                int duracao = 60 + random.nextInt(300); 
                int visualizacoes = random.nextInt(500000); 

                writer.write(i + ";" + nome + ";" + artista + ";" + duracao + ";" + visualizacoes + "\n");

                if (i % 10000 == 0) {
                    System.out.println(i + " músicas geradas...");
                }
            }

            System.out.println("Arquivo gerado com sucesso: " + NOME_ARQUIVO);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    private static String gerarNomeMusicaAleatorio(Random random) {
        return NOMES_MUSICAS[random.nextInt(NOMES_MUSICAS.length)];
    }

    private static String gerarArtistaAleatorio(Random random) {
        return ARTISTAS[random.nextInt(ARTISTAS.length)];
    }
}
