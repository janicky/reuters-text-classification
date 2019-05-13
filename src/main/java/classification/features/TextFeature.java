package classification.features;

import classification.similarity.ISimilarityMeter;
public class TextFeature implements IFeature<String> {

    private String value;
    public TextFeature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public double compareTo(IFeature<String> feature, ISimilarityMeter similarityMeter) {
        return similarityMeter.measure(getValue(), feature.getValue());
    }
}
