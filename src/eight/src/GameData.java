package eight.src;

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
 *         2018/4/9 10:12
 */
public class GameData {


    private int maxTurn;
    private Board startBoard;
    private int N, M;

    private Board showBoard;

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
