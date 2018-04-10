package nine.six;

import templates.AlgorithmVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 控制层
 *
 * @author cheng
 *         2018/4/10 11:29
 */
public class AlgorithmVisualizer {

    private static int DELAY = 40;

    private FractalData data;
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int maxDepth) {

        // 初始化数据
        data = new FractalData(maxDepth);

        // 盘面大小自动获取
        int sceneWidth = (int) Math.pow(2, maxDepth);
        int sceneHeight = (int) Math.pow(2, maxDepth);

        // 盘面大小为矩形
        // int sceneWidth = (int) Math.pow(2, maxDepth);
        // int sceneHeight = (int) Math.pow(2, maxDepth) / 2;

        // 盘面大小为固定值
        // int sceneWidth = 600;
        // int sceneHeight = 600;

        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Fractal Visualizer(输入：0-9，改变深度递归)", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgorithmKeyListener());

            new Thread(() -> run()).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        setData(data.getDepth());
    }

    private void setData(int depth) {

        if (depth >= 0) {
            data.setDepth(depth);
        }

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    private class AlgorithmKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event){
            //System.out.println("Key released:" + event);
            if(event.getKeyChar() >= '0' && event.getKeyChar() <= '9'){
                int depth = event.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    public static void main(String[] args) {

        int maxDepth = 9;
        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(maxDepth);
    }
}
