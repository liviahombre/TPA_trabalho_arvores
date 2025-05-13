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

        // System.out.println("Adicionando...");
        if (raiz == null) {
            raiz = novoNo;
        } else {
            adicionarRecursivo(raiz, novoNo);
        }

        // System.out.println("Balanceando...");
        balancearArvore();
        // System.out.println("Balanceado");

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
        // System.out.println("FB: " + fatorBalanceamento);

        if (fatorBalanceamento < -1 || fatorBalanceamento > 1) {
            if (fatorBalanceamento == 2) {
                // Caso 1: Rotacao Esquerda
                if (calcularFatorBalanceamento(raiz.getDireita()) > 0) {
                    raiz = rotacaoEsquerda(raiz);
                    System.out.println("Caso 1");
                    balancearArvore();
                }
                // Caso 2: Rotacao Direita Esquerda
                else if (calcularFatorBalanceamento(raiz.getDireita()) < 0) {
                    raiz = rotacaoDireitaEsquerda(raiz);
                    System.out.println("Caso 2");
                    balancearArvore();
                }
                
            }
            else if (fatorBalanceamento == -2) {
                // Caso 3: Rotacao Direita
                if (calcularFatorBalanceamento(raiz.getEsquerda()) < 0) {
                    raiz = rotacaoDireita(raiz);
                    System.out.println("Caso 3");
                    balancearArvore();
                }
                // Caso 4: Rotacao Esquerda Direita
                else if (calcularFatorBalanceamento(raiz.getEsquerda()) > 0) {
                    raiz = rotacaoEsquerdaDireita(raiz);
                    System.out.println("Caso 4");
                    balancearArvore();
                }
            }
        } 
    }

    private int calcularFatorBalanceamento(No<T> raizSubarvore) {
        int alturaEsquerda = alturaSubarvore(raiz.getEsquerda());
        int alturaDireita = alturaSubarvore(raiz.getDireita());
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

    // r = 1
    // fihD = 4
    // r->direita = fihD->esquerda = null
    // fihD->esquerda = 1
    // 

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
