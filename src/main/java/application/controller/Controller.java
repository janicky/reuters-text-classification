package application.controller;

import application.model.ClassificationModel;
import application.view.View;

public class Controller {

    private View view;
    private ClassificationModel model;

    public Controller(View view, ClassificationModel model) {
        this.view = view;
        this.model = model;
    }
}
