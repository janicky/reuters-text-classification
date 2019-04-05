import data_models.ArticleParser;
import interfaces.IClassificationObject;
import metrics.Euclidean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Loader;

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
    void perform() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] extractors = new String[] {
                "wordsCountExtraction",
                "densityExtraction"
        };
        classification.perform(new Euclidean(), extractors);
    }

}