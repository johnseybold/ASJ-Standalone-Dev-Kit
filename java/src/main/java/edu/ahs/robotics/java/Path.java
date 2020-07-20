package edu.ahs.robotics.java;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<WayPoint> wayPoints = new ArrayList<>();

    /**
     * @param rawPoints Array of X,Y points.  Consecutive duplicate points are discarded
     *                  A path must have at least 2 non-identical points
     * @throws IllegalArgumentException for paths with fewer than 2 non-duplicate points.
     */
    public Path(Point[] rawPoints) {
        wayPoints = new ArrayList<>();
        Point point = new Point(1, 1);
        wayPoints.add(new WayPoint(point, 0, 0, 0, 0));
        if (wayPoints.size() < 2) {
            throw new IllegalArgumentException("A Path must be defined by at least two non-duplicate points.");
        }
    }

    /**
     * @return total distance along the path
     */
    public double totalDistance() {
        return 0.0;
    }

    /**
     * @return a point at the supplied distance along the path from the supplied current position
     * Note that the point will usually be interpolated between the points that originally defined the Path
     */
    public TargetPoint getTargetPoint(Point current, double lookAheadDistance) {

        // Step 1: find the next WayPoint along the path, past current point
        int iNextWP = 0;
        for (int i = 0; i < wayPoints.size(); i++) {
            iNextWP = i;
            WayPoint nextWayPoint = wayPoints.get(i);
            if (nextWayPoint.componentAlongPath(current) > 0) {
                break;
            }
        }

        // Step 2: starting with that next WayPoint, find a WayPoint past
        // lookAheadDistance from current point
        double distanceFromCurrent = wayPoints.get(iNextWP).componentAlongPath(current);
        int iWayPointPastTarget = iNextWP + 1;

        while(iWayPointPastTarget < wayPoints.size() && distanceFromCurrent < lookAheadDistance) {
            distanceFromCurrent += wayPoints.get(iWayPointPastTarget).distanceFromPrevious;
            iWayPointPastTarget++;
        }
        if (iWayPointPastTarget >= wayPoints.size()){
            // We ran out of path before exceeding distance; return the last point
            WayPoint wayPoint = wayPoints.get(wayPoints.size() - 1);
            return new TargetPoint(wayPoint);
        }

        // Step 3: interpolate between that WayPoint and the one before, and return that point
        return getInterpolatedPoint(lookAheadDistance, distanceFromCurrent, iWayPointPastTarget);
    }

    List<TargetPoint> getPoints() {
        return new ArrayList<>();
    }

    private TargetPoint getInterpolatedPoint(double targetDistance, double distanceFromCurrent, int iWayPointPastTarget) {
        WayPoint first = wayPoints.get(iWayPointPastTarget - 1);
        WayPoint second = wayPoints.get(iWayPointPastTarget);
        LineSegment ls = new LineSegment(first.point, second.point);
        double distanceFromSecond = distanceFromCurrent - targetDistance;
        double distanceFromFirst = ls.length() - distanceFromSecond;
        Point point = ls.interpolate(distanceFromFirst);
        double distanceFromStart = first.distanceFromStart + distanceFromFirst;
        return new TargetPoint(point, distanceFromStart, totalDistance() - distanceFromStart);
    }


    public double distanceFromStart(Point target) {
        return 0.0;
    }

    public double distanceToEnd(Point target) {
        return 0.0;
    }

    public static class TargetPoint {
        public Point point;
        public double distanceFromStart;
        public double distanceToEnd;

        private TargetPoint(Point point, double distanceFromStart, double distanceToEnd) {
            this.point = point;
            this.distanceFromStart = distanceFromStart;
            this.distanceToEnd = distanceToEnd;
        }

        private TargetPoint(WayPoint wayPoint) {
            this.point = wayPoint.point;
            this.distanceFromStart = wayPoint.distanceFromStart;
            this.distanceToEnd = wayPoint.getDistanceToEnd();
        }
    }

    private class WayPoint {
        public Point point;
        private double deltaXFromPrevious;
        private double deltaYFromPrevious;
        private double distanceFromPrevious;
        private double distanceFromStart;

        public WayPoint(Point point, double deltaXFromPrevious, double deltaYFromPrevious, double distanceFromPrevious, double distanceFromStart) {
            this.point = point;
            this.deltaXFromPrevious = deltaXFromPrevious;
            this.deltaYFromPrevious = deltaYFromPrevious;
            this.distanceFromPrevious = distanceFromPrevious;
            this.distanceFromStart = distanceFromStart;
        }

        public double getDistanceToEnd() {
            return totalDistance() - distanceFromStart;
        }

        /**
         * Calculates the projection of the vector Vcurrent leading from the supplied current
         * point to this WayPoint onto the vector Vpath leading from the previous point on the path
         * to this WayPoint.  If the return value is positive, it means that the WayPoint is
         * farther along the path from the current point.  If the return value is negative, it means
         * that the WayPoint is before the current point (earlier on the path).
         * The magnitude of the value tells the
         * distance along the path.  The value is computed as the dot product between Vcurrent and
         * Vpath, normalized by the length of vPath
         *
         * @param current The source point to compare to the WayPoint
         */
        private double componentAlongPath(Point current) {
            double deltaXFromCurrent = point.x - current.x;
            double deltaYFromCurrent = point.y - current.y;

            double dp = deltaXFromCurrent * deltaXFromPrevious + deltaYFromCurrent * deltaYFromPrevious;
            return dp / distanceFromPrevious;
        }
    }
}
