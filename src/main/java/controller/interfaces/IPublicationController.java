package controller.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Publication;
import model.enums.SortType;
import settings.interfaces.IController;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public interface IPublicationController<T extends Publication> extends IController {
    String add(String title, String author, int publicationYear, String type, String status)
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    String getById(int id);
    String getAll(String json);
    String search(String key, String stStr);
    String update(int id, String title, String author, int publicationYear, String type, String status)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    String remove(int id);
}
