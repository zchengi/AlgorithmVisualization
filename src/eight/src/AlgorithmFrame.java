package eight.src;

import templates.AlgorithmVisHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 视图层
 *
 * @author cheng
 *         2018/4/9 10:12
 */
public class AlgorithmFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    private GameData data;

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

    public void render(GameData data) {
        this.data = data;
        repaint();
    }

    private class AlgorithmCanvas extends JPanel {

        private HashMap<Character, Color> colorMap;
        private ArrayList<Color> colorList;

        public AlgorithmCanvas() {
            // 双缓存
            super(true);

            colorMap = new HashMap<>();

            colorList = new ArrayList<>();
            colorList.add(AlgorithmVisHelper.Red);
            colorList.add(AlgorithmVisHelper.Purple);
            colorList.add(AlgorithmVisHelper.Blue);
            colorList.add(AlgorithmVisHelper.Teal);
            colorList.add(AlgorithmVisHelper.LightGreen);
            colorList.add(AlgorithmVisHelper.Lime);
            colorList.add(AlgorithmVisHelper.Amber);
            colorList.add(AlgorithmVisHelper.DeepOrange);
            colorList.add(AlgorithmVisHelper.Brown);
            colorList.add(AlgorithmVisHelper.BlueGrey);
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

            Board showBoard = data.getShowBoard();
            for (int i = 0; i < showBoard.N(); i++) {
                for (int j = 0; j < showBoard.M(); j++) {
                    char c = showBoard.getData(i, j);
                    if (c != Board.EMPTY) {

                        if (!colorMap.containsKey(c)) {
                            int sz = colorMap.size();
                            colorMap.put(c, colorList.get(sz));
                        }

                        Color color = colorMap.get(c);
                        AlgorithmVisHelper.setColor(graphics2D, color);
                        AlgorithmVisHelper.fillRectangle(
                                graphics2D, j * h + 2, i * w + 2, w - 4, h - 4);
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
