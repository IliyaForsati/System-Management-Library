package services.interfaces;

import model.Dissertation;
import model.ResultDTO;

public interface IDissertationService extends IPublicationService<Dissertation> {
    ResultDTO add(String title, String author, String year, String status);
    ResultDTO update(String dist_id, String title, String author, String year, String status);
    ResultDTO remove(String id);
    ResultDTO display(String id);
}
