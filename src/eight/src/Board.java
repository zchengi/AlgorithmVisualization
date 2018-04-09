package eight.src;

/**
 * @author cheng
 *         2018/4/9 10:19
 */
public class Board {

    public static char EMPTY = '.';

    private int N, M;
    private char[][] data;

    public Board(String[] lines) {

        if (lines == null) {
            throw new IllegalArgumentException("Lines cannot be null in Board constructor!");
        }

        N = lines.length;
        if (N == 0) {
            throw new IllegalArgumentException("Lines cannot be empty in Board constructor!");
        }

        M = lines[0].length();

        data = new char[N][M];
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() != M) {
                throw new IllegalArgumentException("All lines' length must be same in Board constructor!");
            }

            data[i] = lines[i].toCharArray();
        }
    }

    public Board(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null in Board constructor!");
        }

        this.N = board.N;
        this.M = board.M;
        this.data = new char[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(board.data[i], 0, this.data[i], 0, M);
        }
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print() {
        for (int i = 0; i < N; i++) {
            System.out.println(String.valueOf(data[i]));
        }
    }

    public char getData(int x, int y) {
        if (!inArea(x, y)) {
            throw new IllegalArgumentException("x, y are out of index in getData!");
        }

        return data[x][y];
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }
}
