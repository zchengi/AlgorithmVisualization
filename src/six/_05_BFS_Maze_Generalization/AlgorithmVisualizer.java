package six._05_BFS_Maze_Generalization;

import templates.AlgorithmVisHelper;

import java.awt.*;
import java.util.LinkedList;

/**
 * 控制层
 *
 * @author cheng
 *         2018/3/19 12:04
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
        LinkedList<Position> queue = new LinkedList<>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        queue.addLast(first);
        data.visited[first.getX()][first.getY()] = true;

        while (!queue.isEmpty()) {
            Position curPos = queue.removeFirst();
            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0] * 2;
                int newY = curPos.getY() + d[i][1] * 2;

                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    queue.addLast(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    setData(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }

        setData(-1, -1);
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
