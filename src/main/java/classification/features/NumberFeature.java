package classification.features;

import classification.similarity.ISimilarityMeter;

public class NumberFeature implements IFeature<Double> {

    private double value;

    public NumberFeature(double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double compareTo(IFeature<Double> feature, ISimilarityMeter similarityMeter) {
        return getValue() - feature.getValue();
    }
}
