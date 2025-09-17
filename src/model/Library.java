package model;

import java.time.Year;
import java.lang.Exception;
import utils.List;

public class Library {
    private List<Book> books = new List<>();

    // constructors
    public Library() {

    }

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

            return new Result(books.toString(), true);
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

            return new Result(filteredBooks.toString(), true);
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
    public Result updateBook(String distTitleOrId, Book src) {
        try {
            List<Book> foundBooks = findBookByTitle(distTitleOrId);

            if (foundBooks.size() > 1) {
                Result result = searchBook(distTitleOrId);
                result.setMessage(result.getMessage() + "\n please use id for this work.");

                return result;
            }

            Book dist = foundBooks.get(0);
            if (dist == null) {
                return new Result("book: " + distTitleOrId + " not found.", false);
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
    public Result removeBook(String titleOrId) {
        try {
            List<Book> foundBooks = findBookByTitle(titleOrId);

            if (foundBooks.size() > 1) {
                Result result = searchBook(titleOrId);
                result.setMessage(result.getMessage() + "\n please use id for this work.");

                return result;
            }

            Book book = foundBooks.get(0);

            if (book == null) {
                return new Result("book: " + titleOrId + " not found.", false);
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

    private List<Book> findBookByTitle(String titleOrId) {
        List<Book> foundBooks = new List<>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(titleOrId) ||
                String.valueOf(books.get(i).getUniqueId()).equals(titleOrId)) {
                foundBooks.append(books.get(i));
            }
        }
        if (foundBooks.size() == 0)
            return null;
        return foundBooks;
    }
}
