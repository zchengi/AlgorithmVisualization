package six._03_DFS_Maze_Generalization;

import templates.AlgorithmVisHelper;

import java.awt.*;

/**
 * 控制层
 *
 * @author cheng
 *         2018/3/19 11:32
 */
public class AlgorithmVisualizer {

    private static int blockSide = 6;
    private static int DELAY = 5;

    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * 数据
     */
    private MazeData data;
    /**
     * 视图
     */
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int N, int M) {

        // 初始化数据
        data = new MazeData(N, M);
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;

        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Random Maze Generation Visualization", sceneWidth, sceneHeight);

            new Thread(() -> run()).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {

        setData(-1, -1);
        go(data.getEntranceX(), data.getEntranceY() + 1);
        setData(-1, -1);
    }

    private void go(int x, int y) {

        if (!data.inArea(x, y)) {
            throw new IllegalArgumentException("x,y are out of index in go function!");
        }

        data.visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0] * 2;
            int newY = y + d[i][1] * 2;
            if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                setData(x + d[i][0], y + d[i][1]);
                go(newX, newY);
            }
        }
    }

    private void setData(int x, int y) {
        if (data.inArea(x, y)) {
            data.maze[x][y] = MazeData.ROAD;
        }

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        // 高
        int N = 101;
        // 宽
        int M = 101;
        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(N, M);
    }
}
