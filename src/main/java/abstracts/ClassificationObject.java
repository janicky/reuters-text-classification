package abstracts;

import interfaces.IClassificationObject;
import utils.Operations;

public abstract class ClassificationObject implements IClassificationObject {
    public String[] getVectorizedText() {
        String normalized = Operations.normalizeText(getText());
        return Operations.splitText(normalized);
    }
}
