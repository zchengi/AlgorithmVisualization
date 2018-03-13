package five._09_BFS_Maze_Solver;

import templates.AlgorithmVisHelper;

import java.awt.*;
import java.util.LinkedList;

/**
 * 控制层
 *
 * @author cheng
 *         2018/3/13 21:04
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

    /**
     * 非递归广度优先遍历走迷宫
     */
    private void run() {

        setData(-1, -1, false);

        LinkedList<Position> queue = new LinkedList<>();
        Position entrance = new Position(data.getEntranceX(), data.getEntranceY());
        queue.addLast(entrance);
        data.visited[entrance.getX()][entrance.getY()] = true;

        boolean isSolved = false;
        while (!queue.isEmpty()) {
            Position curPos = queue.pop();
            setData(curPos.getX(), curPos.getY(), true);

            if (curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY()) {
                isSolved = true;
                findPath(curPos);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0];
                int newY = curPos.getY() + d[i][1];

                if (data.inArea(newX, newY) && !data.visited[newX][newY] && data.getMaze(newX, newY) == MazeData.ROAD) {
                    queue.addLast(new Position(newX, newY, curPos));
                    data.visited[newX][newY] = true;
                }
            }
        }

        if (!isSolved) {
            System.out.println("The maze has no solution!");
        }

        setData(-1, -1, false);
    }

    private void findPath(Position des) {

        Position cur = des;
        while (cur != null) {
            data.result[cur.getX()][cur.getY()] = true;
            cur = cur.getPrev();
        }
    }

    private void setData(int x, int y, boolean isPath) {

        if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        String mazeFile = "src/five/maze_101_101.txt";
        AlgorithmVisualizer vis = new AlgorithmVisualizer(mazeFile);
    }
}
