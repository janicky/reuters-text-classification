package application.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public MainView(String title) throws HeadlessException {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
    }
}
