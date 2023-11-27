package com.example.util;

public class ThirdHashMap<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;
    private Node[] buckets;

    public ThirdHashMap() {
        buckets = new Node[DEFAULT_INITIAL_CAPACITY];
        this.size = 0;
    }

    public ThirdHashMap(int capacity) {
        buckets = new Node[capacity];
        this.size = 0;
    }

    public void put(K key, V value) {
        if (size >= buckets.length * DEFAULT_LOAD_FACTOR) {
            resize();
        }
        putVal(key, value, buckets);
    }

    public V get(K key) {
        int index = getIndex(key, buckets.length);
        Node<K, V> node = buckets[index];
        while (node != null) {
            if (node.key.hashCode() == key.hashCode() && (node.key == key || node.key.equals(key))) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private void resize() {
        Node<K, V>[] newBuckets = new Node[buckets.length * 2];
        rehash(newBuckets);
        buckets = newBuckets;
    }

    private void rehash(Node<K, V>[] newBuckets) {
        size = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) {
                continue;
            }
            Node<K, V> node = buckets[i];
            while (node != null) {
                putVal(node.key, node.value, newBuckets);
                node = node.next;
            }
        }
    }

    private void putVal(K key, V value, Node<K, V>[] newBuckets) {
        int index = getIndex(key, newBuckets.length);
        Node<K, V> node = newBuckets[index];
        if (node == null) {
            newBuckets[index] = new Node<>(key, value);
            size++;
            return;
        }
        Node<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            prev = node;
            node = node.next;
        }
        prev.next = new Node<>(key, value);
        size++;
    }

    private int getIndex(K key, int length) {
        int hashCode = key.hashCode();
        int index = hashCode % length;
        return Math.abs(index);
    }

    class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
