package geometricShapes;

/**
 * @author Maayan Ifergan
 *
 * @version 2.
 *
 * @since 9.1.2024.
 */
public class Point {
    private static final double THRESHOLD = 0.00001;
    // Fields
    private double x;
    private double y;

    /**
     * Constructs a new Point with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return the x value of this point.
     *
     * @return Double-the x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y value of this point.
     *
     * @return Double - the y value.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Check and return the distance of this point to the other point.
     *
     * @param other The other point to which the distance is calculated.
     * @return The Euclidean distance between this point and the other point.
     */
    public double distance(Point other) {
        double distanceX = (getX() - other.getX()) * (getX() - other.getX());
        double distanceY = (getY() - other.getY()) * (getY() - other.getY());
        return Math.sqrt(distanceX + distanceY);
    }

    /**
     * Check and return true if the points are equal, false otherwise.
     *
     * @param other The other point to which the distance is calculated.
     * @return True if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        //Check if the absolute difference in x,y coordinates is within the
        if (other == null) {
            return false;
        }
        //threshold
        return Math.abs(getY() - other.getY()) < THRESHOLD
                && Math.abs(getX() - other.getX()) < THRESHOLD;
    }

}
