package application;

import application.controller.Controller;
import application.model.ClassificationModel;
import application.view.MainView;

public class Application {
    public static void main(String[] args) {
        MainView view = new MainView("Text classification");
        ClassificationModel model = new ClassificationModel();
        Controller controller = new Controller(view, model);

        view.setVisible(true);
    }
}
