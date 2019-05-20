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
    private JSpinner keywordsCountSpinner;
    private JRadioButton significanceRadioButton;
    private JRadioButton countRadioButton;
    private JPanel countPanel;
    private JPanel significancePanel;
    private JRadioButton tfidfRadioButton;
    private JRadioButton tfRadioButton;
    private DefaultListModel listModel = new DefaultListModel();

    public KeywordsTab() {
        keywordsCountSpinner.setModel(new SpinnerNumberModel(5, 1, Integer.MAX_VALUE, 1));
        keywordsSignificanceSpinner.setModel(new SpinnerNumberModel(0.5, 0.0, 999999.0, 0.1));
        countPanel.setVisible(false);
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
    public void addKeywordsCountSpinnerListener(ChangeListener listener) {
        keywordsCountSpinner.addChangeListener(listener);
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

    public void clearKeywordInput() {
        keywordInput.setText("");
    }

    public void addKeywordsExtractionTypeListener(ActionListener listener) {
        significanceRadioButton.addActionListener(listener);
        countRadioButton.addActionListener(listener);
    }
    public void addKeywordsExtractorListener(ActionListener listener) {
        tfidfRadioButton.addActionListener(listener);
        tfRadioButton.addActionListener(listener);
    }

    public void updateControls(int type) {
        countPanel.setVisible(type == 1);
        significancePanel.setVisible(type == 0);
    }
}
