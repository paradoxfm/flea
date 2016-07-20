package ru.megazlo.flea.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ExampleTest {
	@Parameterized.Parameters(name = "pass {index}: fib({0})={1}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3}, {5, 5}, {6, 8}
		});
	}

	@Parameterized.Parameter // first data value (0) is default
	public /* NOT private */ int fInput;

	@Parameterized.Parameter(value = 1)
	public /* NOT private */ int fExpected;

	@Test
	public void test() {
		assertEquals(fExpected, Fibonacci.compute(fInput));
	}
}

class Fibonacci {
	static int compute(int n) {
		return n <= 1 ? n : compute(n - 1) + compute(n - 2);
	}
}
