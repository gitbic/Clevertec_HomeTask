package ru.clevertec.customlibs.linkedlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class NewLinkedListTest {
    NewLinkedList<String> list;

    @BeforeEach
    public void createNewLinkedList() {
        list = new NewLinkedList<>();
    }

    @AfterEach
    public void clearNewLinkedList() {
        list = null;
    }

    @Test
    public void testAddToList() {
        assertTrue(list.add("one"));
        assertTrue(list.add("two"));
        assertTrue(list.add("three"));
    }

    @Test
    public void testGetFromList() {
        fillNewLinkedList();
        assertEquals("element 0", list.get(0));
        assertEquals("element 2", list.get(2));
        assertEquals("element 9", list.get(9));
    }

    @Test
    public void testAddToListToIndex() {
        fillNewLinkedList();
        list.add(0, "zero");
        list.add(4, "four");
        list.add(12, "twelve");

        assertEquals("zero", list.get(0));
        assertEquals("element 2", list.get(3));
        assertEquals("four", list.get(4));
        assertEquals("element 6", list.get(8));
        assertEquals("twelve", list.get(12));
    }

    @Test
    public void testRemoveFromList() {
        list.add("zero");
        assertEquals("zero", list.remove(0));

        fillNewLinkedList();

        assertEquals("element 0", list.remove(0));
        assertEquals("element 1", list.get(0));

        assertEquals("element 5", list.remove(4));
        assertEquals("element 6", list.get(4));

        assertEquals("element 9", list.remove(7));
        assertEquals("element 8", list.get(6));
    }

    @Test
    public void testSetToList() {
        list.add("zero");
        assertEquals("zero", list.set(0, "z"));
        assertEquals("z", list.get(0));
        list.clear();

        fillNewLinkedList();

        assertEquals("element 0", list.set(0, "zero"));
        assertEquals("zero", list.get(0));

        assertEquals("element 5", list.set(5, "five"));
        assertEquals("five", list.get(5));

        assertEquals("element 9", list.set(9, "nine"));
        assertEquals("nine", list.get(9));
    }

    @Test
    public void testGetListSize() {
        assertEquals(0, list.size());
        fillNewLinkedList();
        assertEquals(10, list.size());
        list.add("one");
        assertEquals(11, list.size());
        fillNewLinkedList();
        assertEquals(21, list.size());
        list.remove(4);
        assertEquals(20, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testListToString() {
        list.add("one");
        list.add("two");
        list.add("three");
        assertEquals("[one, two, three]", list.toString());

    }

    @Test
    public void testListAddThrowsIOBException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(list.size() + 1, "one"));

        fillNewLinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "one"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(list.size() + 1, "one"));
    }

    @Test
    public void testListGetThrowsIOBException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));

        fillNewLinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    public void testListRemoveThrowsIOBException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));

        fillNewLinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    public void testListSetThrowsIOBException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(list.size(), "one"));

        fillNewLinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(list.size(), "one"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "one"));
    }


    private void fillNewLinkedList() {
        for (int i = 0; i < 10; i++) {
            list.add("element " + i);
        }
    }

}
