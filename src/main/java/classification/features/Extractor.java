package classification.features;

import java.util.LinkedHashMap;
import java.util.Map;

public class Extractor {

    private String[] text;
    private String[] dictionary;
    private Map<String, Double> features = new LinkedHashMap<>();

    public Extractor(String[] text, String[] dictionary) {
        this.text = text;
        this.dictionary = dictionary;
    }

    public Map occurrencesCountExtraction() {
        Map<String, Double> occurrencesCount = new LinkedHashMap<>();

        for (String dict : dictionary) {
            int occurrences = 0;
            for (String word : text) {
                if (word.equals(dict)) {
                    occurrences++;
                }
            }
            occurrencesCount.put(dict, (double)occurrences);
            features.put(dict, (double)occurrences);
        }
        return occurrencesCount;
    }

    public Map occurrencesSumExtraction() {
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
        features.put("occurrencesSum", occurrences);
        return occurrencesSum;
    }

    public Map densityExtraction() {
        Map<String, Double> result = new LinkedHashMap<>();
        Map<String, Double> occSum = occurrencesSumExtraction();
        if (text.length == 0) {
            result.put("density", 0d);
            return result;
        }
        double density = occSum.get("occurrencesSum") / text.length;
        result.put("density", density);
        features.put("density", density);
        return result;
    }

    public Map averageDistanceExtraction() {
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

        if (occurrences == 0) {
            averageDistance.put("distance", 0d);
            features.put("distance", 0d);
            return averageDistance;
        }

        double distance = sum / occurrences;
        averageDistance.put("distance", distance);
        features.put("distance", distance);

        return averageDistance;
    }

    public Map wordsCountExtraction() {
        Map<String, Double> wordsCount = new LinkedHashMap<>();
        wordsCount.put("wordsCount", (double)text.length);
        features.put("wordsCount", (double)text.length);
        return wordsCount;
    }

    public Map wordsDistractionExtraction() {
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
        double distraction = (words - 1) > 0 ? (sum / text.length) / (words - 1) : 0;
        wordsDistraction.put("wordsDistraction", distraction);
        features.put("wordsDistraction", distraction);
        return wordsDistraction;
    }
}
