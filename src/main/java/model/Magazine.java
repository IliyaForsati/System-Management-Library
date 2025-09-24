package model;

import java.util.ArrayList;

public class Magazine extends Publication {
    public static final ArrayList<Magazine> allMagazines = new ArrayList<>();

    public <T extends PublicationBuilder<T>> Magazine(PublicationBuilder<T> pb) {
        super(pb);
    }

    @Override
    public boolean add() {
        // todo: check first
        allMagazines.add(this);
        return allPublications.add(this);
    }

    @Override
    public boolean remove() {
        // todo: check first
        allMagazines.remove(this);
        return allPublications.remove(this);
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
