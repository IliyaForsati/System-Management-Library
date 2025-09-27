package controller;

import controller.interfaces.IBookController;
import model.Book;
import model.Publication;
import model.ResultDTO;
import model.enums.SortType;
import model.enums.Status;
import model.enums.Type;
import java.time.Year;

class BookController
        extends PublicationController<Book>
        implements IBookController
{
    @Override
    public ResultDTO run(String[] args) {
        try {
            String command = args[0];
            switch (command) {
                case "add" -> {
                    Book.BookBuilder builder = new Book.BookBuilder();
                    Book entity = builder.title(args[1])
                            .author(args[2])
                            .publicationYear(Year.parse(args[3]))
                            .status(Status.valueOf(args[4]))
                            .type(Type.BOOK)
                            .build();
                    return add(entity);
                }
                case "print" -> {
                    if (args.length > 1) {
                        return print(SortType.valueOf(args[1]));
                    }

                    return print(null);
                }
                case "search" -> {
                    String keyWord = args[1];

                    if (args.length > 2) {
                        return search(keyWord, SortType.valueOf(args[2]));
                    }

                    return search(keyWord, null);
                }
                case "update" -> {
                    String distTitle = args[1];
                    Publication entity = Publication.findByTitle(distTitle);
                    Book distBook;
                    if (entity instanceof Book) {
                        distBook = (Book) entity;
                    } else
                        return new ResultDTO("dist is not find!", false);

                    Book.BookBuilder builder = new Book.BookBuilder();
                    Book newBook = builder.title(args[2])
                            .author(args[3])
                            .publicationYear(Year.parse(args[4]))
                            .status(Status.valueOf(args[5]))
                            .type(Type.BOOK)
                            .build();
                    return update(distBook, newBook);
                }
                case "remove" -> {
                    String title = args[1];
                    Publication entity = Publication.findByTitle(title);
                    Book distBook;
                    if (entity instanceof Book) {
                        distBook = (Book) entity;
                    } else
                        return new ResultDTO("dist is not find!", false);

                    return remove(distBook);
                }
                default -> {
                    return new ResultDTO("invalid command", false);
                }
            }
        } catch (Exception e) {
            return new ResultDTO("invalid command!", false);
        }
    }

    private static BookController instance = null;
    private BookController() {}
    public static BookController getInstance() {
        if (instance != null) return instance;

        instance = new BookController();
        return instance;
    }
}
