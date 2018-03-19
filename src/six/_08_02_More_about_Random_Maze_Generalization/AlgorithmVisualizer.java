package six._08_02_More_about_Random_Maze_Generalization;

import templates.AlgorithmVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 控制层
 *
 * @author cheng
 *         2018/3/19 14:37
 */
public class AlgorithmVisualizer {

    private static int blockSide = 6;
    private static int DELAY = 2;

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

            // 添加键盘时间：当迷宫生成后，按下空格，开始走迷宫
            frame.addKeyListener(new AlgorithmKeyListener());

            new Thread(() -> run()).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {

        setRoadData(-1, -1);

        RandomQueue<Position> queue = new RandomQueue<>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        queue.add(first);
        data.visited[first.getX()][first.getY()] = true;
        data.openMist(first.getX(), first.getY());

        while (!queue.empty()) {
            Position curPos = queue.remove();

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0] * 2;
                int newY = curPos.getY() + d[i][1] * 2;

                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    queue.add(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    data.openMist(newX, newY);
                    setRoadData(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }

        setRoadData(-1, -1);
    }

    private void setRoadData(int x, int y) {
        if (data.inArea(x, y)) {
            data.maze[x][y] = MazeData.ROAD;
        }

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    private boolean go(int x, int y) {

        if (!data.inArea(x, y)) {
            throw new IllegalArgumentException("x or y are out of index in go function.");
        }

        data.visited[x][y] = true;
        setPathData(x, y, true);

        if (x == data.getExitX() && y == data.getExitY()) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (data.inArea(newX, newY) && data.maze[newX][newY] == MazeData.ROAD && !data.visited[newX][newY]) {
                if (go(newX, newY)) {
                    return true;
                }
            }
        }

        setPathData(x, y, false);

        return false;
    }

    private void setPathData(int x, int y, boolean isPath) {

        if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    private class AlgorithmKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
                for (int i = 0; i < data.N(); i++) {
                    for (int j = 0; j < data.M(); j++) {
                        data.visited[i][j] = false;
                    }
                }

                new Thread(() -> go(data.getEntranceX(), data.getEntranceY())).start();
            }
        }
    }

    public static void main(String[] args) {

        // 高
        int N = 101;
        // 宽
        int M = 101;
        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(N, M);
    }
}
