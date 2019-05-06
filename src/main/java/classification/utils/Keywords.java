package classification.utils;

import classification.data_models.IClassificationObject;
import classification.features.TermFrequency;

import java.util.ArrayList;
import java.util.List;

public class Keywords {

    IClassificationObject[] objects;
    String[] keywords;

    public Keywords(IClassificationObject[] objects) {
        this.objects = objects;
    }

    public void generate(double significance) {
        String[][] documents = new String[objects.length][];
        for (int i = 0; i < objects.length; i++) {
            documents[i] = objects[i].getVectorizedText();
        }

        List<String> newKeywords = new ArrayList<>();

        for (IClassificationObject object : objects) {
            String[] words = object.getVectorizedText();
            for (String word : words) {
                if (!newKeywords.contains(word)) {
                    double tfidf = TermFrequency.termFrequencyInverseFrequency(documents, words, word);
                    if (tfidf > significance) {
                        newKeywords.add(word);
                    }
                }
            }
        }
        keywords = newKeywords.toArray(new String[newKeywords.size()]);
    }

    public String[] getKeywords() {
        return keywords;
    }
}
