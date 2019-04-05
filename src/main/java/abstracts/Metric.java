package abstracts;

import utils.Feature;

import java.util.ArrayList;
import java.util.List;

public abstract class Metric implements interfaces.Metric {
    protected List<Feature> features = new ArrayList<>();
    public void addFeature(Feature feature) {
        features.add(feature);
    }
}
