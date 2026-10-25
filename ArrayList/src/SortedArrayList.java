import java.util.Iterator;
import java.util.Collection;

/**
 * Provides a sorted version of ArrayList
 *
 * @param <E> the type of the list; cannot be a primitive data type
 *
 * @author Ace Breaux
 * @version 4-29-2025
 */
public class SortedArrayList<E extends Comparable<E>> implements Iterable<E>, SortedArrayListInterface<E> {
    /** Internal array to be used */
    private ArrayList<E> arrayList;
    /** Default total capacity for initial array */
    public static final int DEFAULT_CAPACITY = 100;

    /**
     * Creates a sorted list using a default capacity
     */
    public SortedArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates a sorted list using a given capacity
     *
     * @param capacity the capacity of the list; cannot be negative
     */
    public SortedArrayList(int capacity) {
        arrayList = new ArrayList<>(capacity);
    }

    /**
     * Retrieves the number of elements being maintained by the list
     *
     * @return the number of elements being maintained
     */
    public int size() {
        return arrayList.size();
    }

    /**
     * Retrieves whether the list is empty
     *
     * @return true, if there are no elements in the list; false, if there are elements
     */
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    /**
     * Clears the list; no elements will remain after the call, and size will be 0
     */
    public void clear() {
        arrayList.clear();
    }

    /**
     * Retrieves whether the specified element is in the list
     *
     * @param value the value to search for
     * @return true, if the element is in the list; false, if not
     */
    public boolean contains(E value) {
        return (indexOf(value) > -1);
    }

    /**
     * Uses a binary search to find the index of the first occurrence of the specified value,
     * or, if not found, the place that value should be
     *
     * @param value the value to search for
     * @return if found, the index of the value in the list (range 0 to size - 1);
     * if not found, an index representing where the value would go, if added, returned
     * as -(position+1), e.g., -1 means it goes at index 0, -5 means it goes at index 4
     */
    public int indexOf(E value) {
        int min = 0;
        int max = size() - 1;

        int mid;
        while (min <= max) {
            mid = (max + min) / 2;
            if (get(mid).equals(value)) {
                for (int i = mid; i > 0; i--) {
                    if (!get(i - 1).equals(get(i))) {
                        return i;
                    }
                }
                return min;
            } else if (get(mid).compareTo(value) < 0) {
                min = mid + 1;  // too small
            } else {   // numbers[mid] > value
                max = mid - 1;  // too large
            }
        }

        return -min - 1;   // not found
    }

    /**
     * Retrieves the element at the specified position in the list
     *
     * @param index the index (position) in the list; must be 0 to size-1
     * @return the element at the specified position
     */
    public E get(int index) {
        return arrayList.get(index);
    }

    /**
     * Retrieves an array of elements that compare themselves equally to the specified value (via compareTo),
     * with results being stored in the array specified.
     *
     * @param value    the element being sought; will be used to compareTo() other elements
     * @param template a template array used to create results; pass in a 0-sized array
     * @return a new array that is right-sized and contains element references, if any
     */
    public E[] get(E value, E[] template) {
        if (value == null || template == null) {
            throw new NullPointerException("Value or template cannot be null");
        }

        ArrayList<E> equalsList = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (get(i).compareTo(value) == 0) {
                equalsList.add(value);
            }
        }

        return equalsList.toArray(template);
    }

    /**
     * Adds a new element to the list, maintaining sorting via natural order (via compareTo)
     *
     * @param value the value to add to the list
     */
    public void add(E value) {
        int index = indexOf(value);

        if (index < 0) {
            index = Math.abs(index) - 1;
        }

        arrayList.add(index, value);
    }

    /**
     * Removes from the list the element at the specified index
     *
     * @param index the index in the list; must be in range 0 to size-1
     */
    public void remove(int index) {
        arrayList.remove(index);
    }

    /**
     * Retrieves an iterator over list elements; for/each loops are also supported
     *
     * @return a strongly typed iterator over list elements
     */
    public Iterator<E> iterator() {
        return arrayList.iterator();
    }

    /**
     * Retrieves an array representing the contents of the list
     *
     * @param array an array of the exact type to be returned, e.g., if E is String,
     *              the caller can pass in as an argument: new String[0]
     * @return an array of the elements in the sorted array list
     */
    public E[] toArray(E[] array) {
        return arrayList.toArray(array);
    }

    /**
     * Adds all values from a collection to the end of the list
     *
     * @param other a collection of values to be added
     */
    public void addAll(Collection<E> other) {
        if (other == null) {
            throw new NullPointerException("Collection must not be null");
        }

        for (E value : other) {
            add(value);
        }
    }
}
