package eight.src;

import templates.AlgorithmVisHelper;

import java.awt.*;

/**
 * 控制层
 *
 * @author cheng
 *         2018/4/9 10:12
 */
public class AlgorithmVisualizer {

    private static int blockSide = 70;
    private static int DELAY = 5;

    private GameData data;

    private AlgorithmFrame frame;

    public AlgorithmVisualizer(String filename) {

        data = new GameData(filename);
        int sceneWidth = data.M() * blockSide;
        int sceneHeight = data.N() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Move The Box Solver", sceneWidth, sceneHeight);
            new Thread(() -> run()).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {

        setData();

        if (data.solve()) {
            System.out.println("The game has a solution!");
        } else {
            System.out.println("The game doesn't have a solution!");
        }
    }

    private void setData() {
        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        //  boston_09.txt boston_16.txt
        String filename = "src/eight/level/boston_16.txt";
        AlgorithmVisualizer vis = new AlgorithmVisualizer(filename);
    }
}
