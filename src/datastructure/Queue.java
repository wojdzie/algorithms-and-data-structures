package datastructure;

public class Queue<E> {

    private Node first;
    private Node last;
    private int size = 0;

    public void enqueue(E item) {
        Node oldLastNode = last;

        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLastNode.next = last;
        }
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E item = first.item;
        first = first.next;
        size--;

        if (isEmpty()) {
            last = null;
        }

        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        Node next;
        E item;
    }
}
