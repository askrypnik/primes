package sigma.primes.executors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;

import sigma.primes.interfaces.ICalculator;
import sigma.primes.utils.BoundariesUtils;

/**
 * Iterative algorithm which loops the initial array split into segments
 * 
 * @author alex
 *
 */
public class IterativeCalculator extends ACalculator {

    @Override
    public CalculatorType getCalculatorType() {
	return CalculatorType.Iterative;
    }

    @Override
    public ICalculator init(int concurrency, int startSearch, int endSearch,
	    ExecutorCompletionService<Collection<Integer>> service) {
	setStartSearch(startSearch);
	setEndSearch(endSearch);
	setStep(BoundariesUtils.getStep(startSearch, endSearch, concurrency));
	if (service == null) {
	    setStartSegment(startSearch);
	    setEndSegment(endSearch);
	    return this;
	}
	for (int i = 0; i < concurrency; i++) {
	    int s = BoundariesUtils.getStart(startSearch, endSearch, step, i);
	    int e = BoundariesUtils.getEnd(startSearch, endSearch, step, i);
	    Callable<Collection<Integer>> task = () -> {
		try {
		    return findPrimeNumbers(s, e);
		} catch (Exception ex) {
		    throw new IllegalStateException("task interrupted", ex);
		}
	    };
	    service.submit(task);
	}
	return this;
    }

    @Override
    public Collection<Integer> findPrimeNumbers(int start, int end) throws IllegalArgumentException {
	validateInput(start, end);
	Collection<Integer> res = new ArrayList<>();
	for (int i = start; i <= end; i++) {
	    if (ICalculator.isPrime(i)) {
		res.add(i);
	    }
	}
	return res;
    }

}
