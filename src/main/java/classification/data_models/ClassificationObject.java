package classification.data_models;

import classification.features.IFeature;
import classification.utils.Operations;

import java.util.Map;
import java.util.Objects;

public abstract class ClassificationObject implements IClassificationObject {
    private String[] vectorizedText;
    private Map<String, IFeature> featuresVector;

    private static int index = 0;
    private int id;

    public ClassificationObject(String text) {
        String cleaned = Operations.removeSpecialCharacters(text);
        vectorizedText = Operations.splitText(cleaned);
        id = index++;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassificationObject that = (ClassificationObject) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
