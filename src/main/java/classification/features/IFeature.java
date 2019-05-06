package classification.features;

public interface IFeature<T> {
    T getValue();
    double compareTo(IFeature<T> feature);
}
