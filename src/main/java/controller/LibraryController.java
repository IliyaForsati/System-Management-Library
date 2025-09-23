package controller;

import java.time.Year;
import model.Book;
import model.Library;
import model.ResultDTO;
import model.Status;
import model.SortType;

public class LibraryController {
    private static Library library = new Library();

    public static ResultDTO run(String[] inputLine) {
        try {
            String command = inputLine[0];
            if (command.equals("add")) {
                String title = inputLine[1];
                String author = inputLine[2];
                Year year = Year.parse(inputLine[3]);
                Status status = Status.valueOf(inputLine[4]);
                return library.addBook(title, author, year, status);
            }
            else if (command.equals("print")) {
                if (inputLine.length > 1) {
                    return library.printBooks(SortType.valueOf(inputLine[1]));
                }

                return library.printBooks();
            }
            else if (command.equals("search")) {
                String keyWord = inputLine[1];

                if (inputLine.length > 2) {
                    return library.searchBook(keyWord, SortType.valueOf(inputLine[2]));
                }

                return library.searchBook(keyWord);
            }
            else if (command.equals("update")) {
                String distTitle = inputLine[1];
                String title = inputLine[2];
                String author = inputLine[3];
                Year year = Year.parse(inputLine[4]);
                Status status = Status.valueOf(inputLine[5]);
                Book newBook = new Book(title, author, year, status);
                return library.updateBook(distTitle, newBook);
            }
            else if (command.equals("remove")) {
                String title = inputLine[1];
                return library.removeBook(title);
            }
            else if (command.equals("save")) {
                return library.saveChanges();
            }
            else {
                return new ResultDTO("invalid command", false);
            }
        } catch (Exception e) {
            return new ResultDTO("invalid command!", false);
        }
    }
}