package ru.clevertec.customlibs.linkedlist;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NewLinkedList<E> implements List<E> {
    private int size;
    private Node<E> firstNode;
    private Node<E> lastNode;

    {
        init();
    }

    @Override
    public boolean add(E thisElement) {
        Node<E> previousNode = lastNode;
        Node<E> nextNode = null;

        Node<E> newNode = new Node<E>(thisElement, previousNode, nextNode);

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

            Node<E> previousNode;
            Node<E> nextNode;
            Node<E> newNode;

            if (index == 0) {
                previousNode = null;
                nextNode = firstNode;
                newNode = new Node<>(thisElement, previousNode, nextNode);
                nextNode.setPreviousNode(newNode);
                firstNode = newNode;
            } else {
                Node<E> nodeByIndex = findNodeByIndex(index);
                previousNode = nodeByIndex.getPreviousNode();
                nextNode = nodeByIndex;
                newNode = new Node<>(thisElement, previousNode, nextNode);
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

        Node<E> node = null;

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
            Node<E> previousNode = node.getPreviousNode();
            Node<E> nextNode = node.getNextNode();

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

        Node<E> node;

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
        init();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<E> node = firstNode;

        for (int i = 0; i < size - 1; i++) {
            sb.append(node.getElement().toString());
            sb.append(", ");
            node = node.getNextNode();
        }
        sb.append(node.getElement().toString());

        sb.append("]");
        return sb.toString();
    }

    private Node<E> findNodeByIndex(int index) {
        Node<E> nodeByIndex = null;

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

    private void init() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    //----------

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {

        return new ListIterator<E>() {
            Node<E> currentNode = firstNode;

            @Override
            public boolean hasNext() {
                if (size > 0 && currentNode != null) {
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
                return false;
            }

            @Override
            public E previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(E e) {

            }

            @Override
            public void add(E e) {

            }
        };
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

}
