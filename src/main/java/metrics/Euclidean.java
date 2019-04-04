package metrics;

import utils.Feature;

public class Euclidean extends utils.Metric {
    public double calculate() {
        double sum = 0;
        for (Feature feature : features) {
            sum += feature.getDifference() * feature.getDifference();
        }

        return Math.sqrt(sum);
    }
}
