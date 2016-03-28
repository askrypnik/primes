package sigma.primes.executors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;

import sigma.primes.interfaces.ICalculator;
import sigma.primes.utils.BoundariesUtils;

/**
 * Sieve of Eratosthenes algorithm implementation
 * 
 * @author alex
 *
 */
public class EratosthenesSieveCalculator extends ACalculator {

    private Collection<Integer> sieve = new ArrayList<>();

    @Override
    public CalculatorType getCalculatorType() {
	return CalculatorType.Eratosthenes;
    }

    @Override
    public ICalculator init(int concurrency, int startSearch, int endSearch,
	    ExecutorCompletionService<Collection<Integer>> service) {
	setStartSearch(startSearch);
	setEndSearch(endSearch);
	int k = (int) Math.ceil(Math.sqrt(endSearch));
	for (int i = 2; i < k; i++) {
	    if (ICalculator.isPrime(i)) {
		sieve.add(i);
	    }
	}
	startSegment = Math.max(startSearch, k);
	setStep(BoundariesUtils.getStep(startSegment, endSearch, concurrency));
	if (service == null) {
	    setEndSegment(endSearch);
	    return this;
	}
	for (int i = 0; i < concurrency; i++) {
	    int s = BoundariesUtils.getStart(getStartSegment(), getEndSearch(), getStep(), i);
	    int e = BoundariesUtils.getEnd(getStartSegment(), getEndSearch(), getStep(), i);
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
	Collection<Integer> res = new LinkedList<>(); // choosing this to get
						      // O(1) for add / remove
	for (int i = start; i <= end; i++) {
	    boolean isPrime = true;
	    for (Integer si : sieve) {
		if (i % si.intValue() == 0) {
		    isPrime = false;
		    break;
		}
	    }
	    if (isPrime) {
		res.add(i);
	    }
	}
	if (end == endSearch) {
	    if (startSearch == 1) {
		res.add(1);
	    }
	    res.addAll(sieve);
	}
	return res;
    }

}
