package services.interfaces;

import model.Publication;
import model.enums.SortType;

import java.util.ArrayList;

public interface IPublicationService<T extends Publication> {
    boolean add(T entity);
    T getById(int id);
    ArrayList<T> getAll(SortType st);
    ArrayList<T> search(String key, SortType st);
    boolean update(int distId, T src);
    boolean remove(int entityId);
}
