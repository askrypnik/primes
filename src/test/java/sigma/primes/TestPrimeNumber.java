package sigma.primes;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sigma.primes.interfaces.ICalculator;

/**
 * Test cases to cover prime numbers resolvers
 * 
 * @author alex
 *
 */
public class TestPrimeNumber extends TestCase {

    /**
     * Create the test case
     *
     * @param testName
     *            name of the test case
     */
    public TestPrimeNumber(String testName) {
	super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
	return new TestSuite(TestPrimeNumber.class);
    }

    public void testIFinderIsPrimeCheck() {
	assertTrue(ICalculator.isPrime(1));
	assertTrue(ICalculator.isPrime(2));
	assertTrue(ICalculator.isPrime(3));
	assertFalse(ICalculator.isPrime(4));
	assertTrue(ICalculator.isPrime(5));
	assertFalse(ICalculator.isPrime(6));
	assertTrue(ICalculator.isPrime(7));
	assertFalse(ICalculator.isPrime(8));
	assertFalse(ICalculator.isPrime(9));
	assertFalse(ICalculator.isPrime(10));
	assertTrue(ICalculator.isPrime(11));
	assertFalse(ICalculator.isPrime(12));
	assertTrue(ICalculator.isPrime(13));
	assertFalse(ICalculator.isPrime(14));
	assertFalse(ICalculator.isPrime(15));
	assertFalse(ICalculator.isPrime(16));
	assertTrue(ICalculator.isPrime(17));
	assertFalse(ICalculator.isPrime(18));
	assertTrue(ICalculator.isPrime(19));
	assertFalse(ICalculator.isPrime(20));
    }
}
