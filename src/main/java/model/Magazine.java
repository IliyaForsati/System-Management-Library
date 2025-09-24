package model;

import java.util.ArrayList;

public class Magazine extends Publication {
    public static final ArrayList<Magazine> allMagazines = new ArrayList<>();

    public <T extends PublicationBuilder<T>> Magazine(PublicationBuilder<T> pb) {
        super(pb);
    }

    @Override
    public boolean add(Publication entity) {
        // todo: check first
        if (entity instanceof Magazine)
            allMagazines.add((Magazine) entity);
        else
            throw new IllegalArgumentException("entity must be Article");

        return allPublications.add(entity);
    }

    @Override
    public boolean remove(Publication entity) {
        // todo: check first
        if (entity instanceof Magazine)
            allMagazines.remove((Magazine) entity);
        else
            throw new IllegalArgumentException("entity must be Article");

        return allPublications.remove(entity);
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
