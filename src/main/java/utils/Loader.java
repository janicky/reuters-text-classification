package utils;

import objects.ClassificationObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class Loader {
    private Document document;
    private Parser parser;

    public void Loader(String path, Parser parser) throws IOException {
        File file = new File(path);
        this.parser = parser;
        document = Jsoup.parse(file, "utf-8");
    }

    public ClassificationObject[] getObjects() {
        return parser.parseDocument(document);
    }
}
