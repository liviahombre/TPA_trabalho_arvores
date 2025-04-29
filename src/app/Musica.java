package app;

public class Musica {
    private Long index;
    private String nome, autor;
    private Long views;
    private Long duracao;

    public Musica (String nome, String autor, Long views, Long duracao) {
        this.index = System.nanoTime();
        this.nome = nome;
        this.autor = autor;
        this.views = views;
        this.duracao = duracao;

    }

    public Musica ()
    {
        this.index = null;
        this.nome = null;
        this.autor = null;
        this.views = null;
        this.duracao = null;
    }

    public Musica(Long index, String nome, String autor, Long views, Long duracao) {
        this.index = index;
        this.nome = nome;
        this.autor = autor;
        this.views = views;
        this.duracao = duracao;
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

    public Long getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return "Musica: " + getNome() + " - Autor: " + getAutor();
    }
}