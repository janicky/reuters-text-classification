package classification.metrics;

import classification.features.IFeature;
import classification.similarity.ISimilarityMeter;

import java.util.Map;

public class Manhattan implements IMetric {
    public double compare(Map<String, IFeature> vector_1, Map<String, IFeature> vector_2, ISimilarityMeter similarityMeter) {
        double sum = 0;

        for (Map.Entry<String, IFeature> feature_1 : vector_1.entrySet()) {
            IFeature feature_2 = vector_2.get(feature_1.getKey());
            sum += Math.abs(feature_1.getValue().compareTo(feature_2, similarityMeter));
        }

        return sum;
    }
}
