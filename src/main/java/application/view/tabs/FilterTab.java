package application.view.tabs;

import classification.data_models.IClassificationObject;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;

public class FilterTab {
    private JPanel mainPanel;
    private JList labelsList;
    private JList objectsList;
    private JRadioButton matchAnyRadioButton;
    private JRadioButton matchOnlyRadioButton;
    private JRadioButton matchOnlyOneRadioButton;
    private JButton filterObjectsButton;
    private JList selectedLabelsList;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setLabels(String[] labels) {
        DefaultListModel listModel = new DefaultListModel();
        for (String label : labels) {
            listModel.addElement(label);
        }
        labelsList.setModel(listModel);
    }
    public void setSelectedLabels(String[] labels) {
        DefaultListModel listModel = new DefaultListModel();
        for (String label : labels) {
            listModel.addElement(label);
        }
        selectedLabelsList.setModel(listModel);
    }
    public void setObjects(IClassificationObject[] objects) {
        DefaultListModel listModel = new DefaultListModel();
        for (IClassificationObject object : objects) {
            listModel.addElement(object);
        }
        objectsList.setModel(listModel);
    }

    public void addLabelsListSelectionListener(ListSelectionListener listener) {
        labelsList.addListSelectionListener(listener);
    }

    public void addFilterOptionSelectListener(ActionListener listener) {
        matchAnyRadioButton.addActionListener(listener);
        matchOnlyRadioButton.addActionListener(listener);
        matchOnlyOneRadioButton.addActionListener(listener);
    }
}
