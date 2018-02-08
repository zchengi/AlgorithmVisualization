package four.Merge;

import templates.AlgorithmVisHelper;

import javax.swing.*;
import java.awt.*;

/**
 * 视图层
 *
 * @author cheng
 *         2018/1/27 19:57
 */
public class AlgorithmFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    private MergeSortData data;

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
        this(title, 800, 600);
    }

    public void render(MergeSortData data) {
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
                    // 参与排序的元素
                    AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Green);
                } else {
                    // 未参与排序的元素
                    AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.LightGrey);
                }
                if (i >= data.l && i <= data.mergeIndex) {
                    // 排序完成的元素
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
