package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import controller.interfaces.IBookController;
import model.Book;
import settings.annotations.Route;

public class BookController extends PublicationController<Book> implements IBookController {
    @Route(route = "/api/book/add", httpMethod = "POST")
    @Override
    public String add(String bodyJSON) throws JsonProcessingException {
        return super.add(bodyJSON);
    }

    @Route(route = "/api/book/get/{ book_id }", httpMethod = "GET")
    @Override
    public String getById(int id) throws JsonProcessingException {
        return super.getById(id);
    }

    @Route(route = "/api/book/getAll/{ sort_type? }", httpMethod = "GET")
    @Override
    public String getAll(String stStr) throws JsonProcessingException {
        return super.getAll(stStr);
    }

    @Route(route = "/api/book/search/{ key }{ sort_type }", httpMethod = "GET")
    @Override
    public String search(String key, String stStr) throws JsonProcessingException {
        return super.search(key, stStr);
    }

    @Route(route = "/api/book/update/{ book_id }", httpMethod = "PUT")
    @Override
    public String update(int id, String bodyJSON) throws JsonProcessingException {
        return super.update(id, bodyJSON);
    }

    @Route(route = "/api/book/delete/{ book_id }", httpMethod = "DELETE")
    @Override
    public String remove(int id) {
        return super.remove(id);
    }
}
