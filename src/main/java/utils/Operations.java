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
        for (String word : text) {
            for (String dict : dictionary) {
                if (word.equals(dict)) {
                    if (map.containsKey(word)) {
                        map.put(word, map.get(word) + 1);
                    } else {
                        map.put(word, 1);
                    }
                }
            }
        }
        return map;
    }
}
