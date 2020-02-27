// Name: Jason Manuel
// Class ID: 164
// Assignment number: 1
// Description: A simple array-backed list class which supports adding,
//  searching for, and removing integers.

package cse360assign2;

/**
 * Implements a list of integers backed by an array. Supports adding and
 * removing integers, searching for the first occurrence of an integer, and
 * retrieving the current number of elements in the list. The maximum size of
 * the list is ten.
 */
public class SimpleList {
    private int[] list;
    private int count;
    private static final int INITIAL_CAPACITY = 10;
    public static final int NOT_FOUND = -1;

    public SimpleList() {
        list = new int[INITIAL_CAPACITY];
        count = 0;
    }

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
            int[] copy = new int[(int) (list.length * 1.5)];
            System.arraycopy(list, 0, copy, 0, list.length);
            list = copy;
        }
    }

    public void remove(int element) {
        int index = search(element);
        int copyLength = list.length - index - 1;
        if (index > NOT_FOUND) {
            System.arraycopy(list, index + 1, list, index, copyLength);
            count--;
        }

        if (count / (float) list.length < 0.75) {
            int newCapacity = (int) (list.length * 0.75);
            if (newCapacity >= 1) {
                int[] copy = new int[newCapacity];
                System.arraycopy(list, 0, copy, 0, count);
                list = copy;
            }
        }
    }

    public int count() {
        return count;
    }

    @Override
    public String toString() {
        String[] stringList = new String[count];
        for (int index = 0; index < count; index++) {
            stringList[index] = Integer.toString(list[index]);
        }
        return String.join(" ", stringList);
    }

    public int search(int element) {
        int indexOfElement = NOT_FOUND;
        for (int loopIndex = 0;
             loopIndex < count && indexOfElement <= NOT_FOUND;
             loopIndex++) {
            if (list[loopIndex] == element) {
                indexOfElement = loopIndex;
            }
        }
        return indexOfElement;
    }
}
