package utils;

import abstracts.Metric;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Comparator {
    private String[] extractors;
    private Metric metric;

    public Comparator(Metric metric, String[] extractors) {
        this.metric = metric;
        this.extractors = extractors;
    }

    public double compare(String[] o1, String[] o2, String[] dictionary) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class extractor = Class.forName("Extractor");

        for (int i = 0; i < extractors.length; i++) {
            Method method = extractor.getDeclaredMethod(extractors[i], String[].class, String[].class);
            double e1 = (double) method.invoke(dictionary, o1);
            double e2 = (double) method.invoke(dictionary, o2);
            metric.addFeature(new Feature(e1, e2));
        }

        return metric.calculate();
    }
}
