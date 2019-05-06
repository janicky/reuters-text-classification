package classification.data_models;

import classification.utils.Operations;

public abstract class ClassificationObject implements IClassificationObject {
    private String[] vectorizedText;

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
}
