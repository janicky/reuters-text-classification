package utils;

import objects.ClassificationObject;
import org.jsoup.nodes.Document;

public interface Parser {
    ClassificationObject[] parseDocument(Document document);
}
