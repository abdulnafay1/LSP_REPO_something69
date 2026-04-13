package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test cases for IntegerSet.
 * Every method has at least one normal case and one edge case.
 */
public class IntegerSetTest {

    private IntegerSet setA;
    private IntegerSet setB;

    @BeforeEach
    public void setUp() {
        setA = new IntegerSet();
        setB = new IntegerSet();
    }

    // ---------------------------------------------------------------
    // clear()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("clear() - normal: removes all elements")
    public void testClearNormal() {
        setA.add(1);
        setA.add(2);
        setA.clear();
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("clear() - edge: clearing an already-empty set does nothing")
    public void testClearAlreadyEmpty() {
        setA.clear();
        assertTrue(setA.isEmpty());
        assertEquals(0, setA.length());
    }

    // ---------------------------------------------------------------
    // length()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("length() - normal: returns correct count")
    public void testLengthNormal() {
        setA.add(10);
        setA.add(20);
        setA.add(30);
        assertEquals(3, setA.length());
    }

    @Test
    @DisplayName("length() - edge: empty set returns 0")
    public void testLengthEmpty() {
        assertEquals(0, setA.length());
    }

    // ---------------------------------------------------------------
    // equals()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("equals() - normal: same elements same order")
    public void testEqualsNormal() {
        setA.add(1);
        setA.add(2);
        setB.add(1);
        setB.add(2);
        assertTrue(setA.equals(setB));
    }

    @Test
    @DisplayName("equals() - edge: same elements different order")
    public void testEqualsDifferentOrder() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(3);
        setB.add(1);
        setB.add(2);
        assertTrue(setA.equals(setB));
    }

    @Test
    @DisplayName("equals() - edge: different elements returns false")
    public void testEqualsMismatch() {
        setA.add(1);
        setA.add(2);
        setB.add(1);
        setB.add(99);
        assertFalse(setA.equals(setB));
    }

    // ---------------------------------------------------------------
    // contains()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("contains() - normal: value is present")
    public void testContainsPresent() {
        setA.add(5);
        assertTrue(setA.contains(5));
    }

    @Test
    @DisplayName("contains() - edge: value not present")
    public void testContainsAbsent() {
        setA.add(5);
        assertFalse(setA.contains(99));
    }

    // ---------------------------------------------------------------
    // largest()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("largest() - normal: returns max element")
    public void testLargestNormal() {
        setA.add(3);
        setA.add(1);
        setA.add(7);
        assertEquals(7, setA.largest());
    }

    @Test
    @DisplayName("largest() - edge: single element")
    public void testLargestSingleElement() {
        setA.add(42);
        assertEquals(42, setA.largest());
    }

    @Test
    @DisplayName("largest() - edge: empty set throws IllegalStateException")
    public void testLargestEmptyThrows() {
        assertThrows(IllegalStateException.class, () -> setA.largest());
    }

    // ---------------------------------------------------------------
    // smallest()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("smallest() - normal: returns min element")
    public void testSmallestNormal() {
        setA.add(10);
        setA.add(3);
        setA.add(7);
        assertEquals(3, setA.smallest());
    }

    @Test
    @DisplayName("smallest() - edge: single element")
    public void testSmallestSingleElement() {
        setA.add(42);
        assertEquals(42, setA.smallest());
    }

    @Test
    @DisplayName("smallest() - edge: empty set throws IllegalStateException")
    public void testSmallestEmptyThrows() {
        assertThrows(IllegalStateException.class, () -> setA.smallest());
    }

    // ---------------------------------------------------------------
    // add()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("add() - normal: adds new element")
    public void testAddNormal() {
        setA.add(5);
        assertTrue(setA.contains(5));
        assertEquals(1, setA.length());
    }

    @Test
    @DisplayName("add() - edge: duplicate value is not added twice")
    public void testAddDuplicate() {
        setA.add(5);
        setA.add(5);
        assertEquals(1, setA.length());
    }

    // ---------------------------------------------------------------
    // remove()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("remove() - normal: removes existing element")
    public void testRemoveNormal() {
        setA.add(10);
        setA.add(20);
        setA.remove(10);
        assertFalse(setA.contains(10));
        assertEquals(1, setA.length());
    }

    @Test
    @DisplayName("remove() - edge: removing value not present does nothing")
    public void testRemoveNotPresent() {
        setA.add(10);
        setA.remove(99);
        assertEquals(1, setA.length());
        assertTrue(setA.contains(10));
    }

    // ---------------------------------------------------------------
    // union()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("union() - normal: combines two non-empty sets")
    public void testUnionNormal() {
        setA.add(1);
        setA.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.union(setB);
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertEquals(4, result.length());
    }

    @Test
    @DisplayName("union() - edge: union with empty set returns copy of original")
    public void testUnionWithEmpty() {
        setA.add(1);
        setA.add(2);
        IntegerSet result = setA.union(setB);
        assertEquals(2, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }

    // ---------------------------------------------------------------
    // intersect()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("intersect() - normal: keeps common elements")
    public void testIntersectNormal() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.intersect(setB);
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertFalse(result.contains(1));
        assertFalse(result.contains(4));
        assertEquals(2, result.length());
    }

    @Test
    @DisplayName("intersect() - edge: no common elements returns empty set")
    public void testIntersectNoOverlap() {
        setA.add(1);
        setA.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.intersect(setB);
        assertTrue(result.isEmpty());
    }

    // ---------------------------------------------------------------
    // diff()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("diff() - normal: removes elements in setB from result")
    public void testDiffNormal() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(2);
        IntegerSet result = setA.diff(setB);
        assertTrue(result.contains(1));
        assertTrue(result.contains(3));
        assertFalse(result.contains(2));
    }

    @Test
    @DisplayName("diff() - edge: identical sets produce empty result")
    public void testDiffIdenticalSets() {
        setA.add(1);
        setA.add(2);
        setB.add(1);
        setB.add(2);
        IntegerSet result = setA.diff(setB);
        assertTrue(result.isEmpty());
    }

    // ---------------------------------------------------------------
    // complement()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("complement() - normal: elements in setB not in setA")
    public void testComplementNormal() {
        setA.add(1);
        setA.add(2);
        setB.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.complement(setB);
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertFalse(result.contains(1));
        assertFalse(result.contains(2));
    }

    @Test
    @DisplayName("complement() - edge: disjoint sets return all of setB")
    public void testComplementDisjoint() {
        setA.add(1);
        setA.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.complement(setB);
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertEquals(2, result.length());
    }

    // ---------------------------------------------------------------
    // isEmpty()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("isEmpty() - edge: empty set returns true")
    public void testIsEmptyTrue() {
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("isEmpty() - normal: non-empty set returns false")
    public void testIsEmptyFalse() {
        setA.add(1);
        assertFalse(setA.isEmpty());
    }

    // ---------------------------------------------------------------
    // toString()
    // ---------------------------------------------------------------

    @Test
    @DisplayName("toString() - normal: sorted format for populated set")
    public void testToStringNormal() {
        setA.add(3);
        setA.add(1);
        setA.add(2);
        assertEquals("[1, 2, 3]", setA.toString());
    }

    @Test
    @DisplayName("toString() - edge: empty set returns []")
    public void testToStringEmpty() {
        assertEquals("[]", setA.toString());
    }
}
