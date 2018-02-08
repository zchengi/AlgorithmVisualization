package four.Merge;

/**
 * 归并排序：递归过程可视化。
 *
 * @author cheng
 *         2018/2/8 12:21
 */
public class Merge {

    private Merge() {
    }

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(Comparable[] a, int lo, int hi, int depth) {

        System.out.print(repeatCharacters('-', depth * 2));
        System.out.println("Deal with [ " + lo + " , " + hi + " ]");

        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, depth + 1);
        sort(a, mid + 1, hi, depth + 1);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {

        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static String repeatCharacters(char character, int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void show(Comparable[] a) {
        for (Comparable item : a) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Integer[] arr = new Integer[9];

        for (int i = 0; i < 9; i++) {
            arr[i] = 9 - 1;
        }
        sort(arr);
    }
}
