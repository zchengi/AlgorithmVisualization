package four.Selection;

import templates.AlgorithmVisHelper;

import java.awt.*;

/**
 * 控制层
 *
 * @author cheng
 *         2018/1/28 14:30
 */
public class AlgorithmVisualizer {


    private static final int DELAY = 20;

    private SelectionSortData data;

    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n) {

        // 初始化数据
        data = new SelectionSortData(n, sceneHeight);

        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Selection Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> run()).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {

        setData(0, -1, -1);

        for (int i = 0; i < data.size(); i++) {
            // 寻找从[i, n)区间里的最小索引值
            int minIndex = i;
            setData(i, -1, minIndex);

            for (int j = i + 1; j < data.size(); j++) {
                setData(i, j, minIndex);

                if (data.get(j) < data.get(minIndex)) {
                    minIndex = j;
                    setData(i, j, minIndex);
                }
            }
            data.swap(i, minIndex);
            setData(i + 1, -1, -1);
        }

        setData(data.size(), -1, -1);
    }

    private void setData(int orderedIndex, int currentCompareIndex, int currentMinIndex) {
        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 600;
        int n = 100;

        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(sceneWidth, sceneHeight, n);
    }
}
