package model;

import java.util.ArrayList;

public class Book extends Publication {
    public static final ArrayList<Book> allBooks = new ArrayList<>();

    public <T extends PublicationBuilder<T>> Book(PublicationBuilder<T> pb) {
        super(pb);
    }

    @Override
    public boolean add() {
        // todo: check first
        allBooks.add(this);
        return allPublications.add(this);
    }

    @Override
    public boolean remove() {
        // todo: check first
        allBooks.remove(this);
        return allPublications.remove(this);
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
