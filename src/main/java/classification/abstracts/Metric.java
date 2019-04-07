package classification.abstracts;

import classification.interfaces.IMetric;
import classification.utils.Feature;

import java.util.ArrayList;
import java.util.List;

public abstract class Metric implements IMetric {
    protected List<Feature> features = new ArrayList<>();
    public void addFeature(Feature feature) {
        features.add(feature);
    }
}
