import java.time.Year;

public class Library {
    const static int MAX_NUMBER_OF_BOOKS = 500;
    private int NoOfBooks = 0;
    private Book[] books = new Book[MAX_NUMBER_OF_BOOKS];

    // create
    public Result addBook(Book book) {
        if (NoOfBooks >= 500) {
            return new Result("library if full.", false);
        }

        books[NoOfBooks++] = book;
        return new Result("book: " + book.getTitle() + " added successfully.", true)
    }
    public Result addBook(String title, String author, Year year, Status status) {
        addBook(new Book(title, author, year, status));
    }

    // read
    public Book[] getBooks(SortType type) {
        return books;
    }
    public Result printBooks(SortType type) {
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < NoOfBooks; i++) {
            message.append(books[i]);
            message.append("\n");
        }

        return new Result(message.toString(), true)
    }
    public Result searchBook(String keyWord, SortType type) {
        int NoOfFoundBooks = 0;
        Book[] filteredBooks = new Book[MAX_NUMBER_OF_BOOKS];

        for (int i = 0; i < NoOfBooks; i++) {
            if (books[i].getTitle().toLowerCase().contains(keyWord)) {
                filteredBooks[NoOfFoundBooks++] = books[i];
            }
        }

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < NoOfFoundBooks; i++) {
            message.append(filteredBooks[i]);
            message.append("\n");
        }

        return new Result(message.toString(), true)
    }

    // update
    public Result updateBook(Book dist, Book src) {

    }
    public Result updateBook(String distTitle, Book src) {

    }

    // delete
    public Result removeBook(Book book) {

    }
    public Result removeBook(String title) {

    }

    // private methods
    private Result sortArray(SortType type, int NoOfBooks) {

    }
}
