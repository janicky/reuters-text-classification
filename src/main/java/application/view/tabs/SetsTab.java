package application.view.tabs;

import classification.data_models.IClassificationObject;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public class SetsTab extends JPanel {
    private JPanel mainPanel;
    private JSlider splitRatioSlider;
    private JLabel learningObjectsCount;
    private JList learningObjects;
    private JList testingObjects;
    private JButton splitDataButton;
    private JLabel learningPercent;
    private JLabel testingPercent;
    private JLabel testingObjectsCount;

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

    public void addSplitDataButtonListener(ActionListener listener) {
        splitDataButton.addActionListener(listener);
    }

    public void setLearningObjects(IClassificationObject[] objects) {
        DefaultListModel listModel = new DefaultListModel();
        for (IClassificationObject object : objects) {
            listModel.addElement(object);
        }
        learningObjects.setModel(listModel);
        learningObjectsCount.setText(Integer.toString(objects.length));
    }
    public void setTestingObjects(IClassificationObject[] objects) {
        DefaultListModel listModel = new DefaultListModel();
        for (IClassificationObject object : objects) {
            listModel.addElement(object);
        }
        testingObjects.setModel(listModel);
        testingObjectsCount.setText(Integer.toString(objects.length));
    }
}
