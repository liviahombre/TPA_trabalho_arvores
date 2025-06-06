package lib;

import java.util.Comparator;

public abstract class AbstractArvore<T> implements IArvoreBinaria<T> {

    protected No<T> raiz;
    protected No<T> ultimoRemovido = null;
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

    protected void adicionarRecursivo(No<T> atual, No<T> novoNo) {
        if (comparador.compare(novoNo.getValor(), atual.getValor()) < 0) {
            if (atual.getEsquerda() == null) {
                atual.setEsquerda(novoNo);
            } else {
                adicionarRecursivo(atual.getEsquerda(), novoNo);
            }
        } else {
            if (atual.getDireita() == null) {
                atual.setDireita(novoNo);
            } else {
                adicionarRecursivo(atual.getDireita(), novoNo);
            }
        }
    }
    
    @Override
    public T remover(T valor) {
        this.ultimoRemovido = null; // Limpa antes de remover
        raiz = removerRecursivo(raiz, valor);
        return this.ultimoRemovido != null ? this.ultimoRemovido.getValor() : null;
    }

    /**
     * Remove recursivamente um nó da árvore e retorna a nova subárvore.
     */
    protected No<T> removerRecursivo(No<T> no, T valor) {
        if (no == null) return null;

        int comparacao = comparador.compare(valor, no.getValor());

        if (comparacao < 0) {
            no.setEsquerda(removerRecursivo(no.getEsquerda(), valor));
        } else if (comparacao > 0) {
            no.setDireita(removerRecursivo(no.getDireita(), valor));
        } else {
            // Nó encontrado
            this.ultimoRemovido = new No<>(no.getValor()); // Salva o nó removido (cópia dos dados)
            if (no.getEsquerda() == null && no.getDireita() == null) {
                return null; // Nó folha, simplesmente remove
            }
            if (no.getEsquerda() == null) return no.getDireita();
            if (no.getDireita() == null) return no.getEsquerda();
            No<T> maior = encontrarMaiorNo(no.getEsquerda());
            no.setValor(maior.getValor());
            no.setEsquerda(removerRecursivo(no.getEsquerda(), maior.getValor()));
        }
        return no;
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

    /**
     * Método auxiliar para pesquisar e retornar o nó (No<T>) correspondente ao valor.
     */
    protected No<T> pesquisarNo(No<T> no, T valor) {
        if (no == null) {
            return null;
        }

        int comparacao = comparador.compare(valor, no.getValor());

        if (comparacao == 0) {
            return no;
        } else if (comparacao < 0) {
            return pesquisarNo(no.getEsquerda(), valor);
        } else {
            return pesquisarNo(no.getDireita(), valor);
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

    @Override
    public int altura() {
        if (raiz == null) {
            return -1;
        }
        return alturaRecursiva(raiz);
    }

    /**
     * Método que retorna a altura da árvore a partir do nó passado como parâmetro.
     * @param no - Nó a partir do qual será calculada a altura da árvore.
     * @return A altura da árvore.
     */
    protected int alturaRecursiva(No<T> no) {
        if (no == null) return -1; // se o nó não existe, altura é -1
        int alturaEsquerda = alturaRecursiva(no.getEsquerda());
        int alturaDireita = alturaRecursiva(no.getDireita());
        return 1 + Math.max(alturaEsquerda, alturaDireita);
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