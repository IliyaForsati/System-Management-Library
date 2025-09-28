package controller;

import model.ResultDTO;
import model.enums.SortType;
import services.BookService;

public class BookController {
    public static final BookService service = BookService.getInstance();
    public static ResultDTO run(String[] args) {
        try {
            String command = args[0];
            switch (command) {
                case "add" -> {
                    return service.add(args[1], args[2], args[3], args[4]);
                }
                case "display" -> {
                    return service.display(args[1]);
                }
                case "print" -> {
                    if (args.length > 1) {
                        return service.print(SortType.valueOf(args[1]));
                    }

                    return service.print(null);
                }
                case "search" -> {
                    String keyWord = args[1];

                    if (args.length > 2) {
                        return service.search(keyWord, SortType.valueOf(args[2]));
                    }

                    return service.search(keyWord, null);
                }
                case "update" -> {
                    return service.update(args[1], args[2], args[3], args[4], args[5]);
                }
                case "remove" -> {
                    return service.remove(args[1]);
                }
                default -> {
                    return new ResultDTO("invalid command", false);
                }
            }
        } catch (Exception e) {
            return new ResultDTO("invalid command!", false);
        }
    }
}
