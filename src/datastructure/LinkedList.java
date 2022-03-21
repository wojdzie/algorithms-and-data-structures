package datastructure;

public class LinkedList<E> {

     private static class SinglyLinkedListImplementation<E> {
          private Node<E> head;
          private int size = 0;

          private static class Node<E> {
               Node<E> nextNode;
               E value;
          }

          public void add(E value) {
               if (head == null) {
                    Node<E> newNode = new Node<>();
                    newNode.value = value;
                    head = newNode;
                    size++;
               } else {
                    Node<E> current = head;
                    while (current.nextNode != null) {
                         current = current.nextNode;
                    }
                    Node<E> newNode = new Node<>();
                    newNode.value = value;
                    current.nextNode = newNode;
                    size++;
               }
          }

          public void remove(E value) {
               if (head != null) {
                    Node<E> previous = null;
                    Node<E> current = head;
                    while (!current.value.equals(value) && current.nextNode != null) {
                         previous = current;
                         current = current.nextNode;
                    }
                    if (current.value.equals(value)) {
                         if (previous == null) {
                              head = current.nextNode;
                         } else {
                              previous.nextNode = current.nextNode;
                         }
                         size--;
                    }
               }
          }

          public boolean isEmpty() {
               return size == 0;
          }

          public int size() {
               return size;
          }
     }

     private static class DoublyLinkedListImplementation<E> {
          private Node<E> head;
          private int size = 0;

          private static class Node<E> {
               Node<E> nextNode;
               Node<E> prevNode;
               E value;
          }

          public void add(E value) {
               if (head == null) {
                    Node<E> newNode = new Node<>();
                    newNode.value = value;
                    head = newNode;
                    size++;
               } else {
                    Node<E> current = head;
                    while (current.nextNode != null) {
                         current = current.nextNode;
                    }
                    Node<E> newNode = new Node<>();
                    newNode.prevNode = current;
                    newNode.value = value;
                    current.nextNode = newNode;
                    size++;
               }
          }

          public void remove(E value) {
               if (head != null) {
                    Node<E> current = head;
                    while (!current.value.equals(value) && current.nextNode != null) {
                         current = current.nextNode;
                    }
                    if (current.value.equals(value)) {
                         Node<E> previous = current.prevNode;
                         Node<E> next = current.nextNode;
                         if (previous == null) {
                              head = next;
                         } else {
                              previous.nextNode = next;
                         }
                         if (next == null) {
                              previous.nextNode = null;
                         } else {
                              next.prevNode = previous;
                         }
                         size--;
                    }
               }
          }

          public boolean isEmpty() {
               return size == 0;
          }

          public int size() {
               return size;
          }


     }

     public static void main(String[] args) {
          SinglyLinkedListImplementation<Integer> singlyLinkedListImplementation = new SinglyLinkedListImplementation<>();
          singlyLinkedListImplementation.add(20);
          singlyLinkedListImplementation.add(17);
          singlyLinkedListImplementation.add(11);
          singlyLinkedListImplementation.add(18);
          singlyLinkedListImplementation.add(21);
          singlyLinkedListImplementation.remove(20);
          singlyLinkedListImplementation.remove(11);
          singlyLinkedListImplementation.remove(21);

          DoublyLinkedListImplementation<Integer> doublyLinkedListImplementation = new DoublyLinkedListImplementation<>();
          doublyLinkedListImplementation.add(10);
          doublyLinkedListImplementation.add(7);
          doublyLinkedListImplementation.add(1);
          doublyLinkedListImplementation.add(8);
          doublyLinkedListImplementation.add(11);
          doublyLinkedListImplementation.remove(10);
          doublyLinkedListImplementation.remove(1);
          doublyLinkedListImplementation.remove(11);
     }
}

