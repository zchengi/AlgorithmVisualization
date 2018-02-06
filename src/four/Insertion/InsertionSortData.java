package four.Insertion;

import java.util.Arrays;

/**
 * 插入排序数据
 *
 * @author cheng
 *         2018/2/6 11:57
 */
public class InsertionSortData {

    public enum Type {
        Default,
        NearlyOrdered,
    }

    private int[] numbers;

    public int orderedIndex = -1;
    public int currentIndex = -1;

    public InsertionSortData(int n, int randomBound) {
        this(n, randomBound, Type.Default);
    }

    public InsertionSortData(int n, int randomBound, Type dataType) {
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
        }

        // 生成近乎有序的数组
        if (dataType == Type.NearlyOrdered) {
            Arrays.sort(numbers);
            int swapTime = (int) (0.02 * n);
            for (int i = 0; i < swapTime; i++) {
                int a = (int) (Math.random() * n);
                int b = (int) (Math.random() * n);
                swap(a, b);
            }
        }
    }

    public int size() {
        return numbers.length;
    }

    public int get(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException("Invalid index to access Sort Data!");
        }
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
