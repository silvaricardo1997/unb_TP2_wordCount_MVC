package unb_TP2_wordCount_MVC.WordCounterApp.view;

import unb_TP2_wordCount_MVC.WordCounterApp.controller.WordCounterController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class WordCounterView {
    private JFrame frame;
    private JButton countButton;
    private JTextArea outputTextArea;
    private JTextArea totalCountTextArea;
    private JTextField textFilePathField;
    private JTextField stopWordsFilePathField;
    private JTextField numWordsField;
    private WordCounterController controller;

    public void initialize() {
        // Criação da janela principal
        frame = new JFrame("Word Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 445);

        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);

        // Labels e campos de entrada
        JLabel textFilePathLabel = new JLabel("Caminho do arquivo de texto:");
        textFilePathLabel.setBounds(10, 10, 200, 30);
        panel.add(textFilePathLabel);

        textFilePathField = new JTextField();
        textFilePathField.setBounds(220, 10, 330, 30);
        panel.add(textFilePathField);

        JLabel stopWordsFilePathLabel = new JLabel("Caminho do arquivo de stopwords:");
        stopWordsFilePathLabel.setBounds(10, 50, 200, 30);
        panel.add(stopWordsFilePathLabel);

        stopWordsFilePathField = new JTextField();
        stopWordsFilePathField.setBounds(220, 50, 330, 30);
        panel.add(stopWordsFilePathField);

        JLabel numWordsCounted = new JLabel("Total da contagem:");
        numWordsCounted.setBounds(10, 90, 200, 575);
        panel.add(numWordsCounted);
        
        JLabel numWordsLabel = new JLabel("N palavras mais frequentes:");
        numWordsLabel.setBounds(10, 90, 200, 30);
        panel.add(numWordsLabel);

        numWordsField = new JTextField("20");
        numWordsField.setBounds(220, 90, 60, 30);
        panel.add(numWordsField);

        // Botão "Contar"
        countButton = new JButton("Contar");
        countButton.setBounds(10, 130, 120, 30);
        panel.add(countButton);

        // Área de saída da contagem total
        totalCountTextArea = new JTextArea();
        totalCountTextArea.setBounds(120, 370, 50, 25);
        totalCountTextArea.setEditable(false);
        totalCountTextArea.setOpaque(false);
        panel.add(totalCountTextArea);

        // Área de saída de texto
        outputTextArea = new JTextArea();
        outputTextArea.setBounds(10, 170, 560, 180);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setLineWrap(true);
        outputTextArea.setCaretPosition(0);
        outputTextArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(10, 170, 560, 180);
        panel.add(scroll);

        // Ação ao clicar no botão "Contar"
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countWordsAndDisplay();
            }
        });

        // Torna a janela visível
        frame.setVisible(true);
    }

    public void setController(WordCounterController controller) {
        this.controller = controller;
    }

    private void countWordsAndDisplay() {
        // Obtém caminhos e valores da interface do usuário e invoca o controlador
        String textFilePath = textFilePathField.getText();
        String stopWordsFilePath = stopWordsFilePathField.getText();
        int numWordsToShow = Integer.parseInt(numWordsField.getText());

        if (textFilePath.isEmpty() || stopWordsFilePath.isEmpty()) {
            // Mensagem de erro se os campos estiverem vazios
            outputTextArea.setText("Informe o caminho dos arquivos de texto e stopwords.");
        } else {
            try {
                // Chama o controlador para contar palavras e exibir resultados
                Map<String, Integer> wordFrequencyLimited = controller.countWordsFromFileLimit(textFilePath, stopWordsFilePath, numWordsToShow);
                displayWordFrequency(wordFrequencyLimited);
                Map<String, Integer> wordFrequency = controller.countWordsFromFile(textFilePath, stopWordsFilePath);
                displayTotalCount(wordFrequency);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void displayWordFrequency(Map<String, Integer> wordFrequency) {
        // Exibe a frequência das palavras na área de saída
        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            output.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        outputTextArea.setText(output.toString());
    }
    
    private void displayTotalCount(Map<String, Integer> wordFrequency) {
        
        int totalCount = 0;
        for (Map.Entry<String, Integer> entry: wordFrequency.entrySet()) {
            totalCount += entry.getValue();
        }
        
        StringBuilder output = new StringBuilder();
        output.append(totalCount);
        totalCountTextArea.setText(output.toString());
        
    }
    
}
