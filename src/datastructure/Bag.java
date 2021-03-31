package datastructure;

public class Bag<E> {

    private Node topNode;

    public void add(E item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = topNode;

        topNode = newNode;
    }

    private class Node {
        Node next;
        E item;
    }
}

