package datastructure;

public class Queue<E> {

    private static class QueueImplementation<E> {

        private Node<E> head;
        private Node<E> tail;
        private int size = 0;

        private static class Node<E> {
            Node<E> nextNode;
            E value;
        }

        public void enqueue(E value) {
            Node<E> newNode = new Node<>();
            newNode.value = value;
            // check if queue is empty
            if (head == null) {
                head = newNode;
            } else {
                tail.nextNode = newNode;
            }
            tail = newNode;
            size++;
        }
        public E dequeue() {
            // check if queue is empty
            if (head == null) {
                return null;
            }
            Node<E> firstNode = head;
            head = head.nextNode;
            firstNode.nextNode = null;
            // check if queue is empty after dequeue
            if (head == null) {
                tail = null;
            }
            size--;
            return firstNode.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    private static class DequeImplementation<E> {

        private Node<E> head;
        private Node<E> tail;
        private int size = 0;

        private static class Node<E> {
            Node<E> nextNode;
            Node<E> prevNode;
            E value;
        }

        public void addFirst(E value) {
            Node<E> newNode = new Node<>();
            newNode.value = value;

            // check if deque is empty
            if (head == null) {
                tail = newNode;
            } else {
                newNode.nextNode = head;
                head.prevNode = newNode;
            }
            head = newNode;
            size++;
        }

        public void addLast(E value) {
            Node<E> newNode = new Node<>();
            newNode.value = value;

            // check if deque is empty
            if (tail == null) {
                head = newNode;
            } else {
                tail.nextNode = newNode;
                newNode.prevNode = tail;
            }
            tail = newNode;
            size++;
        }

        public E removeFirst() {
            // check if deque is empty
            if (head == null) {
                return null;
            }
            Node<E> firstNode = head;
            head = head.nextNode;
            if (head != null) {
                head.prevNode = null;
            }
            firstNode.nextNode = null;
            // check if deque is empty after removing first
            if (head == null) {
                tail = null;
            }
            size--;
            return firstNode.value;
        }

        public E removeLast() {
            // check if deque is empty
            if (tail == null) {
                return null;
            }
            Node<E> lastNode = tail;
            Node<E> prevNode = tail.prevNode;
            // check if last node is single
            if (prevNode != null) {
                prevNode.nextNode = null;
            }
            lastNode.prevNode = null;
            tail = prevNode;
            // check if deque is empty after removing last
            if (tail == null) {
                head = null;
            }
            size--;
            return lastNode.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        QueueImplementation<Integer> queueImplementation = new QueueImplementation<>();
        queueImplementation.enqueue(1);
        queueImplementation.enqueue(2);
        queueImplementation.enqueue(3);
        queueImplementation.dequeue();
        queueImplementation.dequeue();
        queueImplementation.dequeue();

        DequeImplementation<Integer> dequeImplementation = new DequeImplementation<>();
        dequeImplementation.addFirst(1);
        dequeImplementation.addFirst(2);
        dequeImplementation.addFirst(3);
        dequeImplementation.removeFirst();
        dequeImplementation.removeFirst();
        dequeImplementation.removeFirst();
        dequeImplementation.addLast(4);
        dequeImplementation.addLast(5);
        dequeImplementation.addLast(6);
        dequeImplementation.removeLast();
        dequeImplementation.removeLast();
        dequeImplementation.removeLast();
    }
}
