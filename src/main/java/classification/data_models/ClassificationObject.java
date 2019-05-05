package classification.data_models;

import classification.utils.Operations;

public abstract class ClassificationObject implements IClassificationObject {
    public String[] getVectorizedText() {
        String cleaned = Operations.removeSpecialCharacters(getText());
        return Operations.splitText(cleaned);
    }
}
