package controller;

import java.time.Year;

import model.Book;
import model.Library;
import model.Result;
import model.Status;
import model.SortType;

public class LibraryController {
    private static Library library = new Library();

    public static Result run(String inputLine) {
        try {
            String[] inputLineParts = inputLine.split(" ");

            String command = inputLineParts[0];
            if (command.equals("add")) {
                String title = inputLineParts[1];
                String author = inputLineParts[2];
                Year year = Year.parse(inputLineParts[3]);
                Status status = Status.valueOf(inputLineParts[4]);
                return library.addBook(title, author, year, status);
            }
            else if (command.equals("print")) {
                if (inputLineParts.length > 1) {
                    return library.printBooks(SortType.valueOf(inputLineParts[1]));
                }

                return library.printBooks();
            }
            else if (command.equals("search")) {
                String keyWord = inputLineParts[1];

                if (inputLineParts.length > 2) {
                    return library.searchBook(keyWord, SortType.valueOf(inputLineParts[2]));
                }

                return library.searchBook(keyWord);
            }
            else if (command.equals("update")) {
                String distTitle = inputLineParts[1];
                String title = inputLineParts[2];
                String author = inputLineParts[3];
                Year year = Year.parse(inputLineParts[4]);
                Status status = Status.valueOf(inputLineParts[5]);
                Book newBook = new Book(title, author, year, status);
                return library.updateBook(distTitle, newBook);
            }
            else if (command.equals("remove")) {
                String title = inputLineParts[1];
                return library.removeBook(title);
            }
            else {
                return new Result("invalid command", false);
            }
        } catch (Exception e) {
            return new Result("invalid command!", false);
        }
    }
}