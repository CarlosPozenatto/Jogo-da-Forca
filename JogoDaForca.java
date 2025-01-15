import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JogoDaForca {

    final private String palavraSecreta;
    final private StringBuilder palavraAdivinhada;
    final private List<Character> letrasErradas;
    private int tentativas;

    public JogoDaForca() {
        letrasErradas = new ArrayList<>();
        tentativas = 6; // Número de tentativas permitidas
        palavraSecreta = sortearPalavra();
        palavraAdivinhada = new StringBuilder("_".repeat(palavraSecreta.length()));

    }

    private String sortearPalavra() {
        List<String> palavras = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Forca.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                palavras.add(linha.trim());

            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de palavras: " + e.getMessage());

        }

        Random random = new Random();
        return palavras.get(random.nextInt(palavras.size()));

    }

    public void jogar() {
        Scanner scanner = new Scanner(System.in);

        while (tentativas > 0 && palavraAdivinhada.indexOf("_") != -1) {
            System.out.println("Palavra: " + palavraAdivinhada);
            System.out.println("Tentativas restantes: " + tentativas);
            System.out.println("Letras erradas: " + letrasErradas);
            System.out.print("Digite uma letra: ");

            char letra = scanner.nextLine().toLowerCase().charAt(0);

            if (palavraSecreta.indexOf(letra) != -1) {

                for (int i = 0; i < palavraSecreta.length(); i++) {

                    if (palavraSecreta.charAt(i) == letra) {
                        palavraAdivinhada.setCharAt(i, letra);

                    }
                }

            } else {
                if (!letrasErradas.contains(letra)) {
                    letrasErradas.add(letra);
                    tentativas--;

                } else {
                    System.out.println("Você já tentou essa letra.");

                }
            }
        }

        if (palavraAdivinhada.indexOf("_") == -1) {
            System.out.println("Parabéns! Você adivinhou a palavra: " + palavraSecreta);

        } else {
            System.out.println("Você perdeu! A palavra era: " + palavraSecreta);

        }
        scanner.close();
    }

    public static void main(String[] args) {
        JogoDaForca jogo = new JogoDaForca();
        jogo.jogar();

    }
}