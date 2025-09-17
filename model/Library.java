package model;

import java.time.Year;
import java.lang.Exception;
import utils.List;

public class Library {
    private List<Book> books = new List<>();

    // create
    private Result addBook(Book book) {
        books.append(book);
        return new Result("book: " + book.getTitle() + " added successfully.", true);
    }
    public Result addBook(String title, String author, Year year, Status status) {
        try {
            return addBook(new Book(title, author, year, status));
        } catch (Exception e) {
            return new Result(e.getMessage(), false);
        }
    }

    // read
    public Result printBooks(SortType type) {
        try {
            if (type != null) {
                sortBooksArray(type, books);
            }

            StringBuilder message = new StringBuilder();

            for (int i = 0; i < books.size(); i++) {
                message.append("\nTitle: " + books.get(i).getTitle() + " Author: " + books.get(i).getAuthor()
                        + " Year: " + books.get(i).getYear().toString() + " Status: " + books.get(i).getStatus().name());

                message.append("\n");
            }

            return new Result(message.toString(), true);
        } catch (Exception e) {
            return new Result(e.getMessage(), false);
        }
    }
    public Result printBooks() {
        return printBooks(null);
    }
    public Result searchBook(String keyWord, SortType type) {
        try {
            List<Book> filteredBooks = new List<>();

            for (int i = 0; i < books.size(); i++)
                if (books.get(i).getTitle().toLowerCase().contains(keyWord.toLowerCase()) ||
                    books.get(i).getAuthor().toLowerCase().contains(keyWord.toLowerCase()))
                    filteredBooks.append(books.get(i));

            if (type != null)
                sortBooksArray(type, filteredBooks);


            StringBuilder message = new StringBuilder();
            for (int i = 0; i < filteredBooks.size(); i++) {
                message.append("\nTitle: " + filteredBooks.get(i).getTitle() + " Author: " + filteredBooks.get(i).getAuthor()
                        + " Year: " + filteredBooks.get(i).getYear().toString() + " Status: " + filteredBooks.get(i).getStatus().name());
                message.append("\n");
            }

            return new Result(message.toString(), true);
        } catch (Exception e) {
            return new Result(e.getMessage(), false);
        }
    }
    public Result searchBook(String keyWord) {
        return searchBook(keyWord, null);
    }

    // update
    private Result updateBook(Book dist, Book src) {
        deepCopyBook(dist, src);
        return new Result("book: " + dist.getTitle() + " updated successfully.", true);
    }
    public Result updateBook(String distTitle, Book src) {
        try {
            Book dist = findBookByTitle(distTitle);
            if (dist == null) {
                return new Result("book: " + distTitle + " not found.", false);
            }
            return updateBook(dist, src);
        } catch (Exception e) {
            return new Result(e.getMessage(), false);
        }
    }

    // delete
    private Result removeBook(Book book) {
        if (books.remove(book)) {
            return new Result("book: " + book.getTitle() + " removed successfully", true);
        }

        return new Result("book: " + book.getTitle() + " not found. But how!!!", false);
    }
    public Result removeBook(String title) {
        try {
            Book book = findBookByTitle(title);

            if (book == null) {
                return new Result("book: " + title + " not found.", false);
            }
            return removeBook(book);
        } catch (Exception e) {
            return new Result(e.getMessage(), false);
        }
    }

    // private methods
    private void sortBooksArray(SortType type, List<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            for (int j = i + 1; j < books.size(); j++) {
                if (type == SortType.DOWNWARD) {
                    if (books.get(i).getYear().compareTo(books.get(j).getYear()) < 0) {
                        Book temp = books.get(i);
                        books.set(i, books.get(j));
                        books.set(j, temp);
                    }
                }
                else if (type == SortType.UPWARD) {
                    if (books.get(i).getYear().compareTo(books.get(j).getYear()) > 0) {
                        Book temp = books.get(i);
                        books.set(i, books.get(j));
                        books.set(j, temp);
                    }
                }
            }
        }
    }

    private void deepCopyBook(Book dist, Book src) {
        dist.setTitle(src.getTitle());
        dist.setAuthor(src.getAuthor());
        dist.setYear(src.getYear());
        dist.setStatus(src.getStatus());
    }

    private Book findBookByTitle(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(title)) {
                return books.get(i);
            }
        }
        return null;
    }
}
