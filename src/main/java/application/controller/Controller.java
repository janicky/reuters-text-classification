package application.controller;

import application.model.ClassificationModel;
import application.model.exceptions.InvalidParserException;
import application.view.MainView;
import application.view.tabs.DataTab;
import application.view.tabs.FilterTab;
import application.view.tabs.SetsTab;
import classification.data_models.IClassificationObject;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Controller {

    private MainView view;
    private DataTab dataTab;
    private FilterTab filterTab;
    private SetsTab setsTab;
    private ClassificationModel model;
    private JFileChooser chooser;

    public Controller(MainView view, ClassificationModel model) {
        this.view = view;
        this.model = model;

        initializeDataTab();
        initializeFilterTab();
        initializeSetsTab();
    }

    private void initializeDataTab() {
        dataTab = new DataTab();
        view.addTab("Load data", dataTab.getMainPanel());
        dataTab.setAvailableModels(model.getAvailableModels());
        dataTab.addSelectedModelListener(e -> onSelectModel(e));
        dataTab.addSelectFilesButtonListener(e -> onSelectFiles());
    }

    private void initializeFilterTab() {
        filterTab = new FilterTab();
        view.addTab("Filter objects", filterTab.getMainPanel());
        filterTab.addLabelsListSelectionListener(e -> onLabelsSelect(e));
    }

    private void initializeSetsTab() {
        setsTab = new SetsTab();
        view.addTab("Create sets", setsTab.getMainPanel());
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
                dataTab.setLabels(model.getLabels());
                dataTab.updateInfo(model.getObjectsInfo());
                filterTab.setLabels(model.getLabels());
                filterTab.setObjects(loaded_objects);
            } catch (InvalidParserException e) {
                view.displayError("Invalid parser. Selected model hasn`t parser.");
            } catch (IOException e) {
                view.displayError("File loading error.");
            }
        }
    }

    private void onLabelsSelect(ListSelectionEvent event) {
        JList source = (JList) event.getSource();
        if (source.getValueIsAdjusting()) {
            int[] selectedIndices = source.getSelectedIndices();
            String[] selectedLabels = model.getLabels(selectedIndices);
            filterTab.setSelectedLabels(selectedLabels);
        }
    }
}
