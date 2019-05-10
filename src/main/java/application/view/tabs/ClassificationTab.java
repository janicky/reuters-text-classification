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

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
