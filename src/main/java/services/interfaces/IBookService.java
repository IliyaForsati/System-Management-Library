package services.interfaces;

import model.Book;
import model.ResultDTO;

public interface IBookService extends IPublicationService<Book> {
    ResultDTO add(String title, String author, String year, String status);
    ResultDTO update(String dist_id, String title, String author, String year, String status);
    ResultDTO remove(String id);
    ResultDTO display(String id);
}
