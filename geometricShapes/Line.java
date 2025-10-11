// 212437453 Maayan Ifergan

package geometricShapes;

import java.util.List;
/**
 * @author Maayan Ifergan
 *
 * @version 2.
 *
 * @since 10.01.2024.
 */
public class Line {
    private static final double THRESHOLD = 0.00001;
    // Fields
    private Point start;
    private Point end;

    /**
     * Constructs a new Line with specified start and end points.
     *
     * @param start The starting point of the line segment.
     * @param end   The ending point of the line segment.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Line with specified coordinates for the start and end
     * points.
     *
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculates and returns the length of the line segment.
     *
     * @return The length of the line segment.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Calculates and returns the middle point of the line segment.
     *
     * @return The middle point of the line segment.
     */
    public Point middle() {
        double middleX = (start().getX() + end().getX()) / 2;
        double middleY = (start().getY() + end().getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * Returns the starting point of the line segment.
     *
     * @return The starting point of the line segment.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of the line segment.
     *
     * @return The ending point of the line segment.
     */
    public Point end() {
        return this.end;
    }


    /**
     * Calculates the slope (m) of the line using the formula: m = (y2 - y1) / (x2 - x1),
     * where (x1, y1) and (x2, y2) are the coordinates of the starting and ending points
     * of the line, respectively.
     *
     * @return The slope (m) of the line.
     */
    public double slopeOfLine() {
        // find slope of line other - (y2-y1) / (x2 - x1)
        double slope = (end().getY() - start().getY())
                / (end().getX() - start().getX());
        return slope;
    }

    /**
     * Calculates the y-intercept (b) of the line using the formula: b = y - m * x,
     * where y is the y-coordinate of the starting point, m is the slope of the line,
     * and x is the x-coordinate of the starting point.
     *
     * @return The y-intercept (b) of the line.
     */
    public double interceptOfLine() {
        // Now we find the intercept(b) of "other" line. b = y -m*x
        double intercept = start().getY() - (slopeOfLine() * start().getX());
        return intercept;
    }


    /**
     * Checks if this line and another line are both parallel to the y-axis and
     * overlap along the y-coordinate
     * at the same x-coordinate.
     *
     * @param other The other line to check for parallelism and overlap.
     * @return {@code true} if both lines are parallel to the y-axis and
     * overlap, {@code false} otherwise.
     */
    public boolean bothParallelY(Line other) {
        // First find min/max for Y
        double maxThisY = Math.max(start().getY(), end().getY());
        double minThisY = Math.min(start().getY(), end().getY());
        double maxOtherY = Math.max(other.start().getY(), other.end().getY());
        double minOtherY = Math.min(other.start().getY(), other.end().getY());

        // Check if they have not the same x
        if (Math.abs(start().getX() - other.start().getX()) > THRESHOLD) {
            return false;
        }
        // If we here they are parallel to y but have same x.
        // Check if line this is compliantly above line other.
        if (minThisY > maxOtherY + THRESHOLD) {
            return false;
        }
        // Check if line other is compliantly above line this.
        if (maxThisY + THRESHOLD < minOtherY) {
            return false;
        }
        return true;
    }

    /**
     * Checks if this line is parallel to another line along the y-axis.
     * The lines are considered parallel if they have a common y-coordinate for the same x-coordinate.
     *
     * @param other line.
     * @return {@code true} if the lines are parallel along the y-axis, {@code false} otherwise.
     */
    public boolean oneParallelY(Line other) {
        double minThisY = Math.min(start().getY(), end().getY());
        double minOtherY = Math.min(other.start().getY(), other.end().getY());
        double maxThisY = Math.max(start().getY(), end().getY());
        double maxOtherY = Math.max(other.start().getY(), other.end().getY());

        double minThisX = Math.min(start().getX(), end().getX());
        double maxThisX = Math.max(start().getX(), end().getX());
        double otherX = Math.min(other.start().getX(), other.end().getX());
        double slopeThis = slopeOfLine();
        double interceptThis = interceptOfLine();
        // If this is parrel to x
        if (slopeThis - 0 < THRESHOLD) {
            if (isWithinRange(otherX, minThisX, maxThisX, THRESHOLD)
                    && isWithinRange(minThisY, minOtherY, maxOtherY, THRESHOLD)) {
                return true;
            }
            return false;
        }
        // Here we see if they intersect - we will see in which y they supposed to meet.
        double numOfMeet = slopeThis * other.start().getX() + interceptThis;
        if ((isWithinRange(numOfMeet, minThisY, maxThisY, THRESHOLD))
                && isWithinRange(numOfMeet, minOtherY, maxOtherY, THRESHOLD)) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether the current line intersects with another line.
     *
     * @param other The other line to check for intersection.
     * @return {@code true} if the lines intersect, {@code false} otherwise.
     */
    public boolean isIntersecting(Line other) {
        // We will divide into cases
        double numThis = start().getX() - end().getX();
        double numOther = other.start().getX() - other.end().getX();
        // First find min/max for Y
        double maxThisY = Math.max(start().getY(), end().getY());
        double minThisY = Math.min(start().getY(), end().getY());
        double maxOtherY = Math.max(other.start().getY(), other.end().getY());
        double minOtherY = Math.min(other.start().getY(), other.end().getY());
        // First find min/max for X
        double maxThisX = Math.max(start().getX(), end().getX());
        double minThisX = Math.min(start().getX(), end().getX());
        double maxOtherX = Math.max(other.start().getX(), other.end().getX());
        double minOtherX = Math.min(other.start().getX(), other.end().getX());
        // If num = 0 so the line this is parallel.
        if (this.equals(other)) {
            return true;
        }
        // Case 1 - they both parallel to y.
        if ((Math.abs(numOther - 0) < THRESHOLD)
                && (Math.abs(numThis - 0) < THRESHOLD)) {
            if (bothParallelY(other)) {
                return true;
            }
        }
        // Case 2 - Check if only "this" line is parallel to y.
        if (Math.abs(numThis - 0) < THRESHOLD) {
            if (other.oneParallelY(this)) {
                return true;
            }
        }
        // Case 3 - Check if only "other" line is parallel to y.
        if (Math.abs(numOther - 0) < THRESHOLD) {
            if (oneParallelY(other)) {
                return true;
            }
        }
        // Case 4+5-the lines don't parallel to y-check if they are Intersecting.
        double slopeThis = slopeOfLine();
        double interceptThis = interceptOfLine();
        double slopeOther = other.slopeOfLine();
        double interceptOther = other.interceptOfLine();
        // if they both parrel to x
        if ((Math.abs(slopeThis - 0) < THRESHOLD)
                && (Math.abs(slopeOther - 0) < THRESHOLD)) {
            return !(minThisX > maxOtherX || minOtherX > maxThisX);
        }
        // Check if they have the same slope
        if (Math.abs(slopeThis - slopeOther) < THRESHOLD) {
            // Check if this above other or other above this
            return !(minThisY > maxOtherY || minOtherY > maxThisY);
        }
        // Find there meeting point.
        double meetXPoint = (interceptOther - interceptThis) / (slopeThis - slopeOther);
        // Find the point they meet
        double meetYpoint = (slopeThis * meetXPoint) + interceptThis;
        // Now check if they are in the range
        if (isWithinRange(meetXPoint, minThisX, maxThisX, THRESHOLD)
                && isWithinRange(meetYpoint, minThisY, maxThisY, THRESHOLD)
                && isWithinRange(meetXPoint, minOtherX, maxOtherX, THRESHOLD)
                && isWithinRange(meetYpoint, minOtherY, maxOtherY, THRESHOLD)) {
            return true;
        }
        // If we got till here they dont cut.
        return false;
    }


    /**
     * Checks if a given value is within the specified range with a tolerance.
     *
     * @param value The value to check for being within the range.
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @param threshold The tolerance used for the comparison. The actual range is [min - threshold, max + threshold].
     * @return true if the value is within the specified range, false otherwise.
     */
    private boolean isWithinRange(double value, double min, double max, double threshold) {
        return value >= min - threshold && value <= max + threshold;
    }


    /**
     * Checks whether this line intersects with two other lines.
     *
     * @param other1 The first line to check for intersection.
     * @param other2 The second line to check for intersection.
     * @return {@code true} if this line intersects with both other lines, {@code false} otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    /**
     * Finds the point of intersection between this line and another line.
     * The method calculates the coordinates of the point where the two lines meet.
     * The lines are assumed to intersect, so it's recommended to check for intersection
     * using the {@link #isIntersecting(Line)} method before calling this method.
     *
     * @param other The other line to find the intersection point with.
     * @return The point of intersection between this line and the other line.
     */
    public Point findPoint(Line other) {
        // Find the lines
        double slopeThis = slopeOfLine();
        double interceptThis = interceptOfLine();
        double slopeOther = other.slopeOfLine();
        double interceptOther = other.interceptOfLine();
        // Find there meeting point.
        double meetXPoint = (interceptOther - interceptThis) / (slopeThis - slopeOther);
        // Find the point they meet
        double meetYpoint = (slopeThis * meetXPoint) + interceptThis;
        // Return point
        return new Point(meetXPoint, meetYpoint);
    }

    /**
     * Finds the intersection point between this line and another line.
     * The method calculates the coordinates of the point where the two lines meet.
     * If the lines do not intersect, the method returns {@code null}.
     *
     * @param other The other line to find the intersection point with.
     * @return The point of intersection between this line and the other line,
     *         or {@code null} if the lines do not intersect.
     */
    public Point intersectionWith(Line other) {
        // Check if the lines intersect
        if (!isIntersecting(other)) {
            return null;
        }
        if (this.equals(other)) {
            return null;
        }
        // We will divide into cases
        double numThis = start().getX() - end().getX();
        double numOther = other.start().getX() - other.end().getX();
        // First find min/max for Y
        double maxThisY = Math.max(start().getY(), end().getY());
        double minThisY = Math.min(start().getY(), end().getY());
        double maxOtherY = Math.max(other.start().getY(), other.end().getY());
        double minOtherY = Math.min(other.start().getY(), other.end().getY());
        // First find min/max for X
        double maxThisX = Math.max(start().getX(), end().getX());
        double minThisX = Math.min(start().getX(), end().getX());
        double maxOtherX = Math.max(other.start().getX(), other.end().getX());
        double minOtherX = Math.min(other.start().getX(), other.end().getX());

        // Divide to cases - case 1 they both parallel to the Y axis.
        if (Math.abs(numOther - 0) < THRESHOLD && (Math.abs(numThis - 0) < THRESHOLD)) {
            if (Math.abs(minThisY - maxOtherY) < THRESHOLD) {
                // There is one point
                return new Point(start().getX(), minThisY);
            }
            if (Math.abs(maxThisY - minOtherY) < THRESHOLD) {
                // There is one point
                return new Point(start().getX(), maxThisY);
            }
            // They have few points the same.
            return null;
        }
        // Case 2 only this is parallel to the Y axis
        if (Math.abs(numThis - 0) < THRESHOLD) {
            double slopeOther = other.slopeOfLine();
            double interceptOther = other.interceptOfLine();
            // Here we see if they intersect - we will see in which y they supposed to meet.
            double numOfMeetY = slopeOther * start().getX() + interceptOther;
            return new Point(start().getX(), numOfMeetY);
        }
        // Case 3 only other is parallel to the Y axis
        if (Math.abs(numOther - 0) < THRESHOLD) {
            double slopeOther = slopeOfLine();
            double interceptOther = interceptOfLine();
            // Here we see if they intersect - we will see in which y they supposed to meet.
            double numOfMeetY = slopeOther * start().getX() + interceptOther;
            return new Point(other.start().getX(), numOfMeetY);
        }
        // If they both parallel to x
        if (Math.abs(this.slopeOfLine() - other.slopeOfLine()) < THRESHOLD
                && Math.abs(this.slopeOfLine() - 0) < THRESHOLD
                && Math.abs(other.slopeOfLine() - 0) < THRESHOLD) {
            if (Math.abs(minThisX - maxOtherX) < THRESHOLD) {
                // There is one point
                return new Point(minThisX, start().getY());
            }
            if (Math.abs(maxThisX - minOtherX) < THRESHOLD) {
                // There is one point
                return new Point(maxThisX, start().getY());
            }
            // They have few points the same.
            return null;
        }
        // Case 4 they have the same slope
        if (Math.abs(slopeOfLine() - other.slopeOfLine()) < THRESHOLD) {
            if (Math.abs(minThisY - maxOtherY) < THRESHOLD) {
                // There is one point
                return new Point(start().getX(), minThisY);
            }
            if (Math.abs(maxThisY - minOtherY) < THRESHOLD) {
                // There is one point
                return new Point(start().getX(), maxThisY);
            }
            // They have few points the same.
            return null;
        }
        // Case 5 they do not parallel but cut
        return findPoint(other);
    }


    /**
     * Compares this line with another line for equality.
     * Two lines are considered equal if they have the same starting and ending points.
     *
     * @param other The other line to compare for equality.
     * @return {@code true} if the lines are equal, {@code false} otherwise.
     */
    public boolean equals(Line other) {
        // Case 1 - check if start1 = start2 && end1 = end2
        if (start().equals(other.start()) && (end().equals(other.end()))) {
            return true;
        }
        // Case 2 - check if end1 = start2 && start1 = end2
        if ((end().equals(other.start())) && (start().equals(other.end()))) {
            return true;
        }
        // The lines are not the same
        return false;
    }


    /**
     * Finds and returns the closest intersection point between the line segment defined
     * by the start and end points of this object and the specified rectangle.
     *
     * @param rect The rectangle to find intersection points with.
     * @return The closest intersection point to the start of the line segment.
     *         Returns null if there are no intersection points.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line line = new Line(this.start, this.end);
        // If the there is no insert between the line to the rectangle
        if (rect.intersectionPoints(line).isEmpty()) {
            return null;
        }
        // Copy the list
        List<Point> pointsList = rect.intersectionPoints(line);
        // There is an intersection
        // If there is only one point so is it the closest one
        if (pointsList.size() == 1) {
            return pointsList.get(0);
        }
        // If there are 2 points
        if (pointsList.size() == 2) {
            // Check who is the closest
            if (pointsList.get(0).distance(this.start) >= pointsList.get(1).distance(this.start)) {
                return pointsList.get(1);
            } else {
                return pointsList.get(0);
            }
        }
        // There are 3 points
        if (pointsList.size() == 3) {
            double distance1 = pointsList.get(0).distance(this.start);
            double distance2 = pointsList.get(1).distance(this.start);
            double distance3 = pointsList.get(2).distance(this.start);

            // Check who is the closest
            if (distance1 <= distance2 && distance1 <= distance3) {
                return pointsList.get(0);
            } else if (distance2 <= distance1 && distance2 <= distance3) {
                return pointsList.get(1);
            } else {
                return pointsList.get(2);
            }
        }
        // There are 4 points
        double distance1 = pointsList.get(0).distance(this.start);
        double distance2 = pointsList.get(1).distance(this.start);
        double distance3 = pointsList.get(2).distance(this.start);
        double distance4 = pointsList.get(3).distance(this.start);
        // Check who is the closest
        if (distance1 <= distance2 && distance1 <= distance3 && distance1 <= distance4) {
            return pointsList.get(0);
        } else if (distance2 <= distance1 && distance2 <= distance3 && distance2 <= distance4) {
            return pointsList.get(1);
        } else if (distance3 <= distance1 && distance3 <= distance2 && distance3 <= distance4) {
            return pointsList.get(2);
        } else {
            return pointsList.get(3);
        }
    }
}

