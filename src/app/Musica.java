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

<<<<<<< HEAD
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
=======
    public int getIndex() {
>>>>>>> 037930bfb23f39554777c0242fea6827500061de
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

<<<<<<< HEAD
    public void setIndex(Long index) {
        this.index = index;
=======
    public int getDuracao() {
        return duracao;
>>>>>>> 037930bfb23f39554777c0242fea6827500061de
    }

    @Override
    public String toString() {
        return "Musica: " + getNome() + " - Autor: " + getAutor();
    }
}