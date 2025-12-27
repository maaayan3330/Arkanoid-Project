package listener;
import ball.Ball;
import sprites.Sprite;
import collision.Collidable;
import geometricShapes.Point;
import geometricShapes.Rectangle;
import ball.Velocity;
import game.Game;

import biuoop.DrawSurface;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

/**
 * @author Maayan Ifergan
 *
 * @version 3
 *
 * @since 31.01.2024
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Color color;
    private Rectangle rectangle;
    private final List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Constructs a Block with the specified color and rectangle.
     *
     * @param color The color of the block.
     * @param rectangle The rectangle defining the position and dimensions of the block.
     */
    public Block(Color color, Rectangle rectangle) {
        this.color = color;
        this.rectangle = rectangle;
    }

    /**
     * Retrieves the color of this object.
     *
     * @return The color of this object.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Retrieves the collision rectangle associated with this object.
     *
     * @return The collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Adds a HitListener to be notified when a hit event occurs.
     *
     * @param hl The HitListener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Removes a HitListener from the list of listeners.
     *
     * @param hl the HitListener to be removed.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Calculates the new velocity after a collision at the specified point.
     *
     * @param collisionPoint  The point of collision.
     * @param currentVelocity The current velocity.
     * @param hitter The ball that hits the block.
     * @return The new velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double xCollision = collisionPoint.getX();
        double yCollision = collisionPoint.getY();

        // Get the corners of the rectangle
        double xmin = getCollisionRectangle().getUpperLeft().getX();
        double xmax = getCollisionRectangle().getUpperLeft().getX() + getCollisionRectangle().getWidth();
        double ymin = getCollisionRectangle().getUpperLeft().getY();
        double ymax = getCollisionRectangle().getUpperLeft().getY() + getCollisionRectangle().getHeight();
        // current
        double xv = currentVelocity.getDx();
        double yv = currentVelocity.getDy();

        // Check if the object hit from up or down
        if ((yCollision == ymin)
                || (yCollision == ymax)) {
            yv = -yv;
        }
        // Check left or right
        if ((xCollision == xmin)
                || (xCollision == xmax)) {
            xv = -xv;
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return new Velocity(xv, yv);
    }

    /**
     * Notifies listeners about a hit by a ball.
     *
     * @param hitter the ball that hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Checks if the color of this object matches the color of the given ball.
     *
     * @param ball The ball to compare colors with.
     * @return true if the colors match, {@code false} otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        Color colorHitOfBall = ball.getColor();
        Color colorHitOfBlock = getColor();
        // Check if they have the same color return true else false
        if (colorHitOfBlock.equals(colorHitOfBall)) {
            return true;
        }
        return false;
    }


    @Override
    /**
     * Draws the Line on the given DrawSurface.
     * This method is responsible for rendering the visual representation of the Line
     * on the specified DrawSurface, which is typically associated with a graphical display.
     *
     * @param surface The DrawSurface on which to draw the Line.
     *                It provides the necessary methods for rendering graphics.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * Updates the state of the Line object as time passes.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Adds the current Line object to the specified Game.
     * This method registers the Line as a drawable object within the Game,
     * allowing it to be rendered and interacted with in the game environment.
     *
     * @param game The Game to which the Line will be added.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Removes this object from the specified game.
     * This method removes the sprite and collidable components of this object from the given game.
     *
     * @param game The game from which this object should be removed.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

}
