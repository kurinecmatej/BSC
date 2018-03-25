package sk.bsc.input;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sk.bsc.commons.SaveAmountUtil;
import sk.bsc.currencies.Currency;

/**
 * Class for process input from console. This class is running in its own thread.
 * 
 * @author matej
 *
 */
@Component
public class ConsoleInput extends SynchronizedInputStream implements Runnable {
	
	@Autowired
	private List<Currency> currencies;
	
	private boolean running = true;
	
	public ConsoleInput() {
		super(System.in);
	}

	@Override
	public void run() {
		while (running) {
			StringBuilder sb = new StringBuilder();
			int input = read();
			// process input values until new line or return is received
			while (input != 10 && input != 13) {
				// create whole line string
				sb.append((char) input);
				input = read();
			}

			// process received string line
			if (sb.length() > 0) {
				processInput(sb);
			}
			sb.setLength(0);
		}
	}
	
	/**
	 * Method for input line process. If "quit" is received, end this thread.
	 * 
	 * @param sb received string line
	 */
	private void processInput(StringBuilder sb) {
		
		if(sb.toString().equals("quit")) {
			running = false;
			return;
		}
		
		SaveAmountUtil.saveAmount(sb.toString(), currencies);	
	}

}
