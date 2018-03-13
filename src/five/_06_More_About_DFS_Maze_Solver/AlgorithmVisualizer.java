package five._06_More_About_DFS_Maze_Solver;

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

        setData(-1, -1, false);

        if (!go(data.getEntranceX(), data.getEntranceY())) {
            System.out.println("The maze has no solution!");
        }

        setData(-1, -1, false);
    }

    private void setData(int x, int y, boolean isPath) {

        if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    /**
     * 深度优先递归走迷宫
     * 代码结构：回溯法
     */
    private boolean go(int x, int y) {

        if (!data.inArea(x, y)) {
            throw new IllegalArgumentException("x,y are out of index in go() function!");
        }

        data.visited[x][y] = true;
        setData(x, y, true);

        if (x == data.getExitX() && y == data.getExitY()) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            // (x,y)是否合法；是否是路，是否没有被访问过
            if (data.inArea(newX, newY) && data.getMaze(newX, newY) == MazeData.ROAD && !data.visited[newX][newY]) {
                // 如果找到出口
                if (go(newX, newY)) {
                    return true;
                }
            }
        }

        // 循环结束如果没有找到出口则证明当前顶点不在迷宫的解中
        setData(x, y, false);

        return false;
    }

    public static void main(String[] args) {

        String mazeFile = "src/five/maze_101_101.txt";
        AlgorithmVisualizer vis = new AlgorithmVisualizer(mazeFile);
    }
}
