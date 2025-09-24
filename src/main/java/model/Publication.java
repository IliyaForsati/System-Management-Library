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
    public final ResultDTO display() {
        String result = createDisplayResult();
        return new ResultDTO(result, true);
    }
    public ResultDTO add() {

    }
    public ResultDTO update(Publication entity) {
        this.deepCopy(entity);
        return new ResultDTO("updated successfully", true);
    }

    protected String createDisplayResult() {
        return String.format(
                " type: %s%n title: %s%n author: %s%n publication year: %s%n status: %s%n",
                type, title, author, publicationYear, status
        );
    }
    protected void deepCopy(Publication entity) {
        this.type = entity.type;
        this.status = entity.status;
        this.title = entity.title;
        this.author = entity.author;
        this.publicationYear = entity.publicationYear;
    }
    // </editor-fold>
}
