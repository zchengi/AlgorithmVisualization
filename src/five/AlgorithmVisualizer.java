package five;

import templates.AlgorithmVisHelper;

import java.awt.*;

/**
 * 控制层
 *
 * @author cheng
 *         2018/1/28 14:30
 */
public class AlgorithmVisualizer {

    private static int DELAY = 20;
    private static int blockSide = 6;

    private MazeData data;

    private AlgorithmFrame frame;

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

        setData();

    }

    private void setData() {

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        String mazeFile = "src/five/maze_101_101.txt";
        AlgorithmVisualizer vis = new AlgorithmVisualizer(mazeFile);
    }
}
