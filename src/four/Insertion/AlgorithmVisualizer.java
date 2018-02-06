package four.Insertion;

import templates.AlgorithmVisHelper;

import java.awt.*;

/**
 * 控制层
 *
 * @author cheng
 *         2018/1/28 14:30
 */
public class AlgorithmVisualizer {

    private static final int DELAY = 40;

    private InsertionSortData data;

    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n) {
        this(sceneWidth, sceneHeight, n, InsertionSortData.Type.Default);
    }

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n, InsertionSortData.Type dataType) {

        // 初始化数据
        data = new InsertionSortData(n, sceneHeight, dataType);

        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Insertion Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> run()).start();
        });
    }

    private void run() {

        setData(0, -1);

        for (int i = 0; i < data.size(); i++) {
            setData(i, i);
            for (int j = i; j > 0 && data.get(j) < data.get(j - 1); j--) {
                data.swap(j, j - 1);
                setData(i + 1, j - 1);
            }
        }
        setData(data.size(), -1);
    }

    private void setData(int orderedIndex, int currentIndex) {
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 600;
        int n = 100;

        // 一般数组的插入排序
        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(sceneWidth, sceneHeight, n);
        // 近乎有序数组的插入排序
        // new AlgorithmVisualizer(sceneWidth, sceneHeight, n, InsertionSortData.Type.NearlyOrdered);
    }
}
