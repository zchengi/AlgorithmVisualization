package six._02_Maze_Generalization_Basic;

import templates.AlgorithmVisHelper;

import java.awt.*;

/**
 * 控制层
 *
 * @author cheng
 *         2018/3/19 11:10
 */
public class AlgorithmVisualizer {

    private static int blockSide = 6;
    private static int DELAY = 5;

    /**
     * 数据
     */
    private MazeData data;
    /**
     * 视图
     */
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int N, int M) {

        // 初始化数据
        data = new MazeData(N, M);
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;

        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Random Maze Generation Visualization", sceneWidth, sceneHeight);

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

        // 高
        int N = 101;
        // 宽
        int M = 101;
        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(N, M);
    }
}
