package gui;

import javax.swing.*;
import java.lang.reflect.Method;

public class Application {
    public JPanel mainPanel;
    private JList extractorsList;
    private JRadioButton euclideanRadioButton;
    private JRadioButton chebyshevRadioButton;
    private JRadioButton radioButton1;
    private JSlider parameterSlider;
    private JLabel kvalue;
    private JFrame frame;

    public Application(JFrame frame) {
        this.frame = frame;
        try {
            fillExtractors();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        parameterSlider.addChangeListener(e -> {
            kvalue.setText(Integer.toString(parameterSlider.getValue()));
        });
    }

    private void fillExtractors() throws ClassNotFoundException {
        Class c = Class.forName("classification.features.Extraction");
        DefaultListModel model = new DefaultListModel();
        extractorsList.setModel(model);
        Method[] methods = c.getDeclaredMethods();

        for (int i = 0; i < methods.length; i++) {
            model.add(i, methods[i].getName());
        }
    }
}
