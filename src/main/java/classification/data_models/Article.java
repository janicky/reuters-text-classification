package classification.data_models;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.StringUtil;

import java.util.Arrays;

public class Article extends ClassificationObject {
    private String title;
    private String[] places;
    private String body;

    public Article(String title, String[] places, String body) {
        super(body);
        this.title = title;
        this.places = places;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String[] getPlaces() {
        return places;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("--------------\n");
        sb.append("Title: " + title);
        sb.append(" " + Arrays.toString(places));
//        sb.append("Body: " + body).append('\n');

        return StringUtils.abbreviate(sb.toString(), 60);
    }

//    Interface implementation
    public String getText() {
        return getBody();
    }

    public String[] getLabels() {
        return getPlaces();
    }

}
