package Genesys.CSVNameGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Path path;
		String pathInput;
		int entries;

		try {
			System.out.println("Enter File Path:");
			pathInput = br.readLine();

			path = Paths.get(pathInput);

			if (!Files.exists(path)) {
				System.out.println("Path doesnot exist. Please input a correct Path. Program Exiting... \n");
				return;
			}

			System.out.println("Enter Number of Entries (1-10,000):");
			entries = Integer.parseInt(br.readLine());

			if (entries < 1 || entries > 10000) {
				System.out.println(
						"Invalid number of entries. Please input values between (1-10,000) Program Exiting... \n");
				return;
			}

		} catch (IOException e) {
			System.out.println("Invalid Input. Program Exiting... \n");
			return;
		}

		//Call Name generator
	}
}
