package nine.five;

import templates.AlgorithmVisHelper;

import javax.swing.*;
import java.awt.*;

/**
 * 视图层
 *
 * @author cheng
 *         2018/4/10 11:12
 */
public class AlgorithmFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    private FractalData data;

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

    public void render(FractalData data) {
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
            drawFractal(graphics2D, 0, 0, canvasWidth, canvasHeight, 0);
        }

        private void drawFractal(Graphics2D graphics2D, int x, int y, int w, int h, int depth) {

            if (w <= 1 || h <= 1) {
                return;
            }

            int w_3 = w / 3;
            int h_3 = h / 3;

            if (depth == data.getDepth()) {
                AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Indigo);
                AlgorithmVisHelper.fillRectangle(graphics2D, x + w_3, y + h_3, w_3, h_3);
                return;
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Indigo);
                        AlgorithmVisHelper.fillRectangle(graphics2D, x + w_3, y + h_3, w_3, h_3);
                    } else {
                        drawFractal(graphics2D, x + i * w_3, y + j * h_3, w_3, h_3, depth + 1);
                    }
                }
            }
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
