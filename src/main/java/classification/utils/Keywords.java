package classification.utils;

import classification.data_models.ClassificationObject;
import classification.data_models.IClassificationObject;
import classification.features.TermFrequency;

import java.io.*;
import java.util.*;

public class Keywords {

    IClassificationObject[] objects;
    String[][] documents;
    List<String> keywords = new ArrayList<>();

    public Keywords(IClassificationObject[] objects) {
        this.objects = objects;
        documents = new String[objects.length][];
        for (int i = 0; i < objects.length; i++) {
            documents[i] = objects[i].getVectorizedText();
        }
    }

    public void generate(double significance) {
        List<String> newKeywords = new ArrayList<>();

        int finished = 0;
        for (IClassificationObject object : objects) {
            String[] words = object.getVectorizedText();
            for (String word : words) {
                if (!newKeywords.contains(word) && word.length() > 2) {
                    double tfidf = TermFrequency.termFrequencyInverseFrequency(documents, words, word);
                    if (tfidf > significance) {
                        newKeywords.add(word);
                    }
                }
            }
            finished++;
            System.out.println(finished + " / " + objects.length);
        }
        keywords = newKeywords;
    }

    public void generate(int count) {
        LinkedHashMap<String, Map<String, Double>> labels = new LinkedHashMap<>();

        int finished = 0;
        for (IClassificationObject object : objects) {
            String[] words = object.getVectorizedText();
            String label = object.getLabels()[0];
            if (!labels.containsKey(label)) {
                labels.put(label, new HashMap<>());
            }
            for (String word : words) {
                Map map = labels.get(label);
                if (!map.containsKey(word) && word.length() > 2) {
                    double tfidf = TermFrequency.termFrequencyInverseFrequency(documents, words, word);
                    map.put(word, tfidf);
                }
            }
            finished++;
            System.out.println(finished + " / " + objects.length);
        }

        List<String> newKeywords = new ArrayList<>();
        for (Map.Entry<String, Map<String, Double>> entry : labels.entrySet()) {
            Map sorted = Operations.sortByValuesDesc(entry.getValue());
            int i = 0;
            for (Object object : sorted.entrySet()) {
                if (i < count) {
                    Map.Entry<String, Double> keyword = (Map.Entry) object;
                    newKeywords.add(keyword.getKey());
                    i++;
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

    public void exportKeywords(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(filename));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (String keyword : keywords) {
            bw.write(keyword);
            bw.newLine();
        }

        bw.close();
    }

    public String[] getKeywords() {
        return keywords.toArray(new String[keywords.size()]);
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
