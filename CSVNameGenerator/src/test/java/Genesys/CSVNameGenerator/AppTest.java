package Genesys.CSVNameGenerator;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testApp() {
		
		boolean result = false;
		Path path = Paths.get(".");
		App.generateCSV(path, 5);

		File file = new File("Genesys.csv");
		if (file.exists() && file.isFile()) {
			result = true;
		}

		assertEquals(true, result);
	}

}
