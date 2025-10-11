// 212437453 Maayan Ifergan

package sprites;

import biuoop.DrawSurface;

/**
 * Represents a graphical sprite in a game.
 * Sprites are objects that can be rendered on the screen.
 */
public interface Sprite {

    /**
     * Draws the sprite on the given drawing surface.
     *
     * @param d The drawing surface on which the sprite will be drawn.
     */
    void drawOn(DrawSurface d);

    /**
     * Updates the state of the sprite over time.
     * Implementing classes should specify how the sprite's state changes.
     */
    void timePassed();
}