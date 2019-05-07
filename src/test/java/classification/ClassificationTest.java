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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ClassificationTest {

    private Classification classification;
    private IClassificationObject[] objects;

    @BeforeEach
    void initialize() throws IOException {
        objects = Loader.load("data/reut2-000.sgm", new ArticleParser());
        classification = new Classification(objects);
        classification.setK(15);
    }

    @Test
    void count() {
        assertEquals(1000, objects.length);
    }

    @Test
    void objectsRatio() {
        classification.splitSets(0.7);
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

        String[] labels = { "west-germany", "usa", "france", "uk", "canada", "japan" };
        System.out.println("Filtering objects... " + Arrays.toString(labels));
        classification.filterObjects(labels, 2);
        System.out.println("Splitting objects...");
        classification.splitSets(0.6);
        System.out.println("Loading stopwords... ");
        classification.loadStopWords("data/stopwords.txt");
        System.out.println("Preparing data...");
        classification.prepareData();
        System.out.println("Generating keywords...");
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