package two;

import javax.swing.*;
import java.awt.*;

/**
 * @author cheng
 *         2018/1/27 19:57
 */
public class AlgorithmFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    private Circle[] circles;

    public AlgorithmFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgorithmCanvas canvas = new AlgorithmCanvas();

        // 设置内容窗格
        this.setContentPane(canvas);

        // 设置窗口大小为不可变
        this.setResizable(false);
        // 窗口布局整理，自动调整窗口大小
        // 在确保系统也不会修改我们的窗口大小后进行pack！
        pack();

        // 设置关闭事件
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 显示窗口
        setVisible(true);
    }

    public AlgorithmFrame(String title) {
        this(title, 800, 600);
    }

    /**
     * 绘制
     */
    public void render(Circle[] circles) {
        this.circles = circles;
        // 对画布刷新，重新绘制
        repaint();
    }

    /**
     * 画布
     * JPanel 默认支持双缓存
     */
    private class AlgorithmCanvas extends JPanel {
        AlgorithmCanvas() {
            // 支持双缓存
            super(true);
        }

        // 绘制组件 Graphics：绘制的上下文环境
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;

            // 打开抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            // 设置运行质量优先
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics2D.addRenderingHints(hints);

            // 具体绘制
            AlgorithmVisHelper.setStrokeWidth(graphics2D, 1);
            AlgorithmVisHelper.setColor(graphics2D, Color.RED);
            for (Circle circle : circles) {
                if (circle.isFilled) {
                    AlgorithmVisHelper.fillCircle(graphics2D, circle.x, circle.y, circle.getR());
                } else {
                    AlgorithmVisHelper.strokeCircle(graphics2D, circle.x, circle.y, circle.getR());
                }
            }
        }

        // 返回画布大小 系统会自动调用，不用显式的设置大小
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }
}
