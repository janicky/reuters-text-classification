package classification.similarity;

public class NGram implements ISimilarityMeter {
    private int n;

    public NGram(int n) {
        this.n = n;
    }

    public double measure(String o1, String o2) {
        String s1 = (o1.length() > o2.length() ? o1 : o2);
        String s2 = (o1.length() > o2.length() ? o2 : o1);

        String[] substrings = split(s1);
        double sum = 0;

        for (int i = 0; i < s2.length() + 1 - n; i++) {
            for (int j = 0; j < substrings.length; j++) {
                if (substrings[j].equals(s2.substring(i, i + n))) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private String[] split(String word) {
        String[] output = new String[word.length() + 1 - n];
        for (int i = 0; i < word.length() + 1 - n; i++){
            output[i] = word.substring(i, i + n);
        }
        return output;
    }
}
