package application.controller;

import application.model.ClassificationModel;
import application.view.MainView;

public class Controller {

    private MainView view;
    private ClassificationModel model;

    public Controller(MainView view, ClassificationModel model) {
        this.view = view;
        this.model = model;
    }
}
