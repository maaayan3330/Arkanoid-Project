// 212437453 Maayan Ifergan

package game;

import ball.Ball;
import listener.Block;
import listener.HitListener;

/**
 * @author Maayan Ifergan
 *
 * @version 5
 *
 * @since 25.02.2024
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * ScoreTrackingListener tracks and updates the score in the game.
     *
     * @param scoreCounter The counter for tracking the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Handles a hit event between a block and a ball.
     *
     * @param beingHit The block being hit.
     * @param hitter The ball that hits the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}
