package classification.metrics;

import classification.features.Feature;

public class Canberra extends Metric {
    public double calculate() {
        double sum = 0;
        for (Feature feature : features) {
            double t = Math.abs(feature.getX() - feature.getY());
            double b = Math.abs(feature.getX()) + Math.abs(feature.getY());

            if (b == 0) {
                continue;
            }
            sum += t / b;
        }
        return sum;
    }
}
