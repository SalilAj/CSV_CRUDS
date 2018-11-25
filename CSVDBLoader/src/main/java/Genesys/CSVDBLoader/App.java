package Genesys.CSVDBLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class App {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String DBurl;
		File file;

		try {
			System.out.println("Enter File Path:");
			file = new File(br.readLine());

			if (!file.exists() || !file.isFile()) {
				System.out.println("File doesnot exist. Please input a correct File. Program Exiting... \n");
				return;
			}

			System.out.println("Enter MongoDB Database URL:");
			DBurl = br.readLine();

		} catch (IOException e) {
			System.out.println("Invalid Input. Program Exiting... \n");
			return;
		}

		//callmongo insert
	}
}
