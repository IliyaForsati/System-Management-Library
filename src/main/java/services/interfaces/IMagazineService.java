package services.interfaces;

import model.Magazine;
import model.ResultDTO;

public interface IMagazineService extends IPublicationService<Magazine> {
    ResultDTO add(String title, String author, String year, String status);
    ResultDTO update(String dist_id, String title, String author, String year, String status);
    ResultDTO remove(String id);
    ResultDTO display(String id);
}
