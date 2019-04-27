package classification.metrics;

import classification.features.Feature;

public class Chebyshev extends Metric {
    private double getMax(double a, double b) {
        return (a>b?a:b);
    }

    public double calculate() { //TODO check implementation
        double sum = 0;
        for (Feature feature : features) {
            sum += getMax(feature.getX(), feature.getY());
        }
        return sum;
    }
}
