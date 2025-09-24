package model;

import java.util.ArrayList;

public class Article extends Publication {
    public static final ArrayList<Article> allArticles = new ArrayList<>();

    public <T extends PublicationBuilder<T>> Article(PublicationBuilder<T> pb) {
        super(pb);
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
