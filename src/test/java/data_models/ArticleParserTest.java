package data_models;

import interfaces.IClassificationObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import interfaces.Parser;

import java.io.File;
import java.io.IOException;

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
        IClassificationObject[] objects = parser.parseFile(file);
        for (IClassificationObject object : objects) {
            System.out.println(object);
        }
    }
}