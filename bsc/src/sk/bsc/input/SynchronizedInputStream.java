package sk.bsc.input;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class used for synchronized input. All input systems should extends this class and 
 * use its read method for obtain input. Then input can be parsed by any mechanism in particular subclass.
 * 
 * @author matej
 *
 */
public class SynchronizedInputStream extends InputStream {
	
	private InputStream in;

    public SynchronizedInputStream(InputStream in) {
        this.in = in;
    }
	
	@Override
	public synchronized int read() {
		try {
            return in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
	}
	
	@Override
    public synchronized int available() {
		try {
            return in.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
