package lib;

import java.util.Comparator;

public abstract class AbstractArvore<T> implements IArvoreBinaria<T> {

    protected No<T> raiz;
    protected Comparator<T> comparador;

    protected AbstractArvore(Comparator<T> comparador) {
        this.raiz = null;
        this.comparador = comparador;
    }

    public No<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(No<T> raiz) {
        this.raiz = raiz;
    }

    public abstract void adicionar(T novoValor);

    @Override
    public T pesquisar(T valor) {
        return pesquisarRecursivo(raiz, valor);
    } 

    @Override
    public T pesquisar(T valor, Comparator<T> novoComparador) {
        return pesquisarRecursivo(raiz, valor, novoComparador);
    }

    protected T pesquisarRecursivo(No<T> no, T valor) {
        if (no == null) {
            return null;
        }

        int comparacao = comparador.compare(valor, no.getValor());

        if (comparacao == 0) {
            return no.getValor();
        } else if (comparacao < 0) {
            return pesquisarRecursivo(no.getEsquerda(), valor);
        } else {
            return pesquisarRecursivo(no.getDireita(), valor);
        }
    }

    protected T pesquisarRecursivo(No<T> no, T valor, Comparator<T> novoComparador) {
        if (no == null) {
            return null;
        }

        if (novoComparador.compare(valor, no.getValor()) == 0) return no.getValor();

        T vEsq = pesquisarRecursivo(no.getEsquerda(), valor, novoComparador);
        if (vEsq != null && novoComparador.compare(valor, vEsq) == 0) return vEsq;
        
        T vDir = pesquisarRecursivo(no.getDireita(), valor, novoComparador);
        
        return vDir;
    }  

    @Override
    public T remover(T valor) {
        No<T> elemRem = removerRecursivo(raiz, valor);
        if (elemRem == null) return null;
        return elemRem.getValor();
    }

    /**
     * Método que remove um nó da árvore a partir do nó passado como parâmetro.
     * @param no - Raiz da Árvore/Sub-Árvore.
     * @param valor - Valor a ser removido.
     * @return O nó removido.
    */
    protected No<T> removerRecursivo(No<T> no, T valor) {
        if (no == null) {
            return null;
        }
        
        int comparacao = comparador.compare(valor, no.getValor());

        if (comparacao == 0) {
            No<T> noRemovido = new No<T>(no.getValor());

            // Caso 1: Nó sem filhos (folha)
            // Remover o nó
            if (no.getEsquerda() == null && no.getDireita() == null) {
                no = null;
                return noRemovido;
            }

            // Caso 3: Nó com 2 filhos
            // Maior valor da sub árvore esquerda assume o lugar do nó removido
            // Remover o maior nó da sub árvore esquerda (Caso 1 ou Caso 2, certamente)
            // Verificado antes para facilitar a verificação do caso 2

            else if (no.getEsquerda() != null && no.getDireita() != null) {
                No<T> maior = encontrarMaiorNo(no.getEsquerda());
                
                removerRecursivo(no.getEsquerda(), maior.getValor());

                no.setValor(maior.getValor());

                return noRemovido;
            }

            // Caso 2: Nó com um filho
            // Filho assume o lugar do nó removido

            // Filho na esquerda
            else if (no.getEsquerda() != null) {
                no.setValor(no.getEsquerda().getValor());

                no.setEsquerda(no.getEsquerda().getEsquerda()); //Copia a referencia da esquerda do filho a esquerda
                no.setDireita(no.getEsquerda().getDireita()); //Copia a referencia da direita do filho a esquerda
                
                return noRemovido;
            }

            // Filho na direita (ultimo caso possivel)
            else {
                no.setValor(no.getDireita().getValor());

                no.setEsquerda(no.getDireita().getEsquerda()); //Copia a referencia da esquerda do filho a direita
                no.setDireita(no.getDireita().getDireita()); // Copia a referencia da direita do filho a direita

                return noRemovido;
            }
        } else if (comparacao < 0) {
            return removerRecursivo(no.getEsquerda(), valor);

        } else {
            return removerRecursivo(no.getDireita(), valor);
        }
    }

    /**
     * Método que encontra o maior nó de uma árvore, dado o nó raiz dessa árvore (ou subarvore).
     * @param no
     * @return Maior nó da árvore
     */
    protected No<T> encontrarMaiorNo(No<T> no) {
        if (no.getDireita() == null) {
            return no;
        } else {
            return encontrarMaiorNo(no.getDireita());
        }
    }

