package ru.clevertec.utils.customList;

import ru.clevertec.Node;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CustomLinkedList<E> implements List<E> {
    private int size;
    private ru.clevertec.Node<E> firstNode;
    private ru.clevertec.Node<E> lastNode;

    public static final String UNSUPPORTED_OPERATION = "Unsupported operation.";

    {
        initialize();
    }

    @Override
    public boolean add(E thisElement) {
        ru.clevertec.Node<E> previousNode = lastNode;
        ru.clevertec.Node<E> nextNode = null;

        ru.clevertec.Node<E> newNode = new ru.clevertec.Node<E>(thisElement, previousNode, nextNode);

        if (size == 0) {
            firstNode = newNode;
        } else {
            previousNode.setNextNode(newNode);
        }
        lastNode = newNode;

        size++;
        return true;
    }

    @Override
    public void add(int index, E thisElement) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == 0 || index == size) {
            add(thisElement);
        } else {

            ru.clevertec.Node<E> previousNode;
            ru.clevertec.Node<E> nextNode;
            ru.clevertec.Node<E> newNode;

            if (index == 0) {
                previousNode = null;
                nextNode = firstNode;
                newNode = new ru.clevertec.Node<>(thisElement, previousNode, nextNode);
                nextNode.setPreviousNode(newNode);
                firstNode = newNode;
            } else {
                ru.clevertec.Node<E> nodeByIndex = findNodeByIndex(index);
                previousNode = nodeByIndex.getPreviousNode();
                nextNode = nodeByIndex;
                newNode = new ru.clevertec.Node<>(thisElement, previousNode, nextNode);
                previousNode.setNextNode(newNode);
                nextNode.setPreviousNode(newNode);
            }
            size++;
        }
    }

    @Override
    public E get(int index) {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return findNodeByIndex(index).getElement();
    }

    @Override
    public E remove(int index) {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        ru.clevertec.Node<E> node = null;

        if (size == 1) {
            node = firstNode;
            firstNode = null;
            lastNode = null;

        } else if (index == 0) {
            node = firstNode;
            firstNode = firstNode.getNextNode();
            firstNode.setPreviousNode(null);

        } else if (index == size - 1) {
            node = lastNode;
            lastNode = lastNode.getPreviousNode();
            lastNode.setNextNode(null);

        } else {
            node = findNodeByIndex(index);
            ru.clevertec.Node<E> previousNode = node.getPreviousNode();
            ru.clevertec.Node<E> nextNode = node.getNextNode();

            previousNode.setNextNode(nextNode);
            nextNode.setPreviousNode(previousNode);
        }

        size--;
        return node.getElement();
    }

    @Override
    public E set(int index, E element) {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        ru.clevertec.Node<E> node;

        if (size == 1) {
            node = firstNode;
        } else {
            node = findNodeByIndex(index);
        }

        E elementForDelete = node.getElement();
        node.setElement(element);

        return elementForDelete;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        initialize();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        ru.clevertec.Node<E> node = firstNode;

        for (int i = 0; i < size - 1; i++) {
            sb.append(node.getElement().toString());
            sb.append(", ");
            node = node.getNextNode();
        }
        sb.append(node.getElement().toString());

        sb.append("]");
        return sb.toString();
    }

    private ru.clevertec.Node<E> findNodeByIndex(int index) {
        ru.clevertec.Node<E> nodeByIndex = null;

        if (index <= size / 2 - 1) {
            nodeByIndex = firstNode;
            for (int i = 0; i < index; i++) {
                nodeByIndex = nodeByIndex.getNextNode();
            }
        } else {
            nodeByIndex = lastNode;
            for (int i = size - 1; i > index; i--) {
                nodeByIndex = nodeByIndex.getPreviousNode();
            }
        }
        return nodeByIndex;
    }

    private void initialize() {
        firstNode = null;
        lastNode = null;
        size = 0;
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
        ReadWriteLock lock = new ReentrantReadWriteLock();

        return new ListIterator<E>() {
            ru.clevertec.Node<E> currentNode = firstNode;

            @Override
            public boolean hasNext() {
                if (currentNode != null) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public E next() {
                Node<E> node = currentNode;
                currentNode = currentNode.getNextNode();
                return node.getElement();
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
