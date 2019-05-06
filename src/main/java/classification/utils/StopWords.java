package classification.utils;

import classification.data_models.IClassificationObject;
import classification.features.TermFrequency;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StopWords {
    private String[] stopWords;
    private IClassificationObject[] objects;

    public StopWords(IClassificationObject[] objects) {
        this.objects = objects;
    }

    public void generate(double significance) {
        List<String> newWords = new ArrayList<>();
        String[][] documents = getDocuments();

        for (String[] document : documents) {
            for (String word : document) {
                if (!newWords.contains(word)) {
                    double idf = TermFrequency.inverseFrequency(documents, word);
                    if (idf < significance) {
                        newWords.add(word);
                    }
                }
            }
        }

        stopWords = newWords.toArray(new String[newWords.size()]);
    }

    public void loadFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        List<String> words = new ArrayList<>();
        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine());
        }
        scanner.close();

        stopWords = words.toArray(new String[words.size()]);
    }

    public String[] getStopWords() {
        return stopWords;
    }

    private String[][] getDocuments() {
        String[][] output = new String[objects.length][];

        for (int i = 0; i < objects.length; i++) {
            output[i] = objects[i].getVectorizedText();
        }

        return output;
    }
}
