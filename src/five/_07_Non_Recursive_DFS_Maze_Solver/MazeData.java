package five._07_Non_Recursive_DFS_Maze_Solver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 迷宫数据层
 *
 * @author cheng
 *         2018/3/13 19:57
 */
public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int N, M;

    /**
     * 迷宫数组
     */
    private char[][] maze;

    /**
     * 迷宫入口坐标
     */
    private int entranceX, entranceY;

    /**
     * 迷宫出口坐标
     */
    private int exitX, exitY;

    /**
     * 迷宫是否被访问过
     */
    public boolean[][] visited;

    /**
     * 深度优先遍历的寻找路径
     */
    public boolean[][] path;

    /**
     * 迷宫最终解
     */
    public boolean[][] result;

    public MazeData(String filename) {

        if (filename == null) {
            throw new IllegalArgumentException("Filename can not be null!");
        }

        Scanner scanner = null;
        try {
            File file = new File(filename);
            if (!file.exists()) {
                throw new IllegalArgumentException("File " + filename + " doesn't exist!");
            }
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");

            // 读取第一行
            String nmline = scanner.nextLine();
            String[] nm = nmline.trim().split("\\s+");
            N = Integer.parseInt(nm[0]);
            M = Integer.parseInt(nm[1]);

            maze = new char[N][M];
            visited = new boolean[N][M];
            path = new boolean[N][M];
            result = new boolean[N][M];

            // 读取后续的N行
            for (int i = 0; i < N; i++) {
                String line = scanner.nextLine();
                // 每行保证有M个字符
                if (line.length() != M) {
                    throw new IllegalArgumentException("Maze file " + filename + "is invalid!");
                }

                // 对每一个迷宫元素赋值
                for (int j = 0; j < M; j++) {
                    maze[i][j] = line.charAt(j);
                    visited[i][j] = false;
                    path[i][j] = false;
                    result[i][j] = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = N - 1;
    }

    public void print() {
        System.out.println(N + " " + M);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y <= M;
    }

    public char getMaze(int i, int j) {
        if (!inArea(i, j)) {
            throw new IllegalArgumentException("i or j is out of index in getMaze()!");
        }
        return maze[i][j];
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }
}
