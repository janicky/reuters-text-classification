package application.view.tabs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;

public class ClassificationTab {
    private JPanel mainPanel;
    private JCheckBox learningSetCheckBox;
    private JComboBox metricComboBox;
    private JComboBox similarityComboBox;
    private JSlider kSlider;
    private JLabel kLabel;
    private JButton startClassificationButton;
    private JCheckBox testingSetCheckBox;
    private JCheckBox keywordsCheckBox;
    private JList extractorsList;
    private JCheckBox extractorsCheckBox;
    private JButton extractFeaturesButton;
    private JLabel accuracy;
    private JCheckBox featuresExtractedCheckBox;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMetrics(String[] metrics) {
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        for (String metric : metrics) {
            comboBoxModel.addElement(metric);
        }
        metricComboBox.setModel(comboBoxModel);
        kSlider.addChangeListener(e -> updateKLabel(e));
    }

    public void setSimilarity(String[] similarity) {
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        for (String sim : similarity) {
            comboBoxModel.addElement(sim);
        }
        similarityComboBox.setModel(comboBoxModel);
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
    public void setExtractorsCheckBoxSelected(boolean enabled) {
        extractorsCheckBox.setSelected(enabled);
    }
    public void setFeaturesExtractedCheckBoxSelected(boolean enabled) {
        featuresExtractedCheckBox.setSelected(enabled);
    }

    public void addMetricComboBoxListener(ActionListener listener) {
        metricComboBox.addActionListener(listener);
    }
    public void addSimilarityComboBoxListener(ActionListener listener) {
        similarityComboBox.addActionListener(listener);
    }

    public void addKSliderListener(ChangeListener listener) {
        kSlider.addChangeListener(listener);
    }

    public void addExtractFeaturesButtonListener(ActionListener listener) {
        extractFeaturesButton.addActionListener(listener);
    }
    public void addStartClassificationButtonListener(ActionListener listener) {
        startClassificationButton.addActionListener(listener);
    }

    private void updateKLabel(ChangeEvent event) {
        JSlider source = (JSlider) event.getSource();
        kLabel.setText(Integer.toString(source.getValue()));
    }

    public void setExtractors(String[] extractors) {
        DefaultListModel listModel = new DefaultListModel();
        for (String extractor : extractors) {
            listModel.addElement(extractor);
        }
        extractorsList.setModel(listModel);
    }

    public void addExtractorsListListener(ListSelectionListener listener) {
        extractorsList.addListSelectionListener(listener);
    }

    public void setAccuracy(double accuracy) {
        double percents = Math.round(accuracy * 100d);
        this.accuracy.setText(Double.toString(percents) + "%");
    }
}
