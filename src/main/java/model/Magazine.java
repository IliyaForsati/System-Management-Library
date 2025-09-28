package model;

public class Magazine extends Publication {
    public <T extends PublicationBuilder<T>> Magazine(PublicationBuilder<T> pb) {
        super(pb);
    }

    public static class MagazineBuilder extends PublicationBuilder<MagazineBuilder> {

        @Override
        protected MagazineBuilder self() {
            return this;
        }

        @Override
        public Magazine build() {
            return new Magazine(this);
        }
    }
}
