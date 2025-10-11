// 212437453 Maayan Ifergan

package game;
import collision.Collidable;
import geometricShapes.Point;
import collision.CollisionInfo;
import geometricShapes.Line;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Maayan Ifergan
 *
 * @version 2
 *
 * @since 10.01.2024
 */
public class GameEnvironment {
    // Make a list of interfaces
    private final ArrayList<Collidable> collidables;

    /**
     * Constructs a GameEnvironment with the provided initial collidables.
     *
     * @param collidables The initial collidables to populate the GameEnvironment.
     *                    The collidables may be null or an empty list.
     */
    public GameEnvironment(ArrayList<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Returns the list of collidables in the GameEnvironment.
     *
     * @return The list of collidables.
     */
    public ArrayList<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Adds a collidable object to the GameEnvironment.
     *
     * @param c The collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes a collidable object from the list of collidables.
     * This method removes the specified collidable object from the list of collidables.
     *
     * @param c The collidable object to be removed.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Finds information about the closest collision along a given trajectory.
     * Assumes an object is moving from the start point of the trajectory to the end point.
     * If the object does not collide with any of the collidables in this collection, returns null.
     * Otherwise, returns information about the closest collision that is going to occur.
     *
     * @param trajectory The trajectory of the object.
     * @return CollisionInfo representing information about the closest collision,
     *         or null if there is no collision along the trajectory.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable collidable1 = null;
        // Iterate over all collidables
        for (Collidable collidable : this.collidables) {
            List<Point> intersections = collidable.getCollisionRectangle().intersectionPoints(trajectory);
            if (intersections != null && !intersections.isEmpty()) {
                // There are intersections with the current collidable
                Point currentClosest = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
                if (closestPoint == null
                        || trajectory.start().distance(currentClosest) < trajectory.start().distance(closestPoint)) {
                    // Update the closest point and associated collidable
                    closestPoint = currentClosest;
                    collidable1 = collidable;
                }
            }
        }
        // Return the CollisionInfo with the closest point and collidable
        if (closestPoint != null && collidable1 != null) {
            return new CollisionInfo(closestPoint, collidable1);
        } else {
            return null;
        }
    }
}
