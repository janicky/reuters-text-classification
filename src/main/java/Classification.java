import interfaces.ClassificationObject;

public class Classification {
    private int truePositive = 0;
    private ClassificationObject[] learningSet;
    private ClassificationObject[] testingSet;

    // splitRatio - learning and testing sets split ratio -> learning = objectsCount * splitRatio
    public Classification(ClassificationObject[] objects, double splitRatio) {
        splitSets(objects, splitRatio);
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
}
