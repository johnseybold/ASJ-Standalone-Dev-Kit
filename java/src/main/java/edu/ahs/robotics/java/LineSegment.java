package edu.ahs.robotics.java;

public class LineSegment {
    private Point point1;
    private Point point2;

    public LineSegment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point[] subdivide(int numSegments){
        double deltaX = point2.getX() - point1.getX();
        double deltaY = point2.getY() - point1.getY();

        Point[] points = new Point[numSegments - 1];

        for (int i = 0; i < points.length; i++) {
            double x = point1.getX() + (i+1) * deltaX / numSegments;
            double y = point1.getY() + (i+1) * deltaY / numSegments;
            points[i] = new Point(x, y);
        }

        return points;
    }

    public double length() {
        return 0.0;
    }

    public Point interpolate(double distanceFromFirst) {
        return new Point(0,0);
    }
}
