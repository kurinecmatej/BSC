package sk.bsc.output;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sk.bsc.currencies.Currency;
import sk.bsc.currencies.CurrencyName;

/**
 * Console user interface output processing.
 * 
 * @author matej
 *
 */
@Component
public class UserInterface {
	
	@Autowired
	private List<Currency> currencies;
	
	public void printCurrenciesAmount() {
		
		currencies.forEach(currency -> {
			// don't print when no amount is present
			if(currency.getAmount() > 0) {
				printAmount(currency);
				// print amount value with value compared to USD when possible
				if(currency.getExchangeRate() != 0 && 
						!currency.getClass().getAnnotation(CurrencyName.class).value().equalsIgnoreCase("usd")) {
					printUsdCompared(currency);
				}
				System.out.println();
			}
		});
	}
	
	/**
	 * Print currency amount value without compared to USD.
	 * 
	 * @param currency which amount print
	 */
	private void printAmount(Currency currency) {
		
		// print max 2 decimal numbers
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.print(currency.getClass().getAnnotation(CurrencyName.class).value().toUpperCase() + " " + df.format(currency.getAmount()));
	}
	
	/**
	 * Print currency amount value with value compared to USD. Just compared value is printed. 
	 * Whole output is then concatenate.
	 * 
	 * @param currency which amount print
	 */
	private void printUsdCompared(Currency currency) {
		
		// print max 2 decimal numbers
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.print(" (USD " + df.format(currency.getAmount() / currency.getExchangeRate()) + ")");
	}

}
