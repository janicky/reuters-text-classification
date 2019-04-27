package classification.data_models;
import java.util.Arrays;

public class Article extends ClassificationObject {
    private String title;
    private String[] places;
    private String body;

    public Article(String title, String[] places, String body) {
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
        sb.append("--------------\n");
        sb.append("Title: " + title).append('\n');
        sb.append("Places: " + Arrays.toString(places)).append('\n');
        sb.append("Body: " + body).append('\n');

        return sb.toString();
    }

//    Interface implementation
    public String getText() {
        return getBody();
    }

    public String[] getLabels() {
        return getPlaces();
    }

}
