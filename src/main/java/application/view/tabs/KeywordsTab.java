package application.view.tabs;

import javax.swing.*;

public class KeywordsTab {
    private JPanel mainPanel;
    private JLabel objectsCount;
    private JList keywordsList;
    private JFormattedTextField keywordInput;
    private JButton addKeywordButton;
    private JButton removeSelectedKeywordButton;
    private JSpinner keywordsSignificanceSpinner;
    private JButton generateKeywordsButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
