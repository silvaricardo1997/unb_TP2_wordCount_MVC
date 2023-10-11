package unb_TP2_wordCount_MVC.WordCounterApp.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class WordCounterModel {

    /**
     * Conta a frequência das palavras em um arquivo de texto e retorna as palavras mais frequentes.
     *
     * @param textFilePath     O caminho do arquivo de texto a ser analisado.
     * @param stopWordsFilePath O caminho do arquivo de stopwords para exclusão.
     * @param numWordsToShow    O número de palavras mais frequentes a serem exibidas.
     * @return Um mapa de palavras mais frequentes e suas contagens.
     * @throws IOException Se houver um erro de leitura dos arquivos.
     */
    public Map<String, Integer> countWordsFromFile(String textFilePath, String stopWordsFilePath, int numWordsToShow) throws IOException {
        // Carrega as stopwords a serem excluídas
        Set<String> stopWords = loadStopWords(stopWordsFilePath);

        // Lê as linhas do arquivo de texto e conta a frequência das palavras
        Stream<String> lines = Files.lines(Paths.get(textFilePath));
        Map<String, Integer> wordFrequency = new HashMap<>();

        lines.forEach(line -> {
            String[] words = line.split("\\s+");
            for (String word : words) {
                word = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
                if (!word.isEmpty() && !stopWords.contains(word)) {
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }
        });

        // Retorna as palavras mais frequentes
        return getTopWords(wordFrequency, numWordsToShow);
    }

    /**
     * Conta o número total de palavras em um arquivo de texto excluindo as stopwords.
     *
     * @param textFilePath     O caminho do arquivo de texto a ser analisado.
     * @param stopWordsFilePath O caminho do arquivo de stopwords para exclusão.
     * @return O número total de palavras no arquivo, excluindo as stopwords.
     * @throws IOException Se houver um erro de leitura dos arquivos.
     */
    public int countTotalWordsExcludingStopwords(String textFilePath, String stopWordsFilePath) throws IOException {
        // Carrega as stopwords a serem excluídas
        Set<String> stopWords = loadStopWords(stopWordsFilePath);

        // Lê as linhas do arquivo de texto e conta o número total de palavras
        Stream<String> lines = Files.lines(Paths.get(textFilePath));
        int totalWords = 0;

        for (String line : (Iterable<String>) lines::iterator) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                word = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
                if (!word.isEmpty() && !stopWords.contains(word)) {
                    totalWords++;
                }
            }
        }

        return totalWords;
    }

    /**
     * Carrega as palavras de stopwords a partir de um arquivo.
     *
     * @param stopWordsFilePath O caminho do arquivo de stopwords.
     * @return Um conjunto de stopwords.
     * @throws IOException Se houver um erro de leitura do arquivo de stopwords.
     */
    private Set<String> loadStopWords(String stopWordsFilePath) throws IOException {
        Set<String> stopWords = new HashSet<>();

        try (Stream<String> stopWordsStream = Files.lines(Paths.get(stopWordsFilePath))) {
            stopWordsStream.forEach(stopWords::add);
        }

        return stopWords;
    }

    /**
     * Retorna um mapa das N palavras mais frequentes a partir de um mapa de frequência de palavras.
     *
     * @param wordFrequency  O mapa de frequência de palavras.
     * @param numWordsToShow O número de palavras mais frequentes a serem exibidas.
     * @return Um mapa das N palavras mais frequentes.
     */
    private Map<String, Integer> getTopWords(Map<String, Integer> wordFrequency, int numWordsToShow) {
        return wordFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(numWordsToShow)
                .collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);
    }
}
