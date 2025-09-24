package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Article extends Publication {
    public static final ArrayList<Article> allArticles = new ArrayList<>();

    public <T extends PublicationBuilder<T>> Article(PublicationBuilder<T> pb) {
        super(pb);
    }

    @Override
    public boolean add(Publication entity) {
        // todo: check first
        if (entity instanceof Article)
            allArticles.add((Article) entity);
        else
            throw new IllegalArgumentException("entity must be Article");

        return allPublications.add(entity);
    }

    @Override
    public boolean remove(Publication entity) {
        // todo: check first
        if (entity instanceof Article)
            allArticles.remove((Article) entity);
        else
            throw new IllegalArgumentException("entity must be Article");

        return allPublications.remove(entity);
    }

    public static class ArticleBuilder extends PublicationBuilder<ArticleBuilder> {

        @Override
        protected ArticleBuilder self() {
            return this;
        }

        @Override
        public Publication build() {
            return new Article(this);
        }
    }

}
