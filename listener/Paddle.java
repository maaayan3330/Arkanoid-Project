package listener;
import ball.Ball;
import sprites.Sprite;
import collision.Collidable;
import geometricShapes.Point;
import geometricShapes.Rectangle;
import ball.Velocity;
import game.Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Maayan Ifergan
 *
 * @version 3
 *
 * @since 20.01.2024
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    public static final double WIDTH_OF_PADDLE = 70;
    public static final double WIDTH_OF_BOARD = 800;
    public static final int MOVE = 5;

    /**
     * Constructs a Paddle object with the specified rectangle and color.
     *
     * @param rectangle The rectangular shape representing the dimensions and position of the paddle.
     * @param color The color of the paddle.
     */
    public Paddle(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Sets the keyboard sensor for interacting with this object.
     *
     * @param keyboard The keyboard sensor to be set for this object.
     */
    public void setKeyboard(biuoop.KeyboardSensor keyboard) {
        this.keyboard = keyboard;
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
     * Moves this object to the left.
     * The specific behavior of the left movement is determined by the implementation.
     * For example, in a graphical context, it might update the position or appearance of the object.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() > 0) {
            double x = this.rectangle.getUpperLeft().getX() - MOVE;
            double y = this.rectangle.getUpperLeft().getY();
            Point p = new Point(x, y);
            this.rectangle.setUpperLeft(p);
        } else {
            // In the next step it will go out the screen - move to right
            double x = WIDTH_OF_BOARD - this.rectangle.getWidth() - 1;
            double y = this.rectangle.getUpperLeft().getY();
            Point p = new Point(x, y);
            this.rectangle.setUpperLeft(p);
        }
    }

    /**
     * Moves this object to the right.
     * The specific behavior of the right movement is determined by the implementation.
     * For example, in a graphical context, it might update the position or appearance of the object.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + WIDTH_OF_PADDLE < WIDTH_OF_BOARD) {
            double x = this.rectangle.getUpperLeft().getX() + MOVE;
            double y = this.rectangle.getUpperLeft().getY();
            Point p = new Point(x, y);
            this.rectangle.setUpperLeft(p);
        } else {
            // In the next step it will go out the screen - move to right
            double x = 1;
            double y = this.rectangle.getUpperLeft().getY();
            Point p = new Point(x, y);
            this.rectangle.setUpperLeft(p);
        }
    }


    /**
     * Updates the state of the sprite for a specific time passage.
     * This method is called regularly to handle changes over time.
     * It may involve movement, animation, or any other time-dependent behavior.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d The DrawSurface on which the paddle will be drawn.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(getColor());
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }


    /**
     * Retrieves the collision rectangle representing the bounding box of this object.
     * The collision rectangle is a rectangle that encompasses the object and is used for collision detection.
     *
     * @return The collision rectangle of this object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Computes the new velocity of this object after a collision at the specified collision point
     * with the given current velocity.
     *
     * @param collisionPoint The point of collision with another object.
     * @param currentVelocity The current velocity of this object before the collision.
     * @return The new velocity of this object after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Lets divide the paddle to 5 parts
        double partOneOfPaddle = WIDTH_OF_PADDLE / 5;
        double partTwoOfPaddle = (WIDTH_OF_PADDLE / 5) * 2;
        double partThreeOfPaddle = (WIDTH_OF_PADDLE / 5) * 3;
        double partFourOfPaddle = (WIDTH_OF_PADDLE / 5) * 4;
        double partFiveOfPaddle = WIDTH_OF_PADDLE;
        // x collision
        double xHit = collisionPoint.getX() - getCollisionRectangle().getUpperLeft().getX();
        double currentSpeed = currentVelocity.getSpeed();
        // let's see where is the collision point x is - area 1
        if (xHit >= 0 && xHit < partOneOfPaddle) {
            return Velocity.fromAngleAndSpeed(300, currentSpeed);
        } else if (xHit >= partOneOfPaddle && xHit < partTwoOfPaddle) {
            // Area 2
            return Velocity.fromAngleAndSpeed(330, currentSpeed);
        } else if (xHit >= partTwoOfPaddle && xHit < partThreeOfPaddle) {
            // Area 3
            return Velocity.fromAngleAndSpeed(0, currentSpeed);
        } else if (xHit >= partThreeOfPaddle && xHit < partFourOfPaddle) {
            // Area 4
            return Velocity.fromAngleAndSpeed(30, currentSpeed);
        } else {
            // Area 5
            return Velocity.fromAngleAndSpeed(60, currentSpeed);
        }
    }


    /**
     * Adds the current object to the specified game.
     *
     * @param g The game to which the object will be added.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
