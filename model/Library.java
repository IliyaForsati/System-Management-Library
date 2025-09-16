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
            message.append(books[i]);
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
        return null;
    }
    public Result updateBook(String distTitle, Book src) {
        return null;
    }

    // delete
    public Result removeBook(Book book) {
        return null;
    }
    public Result removeBook(String title) {
        return null;
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
}
