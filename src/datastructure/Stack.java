package datastructure;

public class Stack<E> {

    private int size = 0;
    private Node topElement;

    private class Node {
        Node underneathElement;
        E item;
    }

    public void push(E item) {
        Node newTopElement = new Node();
        newTopElement.underneathElement = topElement;
        newTopElement.item = item;

        topElement = newTopElement;
        size++;
    }

    public E pop() {
        if (size == 0) {
            return null;
        }

        Node lastTopElement = topElement;
        topElement = topElement.underneathElement;
        size--;

        return lastTopElement.item;
    }

    public int size() {
        return size;
    };

    public boolean isEmpty() {
        return size == 0;
    }
}
