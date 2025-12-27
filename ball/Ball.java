package ball;
import geometricShapes.Point;
import sprites.Sprite;
import game.GameEnvironment;
import geometricShapes.Line;
import collision.CollisionInfo;
import game.Game;



import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Maayan Ifergan
 *
 * @version 2
 *
 * @since 10.1.2024
 */
public class Ball implements Sprite {
    // Fields
    private Point point;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    public static final double COLLISION = 0.1;

    /**
     * Constructs a new Ball with the specified center, radius, and color.
     *
     * @param center the center of the ball (a Point object).
     * @param r      the radius of the ball.
     * @param color  the color of the ball (a java.awt.Color object).
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructs a new Ball with the specified coordinates, radius, and color.
     *
     * @param x the x-coordinate of the center of the ball.
     * @param y the y-coordinate of the center of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.color = color;
        this.radius = r;
    }

    /**
     * Gets the x-coordinate of the center of the ball.
     *
     * @return the x-coordinate of the center of the ball.
     */
    public int getX() {
        return (int) this.point.getX();
    }


    /**
     * Gets the y-coordinate of the center of the ball.
     *
     * @return the y-coordinate of the center of the ball.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * Gets the size of the ball, which is twice the radius.
     *
     * @return the size of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Sets the size (radius) of the ball.
     *
     * @param radius the new size (radius) of the ball.
     */
    public void setSize(int radius) {
        this.radius = radius;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball (a java.awt.Color object).
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of this object and returns the previous color.
     *
     * @param color The new color to set.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the ball.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     * Updates the object's state over time. For a ball, it moves one step based on its velocity.
     * For a block, behavior varies based on the specific game logic.
     */
    @Override
    public void timePassed() {
        this.moveOneStepBall();
    }


    /**
     * Sets the velocity of the ball to the given Velocity object.
     *
     * @param v the Velocity object representing the new velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball using the provided components for the x and y directions.
     *
     * @param dx the change in position on the x-axis.
     * @param dy the change in position on the y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the Velocity object representing the current velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * Moves the ball one step based on its current velocity.
     * If the ball hits the left or right borders, it changes its horizontal direction.
     * If the ball hits the top or bottom borders, it changes its vertical direction.
     * The method ensures that the ball stays within the screen boundaries.
     */
    public void moveOneStep() {
        // We got a point
        this.point = this.getVelocity().applyToPoint(this.point);
    }

    /**
     * Retrieves the GameEnvironment associated with this object.
     *
     * @return The GameEnvironment instance associated with this object.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Moves the ball one step based on its current velocity. If a collision occurs during
     * the movement, the ball's position is adjusted accordingly, and collision-related
     * actions are performed.
     */
    public void moveOneStepBall() {
        // Check the current step and next step
        double currentStepx = this.point.getX();
        double currentStepy = this.point.getY();
        double nextStepx = currentStepx + getVelocity().getDx();
        double nextStepy = currentStepy + getVelocity().getDy();

        // Creat the trajectory of the one only step
        Line trajectory = new Line(currentStepx, currentStepy, nextStepx, nextStepy);
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);

        // Check if in this step the ball stack with anything
        if (collisionInfo == null) {
            // In this step nothing is in the way of the ball - do the step
            moveOneStep();
        } else {
            double vx = -COLLISION * getVelocity().getDx();
            double vy = -COLLISION * getVelocity().getDy();
            this.point = new Point(collisionInfo.collisionPoint().getX() + vx,
                    collisionInfo.collisionPoint().getY() + vy);

            // notify the hit object (using its hit() method) that a collision occurred.
            Velocity velocity1 = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(),
                    getVelocity());
            // update the velocity to the new velocity returned by the hit() method.
            setVelocity(velocity1);
        }
    }
    /**
     * Sets the game environment for this object.
     *
     * @param gameEnvironment The GameEnvironment to be set for this object.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Adds this object to the specified game.
     *
     * @param game The game to which this object will be added.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Removes this object from the specified game.
     *
     * @param game The game from which this object should be removed.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}
