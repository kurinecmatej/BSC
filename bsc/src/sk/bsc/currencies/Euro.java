package sk.bsc.currencies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Class representing euro currency.
 * 
 * This class is example of usage @CurrencyName annotation.
 * Class name is different from currency name. Currency name is defined using this annotation.
 * 
 * @author matej
 *
 */
@Component
@Scope("singleton")
@CurrencyName("eur")
public class Euro extends AbstractCurrency implements Currency {
			
	public Euro(@Value("${eur}") double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	

	@Override
	public synchronized double getAmount() {
		return amount;
	}

	@Override
	public double getExchangeRate() {
		return exchangeRate;
	}
	
	@Override
	public synchronized void addAmount(double delta) {
		
		double tmp = this.amount + delta;
		if(tmp < 0) {
			System.err.println("Amount of " + Euro.class.getAnnotation(CurrencyName.class).value().toUpperCase() + " can not be negative.");
		} else {
			this.amount += delta;
		}
	}
	

}
