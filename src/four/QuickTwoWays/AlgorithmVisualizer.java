package four.QuickTwoWays;

import templates.AlgorithmVisHelper;

import java.awt.*;

import static four.QuickTwoWays.TwoWaysQuickSortData.Type;
import static four.QuickTwoWays.TwoWaysQuickSortData.Type.Default;


/**
 * 控制器
 *
 * @author cheng
 *         2018/1/28 14:30
 */
public class AlgorithmVisualizer {

    private static int DELAY = 40;

    private TwoWaysQuickSortData data;

    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n) {
        this(sceneWidth, sceneHeight, n, Default, -1);
    }

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n, Type dataType) {
        this(sceneWidth, sceneHeight, n, dataType, -1);
    }

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n, Type dataType, int exchanges) {

        // 初始化数据
        data = new TwoWaysQuickSortData(n, sceneHeight, dataType, exchanges);

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

        setData(-1, -1, -1, -1, -1, -1);
        quickSort2Ways(0, data.size() - 1);
        setData(-1, -1, -1, -1, -1, -1);
    }

    private void quickSort2Ways(int l, int r) {

        if (l > r) return;
        if (l == r) {
            setData(l, r, l, -1, -1, -1);
            return;
        }

        setData(l, r, -1, -1, -1, -1);
        int p = partition(l, r);
        quickSort2Ways(l, p - 1);
        quickSort2Ways(p + 1, r);
    }

    private int partition(int l, int r) {

        // 1.对于在近乎有序的数组中算法退化为O(n^2)的优化
        int p = (int) (Math.random() * (r - l + 1)) + l;
        setData(l, r, -1, p, -1, -1);
        data.swap(l, p);

        int v = data.get(l);
        setData(l, r, -1, l, -1, -1);

        int i = l + 1;
        int j = r;
        setData(l, r, -1, l, i, j);
        while (true) {
            while (i <= r && data.get(i) < v) {
                i++;
                setData(l, r, -1, l, i, j);
            }
            while (j >= l + 1 && data.get(j) > v) {
                j--;
                setData(l, r, -1, l, i, j);
            }
            if (i > j) break;
            data.swap(i, j);
            i++;
            j--;
            setData(l, r, -1, l, i, j);
        }

        data.swap(l, j);
        setData(l, r, j, -1, -1, -1);
        return j;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR) {

        data.l = l;
        data.r = r;
        if (fixedPivot != -1) {
            data.fixedPivots[fixedPivot] = true;
        }
        data.curPivot = curPivot;
        data.curL = curL;
        data.curR = curR;

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
