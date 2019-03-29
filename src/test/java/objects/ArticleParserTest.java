package objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Parser;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ArticleParserTest {
    private File file;
    private Parser parser;

    @BeforeEach
    void initialize() {
        file = new File("data/reut2-000.sgm");
        parser = new ArticleParser();
    }

    @Test
    void parseFile() throws IOException {
        ClassificationObject[] objects = parser.parseFile(file);
        for (ClassificationObject object : objects) {
            System.out.println(object);
        }
    }
}