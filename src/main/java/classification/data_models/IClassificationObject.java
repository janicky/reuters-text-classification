package classification.data_models;

import classification.features.IFeature;

import java.util.Map;

public interface IClassificationObject {
    String getText();
    String[] getLabels();
    String[] getVectorizedText();
    void setVectorizedText(String[] vectorizedText);
    Map<String, IFeature> getFeaturesVector();
    void setFeaturesVector(Map<String, IFeature> featuresVector);
}
