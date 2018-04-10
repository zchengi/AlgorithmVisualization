package nine.two;

import templates.AlgorithmVisHelper;

import javax.swing.*;
import java.awt.*;

/**
 * 视图层
 *
 * @author cheng
 *         2018/4/10 9:59
 */
public class AlgorithmFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    private CircleData data;

    public AlgorithmFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgorithmCanvas canvas = new AlgorithmCanvas();
        this.setContentPane(canvas);

        this.setResizable(false);
        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public AlgorithmFrame(String title) {
        this(title, 640, 480);
    }

    public void render(CircleData data) {
        this.data = data;
        repaint();
    }

    private class AlgorithmCanvas extends JPanel {
        AlgorithmCanvas() {
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
            drawCircle(graphics2D, data.getStartX(), data.getStartY(), data.getStartR(), 0);
        }

        private void drawCircle(Graphics2D graphics2D, int x, int y, int r, int depth) {

            if (depth == data.getDepth() || r < 1) {
                return;
            }

            if (depth % 2 == 0) {
                AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Red);
            } else if (depth % 3 == 0) {
                AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Cyan);
            } else if (depth % 5 == 0) {
                AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Purple);
            } else {
                AlgorithmVisHelper.setColor(graphics2D,AlgorithmVisHelper.White);
            }
            AlgorithmVisHelper.fillCircle(graphics2D, x, y, r);
            drawCircle(graphics2D, x, y, r - data.getStep(), depth + 1);
        }

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
