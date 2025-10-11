// 212437453 Maayan Ifergan

package collision;
import geometricShapes.Rectangle;
import geometricShapes.Point;
import ball.Ball;
import ball.Velocity;
/**
 * The Collidable interface represents an object that can participate in collisions within a game.
 * Implementing classes are expected to provide collision-related information and behavior.
 */
public interface Collidable {
    /**
     * Retrieves the collision rectangle representing the bounding box of the collidable object.
     * The collision rectangle is a rectangle that encompasses the object and is used for collision detection.
     *
     * @return The collision rectangle of the collidable object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Calculate the new velocity of the object that has been hit by the given ball.
     *
     * @param hitter the Ball object that hit the other object
     * @param collisionPoint the Point object representing the point of collision
     * @param currentVelocity the current Velocity of the object being hit
     * @return the new Velocity of the object after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}