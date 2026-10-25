import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Test cases for the SortedArrayList class
 *
 * @author Ace Breaux
 * @version 4-29-2025
 */
public class TestSortedArrayList {

    @Test
    public void testSize() {
        // Fill test lists
        SortedArrayList<String> test = new SortedArrayList<>();
        SortedArrayList<String> test2 = new SortedArrayList<>();
        test.add("Test");
        test.add("Test2");
        test.add("Test3");
        test.add("Test4");

        assertEquals(4, test.size());
        assertEquals(0, test2.size());
    }

    @Test
    public void testIsEmptyAndClear() {
        // Fill test list
        SortedArrayList<String> test = new SortedArrayList<>();
        test.add("Test");
        test.add("Test2");
        test.add("Test3");
        test.add("Test4");

        // Test isEmpty
        assertFalse(test.isEmpty());

        // Test clear
        test.clear();

        // Test isEmpty after list is cleared
        assertTrue(test.isEmpty());
    }

    @Test
    public void testContains() {
        // Fill test list
        SortedArrayList<String> test = new SortedArrayList<>();
        test.add("Test");

        // Test contains
        assertTrue(test.contains("Test"));
        assertFalse(test.contains("Test2"));
    }

    @Test
    public void indexOf() {
        // Fill test list
        SortedArrayList<String> test = new SortedArrayList<>();
        test.add("Animal");
        test.add("Animal");
        test.add("Animal");

        // Test indexOf
        assertEquals(0, test.indexOf("Animal"));
        assertEquals(-1, test.indexOf("AAnimal"));
        assertEquals(-4, test.indexOf("B"));

        // Add additional values
        test.add("Bee");
        test.add("Cat");

        // Test indexOf again
        assertEquals(3, test.indexOf("Bee"));
        assertEquals(4, test.indexOf("Cat"));

        assertThrows(NullPointerException.class,
                () -> test.get(null, new String[0]));
    }

    @Test
    public void testComplexGet() {
        // Fill test list
        SortedArrayList<String> test = new SortedArrayList<>();
        test.add("Animal");
        test.add("Animal");
        test.add("Bee");
        test.add("Animal");

        // Test the more complex get method
        assertEquals("[Animal, Animal, Animal]",
                Arrays.toString(test.get("Animal", new String[0])));
        assertEquals("[Animal, Animal, Animal, null, null]",
                Arrays.toString(test.get("Animal", new String[5])));
        assertEquals("[Bee]",
                Arrays.toString(test.get("Bee", new String[0])));
    }

    @Test
    public void testComplexGetException() {
        // Test if the more complex get method throws an exception
        // when asked to give an array of null type
        SortedArrayList<Integer> test = new SortedArrayList<>();
        assertThrows(NullPointerException.class,
                () -> Arrays.toString(test.get(5, null)));
        assertThrows(NullPointerException.class,
                () -> Arrays.toString(test.get(null, new Integer[0])));
    }

    @Test
    public void testAddAndRemoveAndGet() {
        // Note: tested all three "add", "remove", and simple "get" methods here
        //       because it made sense to use all of them alongside each other.

        // Fill test list (add)
        SortedArrayList<String> test = new SortedArrayList<>();
        test.add("Animal");
        test.add("Bee");
        test.add("Animal");
        test.add("Camel");

        // Test get
        assertEquals("Animal", test.get(0));
        assertEquals("Bee", test.get(2));
        assertEquals("Camel", test.get(3));
        // Test precondition for get
        assertThrows(IndexOutOfBoundsException.class, () -> test.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> test.get(4));

        // Add additional value
        test.add("Bear");

        // Test get after something else is added
        assertEquals("Bear", test.get(2));

        // Test remove
        test.remove(1);
        assertEquals("Bear", test.get(1));
        test.remove(1);
        assertEquals("Bee", test.get(1));
        // Test precondition for remove
        assertThrows(IndexOutOfBoundsException.class, () -> test.remove(-1));
    }

    @Test
    public void testIterator() {
        // Fill test list
        SortedArrayList<String> test = new SortedArrayList<>();
        test.add("Animal");

        // Instantiate iterator and make sure it works
        Iterator<String> iterator = test.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testToArrayString() {
        // Fill test list
        SortedArrayList<String> test = new SortedArrayList<>();
        test.add("Animal");
        test.add("Animal");
        test.add("Animal");

        // Test if toArray works
        assertEquals("[Animal, Animal, Animal]",
                Arrays.toString(test.toArray(new String[0])));
        assertEquals("[Animal, Animal, Animal, null, null, null]",
                Arrays.toString(test.toArray(new String[6])));

        // Add some additional values
        test.add("Bear");
        test.add("AAnimal");

        // Test if toArray works again
        assertEquals("[AAnimal, Animal, Animal, Animal, Bear]",
                Arrays.toString(test.toArray(new String[0])));
    }

    @Test
    public void testToArrayInteger() {
        // Fill test list
        SortedArrayList<Integer> test = new SortedArrayList<>();
        test.add(100);
        test.add(10);
        test.add(50);

        // Test if toArray works
        assertEquals("[10, 50, 100, null, null]", Arrays.toString(test.toArray(new Integer[5])));
        assertEquals("[10, 50, 100]", Arrays.toString(test.toArray(new Integer[0])));
    }

    @Test
    public void testToArrayException() {
        // Test if toArray throws an exception when asked to give an array of null type
        SortedArrayList<Integer> test = new SortedArrayList<>();
        assertThrows(NullPointerException.class, () -> Arrays.toString(test.toArray(null)));
    }

    @Test
    public void testAddAll() {
        // Filling a TreeSet (random collection I chose) with numbers
        TreeSet<Integer> treeTest = new TreeSet<>();
        treeTest.add(5);
        treeTest.add(7);
        treeTest.add(1);

        // Filling an SAL with some initial numbers
        SortedArrayList<Integer> salTest = new SortedArrayList<>();
        salTest.add(2);
        salTest.add(0);

        // Adding TreeSet to SAL
        salTest.addAll(treeTest);

        // Test if SAL contains all values from TreeSet
        assertTrue(salTest.contains(7));
        assertTrue(salTest.contains(5));
        assertTrue(salTest.contains(1));

        // Test if SAL still has proper ordering
        assertEquals(1, salTest.indexOf(1));
        assertEquals(3, salTest.indexOf(5));
        assertEquals(4, salTest.indexOf(7));
    }

    @Test
    public void testAddAllException() {
        // Test if addAll throws an exception when trying to access a null value
        SortedArrayList<String> test = new SortedArrayList<>();
        assertThrows(NullPointerException.class, () -> test.addAll(null));
    }
}
