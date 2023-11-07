package com.example.spring;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LruDemo {

    @Test
    public void testSimpleLRU() {
        SimpleLRUCache lruCache = new SimpleLRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1)); // 1
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2)); // 2
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1)); // -1
        System.out.println(lruCache.get(3)); // 3
        System.out.println(lruCache.get(4)); // 4
    }

    @Test
    public void testLRU() {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1)); // 1
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2)); // 2
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1)); // -1
        System.out.println(lruCache.get(3)); // 3
        System.out.println(lruCache.get(4)); // 4
    }

    private class SimpleLRUCache extends LinkedHashMap<Integer, Integer> {
        private final int capacity;

        public SimpleLRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    private class Entry {
        private int key;
        private int value;
        private Entry pre;
        private Entry next;

        public Entry() {
        }

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private class LRUCache {
        // map 容器，空建換時間，保存 key 和 cacheNode 的映射，保證用 O(1) 的時間獲取 value
        private final Map<Integer, Entry> cacheMap = new HashMap<>();
        private final int capacity;
        // 雙向鏈表，保存 cacheNode，實現 O(1) 的插入和刪除
        private final Entry head;
        private final Entry tail;
        // 容器大小
        private int size;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.head = new Entry();
            this.tail = new Entry();
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            Entry entry = cacheMap.get(key);
            if (entry == null) {
                return -1;
            }
            moveToHead(entry);
            return entry.value;
        }

        public void put(int key, int value) {
            Entry node = cacheMap.get(key);
            if (node == null) {
                // 緩存中沒有，創建一個新的節點，放到頭部
                Entry newNode = new Entry(key, value);
                cacheMap.put(key, newNode);
                addNodeToHead(newNode);
                size++;
                // 判斷是否超出容量，超出的話，刪除尾部節點
                if (size > capacity) {
                    Entry tail = removeTail();
                    cacheMap.remove(tail.key);
                    size--;
                }
            } else {
                // 緩存中有，更新 value，並移動到頭部
                node.value = value;
                moveToHead(node);
            }
        }

        /**
         * 移動節點到頭部
         * 1. 先刪除節點
         * 2. 再插入節點到頭部
         */
        private void moveToHead(Entry node) {
            removeNode(node);
            addNodeToHead(node);
        }

        private Entry removeTail() {
            Entry node = tail.pre;
            removeNode(node);
            return node;
        }

        // 段開前後節點的連結
        private void removeNode(Entry node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private void addNodeToHead(Entry node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }
    }
}
