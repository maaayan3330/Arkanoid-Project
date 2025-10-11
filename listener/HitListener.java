// 212437453 Maayan Ifergan

package listener;
import ball.Ball;

/**
 * @author Maayan Ifergan
 *
 * @version 5
 *
 * @since 25.02.2024
 */
public interface HitListener {
    /**
     * Handles a hit event between a block and a ball.
     *
     * @param beingHit the block being hit.
     * @param hitter the ball that hits the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
