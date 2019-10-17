package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Filehandler {

	static boolean lesen() {
		Scanner scanner;

		try {
			scanner = new Scanner(new File("html/part1.txt"));
			SampleController.part1 = scanner.nextLine();

			scanner = new Scanner(new File("html/part2.txt"));
			SampleController.part2 = scanner.nextLine();

			scanner.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}
		return true;
	}

	static void schreiben(String part1, String schlagworte, String part2) {
		try {
			FileWriter filewriter = new FileWriter("html/suchanfrage.html");
			try {
				filewriter.write(part1 + schlagworte + part2);
			} catch (IOException e) {
				e.printStackTrace();
			}

			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
