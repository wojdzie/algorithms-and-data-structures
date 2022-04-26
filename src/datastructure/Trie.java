package datastructure;

import java.util.Arrays;
import java.util.Objects;

public class Trie {

    private static final int ALPHABET_SIZE = 26;

    private static class TrieImplementation {

        private TrieNode root = new TrieNode();

        private static class TrieNode {

            private TrieNode[] children = new TrieNode[ALPHABET_SIZE];
            private boolean isEndOfWord = false;
        }

        public void insert(String key) {
            final int length = key.length();

            TrieNode current = root;
            for (int i = 0; i < length; i++) {
                int index = key.charAt(i) - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.isEndOfWord = true;
        }

        public boolean search(String key) {
            final int length = key.length();

            TrieNode current = root;
            for (int i = 0; i < length; i++) {
                int index = key.charAt(i) - 'a';
                if (current.children[index] == null) {
                    return false;
                }
                current = current.children[index];
            }

            return current.isEndOfWord;
        }
        private boolean isEmpty(TrieNode trieNode) {
            return Arrays.stream(trieNode.children)
                    .noneMatch(Objects::nonNull);
        }

        public TrieNode remove(TrieNode node, String key, int depth) {
            if (node == null) {
                return null;
            }
            if (depth == key.length()) {
                if (node.isEndOfWord) {
                    node.isEndOfWord = false;
                }

                if (isEmpty(node)) {
                    node = null;
                }
                return node;
            }
            int index = key.charAt(depth) - 'a';
            node.children[index] = remove(node.children[index], key, depth + 1);

            if (isEmpty(node) && !node.isEndOfWord) {
                node = null;
            }
            return node;
        }
    }

    public static void main(String[] args) {
        TrieImplementation trie = new TrieImplementation();
        trie.insert("the");
        trie.insert("a");
        trie.insert("there");
        trie.search("the");
        trie.search("there");
        trie.search("a");
        trie.remove(trie.root, "there", 0);
    }
}
