package classification.data_models;

public interface IClassificationObject {
    String getText();
    String[] getLabels();
    String[] getVectorizedText();
    void setVectorizedText(String[] vectorizedText);
}
