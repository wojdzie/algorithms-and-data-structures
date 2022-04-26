package datastructure;

import java.util.ArrayList;
import java.util.List;

public class SkipList {

    private static class SkipListImplementation {

        private SkipNode head;
        private SkipNode tail;

        public SkipListImplementation() {
            head = new SkipNode(Integer.MIN_VALUE);
            tail = new SkipNode(Integer.MAX_VALUE);
            head.right = tail;
            tail.left = head;
        }

        public SkipNode search(int value) {
            SkipNode current = head;

            while (current != null) {
                while (current.right != null && current.right.value <= value) {
                    current = current.right;
                }
                if (current.value == value) {
                    break;
                }
                current = current.down;
            }
            return current;
        }

        public void insert(int value) {
            List<SkipNode> toUpdate = new ArrayList<>();
            SkipNode current = head;
            while (current != null) {
                while (current.right != null && current.right.value < value) {
                    current = current.right;
                }
                toUpdate.add(current);
                current = current.down;
            }

            int level = 0;
            SkipNode newNode = null;
            while (level == 0 || flipCoin()) {
                newNode = newNode == null ? new SkipNode(value) : new SkipNode(newNode.value);

                SkipNode nodeToUpdate;

                if (toUpdate.size() <= level) {
                    createNewLayer();
                    nodeToUpdate = head;
                } else {
                    nodeToUpdate = toUpdate.get(toUpdate.size() - level - 1);
                }

                newNode.right = nodeToUpdate.right;
                newNode.left = nodeToUpdate;

                newNode.right.left = newNode;
                nodeToUpdate.right = newNode;

                level++;
            }
        }

        public void delete(int value) {
            List<SkipNode> toUpdate = new ArrayList<>();

            SkipNode current = head;
            while (current != null) {
                while (current.right != null && current.right.value < value) {
                    current = current.right;
                }
                if (current.right != null && current.right.value == value) {
                    toUpdate.add(current);
                }
                current = current.down;
            }

            for (int i = 0; i < toUpdate.size(); i++) {
                SkipNode nodeToUpdate = toUpdate.get(i);
                SkipNode nodeToDelete = nodeToUpdate.right;

                nodeToUpdate.right = nodeToDelete.right;
                nodeToDelete.right.left = nodeToUpdate;

                nodeToDelete.up = null;
                nodeToDelete.down = null;
            }
        }

        private void createNewLayer() {
            SkipNode newHead = new SkipNode(Integer.MIN_VALUE);
            SkipNode newTail = new SkipNode(Integer.MAX_VALUE);

            newHead.right = newTail;
            newTail.left = newHead;

            head.up = newHead;
            newHead.down = head;
            head = newHead;

            tail.up = newTail;
            newTail.down = tail;
            tail = newTail;
        }

        private boolean flipCoin() {
            return Math.random() >= 0.5;
        }

        private static class SkipNode {

            private final int value;

            private SkipNode left;
            private SkipNode right;
            private SkipNode up;
            private SkipNode down;

            public SkipNode(int value) {
                this.value = value;
            }
        }
    }

    public static void main(String[] args) {
        SkipListImplementation list = new SkipListImplementation();
        list.insert(5);
        list.insert(10);
        list.insert(12);
        list.insert(1);
        list.insert(50);
        list.search(12);
        list.delete(10);
        list.delete(1);
    }
}
