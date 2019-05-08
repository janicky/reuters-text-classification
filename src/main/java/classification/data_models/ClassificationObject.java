package classification.data_models;

import classification.features.IFeature;
import classification.utils.Operations;

import java.util.Map;

public abstract class ClassificationObject implements IClassificationObject {
    private String[] vectorizedText;
    private Map<String, IFeature> featuresVector;

    public ClassificationObject(String text) {
        String cleaned = Operations.removeSpecialCharacters(text);
        vectorizedText = Operations.splitText(cleaned);
    }

    public String[] getVectorizedText() {
        return vectorizedText;
    }

    public void setVectorizedText(String[] vectorizedText) {
        this.vectorizedText = vectorizedText;
    }

    public Map<String, IFeature> getFeaturesVector() {
        return featuresVector;
    }

    public void setFeaturesVector(Map<String, IFeature> featuresVector) {
        this.featuresVector = featuresVector;
    }

    public static final String[] availableModels = { "Article" };
}
