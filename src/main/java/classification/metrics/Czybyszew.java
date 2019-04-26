package classification.metrics;

import classification.abstracts.Metric;
import classification.utils.Feature;

public class Czybyszew extends Metric {
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
