package sk.bsc.currencies;

/**
 * Interface with methods used in each currency.
 * 
 * @author matej
 *
 */
public interface Currency {
	
	/**
	 * Get currency amount value.
	 * 
	 * @return currency amount value.
	 */
	public double getAmount();
	
	/**
	 * Get currency exchange rate value.
	 * 
	 * @return currency exchange rate value.
	 */
	public double getExchangeRate();
	
	/**
	 * Update currency amount. If result amount value will be negative, no changes are done
	 * and warning message is printed.
	 * 
	 * @param delta value to add to actual amount.
	 */
	public void addAmount(double delta);
}
