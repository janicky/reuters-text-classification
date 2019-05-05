package classification.data_models;

import classification.utils.Operations;

public abstract class ClassificationObject implements IClassificationObject {
    public String[] getVectorizedText() {
        return Operations.splitText(getText());
    }
}
