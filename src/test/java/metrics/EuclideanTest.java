package metrics;

import org.junit.jupiter.api.Test;
import utils.Feature;

import static org.junit.jupiter.api.Assertions.*;

class EuclideanTest {

    @Test
    void calculate() {
        Euclidean e = new Euclidean();
        e.addFeature(new Feature(3, 4));
        e.addFeature(new Feature(5, 7));
        assertTrue(Math.abs(2.23606797749979 - e.calculate()) < 0.0001);
    }
}