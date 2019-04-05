import interfaces.IClassificationObject;
import utils.Comparator;
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
    private String[] dictionary;

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
        Comparator comparator = new Comparator(metric, extractors);
        List<Group> groups = new ArrayList<>();

        for(IClassificationObject testObject : testingSet) {
            SortedMap<IClassificationObject, Double> distances = new TreeMap<>(Collections.reverseOrder());

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
        }
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
