package eight.option_01_Interactions;

import com.sun.nio.sctp.IllegalReceiveException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 箱子数据层
 *
 * @author cheng
 *         2018/4/9 13:22
 */
public class GameData {

    private static int[][] d = {{1, 0}, {0, -1}, {0, -1}};

    private int maxTurn;
    private Board startBoard;
    private int N, M;

    private Board showBoard;

    public int clickX = -1, clickY = -1;


    public GameData(String filename) {

        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null!");
        }

        Scanner scanner = null;
        try {
            File file = new File(filename);
            if (!file.exists()) {
                throw new IllegalArgumentException("File " + filename + "doesn't exist!");
            }

            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");

            String turnLine = scanner.nextLine();
            this.maxTurn = Integer.parseInt(turnLine);

            ArrayList<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
            startBoard = new Board(lines.toArray(new String[lines.size()]));
            this.N = startBoard.N();
            this.M = startBoard.M();

            startBoard.print();

            showBoard = new Board(startBoard);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Move The Box 求解
     */
    public boolean solve() {

        if (maxTurn < 0) {
            return false;
        }

        long startTime = System.currentTimeMillis();

        boolean ret = solve(startBoard, maxTurn);

        long endTime = System.currentTimeMillis();
        System.out.println("Time : " + (endTime - startTime) + "ms");

        return ret;
    }


    /**
     * 通过盘面 board，使用 turn 次 move，解决 move the box 的问题
     * 若可以成功解决，则返回true，否则返回false
     */
    private boolean solve(Board board, int turn) {

        if (board == null || turn < 0) {
            throw new IllegalReceiveException("Illegal arguments in solve function!");
        }

        if (turn == 0) {
            return board.isWin();
        }
        if (board.isWin()) {
            return true;
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (board.getData(x, y) != Board.EMPTY) {
                    for (int i = 0; i < 3; i++) {
                        int newX = x + d[i][0];
                        int newY = y + d[i][1];
                        if (inArea(newX, newY)) {
                            String swapString = String.format
                                    ("swap (%d, %d) and swap (%d, %d)", x, y, newX, newY);
                            Board nextBoard = new Board(board, board, swapString);
                            nextBoard.swap(x, y, newX, newY);
                            nextBoard.run();
                            if (solve(nextBoard, turn - 1)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public Board getShowBoard() {
        return showBoard;
    }
}
