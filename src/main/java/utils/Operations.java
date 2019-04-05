package utils;

import interfaces.IClassificationObject;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

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
}
