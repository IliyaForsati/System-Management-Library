package controller;

import controller.interfaces.*;
import model.enums.Type;
import settings.serviceProvider.ServiceProvider;

public class MainController {
    private final IBookController bookController =
            ServiceProvider.mainScope.getService(IBookController.class);

    private final IArticleController articleController =
            ServiceProvider.mainScope.getService(IArticleController.class);

    private final IMagazineController magazineController =
            ServiceProvider.mainScope.getService(IMagazineController.class);

    private final IDissertationController dissertationController =
            ServiceProvider.mainScope.getService(IDissertationController.class);

    private final IUserController userController =
            ServiceProvider.mainScope.getService(IUserController.class);

    private final IBorrowController borrowController =
            ServiceProvider.mainScope.getService(IBorrowController.class);

    public String run(String input) {
        if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
            return "goodbye";
        }

        if (input.equalsIgnoreCase("help")) {
            return printHelp();
        }

        try {
            return processCommand(input);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    private String processCommand(String input) {
        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String params = parts.length > 1 ? parts[1] : "";

        return switch (command) {
            case "book" -> processBookCommand(params);
            case "article" -> processArticleCommand(params);
            case "magazine" -> processMagazineCommand(params);
            case "dissertation" -> processDissertationCommand(params);
            case "user" -> processUserCommand(params);
            case "borrow" -> processBorrowCommand(params);
            default -> "Unknown command. Type 'help' for available commands.";
        };
    }
    
    private String processBookCommand(String params) {
        return processPublicationCommand(bookController, params, "BOOK");
    }
    
    private String processArticleCommand(String params) {
        return processPublicationCommand(articleController, params, "ARTICLE");
    }
    
    private String processMagazineCommand(String params) {
        return processPublicationCommand(magazineController, params, "MAGAZINE");
    }
    
    private String processDissertationCommand(String params) {
        return processPublicationCommand(dissertationController, params, "DISSERTATION");
    }
    
    private String processPublicationCommand(IPublicationController<?> controller, String params, String type) {
        String[] parts = params.split("\\s+", 2);
        String action = parts[0];
        String data = parts.length > 1 ? parts[1] : "";
        
        try {
            return switch (action) {
                case "add" -> {
                    String[] addParts = data.split("\\s+", 4);
                    if (addParts.length < 4) {
                        yield "Usage: " + type + " add <title> <author> <year> <type> <status>";
                    }
                    yield controller.add(
                        addParts[0], 
                        addParts[1], 
                        Integer.parseInt(addParts[2]),
                        type,
                        addParts[3]
                    );
                }
                case "get" -> {
                    if (data.isEmpty()) {
                        yield "Usage: " + type + " get <id>";
                    }
                    yield controller.getById(Integer.parseInt(data));
                }
                case "getAll" -> controller.getAll("");
                case "search" -> {
                    if (data.isEmpty()) {
                        yield "Usage: " + type + " search <query>";
                    }
                    yield controller.search(data, "");
                }
                case "update" -> {
                    String[] updateParts = data.split("\\s+", 6);
                    if (updateParts.length < 6) {
                        yield "Usage: " + type + " update <id> <title> <author> <year> <type> <status>";
                    }
                    yield controller.update(
                        Integer.parseInt(updateParts[0]),
                        updateParts[1],
                        updateParts[2],
                        Integer.parseInt(updateParts[3]),
                        updateParts[4],
                        updateParts[5]
                    );
                }
                case "remove" -> {
                    if (data.isEmpty()) {
                        yield "Usage: " + type + " remove <id>";
                    }
                    yield controller.remove(Integer.parseInt(data));
                }
                default -> "Unknown " + type + " command. Available: add, get, list, search, update, remove";
            };
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    private String processUserCommand(String params) {
        String[] parts = params.split("\\s+", 2);
        String action = parts[0].toLowerCase();
        String data = parts.length > 1 ? parts[1] : "";
        
        try {
            return switch (action) {
                case "register" -> {
                    String[] creds = data.split("\\s+", 2);
                    if (creds.length < 2) {
                        yield "Usage: user register <username> <password>";
                    }
                    yield userController.register(creds[0], creds[1]);
                }
                case "login" -> {
                    String[] creds = data.split("\\s+", 2);
                    if (creds.length < 2) {
                        yield "Usage: user login <username> <password>";
                    }
                    yield userController.login(creds[0], creds[1]);
                }
                case "logout" -> userController.logout();
                default -> "Unknown user command. Available: register, login, logout";
            };
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    private String processBorrowCommand(String params) {
        String[] parts = params.split("\\s+", 2);
        String action = parts[0].toLowerCase();
        String data = parts.length > 1 ? parts[1] : "";
        
        try {
            if (action.equals("history"))
                return borrowController.showHistory();

            if (data.isEmpty()) {
                return "Usage: borrow <borrow|return> <publicationId>";
            }
            int publicationId = Integer.parseInt(data);
            return switch (action) {
                case "borrow" -> borrowController.borrow(publicationId);
                case "return" -> borrowController.return_(publicationId);
                default -> "Unknown borrow command. Available: borrow, return, history";
            };
        } catch (NumberFormatException e) {
            return "Error: Publication ID must be a number";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    private String printHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nAvailable commands:\n");
        sb.append("  book <command> [params]    - Manage books (add, get, list, search)\n");
        sb.append("  article <command> [params] - Manage articles\n");
        sb.append("  magazine <command> [params]- Manage magazines\n");
        sb.append("  dissertation <command>     - Manage dissertations\n");
        sb.append("  user <command> [params]    - Manage users\n");
        sb.append("  borrow <command> [params]  - Manage borrow/return operations\n");
        sb.append("  help                       - Show this help message\n");
        sb.append("  exit | quit                - Exit the application\n");
        return sb.toString();
    }
}
