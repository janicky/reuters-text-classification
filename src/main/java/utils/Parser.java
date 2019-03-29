package utils;

import model.ClassificationObject;
import org.jsoup.nodes.Document;

public interface Parser {
    ClassificationObject[] parseDocument(Document document);
}
