import model.Result;
import model.Status;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Year;

import model.Library;

class LibraryTests {
    private final Library library = new Library();

    @Test
    void addBook() {
        Result addResult = library.addBook("b2", "a2", Year.parse("1991"), Status.BORROWED);
        Result saveResult = library.saveChanges();

        assertTrue(saveResult.isSuccessful() && addResult.isSuccessful());
    }
}
