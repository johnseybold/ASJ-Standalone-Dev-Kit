package edu.ahs.robotics.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GridLoggerTest {

    @Test
    public void writeLn() {
        TestWriter writer = new TestWriter();
        GridLogger gridLogger = new GridLogger(writer);
        gridLogger.add("RobotX", "2.4");
        gridLogger.writeLn();

        List lines = writer.getLines();
        // check the lines


 /*       while (robotIsRunning) {
            // Do some stuff
            // Now log the robot's position
            gridLogger.add("RobotX", robot.currentPos.x);
            gridLogger.add("Roboty", robot.currentPos.y);
            gridLogger.writeLn();
        }
        
  */
    }

    private class TestWriter implements LogWriter {

        List lines = new ArrayList();

        @Override
        public void writeLine(String line) {
            lines.add(line);
        }

        public List getLines() {
            return lines;
        }
    }
}