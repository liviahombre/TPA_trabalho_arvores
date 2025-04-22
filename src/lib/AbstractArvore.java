package lib;

import java.util.Comparator;

public abstract class AbstractArvore implements IArvoreBinaria {

    protected No<T> raiz;
    protected Comparator<T> comparador;

    public AbstractArvore() {
        this.raiz = null;
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

}