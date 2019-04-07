package classification.data_models;

import classification.interfaces.IClassificationObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import classification.interfaces.IParser;

import java.io.File;
import java.io.IOException;

class ArticleParserTest {
    private File file;
    private IParser parser;

    @BeforeEach
    void initialize() {
        file = new File("data/reut2-000.sgm");
        parser = new ArticleParser();
    }

    @Test
    void parseFile() throws IOException {
        IClassificationObject[] objects = parser.parseFile(file);
        for (IClassificationObject object : objects) {
            System.out.println(object);
        }
    }
}