package model;

public class Dissertation extends Publication {
    public <T extends PublicationBuilder<T>> Dissertation(PublicationBuilder<T> pb) {
        super(pb);
    }

    public static class DissertationBuilder extends PublicationBuilder<DissertationBuilder> {

        @Override
        protected DissertationBuilder self() {
            return this;
        }

        @Override
        public Dissertation build() {
            return new Dissertation(this);
        }
    }
}
