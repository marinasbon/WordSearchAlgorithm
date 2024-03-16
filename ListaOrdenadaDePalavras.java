package Trabalho_2;

/**
 * Esta classe guarda as palavra do indice remissivo em ordem alfabetica.
 * 
 * @author Isabel H. Manssour, Heloysa Pelizon, Marina Bon
 */

public class ListaOrdenadaDePalavras {

    // Classe interna
    private class Palavra {
        public String s;
        public ListaDeOcorrencias listaOcorrencias;
        public Palavra next;
        public int frequencia; //atributo que conta quantas vezes uma palavra aparece no total

        public Palavra(String str) {
            s = str;
            next = null;
            listaOcorrencias = new ListaDeOcorrencias();
        }
    }

    private int count;
    private Palavra first;
    private Palavra last;

    public ListaOrdenadaDePalavras() {
        first = null;
        last = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    //retorna true se a lista esta vazia
    public boolean isEmpty() {
        return count == 0;
    }

    //retorna uma palavra a partir de seu elemento
    public Palavra get(String str) {
        Palavra aux = first;
        while (aux != last) {
            if (str.equals(aux.s)) {
                return aux;
            }
            aux = aux.next;
        }
        return null;
    }

    // metodo para acessar o atributo interno de cada palavra
    public void updateFrequencia(String str, int i) {
        Palavra p = get(str);
        if (p != null) {
            p.frequencia += i;
        }
    }

    // método que retorna a string da palavra com maior frequencia
    public String palavraMaisFrequente() {
        if (isEmpty()) {
            return null;
        }
        String palavraMaisFrequente = null;
        int frequenciaMax = 0;
        Palavra aux = first;

        while (aux != null) {
            if (aux.frequencia > frequenciaMax) {
                frequenciaMax = aux.frequencia;
                palavraMaisFrequente = aux.s;
            }
            aux = aux.next;
        }
        return palavraMaisFrequente;
    }

    //metodo que acessa o to string de uma palavra especifica
    public String buscarPalavra(String palavra) {
        Palavra aux = get(palavra);
        if (aux != null) {
            return ("Ocorrências da palavra " + palavra + ": " + aux.listaOcorrencias.toString() + "\n");
        } else {
            return ("A palavra '" + palavra + "' não foi encontrada no índice remissivo.\n");
        }
    }

    //adiciona uma ocorrencia a uma palavra, somente se ainda nao esta presente
    public boolean addOcorrencia(int pagina, String str) {
        Palavra palavra = first;
        while (palavra != null) {
            if (palavra.s.equals(str)) {
                if (!palavra.listaOcorrencias.contains(pagina)) {
                    palavra.listaOcorrencias.add(pagina);
                    return true;
                }
            }
            palavra = palavra.next;
        }
        return false;
    }

    //insere uma palavra na lista em ordem alfabética
    public void orderedAdd(String str, int nPagina) {
        Palavra aux = first;
        Palavra prev = null;
        Palavra pal = new Palavra(str);
        //encontra a posicao
        if (!contains(str)){//se nao tem no indice, adiciona
        while (aux != null && str.compareTo(aux.s) > 0) {
            prev = aux;
            aux = aux.next;
        }
        if (prev == null) {
            // inserir no início
            pal.next = first;
            first = pal;
        } else {
            // inserir no meio ou no final da lista
            prev.next = pal;
            pal.next = aux;
        }
        count++;
    }
    addOcorrencia(nPagina, str);
    updateFrequencia(str, 1);
    }

    //verifica se a lista ja tem alguma palavra identica
    public boolean contains(String element) {
        Palavra aux = first;
        while (aux != last) {
            if (aux.s.equals(element)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Palavra aux = first;
        for (int i = 0; i < count; i++) {
            str.append(aux.s);
            str.append(": ");
            str.append(aux.listaOcorrencias.toString());
            str.append("\n");
            aux = aux.next;
        }
        return str.toString();
    }
}