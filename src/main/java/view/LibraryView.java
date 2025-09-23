package view;

import controller.LibraryController;
import model.Result;

public class LibraryView {
    public static void run(String[] input_line) {
        Result result = LibraryController.run(input_line);

        if (result.isSuccessful()) {
            System.out.println("Success: \n" + result.getMessage());
        } else {
            System.out.println("Error: \n" + result.getMessage());
        }
    }
}
