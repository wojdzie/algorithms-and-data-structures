package datastructure;

public class PriorityQueue<E extends Comparable<E>> {

    private int size = 0;
    private E[] array;

    public PriorityQueue(int initSize) {
        this.array = (E[]) new Comparable[initSize + 1];
    }

    public void insert(E item) {
        if (size < array.length) {
            array[++size] = item;
            swim(size);
        }
    }

    public E max() {
        return array[1];
    }

    public E delMax() {
        E max = array[1];
        array[1] = array[size--];
        array[size + 1] = null;
        sink(1);

        return max;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void swim(int position) {
        while (position > 1 && array[position].compareTo(array[position / 2]) > 0) {
            E temp = array[position];
            array[position] = array[position / 2];
            array[position / 2] = temp;

            position /= 2;
        }
    }

    private void sink(int position) {
        while (position * 2 <= size) {
            int childPosition = position * 2;
            if (childPosition < size && array[childPosition + 1].compareTo(array[childPosition]) > 0) {
                childPosition++;
            }
            if (array[position].compareTo(array[childPosition]) > 0) {
                break;
            }
            E temp = array[childPosition];
            array[childPosition] = array[position];
            array[position] = temp;

            position = childPosition;
        }
    }
}
