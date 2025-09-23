package view;

import controller.MainController;
import model.ResultDTO;

public class LibraryView {
    public static void run(String[] inputLine) {
        ResultDTO result = MainController.run(inputLine);

        assert result != null;
        if (result.isSuccessful()) {
            System.out.println("Success: \n" + result.getMessage());
        } else {
            System.out.println("Error: \n" + result.getMessage());
        }
    }
}
