package five._08_More_About_Non_Recursive_DFS_Maze_Solver;

/**
 * 坐标
 *
 * @author cheng
 *         2018/3/13 20:30
 */
public class Position {

    private int x, y;

    private Position prev;

    public Position(int x, int y, Position prev) {
        this.x = x;
        this.y = y;
        this.prev = prev;
    }

    public Position(int x, int y) {
        this(x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getPrev() {
        return prev;
    }
}
