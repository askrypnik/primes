package sigma.primes.utils;

/**
 * Utils class
 * 
 * @author alex
 *
 */
public final class BoundariesUtils {

    /**
     * Method to calculate step function
     * 
     * @param start
     * @param end
     * @param concurrency
     * @return
     */
    public static final int getStep(int start, int end, int concurrency) {
	return (end - start + 1) / concurrency;
    }

    /**
     * Method to calculate Start of segment for an iteration
     * 
     * @param start
     * @param end
     * @param step
     * @param iteration
     * @return
     */
    public static final int getStart(int start, int end, int step, int iteration) {
	return start + iteration * step;
    }

    /**
     * Method to calculate End of segment for an iteration
     * 
     * @param start
     * @param end
     * @param step
     * @param iteration
     * @return
     */
    public static final int getEnd(int start, int end, int step, int iteration) {
	int r = Math.min(end, start + (iteration + 1) * step - 1);
	if (end - r < step) {
	    return end;
	}
	return r;
    }
}
