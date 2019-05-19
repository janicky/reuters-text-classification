package classification.data_models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class QuoteParser implements IParser {
    public IClassificationObject[] parseFile(File file) throws IOException {
        Scanner scanner = new Scanner(file).useDelimiter("\\A");
        String content = scanner.hasNext() ? scanner.next() : "";
        Document document = Jsoup.parse(content);
        Elements elements = document.getElementsByTag("quote");

        IClassificationObject[] classificationObjects = new IClassificationObject[elements.size()];

        for (int e = 0; e < classificationObjects.length; e++) {
            Element element = elements.get(e);

            Elements tvSerie = element.getElementsByTag("tvserie");
            Elements body = element.getElementsByTag("body");

            Quote article = new Quote(tvSerie.text(), body.text());
            classificationObjects[e] = article;
        }

        return classificationObjects;
    }
}
