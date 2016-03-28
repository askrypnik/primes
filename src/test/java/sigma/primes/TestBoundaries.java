package sigma.primes;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sigma.primes.utils.BoundariesUtils;

/**
 * 
 * 
 * 1 100 10: 1-10 11-20 21-30 31-40 41-50 51-60 61-70 71-80 81-90 91-100 10 100
 * 9: 10-18 19-27 28-36 37-45 46-54 55-63 64-72 73-81 82-90 91-99
 * 
 **/
public class TestBoundaries extends TestCase {

    public static Test suite() {
	return new TestSuite(TestBoundaries.class);
    }

    public void testStep() {
	assertEquals(10, BoundariesUtils.getStep(1, 100, 10));
	assertEquals(9, BoundariesUtils.getStep(10, 100, 10));
    }

    public void testStart() {
	assertEquals(1, BoundariesUtils.getStart(1, 100, 10, 0));
	assertEquals(11, BoundariesUtils.getStart(1, 100, 10, 1));
	assertEquals(51, BoundariesUtils.getStart(1, 100, 10, 5));
	assertEquals(91, BoundariesUtils.getStart(1, 100, 10, 9));

	assertEquals(10, BoundariesUtils.getStart(10, 100, 9, 0));
	assertEquals(19, BoundariesUtils.getStart(10, 100, 9, 1));
	assertEquals(55, BoundariesUtils.getStart(10, 100, 9, 5));
	assertEquals(91, BoundariesUtils.getStart(10, 100, 9, 9));

    }

    public void testEnd() {
	assertEquals(10, BoundariesUtils.getEnd(1, 100, 10, 0));
	assertEquals(20, BoundariesUtils.getEnd(1, 100, 10, 1));
	assertEquals(60, BoundariesUtils.getEnd(1, 100, 10, 5));
	assertEquals(100, BoundariesUtils.getEnd(1, 100, 10, 9));

	assertEquals(18, BoundariesUtils.getEnd(10, 100, 9, 0));
	assertEquals(27, BoundariesUtils.getEnd(10, 100, 9, 1));
	assertEquals(63, BoundariesUtils.getEnd(10, 100, 9, 5));
	assertEquals(100, BoundariesUtils.getEnd(10, 100, 9, 9));

    }

}
