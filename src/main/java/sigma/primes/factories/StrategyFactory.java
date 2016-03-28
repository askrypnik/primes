package sigma.primes.factories;

import sigma.primes.executors.EratosthenesSieveCalculator;
import sigma.primes.executors.IterativeCalculator;
import sigma.primes.interfaces.ICalculator;
import sigma.primes.interfaces.ICalculator.CalculatorType;

/**
 * Factory providing the algorithm implementation
 * 
 * @author alex
 *
 */
public class StrategyFactory {

    public static ICalculator provideCalculator(String calculatorName) throws IllegalArgumentException {
	try {
	    CalculatorType t = CalculatorType.valueOf(calculatorName);
	    switch (t) {
	    case Iterative:
		return new IterativeCalculator();
	    case Eratosthenes:
		return new EratosthenesSieveCalculator();
	    }
	} catch (Exception ex) {
	    new IllegalArgumentException("Calculator unrecognized: " + calculatorName);
	}
	throw new IllegalArgumentException("Calculator unrecognized: " + calculatorName);
    }
}
