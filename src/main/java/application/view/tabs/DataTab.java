package application.view.tabs;

import classification.data_models.IClassificationObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class DataTab extends JPanel {
    private JPanel mainPanel;
    private JList objectsList;
    private JButton selectFilesButton;
    private JComboBox selectedModel;
    private JTable objectsInfo;
    private JList labelsList;
    private DefaultTableModel tableModel;

    public DataTab() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Value");
    }

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
        DefaultListModel listModel = new DefaultListModel();
        for (IClassificationObject o : objects) {
            listModel.addElement(o);
        }
        objectsList.setModel(listModel);
    }

    public void setLabels(String[] labels) {
        DefaultListModel listModel = new DefaultListModel();
        for (String label : labels) {
            listModel.addElement(label);
        }
        labelsList.setModel(listModel);
    }

    public void updateInfo(Object[][] info) {
        tableModel.getDataVector().removeAllElements();
        for (Object[] row : info) {
            tableModel.addRow(row);
        }
        objectsInfo.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }

    private void createUIComponents() {
        objectsInfo = new JTable(tableModel);
    }
}
