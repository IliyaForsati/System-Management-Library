package services;

import model.Article;
import services.interfaces.IArticleService;

public class ArticleService
        extends PublicationService<Article>
        implements IArticleService
{
    private static ArticleService instance = null;
    private ArticleService() {}
    public static ArticleService getInstance() {
        if (instance != null) return instance;

        instance = new ArticleService();
        return  instance;
    }


}
