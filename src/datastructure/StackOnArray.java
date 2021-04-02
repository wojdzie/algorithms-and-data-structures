package datastructure;

public class StackOnArray<E> {

    private E[] array;
    private int nextIndex = 0;

    public StackOnArray(int initialCapacity) {
        this.array = (E[]) new Object[initialCapacity];
    }

    public void push(E item) {
        if (nextIndex == array.length) {
            resize(array.length * 2);
        }
        array[nextIndex++] = item;
    }

    public E pop() {
        if (nextIndex == 0)  return null;

        E item = array[--nextIndex];
        array[nextIndex] = null;

        if (nextIndex == array.length / 4) {
            resize(array.length / 2);
        }

        return item;
    }

    public int size() {
        return nextIndex;
    }

    public boolean isEmpty() {
        return nextIndex == 0;
    }

    private void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < nextIndex; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }
}
