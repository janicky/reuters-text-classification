package utils;

import java.util.HashMap;
import java.util.Map;

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
}
