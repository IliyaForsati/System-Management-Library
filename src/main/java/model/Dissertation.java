package model;

import java.util.ArrayList;

public class Dissertation extends Publication {
    public static final ArrayList<Dissertation> allDissertations = new ArrayList<>();

    public <T extends PublicationBuilder<T>> Dissertation(PublicationBuilder<T> pb) {
        super(pb);
    }

    @Override
    public boolean add(Publication entity) {
        // todo: check first
        if (entity instanceof Dissertation)
            allDissertations.add((Dissertation) entity);
        else
            throw new IllegalArgumentException("entity must be Article");

        return allPublications.add(entity);
    }

    @Override
    public boolean remove(Publication entity) {
        // todo: check first
        if (entity instanceof Dissertation)
            allDissertations.remove((Dissertation) entity);
        else
            throw new IllegalArgumentException("entity must be Article");

        return allPublications.remove(entity);
    }

    public static class DissertationBuilder extends PublicationBuilder<DissertationBuilder> {

        @Override
        protected DissertationBuilder self() {
            return this;
        }

        @Override
        public Publication build() {
            return new Dissertation(this);
        }
    }
}
