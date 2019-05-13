package classification.data_models;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Article2 extends ClassificationObject {
    private String title;
    private String[] topics;
    private String body;

    public Article2(String title, String[] topics, String body) {
        super(body);
        this.title = title;
        this.topics = topics;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String[] getTopics() {
        return topics;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("--------------\n");
        sb.append("Title: " + title);
        sb.append(" " + Arrays.toString(topics));
//        sb.append("Body: " + body).append('\n');

        return StringUtils.abbreviate(sb.toString(), 60);
    }

//    Interface implementation
    public String getText() {
        return getBody();
    }

    public String[] getLabels() {
        return getTopics();
    }

}
