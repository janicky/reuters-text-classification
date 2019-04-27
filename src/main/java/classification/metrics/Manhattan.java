package classification.metrics;

import classification.features.Feature;

public class Manhattan extends Metric {
    public double calculate() {
        double sum = 0;
        for (Feature feature : features) {
            sum += Math.abs(feature.getX() - feature.getY());
        }

        return sum;
    }
}
