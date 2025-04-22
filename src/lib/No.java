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

    public void setEsquerda(No<T> esquerda) {
        this.esquerda = esquerda;
    }

    public void setDireita(No<T> direita) {
        this.direita = direita;
    }

    public int getAltura() {
        int alturaEsquerda = (esquerda != null) ? esquerda.getAltura() : -1;
        int alturaDireita = (direita != null) ? direita.getAltura() : -1;
        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    /*
     *        o
     *       / \
     *      o   o
     *     / \  
     *    o   o
     * 
     * true: get esquerda |  
     * 
     *         O
         ...¬_/|\
    *         / \
     *
     */

}
