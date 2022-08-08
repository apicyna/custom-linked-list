package com.lessons.customLinkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class CustomLinkedList<Element> implements Iterable<Element> {

    private Node<Element> first;
    private Node<Element> last;
    private int size = 0;

    public Element getFirst() {
        return first.item;
    }

    public Element getLast() {
        return last.item;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    public Element get(int index) {
        if (!(index < size && index >= 0)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        return getNode(index).item;
    }

    public int getSize() {
        return size;
    }

    private Node<Element> getNode(int index) {
        Node<Element> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void add(Element element) {
        if (first == null) {
            Node<Element> node = new Node<>(null, element,null);
            first = node;
            last = node;
        } else {
            Node<Element> node = new Node<>(last, element, null);
            last.next = node;
            last = node;
        }
        size++;
    }

    public void addFirst(Element element) {
        if (first == null) {
            Node<Element> node = new Node<>(null, element,null);
            first = node;
            last = node;
        } else {
            Node<Element> node = new Node<>(null, element, first);
            first.prev = node;
            first = node;
        }
        size++;
    }

    private void delete(Node<Element> remote) {
        Element element = remote.item;
        Node<Element> prev = remote.prev;
        Node<Element> next = remote.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            remote.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            remote.next = null;
        }

        size--;
        remote.item = null;
    }

    public void remove(Element element) {
        for (Node<Element> remote = first; remote != null; remote = remote.next) {
            if (remote.item.equals(element)) {
                delete(remote);
                return;
            }
        }
    }

    public void remove(int index) {
        if (!(index < size && index >= 0)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        delete(getNode(index));
    }

    private static class Node<Element> {
        Element item;
        Node<Element> next;
        Node<Element> prev;

        Node(Node<Element> prev, Element element, Node<Element> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public Iterator<Element> iterator() {
        return new Iterator<Element>() {
            private Node<Element> nextElement = first;
            private Node<Element> lastReturned;



            @Override
            public boolean hasNext() {
                return nextElement != null;
            }

            @Override
            public Element next() {
                Element lastElement = nextElement.item;
                lastReturned = nextElement;
                if (hasNext()) {
                    nextElement = nextElement.next;
                }

                return lastElement;
            }

            @Override
            public void remove() {
                Node<Element> lastNext = lastReturned.next;
                delete(lastReturned);
                lastReturned = null;
                nextElement = lastNext;
            }
        };
    }

}
