package lib;

import java.util.Comparator;

public class ArvoreAVL<T> extends AbstractArvore<T> {
    public ArvoreAVL(Comparator<T> comparador) {
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

        balancearArvore();
    }

    @Override
    public T remover(T valor) {
        this.ultimoRemovido = null; // Limpa antes de remover
        raiz = removerRecursivo(raiz, valor);
        if (raiz != null) {
            balancearArvore();
        }
        return this.ultimoRemovido != null ? this.ultimoRemovido.getValor() : null;
    }

    private void balancearArvore() {

        // Primeiro verifica o Fator de Balanceamento
        int fatorBalanceamento = calcularFatorBalanceamento(raiz);

        if (fatorBalanceamento < -1 || fatorBalanceamento > 1) {
            if (fatorBalanceamento == 2) {
                // Caso 1: Rotacao Esquerda
                if (calcularFatorBalanceamento(raiz.getDireita()) > 0) {
                    raiz = rotacaoEsquerda(raiz);
                    balancearArvore();
                }
                // Caso 2: Rotacao Direita Esquerda
                else if (calcularFatorBalanceamento(raiz.getDireita()) < 0) {
                    raiz = rotacaoDireitaEsquerda(raiz);
                    balancearArvore();
                }
                
            }
            else if (fatorBalanceamento == -2) {
                // Caso 3: Rotacao Direita
                if (calcularFatorBalanceamento(raiz.getEsquerda()) < 0) {
                    raiz = rotacaoDireita(raiz);
                    balancearArvore();
                }
                // Caso 4: Rotacao Esquerda Direita
                else if (calcularFatorBalanceamento(raiz.getEsquerda()) > 0) {
                    raiz = rotacaoEsquerdaDireita(raiz);
                    balancearArvore();
                }
            }
        } 
    }

    private int calcularFatorBalanceamento(No<T> raizSubarvore) {
        int alturaEsquerda = alturaSubarvore(raizSubarvore.getEsquerda());
        int alturaDireita = alturaSubarvore(raizSubarvore.getDireita());
        return alturaDireita - alturaEsquerda;
    }

    private int alturaSubarvore(No<T> raizSubarvore) {
        return alturaRecursiva(raizSubarvore);
    }

    private No<T> rotacaoEsquerda(No<T> r) {
        No<T> fihDireita = r.getDireita();
        r.setDireita(fihDireita.getEsquerda());
        fihDireita.setEsquerda(r);
        return fihDireita;
    }

    private No<T> rotacaoDireita(No<T> r) {
        No<T> fihEsquerda = r.getEsquerda();
        r.setEsquerda(fihEsquerda.getDireita());
        fihEsquerda.setDireita(r);
        return fihEsquerda;
    }

    private No<T> rotacaoEsquerdaDireita(No<T> r) {
        r.setEsquerda(rotacaoEsquerda(r.getEsquerda()));
        return rotacaoDireita(r);
    }

    private No<T> rotacaoDireitaEsquerda(No<T> r) {
        r.setDireita(rotacaoDireita(r.getDireita()));
        return rotacaoEsquerda(r);
    }

}
