package templates;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * 控制层
 *
 * @author cheng
 *         2018/1/28 14:30
 */
public class AlgorithmVisualizer {

    // TODO: 创建自己的数据
    /**
     * 数据
     */
    private Object data;

    /**
     * 视图
     */
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight) {

        // 初始化数据
        // TODO: 初始化数据

        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Welcome", sceneWidth, sceneHeight);

            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            new Thread(() -> run()).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        // TODO: 编写自己的动画逻辑
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgorithmKeyListener extends KeyAdapter {
    }

    private class AlgorithmMouseListener extends MouseAdapter {
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 600;

        // TODO: 根据需要设置其他参数，初始化visualizer

        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(sceneWidth, sceneHeight);
    }
}
