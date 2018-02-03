package three.monteCarlo;

import java.awt.*;
import java.util.LinkedList;

/**
 * 使用蒙特卡洛求Pi所用的数据
 *
 * @author cheng
 *         2018/2/4 0:44
 */
public class MonteCarloPiData {

    private Circle circle;

    private LinkedList<Point> points;

    private int insideCircle = 0;

    public MonteCarloPiData(Circle circle) {
        this.circle = circle;
        points = new LinkedList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
        if (circle.contain(point)) {
            insideCircle++;
        }
    }

    public double estimatePi() {
        if (points.size() == 0) {
            return 0.0;
        }
        int circleArea = insideCircle;
        int squareArea = points.size();
        return (double) circleArea * 4 / squareArea;
    }

    public Circle getCircle() {
        return circle;
    }

    public Point getPoint(int i) {
        if (i < 0 || i > points.size()) {
            throw new IllegalArgumentException("out of bound in getPoint!");
        }
        return points.get(i);
    }

    public int getPointsNumber() {
        return points.size();
    }
}
