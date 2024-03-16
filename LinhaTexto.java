package Trabalho_2;

/**
 * @author Isabel H. Manssour, Heloysa Pelizon, Marina Bon
 */
public class LinhaTexto {
    private String linha;
    private String palavras[];
    private int contPalavras;

    /**
     * Recebe a string da linha que sera armazenada.
     * 
     * @param lin String com a linha de linha
     */
    public void setLine(String lin) {
        linha = lin;
        linha = linha.replaceAll("’[^’]*\\s|’[^’]*", " ");// duas linhas para remover o conteudo
        linha = linha.replaceAll("'[^'']*\\s|'[^'']*", " ");// depois de aspas simples pois existem dois tipos
                                                                            // de char ' nos livros
        linha = linha.replaceAll("\\t", " ");
        linha = linha.replaceAll("--", " ");
        linha = linha.replaceAll("[()”’‘:;“1234567890{}_\\-,.?!'\\\"]", "");
        linha = linha.replaceAll("[\\[\\]]", "");
        linha = removerAcentos(linha);
        palavras = linha.split("\\s+");
        contPalavras = 0;
    }

    // metodo que remove acentos e substitui pelas letras sem acento
    private String removerAcentos(String l) {
        l = l.replaceAll("[áàâã]", "a");
        l = l.replaceAll("[éèê]", "e");
        l = l.replaceAll("[íì]", "i");
        l = l.replaceAll("[óòôõ]", "o");
        l = l.replaceAll("[úùû]", "u");
        return l;
    }

    /**
     * Retorna uma palavra da linha.
     * 
     * @return a palavra, ou null caso nao tenha mais palavras.
     */
    public String getNextWord() {
        String pal = null;
        if (contPalavras < palavras.length) {
            pal = palavras[contPalavras];
            contPalavras++;
        }
        return pal;
    }

    public String toString() {
        return linha;
    }
}
