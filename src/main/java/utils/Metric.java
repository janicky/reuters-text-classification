package utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Metric implements interfaces.Metric {
    protected List<Feature> features = new ArrayList<>();
    void addFeature(Feature feature) {
        features.add(feature);
    }
}
