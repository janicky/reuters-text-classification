package application.view.tabs;

import classification.data_models.IClassificationObject;

import javax.swing.*;

public class FilterTab {
    private JPanel mainPanel;
    private JList labelsList;
    private JList objectsList;

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
    public void setObjects(IClassificationObject[] objects) {
        DefaultListModel listModel = new DefaultListModel();
        for (IClassificationObject object : objects) {
            listModel.addElement(object);
        }
        objectsList.setModel(listModel);
    }
}
