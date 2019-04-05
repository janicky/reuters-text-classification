package interfaces;

import java.io.File;
import java.io.IOException;

public interface Parser {
    IClassificationObject[] parseFile(File file) throws IOException;
}
