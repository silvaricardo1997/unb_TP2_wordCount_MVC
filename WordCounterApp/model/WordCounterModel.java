package model;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public final class WordCountModel {
    
    public HashMap<String, Integer> freqHashMap = new HashMap<>();
    
    public WordCountModel(String pathToFile) throws FileNotFoundException {
        this.freqHashMap = update(pathToFile);
    }
    
    public HashMap update (String pathToFile) throws FileNotFoundException {
        // Constrói/atualiza HashMap de frequências
        
        FileReader file = new FileReader(pathToFile);
        Scanner s = new Scanner(file);
        HashMap<String,Integer> wordFrequency = new HashMap<>();
        //chave = palavra; valor = frequencia

        while (s.hasNext()) {
            String palavra = s.next();
            String palavraAux = "";
            //Tratamento palavra, remoção da vírgula
            for(int i=0; i<(palavra.length()-1);i++){
                palavraAux += palavra.charAt(i);
            }
            palavra = palavraAux;

            if (wordFrequency.containsKey(palavra)) {
                // Incrementa frequência palavra já registrada
                int individualFreq = wordFrequency.get(palavra);
                wordFrequency.put(palavra, individualFreq + 1);
            } else {
                // Registra par chave-valor, palavra ainda não registrada freq = 1
                wordFrequency.put(palavra, 1);
            }
        }

        s.close();
        return wordFrequency;
    }
}
