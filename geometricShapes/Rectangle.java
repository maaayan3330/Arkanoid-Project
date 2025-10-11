// 212437453 Maayan Ifergan
package geometricShapes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maayan Ifergan
 *
 * @version 3
 *
 * @since 20.01.2024
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;


    /**
     * Represents a rectangle with a specified upper-left corner, width, and
     * height.
     *
     * @param upperLeft The Point representing the upper-left corner of the
     * rectangle.
     * @param width The width of the rectangle. Must be a positive value.
     * @param height The height of the rectangle. Must be a positive value.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Sets the upper-left corner of this object to the specified point.
     *
     * @param point The new upper-left corner point for this object.
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
    }


    /**
     * Finds and returns a list of intersection points between the given line and the stored lines.
     *
     * @param line The line for which intersection points are to be found.
     * @return A list of intersection points between the given line and the stored lines.
     *         The list may be empty if there are no intersections.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> pointsList = new ArrayList<>();

        // All the corners of the rectangle
        double xmax = upperLeft.getX() + this.width;
        double xmin = upperLeft.getX();
        double ymax = upperLeft.getY() + this.height;
        double ymin = upperLeft.getY();

        // Make the lines of the rectangle
        Line up = new Line(xmin, ymin, xmax, ymin);
        Line down = new Line(xmin, ymax, xmax, ymax);
        Line left = new Line(xmin, ymin, xmin, ymax);
        Line right = new Line(xmax, ymin, xmax, ymax);

        // Check all the possibles ways a point cut can be made - up
        if (up.isIntersecting(line)) {
            if (up.intersectionWith(line) != null) {
                pointsList.add(up.intersectionWith(line));
            }
        }
        // Down
        if (down.isIntersecting(line)) {
            if (down.intersectionWith(line) != null) {
                pointsList.add(down.intersectionWith(line));
            }
        }
        // Left
        if (left.isIntersecting(line)) {
            if (left.intersectionWith(line) != null) {
                pointsList.add(left.intersectionWith(line));
            }
        }
        // Right
        if (right.isIntersecting(line)) {
            if (right.intersectionWith(line) != null) {
                pointsList.add(right.intersectionWith(line));
            }
        }
            return pointsList;
    }


    /**
     * Gets the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets the upper-left corner of the rectangle as a Point.
     *
     * @return The Point representing the upper-left corner of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}