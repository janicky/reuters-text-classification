package classification.utils;

import classification.data_models.IClassificationObject;
import classification.data_models.IParser;

import java.io.File;
import java.io.IOException;

public class Loader {

    public static IClassificationObject[] load(String path, IParser parser) throws IOException {
        File file = new File(path);
        return parser.parseFile(file);
    }

    public static IClassificationObject[] loadFromFile(File file, IParser parser) throws IOException {
        return parser.parseFile(file);
    }
}
