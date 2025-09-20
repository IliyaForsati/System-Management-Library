import model.Result;
import model.Status;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Year;

import model.Library;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class LibraryTests {
    private final Library library = new Library();

    @ParameterizedTest
    @CsvSource({
            "b1, a1, 2021, EXIST",
            "b2, a2, 2022, EXIST"
    })
    @Order(1)
    void addBook(String b, String a, int year, String status) {
        Result addResult = library.addBook(b, a, Year.of(year), Status.valueOf(status));
        Result saveResult = library.saveChanges();

        assertTrue(saveResult.isSuccessful() && addResult.isSuccessful());
    }

    @ParameterizedTest
    @ValueSource(strings = {"b1", "b2"})
    @Order(2)
    void deleteBook(String title) {
        Result deleteResult = library.removeBook(title);
        Result saveResult = library.saveChanges();

        assertTrue(deleteResult.isSuccessful() && saveResult.isSuccessful());
    }
}
