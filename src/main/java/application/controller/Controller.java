package application.controller;

import application.model.ClassificationModel;
import application.model.exceptions.InvalidParserException;
import application.view.MainView;
import application.view.tabs.*;
import classification.Classification;
import classification.data_models.IClassificationObject;
import classification.metrics.IMetric;
import classification.utils.Keywords;
import classification.utils.Operations;
import classification.utils.StopWords;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private MainView view;
    private DataTab dataTab;
    private FilterTab filterTab;
    private SetsTab setsTab;
    private StopWordsTab stopWordsTab;
    private KeywordsTab keywordsTab;
    private ClassificationTab classificationTab;
    private ClassificationModel model;
    private JFileChooser chooser;

    public Controller(MainView view, ClassificationModel model) {
        this.view = view;
        this.model = model;

        initializeDataTab();
        initializeFilterTab();
        initializeSetsTab();
        initializeStopWordsTab();
        initializeKeywordsTab();
        initializeClassificationTab();
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
        filterTab.setSelectedLabels(model.getDefaultLabels());
        model.setSelectedLabels(model.getDefaultLabels());
        filterTab.addFilterOptionSelectListener(e -> onFilterOptionSelect(e));
        filterTab.addFilterObjectsButtonListener(e -> onFilterObjects());
    }

    private void initializeSetsTab() {
        setsTab = new SetsTab();
        view.addTab("Create sets", setsTab.getMainPanel());
        setsTab.setLearningPercent(model.getLearningRatio());
        setsTab.setTestingPercent(model.getTestingRatio());
        setsTab.addSplitRatioSliderListener(e -> onSplitRatioChange(e));
        setsTab.addSplitDataButtonListener(e -> onSplitDataButtonClick());
    }

    private void initializeStopWordsTab() {
        stopWordsTab = new StopWordsTab();
        view.addTab("Stop words", stopWordsTab.getMainPanel());
        stopWordsTab.addSignificanceSpinnerListener(e -> onSignificanceChange(e));
        stopWordsTab.addLoadFromFileButtonListener(e -> onStopWordsLoadFromFile());
        stopWordsTab.addGenerateButtonListener(e -> onStopWordsGenerate());
        stopWordsTab.addRemoveStopWordsListener(e -> onStopWordsRemove());
    }

    private void initializeKeywordsTab() {
        keywordsTab = new KeywordsTab();
        view.addTab("Keywords", keywordsTab.getMainPanel());
        keywordsTab.addKeywordsSignificanceSpinnerListener(e -> onKeywordsSignificanceChange(e));
        keywordsTab.addGenerateKeywordsButtonListener(e -> onKeywordsGenerate());
        keywordsTab.addAddKeywordButtonListener(e -> onAddKeyword());
        keywordsTab.addKeywordsListListener(e -> onKeywordsListSelection(e));
        keywordsTab.addRemoveSelectedKeywordButtonListener(e -> onRemoveSelectedKeyword());
    }

    private void initializeClassificationTab() {
        classificationTab = new ClassificationTab();
        view.addTab("Classification", classificationTab.getMainPanel());
        classificationTab.setMetrics(model.getAvailableMetrics());
        classificationTab.setSimilarity(model.getAvailableSimilarity());
        classificationTab.addMetricComboBoxListener(e -> onMetricChange(e));
        classificationTab.addSimilarityComboBoxListener(e -> onSimilarityChange(e));
        classificationTab.addKSliderListener(e -> onKParameterChange(e));
        classificationTab.setExtractors(model.getAvailableExtractors());
        classificationTab.addExtractorsListListener(e -> onExtractorsSelect(e));
        classificationTab.addExtractFeaturesButtonListener(e -> onExtractFeatures());
        classificationTab.addStartClassificationButtonListener(e -> onStartClassification());
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

                if (model.getSelectedLabels() != null && model.getSelectedLabels().length > 0) {
                    String[] labels = model.getSelectedLabels();
                    int mode = model.getFilterOption();
                    IClassificationObject[] filtered = Operations.filterObjects(loaded_objects, labels, mode);
                    filterTab.setObjects(filtered);
                    model.setFilteredObjects(filtered);
                } else {
                    filterTab.setObjects(loaded_objects);
                    model.setFilteredObjects(loaded_objects);
                }
                onSplitDataButtonClick();
                classificationTab.setFeaturesExtractedCheckBoxSelected(false);

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
            model.setSelectedLabels(selectedLabels);
        }
    }

    private void onFilterOptionSelect(ActionEvent event) {
        JRadioButton radioButton = (JRadioButton) event.getSource();
        String name = radioButton.getActionCommand();
        if (name.equals("Match any")) {
            model.setFilterOption(0);
        } else if (name.equals("Match only")) {
            model.setFilterOption(1);
        } else {
            model.setFilterOption(2);
        }
    }

    private void onFilterObjects() {
        IClassificationObject[] objects = model.getObjects();
        String[] labels = model.getSelectedLabels();
        int mode = model.getFilterOption();
        try {
            if (objects == null || objects.length == 0) {
                throw new Exception("No objects were found.");
            }
            if (labels == null || labels.length == 0) {
                throw new Exception("No labels selected.");
            }
            IClassificationObject[] filtered = Operations.filterObjects(objects, labels, mode);
            model.setFilteredObjects(filtered);
            filterTab.setObjects(filtered);
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onSplitRatioChange(ChangeEvent event) {
        JSlider slider = (JSlider) event.getSource();
        int value = slider.getValue();
        model.setSplitRatio(value / 100.0);
        setsTab.setLearningPercent(model.getLearningRatio());
        setsTab.setTestingPercent(model.getTestingRatio());
    }

    private void onSplitDataButtonClick() {
        try {
            IClassificationObject[] objects = model.getFilteredObjects();
            if (objects == null || objects.length == 0) {
                throw new Exception("No filtered objects were found.");
            }
            double splitRatio = model.getSplitRatio();
            IClassificationObject[][] sets = Operations.splitSets(objects, splitRatio);
            model.setLearningObjects(sets[0]);
            model.setTestingObjects(sets[1]);
            setsTab.setLearningObjects(sets[0]);
            setsTab.setTestingObjects(sets[1]);
            updateClassificationRequirements();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onSignificanceChange(ChangeEvent event) {
        JSpinner source = (JSpinner) event.getSource();
        model.setSignificance((double) source.getValue());
    }

    private void onStopWordsLoadFromFile() {
        chooser = new JFileChooser();

        StopWords stopWords = model.getStopWords();
        if (stopWords == null) {
            view.displayError("Filtered objects not found.");
            return;
        }
        int decision = chooser.showOpenDialog(view.getMainPanel());
        if (decision == JFileChooser.APPROVE_OPTION) {
            String selectedFile = chooser.getSelectedFile().getPath();
            try {
                stopWords.loadFromFile(selectedFile);
                stopWordsTab.setStopWords(stopWords.getStopWords());
            } catch (FileNotFoundException e) {
                view.displayError("Couldn't load stop words from file.");
            }
        }
    }

    private void onStopWordsGenerate() {
        StopWords stopWords = model.getStopWords();
        if (stopWords == null) {
            view.displayError("Filtered objects not found.");
            return;
        } else {
            double significance = model.getSignificance();
            stopWords.generate(significance);
            stopWordsTab.setStopWords(stopWords.getStopWords());
        }
    }

    private void onStopWordsRemove() {
        StopWords stopWords = model.getStopWords();
        try {
            if (stopWords == null) {
                throw new Exception("Filtered objects not found.");
            }
            if (stopWords.getStopWords().length == 0) {
                throw new Exception("First load or generate stop words.");
            }
            stopWords.removeStopWords();
            view.displayInfo("Stop words have been removed from existing objects.");
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onKeywordsSignificanceChange(ChangeEvent event) {
        JSpinner source = (JSpinner) event.getSource();
        double value = (double) source.getValue();
        model.setKeywordsSignificance(value);
    }

    private void onKeywordsGenerate() {
        IClassificationObject[] learningObjects = model.getLearningObjects();
        Keywords keywords = model.getKeywordsUtil();
        try {
            if (learningObjects == null || learningObjects.length == 0) {
                throw new Exception("Learning objects not found.");
            }
            if (keywords == null) {
                throw new Exception("Keywords not initialized.");
            }
            keywords.generate(model.getKeywordsSignificance());
            model.setKeywords(keywords.getKeywords());
            keywordsTab.setKeywords(keywords.getKeywords());
            updateClassificationRequirements();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onAddKeyword() {
        String keywordInput = keywordsTab.getKeywordInput().getText();
        try {
            if (!model.isValidKeyword(keywordInput)) {
               throw new Exception("Invalid keyword.");
            }
            if (model.keywordsContains(keywordInput)) {
                throw new Exception("Keyword `" + keywordInput + "` already exists.");
            }
            model.addKeyword(keywordInput);
            keywordsTab.addKeyword(keywordInput);
            updateClassificationRequirements();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    public void onKeywordsListSelection(ListSelectionEvent event) {
        JList source = (JList) event.getSource();
        if (source.getValueIsAdjusting()) {
            int selectedIndex = source.getSelectedIndex();
            model.setSelectedKeyword(selectedIndex);
            keywordsTab.setRemoveSelectedButtonEnabled(selectedIndex != -1);
        }
    }

    public void onRemoveSelectedKeyword() {
        try {
            int index = model.getSelectedKeyword();
            if (index == -1) {
                throw new Exception("Keyword not selected.");
            }
            model.removeKeyword(index);
            keywordsTab.removeKeyword(index);
            updateClassificationRequirements();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void updateClassificationRequirements() {
        classificationTab.setLearningSetCheckBoxSelected(model.getLearningObjects() != null && model.getLearningObjects().length > 0);
        classificationTab.setTestingSetCheckBoxSelected(model.getTestingObjects() != null && model.getTestingObjects().length > 0);
        classificationTab.setKeywordsCheckBoxSelected(model.getKeywords() != null && model.getKeywords().length > 0);
        classificationTab.setExtractorsCheckBoxSelected(model.getExtractors() != null && model.getExtractors().length > 0);
    }

    private void onMetricChange(ActionEvent event) {
        JComboBox source = (JComboBox) event.getSource();
        int selected = source.getSelectedIndex();
        model.setMetric(model.getMetrics()[selected]);
    }

    private void onSimilarityChange(ActionEvent event) {
        JComboBox source = (JComboBox) event.getSource();
        int selected = source.getSelectedIndex();
        model.setSimilarityMeter(model.getSimilarityMeters()[selected]);
    }

    private void onKParameterChange(ChangeEvent event) {
        JSlider source = (JSlider) event.getSource();
        model.setK(source.getValue());
    }

    private void onExtractorsSelect(ListSelectionEvent event) {
        JList source = (JList) event.getSource();
        int[] selectedItems = source.getSelectedIndices();

        String[] extractors = model.getAvailableExtractors();
        List<String> selectedExtractors = new ArrayList<>();

        for (int item : selectedItems) {
            selectedExtractors.add(extractors[item]);
        }

        model.setExtractors(selectedExtractors.toArray(new String[selectedExtractors.size()]));
        updateClassificationRequirements();
    }

    private void onExtractFeatures() {
        try {
            Classification classification = model.getClassification();
            String[] keywords = model.getKeywords();
            String[] extractors = model.getExtractors();
            if (classification == null || model.getObjects().length == 0) {
                throw new Exception("First load objects.");
            }
            if (keywords == null || keywords.length == 0) {
                throw new Exception("Keywords not found.");
            }
            if (extractors == null || extractors.length == 0) {
                throw new Exception("Please select extractors.");
            }
            classification.extractFeatures(extractors, keywords);
            model.setFeaturesExtracted(true);
            classificationTab.setFeaturesExtractedCheckBoxSelected(true);
            view.displayInfo("Features have been extracted!");
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onStartClassification() {
        try {
            Classification classification = model.getClassification();
            IClassificationObject[] learningObjects = model.getLearningObjects();
            IClassificationObject[] testingObjects = model.getTestingObjects();

            if (classification == null) {
                throw new Exception("First load objects.");
            }
            if (learningObjects == null || learningObjects.length == 0) {
                throw new Exception("Learning objects not found.");
            }
            if (testingObjects == null || testingObjects.length == 0) {
                throw new Exception("Testing objects not found.");
            }
            if (!model.isFeaturesExtracted()) {
                throw new Exception("First extract features.");
            }
            classification.setK(model.getK());
            classification.perform(model.getMetric());
            classificationTab.setAccuracy(classification.getAccuracy());
            view.displayInfo("Classification finished!");
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }
}
