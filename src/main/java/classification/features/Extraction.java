package classification.features;

import classification.helpers.ExtractionHelper;

import java.util.LinkedHashMap;
import java.util.Map;

public class Extraction {

    private String[] text;
    private String[] keywords;
    private Map<String, IFeature> features = new LinkedHashMap<>();

    public Extraction(String[] text, String[] keywords) {
        this.text = text;
        this.keywords = keywords;
    }

    @FeatureAnnotation
    public void keywordOccurrences() {
        Map<String, Integer> occurrences = ExtractionHelper.getKeywordsOccurrences(text, keywords);
        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            features.put("ko_" + entry.getKey(), new NumberFeature(entry.getValue()));
        }
    }

    @FeatureAnnotation
    public void keywordOccurrencesCount() {
        Map<String, Integer> keywordsOccurrences = ExtractionHelper.getKeywordsOccurrences(text, keywords);
        int occurrencesCount = keywordsOccurrences.values().stream().reduce(0, Integer::sum);
        features.put("keywords_occurrences_count", new NumberFeature(occurrencesCount));
    }

    @FeatureAnnotation
    public void keywordsDensity() {
        if (text.length == 0) {
            features.put("keywords_density", new NumberFeature(0d));
            return;
        }

        Map<String, Integer> keywordsOccurrences = ExtractionHelper.getKeywordsOccurrences(text, keywords);
        int occurrencesSum = keywordsOccurrences.values().stream().reduce(0, Integer::sum);
        double density = occurrencesSum / text.length;
        features.put("keywords_density", new NumberFeature(density));
    }

    @FeatureAnnotation
    public void keywordsPosition() {
        for (String keyword : keywords) {
            for (int i = 0; i < text.length; i++) {
                if (keyword.equals(text[i])) {
                    features.put("kp_" + keyword, new NumberFeature(i));
                }
            }
            if (features.get("kp_" + keyword) == null) {
                features.put("kp_" + keyword, new NumberFeature(text.length));
            }
        }
    }

    @FeatureAnnotation
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

    @FeatureAnnotation
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

    @FeatureAnnotation
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

    @FeatureAnnotation
    public void wordsCount() {
        features.put("wordsCount", new NumberFeature(text.length));
    }

    public void normalize() {
        double sum = 0, mean = 0;
        int count = 0;
        for (IFeature feature : features.values()) {
            if (feature.getClass().equals(NumberFeature.class)) {
                NumberFeature numberFeature = (NumberFeature) feature;
                sum += numberFeature.getValue();
                count++;
            }
        }
        if (count > 0) {
            mean = sum / (double)count;
        }

        double dev_sum = 0, deviation = 0;

        for (IFeature feature : features.values()) {
            if (feature.getClass().equals(NumberFeature.class)) {
                NumberFeature numberFeature = (NumberFeature) feature;
                double diff = (numberFeature.getValue() - mean);
                dev_sum += diff * diff;
            }
        }

        if (count > 0) {
            deviation = Math.sqrt(dev_sum / (double) count);
        }

        for (IFeature feature : features.values()) {
            if (feature.getClass().equals(NumberFeature.class)) {
                NumberFeature numberFeature = (NumberFeature) feature;
                double normalized = (numberFeature.getValue() - mean) / deviation;
                numberFeature.setValue(normalized);
            }
        }
    }

    public Map<String, IFeature> getFeatures() {
        return features;
    }
}
