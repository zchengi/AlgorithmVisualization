package nine.eight;

/**
 * @author cheng
 *         2018/4/10 12:23
 */
public class FractalData {

    private int depth;
    private double splitAngle;

    public FractalData(int depth, double splitAngle) {
        this.depth = depth;
        this.splitAngle = splitAngle;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public double getSplitAngle() {
        return splitAngle;
    }

    public void setSplitAngle(double splitAngle) {
        this.splitAngle = splitAngle;
    }
}
