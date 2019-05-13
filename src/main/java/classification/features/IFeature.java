package classification.features;

import classification.similarity.ISimilarityMeter;

public interface IFeature<T> {
    T getValue();
    double compareTo(IFeature<T> feature, ISimilarityMeter similarityMeter);
}
