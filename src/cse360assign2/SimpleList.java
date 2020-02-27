// Name: Jason Manuel
// Class ID: 164
// Assignment number: 2
// Description: A simple array-backed list class which supports adding,
//  searching for, accessing first and last, and removing integers.

package cse360assign2;

/**
 * Implements a list of integers backed by an array. Supports adding and
 * removing integers, searching for the first occurrence of an integer,
 * accessing the first and last elements of the list, and retrieving the current
 * number of elements in the list. The internal size of the array adjusts based
 * on the size of the list.
 */
public class SimpleList {
    private int[] list;
    private int count;
    private static final int INITIAL_CAPACITY = 10;
    public static final int NOT_FOUND = -1;

    /**
     * Constructs a new, empty SimpleList object.
     */
    public SimpleList() {
        list = new int[INITIAL_CAPACITY];
        count = 0;
    }

    /**
     * Adds newElement to the beginning of the list, expanding the capacity of the
     * list if necessary.
     * 
     * @param newElement
     */
    public void add(int newElement) {
        expandIfNeeded();

        System.arraycopy(list, 0, list, 1, list.length - 1);
        list[0] = newElement;
        count++;
    }

    /**
     * Adds 50% to the capacity of the internal array by reallocation if the array
     * is full.
     */
    private void expandIfNeeded() {
        if (count == list.length) {
            int[] copy = new int[list.length + list.length / 2];
            System.arraycopy(list, 0, copy, 0, list.length);
            list = copy;
        }
    }

    /**
     * Removes the first occurrence of element from the list if it is present.
     * Elements after the remove element are shifted to the left to fill the new
     * empty space.
     * 
     * @param element
     */
    public void remove(int element) {
        int index = search(element);
        int copyLength = list.length - index - 1;
        if (index > NOT_FOUND) {
            System.arraycopy(list, index + 1, list, index, copyLength);
            count--;
        }

        // this tests if count/list.length < 0.75, but using only integer
        // arithmetic
        if (count * 4 < list.length * 3) {
            int newCapacity = list.length * 3 / 4;
            if (newCapacity >= 1) {
                int[] copy = new int[newCapacity];
                System.arraycopy(list, 0, copy, 0, count);
                list = copy;
            }
        }
    }

    /**
     * Returns the number of elements in the list.
     * 
     * @return Size of the list
     */
    public int count() {
        return count;
    }

    /**
     * Returns a string representation of the list where the elements are joined
     * together, in order, by spaces.
     * 
     * For example, calling toString() on a list containing 1, 2, and 3 (in that
     * order) returns the string "1 2 3".
     */
    @Override
    public String toString() {
        String[] stringList = new String[count];
        for (int index = 0; index < count; index++) {
            stringList[index] = Integer.toString(list[index]);
        }
        return String.join(" ", stringList);
    }

    /**
     * Searches for the first occurrence of element in the list and returns its
     * index. If element is not in the list, SimpleList.NOT_FOUND is returned.
     * 
     * @param element
     * @return the index of element in the list or SimpleList.NOT_FOUND
     */
    public int search(int element) {
        int indexOfElement = NOT_FOUND;
        for (int loopIndex = 0; loopIndex < count && indexOfElement <= NOT_FOUND; loopIndex++) {
            if (list[loopIndex] == element) {
                indexOfElement = loopIndex;
            }
        }
        return indexOfElement;
    }

    /**
     * Appends the given element to the end of list, increasing the capacity of the
     * list if necessary.
     * 
     * @param element
     */
    public void append(int element) {
        expandIfNeeded();
        list[count++] = element;
    }

    /**
     * Returns the first element in the list or SimpleList.NOT_FOUND if the list is
     * empty.
     * 
     * @return the first element in the list or SimpleList.NOT_FOUND
     */
    public int first() {
        int first = NOT_FOUND;
        if (count > 0) {
            first = list[0];
        }
        return first;
    }

    /**
     * Returns the last element in the list or SimpleList.NOT_FOUND if the list is
     * empty.
     * 
     * @return the last element of the list or SimpleList.NOT_FOUND
     */
    public int last() {
        int last = NOT_FOUND;
        if (count > 0) {
            last = list[count - 1];
        }
        return last;
    }

    /**
     * Returns the first empty index in the list. This is just the size of the list,
     * so this method is equivalent to the count method.
     * 
     * @return the size of the list
     */
    public int size() {
        // seems to be the same as count, according to a TA
        return count;
    }
}