    /**
     * Método que encontra o menor nó de uma árvore, dado o nó raiz dessa árvore (ou subarvore).
     * @param no
     * @return Menor nó da árvore
     */
    protected No<T> encontrarMenorNo(No<T> no) {
        if (no.getEsquerda() == null) {
            return no;
        } else {
            return encontrarMenorNo(no.getEsquerda());
        }
    }

    // /**
    //  * Método que remove o maior nó da árvore a partir do nó passado como parâmetro.
    //  * @see #removerRecursivo(No, Object)
    //  * @param no
    //  * @return O maior nó removido.
    //  */
    // protected No<T> removerMaiorNo(No<T> no) {
    //     if (no.getDireita() == null) {
    //         No<T> aux = no;
    //         no = null;
    //         return aux;
    //     } else {
    //         return removerMaiorNo(no.getDireita());
    //     }
    // }

    // /**
    //  * Método que remove o menor nó da árvore a partir do nó passado como parâmetro.
    //  * @see #removerRecursivo(No, Object)
    //  * @param no
    //  * @return O menor nó removido.
    //  */
    // protected No<T> removerMenorNo(No<T> no) {
    //     if (no.getEsquerda() == null) {
    //         No<T> aux = no;
    //         no = null;
    //         return aux;
    //     } else {
    //         return removerMenorNo(no.getEsquerda());
    //     }
    // }

    @Override
    public int altura() {
        if (raiz == null) {
            return -1;
        }
        return alturaRecursiva(raiz) - 1;
    }

    /**
     * Método que retorna a altura da árvore a partir do nó passado como parâmetro.
     * @param no - Nó a partir do qual será calculada a altura da árvore.
     * @return A altura da árvore.
     */
    protected int alturaRecursiva(No<T> no) {
        int alturaEsquerda = no.getEsquerda() != null ? alturaRecursiva(no.getEsquerda()) : 1;
        int alturaDireita = no.getDireita() != null ? alturaRecursiva(no.getDireita()) : 1;
        return Math.max(alturaEsquerda, alturaDireita);
    }

    @Override
    public int quantidadeNos() {
        return quantidadeNosRecursivo(raiz);
    }

    /**
     * Método que retorna a quantidade de nós da árvore a partir do nó passado como parâmetro.
     * @param no - Nó a partir do qual será calculada a quantidade de nós.
     * @return A quantidade de nós da árvore.
     */
    protected int quantidadeNosRecursivo(No<T> no) {
        if (no == null) {
            return 0;
        }
        return 1 + quantidadeNosRecursivo(no.getEsquerda()) + quantidadeNosRecursivo(no.getDireita());
    }

    @Override
    public String caminharEmNivel() {
        StringBuilder resultado = new StringBuilder("[");
        int altura = altura();
        for (int i = 0; i <= altura; i++) {
            caminharEmNivelRecursivo(raiz, i, resultado);
        }
        resultado.append("]");
        return resultado.toString();
    }

    /**
     * Método que percorre a árvore em nível a partir do nó passado como parâmetro.
     * @param no - Nó a partir do qual será feito o caminhamento em nível.
     * @param nivel - Nível atual da árvore.
     * @param resultado - StringBuilder que armazenará o resultado do caminhamento em nível.
     */
    protected void caminharEmNivelRecursivo(No<T> no, int nivel, StringBuilder resultado) {
        if (no == null) return;
        if (nivel == 0) {
            resultado.append(no.getValor()).append(" \n ");
        } else {
            caminharEmNivelRecursivo(no.getEsquerda(), nivel - 1, resultado);
            caminharEmNivelRecursivo(no.getDireita(), nivel - 1, resultado);
        }
    }

    @Override
    public String caminharEmOrdem() {
        StringBuilder resultado = new StringBuilder("[");
        caminharEmOrdemRecursivo(raiz, resultado);
        resultado.append("]");
        return resultado.toString();
    }

    /**
     * Método que percorre a árvore em ordem a partir do nó passado como parâmetro.
     * @param no - Nó a partir do qual será feito o caminhamento em ordem.
     * @param resultado - StringBuilder que armazenará o resultado do caminhamento em ordem.
     */
    protected void caminharEmOrdemRecursivo(No<T> no, StringBuilder resultado) {
        if (no == null) return;
        caminharEmOrdemRecursivo(no.getEsquerda(), resultado);
        resultado.append(no.getValor()).append(" \n ");
        caminharEmOrdemRecursivo(no.getDireita(), resultado);
    }

    @Override
    public String toString() {
        return caminharEmNivel();
    }

}