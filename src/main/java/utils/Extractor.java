package utils;

import java.util.Arrays;
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

    public static double[] densityExtraction(String[] dictionary, String[] text) {
        double[] output = new double[dictionary.length];
        Map<String, Integer> occurrences = Operations.wordsOccurrences(dictionary, text);

        if (text.length > 0) {
            for (int i = 0; i < dictionary.length; i++) {
                output[i] = occurrences.get(dictionary[i]) / (double) text.length;
            }
        } else {
            Arrays.fill(output, 0);
        }
        return output;
    }
}
