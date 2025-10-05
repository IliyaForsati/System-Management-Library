package controller.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Publication;
import model.enums.SortType;
import settings.interfaces.IController;

import java.util.ArrayList;

public interface IPublicationController<T extends Publication> extends IController {
    String add(String json);
    String getById(String json) throws JsonProcessingException;
    String getAll(String json) throws JsonProcessingException;
    String search(String json) throws JsonProcessingException;
    String update(String json);
    String remove(String json);
}
