package unb_TP2_wordCount_MVC.WordCounterApp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

class WordCounterModelTest {
    WordCounterModel testModel = new WordCounterModel();
    Map<String, Integer> testWordFrequency = new LinkedHashMap<>();

    @Test
    public void testCountWordsFromFile_IOExceptionOnFileNotFound() throws IOException {
        // Verifica lançamento de exceção
        assertThrows(IOException.class, () -> {
            testModel.countWordsFromFile("/invalid.txt", "/invalid.txt", 0);
        });
    }

    @Test
    public void testCountWordsFromFile_OnlyNumbersAndSpecialCharacters() throws IOException {
        // when
        testWordFrequency = testModel.countWordsFromFile("/WordCounterApp/resources/txt_UnitTest/irregular.txt", "/WordCounterApp/resources/stop-words.txt", 1);

        // then
        assertTrue(testWordFrequency.isEmpty());
    }

    @Test
    public void testCountWordsFromFile_OnlyStopWords() throws IOException {
        // when
        testWordFrequency = testModel.countWordsFromFile("/WordCounterApp/resources/stop-words.txt", "/WordCounterApp/resources/stop-words.txt", 1);

        // then
        assertTrue(testWordFrequency.isEmpty());
    }

    @Test
    public void testCountWordsFromFile_TrivialWithStopWords() throws IOException {
        // given
        Map<String, Integer> testCorrectOutput = new LinkedHashMap<>() {{
            put("degree", 6);
            put("light", 4);
            put("trivial", 2);
            put("thus", 2);
            put("concert", 2);
            put("without", 2);
        }};

        // when
        testWordFrequency = testModel.countWordsFromFile("/WordCounterApp/resources/txt_UnitTest/random_test.txt", "/WordCounterApp/resources/stop-words.txt", 30);

        // then
        Assertions.assertEquals(testWordFrequency, testCorrectOutput);
    }

    @Test
    public void testCountTotalWordsExcludingStopwords_TrivialWithStopWords() throws IOException {
        // given
        int expected = 18;

        // when
        int actual = testModel.countTotalWordsExcludingStopwords("/WordCounterApp/resources/txt_UnitTest/random_test.txt", "/WordCounterApp/resources/stop-words.txt");

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testCountTotalWordsExcludingStopwords_OnlyStopWords() throws IOException {
        // given
        int expected = 0;

        // when
        int actual = testModel.countTotalWordsExcludingStopwords("/WordCounterApp/resources/stop-words.txt", "/WordCounterApp/resources/stop-words.txt");

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testCountTotalWordsExcludingStopwords_OnlySpecialCharacters() throws IOException {
        // given
        int expected = 0;

        // when
        int actual = testModel.countTotalWordsExcludingStopwords("/WordCounterApp/resources/txt_UnitTest/irregular.txt", "/WordCounterApp/resources/stop-words.txt");

        // then
        assertEquals(expected, actual);
    }
}
