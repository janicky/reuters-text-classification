package classification.metrics;

import classification.abstracts.Metric;
import classification.utils.Feature;

public class Euclidean extends Metric {
    public double calculate() {
        double sum = 0;
        for (Feature feature : features) {
            sum += feature.getDifference() * feature.getDifference();
        }

        return Math.sqrt(sum);
    }
}
