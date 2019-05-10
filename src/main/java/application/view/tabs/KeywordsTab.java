package application.view.tabs;

import javax.swing.*;

public class KeywordsTab {
    private JPanel mainPanel;
    private JLabel objectsCount;
    private JList keywordsList;
    private JFormattedTextField keywordInput;
    private JButton addKeywordButton;
    private JButton removeSelectedKeywordButton;
    private JSpinner significanceSpinner;
    private JButton generateButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
