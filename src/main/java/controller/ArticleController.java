package controller;

import controller.interfaces.IArticleController;
import model.Article;
import model.ResultDTO;

class ArticleController
        extends PublicationController<Article>
        implements IArticleController
{
    @Override
    public ResultDTO run(String[] args) {
        return null;
    }

    private static ArticleController instance = null;
    private ArticleController() {}
    public static ArticleController getInstance() {
        if (instance != null) return instance;

        instance = new ArticleController();
        return  instance;
    }
}
