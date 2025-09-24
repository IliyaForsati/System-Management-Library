package controller;

import model.ResultDTO;

import java.util.Arrays;

public class MainController {
    public static ResultDTO run(String[] inputLine) {
        String command = inputLine[0];
        String[] args = Arrays.copyOfRange(inputLine, 1, inputLine.length);

        switch (command) {
            case "book" -> {
                BookController bc = BookController.getInstance();
                return bc.run(args);
            }
            case "magazine" -> {
                MagazineController mc = MagazineController.getInstance();
                return mc.run(args);
            }
            case "dissertation" -> {
                DissertationController dc = DissertationController.getInstance();
                return dc.run(args);
            }
            case "article" -> {
                ArticleController ac = ArticleController.getInstance();
                return ac.run(args);
            }
            default -> {
                return new ResultDTO("we have no " + command + " in this library", false);
            }
        }
    }
}
