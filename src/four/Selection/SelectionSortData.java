package four.Selection;

/**
 * 选择排序数据
 *
 * @author cheng
 *         2018/2/6 10:46
 */
public class SelectionSortData {
    private int[] numbers;

    /**
     * [0...orderedIndex) 是有序的
     */
    public int orderedIndex = -1;

    /**
     * 当前找到的最小元素的索引
     */
    public int currentMinIndex = -1;

    /**
     * 当前比较的元素索引
     */
    public int currentCompareIndex = -1;


    public SelectionSortData(int n, int randomBound) {
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
        }
    }

    /**
     * 返回数组大小
     */
    public int size() {
        return numbers.length;
    }

    /**
     * 根据索引返回数据值
     */
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
