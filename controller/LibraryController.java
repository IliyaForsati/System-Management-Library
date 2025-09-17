package controller;

import java.time.Year;

import model.Book;
import model.Library;
import model.Result;
import model.Status;
import model.SortType;

public class LibraryController {
    private static Library library = new Library();

    public static Result run(String input_line) {
        String[] input_line_parts = input_line.split(" ");

        String command = input_line_parts[0];
        if (command.equals("add")) {
            String title = input_line_parts[1];
            String author = input_line_parts[2];
            Year year = Year.parse(input_line_parts[3]);
            Status status = Status.valueOf(input_line_parts[4]);
            return library.addBook(title, author, year, status);
        }
        else if (command.equals("print")) {
            if (input_line_parts.length > 1) {
                return library.printBooks(SortType.valueOf(input_line_parts[1]));
            }

            return library.printBooks();
        }
        else if (command.equals("search")) {
            String keyWord = input_line_parts[1];

            if (input_line_parts.length > 2) {
                return library.searchBook(keyWord, SortType.valueOf(input_line_parts[2]));
            }

            return library.searchBook(keyWord);
        }
        else if (command.equals("update")) {
            String distTitle = input_line_parts[1];
            String title = input_line_parts[2];
            String author = input_line_parts[3];
            Year year = Year.parse(input_line_parts[4]);
            Status status = Status.valueOf(input_line_parts[5]);
            Book newBook = new Book(title, author, year, status);
            return library.updateBook(distTitle, newBook);
        }
        else if (command.equals("remove")) {
            String title = input_line_parts[1];
            return library.removeBook(title);
        }
        else {
            return new Result("invalid command", false);
        }
    }
}