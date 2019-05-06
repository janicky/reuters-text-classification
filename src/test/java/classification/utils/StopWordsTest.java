package classification.utils;

import classification.data_models.ArticleParser;
import classification.data_models.IClassificationObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StopWordsTest {

    StopWords stopWords;

    @BeforeEach
    void initialize() throws IOException {
        IClassificationObject[] objects = Loader.load("data/reut2-000.sgm", new ArticleParser());
        stopWords = new StopWords(objects);
    }

    @Test
    void loadFromFile() throws FileNotFoundException {
        stopWords.loadFromFile("data/stopwords.txt");
        assertEquals(127, stopWords.getStopWords().length);
    }

    @Test
    void generate() {
        stopWords.generate(2.5);
        System.out.println(Arrays.toString(stopWords.getStopWords()));
        System.out.println(stopWords.getStopWords().length);
    }
}