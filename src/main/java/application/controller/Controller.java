package application.controller;

import application.model.ClassificationModel;
import application.view.MainView;
import application.view.tabs.DataTab;

public class Controller {

    private MainView view;
    private ClassificationModel model;

    public Controller(MainView view, ClassificationModel model) {
        this.view = view;
        this.model = model;

        DataTab dt = new DataTab();
        view.addTab("Data", dt.getMainPanel());
    }
}
