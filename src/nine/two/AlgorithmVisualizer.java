package nine.two;

import templates.AlgorithmVisHelper;

import java.awt.*;

/**
 * 控制层
 *
 * @author cheng
 *         2018/4/10 9:59
 */
public class AlgorithmVisualizer {

    private static int DELAY = 40;

    private CircleData data;
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight) {

        // 初始化数据
        int R = Math.min(sceneWidth, sceneHeight) / 2 - 2;
        data = new CircleData(sceneWidth / 2, sceneHeight / 2, R, R / 2, 2);

        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Fractal Visualizer", sceneWidth, sceneHeight);

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

        int sceneWidth = 800;
        int sceneHeight = 600;

        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(sceneWidth, sceneHeight);
    }
}
