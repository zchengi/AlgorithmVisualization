package three.monteCarlo;

import templates.AlgorithmVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;

/**
 * 控制层
 *
 * @author cheng
 *         2018/1/28 14:30
 */
public class AlgorithmVisualizer {

    /**
     * 数据
     */
    private MonteCarloPiData data;
    private int n;

    /**
     * 视图
     */
    private AlgorithmFrame frame;

    private static final int DELAY = 40;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n) {
        // 初始化数据
        if (sceneWidth != sceneHeight) {
            throw new IllegalArgumentException("This demo must be run in a square window!");
        }
        this.n = n;
        Circle circle = new Circle(sceneWidth / 2, sceneHeight / 2, sceneWidth / 2);
        data = new MonteCarloPiData(circle);


        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame(" Get Pi with Monte Carlo ", sceneWidth, sceneHeight);

            new Thread(() -> run()).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        for (int i = 0; i < n; i++) {
            if (i % 100 == 0) {
                frame.render(data);
                AlgorithmVisHelper.pause(DELAY);

                System.out.println(data.estimatePi());
            }

            int x = (int) (Math.random() * frame.getCanvasWidth());
            int y = (int) (Math.random() * frame.getCanvasHeight());

            data.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args) {
        int sceneWidth = 600;
        int sceneHeight = 600;
        int n = 20000;

        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(sceneWidth, sceneHeight, n);
    }
}
