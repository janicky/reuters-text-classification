package application.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JTabbedPane mainPane;
    private JPanel mainPanel;

    public MainView(String title) throws HeadlessException {
        setTitle(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        Center window
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
    }

    public void addTab(String title, JPanel panel) {
        mainPane.addTab(title, panel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public void displayInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.PLAIN_MESSAGE);
    }
}
