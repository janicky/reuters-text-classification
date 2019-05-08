package application;

import application.controller.Controller;
import application.model.Model;
import application.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
    }
}
