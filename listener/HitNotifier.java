package listener;


/**
 * @author Maayan Ifergan
 *
 * @version 5
 *
 * @since 25.02.2024
 */
public interface HitNotifier {
    /**
     * Adds a HitListener to be notified when a hit event occurs.
     *
     * @param hl The HitListener to add.
     */
    void addHitListener(HitListener hl);
    /**
     * Removes a HitListener from the list of listeners.
     *
     * @param hl the HitListener to be removed.
     */
    void removeHitListener(HitListener hl);
}
