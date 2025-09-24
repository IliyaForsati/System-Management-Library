package model;

import java.util.ArrayList;

public class Magazine extends Publication {
    public static final ArrayList<Magazine> allMagazines = new ArrayList<>();

    public <T extends PublicationBuilder<T>> Magazine(PublicationBuilder<T> pb) {
        super(pb);
    }

    public static class MagazineBuilder extends PublicationBuilder<MagazineBuilder> {

        @Override
        protected MagazineBuilder self() {
            return this;
        }

        @Override
        public Publication build() {
            return new Magazine(this);
        }
    }
}
