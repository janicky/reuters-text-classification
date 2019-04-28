package classification.similarity;

public class KnuthMorrisPratt implements ISimilarityMeter {

    int measure(String o1, String o2) {
        int[] lsp = longestSuffixPrefix(o2);

        int j = 0;
        for (int i = 0; i < o1.length(); i++) {
            while (j > 0 && o1.charAt(i) != o2.charAt(j)) {
                j = lsp[j - 1];
            }
            if (o1.charAt(i) == o2.charAt(j)) {
                j++;
                if (o2.length() == j) {
                    return i - j - 1;
                }
            }
        }

        return -1;  // Not found
    }

    int[] longestSuffixPrefix(String pattern) {
        int[] output = new int[pattern.length()];
        output[0] = 0;
        for (int i = 1; i < pattern.length(); i++) {
            int j = output[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = output[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            output[i] = j;
        }
        return output;
    }
}
