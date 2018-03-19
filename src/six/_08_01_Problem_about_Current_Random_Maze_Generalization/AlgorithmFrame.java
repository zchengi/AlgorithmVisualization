package six._08_01_Problem_about_Current_Random_Maze_Generalization;

import templates.AlgorithmVisHelper;

import javax.swing.*;
import java.awt.*;

/**
 * 视图层
 *
 * @author cheng
 *         2018/3/19 14:20
 */
public class AlgorithmFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    private MazeData data;

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

    public void render(MazeData data) {
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
            int w = canvasWidth / data.M();
            int h = canvasHeight / data.N();

            for (int i = 0; i < data.N(); i++) {
                for (int j = 0; j < data.M(); j++) {

                    if (data.inMist[i][j]) {
                        AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Cyan);
                    } else {
                        if (data.maze[i][j] == MazeData.WALL) {
                            AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.LightBlue);
                        } else {
                            AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.White);
                        }
                        if (data.path[i][j]) {
                            AlgorithmVisHelper.setColor(graphics2D, AlgorithmVisHelper.Yellow);
                        }
                    }
                    AlgorithmVisHelper.fillRectangle(graphics2D, j * w, i * h, w, h);
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
