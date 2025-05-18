package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class GeradorDeMusicas {
    private static final int NUM_REGISTROS = 1000; // Você pode ajustar para 100000 se quiser
    private static final String NOME_ARQUIVO = "musicas.txt";

    static String[] substantivos = {
        "Noite", "Luz", "Sombra", "Caminho", "Destino", "Fogo", "Chuva", "Sonho", "Alma", "Vento",
        "Céu", "Flor", "Raio", "Silêncio", "Ecos", "Mar", "Estrela", "Sol", "Terra", "Grito",
        "Memória", "Chama", "Som", "Tempo", "Horizonte", "Brisa", "Folha", "Verdade", "Segredo", "Olhar",
        "Voz", "Areia", "Fronteira", "Espelho", "Abismo", "Muralha", "Relâmpago", "Corrente", "Miragem", "Coração"
    };

    static String[] adjetivos = {
        "Perdido", "Infinito", "Partido", "Negro", "Doce", "Cruel", "Sagrado", "Vermelho", "Vazio",
        "Solitário", "Velho", "Frio", "Calmo", "Profundo", "Brilhante", "Sombrio", "Rápido", "Silencioso",
        "Distante", "Somente", "Mortal", "Vivo", "Cego", "Lento", "Amargo", "Encantado", "Azul", "Mágico", "Etéreo", "Instável"
    };


    static String[] prefixosArtista = {"DJ", "MC", "Grupo", "Os", "Projeto", "Coletivo", "Orquestra", "Tribo", "Banda", "Senhor"};
    static String[] nomesArtista = {
        "Cósmico", "Fantasma", "Acústico", "Digital", "Solar", "Psicodélico", "Ruidoso", "Místico", "Invisível", "Vibrante",
        "Escuro", "Urbano", "Atômico", "Sônico", "Estelar", "Bravio", "Azul", "Sublime", "Íntimo", "Noturno"
    };

    public static void gerarOrdenado() {
        gerarArquivoOrdenado();
    }

    private static void gerarArquivoOrdenado() {
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO, StandardCharsets.UTF_8))) {
            writer.write(NUM_REGISTROS + "\n");

            for (int i = 1; i <= NUM_REGISTROS; i++) {
                Long index = System.nanoTime(); // Simulando um índice único
                String nome = gerarNomeMusicaAleatorio(random);
                String artista = gerarArtistaAleatorio(random);
                int duracao = 60 + random.nextInt(300); 
                int visualizacoes = random.nextInt(500000); 

                writer.write(i + ";"+ index + ";" + nome + ";" + artista + ";" + visualizacoes + ";" + duracao + ";" + "\n");

                if (i % 10000 == 0) {
                    System.out.println(i + " músicas geradas...");
                }
            }

            System.out.println("Arquivo gerado com sucesso: " + NOME_ARQUIVO);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public static void gerarBalanceado() {
        gerarArquivoBalanceado();
    }

    private static void gerarArquivoBalanceado() {
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO, StandardCharsets.UTF_8))) {
            writer.write(NUM_REGISTROS + "\n");

            for (int i = 1; i <= NUM_REGISTROS; i++) {
                String nome = gerarNomeMusicaAleatorio(random);
                String artista = gerarArtistaAleatorio(random);
                int duracao = 60 + random.nextInt(540); 
                int visualizacoes = random.nextInt(5000000); 
                int index = obterIndiceBalanceado(NUM_REGISTROS, i);

                writer.write(i +  ";" + index + ";" + nome + ";" + artista + ";" + duracao + ";" + visualizacoes + "\n");

                if (i % 10000 == 0) {
                    System.out.println(i + " músicas geradas...");
                }
            }

            System.out.println("Arquivo gerado com sucesso: " + NOME_ARQUIVO);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    private static int encontrarIndiceBalanceado(int inicio, int fim, int[] k) {
        if (inicio > fim) return -1;

        int meio = (inicio + fim) / 2;

        if (k[0] == 0) return meio;

        k[0]--;
        int esquerda = encontrarIndiceBalanceado(inicio, meio - 1, k);
        if (esquerda != -1) return esquerda;

        return encontrarIndiceBalanceado(meio + 1, fim, k);
    }

    private static int obterIndiceBalanceado(int n, int k) {
        int[] contador = new int[]{k};  // usar array para mutabilidade
        return encontrarIndiceBalanceado(0, n - 1, contador);
    }



    private static String gerarNomeMusicaAleatorio(Random random) {
        String s1 = getAleatorio(substantivos, random);
        String s2 = getAleatorio(adjetivos, random);

        int tipo = random.nextInt(6);
        switch (tipo) {
            case 0: return s1 + " " + s2; 
            case 1: return "Canção da " + s1;
            case 2: return "Entre " + s1 + " e " + s2;
            case 3: return "No " + s1 + " do " + s2; 
            case 4: return "Sob o " + s2 + " " + s1; 
            case 5: return s1 + " do " + s2;
            default: return s1 + " " + s2;
        }
    }

    private static String gerarArtistaAleatorio(Random random) {
        String prefixo = getAleatorio(prefixosArtista, random);
        String nome = getAleatorio(nomesArtista, random);
        String extra = getAleatorio(substantivos, random);

        int tipo = random.nextInt(5);
        switch (tipo) {
            case 0: return prefixo + " " + nome;
            case 1: return nome + " " + extra;
            case 2: return "Os " + nome + "s";
            case 3: return "Coletivo " + nome;
            case 4: return "DJ " + nome + " do " + extra;
            default: return prefixo + " " + nome;
        }
    }

    private static String getAleatorio(String[] array, Random random) {
        return array[random.nextInt(array.length)];
    }

    
}
