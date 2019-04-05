package interfaces;

import java.io.File;
import java.io.IOException;

public interface IParser {
    IClassificationObject[] parseFile(File file) throws IOException;
}
