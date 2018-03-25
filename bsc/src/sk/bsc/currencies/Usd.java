package sk.bsc.currencies;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Class representing usd currency.
 * 
 * @author matej
 *
 */
@Component
@Scope("singleton")
@CurrencyName("usd")
public class Usd extends AbstractCurrency implements Currency {

	public Usd() {
		this.exchangeRate = 1;
	}

	@Override
	public double getAmount() {
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
