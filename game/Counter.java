package game;

/**
 * @author Maayan Ifergan
 *
 * @version 5
 *
 * @since 25.02.2024
 */
public class Counter {
    private int count = 0;

    /**
     * Adds the given number to the current count.
     *
     * @param number The number to add to the current count.
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Subtracts the given number from the current count.
     *
     * @param number The number to subtract from the current count.
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Retrieves the current count.
     *
     * @return The current count.
     */
    public int getValue() {
        return count;
    }
}
