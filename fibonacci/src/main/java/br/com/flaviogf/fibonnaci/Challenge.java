package br.com.flaviogf.fibonnaci;

import java.util.ArrayList;
import java.util.List;

public class Challenge {

	public static List<Integer> fibonacci() {
		List<Integer> numbers = new ArrayList<>();

		Integer result = 0;

		Integer i = 0;

		while ((result = fibonacci(i)) <= 350) {
			numbers.add(result);
			i++;
		}

		numbers.add(fibonacci(i));

		return numbers;
	}

	public static Boolean isFibonacci(Integer number) {
		List<Integer> numbers = fibonacci();
		
		return numbers.contains(number);
	}

	private static Integer fibonacci(Integer number) {
		if (number < 2) {
			return number;
		}

		return fibonacci(number - 1) + fibonacci(number - 2);
	}
}
