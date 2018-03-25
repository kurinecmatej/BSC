package sk.bsc.commons;

import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import sk.bsc.currencies.Currency;
import sk.bsc.currencies.CurrencyName;
import sk.bsc.exceptions.NoCurrenciesException;

/**
 * Utility class for updating currency amount.
 * 
 * @author matej
 *
 */
public class SaveAmountUtil {

	/**
	 * Method for update currency amount.
	 * 
	 * @param sb input command from console, or one line from init file.
	 * @param currencies all active currencies in the app.
	 * @throws NoCurrenciesException 
	 */
	public static void saveAmount(String sb, List<Currency> currencies) throws NoCurrenciesException {
		
		// at least one currency has to be implemented in this app
		if(CollectionUtils.isEmpty(currencies)) {
			throw new NoCurrenciesException("No currencies are implemented in this application.");
		}
		
		// wrong input. Input have to be at least 5 chars long (3ch currency name + space + at least one digit)
		if (sb == null || sb.length() < 5) {
			System.err.println("Please enter valid input.");
			return;
		}
		
		// split input line to currency name and value to update
		String currencyName = sb.substring(0, 3);
		String currencyValue = sb.substring(4);
		
		for(Currency currency : currencies) {
			
			String classCurrency = currency.getClass().getAnnotation(CurrencyName.class).value().toUpperCase();
			// check if currency exists in app
			if(classCurrency.equals(currencyName)) {
				try {
					// parse number from input line
					double d = Double.parseDouble(currencyValue);  
					// update currency amount
					currency.addAmount(d);
				} catch(NumberFormatException nfe) {
					System.err.println("Wrong format of value.");
				}
				return;
			} else if(classCurrency.equalsIgnoreCase(currencyName)) {
				// if currency name is just in wrong format (E.g. lowercase)
				System.err.println("Input currency name must be in UPPERCASE.");
				return;
			}
		}
		
		//wrong input formats
		if(StringUtils.isEmpty(currencyValue)) {
			System.err.println("Input currency not exists.");
		} else {
			System.err.println("Please enter valid input.");
		}
	}
	
}
