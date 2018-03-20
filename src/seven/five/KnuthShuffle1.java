package seven.five;

/**
 * 验证随机生成雷区的随机性
 * <p>
 * 随机性很强
 *
 * @author cheng
 *         2018/3/20 11:39
 */
public class KnuthShuffle1 {

    private int N;
    private int n;
    private int m;

    public KnuthShuffle1(int N, int n, int m) {

        if (N <= 0) {
            throw new IllegalArgumentException("N must be larger than 0.");
        }
        if (n < m) {
            throw new IllegalArgumentException("n must be larger than or equals to m.");
        }

        this.N = N;
        this.n = n;
        this.m = m;
    }

    public void run() {

        int freq[] = new int[n];

        int[] arr = new int[n];
        for (int i = 0; i < N; i++) {
            reset(arr);
            shuffle(arr);
            for (int j = 0; j < n; j++) {
                freq[j] += arr[j];
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(i + " : " + (double) freq[i] / N);
        }
    }

    private void shuffle(int[] arr) {
        for (int i = 0; i < m; i++) {

            // 从 [i,n) 区间里随机选择元素
            int x = (int) (Math.random() * (n - i)) + i;
            swap(arr, i, x);
        }
    }

    private void reset(int[] arr) {
        for (int i = 0; i < m; i++) {
            arr[i] = 1;
        }
        for (int i = m; i < n; i++) {
            arr[i] = 0;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        int N = 10000000;
        int n = 10;
        int m = 5;
        KnuthShuffle1 exp = new KnuthShuffle1(N, n, m);
        exp.run();
    }
}
