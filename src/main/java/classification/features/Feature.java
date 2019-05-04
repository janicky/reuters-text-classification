package classification.features;

public abstract class Feature {
    public enum Measure { DISTANCE, SIMILARITY }
    public Measure measure = Measure.DISTANCE;
}
