package services;

import model.Article;
import model.Book;
import model.Publication;
import model.ResultDTO;
import model.enums.Status;
import model.enums.Type;
import services.interfaces.IArticleService;

import java.time.Year;

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

    public ResultDTO add(String title, String author, String year, String status) {
        Article.ArticleBuilder builder = new Article.ArticleBuilder();
        Article article = builder.title(title)
                .author(author)
                .type(Type.ARTICLE)
                .publicationYear(Year.parse(year))
                .status(Status.valueOf(status))
                .build();
        return add(article);
    }
    public ResultDTO update(String dist_id, String title, String author, String year, String status) {
        Publication dist_parent = Publication.findById(Integer.parseInt(dist_id));
        Article dist = null;
        if (dist_parent instanceof Article)
            dist = (Article) dist_parent;

        if (dist == null) {
            return new ResultDTO("dist not found!", false);
        }

        Article.ArticleBuilder builder = new Article.ArticleBuilder();
        Article article = builder.title(title)
                .author(author)
                .type(Type.BOOK)
                .publicationYear(Year.parse(year))
                .status(Status.valueOf(status))
                .build();

        return update(dist, article);
    }
    public ResultDTO remove(String id) {
        Publication dist_parent = Publication.findById(Integer.parseInt(id));
        Article dist = null;
        if (dist_parent instanceof Article)
            dist = (Article) dist_parent;

        if (dist == null) {
            return new ResultDTO("dist not found!", false);
        }

        return remove(dist);
    }
    public ResultDTO display(String id) {
        Publication dist_parent = Publication.findById(Integer.parseInt(id));
        Article dist = null;
        if (dist_parent instanceof Article)
            dist = (Article) dist_parent;

        if (dist == null) {
            return new ResultDTO("dist not found!", false);
        }

        return display(dist);
    }
}
