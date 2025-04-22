package app;


public class Musica {
    private String nome;
    private String artista;
    private int duracao; // em segundos
    private int visualizacoes;  

    public Musica(String nome, String artista, int duracao, int visualizacoes) {
        this.nome = nome;
        this.artista = artista;
        this.duracao = duracao;
        this.visualizacoes = visualizacoes;
    }

    public String getNome() {
        return nome;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracao() {
        return duracao;
    }

    public int getVisualizacoes() {
        return visualizacoes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Musica musica = (Musica) obj;
        return duracao == musica.duracao && nome.equals(musica.nome) && artista.equals(musica.artista) && visualizacoes == musica.visualizacoes;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Artista: " + artista + ", Duração: " + duracao + " segundos" + ", Visualizações: " + visualizacoes;
    }
}
