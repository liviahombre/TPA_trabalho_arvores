package lib;

import java.util.Comparator;

public abstract class AbstractArvore<T> implements IArvoreBinaria<T> {

    protected No<T> raiz;
    protected Comparator<T> comparador;

    public AbstractArvore(Comparator<T> comparador) {
        this.raiz = null;
        this.comparador = comparador;
    }

    public No<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(No<T> raiz) {
        this.raiz = raiz;
    }

    public abstract void adicionar(No<T> novoNo);

    @Override
    public T pesquisar(T valor) {

        No<T> atual = raiz;

        while (atual != null) {
            int comparacao = comparador.compare(valor, atual.getValor());

            if (comparacao == 0) {
                return atual.getValor();
            } else if (comparacao < 0) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        return null;
    }

    @Override
    public T pesquisar(T valor, Comparator<T> newComparador) {
        No<T> atual = raiz;

        while (atual != null) {
            int comparacao = newComparador.compare(valor, atual.getValor());

            if (comparacao == 0) {
                return atual.getValor();
            } else if (comparacao < 0) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        return null;
    }

    @Override
    public int altura() {
        if (raiz == null) {
            return -1;
        }
        return Math.max(raiz.getEsquerda().getAltura(), raiz.getDireita().getAltura());

    }
}