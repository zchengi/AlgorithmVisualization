package seven.seven;

import templates.AlgorithmVisHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 控制层
 *
 * @author cheng
 *         2018/3/20 13:00
 */
public class AlgorithmVisualizer {

    private static int blockSide = 32;
    private static int DELAY = 5;

    /**
     * 数据
     */
    private MineSweeperData data;
    /**
     * 视图
     */
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int N, int M, int mineNumber) {

        // 初始化数据
        data = new MineSweeperData(N, M, mineNumber);
        int sceneWidth = M * blockSide;
        int sceneHeight = N * blockSide;

        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Mine sweeper", sceneWidth, sceneHeight);

            frame.addMouseListener(new AlgorithmMouseListener());

            new Thread(() -> run()).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        setData(false, -1, -1);
    }

    private void setData(boolean isLeftClicked, int x, int y) {

        if (data.inArea(x, y)) {
            if (isLeftClicked) {
                if (data.isMine(x, y)) {
                    // Game Over
                    data.open[x][y] = true;
                    System.out.println("Game Over!");
                } else {
                    data.open(x, y);
                }
            } else {
                data.flags[x][y] = !data.flags[x][y];
            }
        }

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    private class AlgorithmMouseListener extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent event) {

            event.translatePoint(
                    -(frame.getBounds().width - frame.getCanvasWidth()),
                    -(frame.getBounds().height - frame.getCanvasHeight())
            );

            Point pos = event.getPoint();

            int w = frame.getCanvasWidth() / data.M();
            int h = frame.getCanvasHeight() / data.N();

            int x = pos.y / h;
            int y = pos.x / w;
            // System.out.println(x + " " + y);

            if (SwingUtilities.isLeftMouseButton(event)) {
                setData(true, x, y);
            } else {
                setData(false, x, y);
            }
        }
    }

    public static void main(String[] args) {

        int N = 20;
        int M = 20;
        int mineNumber = 50;

        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(N, M, mineNumber);
    }
}
