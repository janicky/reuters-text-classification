package utils;

import interfaces.IClassificationObject;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Operations {
    public static String normalizeText(final String text) {
        return text.trim().replaceAll("[^A-Za-z\\s+]", "").toLowerCase();
    }

    public static String[] splitText(final String text) {
        return text.split("\\s+");
    }

    public static Map<String, Integer> wordsOccurrences(String[] dictionary, String[] text) {
        Map<String, Integer> map = new HashMap<>();
        for (String dict : dictionary) {
            boolean found = false;
            for (String word : text) {
                if (word.equals(dict)) {
                    found = true;
                    map.put(dict, (map.containsKey(dict) ? map.get(word) + 1 : 1));
                }
            }
            if (!found) {
                map.put(dict, 0);
            }
        }
        return map;
    }

    public static IClassificationObject[] selectObjects(SortedMap<IClassificationObject, Double> objects, int k) {
        IClassificationObject[] selected = new IClassificationObject[k];
        int i = 0;
        for (Map.Entry<IClassificationObject, Double> o : objects.entrySet()) {
            selected[i++] = o.getKey();
            if (i >= k - 1) {
                return selected;
            }
        }
        return selected;
    }

    public static String selectLabel(IClassificationObject[] objects) {
        Map<String, Integer> labelsOccurrences = new HashMap<>();
        String max_label = null;
        int max_count = 0;

        for (IClassificationObject o : objects) {
            for (String label : o.getLabels()) {
                if (labelsOccurrences.containsKey(label)) {
                    int current = labelsOccurrences.get(label);
                    labelsOccurrences.put(label, current + 1);
                } else {
                    labelsOccurrences.put(label, 1);
                }

                int element = labelsOccurrences.get(label);
                if (element > max_count) {
                    max_count = element;
                    max_label = label;
                }
            }
        }

        return max_label;
    }

    public static boolean checkLabel(String selectedLabel, String[] labels) {
        for (String label : labels) {
            if (label.equals(selectedLabel)) {
                return true;
            }
        }
        return false;
    }

}
