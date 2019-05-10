package application.view.tabs;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public class KeywordsTab {
    private JPanel mainPanel;
    private JLabel objectsCount;
    private JList keywordsList;
    private JFormattedTextField keywordInput;
    private JButton addKeywordButton;
    private JButton removeSelectedKeywordButton;
    private JSpinner keywordsSignificanceSpinner;
    private JButton generateKeywordsButton;
    private DefaultListModel listModel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void addGenerateKeywordsButtonListener(ActionListener listener) {
        generateKeywordsButton.addActionListener(listener);
    }

    public void addKeywordButtonListener(ActionListener listener) {
        addKeywordButton.addActionListener(listener);
    }

    public void addRemoveSelectedKeywordButtonListener(ActionListener listener) {
        removeSelectedKeywordButton.addActionListener(listener);
    }

    public void addKeywordsSignificanceSpinnerListener(ChangeListener listener) {
        keywordsSignificanceSpinner.addChangeListener(listener);
    }

    public void setKeywords(String[] keywords) {
        listModel = new DefaultListModel();
        for (String keyword : keywords) {
            listModel.addElement(keyword);
        }
        keywordsList.setModel(listModel);
        objectsCount.setText(Integer.toString(keywords.length));
    }
}
