import model.Book;
import model.Result;
import model.Status;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Year;
import java.util.Objects;
import model.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.List;

class LibraryTests {
    private final Library library = new Library();

    @BeforeEach
    void deleteAll() {
        List<Book> bookList = library.getBooks();

        for (int i = 0; i < bookList.size(); i++) {
            library.removeBook(bookList.get(i).getTitle());
        }

        library.saveChanges();
    }

    @ParameterizedTest
    @CsvSource({
            "b1, a1, 2021, EXIST",
            "b2, a2, 2022, EXIST"
    })
    void addBookTest(String b, String author, int year, String status) {
        Result addResult = library.addBook(b, author, Year.of(year), Status.valueOf(status));

        List<Book> bookList = library.getBooks();

        if (bookList.size() > 0 && bookList.get(bookList.size() - 1).equals(
                new Book(b, author, Year.of(year), Status.valueOf(status))))
            assertTrue(addResult.isSuccessful());
    }

    @ParameterizedTest
    @CsvSource({
            "b1, a1, 2021, EXIST",
            "b2, a2, 2022, EXIST"
    })
    void deleteBookTest(String title, String author, int year, String status) {
        addBookTest(title, author, year, status);
        Result deleteResult = library.removeBook(title);

        List<Book> bookList = library.getBooks();

        if (bookList.size() > 0 && !bookList.get(bookList.size() - 1).equals(
                new Book(title, author, Year.of(year), Status.valueOf(status))))
            assertTrue(deleteResult.isSuccessful());
    }

    @ParameterizedTest
    @CsvSource({
            "b1, a1, 2021, EXIST",
            "b2, a2, 2022, EXIST"
    })
    void addBookWithSaveTest(String title, String author, int year, String status) {
        addBookTest(title, author, year, status);
        Result saveResult = library.saveChanges();

        URL url = getClass().getClassLoader().getResource("test.txt");

        String lastLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader(new File(Objects.requireNonNull(url).toURI())))) {
            String line;
            while ((line = br.readLine()) != null)
                lastLine = line;
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        List<Book> bookList = library.getBooks();
        Book lastBook = bookList.get(bookList.size() - 1);

        String[] args = lastLine.split(",");
        Book lastInFile = new Book(args[0], args[1], Year.parse(args[2]), Status.valueOf(args[3]));

        if (lastBook.equals(lastInFile))
            assertTrue(saveResult.isSuccessful());
    }
}
