package sk.bsc.tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import mockit.Deencapsulation;
import sk.bsc.currencies.Euro;
import sk.bsc.output.UserInterface;

/**
 * Test class for console output testing.
 * 
 * @author matej
 *
 */
public class UserInterfaceTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	UserInterface ui = new UserInterface();

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
	
	/**
	 * Test for {@link UserInterface#printAmount} method.
	 * 
	 * In this test output format is tested. Currency has set exchange rate. 
	 */
	@Test
	public void printAmountWithExchangeRate() {
		
		Euro euro = new Euro(1.21);
		euro.addAmount(10);
		
		Deencapsulation.invoke(ui, "printAmount", euro);
		
		TestCase.assertEquals("EUR 10", outContent.toString());
		
	}
	
	/**
	 * Test for {@link UserInterface#printUsdCompared} method.
	 * 
	 * In this test output format is tested.
	 */
	@Test
	public void printUsdComparedTest() {
		
		Euro euro = new Euro(1.21);
		euro.addAmount(10);
		
		Deencapsulation.invoke(ui, "printUsdCompared", euro);
		
		TestCase.assertEquals(" (USD 8.26)", outContent.toString());
	}
	
}
