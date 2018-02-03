package two;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 控制层
 *
 * @author cheng
 *         2018/1/28 14:30
 */
public class AlgorithmVisualizer {

    /**
     * 数据
     */
    private Circle[] circles;

    /**
     * 视图
     */
    private AlgorithmFrame frame;

    /**
     * 动画是否在执行
     */
    private boolean isAnimated = true;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int n) {
        circles = new Circle[n];
        int r = 50;

        // 随机生成圆
        for (int i = 0; i < n; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * r)) + r;
            int y = (int) (Math.random() * (sceneHeight - 2 * r)) + r;

            // 生成一个 -5 ~ 5 之间的随机数
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;

            circles[i] = new Circle(x, y, r, vx, vy);
        }

        // 初始化视图    将GUI放入事件派生队列中（java事件分发线程）
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Welcome", sceneWidth, sceneHeight);

            // 添加键盘监听器
            frame.addKeyListener(new AlgorithmKeyListener());
            // 添加鼠标监听器
            frame.addMouseListener(new AlgorithmMouseListener());

            // 创建新线程运行任务
            new Thread(this::run).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        while (true) {
            // 绘制数据
            frame.render(circles);
            // 暂停绘制
            AlgorithmVisHelper.pause(20);

            // 更新数据
            if (isAnimated) {
                for (Circle circle : circles) {
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
                }
            }
        }
    }

    /**
     * 键盘事件
     */
    private class AlgorithmKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }

    /**
     * 鼠标事件
     */
    private class AlgorithmMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            // 因为窗口本身含有大小，所以画布应减去该大小
            // http://coding.imooc.com/learn/questiondetail/26420.html
            event.translatePoint(0,
                    -(frame.getBounds().height - frame.getCanvasHeight()));

            // System.out.println(event.getPoint());

            for (Circle circle : circles) {
                if (circle.contain(event.getPoint())) {
                    circle.isFilled = !circle.isFilled;
                }
            }
        }
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 600;

        int n = 10;

        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(sceneWidth, sceneHeight, n);
    }
}
