package app;

public class Musica {
    private int index;
    private String nome, autor;
    private int views;
    private int duracao;

    public Musica (int index, String nome, String autor, int views, int duracao) {
        
        this.index = index;
        this.nome = nome;
        this.autor = autor;
        this.views = views;
        this.duracao = duracao;

    }

    public int getIndex() {
        return index;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public int getViews() {
        return views;
    }

    public int getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return "Musica: " + getNome() + " - Autor: " + getAutor();
    }
}