package five;

import five._05_DFS_Maze_Solver.MazeData;

/**
 * 迷宫测试类
 *
 * @author cheng
 *         2018/3/13 18:15
 */
public class TestMazeData {
    public static void main(String[] args) {

        String mazeFile = "src/five/maze_101_101.txt";
        MazeData data = new MazeData(mazeFile);
        data.print();

    }
}
