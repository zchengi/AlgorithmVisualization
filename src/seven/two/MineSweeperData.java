package seven.two;

/**
 * 扫雷数据层
 *
 * @author cheng
 *         2018/3/20 10:33
 */
public class MineSweeperData {

    public static final String blockImageURL = "src/seven/resources/block.png";
    public static final String flagImageURL = "src/seven/resources/flag.png";
    public static final String mineImageURL = "src/seven/resources/mine.png";

    private int N, M;
    private boolean[][] mines;

    public MineSweeperData(int N, int M, int mineNumber) {

        if (N <= 0 || M <= 0) {
            throw new IllegalArgumentException("Mine sweeper size is invalid.");
        }
        if (mineNumber < 0 || mineNumber > N * M) {
            throw new IllegalArgumentException("Mine number is larger than the size of mine sweeper board.");
        }

        this.N = N;
        this.M = M;
        mines = new boolean[N][M];

        mines[0][0] = true;
    }

    public static String numberImageURL(int num) {
        if (num < 0 || num > 8) {
            throw new IllegalArgumentException("No such a number image.");
        }
        return String.format("src/seven/resources/%d.png", num);
    }

    public boolean isMine(int x, int y) {
        if (!inArea(x, y)) {
            throw new IllegalArgumentException("Out of index in isMine function.");
        }
        return mines[x][y];
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }
}
