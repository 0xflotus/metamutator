import static org.junit.Assert.fail;

import org.junit.Test;

public class ClassSleeping2 {
	
	@Test
	public void testwait10s() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			fail();
		}
	}
}