package sk.bsc.tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import sk.bsc.commons.SaveAmountUtil;
import sk.bsc.currencies.Currency;
import sk.bsc.currencies.Euro;
import sk.bsc.currencies.Gpb;
import sk.bsc.exceptions.NoCurrenciesException;

/**
 * Test class for save amount utility method.
 * 
 * @author matej
 *
 */
public class SaveAmountUtilTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(System.out);
	    System.setErr(System.err);
	}
	
	private List<Currency> createCurrencies() {
		
		List<Currency> currencies = new LinkedList<>();
		currencies.add(new Euro(1.12));
		currencies.add(new Gpb(0.97));
		
		return currencies;
	}
	
	
	/**
	 * Test for {@link SaveAmountUtil#saveAmount(String, java.util.List)} method.
	 * 
	 * In this test input string representing one command, or line from input file is shorter than should to be.
	 */
	@Test
	public void saveAmountShortString() {
		
		SaveAmountUtil.saveAmount("EUR", createCurrencies());
		TestCase.assertEquals("Please enter valid input.\n", errContent.toString());
	}
	
	/**
	 * Test for {@link SaveAmountUtil#saveAmount(String, java.util.List)} method.
	 * 
	 * In this test input string representing one command, or line from input file contains
	 * existing currency name, but in wrong format (E.g. lowercase).
	 */
	@Test
	public void saveAmountWrongCurrencyNameFormat() {
		
		SaveAmountUtil.saveAmount("EuR 20", createCurrencies());
		TestCase.assertEquals("Input currency name must be in UPPERCASE.\n", errContent.toString());
	}
	
	/**
	 * Test for {@link SaveAmountUtil#saveAmount(String, java.util.List)} method.
	 * 
	 * Input currency amount in wrong format (no number).
	 */
	@Test
	public void saveAmountWrongCurrencyValueFormat() {
		
		SaveAmountUtil.saveAmount("EUR 20,4", createCurrencies());
		TestCase.assertEquals("Wrong format of value.\n", errContent.toString());
	}
	
	/**
	 * Test for {@link SaveAmountUtil#saveAmount(String, java.util.List)} method.
	 * 
	 * Input string is null.
	 */
	@Test
	public void saveAmountInputStringNull() {
		
		SaveAmountUtil.saveAmount(null, createCurrencies());
		TestCase.assertEquals("Please enter valid input.\n", errContent.toString());
	}
	
	/**
	 * Test for {@link SaveAmountUtil#saveAmount(String, java.util.List)} method.
	 * 
	 * Input currency not exists in application.
	 */
	@Test
	public void saveAmountInputStringNonExistingCurrency() {
		
		SaveAmountUtil.saveAmount("SKK 20.4", createCurrencies());
		TestCase.assertEquals("Please enter valid input.\n", errContent.toString());
	}
	
	/**
	 * Test for {@link SaveAmountUtil#saveAmount(String, java.util.List)} method.
	 * 
	 * Passed list with all active currencies is null.
	 * NoCurrenciesException is expected.
	 */
	@Test
	public void saveAmountCurrenciesNull() {
		
		try {
			SaveAmountUtil.saveAmount("SKK 20.4", null);
		} catch (NoCurrenciesException ex) {
			return;
		}
		 
		TestCase.fail("Should throw exception.");
	}
	
	/**
	 * Test for {@link SaveAmountUtil#saveAmount(String, java.util.List)} method.
	 * 
	 * Passed list with all active currencies is empty.
	 * NoCurrenciesException is expected.
	 */
	@Test
	public void saveAmountCurrenciesEmptyList() {
		
		try {
			SaveAmountUtil.saveAmount("SKK 20.4", new LinkedList<>());
		} catch (NoCurrenciesException ex) {
			return;
		}
		 
		TestCase.fail("Should throw exception.");
	}
	
	/**
	 * Test for {@link SaveAmountUtil#saveAmount(String, java.util.List)} method.
	 * 
	 * Correct parameters, but final currency amount will be negative after execution.
	 * In this situation output warning is printed and no change on amount is done.
	 */
	@Test
	public void saveAmountNegativeResult() {
		
		SaveAmountUtil.saveAmount("EUR -1000", createCurrencies());
		TestCase.assertEquals("Amount of EUR can not be negative.\n", errContent.toString());
	}
	
	/**
	 * Test for {@link SaveAmountUtil#saveAmount(String, java.util.List)} method.
	 * 
	 * Correct parameters. Tested positive situation.
	 * In this test EUR amount is updated and tested its new value.
	 */
	@Test
	public void saveAmountCorrectParameters() {
		
		List<Currency> currencies = createCurrencies();
		
		SaveAmountUtil.saveAmount("EUR 20.4", currencies);
		
		TestCase.assertEquals("Updated value not as expected.", 20.4, currencies.get(0).getAmount());
	}
}
