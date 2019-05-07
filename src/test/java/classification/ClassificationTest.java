package classification;

import classification.data_models.ArticleParser;
import classification.data_models.IClassificationObject;
import classification.metrics.Euclidean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import classification.utils.Loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ClassificationTest {

    private Classification classification;
    private IClassificationObject[] objects;

    @BeforeEach
    void initialize() throws IOException {
        objects = Loader.load("data/reut2-000.sgm", new ArticleParser());
        classification = new Classification(objects, 0.7, 15);
    }

    @Test
    void count() {
        assertEquals(1000, objects.length);
    }

    @Test
    void objectsRatio() {
        assertEquals(700, classification.getLearningSet().length);
        assertEquals(300, classification.getTestingSet().length);
    }

    @Test
    void getAccuracy() {
        assertEquals(0, classification.getAccuracy());
    }

    @Test
    void perform() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, FileNotFoundException {
        String[] extractors = new String[] {
                "keywordOccurrences",
                "keywordsPosition",
                "mostFrequentKeyword"
        };

        System.out.println("Loading stopwords... ");
        classification.loadStopWords("data/stopwords.txt");
        System.out.println("Prepare data...");
        classification.prepareData();
        System.out.println("Generate keywords...");
        classification.generateKeywords(0.5);
        System.out.println("Features extraction...");
        classification.extractFeatures(extractors);
        System.out.println("Starting classification...");
        classification.perform(new Euclidean());

        System.out.println("Accuracy: " + classification.getAccuracy());
    }

    @Test
    void prepareData() {

    }
}