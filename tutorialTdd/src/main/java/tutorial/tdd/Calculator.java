package tutorial.tdd;

public class Calculator {
	public Integer sum(Integer number1, Integer number2) {
		return number1 + number2;
	}

	public Integer divide(Integer number1, Integer number2) {
		return number1 / number2;
	}

	public Integer multiply(Integer number1, Integer number2) throws Exception {
        Thread.sleep(6000);
		return number1 * number2;
	}

	public Integer substract(Integer number1, Integer number2) {
		return number1 - number2;
	}
}
