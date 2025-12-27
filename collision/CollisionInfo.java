package collision;
import geometricShapes.Point;

/**
 * @author Maayan Ifergan
 *
 * @version 3
 *
 * @since 20.01.2024
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a new CollisionInfo object with the specified collision point and collidable object.
     *
     * @param collisionPoint The point where the collision occurred.
     * @param collisionObject The collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Gets the point where the collision occurred.
     *
     * @return The collision point.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }


    /**
     * Gets the collidable object involved in the collision.
     *
     * @return The collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }

}
