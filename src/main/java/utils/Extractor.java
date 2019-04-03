package utils;

import java.util.HashMap;
import java.util.Map;

public class Extractor {
    public static Map binaryExtraction(String[] dictionary, String[] text) {
        Map<String, Integer> output = new HashMap<>();

        for (String dict : dictionary) {
            boolean found = false;
            for (String word : text) {
                if (word.equals(dict)) {
                    found = true;
                }
            }
            output.put(dict, (found ? 1 : 0));
        }
        return output;
    }

    public static Map occurrencesExtraction(String[] dictionary, String[] text) {
        Map<String, Integer> output = new HashMap<>();

        for (String dict : dictionary) {
            int occurrences = 0;
            for (String word : text) {
                if (word.equals(dict)) {
                    occurrences++;
                }
            }
            output.put(dict, occurrences);
        }
        return output;
    }

    public static Map densityExtraction(String[] dictionary, String[] text) {
        Map<String, Integer> output = new HashMap<>();
        Map<String, Integer> occurrences = occurrencesExtraction(dictionary, text);

        for (String dict : dictionary) {
            double density = occurrences.get(dict).intValue() / (double)text.length;
            output.put(dict, (int) (density * 100));
        }
        return output;
    }

    public static Map distanceExtraction(String[] dictionary, String[] text) {
        Map<String, Integer> output = new HashMap<>();

        for (String dict : dictionary) {
            for (int i = 0; i < text.length; i++) {
                if (text[i].equals(dict)) {
                    output.put(dict, i);
                    break;
                }
            }
        }
        return output;
    }

    public static Map wordsCountExtraction(String[] dictionary, String[] text) {
        Map<String, Integer> output = new HashMap<>();
        output.put("count", text.length);
        return output;
    }

    public static Map wordsDistractionExtraction(String[] dictionary, String[] text) {
        Map<String, Double> output = new HashMap<>();
        int start = -1, sum = 0, words = 0;

        for (int i = 0; i < text.length; i++) {
            for (String dict : dictionary) {
                if (text[i].equals(dict)) {
                    if (start != -1) {
                        sum += i - start;
                    }
                    ++words;
                    start = i;
                    break;
                }
            }
        }

        double distraction = ((words - 1) > 0 ? (sum / (double) text.length) / (double)(words - 1) : text.length);
        output.put("distraction", distraction);

        return output;
    }
}
