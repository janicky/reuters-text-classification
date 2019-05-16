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
    private Object[][] results;

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
            extraction.normalize();
            object.setFeaturesVector(extraction.getFeatures());
        }
    }

    public void perform(IMetric metric, ISimilarityMeter similarityMeter) {
        List<Group> groups = new ArrayList<>();
        Map<String, Integer> truePositiveLabels = new HashMap<>();
        Map<String, Integer> truePositiveCounts = new HashMap<>();
        truePositive = 0;

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
                if (truePositiveLabels.containsKey(selectedLabel)) {
                    truePositiveLabels.put(selectedLabel, truePositiveLabels.get(selectedLabel) + 1);
                } else {
                    truePositiveLabels.put(selectedLabel, 1);
                }
            }
            if (truePositiveCounts.containsKey(selectedLabel)) {
                truePositiveCounts.put(selectedLabel, truePositiveCounts.get(selectedLabel) + 1);
            } else {
                truePositiveCounts.put(selectedLabel, 1);
            }

            Object[][] results = new Object[truePositiveCounts.size()][];
            int i = 0;
            for (Map.Entry<String, Integer> entry : truePositiveCounts.entrySet()) {
                int entry_tp = (truePositiveLabels.containsKey(entry.getKey()) ? truePositiveLabels.get(entry.getKey()) : 0);
                double acc = getAccuracy(entry_tp, entry.getValue());
                results[i++] = new Object[] { entry.getKey(), Math.round(acc * 100d) + "%", entry_tp + "/" + entry.getValue() };
            }

            this.results = results;
        }
    }

    public double getAccuracy() {
        return getAccuracy(truePositive, testingSet.length);
    }

    private double getAccuracy(int tp, int count) {
        if (count == 0) {
            return 0;
        }
        return tp / (double) count;
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

    public Object[][] getResults() {
        return results;
    }
}
