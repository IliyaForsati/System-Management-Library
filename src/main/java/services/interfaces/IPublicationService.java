package services.interfaces;

import model.Publication;
import model.ResultDTO;
import model.enums.SortType;

public interface IPublicationService<T extends Publication> {
    ResultDTO display(int entityId);
    ResultDTO add(T entity);
    ResultDTO update(int distId, T src);
    ResultDTO remove(int entityId);
    ResultDTO print(SortType st);
    ResultDTO search(String key, SortType st);
}
