package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtractorTest {

    @Test
    void binaryExtraction() {
        String[] text = new String[] { "machine", "learning", "based", "systems" };
        String[] dictionary = new String[] { "learning", "artificial", "representation", "systems" };
        boolean[] binary = Extractor.binaryExtraction(dictionary, text);
//        Assertions
        assertEquals(4, binary.length);
        assertTrue(binary[0]);
        assertFalse(binary[1]);
        assertFalse(binary[2]);
        assertTrue(binary[3]);
    }
}