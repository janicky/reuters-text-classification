package classification.utils;

import classification.data_models.IClassificationObject;
import classification.interfaces.IParser;

import java.io.File;
import java.io.IOException;

public class Loader {

    public static IClassificationObject[] load(String path, IParser parser) throws IOException {
        File file = new File(path);
        return parser.parseFile(file);
    }
}
