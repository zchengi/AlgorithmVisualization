package six._06_Random_Maze_Generalization;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * 随机队列 实现
 *
 * @author cheng
 *         2018/3/19 13:38
 */
public class RandomQueue<E> {

    private ArrayList<E> queue;

    public RandomQueue() {
        queue = new ArrayList<>();
    }

    public void add(E e) {
        queue.add(e);
    }

    public E remove() {

        if (queue.size() == 0) {
            throw new NoSuchElementException("There's no element to remove in Random Queue.");
        }

        int randIndex = (int) (Math.random() * queue.size());

        E randElement = queue.get(randIndex);
        queue.set(randIndex, queue.get(queue.size() - 1));
        queue.remove(randElement);

        return randElement;
    }

    public int size() {
        return queue.size();
    }

    public boolean empty() {
        return queue.size() == 0;
    }
}
