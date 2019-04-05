import interfaces.IClassificationObject;
import utils.Calculations;
import abstracts.Metric;
import utils.Group;
import utils.Operations;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Classification {
    private int truePositive = 0;
    private int k;
    private IClassificationObject[] learningSet;
    private IClassificationObject[] testingSet;
    private String[] dictionary = new String[] {};

    // splitRatio - learning and testing sets split ratio -> learning = objectsCount * splitRatio
    public Classification(IClassificationObject[] objects, double splitRatio, int k) {
        splitSets(objects, splitRatio);
        this.k = k;
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

    public void perform(Metric metric, String[] extractors) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Calculations comparator = new Calculations(metric, extractors);
        List<Group> groups = new ArrayList<>();
        truePositive = 0;

        for(IClassificationObject testObject : testingSet) {
            Map<IClassificationObject, Double> distances = new HashMap<>();

            for (IClassificationObject learningObject : learningSet) {
                double distance = comparator.compare(testObject.getVectorizedText(), learningObject.getVectorizedText(), dictionary);
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

    public void setDictionary(String[] dictionary) {
        this.dictionary = dictionary;
    }

    public IClassificationObject[] getLearningSet() {
        return learningSet;
    }

    public IClassificationObject[] getTestingSet() {
        return testingSet;
    }
}
