package datastructure;

public class LinkedList<E> {

     private static class SinglyLinkedListImplementation<E> {

          private Node<E> head;
          private int size = 0;

          private static class Node<E> {
               Node<E> nextNode;
               E value;
          }

          public void push(E value) {
               Node<E> newNode = new Node<>();
               newNode.nextNode = head;
               newNode.value = value;
               head = newNode;
               size++;
          }

          public void append(E value) {
               Node<E> newNode = new Node<>();
               newNode.value = value;
               if (head == null) {
                    head = newNode;
                    return;
               }
               Node<E> lastNode = head;
               while (lastNode.nextNode != null) {
                    lastNode = lastNode.nextNode;
               }
               lastNode.nextNode = newNode;
               size++;
          }

          public void remove(E value) {
               if (head == null) {
                    return;
               }
               Node<E> previousNode = null;
               Node<E> searchedNode = head;
               while (!searchedNode.value.equals(value) && searchedNode.nextNode != null) {
                    previousNode = searchedNode;
                    searchedNode = searchedNode.nextNode;
               }
               if (!searchedNode.value.equals(value)) {
                    return;
               }
               if (previousNode == null) {
                    head = searchedNode.nextNode;
               } else {
                    previousNode.nextNode = searchedNode.nextNode;
               }
               size--;
          }

          public boolean isEmpty() {
               return size == 0;
          }

          public int size() {
               return size;
          }
     }

     private static class CircularLinkedListImplementation<E> {

          private Node<E> last;
          private int size = 0;

          private static class Node<E> {
               Node<E> nextNode;
               E value;
          }

          public void push(E value) {
               if (last == null) {
                    Node<E> newNode = new Node<>();
                    newNode.value = value;
                    last = newNode;
                    last.nextNode = last;
               } else {
                    Node<E> newNode = new Node<>();
                    newNode.value = value;
                    newNode.nextNode = last.nextNode;
                    last.nextNode = newNode;
               }
               size++;
          }
          public void append(E value) {
               if (last == null) {
                    Node<E> newNode = new Node<>();
                    newNode.value = value;
                    last = newNode;
                    last.nextNode = last;
               } else {
                    Node<E> newNode = new Node<>();
                    newNode.value = value;
                    newNode.nextNode = last.nextNode;
                    last.nextNode = newNode;
                    last = newNode;
               }
               size++;
          }
          public void remove(E value) {
               if (last == null) {
                    return;
               }
               Node<E> prevNode = null;
               Node<E> searchedNode = last;
               while (!searchedNode.value.equals(value)) {
                    // Break after iterating whole list
                    if (searchedNode.nextNode.equals(last)) {
                         break;
                    }
                    prevNode = searchedNode;
                    searchedNode = searchedNode.nextNode;
               }
               // Check if node with given value is found
               if (!searchedNode.value.equals(value)) {
                    return;
               }
               // Check if found node is single node
               if (searchedNode.equals(searchedNode.nextNode)) {
                    last = null;
               } else {
                    // Check if found node is last node
                    if (searchedNode.equals(last)) {
                         // Find previous node
                         searchedNode = searchedNode.nextNode;
                         while (!searchedNode.equals(last)) {
                              prevNode = searchedNode;
                              searchedNode = searchedNode.nextNode;
                         }
                         last = searchedNode.nextNode;
                    }
                    prevNode.nextNode = searchedNode.nextNode;
               }
               size--;
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

          public void push(E value) {
               Node<E> newNode = new Node<>();
               newNode.value = value;
               newNode.nextNode = head;
               if (head != null) {
                    head.prevNode = newNode;
               }
               head = newNode;
               size++;
          }

          public void append(E value) {
               Node<E> newNode = new Node<>();
               newNode.value = value;
               if (head == null) {
                    head = newNode;
                    return;
               }
               Node<E> lastNode = head;
               while (lastNode.nextNode != null) {
                    lastNode = lastNode.nextNode;
               }
               lastNode.nextNode = newNode;
               newNode.prevNode = lastNode;
               size++;
          }

          public void remove(E value) {
               if (head == null) {
                    return;
               }
               Node<E> searchedNode = head;
               while (!searchedNode.value.equals(value) && searchedNode.nextNode != null) {
                    searchedNode = searchedNode.nextNode;
               }
               if (!searchedNode.value.equals(value)) {
                    return;
               }
               Node<E> nextNode = searchedNode.nextNode;
               if (searchedNode.prevNode == null) {
                    head = nextNode;
                    if (nextNode != null) {
                         nextNode.prevNode = null;
                    }
               } else {
                    Node<E> prevNode = searchedNode.prevNode;
                    prevNode.nextNode = nextNode;
                    if (nextNode != null) {
                         nextNode.prevNode = prevNode;
                    }
               }
               size--;
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
          singlyLinkedListImplementation.push(10);
          singlyLinkedListImplementation.push(7);
          singlyLinkedListImplementation.push(3);
          singlyLinkedListImplementation.append(1);
          singlyLinkedListImplementation.append(11);
          singlyLinkedListImplementation.append(8);
          singlyLinkedListImplementation.remove(3);
          singlyLinkedListImplementation.remove(8);
          singlyLinkedListImplementation.remove(10);

          DoublyLinkedListImplementation<Integer> doublyLinkedListImplementation = new DoublyLinkedListImplementation<>();
          doublyLinkedListImplementation.push(20);
          doublyLinkedListImplementation.push(17);
          doublyLinkedListImplementation.push(13);
          doublyLinkedListImplementation.append(11);
          doublyLinkedListImplementation.append(21);
          doublyLinkedListImplementation.append(18);
          doublyLinkedListImplementation.remove(13);
          doublyLinkedListImplementation.remove(18);
          doublyLinkedListImplementation.remove(20);

          CircularLinkedListImplementation<Integer> circularLinkedListImplementation = new CircularLinkedListImplementation<>();
          circularLinkedListImplementation.push(5);
          circularLinkedListImplementation.push(3);
          circularLinkedListImplementation.append(11);
          circularLinkedListImplementation.append(9);
          circularLinkedListImplementation.remove(5);
          circularLinkedListImplementation.remove(3);
          circularLinkedListImplementation.remove(9);
     }
}

