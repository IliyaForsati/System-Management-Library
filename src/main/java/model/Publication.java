package model;

import model.enums.Status;
import model.enums.Type;

import java.time.Year;
import java.util.ArrayList;

public abstract class Publication {
    protected int id;
    protected String title;
    protected String author;
    protected Year publicationYear;
    protected Type type;
    protected Status status;
    public static final ArrayList<Publication> allPublications = new ArrayList<>();

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
}
