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
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Manages ball removal and updates remaining ball count in the game.
     *
     * @param game The game from which balls will be removed.
     * @param remainingBalls The counter for remaining balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    /**
     * Handles a hit event between a block and a ball.
     *
     * @param beingHit The block being hit.
     * @param hitter The ball that hits the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Remove
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
