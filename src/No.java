public class No {
    private int altura;
    private int valorNo;
    private No noEsquerdo;
    private No noDireito;

    //Construtor
    public No(int valorNo) {
        this.valorNo = valorNo;
        this.noEsquerdo = null;
        this.noDireito = null;

    }

    //encapsulamento

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getValorNo() {
        return valorNo;
    }

    public void setValorNo(int valorNo) {
        this.valorNo = valorNo;
    }

    public No getNoEsquerdo() {
        return noEsquerdo;
    }

    public void setNoEsquerdo(No noEsquerdo) {
        this.noEsquerdo = noEsquerdo;
    }

    public No getNoDireito() {
        return noDireito;
    }

    public void setNoDireito(No noDireito) {
        this.noDireito = noDireito;
    }
}
