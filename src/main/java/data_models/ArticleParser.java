package data_models;

import interfaces.IClassificationObject;
import interfaces.IParser;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class ArticleParser implements IParser {
    public IClassificationObject[] parseFile(File file) throws IOException {
        Scanner scanner = new Scanner(file).useDelimiter("\\A");
        String content = scanner.hasNext() ? scanner.next() : "";
        content = content.replace("BODY>", "CONTENT>");
        Document document = Jsoup.parse(content);
        Elements elements = document.getElementsByTag("REUTERS");

        IClassificationObject[] classificationObjects = new IClassificationObject[elements.size()];

        for (int e = 0; e < classificationObjects.length; e++) {
            Element element = elements.get(e);

            Elements title = element.getElementsByTag("TITLE");
            Elements places = element.getElementsByTag("PLACES");
            Elements body = element.getElementsByTag("CONTENT");

            Elements places_items = places.select("D");
            String[] places_names = new String[places_items.size()];
            for (int i = 0; i < places_names.length; i++) {
                places_names[i] = places_items.get(i).text();
            }

            Article article = new Article(title.text(), places_names, body.text());
            classificationObjects[e] = article;
        }

        return classificationObjects;
    }
}
