package model;

public class Book extends Publication {
    public <T extends PublicationBuilder<T>> Book(PublicationBuilder<T> pb) {
        super(pb);
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
