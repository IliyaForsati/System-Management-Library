package services.interfaces;

import model.Publication;
import model.ResultDTO;
import model.enums.SortType;

public interface IPublicationService<T extends Publication> {
    ResultDTO display(T entity);
    ResultDTO add(T entity);
    ResultDTO update(T dist, T src);
    ResultDTO remove(T entity);
    ResultDTO print(SortType st);
    ResultDTO search(String key, SortType st);
}
