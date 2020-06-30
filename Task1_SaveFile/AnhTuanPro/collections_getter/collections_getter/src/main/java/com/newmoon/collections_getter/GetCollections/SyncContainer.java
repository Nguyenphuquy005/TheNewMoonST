package com.newmoon.collections_getter.GetCollections;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SyncContainer<E> {
    private Queue<E> clQueue = new ConcurrentLinkedQueue<>();

    public boolean isReady() {
        return clQueue.peek() != null;
    }

    public E getAndPut() {
        E temp = clQueue.poll();
        clQueue.add(temp);
        return temp;
    }

    public E get() {
        return clQueue.poll();
    }

    public void put(E str) {
        clQueue.add(str);
    }

    public int getSize() {
        return clQueue.size();
    }

    public boolean isEmpty() {
        return clQueue.isEmpty();
    }

    public void addAll(Collection<E> collection) {
        clQueue.addAll(collection);
    }
}
