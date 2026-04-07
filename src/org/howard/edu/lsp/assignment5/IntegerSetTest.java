package org.howard.edu.lsp.assignment5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerSetTest {

    private IntegerSet set1;
    private IntegerSet set2;

    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
    }

    // ── clear() ──────────────────────────────────────────────────────────
    @Test
    @DisplayName("Test clear on non-empty set")
    public void testClear() {
        set1.add(1);
        set1.add(2);
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("Test clear on already empty set")
    public void testClearAlreadyEmpty() {
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    // ── length() ─────────────────────────────────────────────────────────
    @Test
    @DisplayName("Test length of empty set")
    public void testLengthEmpty() {
        assertEquals(0, set1.length());
    }

    @Test
    @DisplayName("Test length after adding elements")
    public void testLengthAfterAdds() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        assertEquals(3, set1.length());
    }

    @Test
    @DisplayName("Test length does not increase on duplicate add")
    public void testLengthNoDuplicates() {
        set1.add(5);
        set1.add(5);
        assertEquals(1, set1.length());
    }

    // ── equals() ─────────────────────────────────────────────────────────
    @Test
    @DisplayName("Test equals with same elements in same order")
    public void testEqualsSameOrder() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(1); set2.add(2); set2.add(3);
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals with same elements in different order")
    public void testEqualsDifferentOrder() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(3); set2.add(1); set2.add(2);
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals with different elements")
    public void testNotEquals() {
        set1.add(1); set1.add(2);
        set2.add(1); set2.add(3);
        assertFalse(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals with different sizes")
    public void testEqualsDifferentSize() {
        set1.add(1); set1.add(2);
        set2.add(1);
        assertFalse(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals with two empty sets")
    public void testEqualsBothEmpty() {
        assertTrue(set1.equals(set2));
    }

    // ── contains() ───────────────────────────────────────────────────────
    @Test
    @DisplayName("Test contains returns true for existing element")
    public void testContainsTrue() {
        set1.add(7);
        assertTrue(set1.contains(7));
    }

    @Test
    @DisplayName("Test contains returns false for missing element")
    public void testContainsFalse() {
        set1.add(7);
        assertFalse(set1.contains(99));
    }

    @Test
    @DisplayName("Test contains on empty set")
    public void testContainsEmpty() {
        assertFalse(set1.contains(1));
    }

    // ── largest() ────────────────────────────────────────────────────────
    @Test
    @DisplayName("Test largest returns correct value")
    public void testLargest() {
        set1.add(3); set1.add(1); set1.add(7); set1.add(2);
        assertEquals(7, set1.largest());
    }

    @Test
    @DisplayName("Test largest throws exception on empty set")
    public void testLargestEmptyThrows() {
        assertThrows(IllegalStateException.class, () -> set1.largest());
    }

    // ── smallest() ───────────────────────────────────────────────────────
    @Test
    @DisplayName("Test smallest returns correct value")
    public void testSmallest() {
        set1.add(3); set1.add(1); set1.add(7); set1.add(2);
        assertEquals(1, set1.smallest());
    }

    @Test
    @DisplayName("Test smallest throws exception on empty set")
    public void testSmallestEmptyThrows() {
        assertThrows(IllegalStateException.class, () -> set1.smallest());
    }

    // ── add() ────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Test add increases length")
    public void testAdd() {
        set1.add(10);
        assertEquals(1, set1.length());
        assertTrue(set1.contains(10));
    }

    @Test
    @DisplayName("Test add does not allow duplicates")
    public void testAddDuplicate() {
        set1.add(5);
        set1.add(5);
        assertEquals(1, set1.length());
    }

    // ── remove() ─────────────────────────────────────────────────────────
    @Test
    @DisplayName("Test remove existing element")
    public void testRemove() {
        set1.add(1); set1.add(2); set1.add(3);
        set1.remove(2);
        assertFalse(set1.contains(2));
        assertEquals(2, set1.length());
    }

    @Test
    @DisplayName("Test remove element not in set does nothing")
    public void testRemoveNotPresent() {
        set1.add(1);
        set1.remove(99);
        assertEquals(1, set1.length());
    }

    // ── union() ──────────────────────────────────────────────────────────
    @Test
    @DisplayName("Test union of two sets with overlap")
    public void testUnion() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.union(set2);
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertEquals(4, result.length());
    }

    @Test
    @DisplayName("Test union does not modify originals")
    public void testUnionNoSideEffects() {
        set1.add(1); set1.add(2);
        set2.add(3);
        set1.union(set2);
        assertEquals(2, set1.length());
        assertEquals(1, set2.length());
    }

    @Test
    @DisplayName("Test union with empty set")
    public void testUnionWithEmpty() {
        set1.add(1); set1.add(2);
        IntegerSet result = set1.union(set2);
        assertEquals(2, result.length());
    }

    // ── intersect() ──────────────────────────────────────────────────────
    @Test
    @DisplayName("Test intersect returns common elements")
    public void testIntersect() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertEquals(2, result.length());
    }

    @Test
    @DisplayName("Test intersect with no common elements")
    public void testIntersectNoOverlap() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test intersect does not modify originals")
    public void testIntersectNoSideEffects() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.intersect(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    // ── diff() ───────────────────────────────────────────────────────────
    @Test
    @DisplayName("Test diff returns elements in set1 not in set2")
    public void testDiff() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.diff(set2);
        assertTrue(result.contains(1));
        assertEquals(1, result.length());
    }

    @Test
    @DisplayName("Test diff with no overlap returns copy of set1")
    public void testDiffNoOverlap() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.diff(set2);
        assertEquals(2, result.length());
    }

    @Test
    @DisplayName("Test diff does not modify originals")
    public void testDiffNoSideEffects() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.diff(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    // ── complement() ─────────────────────────────────────────────────────
    @Test
    @DisplayName("Test complement returns elements in set2 not in set1")
    public void testComplement() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.complement(set2);
        assertTrue(result.contains(4));
        assertEquals(1, result.length());
    }

    @Test
    @DisplayName("Test complement does not modify originals")
    public void testComplementNoSideEffects() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.complement(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    // ── isEmpty() ────────────────────────────────────────────────────────
    @Test
    @DisplayName("Test isEmpty on new set")
    public void testIsEmptyTrue() {
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("Test isEmpty returns false after add")
    public void testIsEmptyFalse() {
        set1.add(1);
        assertFalse(set1.isEmpty());
    }

    // ── toString() ───────────────────────────────────────────────────────
    @Test
    @DisplayName("Test toString on empty set")
    public void testToStringEmpty() {
        assertEquals("[]", set1.toString());
    }

    @Test
    @DisplayName("Test toString returns ascending order")
    public void testToStringOrdered() {
        set1.add(3); set1.add(1); set1.add(2);
        assertEquals("[1, 2, 3]", set1.toString());
    }

    @Test
    @DisplayName("Test toString with single element")
    public void testToStringSingle() {
        set1.add(5);
        assertEquals("[5]", set1.toString());
    }
}