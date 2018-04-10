package nine.two;

/**
 * @author cheng
 *         2018/4/10 9:59
 */
public class CircleData {

    private int startX,startY;
    private int startR;
    private int depth;
    private int step;

    public CircleData(int x, int y, int r, int d, int step) {
        startX = x;
        startY = y;
        startR = r;
        depth = d;
        this.step = step;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getStartR() {
        return startR;
    }

    public int getDepth() {
        return depth;
    }

    public int getStep() {
        return step;
    }
}
