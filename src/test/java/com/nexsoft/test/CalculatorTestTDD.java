package com.nexsoft.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.nexsoft.calc.Calculator;

public class CalculatorTestTDD {
	
	@ParameterizedTest
	@CsvFileSource(resources = "multiply-test.csv", numLinesToSkip = 1)
	public void test_multiply_fn_by_integer(int a, int b, int c, int expected) {
		// arrange
		Calculator calc = new Calculator();
		
		// act
		int result = calc.multiply(a, b, c);
		
		// assert
		assertEquals(expected, result);
	}
}
