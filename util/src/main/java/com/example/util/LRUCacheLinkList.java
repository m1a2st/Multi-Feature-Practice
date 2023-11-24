package com.example.util;

import java.util.Hashtable;

/**
 * LRU Cache 刪除最近最少使用的頁面
 */
public class LRUCacheLinkList {

    private final Hashtable<Integer, Node> cache = new Hashtable<>();
    private final int capacity;
    private int size;
    private final Node head;
    private final Node tail;

    public LRUCacheLinkList(int capacity) {
        this.capacity = capacity;
        size = 0;
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
                Node last = tail.prev;
                cache.remove(last.key);
                removeLastNode();
                --size;
            }
        } else {
            node.value = value;
            removeToHead(node);
        }
    }

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }

    /**
     * add Node to head
     */
    private void addNodeToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * delete node
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * delete last node
     */
    private void removeLastNode() {
        removeNode(tail.prev);
    }

    /**
     * remove node to head
     */
    private void removeToHead(Node node) {
        removeNode(node);
        addNodeToHead(node);
    }

}
