package sk.bsc.project;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sk.bsc.input.ConsoleInput;
import sk.bsc.output.ConsoleOutput;

/**
 * This application is used to calculate currency operations. 
 * At this moment there are 5 currencies implemented: EUR, GPB, HKD, RMB, USD.
 * 
 * Detailed informations about this app are in readme.txt.
 * 
 * <b>In this app is used Spring framework for CDI.</b>
 * 
 * @author Matej Kurinec. March 24th 2018.
 *
 */
public class Main {

	public static void main(String[] args) {
				
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// if possible, initialize currencies values from input file
		if(args.length > 0) {
			FileInit fileInit = context.getBean("fileInit", FileInit.class);
			fileInit.setPath(args[0]);		
			fileInit.read();
		}

		ConsoleOutput out = context.getBean("consoleOutput", ConsoleOutput.class);
		ConsoleInput in = context.getBean("consoleInput", ConsoleInput.class);
		
		// thread for output processing
		Thread t1 = new Thread(out);
		// thread for input processing
		Thread t2 = new Thread(in);

		t1.start();
		t2.start();
		
		try {
			// wait to input thread end. Thread ends when "quit" is eneterd
			t2.join();
			t1.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// cleanup process
			out.cleanUp();
			context.close();
			System.out.println("Bye Bye.");
		}
						

	}
}
