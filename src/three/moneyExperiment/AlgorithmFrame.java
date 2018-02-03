package three.moneyExperiment;

import templates.AlgorithmVisHelper;

import javax.swing.*;
import java.awt.*;

/**
 * @author cheng
 *         2018/2/3 21:30
 */
public class AlgorithmFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    private int[] money;

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

    public void render(int[] money) {
        this.money = money;
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

            // 小矩形宽度
            int width = canvasWidth / money.length;
            // 柱状矩形
            for (int i = 0; i < money.length; i++) {
                if (money[i] > 0) {
                    AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Blue);
                    AlgorithmVisHelper.fillRectangle(graphics2D,
                            i * width + 1, canvasHeight / 2 - money[i], width - 1, money[i]);
                } else if (money[i] < 0) {
                    AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Red);
                    AlgorithmVisHelper.fillRectangle(graphics2D,
                            i * width + 1, canvasHeight / 2, width - 1, -money[i]);
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
