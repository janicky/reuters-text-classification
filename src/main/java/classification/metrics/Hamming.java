package classification.metrics;

import classification.features.Feature;

public class Hamming extends Metric {
    public double calculate() { //TODO check implementation
        double sum = 0;
        for (Feature feature : features) {
            if(feature.getY() != feature.getX()) {
                sum++;
            }
        }
        return sum;
    }
}
