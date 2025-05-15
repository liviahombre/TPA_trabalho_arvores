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
        No<T> elemRem = removerRecursivo(raiz, valor);
        balancearArvore();
        if (elemRem == null) return null;
        return elemRem.getValor();
    }

    private void balancearArvore() {

        // Primeiro verifica o Fator de Balanceamento
        int fatorBalanceamento = calcularFatorBalanceamento(raiz);

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
