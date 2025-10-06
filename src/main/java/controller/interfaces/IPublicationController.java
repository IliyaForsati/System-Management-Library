package controller.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Publication;
import model.enums.SortType;
import settings.interfaces.IController;

import java.util.ArrayList;

public interface IPublicationController<T extends Publication> extends IController {
    String add(String json) throws JsonProcessingException;
    String getById(int id) throws JsonProcessingException;
    String getAll(String json) throws JsonProcessingException;
    String search(String key, String stStr) throws JsonProcessingException;
    String update(int id, String bodyJSON) throws JsonProcessingException;
    String remove(int id);
}
