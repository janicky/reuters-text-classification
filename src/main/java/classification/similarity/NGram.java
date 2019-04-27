package classification.similarity;

public class NGram implements ISimilarityMeter {
    private int n;

    public NGram(int n) {
        this.n = n;
    }

    public double measure(String o1, String o2) {
        // TODO: Measure implementation
        return 0;
    }
}
