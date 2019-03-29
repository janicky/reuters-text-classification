package utils;

public class Operations {
    public static String cleanText(String text) {
        return text.trim().replaceAll("[^A-Za-z\\s+]", "");
    }

    public static String[] vectorizeText(String text) {
        return text.split("\\s+");
    }
}
