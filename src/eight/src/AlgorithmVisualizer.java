package eight.src;

/**
 * 控制层
 *
 * @author cheng
 *         2018/4/9 10:12
 */
public class AlgorithmVisualizer {

    private GameData data;

    private AlgorithmFrame frame;

    public AlgorithmVisualizer(String filename) {
        data = new GameData(filename);
    }

    /**
     * 动画逻辑
     */
    private void run() {
    }

    public static void main(String[] args) {

        String filename = "src/eight/level/boston_09.txt";
        AlgorithmVisualizer vis = new AlgorithmVisualizer(filename);
    }
}
