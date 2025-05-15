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

    @Override
    public T remover(T valor) {
        No<T> elemRem = removerRecursivo(raiz, valor);
        if (elemRem == null) return null;
        return elemRem.getValor();
    }
}