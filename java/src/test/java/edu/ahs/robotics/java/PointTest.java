package edu.ahs.robotics.java;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void distanceToOrigin() {
        assertEquals(5, new Point(3,4).distanceToOrigin(), 0.00001);
    }
}