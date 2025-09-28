package controller;

import services.interfaces.*;
import model.ResultDTO;

import java.util.Arrays;

public class MainController {
    public static ResultDTO run(String[] inputLine) {
        String command = inputLine[0];
        String[] args = Arrays.copyOfRange(inputLine, 1, inputLine.length);

        switch (command) {
            case "user" -> {
                return UserController.run(args);
            }
            case "book" -> {
                return BookController.run(args);
            }
            case "magazine" -> {
                return MagazineController.run(args);
            }
            case "dissertation" -> {
                return DissertationController.run(args);
            }
            case "article" -> {
                return ArticleController.run(args);
            }
            default -> {
                return new ResultDTO("we have no " + command + " in this library", false);
            }
        }
    }
}
