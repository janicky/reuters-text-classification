package application.view.tabs;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public class StopWordsTab {
    private JPanel mainPanel;
    private JList stopWordsList;
    private JButton loadFromFileButton;
    private JButton generateButton;
    private JSpinner significanceSpinner;
    private JProgressBar progressBar;

    public StopWordsTab() {
        significanceSpinner.setModel(new SpinnerNumberModel(0.5, 0.0, 999999.0, 0.1));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void addSignificanceSpinnerListener(ChangeListener listener) {
        significanceSpinner.addChangeListener(listener);
    }

    public void addLoadFromFileButtonListener(ActionListener listener) {
        loadFromFileButton.addActionListener(listener);
    }
    public void addGenerateButtonListener(ActionListener listener) {
        generateButton.addActionListener(listener);
    }

    public void setStopWords(String[] stopWords) {
        DefaultListModel listModel = new DefaultListModel();
        for (String word : stopWords) {
            listModel.addElement(word);
        }
        stopWordsList.setModel(listModel);
    }
}
