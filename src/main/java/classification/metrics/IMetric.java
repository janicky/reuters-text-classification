package classification.metrics;

import classification.features.IFeature;
import classification.similarity.ISimilarityMeter;

import java.util.Map;

public interface IMetric {
    double compare(Map<String, IFeature> vector_1, Map<String, IFeature> vector_2, ISimilarityMeter similarityMeter);
}
