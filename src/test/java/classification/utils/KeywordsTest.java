package classification.utils;

import classification.data_models.ArticleParser;
import classification.data_models.IClassificationObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KeywordsTest {

    private Keywords keywords;

    @BeforeEach
    void initialize() throws IOException {
        IClassificationObject[] objects = Loader.load("data/reut2-000.sgm", new ArticleParser());
        Operations.stem(objects);
        StopWords stopWords = new StopWords(objects);
        stopWords.loadFromFile("data/stopwords.txt");
        stopWords.removeStopWords();
        keywords = new Keywords(objects);
    }
    @Test
    void generate() {
        keywords.generate(0.5);
        assertEquals(71, keywords.getKeywords().length);
    }
}