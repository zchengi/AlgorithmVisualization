package four.QuickThreeWays;

import templates.AlgorithmVisHelper;

import java.awt.*;

import static four.QuickThreeWays.ThreeWaysQuickSortData.Type;
import static four.QuickThreeWays.ThreeWaysQuickSortData.Type.Default;

/**
 * 控制层
 *
 * @author cheng
 *         2018/1/28 14:30
 */
public class AlgorithmVisualizer {

    private static int DELAY = 40;

    private ThreeWaysQuickSortData data;

    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n) {
        this(sceneWidth, sceneHeight, n, Default, -1);
    }

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n, Type dataType) {
        this(sceneWidth, sceneHeight, n, dataType, -1);
    }

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n, Type dataType, int exchanges) {

        // 初始化数据
        data = new ThreeWaysQuickSortData(n, sceneHeight, dataType, exchanges);

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
        quickSort3Ways(0, data.size() - 1);
        setData(-1, -1, -1, -1, -1, -1);
    }

    private void quickSort3Ways(int l, int r) {

        if (l > r)
            return;

        if (l == r) {
            setData(l, r, l, -1, -1, -1);
            return;
        }

        setData(l, r, -1, -1, -1, -1);

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        int p = (int) (Math.random() * (r - l + 1)) + l;
        setData(l, r, -1, p, -1, -1);

        data.swap(l, p);
        int v = data.get(l);
        setData(l, r, -1, l, -1, -1);

        int lt = l;     // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l + 1;    // arr[lt+1...i) == v
        setData(l, r, -1, l, lt, gt);

        while (i < gt) {
            if (data.get(i) < v) {
                data.swap(i, lt + 1);
                i++;
                lt++;
            } else if (data.get(i) > v) {
                data.swap(i, gt - 1);
                gt--;
            } else // arr[i] == v
                i++;

            setData(l, r, -1, l, i, gt);
        }

        data.swap(l, lt);
        setData(l, r, lt, -1, -1, -1);

        quickSort3Ways(l, lt - 1);
        quickSort3Ways(gt, r);
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR) {
        data.l = l;
        data.r = r;
        if (fixedPivot != -1) {
            data.fixedPivots[fixedPivot] = true;
            int i = fixedPivot;
            while (i < data.size() && data.get(i) == data.get(fixedPivot)) {
                data.fixedPivots[i] = true;
                i++;
            }
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
