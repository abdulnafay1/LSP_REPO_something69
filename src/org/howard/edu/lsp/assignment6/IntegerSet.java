package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a mathematical set of integers.
 * A set cannot contain duplicates and supports standard set operations.
 * All set operations return a new IntegerSet and do not modify the originals.
 */
public class IntegerSet {

    private ArrayList<Integer> set = new ArrayList<>();

    /** Default constructor */
    public IntegerSet() {}

    /**
     * Clears all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * @return size of the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if this set and set b contain exactly the same elements.
     * Order does not matter.
     * @param b the other IntegerSet
     * @return true if equal
     */
    public boolean equals(IntegerSet b) {
        ArrayList<Integer> copy1 = new ArrayList<>(set);
        ArrayList<Integer> copy2 = new ArrayList<>(b.set);
        Collections.sort(copy1);
        Collections.sort(copy2);
        return copy1.equals(copy2);
    }

    /**
     * Returns true if the set contains the given value.
     * @param value the integer to look for
     * @return true if found
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element in the set.
     * @return largest integer
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) throw new IllegalStateException("Set is empty");
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in the set.
     * @return smallest integer
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) throw new IllegalStateException("Set is empty");
        return Collections.min(set);
    }

    /**
     * Adds an item to the set if it is not already present.
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set if it exists.
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new set containing all elements from both sets.
     * @param intSetb the other IntegerSet
     * @return union of the two sets
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(set);
        for (int item : intSetb.set) {
            if (!result.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new set containing only elements common to both sets.
     * @param intSetb the other IntegerSet
     * @return intersection of the two sets
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(set);
        result.set.retainAll(intSetb.set);
        return result;
    }

    /**
     * Returns a new set containing elements in this set but not in intSetb.
     * @param intSetb the other IntegerSet
     * @return difference (this - intSetb)
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(set);
        result.set.removeAll(intSetb.set);
        return result;
    }

    /**
     * Returns a new set containing elements in intSetb but not in this set.
     * @param intSetb the other IntegerSet
     * @return complement (intSetb - this)
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(intSetb.set);
        result.set.removeAll(set);
        return result;
    }

    /**
     * Returns true if the set has no elements.
     * @return true if empty
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set in ascending order.
     * Format: [1, 2, 3] or [] if empty.
     * @return string representation
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        return sorted.toString();
    }
}