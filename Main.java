package Trabalho_2;

import java.util.Scanner;

/**
 * Classe que inicializa a execução da aplicacao.
 * 
 * @author Isabel H. Manssour, Heloysa Pelizon, Marina Bon
 */

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArquivoTexto arquivo = new ArquivoTexto();
        LinhaTexto linha = new LinhaTexto();
        String l;
        int nLinha = 1;
        int nPagina = 1;
        int nPalavras = 0;
        int nStopWords = 0;
        ListaOrdenadaDePalavras indice = new ListaOrdenadaDePalavras();
        int escolha = -1;
        System.out.print("Digite o nome do arquivo: ");
        String nome = sc.nextLine();
        System.out.println("\nProcessando...\n");

        arquivo.open(nome);

        l = arquivo.getNextLine();

        do {
            l = arquivo.getNextLine();
            if (l == null) {
                break;
            }
            l = l.toLowerCase();
            nLinha++;
            if (nLinha == 40) {
                nLinha = 1;
                nPagina++;
            }
            linha.setLine(l);
            String palavra;
            do {
                palavra = linha.getNextWord();
                if (palavra == null) {
                    break;
                }
                if (stopWord(palavra)) {// se for uma stop word, nao adiciona e só conta para os contadores
                    nStopWords++;
                    nPalavras++;
                } else if (palavra.length() >= 3) { // remover palavras menores que 3 caracteres
                    indice.orderedAdd(palavra, nPagina);
                    nPalavras++;
                }
            } while (linha.getNextWord() != null);
        } while (arquivo.getNextLine() != null);
        arquivo.close();
        do {
            menuOp();
            escolha = sc.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println(indice.toString());
                    break;
                case 2:
                    System.out.print("\nPorcentagem de stop words: ");
                    System.out.printf("%.2f", porcentagem(nStopWords, nPalavras));
                    System.out.println("%\n");
                    System.out.println("Total de palavras: " + nPalavras + "\nTotal de stop words: " + nStopWords + "\n");
                    break;
                case 3:
                    String palavraMaisFrequente = indice.palavraMaisFrequente();
                    System.out.println("\nPalavra mais frequente: " + palavraMaisFrequente + "\n");
                    break;
                case 4:
                    sc.nextLine();
                    System.out.print("\nDigite a palavra que deseja pesquisar: ");
                    String palavraPesquisada = sc.nextLine().toLowerCase();
                    if (indice.get(palavraPesquisada) != null) {
                        System.out.println(indice.buscarPalavra(palavraPesquisada));
                    }
                    break;
                case 5:
                    System.out.println("\nSaindo do programa...\n");
                    break;
                default:
                    break;
            }
        } while (escolha != 5);
        sc.close();
    }

    // metodo que verifica se a palavra lida está presente no arquivo de stop words
    public static boolean stopWord(String pal) {
        ArquivoTexto arquivo = new ArquivoTexto();
        String l;
        arquivo.open("StopWords-EN.txt");
        do {
            l = arquivo.getNextLine();
            if (l == null) {
                break;
            }
            if (pal.equals(l)) {
                arquivo.close();
                return true;
            }
        } while (true);
        arquivo.close();
        return false;
    }

    // metodo simples que calcula a porcentagem de stopwords
    public static double porcentagem(int nStopWords, int nPalavras) {
        if (nPalavras == 0) {
            return 0.0;
        }
        return (nStopWords * 100.0) / nPalavras;
    }

    public static void menuOp() {
        System.out.println("(1) Exibir índice remissivo");
        System.out.println("(2) Porcentagem de stop words no livro");
        System.out.println("(3) Palavra mais frequente");
        System.out.println("(4) Pesquisar palavras");
        System.out.println("(5) Encerrar programa");
    }
}
