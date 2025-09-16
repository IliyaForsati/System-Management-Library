package model;

import java.time.Year;

public class Library {
    final static int MAX_NUMBER_OF_BOOKS = 500;
    private int NoOfBooks = 0;
    private Book[] books = new Book[MAX_NUMBER_OF_BOOKS];

    // create
    public Result addBook(Book book) {
        if (NoOfBooks >= 500) {
            return new Result("library if full.", false);
        }

        books[NoOfBooks++] = book;
        return new Result("book: " + book.getTitle() + " added successfully.", true);
    }
    public Result addBook(String title, String author, Year year, Status status) {
        return addBook(new Book(title, author, year, status));
    }

    // read
    public Book[] getBooks(SortType type) {
        if (type != null) {
            sortBooksArray(type, books, NoOfBooks);
        }
        
        return books;
    }
    public Book[] getBooks() {
        return getBooks(null);
    }
    public Result printBooks(SortType type) {
        if (type != null) {
            sortBooksArray(type, books, NoOfBooks);
        }

        StringBuilder message = new StringBuilder();

        for (int i = 0; i < NoOfBooks; i++) {
            message.append("Title: " + books[i].getTitle() + " Author: " + books[i].getAuthor() + 
            " Year: " + books[i].getYear().toString() + " Status: " + books[i].getStatus().name());

            message.append("\n");
        }

        return new Result(message.toString(), true);
    }
    public Result printBooks() {
        return printBooks(null);
    }
    public Result searchBook(String keyWord, SortType type) {
        int NoOfFoundBooks = 0;
        Book[] filteredBooks = new Book[MAX_NUMBER_OF_BOOKS];

        for (int i = 0; i < NoOfBooks; i++) {
            if (books[i].getTitle().toLowerCase().contains(keyWord)) {
                filteredBooks[NoOfFoundBooks++] = books[i];
            }
        }

        if (type != null) {
            sortBooksArray(type, filteredBooks, NoOfFoundBooks);
        }

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < NoOfFoundBooks; i++) {
            message.append(filteredBooks[i]);
            message.append("\n");
        }

        return new Result(message.toString(), true);
    }
    public Result searchBook(String keyWord) {
        return searchBook(keyWord, null);
    }

    // update
    public Result updateBook(Book dist, Book src) {
        deepCopyBook(dist, src);
        return new Result("book: " + dist.getTitle() + " updated successfully.", true);
    }
    public Result updateBook(String distTitle, Book src) {
        Book dist = findBookByTitle(distTitle);
        if (dist == null) {
            return new Result("book: " + distTitle + " not found.", false);
        }
        return updateBook(dist, src);
    }

    // delete
    public Result removeBook(Book book) {
        for (int i = 0; i < NoOfBooks; i++) {
            if (books[i] == book) {
                if (i + 1 < NoOfBooks) 
                    books[i] = books[i+1];
                else
                    books[i] = null;

                NoOfBooks--;
                return new Result("book: " + book.getTitle() + " removed successfully.", true);
            }
        }
        return new Result("book: " + book.getTitle() + " not found. But how!!!", false);
    }
    public Result removeBook(String title) {
        Book book = findBookByTitle(title);

        if (book == null) {
            return new Result("book: " + title + " not found.", false);
        }
        return removeBook(book);
    }

    // private methods
    private void sortBooksArray(SortType type, Book[] books, int NoOfBooks) {
        for (int i = 0; i < NoOfBooks; i++) {
            for (int j = i + 1; j < NoOfBooks; j++) {
                if (type == SortType.SORT_BY_YEAR_DOWNWARD) {
                    if (books[i].getYear().compareTo(books[j].getYear()) < 0) {
                        Book temp = books[i];
                        books[i] = books[j];
                        books[j] = temp;
                    }
                }
                else if (type == SortType.SORT_BY_YEAR_UPWARD) {
                    if (books[i].getYear().compareTo(books[j].getYear()) > 0) {
                        Book temp = books[i];
                        books[i] = books[j];
                        books[j] = temp;
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
        for (int i = 0; i < NoOfBooks; i++) {
            if (books[i].getTitle().equals(title)) {
                return books[i];
            }
        }
        return null;
    }
}
