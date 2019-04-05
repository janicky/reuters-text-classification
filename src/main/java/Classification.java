import interfaces.ClassificationObject;
import utils.Comparator;
import abstracts.Metric;
import utils.Operations;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Classification {
    private int truePositive = 0;
    private int k;
    private ClassificationObject[] learningSet;
    private ClassificationObject[] testingSet;
    private String[] dictionary;

    // splitRatio - learning and testing sets split ratio -> learning = objectsCount * splitRatio
    public Classification(ClassificationObject[] objects, double splitRatio, int k) {
        splitSets(objects, splitRatio);
        this.k = k;
    }

    private void splitSets(ClassificationObject[] objects, double splitRatio) {
        int learningSetSize = (int) Math.ceil(objects.length * splitRatio);
        int testingSetSize = objects.length - learningSetSize;

        learningSet = new ClassificationObject[learningSetSize];
        testingSet = new ClassificationObject[testingSetSize];

        for (int i = 0; i < learningSetSize; i++) {
            learningSet[i] = objects[i];
        }
        for (int i = 0; i < testingSetSize; i++) {
            testingSet[i] = objects[learningSetSize + i];
        }
    }

    public void perform(Metric metric, String[] extractors) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Comparator comparator = new Comparator(metric, extractors);

        for(ClassificationObject testObject : testingSet) {
            SortedMap<ClassificationObject, Double> distances = new TreeMap<>();

            for (ClassificationObject learningObject : learningSet) {
                String[] vectorized_1 = Operations.splitText(Operations.normalizeText(testObject.getText()));
                String[] vectorized_2 = Operations.splitText(Operations.normalizeText(learningObject.getText()));
                double distance = comparator.compare(vectorized_1, vectorized_2, dictionary);
                distances.put(learningObject, distance);
            }

            TreeMap<ClassificationObject, Double> sorted = new TreeMap<>();
            sorted.putAll(distances);

            for (int i = sorted.size() - 1; i > sorted.size() - 1 - k; i--) {
//                to do
            }
        }
    }

    public void setDictionary(String[] dictionary) {
        this.dictionary = dictionary;
    }

    public ClassificationObject[] getLearningSet() {
        return learningSet;
    }

    public ClassificationObject[] getTestingSet() {
        return testingSet;
    }
}
