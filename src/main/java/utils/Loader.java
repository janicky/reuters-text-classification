package utils;

import interfaces.IClassificationObject;
import interfaces.IParser;

import java.io.File;
import java.io.IOException;

public class Loader {

    public static IClassificationObject[] load(String path, IParser parser) throws IOException {
        File file = new File(path);
        return parser.parseFile(file);
    }
}
