package utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void vectorizeText() {
        String text = "With increasing distance, our knowledge fades, and fades rapidly.";
        String[] vectorizedText = Operations.vectorizeText(text);
        String[] expected = new String[] { "With", "increasing", "distance,", "our", "knowledge", "fades,", "and", "fades", "rapidly." };

        for (int i = 0; i < vectorizedText.length; i++) {
            assertEquals(expected[i], vectorizedText[i]);
        }
    }
}