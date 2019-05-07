package classification;

import classification.data_models.IClassificationObject;
import classification.features.Extraction;
import classification.metrics.IMetric;
import classification.utils.*;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Classification {
    private IClassificationObject[] objects;
    private IClassificationObject[] filteredObjects;
    private IClassificationObject[] learningSet;
    private IClassificationObject[] testingSet;
    private StopWords stopWords;
    private Keywords keywords;

    private int truePositive = 0;
    private int k;
    private String[] labels;

//    splitRatio - learning and testing sets split ratio -> learning = objectsCount * splitRatio
    public Classification(IClassificationObject[] objects) {
        this.objects = objects;
        this.filteredObjects = objects;
        stopWords = new StopWords(objects);
        keywords = new Keywords(objects);
    }

//    Use objects only with specified labels
    public void filterObjects(String[] labels, int mode) {
        filteredObjects = Operations.filterObjects(objects, labels, mode);
    }

//    Split data sets
    public void splitSets(double splitRatio) {
        splitSets(filteredObjects, splitRatio);
    }

//     Load stop words from file
    public void loadStopWords(String filename) throws FileNotFoundException {
        stopWords.loadFromFile(filename);
    }

//     Stemming and remove stop words
    public void prepareData() {
        Operations.stem(filteredObjects);
        stopWords.removeStopWords();
    }

//     Generate keywords
    public void generateKeywords(double significance) {
        keywords.generate(significance);
    }

    public void extractFeatures(String[] extractors) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (IClassificationObject object : filteredObjects) {
            Extraction extraction = new Extraction(object.getVectorizedText(), keywords.getKeywords());
            for (String extractor : extractors) {
                Method method = extraction.getClass().getDeclaredMethod(extractor);
                method.invoke(extraction);
            }
            object.setFeaturesVector(extraction.getFeatures());
        }
    }

    private void splitSets(IClassificationObject[] objects, double splitRatio) {
        int learningSetSize = (int) Math.ceil(objects.length * splitRatio);
        int testingSetSize = objects.length - learningSetSize;

        learningSet = new IClassificationObject[learningSetSize];
        testingSet = new IClassificationObject[testingSetSize];

        for (int i = 0; i < learningSetSize; i++) {
            learningSet[i] = objects[i];
        }
        for (int i = 0; i < testingSetSize; i++) {
            testingSet[i] = objects[learningSetSize + i];
        }
    }

    public void perform(IMetric metric) {
        List<Group> groups = new ArrayList<>();
        truePositive = 0;

        int x = 0;
        for(IClassificationObject testObject : testingSet) {
            Map<IClassificationObject, Double> distances = new HashMap<>();

            for (IClassificationObject learningObject : learningSet) {
                double distance = metric.compare(testObject.getFeaturesVector(), learningObject.getFeaturesVector());
                distances.put(learningObject, distance);
            }
            System.out.println("Testing object #" + x++);
            IClassificationObject[] selectedObjects = Operations.selectObjects(distances, k);
            String selectedLabel = Operations.selectLabel(selectedObjects);
            Group selectedGroup = groups.stream().filter(e -> e.getLabel() == selectedLabel).findFirst().orElse(null);
            if (selectedGroup == null) {
                selectedGroup = new Group(selectedLabel);
                groups.add(selectedGroup);
            }
            selectedGroup.addObject(testObject);

            if (Operations.checkLabel(selectedLabel, testObject.getLabels())) {
                truePositive++;
            }
        }
    }



    public double getAccuracy() {
        if (testingSet.length == 0) {
            return 0;
        }
        return truePositive / (double) testingSet.length;
    }

    public IClassificationObject[] getLearningSet() {
        return learningSet;
    }

    public IClassificationObject[] getTestingSet() {
        return testingSet;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }
}
