package four.Merge;

import templates.AlgorithmVisHelper;

import java.awt.*;
import java.util.Arrays;

/**
 * 自底向上的归并排序可视化
 *
 * @author cheng
 *         2018/1/28 14:30
 */
public class MergeSortBUVisualizer {

    private static final int DELAY = 60;
    private MergeSortData data;
    private AlgorithmFrame frame;

    public MergeSortBUVisualizer(int sceneWidth, int sceneHeight, int n) {

        // 初始化数据
        data = new MergeSortData(n, sceneWidth);

        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Merge Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> run()).start();
        });
    }

    /**
     * 自底向上排序的动画逻辑
     */
    private void run() {
        setData(-1, -1, -1);
        for (int sz = 1; sz < data.size(); sz *= 2) {
            for (int i = 0; i < data.size(); i += sz + sz) {
                merge(i, i + sz - 1, Math.min(i + sz + sz - 1, data.size() - 1));
            }
        }
        setData(0, data.size() - 1, data.size());
    }

    private void mergeSort(int l, int r) {
        if (l >= r) return;

        setData(l, r, -1);

        int mid = (l + r) / 2;
        mergeSort(l, mid);
        mergeSort(mid + 1, r);
        merge(l, mid, r);
    }

    private void merge(int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(data.numbers, l, r + 1);

        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                data.numbers[k] = aux[j - l];
                j++;
            } else if (j > r) {
                data.numbers[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {
                data.numbers[k] = aux[i - l];
                i++;
            } else {
                data.numbers[k] = aux[j - l];
                j++;
            }
            setData(l, r, k);
        }
    }

    private void setData(int l, int r, int mergeIndex) {
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;

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
