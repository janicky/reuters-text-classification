import data_models.ArticleParser;
import interfaces.ClassificationObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Loader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClassificationTest {

    private static final int OBJECTS = 20;
    private Classification classification;
    private ClassificationObject[] objects;

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

}