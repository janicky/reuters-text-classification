package classification.features;

public class Feature {
    private double difference, x, y;

    public Feature(int x, int y) {
        difference = (y - x);
    }

    public Feature(double x, double y) {
        difference = (y - x);
    }

    public double getDifference() {
        return difference;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
