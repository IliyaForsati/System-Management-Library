import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Year;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import util.List;

class LibraryTests {
    private final Library library = new Library();

    @BeforeEach
    void deleteAllWithSave() {
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
        library.addBook(b, author, Year.of(year), Status.valueOf(status));

        List<Book> bookList = library.getBooks();

        boolean isPass = bookList.size() > 0 && bookList.get(bookList.size() - 1).equals(
                new Book(b, author, Year.of(year), Status.valueOf(status)));
        assertTrue(isPass);
    }

    @ParameterizedTest
    @CsvSource({
            "b1, a1, 2021, EXIST",
            "b2, a2, 2022, EXIST"
    })
    void addBookWithSaveTest(String title, String author, int year, String status) {
        addBookTest(title, author, year, status);
        ResultDTO saveResult = library.saveChanges();

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

        assertEquals(lastBook, lastInFile);
    }

    @ParameterizedTest
    @CsvSource({
            "b1, a1, 2021, EXIST",
            "b2, a2, 2022, EXIST"
    })
    void deleteBookTest(String title, String author, int year, String status) {
        addBookTest(title, author, year, status);
        library.removeBook(title);

        List<Book> bookList = library.getBooks();

        boolean isPass = bookList.size() == 0 || !bookList.get(bookList.size() - 1).equals(
                new Book(title, author, Year.of(year), Status.valueOf(status)));
        assertTrue(isPass);
    }

    @ParameterizedTest
    @CsvSource({
            "b1, new_b1, a1, 2021, EXIST",
            "b2, new_b2, a2, 2022, EXIST"
    })
    void updateBookTest(String dist_title, String title, String author, int year, String status) {
        addBookTest(dist_title, author, year, status);

        List<Book> bookList = library.getBooks();
        Book lastBook = bookList.get(bookList.size() - 1);

        String titleBeforeChange = lastBook.getTitle();
        library.updateBook(dist_title, new Book(title, author, Year.of(year), Status.valueOf(status)));
        String titleAfterChange = lastBook.getTitle();

        boolean isPass = titleBeforeChange.equals(dist_title) && titleAfterChange.equals(title);
        assertTrue(isPass);
    }

    @ParameterizedTest
    @CsvSource({
            "b1, new_b1, a1, 2021, EXIST",
            "b2, new_b2, a2, 2022, EXIST"
    })
    void updateBookWithSaveTest(String dist_title, String title, String author, int year, String status) {
        updateBookTest(dist_title, title, author, year, status);
        library.saveChanges();

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

        assertEquals(lastBook, lastInFile);
    }


    @ParameterizedTest
    @ValueSource(booleans = {false, true}) // for having sort filter or not
    void printTest(boolean haveSortFilter) {
        String[][] books = {{"b1", "a1", "2021", "EXIST"}, {"b2", "a2", "2022", "EXIST"}};
        addBookTest(books[0][0], books[0][1], Integer.parseInt(books[0][2]), books[0][3]);
        addBookTest(books[1][0], books[1][1], Integer.parseInt(books[1][2]), books[1][3]);

        ResultDTO printResult;
        if (haveSortFilter)
            printResult = library.printBooks(SortType.DOWNWARD);
        else
            printResult = library.printBooks();

        String[] firstRow = haveSortFilter ? books[1] : books[0];
        String[] secondRow = haveSortFilter ? books[0] : books[1];

        String expectedValue = "1. id: " + (firstRow[0] + firstRow[1]).hashCode()
                + " title: " + firstRow[0] + " author: " + firstRow[1] + " year: "
                + firstRow[2] + " status: " + firstRow[3] + "\n"
                + "2. id: " + (secondRow[0] + secondRow[1]).hashCode()
                + " title: " + secondRow[0] + " author: " + secondRow[1] + " year: "
                + secondRow[2] + " status: " + secondRow[3] + "\n";


        assertEquals(expectedValue, printResult.getMessage());
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true}) // for having sort filter or not
    void searchTest(boolean haveSortFilter) {
        String[][] books = {{"b1", "a1", "2021", "EXIST"}, {"b2", "a1", "2022", "EXIST"}, {"b3", "a3", "2020", "EXIST"}};
        addBookTest(books[0][0], books[0][1], Integer.parseInt(books[0][2]), books[0][3]);
        addBookTest(books[1][0], books[1][1], Integer.parseInt(books[1][2]), books[1][3]);
        addBookTest(books[2][0], books[2][1], Integer.parseInt(books[2][2]), books[2][3]);

        ResultDTO searchResult;
        if (haveSortFilter)
            searchResult = library.searchBook(books[0][1], SortType.DOWNWARD);
        else
            searchResult = library.searchBook(books[0][1]);

        String[] firstRow = haveSortFilter ? books[1] : books[0];
        String[] secondRow = haveSortFilter ? books[0] : books[1];

        String expectedValue = "1. id: " + (firstRow[0] + firstRow[1]).hashCode()
                + " title: " + firstRow[0] + " author: " + firstRow[1] + " year: "
                + firstRow[2] + " status: " + firstRow[3] + "\n"
                + "2. id: " + (secondRow[0] + secondRow[1]).hashCode()
                + " title: " + secondRow[0] + " author: " + secondRow[1] + " year: "
                + secondRow[2] + " status: " + secondRow[3] + "\n";

        assertEquals(expectedValue, searchResult.getMessage());
    }
}
