package sk.bsc.project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sk.bsc.commons.SaveAmountUtil;
import sk.bsc.currencies.Currency;

/**
 * Class for initial setup currencies values.
 * 
 * @author matej
 *
 */
@Component
public class FileInit {

	@Autowired
	private List<Currency> currencies;

	private FileInputStream fis = null;
	private BufferedReader reader = null;
	private String filePath;

	public void setPath(String filePath) {
		this.filePath = filePath;
	}

	public void read() {
		
		try {
			fis = new FileInputStream(filePath);
			reader = new BufferedReader(new InputStreamReader(fis));

			String line = reader.readLine();
			// process each line from file
			while (line != null) {
				SaveAmountUtil.saveAmount(line, currencies);
				line = reader.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
