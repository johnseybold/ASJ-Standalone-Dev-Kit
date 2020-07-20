package edu.ahs.robotics.java;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void distanceToOrigin() {
        assertEquals(5, new Point(3,4).distanceToOrigin(), 0.00001);
    }

    @Test
    public void distanceTo() {
        Point current = new Point(1,1);
        Point next = new Point(4,5);
        assertEquals(5.0, current.distanceTo(next), 0.0001);
    }
}