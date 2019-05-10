package application.view.tabs;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class KeywordsTab {
    private JPanel mainPanel;
    private JLabel objectsCount;
    private JList keywordsList;
    private JTextField keywordInput;
    private JButton addKeywordButton;
    private JButton removeSelectedKeywordButton;
    private JSpinner keywordsSignificanceSpinner;
    private JButton generateKeywordsButton;
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
    }

    public JTextField getKeywordInput() {
        return keywordInput;
    }

    public void addKeyword(String keyword) {
        listModel.addElement(keyword);
        keywordsList.setModel(listModel);
    }

    public void setRemoveSelectedButtonEnabled(boolean state) {
        removeSelectedKeywordButton.setEnabled(state);
    }

    public void removeKeyword(int index) {
        listModel.remove(index);
        keywordsList.setModel(listModel);
    }
}
