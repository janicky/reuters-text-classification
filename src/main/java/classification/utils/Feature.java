package classification.utils;

public class Feature {
    private double difference;

    public Feature(int x, int y) {
        difference = (y - x);
    }

    public Feature(double x, double y) {
        difference = (y - x);
    }

    public double getDifference() {
        return difference;
    }
}
