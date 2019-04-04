package utils;

import java.util.HashMap;
import java.util.Map;

public class Extractor {
    public static double occurrencesCountExtraction(String[] dictionary, String[] text) {
        int occurrences = 0;

        for (String dict : dictionary) {
            for (String word : text) {
                if (word.equals(dict)) {
                    occurrences++;
                    break;
                }
            }
        }
        return (double) occurrences;
    }

    public static double occurrencesSumExtraction(String[] dictionary, String[] text) {
        int occurrences = 0;

        for (String dict : dictionary) {
            for (String word : text) {
                if (word.equals(dict)) {
                    occurrences++;
                }
            }
        }
        return (double) occurrences;
    }

    public static double densityExtraction(String[] dictionary, String[] text) {
        double occurrences = occurrencesSumExtraction(dictionary, text);
        if (text.length == 0) {
            return 0;
        }
        return occurrences / (double) text.length;
    }

    public static double averageDistanceExtraction(String[] dictionary, String[] text) {
        int sum = 0;
        int occurrences = 0;
        for (String dict : dictionary) {
            for (int i = 0; i < text.length; i++) {
                if (text[i].equals(dict)) {
                    sum += i;
                    occurrences++;
                }
            }
        }

        if (occurrences == 0) {
            return 0;
        }
        return sum / (double) occurrences;
    }

    public static double wordsCountExtraction(String[] dictionary, String[] text) {
        return text.length;
    }

    public static double wordsDistractionExtraction(String[] dictionary, String[] text) {
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

        return ((words - 1) > 0 ? (sum / (double) text.length) / (double)(words - 1) : 0);
    }
}
