package application.view.tabs;

import classification.data_models.IClassificationObject;

import javax.swing.*;
import java.awt.event.ActionListener;

public class DataTab extends JPanel {
    private JPanel mainPanel;
    private JList objectsList;
    private JButton selectFilesButton;
    private JComboBox selectedModel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setAvailableModels(final String[] models) {
        selectedModel.setModel(new DefaultComboBoxModel(models));
    }

    public void addSelectedModelListener(ActionListener listener) {
        selectedModel.addActionListener(listener);
    }

    public void addSelectFilesButtonListener(ActionListener listener) {
        selectFilesButton.addActionListener(listener);
    }

    public void setObjects(IClassificationObject[] objects) {
        DefaultListModel list_model = new DefaultListModel();
        for (IClassificationObject o : objects) {
            list_model.addElement(o);
        }
        objectsList.setModel(list_model);
    }
}
