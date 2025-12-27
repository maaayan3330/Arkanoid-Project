package ball;
import geometricShapes.Point;

/**
 * @author Maayan Ifergan
 *
 * @version 2
 *
 * @since 10.01.2024
 */
public class Velocity {

    // Fields
    private double dx;
    private double dy;
    /**
     * Creates a new Velocity object based on the specified angle and speed.
     *
     * @param angle the angle in degrees, where 0 degrees points to the right and increases counterclockwise.
     * @param speed the speed of the object's movement.
     * @return a new Velocity object representing the motion described by the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Convert angle to radians
        double angleInRadians = Math.toRadians(angle);
        // Calculate horizontal and vertical components
        double dx = speed * Math.sin(angleInRadians);
        double dy = -speed * Math.cos(angleInRadians);
        // Return a new Velocity object
        return new Velocity(dx, dy);
    }
    /**
     * Constructs a new Velocity with the specified changes in position on the
     * x and y axes.
     *
     * @param dx the change in position on the x-axis.
     * @param dy the change in position on the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Sets the vertical component of the ball's velocity.
     *
     * @param dx the new vertical velocity component to set.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the vertical component of the ball's velocity.
     *
     * @param dy the new vertical velocity component to set.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Gets the change in position on the x-axis (dx) represented by this
     * velocity.
     *
     * @return the change in position on the x-axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the change in position on the y-axis (dy) represented by this
     * velocity.
     *
     * @return the change in position on the y-axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Apply the velocity to a given point and return a new point with the
     * updated position.
     *
     * @param p the point to which the velocity will be applied.
     * @return a new point with the updated position after applying the
     * velocity.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + getDx(), p.getY() + getDy());
    }

    /**
     * Get the speed of the velocity.
     *
     * @return The speed of the velocity.
     */
    public double getSpeed() {
        // Using the Pythagorean theorem to calculate the speed
        return Math.sqrt(dx * dx + dy * dy);
    }
}
