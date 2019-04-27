package classification.abstracts;

import classification.data_models.IClassificationObject;
import classification.utils.Operations;

public abstract class ClassificationObject implements IClassificationObject {
    public String[] getVectorizedText() {
        String normalized = Operations.normalizeText(getText());
        return Operations.splitText(normalized);
    }
}
