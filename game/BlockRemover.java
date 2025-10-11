// 212437453 Maayan Ifergan

package game;
import ball.Ball;
import listener.HitListener;
import listener.Block;

/**
 * @author Maayan Ifergan
 *
 * @version 5
 *
 * @since 25.02.2024
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Manages block removal and updates remaining block count in the game.
     *
     * @param game The game from which blocks will be removed.
     * @param remainingBlocks The counter for remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Handles a hit event between a block and a ball.
     *
     * @param beingHit The block being hit.
     * @param hitter The ball that hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // Change the color
        hitter.setColor(beingHit.getColor());
        // Remove
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}
