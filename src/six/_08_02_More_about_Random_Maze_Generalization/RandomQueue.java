package six._08_02_More_about_Random_Maze_Generalization;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 随机队列 实现
 *
 * @author cheng
 *         2018/3/19 14:37
 */
public class RandomQueue<E> {

    private LinkedList<E> queue;

    public RandomQueue() {
        queue = new LinkedList<>();
    }

    public void add(E e) {
        if (Math.random() < 0.5) {
            queue.addFirst(e);
        } else {
            queue.addLast(e);
        }
    }

    public E remove() {

        if (queue.size() == 0) {
            throw new NoSuchElementException("There's no element to remove in Random Queue.");
        }

        if (Math.random() < 0.5) {
            return queue.removeFirst();
        } else {
            return queue.removeLast();
        }

    }

    public int size() {
        return queue.size();
    }

    public boolean empty() {
        return queue.size() == 0;
    }
}
