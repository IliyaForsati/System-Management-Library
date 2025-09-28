package services;

import model.Book;
import model.Publication;
import model.ResultDTO;
import model.enums.Status;
import model.enums.Type;
import services.interfaces.IBookService;

import java.time.Year;

public class BookService
        extends PublicationService<Book>
        implements IBookService
{
    private static BookService instance = null;
    private BookService() {}
    public static BookService getInstance() {
        if (instance != null) return instance;

        instance = new BookService();
        return instance;
    }

    public ResultDTO add(String title, String author, String year, String status) {
        Book.BookBuilder builder = new Book.BookBuilder();
        Book book = builder.title(title)
                .author(author)
                .type(Type.BOOK)
                .publicationYear(Year.parse(year))
                .status(Status.valueOf(status))
                .build();
        return add(book);
    }
    public ResultDTO update(String dist_id, String title, String author, String year, String status) {
        Publication dist_parent = Publication.findById(Integer.parseInt(dist_id));
        Book dist = null;
        if (dist_parent instanceof Book)
            dist = (Book) dist_parent;

        if (dist == null) {
            return new ResultDTO("dist not found!", false);
        }

        Book.BookBuilder builder = new Book.BookBuilder();
        Book book = builder.title(title)
                .author(author)
                .type(Type.BOOK)
                .publicationYear(Year.parse(year))
                .status(Status.valueOf(status))
                .build();

        return update(dist, book);
    }
    public ResultDTO remove(String id) {
        Publication dist_parent = Publication.findById(Integer.parseInt(id));
        Book dist = null;
        if (dist_parent instanceof Book)
            dist = (Book) dist_parent;

        if (dist == null) {
            return new ResultDTO("dist not found!", false);
        }

        return remove(dist);
    }
    public ResultDTO display(String id) {
        Publication dist_parent = Publication.findById(Integer.parseInt(id));
        Book dist = null;
        if (dist_parent instanceof Book)
            dist = (Book) dist_parent;

        if (dist == null) {
            return new ResultDTO("dist not found!", false);
        }

        return display(dist);
    }
}
