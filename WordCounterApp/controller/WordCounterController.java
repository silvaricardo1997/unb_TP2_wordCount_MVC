package unb_TP2_wordCount_MVC.WordCounterApp.controller;

import unb_TP2_wordCount_MVC.WordCounterApp.model.WordCounterModel;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordCounterController {
    private WordCounterModel model;

    public WordCounterController() {
        // Inicializa o modelo do controlador
        this.model = new WordCounterModel();
    }

    public Map<String, Integer> countWordsFromFile(String textFilePath, String stopWordsFilePath) throws IOException {
        // Encaminha a solicitação para contar palavras ao modelo, sem limitação de pares chave-valor
        return model.countWordsFromFile(textFilePath, stopWordsFilePath);
    }
    
    public Map<String, Integer> countWordsFromFileLimit(Map<String, Integer> wordFrequency, int numWordsToShow) throws IOException {
        // Encaminha a solicitação para contar palavras ao modelo, com limitação de pares chave-valor
        return model.getTopWords(wordFrequency, numWordsToShow);
    }
}
