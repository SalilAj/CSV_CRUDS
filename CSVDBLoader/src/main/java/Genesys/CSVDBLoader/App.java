package Genesys.CSVDBLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.opencsv.CSVReader;

public class App {
	public static boolean dumpCSVData(File file, String DBurl) {

		try {

			MongoClient mongoClient = new MongoClient(new MongoClientURI(DBurl));
			DB database = mongoClient.getDB("genesys");
			DBCollection collection = database.getCollection("people");

			FileReader filereader = new FileReader(file);
			CSVReader csvReader = new CSVReader(filereader);

			String[] nextRecord;
			String[] headerRecord;
			
			//read each row of the csv, form a document and insert into mongoDB
			if ((headerRecord = csvReader.readNext()) != null) {
				while ((nextRecord = csvReader.readNext()) != null) {
					DBObject person = new BasicDBObject();
					for (int i = 0; i < nextRecord.length; i++) {
						person.put(headerRecord[i], nextRecord[i]);
					}
					collection.insert(person);
				}
				System.out.println("Done!!!");
			} else {
				System.out.println("File was empty!!!");
			}
			csvReader.close();

		} catch (UnknownHostException e) {
			System.out.println("Invalid MongoDB URI. Please input a correct URI. Exiting program");
			return false;
		} catch (FileNotFoundException e) {
			System.out.println("File doesnot exist. Please input a correct File. Program Exiting... \n");
			return false;
		} catch (IOException e) {
			System.out.println("Error Reading CSV File. Program Exiting... \n");
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String DBurl;
		File file;
		boolean result;

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

		result = dumpCSVData(file, DBurl);

		if (result) {
			System.out.println("Entry to database was successful\n");
		} else {
			System.out.println("Failed to add entry to database\n");
		}
	}
}
