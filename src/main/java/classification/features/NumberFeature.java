package classification.features;

public class NumberFeature extends Feature implements IFeature<Double> {

    private double value;

    public NumberFeature(double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public double compareTo(IFeature<Double> feature) {
        return getValue() - feature.getValue();
    }
}
