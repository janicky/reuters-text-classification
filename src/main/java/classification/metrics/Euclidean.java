package classification.metrics;

import classification.features.IFeature;

import java.util.Map;

public class Euclidean implements IMetric {

    public double compare(Map<String, IFeature> vector_1, Map<String, IFeature> vector_2) {
        double sum = 0;

        for (Map.Entry<String, IFeature> feature_1 : vector_1.entrySet()) {
            IFeature feature_2 = vector_2.get(feature_1.getKey());
            sum += feature_1.getValue().compareTo(feature_2) * feature_1.getValue().compareTo(feature_2);
        }

        return Math.sqrt(sum);
    }
}
