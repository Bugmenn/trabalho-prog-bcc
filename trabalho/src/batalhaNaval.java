import java.util.Random;
import java.util.Scanner;

public class batalhaNaval {

    public static void main(String[] args) {
        new batalhaNaval();
    }

    private batalhaNaval() {
        char matriz[][] = CriarMatriz();
        int qtAcertos = 0;
        int qtJogadas = 0;
        int fim[] = {qtAcertos, qtJogadas}; // valores para confimar o fim do jogo

        boolean continuar; // valor para continuar jogo
        
        do {
            Jogar(matriz, fim);
            continuar = Fim(fim);
            Escrever(matriz, continuar); // imprime o tabuleiro
            System.out.println("Acertos: "+fim[0]+"  Jogadas: "+fim[1]);
        } while (continuar);
    }

    // cria a matriz e coloca as informações iniciais dentro
    private char[][] CriarMatriz() {
        // criação matriz 8x8
        char matriz[][] = new char[8][8];

        // colocar a "água" no tabuleiro
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = '~';
            }
        }

        // gerador de número aleatorio para colocar navios
        Random gerador = new Random();
        for (int i = 0; i < 10; i++) {
            int numeroAleatorio1 = gerador.nextInt(8); // pega os números de 0 a 7
            int numeroAleatorio2 = gerador.nextInt(8);

            // colocar os navios nas posições aleatorias
            if (matriz[numeroAleatorio1][numeroAleatorio2] == '~') { // if para não colocar navios na mesma posição
                matriz[numeroAleatorio1][numeroAleatorio2] = 'N';

            } else { // caso forem colocados na mesma posição refaz a tentativa
                i--;
            }
        }

        return matriz;
    }

    // realiza os prints do tabuleiro
    private void Escrever(char matriz[][], boolean continuar) {

        if (continuar == true) { // se o jogo continuar será imprimido sem o local dos navios
            // escrever o tabuleiro
            System.out.println(" 0 1 2 3 4 5 6 7"); // casas eixo x
            for (int i = 0; i < matriz.length; i++) { // for de linhas

                System.out.print(i); // casa eixo y

                for (int j = 0; j < matriz.length; j++) { // for de colunas

                    if (j == 7) { // if para quebrar linha no ultimo valor

                        if (matriz[i][j] == 'N') { // if para não mostrar os navios ao jogador
                            System.out.println('~');
                        } else {
                            System.out.println(matriz[i][j]);
                        }

                    } else {

                        if (matriz[i][j] == 'N') { // if para não mostrar navios ao jogador
                            System.out.print('~'+" ");
                        } else {
                            System.out.print(matriz[i][j]+" ");
                        }
                    }
                }
            }
        }
        
        if (continuar == false) { // se acabar será imprimido com o local dos navios
            System.out.println("\n 0 1 2 3 4 5 6 7"); // casas eixo x
            for (int i = 0; i < matriz.length; i++) { // for de linhas
    
                System.out.print(i); // casa eixo y
    
                for (int j = 0; j < matriz.length; j++) { // for de colunas
    
                    if (j == 7) { // if para quebrar linha no ultimo valor
                        System.out.println(matriz[i][j]);
                    } else { // para continuar na mesma linha
                         System.out.print(matriz[i][j]+" ");
                    }
                }
            }    
        }
    }

    // faz as jogadas do player
    private void Jogar(char matriz[][], int fim[]) {
        Scanner s = new Scanner(System.in); // criação do scanner (não fechei por que da problema de execução)
        System.out.println("Informe as coordenadas (linha coluna):");
        // inputs do jogador
        int linha = s.nextInt();
        int coluna = s.nextInt();
        
        // verificação dos valores
        if (linha >= 0 && linha <= 7 && coluna >= 0 && coluna <= 7) {

            if (matriz[linha][coluna] == 'N') { // verifica se a casa tem navio
                matriz[linha][coluna] = 'X'; // coloca o valor de acerto em navio
                System.out.println("Navio acertado!"); // informa que um navio foi acertado
                fim[0]++; // acrescenta 1 em acertos
                fim[1]++; // acrescenta 1 em jogadas

            } else if (matriz[linha][coluna] == 'X' || matriz[linha][coluna] == 'O') { // verifica se a casa já foi atacada
                System.out.println("Casa já atacada"); // informa que a casa já foi atacada

            } else { // se um navio não foi acertado
                matriz[linha][coluna] = 'O'; // coloca o valor de erro
                System.out.println("Errou!"); // informa que não acertou
                fim[1]++; // acrescenta 1 em jogadas 
            }

        } else { // se os valores não forem corretos
            System.out.println("Posição inválida!!!!!!");
        }
    }

    // verifica as condições para terminar o jogo
    // retorna falso caso o jogo acabe e verdadeiro para continuar
    private boolean Fim(int fim[]) {
        if (fim[0] == 10) { // se o jogador acertos os 10 navios ele ganha
            System.out.println("Jogador ganhou!!!!!");
            return false;

        } else if (fim[1] == 30) { // se o jogador jogar 30 ele perde
            System.out.println("Jogador perdeu!");
            return false;
        }

        return true;
    }
}
