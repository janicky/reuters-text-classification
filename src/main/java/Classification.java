import interfaces.ClassificationObject;
import utils.Metric;

public class Classification {
    private int truePositive = 0;
    private int k;
    private ClassificationObject[] learningSet;
    private ClassificationObject[] testingSet;

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

    public void perform(Metric metric) {
        for(ClassificationObject object : testingSet) {

        }
    }

    public ClassificationObject[] getLearningSet() {
        return learningSet;
    }

    public ClassificationObject[] getTestingSet() {
        return testingSet;
    }
}
