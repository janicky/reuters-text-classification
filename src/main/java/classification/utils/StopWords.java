package classification.utils;

import classification.data_models.IClassificationObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StopWords {
    private String[] stopWords;
    private IClassificationObject[] objects;

    public StopWords(IClassificationObject[] objects) {
        this.objects = objects;
    }

    public void generate() {

    }

    public void loadFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        List<String> words = new ArrayList<>();
        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine());
        }
        scanner.close();
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
