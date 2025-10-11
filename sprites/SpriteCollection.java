// 212437453 Maayan Ifergan

package sprites;

import java.util.ArrayList;
import biuoop.DrawSurface;

/**
 * @author Maayan Ifergan
 *
 * @version 3
 *
 * @since 23.01.2023
 */
public class SpriteCollection {
    private ArrayList<Sprite> spriteCollection;
    /**
     * Constructs a SpriteCollection with the provided initial sprites.
     *
     * @param spriteCollection The initial sprites to populate the SpriteCollection.
     *                         The sprites may be null or an empty list.
     */
    public SpriteCollection(ArrayList<Sprite> spriteCollection) {
        this.spriteCollection = spriteCollection;
    }

    /**
     * Returns the collection of sprites.
     *
     * @return The collection of sprites.
     */
    public ArrayList<Sprite> getSpriteCollection() {
        return this.spriteCollection;
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s The sprite to be added. Should not be null.
     */
    public void addSprite(Sprite s) {
        spriteCollection.add(s);
    }

    /**
     * Removes a sprite from the sprite collection.
     * This method removes the specified sprite from the sprite collection.
     *
     * @param s The sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        spriteCollection.remove(s);
    }
    /**
     * Notifies all sprites in the collection that a unit of time has passed.
     * This method should be called periodically to update sprite states based on time.
     */
    public void notifyAllTimePassed() {
        int size = spriteCollection.size();
        for (int i = 0; i < spriteCollection.size(); i++) {
            spriteCollection.get(i).timePassed();
            if (size != spriteCollection.size()) {
                i--;
                size = spriteCollection.size();
            }
        }
    }
    /**
     * Draws all sprites in the collection on the provided DrawSurface.
     *
     * @param d The DrawSurface on which to draw the sprites. Should not be null.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteCollection) {
            sprite.drawOn(d);
        }
    }
}