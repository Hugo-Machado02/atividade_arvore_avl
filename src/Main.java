import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Arvore arvore = new Arvore();
        int op = 0;

        do {
            System.out.println("============ Menu ============");
            System.out.println("1- Adicionar 100 Novos Valores");
            System.out.println("2- Excluir 20 valores");
            System.out.println("0- Sair");
            System.out.printf("-> Selecione uma opção: ");
            op = scan.nextInt();
            System.out.println("==============================");

            switch (op){
                case 0:
                    System.out.println("Sessão Finalizada");
                    break;

                case 1:
                    System.out.println("=== Adicionar novos Valores ==");
                    InserirDados(arvore);
                    System.out.println("==============================");

                    System.out.printf("\n\nÁrvore após inserções => ");
                    boolean arvoreBalanceada = arvore.isArvoreBalanceada(arvore.getRaiz());

                    if(arvoreBalanceada){
                        System.out.println("Balanceada");
                    }
                    else {
                        System.out.println("Desbalanceada");
                    }
                    break;

                case 2:
                    System.out.println("===== Remocao de Valores =====");
                    removerDados(arvore);
                    System.out.println("====== Adicionar Valores =====");
                    // Verifica se a árvore está balanceada após remoções
                    System.out.printf("\nÁrvore após Remoção => ");
                    boolean arvoreBalanceadaRemocao = arvore.isArvoreBalanceada(arvore.getRaiz());

                    if(arvoreBalanceadaRemocao){
                        System.out.println("Balanceada");
                    }
                    else {
                        System.out.println("Desbalanceada");
                    }
                    break;

                default:
                    System.out.println("Opção não válida! Favor tentar novamente");
                    break;
            }
        }while (op != 0);

    }

    public static void InserirDados(Arvore arvore){
        Random random = new Random();
        int min = -500;
        int max = 500;
        int qtd = 100;


        for(int i=0; i < qtd; i++){
            int numero = random.nextInt((max - min) + 1) + min;
            arvore.setRaiz(numero);
        }
    }

    public static void removerDados(Arvore arvore){
        Random random = new Random();
        for(int i=0; i < 20; i++){
            int numero = random.nextInt((100 - 0) + 1) + 0;
            arvore.setRaiz(numero);
            arvore.remocao(i);
        }
    }
}
