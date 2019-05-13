package application.view.tabs;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class KeywordsTab {
    private JPanel mainPanel;
    private JLabel objectsCount;
    private JList keywordsList;
    private JTextField keywordInput;
    private JButton addKeywordButton;
    private JButton removeSelectedKeywordButton;
    private JSpinner keywordsSignificanceSpinner;
    private JButton generateKeywordsButton;
    private JButton importButton;
    private JButton exportButton;
    private DefaultListModel listModel = new DefaultListModel();

    public KeywordsTab() {
        keywordsSignificanceSpinner.setModel(new SpinnerNumberModel(0.5, 0.0, 999999.0, 0.1));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void addGenerateKeywordsButtonListener(ActionListener listener) {
        generateKeywordsButton.addActionListener(listener);
    }

    public void addAddKeywordButtonListener(ActionListener listener) {
        addKeywordButton.addActionListener(listener);
    }

    public void addRemoveSelectedKeywordButtonListener(ActionListener listener) {
        removeSelectedKeywordButton.addActionListener(listener);
    }

    public void addKeywordsSignificanceSpinnerListener(ChangeListener listener) {
        keywordsSignificanceSpinner.addChangeListener(listener);
    }

    public void addImportButtonListener(ActionListener listener) {
        importButton.addActionListener(listener);
    }
    public void addExportButtonListener(ActionListener listener) {
        exportButton.addActionListener(listener);
    }

    public void addKeywordsListListener(ListSelectionListener listener) {
        keywordsList.addListSelectionListener(listener);
    }

    public void setKeywords(String[] keywords) {
        listModel = new DefaultListModel();
        for (String keyword : keywords) {
            listModel.addElement(keyword);
        }
        keywordsList.setModel(listModel);
        objectsCount.setText(Integer.toString(keywords.length));
        updateButtons();
    }

    public JTextField getKeywordInput() {
        return keywordInput;
    }

    public void addKeyword(String keyword) {
        listModel.addElement(keyword);
        keywordsList.setModel(listModel);
        updateButtons();
    }

    public void setRemoveSelectedButtonEnabled(boolean state) {
        removeSelectedKeywordButton.setEnabled(state);
    }

    public void removeKeywords(int[] indices) {
        List<String> toRemove = new ArrayList<>();
        for (int index : indices) {
            toRemove.add((String) listModel.get(index));
        }

        for (String element : toRemove) {
            listModel.removeElement(element);
        }

        keywordsList.setModel(listModel);
        updateButtons();
    }

    private void updateButtons() {
        exportButton.setEnabled(listModel.getSize() != 0);
    }
}
