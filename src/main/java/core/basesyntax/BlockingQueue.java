package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while (queue.size() == capacity) {
            try {
                wait();
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Wrong!", e);
            }
        }
        queue.add(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()) {
            try {
                wait();
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Wrong!", e);
            }
        }
        T poll = queue.poll();
        notify();
        return poll;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
