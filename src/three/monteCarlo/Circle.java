package three.monteCarlo;

import java.awt.*;

/**
 * @author cheng
 *         2018/2/3 22:14
 */
public class Circle {
    private int x, y, r;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean contain(Point p) {
        // 点P（x1,y1) 与圆 x^2 + y^2 = r^2 的位置关系：
        // 当 (x1 - x)^2 + (y1 - y)^2 < r^2时 点在圆内；
        // 当 (x1 - x)^2 + (y1 - y)^2 = r^2时 点在圆上；
        // 当 (x1 - x)^2 + (y1 - y)^2 > r^2时 点在圆外；
        return Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2) <= r * r;
    }
}
