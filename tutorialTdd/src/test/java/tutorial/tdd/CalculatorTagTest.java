package tutorial.tdd;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CalculatorTagTest {
	
	@Test
	@DisplayName("Test: Try sum when two positive number is given (1, 1)")
	@Tag("development")
	@Tag("pako")
	void testSum() {
		Calculator myCalculator = new Calculator();
		Integer calculatorResult;
		
		calculatorResult = myCalculator.sum(1, 1);
		
		System.out.println("Test Sum");
		
		assertEquals(2, calculatorResult);
		assertTrue(calculatorResult.equals(2));
		
		assertNotNull(calculatorResult);
		assertTrue(calculatorResult != null);
	}
	
	@Test
	@DisplayName("Test: Try sum when two positive number is given (1, 1)")
	@Tag("pako")
	void testSumWithAssertAll() {
		Calculator myCalculator = new Calculator();
		Integer calculatorResult;
		
		calculatorResult = myCalculator.sum(1, 1);
		
		System.out.println("Test Sum");
		
		assertAll(() -> {
			assertEquals(2, calculatorResult);
			assertTrue(calculatorResult.equals(2));
			
			assertNotNull(calculatorResult);
			assertTrue(calculatorResult != null);
		});
	}
}
