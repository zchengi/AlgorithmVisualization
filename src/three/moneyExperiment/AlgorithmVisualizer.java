package three.moneyExperiment;

import templates.AlgorithmVisHelper;

import java.awt.*;
import java.util.Arrays;

/**
 * 控制层
 *
 * @author cheng
 *         2018/2/3 22:14
 */
public class AlgorithmVisualizer {

    /**
     * 数据
     */
    private int[] money;

    /**
     * 视图
     */
    private AlgorithmFrame frame;

    /**
     * 延时
     */
    private static final int DELAY = 10;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight) {

        // 初始化数据
        money = new int[100];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100;
        }

        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame(" A Money Experiment ", sceneWidth, sceneHeight);

            new Thread(() -> run()).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        while (true) {

            // 改进2：是否排序
            Arrays.sort(money);
            frame.render(money);

            // 大概是帧率为 25 所用的时间
            AlgorithmVisHelper.pause(DELAY);

            // 改进1：每一帧执行的轮数
            for (int k = 0; k < 30; k++) {
                for (int i = 0; i < money.length; i++) {
                    // 改进3：允许money为负值
                    //if (money[i] > 0) {
                    int j = (int) (Math.random() * money.length);
                    money[i] -= 1;
                    money[j] += 1;
                    //}
                }
            }
        }
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 600;


        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(sceneWidth, sceneHeight);
    }
}
