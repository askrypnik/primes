package sigma.primes;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import junit.framework.TestCase;
import sigma.primes.executors.EratosthenesSieveCalculator;
import sigma.primes.executors.IterativeCalculator;
import sigma.primes.interfaces.ICalculator;

@RunWith(Parameterized.class)
public class TestAlgos extends TestCase {

    private ICalculator calculator;
    private int[] expected;

    @Parameters(name = "Testing {0}")
    public static Collection<Object[]> data() {
	return Arrays.asList(
		new Object[][] { { new IterativeCalculator().init(1, 1, 10, null), new int[] { 1, 2, 3, 5, 7 } },
			{ new EratosthenesSieveCalculator().init(1, 1, 10, null), new int[] { 1, 2, 3, 5, 7 } } });
    }

    public TestAlgos(ICalculator calc, int[] exp) {
	calculator = calc;
	expected = exp;
    }

    @Test
    public void testNumbers() {
	Collection<Integer> res = calculator.findPrimeNumbers(calculator.getStartSegment(), calculator.getEndSegment());
	assertNotNull("Result is not null", res);

	for (int i : expected) {
	    assertTrue(res.contains(i));
	}
	assertEquals("Number of prime numbers", expected.length, res.size());
    }
}
