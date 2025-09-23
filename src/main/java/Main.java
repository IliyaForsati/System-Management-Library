import java.util.Scanner;
import view.LibraryView;

public class Main{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			String inputLine = scanner.nextLine();
			if (inputLine.equals("exit")) {
				break;
			}
			LibraryView.run(inputLine.trim());
		}

		scanner.close();
	}
}
