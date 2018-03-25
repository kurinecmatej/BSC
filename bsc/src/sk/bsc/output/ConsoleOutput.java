package sk.bsc.output;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Class used for output. Output mechanism is running in its own thread, for needed of parallelism
 * between printing output and processing input.
 * 
 * @author matej
 *
 */
@Component
public class ConsoleOutput extends UserInterface implements Runnable {

	@Value("${timer}")
	private int timerLoop;
	
	private Timer timer = new Timer();
	
	@Override
	public void run() {
		// set loop timer 
        timer.schedule(new HelloWorld(), 0, timerLoop);
	}

	/**
	 * Inner class for printing output in loop.
	 * 
	 * @author matej
	 *
	 */
	public class HelloWorld extends TimerTask{

		@Override
	    public void run() {
	    	printCurrenciesAmount();
	    }
	}
	
	/**
	 * Cleanup method, when this thread is interrupted. Need to stop timer.
	 */
	public void cleanUp() {
		timer.cancel();
	}
}
