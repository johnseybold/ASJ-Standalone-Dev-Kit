package edu.ahs.robotics.java;

public class GridLogger {

    private LogWriter writer;

    public GridLogger(LogWriter writer) {
        this.writer = writer;
    }

    /**
     * Add a value to the logger under the category.  Categories are lazily added to the logger
     * in the order encountered.
     * @param category
     * @param value
     */
    public void add(String category, String value) {}

    /**
     * Write a line of data to the log.  If this is the first call to writeLn, categories are
     * written first, followed by the line of data.  Once the data is written, the logger is reset
     * and calls to add() will add values to the next line of data.
     */
    public void writeLn() {
        writer.writeLine("something");
    }

    public void stop() {}
}
