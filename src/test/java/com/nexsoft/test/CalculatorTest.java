package com.nexsoft.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.nexsoft.calc.Calculator;



public class CalculatorTest {

	/**
	 * @ -> annotation assertEquals(actual, expected) -> if actual = expected =>
	 * success; else => failure assertNotEquals(actual, expected) -> if actual =
	 * expected => failure; else => success assertAll("Optional Message", group of
	 * assert / execute / lambda) assertThrows(expected throws, actual throws) -> if
	 * actual = expected => success; else => failure assertThrows(Exception.class,
	 * lambda tested method) assertDoesNotThrow(lambda tested method) -> if result =
	 * throw => false; else => success
	 * 
	 * @Test -> without parameter test @DisplayName("Display name") -> display name
	 *       on JUnit GUI test runner
	 * @Disabled -> disabled test run for specific test
	 * @ParameterizedTest -> with parameter test @ValueSource() -> single variable
	 *                    parameterized test
	 * @MethodSource -> multi variable parameterized test @CsvSource() -> multi
	 *               variable using csv
	 * @CsvFileSource -> multi variable using csv file Integer.parseInt() -> convert
	 *                int to String
	 */

	/*
	 * NOTES : SOME RESULTS ARE INTENTIONALLY THWARTED
	 */

	Calculator calc; // variable container for instantiate class Calculator
	int actual, expected; // variable container for actual and/or expected value

	@BeforeAll
	public static void setup() {
		// method for @BeforeAll, should be static
	}

	@BeforeEach
	public void init() {
		// method for @BeforeEach. can be used for initialization like instantiate or
		// value

		calc = new Calculator(); // instantiate object from Calculator class
	}

	// ======================================================================
	// indirect / using variable
	@Test
	public void testAdd1() {
		actual = 10;
		expected = calc.add(5, 5); // because return value so -> expected = 5 + 5 = 10
		assertEquals(actual, expected);
	}

	@Test
	public void testSubstract1() {
		actual = 6;
		expected = calc.susbtract(10, 4); // because return value so -> expected = 10 - 4 = 6
		assertEquals(actual, expected);
	}

	// or direct
	@Test
	public void testAdd2() {
		assertEquals(10, calc.add(5, 5));
	}

	@Test
	public void testSubstract2() {
		assertEquals(6, calc.susbtract(10, 4));
	}

	// ======================================================================
	@Test
	public void testAdd4() {
		assertNotEquals(1, calc.add(9, 0));
	}

	@Test
	public void testSubstract4() {
		assertNotEquals(3, calc.susbtract(14, 7));
	}

	// ======================================================================
	@Test
	public void testUnion1() {
		assertAll("test for addition and substraction method", () -> assertEquals(50, calc.add(20, 30)),
				() -> assertEquals(40, calc.susbtract(70, 30)), () -> assertNotEquals(13, calc.add(1, 7)),
				() -> assertNotEquals(-2, calc.susbtract(7, 1)));
	}

	// ======================================================================
	@Test
	public void testDivideThrow1() {
		assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
	}

	@Test
	public void testDivideThrow2() {
		assertDoesNotThrow(() -> calc.divide(5, 1));
	}

	// ======================================================================
	@ParameterizedTest
	@ValueSource(ints = { 1, 3, 5, 7, 9 })
	public void testCheckGanjil(int varName) {
		assertTrue(calc.isGanjil(varName));
	}

	@ParameterizedTest
	@ValueSource(strings = { "a", "i", "u", "e", "o" })
	public void testCheckVocal(String alphabet) {
		assertTrue(calc.isVocal(alphabet));
	}

	// ======================================================================
	@DisplayName("Divide test using multi parameter")
	@ParameterizedTest
	@MethodSource("methodSourceName")
	public void testDivideStream(int a, int b, int expected) {
		assertEquals(expected, calc.divide(a, b));
	}

	private static Stream<Arguments> methodSourceName() {
		// Arguments.of(60 -> int a, 15 -> int b, 4 -> expected or check image A for
		// details
		return Stream.of(Arguments.of(25, 25, 1), Arguments.of(60, 15, 4));
	}

	@DisplayName("Divide test using multi parameter using csv source")
	@ParameterizedTest
	@CsvSource({ "2, 2, 3, 12", "3, 4, 2, 24", "5, 5, 2, 50" })
	public void testMultiplyCsv(String a, String b, String c, String expected) {
		assertEquals(Integer.parseInt(expected),
				calc.multiply(Integer.parseInt(a), Integer.parseInt(b), Integer.parseInt(c)));
	}

	@DisplayName("Divide test using multi parameter using csv file")
	@ParameterizedTest
	@CsvFileSource(resources = "/multiply-test.csv", numLinesToSkip = 1) // check image B for details
	public void testMultiplyCsvFile(int a, int b, int c, int expected) {
		assertEquals(expected, calc.multiply(a, b, c));
	}
	/*
	 * NOTES : SOME RESULTS ARE INTENTIONALLY THWARTED
	 */
}
