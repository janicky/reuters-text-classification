package classification.features;

public class TextFeature extends Feature implements IFeature<String> {

    private String value;

    public TextFeature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
