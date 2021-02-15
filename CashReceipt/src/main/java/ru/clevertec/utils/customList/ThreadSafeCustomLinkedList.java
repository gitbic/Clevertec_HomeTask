package ru.clevertec.utils.customList;

import ru.clevertec.CustomLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static ru.clevertec.CustomLinkedList.UNSUPPORTED_OPERATION;

public class ThreadSafeCustomLinkedList<E> implements List<E> {

    private final List<E> customLinkedList;
    private final ReadWriteLock lock;

    public ThreadSafeCustomLinkedList() {
        customLinkedList = new CustomLinkedList<>();
        lock = new ReentrantReadWriteLock();
    }

    public ThreadSafeCustomLinkedList(List<E> customLinkedList, ReadWriteLock lock) {
        this.customLinkedList = customLinkedList;
        this.lock = lock;
    }

    @Override
    public boolean add(E thisElement) {
        try {
            lock.writeLock().lock();
            return customLinkedList.add(thisElement);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void add(int index, E thisElement) {
        try {
            lock.writeLock().lock();
            customLinkedList.add(index, thisElement);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public E get(int index) {
        try {
            lock.readLock().lock();
            return customLinkedList.get(index);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public E remove(int index) {
        try {
            lock.writeLock().lock();
            return customLinkedList.remove(index);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public E set(int index, E element) {
        try {
            lock.writeLock().lock();
            return customLinkedList.set(index, element);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public int size() {
        try {
            lock.readLock().lock();
            return customLinkedList.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void clear() {
        try {
            lock.writeLock().lock();
            customLinkedList.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public String toString() {
        try {
            lock.readLock().lock();
            return customLinkedList.toString();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public ListIterator<E> listIterator() {

        try {
            lock.writeLock().lock();
            return new ListIterator<E>() {
                final int size = customLinkedList.size();
                final AtomicInteger index = new AtomicInteger(0);
                final Iterator<E> iterator = customLinkedList.listIterator();

                @Override
                public boolean hasNext() {
                    try {
                        lock.writeLock().lock();
                        return index.get() < size;
                    } finally {
                        index.getAndAdd(1);
                        lock.writeLock().unlock();
                    }
                }

                @Override
                public E next() {
                    try {
                        lock.writeLock().lock();
                        return iterator.next();
                    } finally {
                        lock.writeLock().unlock();
                    }
                }

                @Override
                public boolean hasPrevious() {
                    throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
                }

                @Override
                public E previous() {
                    throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
                }

                @Override
                public int nextIndex() {
                    throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
                }

                @Override
                public int previousIndex() {
                    throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
                }

                @Override
                public void set(E e) {
                    throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
                }

                @Override
                public void add(E e) {
                    throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
                }
            };
        } finally {
            lock.writeLock().unlock();
        }

    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }
}
