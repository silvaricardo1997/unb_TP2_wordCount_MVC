package model;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordCounterModelTest {
    WordCounterModel testModel = new WordCounterModel();
    Map<String, Integer> testWordFrequency = new LinkedHashMap<>();

    @Test
    public void testCountWordsFromFile_IOExceptionOnFileNotFound() throws IOException {
        // Verifica lançamento de exceção
        assertThrows(IOException.class, () -> {
            testModel.countWordsFromFile("/invalid.txt", "/invalid.txt");
        
        });
    }

    @Test
    public void testCountWordsFromFile_OnlyNumbersAndSpecialCharacters() throws IOException {
        // when
        testWordFrequency = testModel.countWordsFromFile("\\WordCounterApp\\resources\\txt_UnitTest\\irregular_test.txt", "\\WordCounterApp\\resources\\stop-words.txt");

        // then
        assertTrue(testWordFrequency.isEmpty());
    }

    @Test
    public void testCountWordsFromFile_OnlyStopWords() throws IOException {
        // when
        testWordFrequency = testModel.countWordsFromFile("\\WordCounterApp\\resources\\stop-words.txt", "\\WordCounterApp\\resources\\stop-words.txt");

        // then
        assertTrue(testWordFrequency.isEmpty());
    }

    @Test
    public void testCountWordsFromFile_TrivialWithStopWords() throws IOException {
        // given
        Map<String, Integer> testCorrectOutput = new LinkedHashMap<>();
        testCorrectOutput.put("degree", 6);
        testCorrectOutput.put("light", 4);
        testCorrectOutput.put("trivial", 2);
        testCorrectOutput.put("thus", 2);
        testCorrectOutput.put("concert", 2);
        testCorrectOutput.put("without", 2);

        // when
        testWordFrequency = testModel.countWordsFromFile("WordCounterApp\\resources\\txt_UnitTest\\random_test.txt", "WordCounterApp\\resources\\stop-words.txt");

        // then
        assertEquals(testWordFrequency, testCorrectOutput);
    }


    @Test
    public void testCountTotalWordsExcludingStopwords_TrivialWithStopWords() throws IOException {
        // given
        int expected = 18;

        // when
        int actual = testModel.countTotalWordsExcludingStopwords("WordCounterApp\\resources\\txt_UnitTest\\random_test.txt", "WordCounterApp\\resources\\stop-words.txt");

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testCountTotalWordsExcludingStopwords_OnlyStopWords() throws IOException {
        // given
        int expected = 0;

        // when
        int actual = testModel.countTotalWordsExcludingStopwords("WordCounterApp\\resources\\stop-words.txt", "WordCounterApp\\resources\\stop-words.txt");

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testCountTotalWordsExcludingStopwords_OnlySpecialCharacters() throws IOException {
        // given
        int expected = 0;

        // when
        int actual = testModel.countTotalWordsExcludingStopwords("WordCounterApp\\resources\\txt_UnitTest\\irregular_test.txt", "WordCounterApp\\resources\\stop-words.txt");

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTopWords() throws IOException {
        // given
        Map<String, Integer> testCorrectOutput = new LinkedHashMap<>();
        testCorrectOutput.put("could", 179);
        testCorrectOutput.put("one", 168);

        // when
        testWordFrequency = testModel.countWordsFromFile("WordCounterApp\\resources\\texto.txt", "WordCounterApp\\resources\\stop-words.txt");
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>(testWordFrequency);

        // then
        assertEquals(testCorrectOutput, testModel.getTopWords(linkedHashMap, 2));
    }
}
