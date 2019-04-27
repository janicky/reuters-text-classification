package classification.metrics;

import classification.features.Feature;

public class Euclidean extends Metric {
    public double calculate() {
        double sum = 0;
        for (Feature feature : features) {
            sum += (Math.pow(2, feature.getX()))
                    - (2 * feature.getX()*feature.getY())
                    + (Math.pow(2, feature.getY()));

//            sum += feature.getDifference() * feature.getDifference();
        }
        return Math.sqrt(sum);
    }
}
