package lib;

public class No<T> {
    
    private T valor;
    private No<T> esquerda;
    private No<T> direita;
    // private No<T> pai;

    public No(T valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        // this.pai = null;
    }

    public T getValor() {
        return valor;
    }

    protected void setValor(T valor) {
        this.valor = valor;
    }

    public No<T> getEsquerda() {
        return esquerda;
    }

    public No<T> getDireita() {
        return direita;
    }

    // public No<T> getPai() {
    //     return pai;
    // }

    public void setEsquerda(No<T> esquerda) {
        this.esquerda = esquerda;
    }

    public void setDireita(No<T> direita) {
        this.direita = direita;
    }

    // public void setPai(No<T> pai) {
    //     this.pai = pai;
    // }

    /*
     *          __ 20 __
     *        /         \
     *       6          o
     *     /   \       /   \
     *    5     15    o     o
     *   / \   / \   / \   / \
     *  1  6  12  16 o   o o   o
     *    /
     *   5.5
     * 
     * 
     *   
     * Homemzinho em ASCII (armado):
     *        O
     *   ...¬/|\
     *       / \
     *
     * Homemzinho em ACII (cavaleiro):
     *        O
     *      [/|\->~~~
     *       / \ 
     * 
     * Homemzinho em ASCII (metade)
     *        o
     *        |\
     *         \
     * 
     * Homemzinho em ASCII (minecraft)
     *       o
     *     ⇱/|\
     *      / \
     * 
     * Homemzinho em ASCII (Rei)
     * 
     *       ⏕
     *       o
     *      v|v
     *      / \
     */

}
