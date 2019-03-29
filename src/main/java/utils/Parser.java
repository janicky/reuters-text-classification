package utils;

import objects.ClassificationObject;
import java.io.File;

public interface Parser {
    ClassificationObject[] parseFile(File file);
}
