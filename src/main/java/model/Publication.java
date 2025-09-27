package model;

import model.enums.SortType;
import model.enums.Status;
import model.enums.Type;
import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;

public abstract class Publication {
    protected int id;
    protected String title;
    protected String author;
    protected Year publicationYear;
    protected Type type;
    protected Status status;
    public static final ArrayList<Publication> allPublications = new ArrayList<>();

    // <editor-fold desc="constructors">
    public <T extends PublicationBuilder<T>> Publication(PublicationBuilder<T> pb) {
        this.title = pb.title;
        this.author = pb.author;
        this.publicationYear = pb.publicationYear;
        this.type = pb.type;
        this.status = pb.status;
    }
    // </editor-fold>

    // <editor-fold desc="builder">
    public abstract static class PublicationBuilder<T extends PublicationBuilder<T>> {
        protected String title;
        protected String author;
        protected Year publicationYear;
        protected Type type;
        protected Status status;

        // <editor-fold desc="builder setters">
        public T title(String title) {
            this.title = title;
            return self();
        }

        public T author(String author) {
            this.author = author;
            return self();
        }

        public T publicationYear(Year publicationYear) {
            this.publicationYear = publicationYear;
            return self();
        }

        public T type(Type type) {
            this.type = type;
            return self();
        }

        public T status(Status status) {
            this.status = status;
            return self();
        }
        // </editor-fold>

        protected abstract T self();
        public abstract Publication build();
    }
    // </editor-fold>

    // <editor-fold desc="getters and setters">
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Year publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    // </editor-fold>

    // <editor-fold desc="logical methods">
    public boolean add() {
        return allPublications.add(this);
    }
    public boolean remove() {
        return allPublications.remove(this);
    }
    public String createDisplayResult() {
        return String.format(
                " type: %s%n title: %s%n author: %s%n publication year: %s%n status: %s%n",
                type, title, author, publicationYear, status
        );
    }
    public void update(Publication entity) {
        this.type = entity.type;
        this.status = entity.status;
        this.title = entity.title;
        this.author = entity.author;
        this.publicationYear = entity.publicationYear;
    }
    public static ArrayList<Publication> sort(ArrayList<Publication> src, SortType st) {
        ArrayList<Publication> copy = new ArrayList<>(src);
        if (st.equals(SortType.UPWARD)) {
            copy.sort(Comparator.comparingInt(a -> a.publicationYear.getValue()));
        } else {
            copy.sort(Comparator.comparingInt(a -> -1 * a.publicationYear.getValue()));
        }
        return copy;
    }
    public static Publication findByTitle(String title) {
        for (Publication entity : allPublications) {
            if (entity.title.equals(title))
                return entity;
        }
        return null;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return  "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", type=" + type +
                ", status=" + status;
    }
}
