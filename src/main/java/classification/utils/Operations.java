package classification.utils;

import classification.data_models.IClassificationObject;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.englishStemmer;

import java.util.*;

public class Operations {
    public static String removeSpecialCharacters(final String text) {
        return text.trim().replaceAll("[^A-Za-z\\s+]", "").toLowerCase();
    }

    public static String[] splitText(final String text) {
        return text.split("\\s+");
    }

    public static Map<String, Integer> wordsOccurrences(String[] dictionary, String[] text) {
        Map<String, Integer> map = new HashMap<>();
        for (String dict : dictionary) {
            boolean found = false;
            for (String word : text) {
                if (word.equals(dict)) {
                    found = true;
                    map.put(dict, (map.containsKey(dict) ? map.get(word) + 1 : 1));
                }
            }
            if (!found) {
                map.put(dict, 0);
            }
        }
        return map;
    }

    public static IClassificationObject[] selectObjects(Map<IClassificationObject, Double> map, int k) {
        Map sortedMap = Operations.sortByValues(map);
        Set objects = sortedMap.entrySet();

        IClassificationObject[] selected = new IClassificationObject[k];
        int i = 0;
        for (Object o : objects) {
            Map.Entry<IClassificationObject, Double> entry = (Map.Entry) o;
            selected[i++] = entry.getKey();
            if (i >= k) {
                return selected;
            }
        }
        return selected;
    }

    public static String selectLabel(IClassificationObject[] objects) {
        Map<String, Integer> labelsOccurrences = new HashMap<>();
        String max_label = null;
        int max_count = 0;

        for (IClassificationObject o : objects) {
            for (String label : o.getLabels()) {
                if (labelsOccurrences.containsKey(label)) {
                    int current = labelsOccurrences.get(label);
                    labelsOccurrences.put(label, current + 1);
                } else {
                    labelsOccurrences.put(label, 1);
                }

                int element = labelsOccurrences.get(label);
                if (element > max_count) {
                    max_count = element;
                    max_label = label;
                }
            }
        }

        return max_label;
    }

    public static boolean checkLabel(String selectedLabel, String[] labels) {
        for (String label : labels) {
            if (label.equals(selectedLabel)) {
                return true;
            }
        }
        return false;
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                (k1, k2) -> {
                    int compare =
                            map.get(k1).compareTo(map.get(k2));
                    if (compare == 0)
                        return 1;
                    else
                        return compare;
                };

        Map<K, V> sortedByValues = new TreeMap<>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    public static String stem(String word) {
        SnowballStemmer stemmer = new englishStemmer();
        stemmer.setCurrent(word);
        stemmer.stem();
        return stemmer.getCurrent();
    }

    public static void stem(IClassificationObject[] objects) {
        for (IClassificationObject object : objects) {
            String[] text = object.getVectorizedText();
            for (int i = 0; i < text.length; i++) {
                text[i] = Operations.stem(text[i]);
            }
        }
    }

    public static IClassificationObject[] filterObjects(IClassificationObject[] objects, String[] labels, int mode) {
        List<IClassificationObject> filtered = new ArrayList<>();
        for (IClassificationObject object : objects) {
            String[] object_labels = object.getLabels();
            boolean match = matchLabels(labels, object_labels, 2);
            if (match) {
                filtered.add(object);
            }
        }

        return filtered.toArray(new IClassificationObject[filtered.size()]);
    }

    private static boolean matchLabels(String[] labels, String[] o_labels, int mode) {
//        mode:
//            0 - match any
//            1 - match only
//            2 - match only one
        if (mode == 2 && o_labels.length != 1) {
            return false;
        }
        for (String o_label : o_labels) {
            boolean proper = false;
            for (String label : labels) {
                if (label.equals(o_label)) {
                    if (mode == 0) {
                        return true;
                    }
                    proper = true;
                }
            }
            if (!proper) {
                return false;
            }
        }
        return true;
    }
}
