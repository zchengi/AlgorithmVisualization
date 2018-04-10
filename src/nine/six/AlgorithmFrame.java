package nine.six;

import templates.AlgorithmVisHelper;

import javax.swing.*;
import java.awt.*;

/**
 * 视图层
 *
 * @author cheng
 *         2018/4/10 11:29
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
            drawFractal(graphics2D, 0, canvasHeight, canvasWidth, 0);
        }

        private void drawFractal(Graphics2D graphics2D, int Ax, int Ay, int side, int depth) {

            if (side <= 1) {
                AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Indigo);
                AlgorithmVisHelper.fillRectangle(graphics2D, Ax, Ay, 1, 1);
                return;
            }

            int Bx = Ax + side;
            int By = Ay;

            int h = (int) (Math.sin(60.0 * Math.PI / 180.0) * side);
            int Cx = Ax + side / 2;
            int Cy = Ay - h;

            if (depth == data.getDepth()) {
                AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Indigo);
                AlgorithmVisHelper.fillTriangle(graphics2D, Ax, Ay, Bx, By, Cx, Cy);
                return;
            }

            int AB_centerX = (Ax + Bx) / 2;
            int AB_centerY = (Ay + By) / 2;

            int AC_centerX = (Ax + Cx) / 2;
            int AC_centerY = (Ay + Cy) / 2;

            drawFractal(graphics2D, Ax, Ay, side / 2, depth + 1);
            drawFractal(graphics2D, AC_centerX, AC_centerY, side / 2, depth + 1);
            drawFractal(graphics2D, AB_centerX, AB_centerY, side / 2, depth + 1);
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
