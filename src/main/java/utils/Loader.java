package utils;

import interfaces.IClassificationObject;
import interfaces.Parser;

import java.io.File;
import java.io.IOException;

public class Loader {

    public static IClassificationObject[] load(String path, Parser parser) throws IOException {
        File file = new File(path);
        return parser.parseFile(file);
    }
}
