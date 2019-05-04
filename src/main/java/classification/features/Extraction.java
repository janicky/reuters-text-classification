package classification.features;

import classification.helpers.ExtractionHelper;

import java.util.LinkedHashMap;
import java.util.Map;

public class Extraction {

    private String[] text;
    private String[] keywords;
    private Map<String, Feature> features = new LinkedHashMap<>();

    public Extraction(String[] text, String[] keywords) {
        this.text = text;
        this.keywords = keywords;
    }

    public void keywordOccurrences() {
        Map<String, Integer> occurrences = ExtractionHelper.getKeywordsOccurrences(text, keywords);
        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            features.put("ko_" + entry.getKey(), new NumberFeature(entry.getValue()));
        }
    }

    public void keywordOccurrencesCount() {
        Map<String, Integer> keywordsOccurrences = ExtractionHelper.getKeywordsOccurrences(text, keywords);
        int occurrencesCount = keywordsOccurrences.values().stream().reduce(0, Integer::sum);
        features.put("keywords_occurrences_count", new NumberFeature(occurrencesCount));
    }

    public void keywordsDensity() {
        if (text.length == 0) {
            features.put("density", new NumberFeature(0d));
            return;
        }

        Map<String, Integer> keywordsOccurrences = ExtractionHelper.getKeywordsOccurrences(text, keywords);
        int occurrencesSum = keywordsOccurrences.values().stream().reduce(0, Integer::sum);
        double density = occurrencesSum / text.length;
        features.put("keywords_density", new NumberFeature(density));
    }

    public void keywordsPosition() {
        for (String keyword : keywords) {
            for (int i = 0; i < text.length; i++) {
                if (keyword.equals(text[i])) {
                    features.put("kp_" + keyword, new NumberFeature(i));
                }
            }
        }
    }

    public void keywordsAveragePosition() {
        for (String keyword : keywords) {
            int position = 0;
            int i = 0;
            for (; i < text.length; i++) {
                if (keyword.equals(text[i])) {
                    position += i;
                }
            }
            double averageDistance = (i > 0 ? position / (double)i : 0);
            features.put("kad_" + keyword, new NumberFeature(averageDistance));
        }
    }

    public void firstKeyword() {
        for (String keyword : keywords) {
            for (String word : text) {
                if (keyword.equals(word)) {
                    features.put("first_keyword", new TextFeature(keyword));
                    return;
                }
            }
        }
        features.put("first_keyword", new TextFeature(null));
    }

    public void mostFrequentKeyword() {
        Map<String, Integer> occurrences = ExtractionHelper.getKeywordsOccurrences(text, keywords);
        String feature_keyword = null;
        int max = 0;

        for (Map.Entry<String, Integer> keyword : occurrences.entrySet()) {
            if (keyword.getValue() > max) {
                feature_keyword = keyword.getKey();
                max = keyword.getValue();
            }
        }

        features.put("most_frequent_keyword", new TextFeature(feature_keyword));
    }

    public void wordsCount() {
        features.put("wordsCount", new NumberFeature(text.length));
    }

    public Map<String, Feature> getFeatures() {
        return features;
    }
}
