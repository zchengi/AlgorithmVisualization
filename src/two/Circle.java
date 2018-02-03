package two;

import java.awt.*;

/**
 * 圆圈类
 *
 * @author cheng
 *         2018/1/28 13:19
 */
public class Circle {
    /**
     * 设置圆的圆心位置
     */
    public int x, y;

    /**
     * 半径
     */
    private int r;

    /**
     * 速度
     */
    public int vx, vy;

    /**
     * 是否为实心圆
     */
    public boolean isFilled = false;

    public Circle(int x, int y, int r, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * 碰撞检测
     */
    private void checkCollision(int minX, int minY, int maxX, int maxY) {
        // 贴到左边缘
        if (x - r < minX) {
            x = r;
            vx = -vx;
        }
        if (x + r >= maxX) {
            x = maxX - r;
            vx = -vx;
        }
        // 贴到下边缘
        if (y - r < minY) {
            y = r;
            vy = -vy;
        }
        if (y + r >= maxY) {
            y = maxY - r;
            vy = -vy;
        }
    }

    public int getR() {
        return r;
    }

    /**
     * 圆的移动动画
     */
    public void move(int minX, int minY, int maxX, int maxY) {
        x += vx;
        y += vy;

        checkCollision(minX, minY, maxX, maxY);
    }

    /**
     * 判断某个点是否在圆内
     */
    public boolean contain(Point point) {
        return (x - point.x) * (x - point.x) + (y - point.y) * (y - point.y) <= r * r;
    }
}
