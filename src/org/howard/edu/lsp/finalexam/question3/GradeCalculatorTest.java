package org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {

    GradeCalculator calc = new GradeCalculator();

    // Test for average()
    @Test
    public void testAverageOfThreeScores() {
        assertEquals(80.0, calc.average(70, 80, 90), 0.001);
    }

    // Test for letterGrade()
    @Test
    public void testLetterGradeA() {
        assertEquals("A", calc.letterGrade(95.0));
    }

    // Test for isPassing()
    @Test
    public void testIsPassingTrue() {
        assertTrue(calc.isPassing(75.0));
    }

    // Boundary: exactly 60 is the minimum passing average
    @Test
    public void testBoundaryPassingAt60() {
        assertTrue(calc.isPassing(60.0));
        assertEquals("D", calc.letterGrade(60.0));
    }

    // Boundary: scores of 0 and 100 are valid edge values
    @Test
    public void testBoundaryValidMinAndMaxScores() {
        assertDoesNotThrow(() -> calc.average(0, 50, 100));
    }

    // Exception: score below 0 should throw IllegalArgumentException
    @Test
    public void testExceptionScoreBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> calc.average(-1, 50, 50));
    }

    // Exception: score above 100 should throw IllegalArgumentException
    @Test
    public void testExceptionScoreAbove100() {
        assertThrows(IllegalArgumentException.class, () -> calc.average(101, 50, 50));
    }
}
