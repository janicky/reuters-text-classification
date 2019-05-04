package classification.helpers;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExtractionHelper {
    public static Map<String, Integer> getKeywordsOccurrences(String[] text, String[] keywords) {
        Map<String, Integer> occurrencesMap = new LinkedHashMap<>();

        for (String dict : keywords) {
            int occurrences = 0;
            for (String word : text) {
                if (word.equals(dict)) {
                    occurrences++;
                }
            }
            occurrencesMap.put(dict, occurrences);
        }

        return occurrencesMap;
    }
}
