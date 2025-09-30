package services;

import model.Book;
import services.interfaces.IBookService;

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


}
