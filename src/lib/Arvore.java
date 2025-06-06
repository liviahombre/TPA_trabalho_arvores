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
        this.ultimoRemovido = null; // Limpa antes de remover
        raiz = removerRecursivo(raiz, valor);
        return this.ultimoRemovido != null ? this.ultimoRemovido.getValor() : null;
    }
}