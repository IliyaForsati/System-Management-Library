package model;

import java.util.ArrayList;

public class Dissertation extends Publication {
    public static final ArrayList<Dissertation> allDissertations = new ArrayList<>();

    public <T extends PublicationBuilder<T>> Dissertation(PublicationBuilder<T> pb) {
        super(pb);
    }

    @Override
    public boolean add() {
        // todo: check first
        allDissertations.add(this);
        return allPublications.add(this);
    }

    @Override
    public boolean remove() {
        // todo: check first
        allDissertations.remove(this);
        return allPublications.remove(this);
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
