package model;

import java.util.ArrayList;

public class Dissertation extends Publication {
    public static final ArrayList<Dissertation> allDissertations = new ArrayList<>();

    public <T extends PublicationBuilder<T>> Dissertation(PublicationBuilder<T> pb) {
        super(pb);
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
