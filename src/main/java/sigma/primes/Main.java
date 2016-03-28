package sigma.primes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import sigma.primes.factories.StrategyFactory;

/**
 * Prime numbers generator
 * 
 * @author alex
 *
 */
public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    private Main(int start, int end, String calculatorName, int concurrency) {
	logger.info("Starting " + calculatorName + " with Start = " + start + ", End = " + end
		+ ", and concurrency level " + concurrency);
	final ExecutorService pool = Executors.newFixedThreadPool(concurrency);
	ExecutorCompletionService<Collection<Integer>> service = new ExecutorCompletionService<>(pool);
	long startTime = System.currentTimeMillis();
	StrategyFactory.provideCalculator(calculatorName).init(concurrency, start, end, service);
	Collection<Integer> result = new ArrayList<>();
	for (int i = 0; i < concurrency; i++) {
	    try {
		final Future<Collection<Integer>> future = service.take();
		if (future == null || future.get() == null) {
		    continue;
		}
		result.addAll(future.get());
	    } catch (InterruptedException ie) {
		logger.error("Interrupted while fetching results", ie);
	    } catch (ExecutionException ee) {
		logger.error("ExecutionException while fetching results", ee);
	    }
	}
	long endTime = System.currentTimeMillis();
	pool.shutdown();
	logger.info("Took " + (endTime - startTime) + " millis. Found " + result.size() + " numbers: " + result);
    }

    public static void main(String[] args) {
	logger.info("Started Prime Numbers resolver");
	if (args.length != 4) {
	    usagePrint();
	    System.exit(1);
	}
	int start = 0, end = 0, concurrency = 1;
	String calcName = null;
	try {
	    start = resolveInteger(args[0]);
	    end = resolveInteger(args[1]);
	    if (start < 1 || end <= start || end > Runtime.getRuntime().totalMemory() / 4) {
		throw new Exception("Start / End numbers combination is invalid");
	    }
	    calcName = resolveString(args[2]);
	    concurrency = resolveInteger(args[3]);
	    if (concurrency < 1 || concurrency > Runtime.getRuntime().availableProcessors() * 2) {
		throw new IllegalArgumentException(
			"Concurrency must be within 0 and " + (Runtime.getRuntime().availableProcessors() * 2));
	    }
	} catch (Exception ex) {
	    logger.error("Invalid input parameters", ex);
	    usagePrint();
	    System.exit(1);
	}
	new Main(start, end, calcName, concurrency);
	logger.info("Terminating the program...");
    }

    private static void usagePrint() {
	logger.info(
		"    Usage: java Main -start=<start_number> -end=<end_number> -algo=[Iterative|Eratosthenes] -concurrency=<number_of_threads>");
    }

    private static String resolveString(String str) {
	if (str == null || str.length() == 0) {
	    throw new IllegalArgumentException("Invalid argument: " + str);
	}
	if (str.indexOf("=") > 0) {
	    return str.split("=")[1];
	}
	return str;
    }

    private static int resolveInteger(String str) {
	if (str == null || str.length() == 0) {
	    throw new IllegalArgumentException("Invalid argument: " + str);
	}
	if (str.indexOf("=") > 0) {
	    return Integer.parseInt(str.split("=")[1]);
	}
	return Integer.parseInt(str);
    }
}
