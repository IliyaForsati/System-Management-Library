package model;

import java.util.ArrayList;

public class Book extends Publication {
    public static final ArrayList<Book> allBooks = new ArrayList<>();

    public <T extends PublicationBuilder<T>> Book(PublicationBuilder<T> pb) {
        super(pb);
    }

    @Override
    public boolean add(Publication entity) {
        // todo: check first
        if (entity instanceof Book)
            allBooks.add((Book) entity);
        else
            throw new IllegalArgumentException("entity must be Article");

        return allPublications.add(entity);
    }

    @Override
    public boolean remove(Publication entity) {
        // todo: check first
        if (entity instanceof Book)
            allBooks.remove((Book) entity);
        else
            throw new IllegalArgumentException("entity must be Article");

        return allPublications.remove(entity);
    }

    public static class BookBuilder extends PublicationBuilder<BookBuilder> {
        @Override
        protected BookBuilder self() {
            return this;
        }

        @Override
        public Book build() {
            return new Book(this);
        }
    }
}
