package application;

import application.controller.Controller;
import application.model.ClassificationModel;
import application.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new View();
        ClassificationModel model = new ClassificationModel();
        Controller controller = new Controller(view, model);
    }
}
