package classification.utils;

import classification.data_models.IClassificationObject;
import classification.features.TermFrequency;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Keywords {

    IClassificationObject[] objects;
    List<String> keywords = new ArrayList<>();

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
        keywords = newKeywords;
    }

    public void loadFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        List<String> words = new ArrayList<>();
        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine());
        }
        scanner.close();

        keywords = words;
    }

    public String[] getKeywords() {
        return keywords.toArray(new String[keywords.size()]);
    }
}
