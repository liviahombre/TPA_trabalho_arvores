package lib;

import java.util.Comparator;

public class Arvore<T> extends AbstractArvore<T> {
    
    public Arvore(Comparator<T> comparador) {
        super(comparador);
    } 

    @Override
    public void adicionar(T novoValor) {

        No<T> novoNo = new No<>(novoValor);

        if (raiz == null) {
            raiz = novoNo;
        } else {
            adicionarRecursivo(raiz, novoNo);
        }
    }

    private void adicionarRecursivo(No<T> atual, No<T> novoNo) {
        if (comparador.compare(novoNo.getValor(), atual.getValor()) < 0) {
            if (atual.getEsquerda() == null) {
                atual.setEsquerda(novoNo);
                novoNo.setPai(atual);
            } else {
                adicionarRecursivo(atual.getEsquerda(), novoNo);
            }
        } else {
            if (atual.getDireita() == null) {
                atual.setDireita(novoNo);
                novoNo.setPai(atual);
            } else {
                adicionarRecursivo(atual.getDireita(), novoNo);
            }
        }
    }

}