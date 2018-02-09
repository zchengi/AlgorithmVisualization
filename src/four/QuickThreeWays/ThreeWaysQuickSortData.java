package four.QuickThreeWays;

import java.util.Arrays;

/**
 * 快速排序数据
 *
 * @author cheng
 *         2018/2/9 12:38
 */
public class ThreeWaysQuickSortData {

    public enum Type {
        Default,
        NearlyOrdered,
        Identical
    }

    private int[] numbers;
    public int l, r;
    public boolean[] fixedPivots;
    public int curPivot;
    public int curL,curR;

    public ThreeWaysQuickSortData(int n, int randomBound) {
        this(n, randomBound, Type.Default, -1);
    }

    public ThreeWaysQuickSortData(int n, int randomBound, Type dataType, int exchanges) {

        numbers = new int[n];
        fixedPivots = new boolean[n];

        int lBound = 1;
        int rBound = randomBound;
        if (dataType == Type.Identical) {
            int avgNumber = (lBound + rBound) / 2;
            lBound = avgNumber;
            rBound = avgNumber;
        }

        for (int i = 0; i < n; i++) {
            numbers[i] = (int) (Math.random() * (rBound - lBound + 1)) + lBound;
            fixedPivots[i] = false;
        }

        if (dataType == Type.NearlyOrdered) {
            Arrays.sort(numbers);
            for (int i = 0; i < exchanges; i++) {
                int a = (int) (Math.random());
                int b = (int) (Math.random());
                swap(a, b);
            }
        }

    }

    public int size() {
        return numbers.length;
    }

    public int get(int index) {

        if (index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        return numbers[index];
    }

    public void swap(int i, int j) {

        if (i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
