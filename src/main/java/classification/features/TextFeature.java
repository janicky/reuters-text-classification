package classification.features;

import classification.similarity.NGram;

public class TextFeature implements IFeature<String> {

    private String value;
    public TextFeature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public double compareTo(IFeature<String> feature) {
        NGram trigram = new NGram(3);
        return trigram.measure(getValue(), feature.getValue());
    }
}
