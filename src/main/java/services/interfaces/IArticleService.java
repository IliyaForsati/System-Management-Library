package services.interfaces;

import model.Article;
import model.ResultDTO;

public interface IArticleService extends IPublicationService<Article> {
    ResultDTO add(String title, String author, String year, String status);
    ResultDTO update(String dist_id, String title, String author, String year, String status);
    ResultDTO remove(String id);
    ResultDTO display(String id);
}
