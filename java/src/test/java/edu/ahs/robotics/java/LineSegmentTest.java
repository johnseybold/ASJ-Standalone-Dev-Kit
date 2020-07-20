package edu.ahs.robotics.java;

import org.junit.Test;

import javax.sound.sampled.Line;

import static org.junit.Assert.*;

public class LineSegmentTest {

    @Test
    public void subdivide() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 4);
        LineSegment lineSegment = new LineSegment(p1, p2);
        Point[] expected = {new Point(2, 2), new Point(3, 3)};
        Point[] actual = lineSegment.subdivide(3);
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].getX(), actual[i].getX(), 0.0001);
            assertEquals(expected[i].getY(), actual[i].getY(), 0.0001);
        }

    }
}