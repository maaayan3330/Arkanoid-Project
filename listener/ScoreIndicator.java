package listener;
import sprites.Sprite;
import game.Counter;
import game.Game;

import biuoop.DrawSurface;

/**
 * @author Maayan Ifergan
 *
 * @version 5
 *
 * @since 25.02.2024
 */
public class ScoreIndicator implements Sprite {
    private final Counter counterOfGame;

    /**
     * Constructs a new ScoreIndicator object with the given Counter representing the score counter of the game.
     *
     * @param counterOfGame the Counter object representing the score counter of the game
     */
    public ScoreIndicator(Counter counterOfGame) {
        this.counterOfGame = counterOfGame;
    }
    /**
     * Draws the sprite on the given drawing surface.
     *
     * @param d The drawing surface on which the sprite will be drawn.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(370, 20, "Score: " + this.counterOfGame.getValue(), 20);
    }

    /**
     * Updates the state of the sprite over time.
     * Implementing classes should specify how the sprite's state changes.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Adds this Sprite object to the specified Game object.
     *
     * @param g the Game object to which this Sprite will be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

}
