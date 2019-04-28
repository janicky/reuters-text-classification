package classification.similarity;

public class TermFrequency implements ISimilarityMeter {
    public static double termFrequency(String[] text, String term) {
        int count = 0;
        if (text.length == 0) {
            return 0;
        }
        for (String word : text) {
            if (word.equalsIgnoreCase(term)) {
                count++;
            }
        }
        return count / (double)text.length;
    }

    public static double inverseFrequency(String[][] documents, String term) {
        double count = 0;
        for (String[] document : documents) {
            for (String word : document) {
                if (word.equalsIgnoreCase(term)) {
                    count++;
                    break;
                }
            }
        }
        if (count == 0) {
            return 0;
        }
        return Math.log(documents.length / count);
    }

    public static double termFrequencyInverseFrequency(String[][] documents, String[] text, String term) {
        return termFrequency(text, term) * inverseFrequency(documents, term);
    }
}
