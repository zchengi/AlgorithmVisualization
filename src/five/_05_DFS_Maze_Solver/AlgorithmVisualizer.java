package five._05_DFS_Maze_Solver;

import templates.AlgorithmVisHelper;

import java.awt.*;

/**
 * 控制层
 *
 * @author cheng
 *         2018/1/28 14:30
 */
public class AlgorithmVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 6;

    private MazeData data;
    private AlgorithmFrame frame;

    /**
     * (x,y) 坐标偏移量：左移，下移，右移，上移
     */
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgorithmVisualizer(String mazeFile) {

        // 初始化数据
        data = new MazeData(mazeFile);

        int sceneHeight = data.N() * blockSide;
        int sceneWeight = data.M() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Maze Solver Visualization", sceneWeight, sceneHeight);

            new Thread(this::run).start();
        });
    }

    private void run() {

        setData(-1, -1);

        go(data.getEntranceX(), data.getEntranceY());

        setData(-1, -1);
    }

    private void setData(int x, int y) {

        if (data.inArea(x, y)) {
            data.path[x][y] = true;
        }

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    /**
     * 深度优先递归走迷宫
     */
    private void go(int x, int y) {

        if (!data.inArea(x, y)) {
            throw new IllegalArgumentException("x,y are out of index in go() function!");
        }

        data.visited[x][y] = true;
        setData(x, y);

        if (x == data.getExitX() && y == data.getExitY()) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            // (x,y)是否合法；是否是路，是否没有被访问过
            if (data.inArea(newX, newY) && data.getMaze(newX, newY) == MazeData.ROAD && !data.visited[newX][newY]) {
                go(newX, newY);
            }
        }
    }

    public static void main(String[] args) {

        String mazeFile = "src/five/maze_101_101.txt";
        AlgorithmVisualizer vis = new AlgorithmVisualizer(mazeFile);
    }
}
