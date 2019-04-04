package interfaces;

import utils.Feature;

public interface Metric {

    double calculate();
    void addFeature(Feature feature);
}
