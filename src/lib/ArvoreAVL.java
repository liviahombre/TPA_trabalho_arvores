package lib;

import java.util.Comparator;

public class ArvoreAVL<T> extends AbstractArvore<T> {
    public ArvoreAVL(Comparator<T> comparador) {
        super(comparador);
    }

    @Override
    public void adicionar(T novoValor) {
        
        throw new UnsupportedOperationException("Não há suporte para esse método ainda.");
        
        // No<T> novoNo = new No<>(novoValor);
        // if (raiz == null) {
        //     raiz = novoNo;
        // } else {
        //     TODO: Implementar a lógica de inserção AVL
        // }
    }
}
