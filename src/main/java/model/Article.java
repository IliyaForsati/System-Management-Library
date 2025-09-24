package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Article extends Publication {
    public static final ArrayList<Article> allArticles = new ArrayList<>();

    public <T extends PublicationBuilder<T>> Article(PublicationBuilder<T> pb) {
        super(pb);
    }

    @Override
    public boolean add() {
        // todo: check first
        allArticles.add(this);
        return allPublications.add(this);
    }

    @Override
    public boolean remove() {
        // todo: check first
          allArticles.remove(this);

        return allPublications.remove(this);
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
