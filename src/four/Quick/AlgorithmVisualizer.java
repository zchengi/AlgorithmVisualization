package four.Quick;

import templates.AlgorithmVisHelper;

import java.awt.*;

import static four.Quick.QuickSortData.Type;
import static four.Quick.QuickSortData.Type.Default;

/**
 * 控制层
 *
 * @author cheng
 *         2018/1/28 14:30
 */
public class AlgorithmVisualizer {

    private static int DELAY = 40;

    private QuickSortData data;

    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n) {
        this(sceneWidth, sceneHeight, n, Default, -1);
    }

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n, Type dataType) {
        this(sceneWidth, sceneHeight, n, dataType, -1);
    }

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n, Type dataType, int exchanges) {

        // 初始化数据
        data = new QuickSortData(n, sceneHeight, dataType, exchanges);

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

        setData(-1, -1, -1, -1, -1);
        quickSort(0, data.size() - 1);
        setData(-1, -1, -1, -1, -1);
    }

    private void quickSort(int l, int r) {

        if (l > r) return;
        if (l == r) {
            setData(l, r, l, -1, -1);
            return;
        }

        setData(l, r, -1, -1, -1);
        int p = partition(l, r);
        quickSort(l, p - 1);
        quickSort(p + 1, r);
    }

    private int partition(int l, int r) {

        // 1.对于在近乎有序的数组中算法退化为O(n^2)的优化
        int p = (int) (Math.random() * (r - l + 1)) + l;
        setData(l, r, -1, p, -1);
        data.swap(l, p);

        int v = data.get(l);
        setData(l, r, -1, l, -1);

        int j = l;
        for (int i = l + 1; i <= r; i++) {
            setData(l, r, -1, l, i);
            if (data.get(i) < v) {
                j++;
                data.swap(j, i);
                setData(l, r, -1, l, i);
            }
        }
        data.swap(l, j);
        setData(l, r, j, -1, -1);
        return j;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curElement) {

        data.l = l;
        data.r = r;
        if (fixedPivot != -1) {
            data.fixedPivots[fixedPivot] = true;
        }
        data.curPivot = curPivot;
        data.curElement = curElement;

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 600;
        int n = 100;
        int exchanges = 10;
        // 普通数组
        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(sceneWidth, sceneHeight, n);

        // 近乎有序的数据
        // new AlgorithmVisualizer(sceneWidth, sceneHeight, n, NearlyOrdered, exchanges);

        // 完全相等的数据
        // new AlgorithmVisualizer(sceneWidth, sceneHeight, n, Identical);
    }
}
