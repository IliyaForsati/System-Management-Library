package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.enums.Status;
import model.enums.Type;
import java.time.Year;
import java.util.Random;

public abstract class Publication {
    @JsonIgnore
    protected int id = new Random().nextInt(1000, 9999);
    protected String title;
    protected String author;
    protected Year publicationYear;
    protected Type type;
    protected Status status;

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
