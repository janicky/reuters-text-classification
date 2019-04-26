package classification.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Extractor {
    public static Map occurrencesCountExtraction(String[] dictionary, String[] text) {
        Map<String, Double> occurrencesCount = new LinkedHashMap<>();

        for (String dict : dictionary) {
            int occurrences = 0;
            for (String word : text) {
                if (word.equals(dict)) {
                    occurrences++;
                }
            }
            occurrencesCount.put(dict, (double)occurrences);
        }
        return occurrencesCount;
    }

    public static Map occurrencesSumExtraction(String[] dictionary, String[] text) {
        Map<String, Double> occurrencesSum = new LinkedHashMap<>();
        double occurrences = 0;

        for (String dict : dictionary) {
            for (String word : text) {
                if (word.equals(dict)) {
                    occurrences++;
                }
            }
        }
        occurrencesSum.put("occurrencesSum", occurrences);
        return occurrencesSum;
    }

    public static Map densityExtraction(String[] dictionary, String[] text) {
        Map<String, Double> result = new LinkedHashMap<>();
        Map<String, Double> occSum = occurrencesSumExtraction(dictionary, text);
        if (text.length == 0) {
            result.put("density", 0d);
            return result;
        }
        result.put("density", (occSum.get("occurrencesSum") / text.length));
        return result;
    }

    public static Map averageDistanceExtraction(String[] dictionary, String[] text) {
        Map<String, Double> averageDistance = new LinkedHashMap<>();
        double sum = 0;
        double occurrences = 0;
        for (String dict : dictionary) {
            for (int i = 0; i < text.length; i++) {
                if (text[i].equals(dict)) {
                    sum += i;
                    occurrences++;

                }
            }
        }
        averageDistance.put("distance", sum/occurrences);

        if (occurrences == 0) {
            averageDistance.put("distance", 0d);
            return averageDistance;
        }
        return averageDistance;
    }

    public static Map wordsCountExtraction(String[] dictionary, String[] text) {
        Map<String, Double> wordsCount = new LinkedHashMap<>();
        wordsCount.put("wordsCount", (double)text.length);
        return wordsCount;
    }

    public static Map wordsDistractionExtraction(String[] dictionary, String[] text) {
        Map<String, Double> wordsDistraction = new LinkedHashMap<>();
        double start = -1, sum = 0, words = 0;

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
        wordsDistraction.put("wordsDistraction", (words - 1) > 0 ? (sum / text.length) / (double)(words - 1) : 0);
        return wordsDistraction;
    }
}
