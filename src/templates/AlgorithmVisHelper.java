package templates;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * 工具类
 *
 * @author cheng
 *         2018/1/28 12:46
 */
public class AlgorithmVisHelper {

    private AlgorithmVisHelper() {
    }

    public static final Color Red = new Color(0xF44336);
    public static final Color Pink = new Color(0xE91E63);
    public static final Color Purple = new Color(0x9C27B0);
    public static final Color DeepPurple = new Color(0x673AB7);
    public static final Color Indigo = new Color(0x3F51B5);
    public static final Color Blue = new Color(0x2196F3);
    public static final Color LightBlue = new Color(0x03A9F4);
    public static final Color Cyan = new Color(0x00BCD4);
    public static final Color Teal = new Color(0x009688);
    public static final Color Green = new Color(0x4CAF50);
    public static final Color LightGreen = new Color(0x8BC34A);
    public static final Color Lime = new Color(0xCDDC39);
    public static final Color Yellow = new Color(0xFFEB3B);
    public static final Color Amber = new Color(0xFFC107);
    public static final Color Orange = new Color(0xFF9800);
    public static final Color DeepOrange = new Color(0xFF5722);
    public static final Color Brown = new Color(0x795548);
    public static final Color Grey = new Color(0x9E9E9E);
    public static final Color LightGrey = new Color(0xCDCDCD);
    public static final Color BlueGrey = new Color(0x607D8B);
    public static final Color Black = new Color(0x000000);
    public static final Color White = new Color(0xFFFFFF);

    /**
     * 空心圆
     */
    public static void strokeCircle(Graphics2D graphics2D, int x, int y, int r) {
        // 绘制一个圆形 x,y:左上角坐标 width,height:以其为正方形包围盒的圆
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        graphics2D.draw(circle);
    }

    /**
     * 实心圆
     */
    public static void fillCircle(Graphics2D graphics2D, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        graphics2D.fill(circle);
    }

    /**
     * 空心矩形
     */
    public static void strokeRectangle(Graphics2D graphics2D, int x, int y, int w, int h) {
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        graphics2D.draw(rectangle);
    }

    /**
     * 实心矩形
     */
    public static void fillRectangle(Graphics2D graphics2D, int x, int y, int w, int h) {
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        graphics2D.fill(rectangle);
    }

    /**
     * 实心三角形
     */
    public static void fillTriangle(Graphics2D graphics2D, int x1, int y1, int x2, int y2, int x3, int y3) {

        GeneralPath path = new GeneralPath();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.closePath();

        graphics2D.fill(path);
    }

    /**
     * 颜色
     */
    public static void setColor(Graphics2D graphics2D, Color color) {
        graphics2D.setColor(color);
    }

    /**
     * 笔划宽度
     */
    public static void setStrokeWidth(Graphics2D graphics2D, int strokeWidth) {
        // 线段端点是圆 BasicStroke.CAP_ROUND
        // 线段折点 BasicStroke.JOIN_ROUND
        graphics2D.setStroke(
                new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    /**
     * 暂停运行
     */
    public static void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Error sleeping.");
        }
    }

    /**
     * 图片
     */
    public static void putImage(Graphics2D graphics2D, int x, int y, String imageURL) {
        ImageIcon icon = new ImageIcon(imageURL);
        Image image = icon.getImage();

        graphics2D.drawImage(image, x, y, null);
    }

    /**
     * 文本
     */
    public static void drawText(Graphics2D graphics2D, String text, int centerX, int centerY) {
        if (text == null) throw new IllegalArgumentException("Text is null in drawText function!");

        FontMetrics metrics = graphics2D.getFontMetrics();
        int width = metrics.stringWidth(text);
        int height = metrics.getDescent();

        graphics2D.drawString(text, centerX - width / 2, centerY + height);
    }

    /**
     * 线段
     */
    public static void drawLine(Graphics2D graphics2D, double x1, double y1, double x2, double y2) {
        Line2D line = new Line2D.Double(x1, y1, x2, y2);
        graphics2D.draw(line);
    }
}
