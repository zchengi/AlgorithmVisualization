package four.QuickTwoWays;

import templates.AlgorithmVisHelper;

import javax.swing.*;
import java.awt.*;

/**
 * @author cheng
 *         2018/1/27 19:57
 */
public class AlgorithmFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    private TwoWaysQuickSortData data;

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

    public void render(TwoWaysQuickSortData data) {
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
            int width = canvasWidth / data.size();
            for (int i = 0; i < data.size(); i++) {
                if (i >= data.l && i <= data.r) {
                    AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Green);
                } else {
                    AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.LightGrey);
                }
                if (i == data.curPivot) {
                    AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Indigo);
                }
                if (i >= data.l + 1 && i <= data.curL || i >= data.curR && i <= data.r) {
                    AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.LightBlue);
                }
                if (data.fixedPivots[i]) {
                    AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Red);
                }
                AlgorithmVisHelper.fillRectangle(
                        graphics2D, i * width, canvasHeight - data.get(i), width - 1, data.get(i));
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
