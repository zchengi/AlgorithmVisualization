package four.Heap;

import templates.AlgorithmVisHelper;

import java.awt.*;

/**
 * 控制层
 *
 * @author cheng
 *         2018/1/28 14:30
 */
public class AlgorithmVisualizer {

    public static final int DELAY = 40;
    private HeapSortData data;

    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n) {

        // 初始化数据
        data = new HeapSortData(n, sceneHeight);

        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Welcome", sceneWidth, sceneHeight);

            new Thread(() -> run()).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        setData(data.size());

        // 建堆
        for (int i = (data.size() - 1 - 1) / 2; i >= 0; i--) {
            shiftDown(data.size(), i);
        }

        // 堆排序
        for (int i = data.size() - 1; i > 0; i--) {
            data.swap(0, i);
            shiftDown(i, 0);
            setData(i);
        }
    }

    private void shiftDown(int n, int k) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && data.get(j + 1) > data.get(j)) j++;
            if (data.get(k) >= data.get(j)) break;

            data.swap(k, j);
            setData(data.heapIndex);

            k = j;
        }
    }

    public void setData(int heapIndex) {
        data.heapIndex = heapIndex;

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 600;
        int n = 100;

        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(sceneWidth, sceneHeight, 100);
    }
}
