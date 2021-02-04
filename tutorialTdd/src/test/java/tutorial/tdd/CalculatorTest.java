package tutorial.tdd;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Anotación display a nivel de clase")
public class CalculatorTest {
	
	@BeforeAll //BeforeClass
	static void beforeAllDoThis() {
		System.out.println("BeforeAll");
	}
	
	@BeforeEach //Before
	void beforeEachDoThis() {
		System.out.println("BeforeEach");
	}
	
	@Test
	@Disabled("Desactivado por que estoy completando el código") // @Ignore
	@DisplayName("Test: Try sum when two positive number is given (1, 1)")
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
	
	@Test
	void testDivideByZero() {
		Calculator myCalculator = new Calculator();
		
		assertThrows(ArithmeticException.class, () -> {
			myCalculator.divide(1, 0);
		});
		
		/*try {
			myCalculator.divide(1, 0);
		} catch(Exception ex) {
			if (ex instanceof ArithmeticException) {
				System.out.println("Error dividiendo por 0");
			}
		}*/
	}
	
	@Test
	void testMultiplyAssertTimeout() {
		Calculator myCalculator = new Calculator();
		
		assertTimeout(Duration.ofSeconds(7), () -> {
			Integer multiplyResult;
 			multiplyResult = myCalculator.multiply(5, 4);
 			
 			assertEquals(20, multiplyResult);
		});
	}
	
	@Test
	void testMultiplyAssertTimeoutPreemptively() {
		Calculator myCalculator = new Calculator();
		
		assertTimeoutPreemptively(Duration.ofSeconds(7), () -> {
			Integer multiplyResult;
 			multiplyResult = myCalculator.multiply(5, 4);
 			
 			assertEquals(20, multiplyResult);
		});
	}
	
	@Test
	void testSumWithAssuming() {
		assumingThat(
				System.getProperty("os.name").equals("Linux"), 
				() -> { 
						Calculator myCalculator = new Calculator();
						Integer sumResult;
					
						sumResult = myCalculator.sum(5, 4);
						
						assertEquals(9, sumResult, () -> "Error: Sum is not 9!");
					  });
	}
	
	
	@AfterEach //After
    void afterEachDoThis() {
		System.out.println("AfterEach");
	}
	
	@AfterAll //AfterClass
	static void afterAll() {
		System.out.println("AfterAll");
	}
}
