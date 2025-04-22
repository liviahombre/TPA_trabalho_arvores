package lib;

public class No<T> {
    
    private T valor;
    private No<T> esquerda;
    private No<T> direita;

    public No(T valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }

    public T getValor() {
        return valor;
    }

    public No<T> getEsquerda() {
        return esquerda;
    }

    public No<T> getDireita() {
        return direita;
    }
}
