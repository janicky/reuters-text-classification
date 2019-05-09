package application.view.tabs;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public class SetsTab extends JPanel {
    private JPanel mainPanel;
    private JSlider splitRatioSlider;
    private JLabel objectsCount;
    private JList learningObjects;
    private JList testingObjects;
    private JButton splitDataButton;
    private JLabel learningPercent;
    private JLabel testingPercent;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setLearningPercent(int learningRatio) {
        learningPercent.setText(Math.ceil(learningRatio) + "%");
    }
    public void setTestingPercent(int testingRatio) {
        testingPercent.setText(Math.ceil(testingRatio) + "%");
    }

    public void addSplitRatioSliderListener(ChangeListener listener) {
        splitRatioSlider.addChangeListener(listener);
    }
}
