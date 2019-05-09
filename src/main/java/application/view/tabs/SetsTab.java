package application.view.tabs;

import javax.swing.*;

public class SetsTab extends JPanel {
    private JPanel mainPanel;
    private JSlider slider1;
    private JLabel objectsCount;
    private JList learningObjects;
    private JList testingObjects;
    private JButton splitDataButton;
    private JLabel learningPercent;
    private JLabel testingPercent;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setLearningPercent(double learningRatio) {
        learningPercent.setText(Math.ceil(learningRatio * 100) + "%");
    }
    public void setTestingPercent(double testingRatio) {
        testingPercent.setText(Math.ceil(testingRatio * 100) + "%");
    }
}
