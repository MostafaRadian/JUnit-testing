package org.jfree.data.test;

import org.jfree.data.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
//import java.security.InvalidParameterException;

public class RangeClassTest {
    @Test
    public void testOfLength() {
        Range rangeObj = new Range(1, 20);
        Assertions.assertEquals(19, rangeObj.getLength());
    }

    @Test
    public void testOfUpperB() {
        Range rangeObj = new Range(1, 20);
        Assertions.assertEquals(20, rangeObj.getUpperBound());
    }

    @Test
    public void testOfLowerB() {
        Range rangeObj = new Range(1, 20);
        Assertions.assertEquals(1, rangeObj.getLowerBound());
    }

    @Test
    public void testOfCentralV() {
        Range rangeObj = new Range(1, 20);
        Assertions.assertEquals(10.5, rangeObj.getCentralValue());
    }

    @Test
    public void testOfContain() {
        Range rangeObj = new Range(1, 10);
        Assertions.assertTrue(rangeObj.contains(1));
        Assertions.assertFalse(rangeObj.contains(11));
        Assertions.assertTrue(rangeObj.contains(10));
    }

    @Test
    public void testOfIntersects() {
        Range rangeObj = new Range(1, 20);
        Assertions.assertTrue(rangeObj.intersects(9, 11));
        Assertions.assertTrue(rangeObj.intersects(5, 10));
        Assertions.assertTrue(rangeObj.intersects(1, 20));
        Assertions.assertTrue(rangeObj.intersects(20, 30));
        Assertions.assertTrue(rangeObj.intersects(-3, 1));
        Assertions.assertFalse(rangeObj.intersects(30, 40));
    }

    @Test
    public void testOfConstrain() {
        Range rangeObj = new Range(1, 20);
        Assertions.assertEquals(12, rangeObj.constrain(12));
        Assertions.assertEquals(20, rangeObj.constrain(20));
        Assertions.assertEquals(20, rangeObj.constrain(25));
        Assertions.assertEquals(1, rangeObj.constrain(0));
        Assertions.assertEquals(1, rangeObj.constrain(-4));
    }

    @Test
    public void testOfCombine() {
        Assertions.assertNull(Range.combine(null, null));
        Assertions.assertEquals(new Range(1, 20), Range.combine(new Range(1, 20), null));
        Assertions.assertEquals(new Range(1, 30),
                Range.combine(new Range(5, 20), new Range(1, 30)));
        Assertions.assertEquals(new Range(1, 30),
                Range.combine(new Range(1, 20), new Range(21, 30)));
    }

    @Test
    public void testOfExpandToInclude() {
        Assertions.assertEquals(new Range(1, 5), Range.expandToInclude(new Range(1, 5), 2.5));
        Assertions.assertEquals(new Range(9, 9), Range.expandToInclude(null, 9));
        Assertions.assertEquals(new Range(1, 9), Range.expandToInclude(new Range(1, 5), 9));
        Assertions.assertEquals(new Range(9, 50), Range.expandToInclude(new Range(20, 50), 9));
        Assertions.assertEquals(new Range(-4, 5), Range.expandToInclude(new Range(1, 5), -4));
    }

    @Test
    public void testOfExpand() {
        Assertions.assertEquals(
                new Range(0, 6),
                Range.expand(new Range(1, 5), 0.25, 0.25)
        );
        Assertions.assertEquals(new Range(-1, 7), Range.expand(new Range(1, 5), 0.5, 0.5));
        Assertions.assertEquals(new Range(12.5, 71), Range.expand(new Range(20, 50), 0.25, 0.7));
        Assertions.assertEquals(new Range(1, 5), Range.expand(new Range(1, 5), 0, 0));
    }

    @Test
    public void testOfShift() {
        Assertions.assertEquals(new Range(2, 11), Range.shift(new Range(1, 10), 1));
        Assertions.assertEquals(new Range(0, 8), Range.shift(new Range(1, 10), -2));
        Assertions.assertEquals(new Range(-8, 0), Range.shift(new Range(-10, -1), 2));
        Assertions.assertEquals(new Range(-10, -1), Range.shift(new Range(-10, -1), 0));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> Range.shift(null,1));
    }

    @Test
    public void testOfShiftV2() {
        Assertions.assertEquals(new Range(2, 11), Range.shift(new Range(1, 10), 1, false));
        Assertions.assertEquals(new Range(2, 11), Range.shift(new Range(1, 10), 1, true));

        Assertions.assertEquals(new Range(0, 8), Range.shift(new Range(1, 10), -2, false));
        Assertions.assertEquals(new Range(-1, 8), Range.shift(new Range(1, 10), -2, true));

        Assertions.assertEquals(new Range(-8, 0), Range.shift(new Range(-10, -1), 2, false));
        Assertions.assertEquals(new Range(-8, 1), Range.shift(new Range(-10, -1), 2, true));

        Assertions.assertEquals(new Range(-10, -1), Range.shift(new Range(-10, -1), 0, false));
        Assertions.assertEquals(new Range(-10, -1), Range.shift(new Range(-10, -1), 0, true));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> Range.shift(null,1,true));
    }
    @Test
    public void testOfEquals() {
        Range rangeObj = new Range(1, 20);
        Assertions.assertTrue(rangeObj.equals(new Range(1,20)));
        Assertions.assertFalse(rangeObj.equals(new Range(4,20)));
        Assertions.assertFalse(rangeObj.equals(null));
    }


}





