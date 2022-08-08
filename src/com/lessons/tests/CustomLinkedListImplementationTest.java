package com.lessons.tests;

import com.lessons.customLinkedList.CustomLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;


public class CustomLinkedListImplementationTest extends CustomLinkedList {


    @Test
    void getTest() {
        CustomLinkedList<String> linkedList = new CustomLinkedList<>();
        linkedList.add("I love Spring");
        linkedList.add("I love Java");
        linkedList.add("I love concurrency");

        Assertions.assertEquals(linkedList.getFirst(), "I love Spring");
        Assertions.assertEquals(linkedList.getLast(), "I love concurrency");
        Assertions.assertEquals(linkedList.getSize(), 3);
        Assertions.assertEquals(linkedList.get(1), "I love Java" );
    }

    @Test
    void addTest() {
        CustomLinkedList<String> linkedList = new CustomLinkedList<>();
        linkedList.add("I love Spring");
        linkedList.add("I love Java");
        linkedList.add("I love concurrency");

        linkedList.addFirst("I love tik tok");
        Assertions.assertEquals(linkedList.getFirst(), "I love tik tok");

        linkedList.add("Lori is a cat");
        Assertions.assertEquals(linkedList.getLast(), "Lori is a cat");
    }

    @Test
    void removeTest() {
        CustomLinkedList<String> linkedList = new CustomLinkedList<>();
        linkedList.add("I love Spring");
        linkedList.add("I love Java");
        linkedList.add("I love concurrency");
        linkedList.add("I love Java");
        linkedList.add("I love Java very much");

        linkedList.remove(3);
        Assertions.assertEquals(linkedList.get(3),"I love Java very much" );

        linkedList.remove("I love concurrency");
        Assertions.assertEquals(linkedList.getSize(), 3);

    }

    @Test
    void iteratorTest() {
        CustomLinkedList<String> linkedList = new CustomLinkedList<>();
        linkedList.add("I love Spring");
        linkedList.add("I love Java");
        linkedList.add("I love concurrency");

        int i = 0;
        for (Iterator<String> iterator = linkedList.iterator(); iterator.hasNext(); ) {
            Assertions.assertEquals(iterator.next(), linkedList.get(i));
            if (linkedList.get(i) == "I love Java") {
                iterator.remove();
                i--;
            }
            i++;
        }

        Assertions.assertEquals(linkedList.getSize(), 2);
        Assertions.assertEquals(linkedList.getFirst(), "I love Spring");
        Assertions.assertEquals(linkedList.getLast(), "I love concurrency");
    }

    @Test
    void anotherIteratorTest() {
        CustomLinkedList<String> linkedList = new CustomLinkedList<>();
        linkedList.add("I love Spring");
        linkedList.add("I love Java");
        linkedList.add("I love concurrency");

        Iterator<String> iterator = linkedList.iterator();


        for (String element : linkedList) {
            Assertions.assertEquals(iterator.next(), element);
            if (element == "I love concurrency") {
                iterator.remove();
            }
        }

        Assertions.assertEquals(linkedList.getSize(), 2);
        Assertions.assertEquals(linkedList.getFirst(), "I love Spring");
        Assertions.assertEquals(linkedList.getLast(), "I love Java");
    }
}

