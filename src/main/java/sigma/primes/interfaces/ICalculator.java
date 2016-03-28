package sigma.primes.interfaces;

import java.util.Collection;
import java.util.concurrent.ExecutorCompletionService;

/**
 * Interface describing the model used
 * 
 * @author alex
 *
 */
public interface ICalculator {

    /**
     * Calculator type
     * 
     * @author alex
     *
     */
    public enum CalculatorType {
	Iterative("Iterative calculator"), Eratosthenes("Eratosthenes calculator");

	private String description;

	private CalculatorType(String desc) {
	    description = desc;
	}

	public String getDescription() {
	    return description;
	}
    }

    default void validateInput(int start, int end) throws IllegalArgumentException {
	if (end <= start) {
	    throw new IllegalArgumentException("Invalid input parameters: " + start + " :: " + end);
	}
    }

    static boolean isPrime(int number) {
	if (number == 1 || number == 2 || number == 3) {
	    return true;
	}
	if (number < 10) {
	    for (int i = 2; i < number; i++) {
		if (number % i == 0) {
		    return false;
		}
	    }
	} else {
	    for (int i = 2; i <= Math.sqrt(number); i++) {
		if (number % i == 0) {
		    return false;
		}
	    }
	}
	return true;
    }

    CalculatorType getCalculatorType();

    /**
     * Initialises the algorithm
     * 
     * @param concurrency
     * @param start
     * @param end
     * @param service
     */
    ICalculator init(int concurrency, int start, int end, ExecutorCompletionService<Collection<Integer>> service);

    /**
     * Executes the algorithm
     * 
     * @param segmentStart
     * @param segmentEnd
     * @return
     * @throws IllegalArgumentException
     */
    Collection<Integer> findPrimeNumbers(int segmentStart, int segmentEnd) throws IllegalArgumentException;

    /**
     * Returns start of the search
     * 
     * @return
     */
    int getStartSearch();

    /**
     * Sets start of search
     * 
     * @param startSearch
     */
    void setStartSearch(int startSearch);

    /**
     * Returns end of the search
     * 
     * @return
     */
    int getEndSearch();

    /**
     * Sets end of search
     * 
     * @param endSearch
     */
    void setEndSearch(int endSearch);

    /**
     * Returns start of the interval
     * 
     * @return
     */
    int getStartSegment();

    /**
     * Sets start of segment
     * 
     * @param startSegment
     */
    void setStartSegment(int startSegment);

    /**
     * Returns end of the interval
     * 
     * @return
     */
    int getEndSegment();

    /**
     * Sets end of segment
     * 
     * @param endSegment
     */
    void setEndSegment(int endSegment);

    /**
     * Returns step
     * 
     * @return
     */
    int getStep();

    /**
     * Sets the step
     * 
     * @param step
     */
    void setStep(int step);

}
