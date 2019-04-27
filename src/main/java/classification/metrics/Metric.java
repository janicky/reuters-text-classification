package classification.metrics;

import classification.features.Feature;

import java.util.ArrayList;
import java.util.List;

public abstract class Metric implements IMetric {
    protected List<Feature> features = new ArrayList<>();
    public void addFeature(Feature feature) {
        features.add(feature);
    }
}
