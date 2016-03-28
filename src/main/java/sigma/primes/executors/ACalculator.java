package sigma.primes.executors;

import sigma.primes.interfaces.ICalculator;

/**
 * Abstract class
 * 
 * @author alex
 *
 */
public abstract class ACalculator implements ICalculator {

    protected int startSearch, endSearch;
    protected int startSegment, endSegment;
    protected int step;

    @Override
    public int getStartSearch() {
	return startSearch;
    }

    @Override
    public void setStartSearch(int startSearch) {
	this.startSearch = startSearch;
    }

    @Override
    public int getEndSearch() {
	return endSearch;
    }

    @Override
    public void setEndSearch(int endSearch) {
	this.endSearch = endSearch;
    }

    @Override
    public int getStartSegment() {
	return startSegment;
    }

    @Override
    public void setStartSegment(int startSegment) {
	this.startSegment = startSegment;
    }

    @Override
    public int getEndSegment() {
	return endSegment;
    }

    @Override
    public void setEndSegment(int endSegment) {
	this.endSegment = endSegment;
    }

    @Override
    public int getStep() {
	return step;
    }

    @Override
    public void setStep(int step) {
	this.step = step;
    }

    @Override
    public String toString() {
	return getCalculatorType().getDescription();
    }

}
