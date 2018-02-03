package two;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * 工具类
 *
 * @author cheng
 *         2018/1/28 12:46
 */
public class AlgorithmVisHelper {
    private AlgorithmVisHelper() {
    }

    /**
     * 绘制一个空心圆
     */
    public static void strokeCircle(Graphics2D graphics2D, int x, int y, int r) {
        // 绘制一个圆形 x,y:左上角坐标 width,height:以其为正方形包围盒的圆
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        graphics2D.draw(circle);
    }

    /**
     * 绘制一个实心圆
     */
    public static void fillCircle(Graphics2D graphics2D, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        graphics2D.fill(circle);
    }

    /**
     * 设置笔划宽度
     */
    public static void setStrokeWidth(Graphics2D graphics2D, int strokeWidth) {
        // 线段端点是圆 BasicStroke.CAP_ROUND
        // 线段折点 BasicStroke.JOIN_ROUND
        graphics2D.setStroke(
                new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    /**
     * 设置颜色
     */
    public static void setColor(Graphics2D graphics2D, Color color) {
        graphics2D.setColor(color);
    }

    /**
     * 暂停运行
     */
    public static void pause(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("Error in sleeping.");
        }
    }
}
