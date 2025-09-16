import java.util.Scanner;

import view.LibraryView;

public class Main{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			String input_line = scanner.nextLine();
			if (input_line.equals("exit")) {
				break;
			}
			LibraryView.run(input_line.trim());
		}

		scanner.close();
	}
}
