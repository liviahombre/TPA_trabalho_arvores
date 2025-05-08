package lib;

import java.util.Comparator;

public class ArvoreAVL<T> extends AbstractArvore<T> {
    public ArvoreAVL(Comparator<T> comparador) {
        super(comparador);
    }

    @Override
    public void adicionar(T novoValor) {
        
        // throw new UnsupportedOperationException("Não há suporte para esse método ainda.");
        
        No<T> novoNo = new No<>(novoValor);

        if (raiz == null) {
            raiz = novoNo;
        } else {
            adicionarRecursivo(raiz, novoNo);
        }

        balancearArvore();

    }

    private void adicionarRecursivo(No<T> atual, No<T> novoNo) {
        if (comparador.compare(novoNo.getValor(), atual.getValor()) < 0) {
            if (atual.getEsquerda() == null) {
                atual.setEsquerda(novoNo);
                // novoNo.setPai(atual);
            } else {
                adicionarRecursivo(atual.getEsquerda(), novoNo);
            }
        } else {
            if (atual.getDireita() == null) {
                atual.setDireita(novoNo);
                // novoNo.setPai(atual);
            } else {
                adicionarRecursivo(atual.getDireita(), novoNo);
            }
        }
    }

    private void balancearArvore() {

        // Primeiro verifica o Fator de Balanceamento
        int fatorBalanceamento = calcularFatorBalanceamento(raiz);

        if (fatorBalanceamento < -1 || fatorBalanceamento > 1) {
            if (fatorBalanceamento == 2) {
                // Caso 1: Rotacao Esquerda
                if (calcularFatorBalanceamento(raiz.getDireita()) > 0) {
                    rotacaoEsquerda();
                    balancearArvore();
                }
                // Caso 3: Rotacao Esquerda Direita
                if (calcularFatorBalanceamento(raiz.getEsquerda()) < 0) {
                    // rotacaoEsquerdaDireita()
                }
                
            }
            else if (fatorBalanceamento == -2 && calcularFatorBalanceamento(raiz.getEsquerda()) < 0) {
                rotacaoDireita();
                balancearArvore();
            }
        } 
        return;
    }

    private int calcularFatorBalanceamento(No<T> raizSubarvore) {
        int alturaEsquerda = alturaSubarvore(raiz.getEsquerda());
        int alturaDireita = alturaSubarvore(raiz.getDireita());
        return alturaDireita - alturaEsquerda;
    }

    private int alturaSubarvore(No<T> raizSubarvore) {
        if (raiz == null) {
            return -1;
        }
        return alturaRecursiva(raiz);
    }

    private No<T> rotacaoEsquerda() {
        No<T> fihDireita = raiz.getDireita();
        raiz.setDireita(fihDireita.getEsquerda());
        fihDireita.setEsquerda(raiz);
        return fihDireita;
    }

    private No<T> rotacaoDireita() {
        No<T> fihEsquerda = raiz.getEsquerda();
        raiz.setEsquerda(fihEsquerda.getDireita());
        fihEsquerda.setDireita(raiz);
        return fihEsquerda;
    }

    // private No<T> rotacaoEsquerdaDireita() {
    //     raiz.setEsquerda(rotacaoEsquerda(raiz.getEsquerda()));
    //     return rotacaoDireita();
    // }

}
