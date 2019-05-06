package classification.metrics;

import classification.features.IFeature;
import classification.features.NumberFeature;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EuclideanTest {

    @Test
    void calculate() {
        Euclidean e = new Euclidean();
        Map<String, IFeature> features_1 = Map.ofEntries(
          Map.entry("length", new NumberFeature(4))
        );
        Map<String, IFeature> features_2 = Map.ofEntries(
          Map.entry("length", new NumberFeature(3))
        );

        assertTrue(Math.abs(1.0 - e.compare(features_1, features_2)) < 0.0001);
    }
}