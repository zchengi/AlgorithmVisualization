package four.Heap;

/**
 * 堆排序数据层
 *
 * @author cheng
 *         2018/2/9 16:11
 */
public class HeapSortData {

    public int[] numbers;

    /**
     * numbers[heapIndex...n]已经排好序了
     */
    public int heapIndex;

    public HeapSortData(int n, int randomBound) {
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
        }
    }

    public int size() {
        return numbers.length;
    }

    public int get(int index) {
        if (index < 0 || index >= numbers.length) {
            throw new IllegalArgumentException("Invalid index to access Sort Data!");
        }
        return numbers[index];
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= numbers.length || j < 0 || j >= numbers.length) {
            throw new IllegalArgumentException("Invalid index to access Sort Data!");
        }
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}