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

	public static void generateCSV(Path path, int entries) {

		final String FILE_HEADER = "firstName,lastName,age";
		final String NEW_LINE_SEPARATOR = "\n";
		final String COMMA_DELIMITER = ",";

		StringBuffer sbFirstName = new StringBuffer();
		char fname = 'a';
		char lname = 'a';
		int randomAge;

		try {

			FileWriter fileWriter = new FileWriter(path.toString() + "/Genesys.csv");

			fileWriter.append(FILE_HEADER);
			fileWriter.append(NEW_LINE_SEPARATOR);

			for (int i = 0; i < entries; i++) {

				fileWriter.append(sbFirstName.toString() + fname);
				fileWriter.append(COMMA_DELIMITER);

				fileWriter.append(lname);
				fileWriter.append(COMMA_DELIMITER);

				randomAge = ThreadLocalRandom.current().nextInt(1, 99 + 1);
				fileWriter.append(String.valueOf(randomAge));
				fileWriter.append(NEW_LINE_SEPARATOR);

				fname++;
				lname++;

				//if first name reaches 'z', set to 'a' again while pre-pending 'a' to the String buffer
				if (fname > 122) {
					fname = 97;
					sbFirstName.append(fname);
				}
				//if last name reaches 'z', set to 'a' again
				if (lname > 122) {
					lname = 97;
				}

			}
			fileWriter.close();

			System.out.println("done!");

		} catch (IOException e) {
			System.out.println("Path doesnot exist. Please input a correct Path. Program Exiting... \n");
			return;
		}
		return;
	}

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

		generateCSV(path, entries);
	}
}
