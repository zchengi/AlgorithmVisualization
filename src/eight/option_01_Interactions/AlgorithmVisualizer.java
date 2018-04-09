package eight.option_01_Interactions;

import templates.AlgorithmVisHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 控制层
 *
 * @author cheng
 *         2018/4/9 13:22
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
            frame.addMouseListener(new AlgorithmListener());

            new Thread(() -> run()).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {

        setData(-1, -1);

        if (data.solve()) {
            System.out.println("The game has a solution!");
        } else {
            System.out.println("The game doesn't have a solution!");
        }

        setData(-1, -1);
    }

    private void setData(int clickX, int clickY) {
        data.clickX = clickX;
        data.clickY = clickY;

        frame.render(data);
        AlgorithmVisHelper.pause(DELAY);
    }

    public void addAlgorithmMouseListener() {
        frame.addMouseListener(new AlgorithmListener());
    }

    private Position clickPos1 = null;
    private Position clickPos2 = null;

    private class AlgorithmListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent event) {
            event.translatePoint(
                    -(frame.getBounds().width - frame.getCanvasWidth()),
                    -(frame.getBounds().height - frame.getCanvasHeight())
            );

            Point pos = event.getPoint();
            // System.out.println(pos.x + " , " + pos.y);

            int w = frame.getCanvasWidth() / data.M();
            int h = frame.getCanvasHeight() / data.N();

            int x = pos.y / h;
            int y = pos.x / w;
            // System.out.println(x + " , " + y);

            if (SwingUtilities.isLeftMouseButton(event)) {
                if (data.inArea(x, y)) {
                    setData(x, y);
                    if (clickPos1 == null) {
                        clickPos1 = new Position(x, y);
                    } else {
                        clickPos2 = new Position(x, y);
                        if (clickPos2.nextTo(clickPos1)) {
                            data.getShowBoard().swap(clickPos1.getX(), clickPos1.getY(),
                                    clickPos2.getX(), clickPos2.getY());
                            data.getShowBoard().run();
                        }

                        clickPos1 = null;
                        clickPos2 = null;
                        setData(-1, -1);
                    }
                }
            } else {
                setData(-1, -1);
                clickPos1 = null;
                clickPos2 = null;
            }
        }
    }

    public static void main(String[] args) {

        //  boston_09.txt boston_16.txt
        String filename = "src/eight/level/boston_16.txt";
        AlgorithmVisualizer vis = new AlgorithmVisualizer(filename);
    }
}
