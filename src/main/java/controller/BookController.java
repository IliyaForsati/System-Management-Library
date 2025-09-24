package controller;

import controller.interfaces.IBookController;
import controller.interfaces.IController;
import model.Book;
import model.ResultDTO;

class BookController extends PublicationController<Book> implements IBookController, IController {
    @Override
    public ResultDTO run(String[] args) {
        return null;
    }

    private static BookController instance = null;
    private BookController() {}
    public static BookController getInstance() {
        if (instance != null) return instance;

        instance = new BookController();
        return  instance;
    }
}
