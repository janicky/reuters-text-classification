package classification.utils;

import classification.features.Extraction;
import classification.features.Feature;
import classification.metrics.Metric;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class Calculations {
    private String[] extractors;
    private Metric metric;

    public Calculations(Metric metric, String[] extractors) {
        this.metric = metric;
        this.extractors = extractors;
    }

    public double compare(String[] o1, String[] o2, String[] dictionary) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Extraction extractor1 = new Extraction(o1, dictionary);
        Extraction extractor2 = new Extraction(o2, dictionary);

        for (int i = 0; i < extractors.length; i++) {
            Method method1 = extractor1.getClass().getDeclaredMethod(extractors[i]);
            Method method2 = extractor2.getClass().getDeclaredMethod(extractors[i]);
            method1.invoke(extractor1);
            method2.invoke(extractor2);
        }

        for (Map.Entry<String, Double> feature : extractor1.getFeatures().entrySet()) {
            Double val1 = feature.getValue();
            Double val2 = extractor2.getFeatures().get(feature.getKey());
            metric.addFeature(new Feature(val1, val2));
        }

        return metric.calculate();
    }
}
