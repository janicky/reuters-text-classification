package classification.metrics;

import classification.features.IFeature;
import classification.similarity.ISimilarityMeter;

import java.util.Map;

public class Chebyshev implements IMetric {

    public double compare(Map<String, IFeature> vector_1, Map<String, IFeature> vector_2, ISimilarityMeter similarityMeter) {
        double max = 0;

        for (Map.Entry<String, IFeature> feature_1 : vector_1.entrySet()) {
            IFeature feature_2 = vector_2.get(feature_1.getKey());
            double diff = Math.abs(feature_1.getValue().compareTo(feature_2, similarityMeter));
            if (diff > max) {
                max = diff;
            }
        }
        return max;
    }
}
