package four.Insertion;

/**
 * 插入排序数据
 *
 * @author cheng
 *         2018/2/6 11:57
 */
public class InsertionSortData {

    private int[] numbers;

    public int orderedIndex = -1;
    public int currentIndex = -1;

    public InsertionSortData(int n, int randomBound) {
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
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
