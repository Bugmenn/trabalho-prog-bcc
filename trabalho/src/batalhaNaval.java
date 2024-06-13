import java.util.Random;
import java.util.Scanner;

public class batalhaNaval {

    public static void main(String[] args) {
        new batalhaNaval();
    }

    private batalhaNaval() {
        Scanner s = new Scanner(System.in);

        String matriz[][] = CriarMatriz();

        Escrever(matriz);
    }

    private String[][] CriarMatriz() {
        // criação matriz 8x8
        String matriz[][] = new String[8][8];

        // colocar a "água no tabuleiro"
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = "~";
            }
        }

        // gerador de número aleatorio para colocar navios
        Random gerador = new Random();
        for (int i = 0; i < 10; i++) {
            int numeroAleatorio1 = gerador.nextInt(8);
            int numeroAleatorio2 = gerador.nextInt(8);

            // colocar os navios nas posições aleatorias
            if (matriz[numeroAleatorio1][numeroAleatorio2].contains("~")) { // if para não colocar navios na mesma posição
                matriz[numeroAleatorio1][numeroAleatorio2] = "N";
            } else { // caso forem colocados na mesma posição refaz a tentativa
                i--;
            }
        }

        return matriz;
    }

    private void Escrever(String matriz[][]) {
        // escrever o tabuleiro
        System.out.println(" 0 1 2 3 4 5 6 7"); // casas eixo x
        for (int i = 0; i < matriz.length; i++) { // for de linhas
            System.out.print(i); // casa eixo y
            for (int j = 0; j < matriz.length; j++) { // for de colunas
                if (j == 7) { // if para quebrar linha no ultimo valor
                    if (matriz[i][j].contains("N")) { // if para não mostrar os navios ao jogador
                        System.out.println("~");
                    } else {
                        System.out.println(matriz[i][j]);
                    }
                } else {
                    if (matriz[i][j].contains("N")) { // if para não mostrar navios ao jogador
                        System.out.print("~ ");
                    } else {
                        System.out.print(matriz[i][j]+" ");
                    }
                }
            }
        }
        
        System.out.println("\n 0 1 2 3 4 5 6 7"); // casas eixo x
        for (int i = 0; i < matriz.length; i++) { // for de linhas
            System.out.print(i); // casa eixo y
            for (int j = 0; j < matriz.length; j++) { // for de colunas
                if (j == 7) { // if para quebrar linha no ultimo valor
                    System.out.println(matriz[i][j]);
                } else {
                     System.out.print(matriz[i][j]+" ");
                }
            }
        }
    }
}
