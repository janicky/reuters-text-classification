package utils;

public class Operations {
    public static String normalizeText(String text) {
        return text.trim().replaceAll("[^A-Za-z\\s+]", "").toLowerCase();
    }

    public static String[] vectorizeText(String text) {
        return text.split("\\s+");
    }
}
