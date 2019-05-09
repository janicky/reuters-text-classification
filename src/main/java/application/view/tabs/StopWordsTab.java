package application.view.tabs;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public class StopWordsTab {
    private JPanel mainPanel;
    private JList stopWordsList;
    private JButton loadFromFileButton;
    private JButton generateButton;
    private JSpinner significanceSpinner;

    public StopWordsTab() {
        significanceSpinner.setModel(new SpinnerNumberModel(0.5, 0.0, 999999.0, 0.1));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void addSignificanceSpinnerListener(ChangeListener listener) {
        significanceSpinner.addChangeListener(listener);
    }
}
