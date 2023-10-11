package model;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public final class WordCountModel {
    
    // Chave:Palavra ; Valor:Frequência
    public HashMap<String, Integer> freqHashMap = new HashMap<>();
    private ArrayList<String> stopWords = new ArrayList<>();
    
    // Construtora. Já atualiza os dados ao instanciar
    public WordCountModel(String pathToFile) throws FileNotFoundException {
        update(pathToFile);
    }
    
    // Constrói/atualiza HashMap de frequências
    public void update (String pathToFile) throws FileNotFoundException {
        // Lê palavras de parada. Inserir caminho do seu txt das StopWords
        FileReader fileStopWords = new FileReader("C:\\Users\\artde\\Documents\\NetBeansProjects\\WordCounterApp\\src\\resources\\stop-words.txt");
        Scanner scannerStopWords = new Scanner(fileStopWords);
        
        // Lê palavras do texto, uma a uma. Printar instância do WordCountModel em uma main, passando o caminho do texto.txt. Delimitador padrão
        FileReader fileWords = new FileReader(pathToFile);
        Scanner scanner = new Scanner(fileWords);
        
        // Monta arr com palavras de parada
        while (scannerStopWords.hasNext()) {
            stopWords.add(scannerStopWords.next().trim());
        }
        
        // Monta HashMap com palavras do texto
        while (scanner.hasNext()) {
            String palavra = scanner.next().trim();
            String palavraAux = "";
            
            // Lê palavra, mesmo que irregular. Desconsidera caracteres especiais
            for(int i=0;i<palavra.length();i++){
                if (palavra.charAt(i) != '.' && palavra.charAt(i) != ',' && palavra.charAt(i) != ';' && palavra.charAt(i) != ':' &&
                        palavra.charAt(i)!= '_' && palavra.charAt(i) != '!' && palavra.charAt(i) != '?') {
                    palavraAux += palavra.charAt(i);
                }
            }
            palavra = palavraAux.toLowerCase();
            
            // Só registra se não for StopWord
            if (!(stopWords.contains(palavra))) {
                // Incrementa frequência palavra já registrada
                if (this.freqHashMap.containsKey(palavra)) {
                    int individualFreq = this.freqHashMap.get(palavra);
                    freqHashMap.put(palavra, individualFreq + 1);
                } else {
                    // Registra par chave-valor, palavra ainda não registrada freq = 1
                    this.freqHashMap.put(palavra, 1);
                }
            }
        }
        scanner.close();
    }
}
