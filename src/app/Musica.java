package app;

public class Musica {
    private Long index;
    private String nome, autor;
    private Long views;

    public Musica (String nome, String autor, Long views) {
        
        this.index = System.nanoTime();
        this.nome = nome;
        this.autor = autor;
        this.views = views;

    }

    public Musica ()
    {
        this.index = null;
        this.nome = null;
        this.autor = null;
        this.views = null;
    }

    public Musica(Long index, String nome, String autor, Long views) {
        this.index = index;
        this.nome = nome;
        this.autor = autor;
        this.views = views;
    }

    public Long getIndex() {
        return index;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public Long getViews() {
        return views;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Musica: " + getNome() + " - Autor: " + getAutor();
    }
}