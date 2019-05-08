package application.controller;

import application.model.ClassificationModel;
import application.model.exceptions.InvalidParserException;
import application.view.MainView;
import application.view.tabs.DataTab;
import classification.data_models.IClassificationObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Controller {

    private MainView view;
    private DataTab dataTab;
    private ClassificationModel model;
    private JFileChooser chooser;

    public Controller(MainView view, ClassificationModel model) {
        this.view = view;
        this.model = model;

        initializeDataTab();
    }

    private void initializeDataTab() {
        dataTab = new DataTab();
        view.addTab("Data", dataTab.getMainPanel());
        dataTab.setAvailableModels(model.getAvailableModels());
        dataTab.addSelectedModelListener(e -> onSelectModel(e));
        dataTab.addSelectFilesButtonListener(e -> onSelectFiles());
    }

    private void onSelectModel(ActionEvent event) {
        JComboBox source = (JComboBox) event.getSource();
        model.setSelectedModel(source.getSelectedIndex());
    }

    private void onSelectFiles() {
        chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int decision = chooser.showOpenDialog(view.getMainPanel());
        if (decision == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = chooser.getSelectedFiles();
            try {
                model.loadObjects(selectedFiles);
                IClassificationObject[] loaded_objects = model.getObjects();
                dataTab.setObjects(loaded_objects);
                view.displayInfo("Loaded objects: " + loaded_objects.length);
            } catch (InvalidParserException e) {
                view.displayError("Invalid parser. Selected model hasn`t parser.");
            } catch (IOException e) {
                view.displayError("File loading error.");
            }
        }
    }
}
