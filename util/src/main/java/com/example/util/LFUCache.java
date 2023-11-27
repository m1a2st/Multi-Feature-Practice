package com.example.util;

import java.util.Hashtable;

public class LFUCache {

    private final Hashtable<Integer, Node> cache = new Hashtable<>();
    private int capacity, size;
    private Node head, tail;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        removeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNodeToHead(newNode);
            size++;
            if (size > capacity) {
                Node leastFreqNode = removeLeastFreqNode();
                cache.remove(leastFreqNode.key);
                size--;
            }
        } else {
            node.value = value;
            removeToHead(node);
        }
    }

    class Node {
        int key;
        int value;
        int count;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {}
    }

    /** add Node to head */
    private void addNodeToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.count++;
    }

    /** delete node */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /** delete last node */
    private void removeLastNode() {
        removeNode(tail.prev);
    }

    /** remove node to head */
    private void removeToHead(Node node) {
        removeNode(node);
        addNodeToHead(node);
    }

    /** remove least frequency node */
    private Node removeLeastFreqNode() {
        // 因為 head.next 是剛插入的節點所以不參與查找
        Node pNode = head.next.next;
        // 找到鍊表訪問頻率最低節點
        int tempFreq = tail.prev.count;
        while (pNode != null && pNode.count > 0) {
            if (tempFreq > pNode.count) {
                tempFreq = pNode.count;
            }
            pNode = pNode.next;
        }
        pNode = tail.prev;
        while (pNode.count > 0 && pNode.count != tempFreq) {
            pNode = pNode.prev;
        }
        removeNode(pNode);
        return pNode;
    }
}
