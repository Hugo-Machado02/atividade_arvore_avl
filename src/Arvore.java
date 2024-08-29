import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Math.max;

public class Arvore {
    private No raiz;

    // Construtor
    public Arvore() {
        this.raiz = null;
    }

    // Encapsulamento
    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(int valNo) {
        this.raiz = setNovoNo(this.raiz, valNo);
    }

    public No setNovoNo(No no, int valor) {
        if (no == null) {
            no = new No(valor);
            verificaStatus(no);
            return no;
        } else if (valor < no.getValorNo()) {
            no.setNoEsquerdo(setNovoNo(no.getNoEsquerdo(), valor));
        } else if (valor > no.getValorNo()) {
            no.setNoDireito(setNovoNo(no.getNoDireito(), valor));
        }

        no = AtualizaAlturaInsercao(no);

        verificaStatus(no);
        return no;
    }

    public void remocao(int valorNo) {
        this.raiz = removeNo(this.raiz, valorNo);
    }

    public No removeNo(No no, int val) {
        if (no == null) {
            return null;
        }

        if (val < no.getValorNo()) {
            no.setNoEsquerdo(removeNo(no.getNoEsquerdo(), val));
        } else if (val > no.getValorNo()) {
            no.setNoDireito(removeNo(no.getNoDireito(), val));
        } else {
            if (no.getNoEsquerdo() == null) {
                return no.getNoDireito();
            } else if (no.getNoDireito() == null) {
                return no.getNoEsquerdo();
            }
            No temp = minimoVal(no.getNoDireito());
            no.setValorNo(temp.getValorNo());
            no.setNoDireito(removeNo(no.getNoDireito(), temp.getValorNo()));
        }

        // Atualiza a altura do nó
        no = AtualizaAlturaRemocao(no);

        verificaStatus(no);
        return no;
    }

    private No AtualizaAlturaInsercao(No no) {
        no.setAltura(1 + max(altura(no.getNoEsquerdo()), altura(no.getNoDireito())));

        // Verifica o balanceamento e aplica rotações se necessário
        int balanceamento = validaBalanceamento(no);

        if (balanceamento > 1) {
            if (validaBalanceamento(no.getNoEsquerdo()) >= 0) {
                no = rotacaoLL(no);
            } else {
                no.setNoEsquerdo(rotacaoRR(no.getNoEsquerdo()));
                no = rotacaoLL(no);
            }
        } else if (balanceamento < -1) {
            if (validaBalanceamento(no.getNoDireito()) <= 0) {
                no = rotacaoRR(no);
            } else {
                no.setNoDireito(rotacaoLL(no.getNoDireito()));
                no = rotacaoRR(no);
            }
        }

        return no;
    }

    private No AtualizaAlturaRemocao(No no) {
        no.setAltura(1 + max(altura(no.getNoEsquerdo()), altura(no.getNoDireito())));

        int balanceamento = validaBalanceamento(no);

        if (balanceamento > 1) {
            if (validaBalanceamento(no.getNoEsquerdo()) >= 0) {
                no = rotacaoLL(no);
            } else {
                no.setNoEsquerdo(rotacaoRR(no.getNoEsquerdo()));
                no = rotacaoLL(no);
            }
        } else if (balanceamento < -1) {
            if (validaBalanceamento(no.getNoDireito()) <= 0) {
                no = rotacaoRR(no);
            } else {
                no.setNoDireito(rotacaoLL(no.getNoDireito()));
                no = rotacaoRR(no);
            }
        }

        return no;
    }

    public No minimoVal(No no) {
        No noAtual = no;
        while (noAtual.getNoEsquerdo() != null) {
            noAtual = noAtual.getNoEsquerdo();
        }
        return noAtual;
    }

    private int altura(No no) {
        return (no != null) ? no.getAltura() : 0;
    }

    private int validaBalanceamento(No no) {
        return (no != null) ? altura(no.getNoEsquerdo()) - altura(no.getNoDireito()) : 0;
    }

    private void verificaStatus(No no) {
        if (no != null) {
            System.out.println("numero: " + no.getValorNo() + " - Fator de Balanceamento: " + validaBalanceamento(no));
        }
    }

    public boolean isArvoreBalanceada(No no) {
        if (no == null) {
            return true;
        }

        int balanceamento = validaBalanceamento(no);
        return (balanceamento >= -1 && balanceamento <= 1) &&
                isArvoreBalanceada(no.getNoEsquerdo()) &&
                isArvoreBalanceada(no.getNoDireito());
    }

    public No rotacaoLL(No no) {
        No filhoEsquerdo = no.getNoEsquerdo();
        no.setNoEsquerdo(filhoEsquerdo.getNoDireito());
        filhoEsquerdo.setNoDireito(no);

        no.setAltura(1 + max(altura(no.getNoEsquerdo()), altura(no.getNoDireito())));
        filhoEsquerdo.setAltura(1 + max(altura(filhoEsquerdo.getNoEsquerdo()), altura(filhoEsquerdo.getNoDireito())));

        return filhoEsquerdo;
    }

    public No rotacaoRR(No no) {
        No filhoDireito = no.getNoDireito();

        if (filhoDireito == null) {
            return no; // Retorna o nó atual se não há filho direito
        }

        no.setNoDireito(filhoDireito.getNoEsquerdo());
        filhoDireito.setNoEsquerdo(no);

        no.setAltura(1 + max(altura(no.getNoEsquerdo()), altura(no.getNoDireito())));
        filhoDireito.setAltura(1 + max(altura(filhoDireito.getNoEsquerdo()), altura(filhoDireito.getNoDireito())));

        return filhoDireito;
    }

    public No rotacaoLR(No no) {
        no.setNoEsquerdo(rotacaoRR(no.getNoEsquerdo()));
        return rotacaoRR(no);
    }

    public No rotacaoRL(No no) {
        no.setNoDireito(rotacaoLL(no.getNoDireito()));
        return rotacaoLL(no);
    }

    public void imprimirNivel(No raiz) {
        if (raiz == null) {
            return;
        }
        Queue<No> nos = new LinkedList<>();
        nos.add(raiz);

        while (!nos.isEmpty()) {
            No no = nos.poll();
            System.out.printf(no.getValorNo() + ", ");

            if (no.getNoEsquerdo() != null) {
                nos.add(no.getNoEsquerdo());
            }

            if (no.getNoDireito() != null) {
                nos.add(no.getNoDireito());
            }
        }
    }
}
