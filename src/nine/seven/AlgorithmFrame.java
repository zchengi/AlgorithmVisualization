package nine.seven;

import templates.AlgorithmVisHelper;

import javax.swing.*;
import java.awt.*;

/**
 * 视图层
 *
 * @author cheng
 *         2018/4/10 11:53
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
            drawFractal(graphics2D, 0, canvasHeight - 3, canvasWidth, 0, 0);
        }

        private void drawFractal(Graphics2D graphics2D, double x1, double y1,
                                 double side, double angle, int depth) {

            if (side <= 0) {
                return;
            }

            if( depth == data.getDepth() ){
                double x2 = x1 + side * Math.cos(angle*Math.PI/180.0);
                double y2 = y1 - side * Math.sin(angle*Math.PI/180.0);
                AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Indigo);
                AlgorithmVisHelper.drawLine(graphics2D, x1, y1, x2, y2);
                return;
            }

            double side_3 = side / 3;

            double x2 = x1 + side_3 * Math.cos(angle*Math.PI/180.0);
            double y2 = y1 - side_3 * Math.sin(angle*Math.PI/180.0);
            drawFractal(graphics2D, x1, y1, side_3, angle, depth+1);

            double x3 = x2 + side_3 * Math.cos((angle+60.0)*Math.PI/180.0);
            double y3 = y2 - side_3 * Math.sin((angle+60.0)*Math.PI/180.0);
            drawFractal(graphics2D, x2, y2, side_3, angle+60.0, depth+1);

            double x4 = x3 + side_3 * Math.cos((angle-60.0)*Math.PI/180.0);
            double y4 = y3 - side_3 * Math.sin((angle-60.0)*Math.PI/180.0);
            drawFractal(graphics2D, x3, y3, side_3, angle-60.0, depth+1);

            // double x5 = x4 + side_3 * Math.cos(angle*Math.PI/180.0);
            // double y5 = y4 - side_3 * Math.sin(angle*Math.PI/180.0);

            drawFractal(graphics2D, x4, y4, side_3, angle, depth + 1);
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
