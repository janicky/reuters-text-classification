package utils;

import java.util.Arrays;
import java.util.Map;

public class Extractor {
    public static boolean[] binaryExtraction(String[] dictionary, String[] text) {
        boolean[] output = new boolean[dictionary.length];

        for (int i = 0; i < dictionary.length; i++) {
            boolean found = false;
            for (String word : text) {
                if (word.equals(dictionary[i])) {
                    found = true;
                    break;
                }
            }
            output[i] = found;
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
