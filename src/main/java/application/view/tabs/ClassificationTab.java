package application.view.tabs;

import javax.swing.*;

public class ClassificationTab {
    private JPanel mainPanel;
    private JCheckBox learningSetCheckBox;
    private JComboBox comboBox1;
    private JPanel metricComboBox;
    private JComboBox similarityComboBox;
    private JSlider kSlider;
    private JLabel kLabel;
    private JButton startClassificationButton;
    private JCheckBox testingSetCheckBox;
    private JCheckBox keywordsCheckBox;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setLearningSetCheckBoxSelected(boolean enabled) {
        learningSetCheckBox.setSelected(enabled);
    }
    public void setTestingSetCheckBoxSelected(boolean enabled) {
        testingSetCheckBox.setSelected(enabled);
    }
    public void setKeywordsCheckBoxSelected(boolean enabled) {
        keywordsCheckBox.setSelected(enabled);
    }
}
