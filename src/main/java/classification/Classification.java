package classification;

import classification.data_models.IClassificationObject;
import classification.features.Extraction;
import classification.metrics.IMetric;
import classification.similarity.ISimilarityMeter;
import classification.utils.*;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Classification {
    private IClassificationObject[] objects;
    private IClassificationObject[] learningSet;
    private IClassificationObject[] testingSet;

    private int truePositive = 0;
    private int k;
    private String[] labels;

//    splitRatio - learning and testing sets split ratio -> learning = objectsCount * splitRatio
    public Classification(IClassificationObject[] objects) {
        this.objects = objects;
    }

    public void extractFeatures(String[] extractors, String[] keywords) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (IClassificationObject object : objects) {
            Extraction extraction = new Extraction(object.getVectorizedText(), keywords);
            for (String extractor : extractors) {
                Method method = extraction.getClass().getDeclaredMethod(extractor);
                method.invoke(extraction);
            }
            object.setFeaturesVector(extraction.getFeatures());
        }
    }

    public void perform(IMetric metric, ISimilarityMeter similarityMeter) {
        List<Group> groups = new ArrayList<>();
        truePositive = 0;

        int x = 0;
        for(IClassificationObject testObject : testingSet) {
            Map<IClassificationObject, Double> distances = new HashMap<>();

            for (IClassificationObject learningObject : learningSet) {
                double distance = metric.compare(testObject.getFeaturesVector(), learningObject.getFeaturesVector(), similarityMeter);
                distances.put(learningObject, distance);
            }
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

    public void setLearningSet(IClassificationObject[] learningSet) {
        this.learningSet = learningSet;
    }

    public void setTestingSet(IClassificationObject[] testingSet) {
        this.testingSet = testingSet;
    }
}
