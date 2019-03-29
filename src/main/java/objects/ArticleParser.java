package objects;

import utils.Parser;
import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class ArticleParser implements Parser {
    public ClassificationObject[] parseFile(File file) throws IOException {
        Document document = Jsoup.parse(file, "utf-8");
        Elements elements = document.getElementsByTag("REUTERS");

        ClassificationObject[] classificationObjects = new ClassificationObject[elements.size()];

        for (int e = 0; e < classificationObjects.length; e++) {
            Element element = elements.get(e);

            Elements title = element.getElementsByTag("TITLE");
            Elements places = element.getElementsByTag("PLACES");
            Elements body = element.getElementsByTag("BODY");

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
