package application.model;

import application.model.exceptions.InvalidParserException;
import classification.data_models.IClassificationObject;
import classification.data_models.IParser;
import classification.utils.Keywords;
import classification.utils.Loader;
import classification.utils.StopWords;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassificationModel {

    private final String[] availableModels = { "Article" };
    private int selectedModel = 0;
    private IClassificationObject[] objects;
    private IClassificationObject[] filteredObjects;
    private IClassificationObject[] learningObjects;
    private IClassificationObject[] testingObjects;
    private StopWords stopWords;
    private Keywords keywordsUtil;
    private String[] labels;
    private final String[] defaultLabels = { "west-germany", "usa", "france", "uk", "canada", "japan" };
    private String[] selectedLabels;
    private int filterOption = 2;
    private double splitRatio = 0.6;
    private double significance = 0.5;
    private double keywordsSignificance = 0.5;
    private List<String> keywords = new ArrayList<>();
    private int selectedKeyword = -1;

    public String[] getAvailableModels() {
        return availableModels;
    }

    public int getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(int selectedModel) {
        this.selectedModel = selectedModel;
    }

    public IClassificationObject[] getObjects() {
        return objects;
    }

    public void setObjects(IClassificationObject[] objects) {
        this.objects = objects;
    }

    public IClassificationObject[] getFilteredObjects() {
        return filteredObjects;
    }

    public void setFilteredObjects(IClassificationObject[] filteredObjects) {
        this.filteredObjects = filteredObjects;
        stopWords = new StopWords(filteredObjects);
    }

    private IParser getParserBySelectedModel() throws InvalidParserException {
        try {
            Class c = Class.forName("classification.data_models." + availableModels[selectedModel] + "Parser");
            Constructor constructor = c.getConstructor();
            return (IParser) constructor.newInstance();
        } catch (Exception e) {
            throw new InvalidParserException();
        }
    }

    public void loadObjects(File[] files) throws InvalidParserException, IOException {
        List<IClassificationObject> objectsList = new ArrayList<>();
        IParser parser = getParserBySelectedModel();

        for (File file : files) {
            IClassificationObject[] loadedObjects = Loader.loadFromFile(file, parser);
            for (IClassificationObject o : loadedObjects) {
                objectsList.add(o);
            }
        }

        objects = objectsList.toArray(new IClassificationObject[objectsList.size()]);
        loadLabels();
    }

    private void loadLabels() {
        HashMap<String, Integer> labels = new HashMap<>();
        for (IClassificationObject object : objects) {
            for (String label : object.getLabels()) {
                labels.put(label, 1);
            }
        }

        String[] output = new String[labels.size()];
        int i = 0;
        for (String key : labels.keySet()) {
            output[i++] = key;
        }

        this.labels = output;
    }

    public Object[][] getObjectsInfo() {
        return new Object[][] {
                { "Objects count", objects.length },
                { "Labels", getLabels().length }
        };
    }

    public String[] getLabels() {
        return labels;
    }

    public String[] getLabels(int[] indices) {
        String[] output = new String[indices.length];
        int i = 0;
        for (int index : indices) {
            output[i++] = labels[index];
        }
        return output;
    }

    public String[] getSelectedLabels() {
        return selectedLabels;
    }

    public void setSelectedLabels(String[] selectedLabels) {
        this.selectedLabels = selectedLabels;
    }

    public String[] getDefaultLabels() {
        return defaultLabels;
    }

    public int getFilterOption() {
        return filterOption;
    }

    public void setFilterOption(int filterOption) {
        this.filterOption = filterOption;
    }

    public double getSplitRatio() {
        return splitRatio;
    }

    public void setSplitRatio(double splitRatio) {
        this.splitRatio = splitRatio;
    }

    public int getLearningRatio() {
        return (int)(splitRatio * 100);
    }

    public int getTestingRatio() {
        return (int)((1 - splitRatio) * 100);
    }

    public void setLearningObjects(IClassificationObject[] learningObjects) {
        this.learningObjects = learningObjects;
        keywordsUtil = new Keywords(learningObjects);
    }

    public void setTestingObjects(IClassificationObject[] testingObjects) {
        this.testingObjects = testingObjects;
    }

    public double getSignificance() {
        return significance;
    }

    public void setSignificance(double significance) {
        this.significance = significance;
    }

    public StopWords getStopWords() {
        return stopWords;
    }

    public double getKeywordsSignificance() {
        return keywordsSignificance;
    }

    public void setKeywordsSignificance(double keywordsSignificance) {
        this.keywordsSignificance = keywordsSignificance;
    }

    public Keywords getKeywordsUtil() {
        return keywordsUtil;
    }

    public IClassificationObject[] getLearningObjects() {
        return learningObjects;
    }

    public IClassificationObject[] getTestingObjects() {
        return testingObjects;
    }

    public String[] getKeywords() {
        return keywords.toArray(new String[keywords.size()]);
    }

    public boolean keywordsContains(String keyword) {
        return keywords.contains(keyword);
    }

    public boolean isValidKeyword(String keyword) {
        return keyword.matches("^([A-Za-z0-9]+){3,}$");
    }

    public void setKeywords(String[] keywords) {
        this.keywords = new ArrayList<>();
        for (String keyword : keywords) {
            this.keywords.add(keyword);
        }
    }

    public void addKeyword(String keyword) {
        keywords.add(keyword);
    }

    public int getSelectedKeyword() {
        return selectedKeyword;
    }

    public void setSelectedKeyword(int selectedKeyword) {
        this.selectedKeyword = selectedKeyword;
    }

    public void removeKeyword(int index) {
        keywords.remove(index);
    }
}
