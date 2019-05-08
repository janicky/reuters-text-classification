package application.view.tabs;

import javax.swing.*;

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
}
