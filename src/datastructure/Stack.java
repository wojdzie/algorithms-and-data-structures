package datastructure;

public class Stack {

    private static class StackLinkedListImplementation<E> {

        private StackNode<E> lastNode;
        private int size = 0;

        private class StackNode<E> {
            private final StackNode<E> nextNode;
            private final E value;

            public StackNode(StackNode<E> nextNode, E value) {
                this.nextNode = nextNode;
                this.value = value;
            }
        }

        public void push(E value) {
            if (lastNode == null) {
                size++;
                lastNode = new StackNode<>(null, value);
            } else {
                size++;
                StackNode<E> currentLastNode = lastNode;
                lastNode = new StackNode<>(currentLastNode, value);
            }
        }

        public E pop() {
            if (lastNode == null) {
                return null;
            }
            size--;
            E value = lastNode.value;
            lastNode = lastNode.nextNode;
            return value;
        }

        public E peek() {
            if (lastNode == null) {
                return null;
            }
            return lastNode.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    private static class StackArrayImplementation<E> {

        private E[] array;
        private int size = 0;

        public StackArrayImplementation(int initialCapacity) {
            this.array = (E[]) new Object[initialCapacity];
        }

        public void push(E value) {
            if (size >= array.length / 2) {
                resize(array.length * 2);
            }
            array[size++] = value;
        }

        public E pop() {
            if (size == 0) {
                return null;
            }
            if (size <= array.length / 4) {
                resize(array.length / 2);
            }
            E value = array[--size];
            array[size] = null;
            return value;
        }

        public E peek() {
            if (size == 0) {
                return null;
            }
            return array[size - 1];
        }

        private void resize(int capacity) {
            E[] resizedArray = (E[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                resizedArray[i] = array[i];
            }
            this.array = resizedArray;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        StackLinkedListImplementation<Integer> stackLinkedListImplementation = new StackLinkedListImplementation<>();
        stackLinkedListImplementation.push(3);
        stackLinkedListImplementation.push(2);
        stackLinkedListImplementation.push(1);
        stackLinkedListImplementation.pop();
        stackLinkedListImplementation.pop();
        stackLinkedListImplementation.pop();

        StackArrayImplementation<Integer> stackArrayImplementation = new StackArrayImplementation<>(5);
        stackArrayImplementation.push(4);
        stackArrayImplementation.push(3);
        stackArrayImplementation.push(2);
        stackArrayImplementation.push(1);
        stackArrayImplementation.pop();
        stackArrayImplementation.pop();
        stackArrayImplementation.pop();
        stackArrayImplementation.pop();
    }
}
