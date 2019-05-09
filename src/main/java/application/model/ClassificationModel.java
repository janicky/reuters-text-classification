package application.model;

import application.model.exceptions.InvalidParserException;
import classification.data_models.IClassificationObject;
import classification.data_models.IParser;
import classification.utils.Loader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ClassificationModel {

    private final String[] availableModels = { "Article" };
    private int selectedModel = 0;
    private IClassificationObject[] objects;

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
    }

    public Object[][] getObjectsInfo() {
        return new Object[][] {
                { "Objects count", objects.length }
        };
    }
}
