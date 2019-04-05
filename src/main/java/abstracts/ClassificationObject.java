package abstracts;

import utils.Operations;

public abstract class ClassificationObject implements interfaces.ClassificationObject {
    public String[] getVectorizedText() {
        String normalized = Operations.normalizeText(getText());
        return Operations.splitText(normalized);
    }
}
