// Name: Jason Manuel
// Class ID: 164
// Assignment number: 2
// Description: Tests of methods in the SimpleList class.

package cse360assign2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Contains JUnit tests of the SimpleTest class. The functionality tested is
 * described in the assignment description.
 *
 * @see SimpleList
 */
public class SimpleListTest {
    private SimpleList testList;

    @Before
    public void createSimpleList() {
        testList = new SimpleList();
    }

    @Test
    public void addToIncreaseCount() {
        testList.add(42);
        assertEquals(1, testList.count());
    }

    @Test
    public void addPastInitialCapacity() {
        for (int iteration = 0; iteration < 20; iteration++) {
            testList.add(42);
        }
        assertEquals(20, testList.count());
    }

    @Test
    public void remove() {
        testList.add(42);
        testList.remove(42);
        assertEquals(0, testList.count());
    }

    @Test
    public void removeFirstOccurrence() {
        testList.add(42);
        testList.add(24);
        testList.add(42);
        testList.remove(42);
        assertEquals("24 42", testList.toString());
    }

    @Test
    public void removeNoOccurrence() {
        testList.remove(42);
        assertEquals(0, testList.count());
    }

    @Test
    public void count() {
        testList.add(42);
        testList.add(359);
        testList.add(1729);
        testList.remove(42);
        assertEquals(2, testList.count());
    }

    @Test
    public void toString1() {
        testList.add(42);
        testList.add(359);
        testList.add(1729);
        assertEquals("1729 359 42", testList.toString());
    }

    @Test
    public void emptyListToString() {
        assertEquals("", testList.toString());
    }

    @Test
    public void searchForNoOccurrence() {
        assertEquals(SimpleList.NOT_FOUND, testList.search(42));
    }

    @Test
    public void searchForFirstOccurrence() {
        testList.add(42);
        testList.add(42);
        assertEquals(0, testList.search(42));
    }

    @Test
    public void createEmptySimpleList() {
        assertEquals(0, testList.count());
    }

    @Test
    public void appendToIncreaseCount() {
        testList.append(42);
        assertEquals(1, testList.count());
    }

    @Test
    public void getFirstOfEmptyList() {
        assertEquals(SimpleList.NOT_FOUND, testList.first());
    }

    @Test
    public void getFirstOfNonEmptyList() {
        testList.append(42);
        assertEquals(42, testList.first());
    }

    @Test
    public void getLastOfEmptyList() {
        assertEquals(SimpleList.NOT_FOUND, testList.last());
    }

    @Test
    public void getLastOfNonEmptyList() {
        testList.append(42);
        assertEquals(42, testList.last());
    }

    @Test
    public void shrinkListALot() {
        for (int iteration = 0; iteration < 10; iteration++) {
            testList.remove(0);
        }
        assertEquals(0, testList.size());
    }

    @Test
    public void removeWithoutShrinking() {
        for (int iteration = 0; iteration < 10; iteration++) {
            testList.append(0);
        }
        testList.remove(0);
        assertEquals(9, testList.size());
    }
}
