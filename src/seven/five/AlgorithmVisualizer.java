package seven.five;

import templates.AlgorithmVisHelper;

import java.awt.*;

/**
 * 控制层
 *
 * @author cheng
 *         2018/3/20 11:45
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

            new Thread(() -> run()).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        setData();
    }

    private void setData() {
        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }


    public static void main(String[] args) {

        int N = 20;
        int M = 20;
        int mineNumber = 50;

        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(N, M, mineNumber);
    }
}
