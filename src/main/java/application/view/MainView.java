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
        setSize(800, 600);
    }
}
