package interfaces;

import java.io.File;
import java.io.IOException;

public interface Parser {
    ClassificationObject[] parseFile(File file) throws IOException;
}
