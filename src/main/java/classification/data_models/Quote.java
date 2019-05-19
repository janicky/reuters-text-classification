package classification.data_models;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Quote extends ClassificationObject {
    private String[] tvseries;
    private String body;

    public Quote(String label, String body) {
        super(body);
        this.tvseries = new String[] { label };
        this.body = body;
    }

    public String[] getTvSeries() {
        return tvseries;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TV Series " + Arrays.toString(tvseries));
        sb.append("Body: " + body).append('\n');

        return StringUtils.abbreviate(sb.toString(), 60);
    }

//    Interface implementation
    public String getText() {
        return getBody();
    }

    public String[] getLabels() {
        return getTvSeries();
    }

}
