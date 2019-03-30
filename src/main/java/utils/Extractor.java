package utils;

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
}
