package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.enums.Status;
import model.enums.Type;
import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Book.class, name = "Book"),
        @JsonSubTypes.Type(value = Article.class, name = "Article"),
        @JsonSubTypes.Type(value = Dissertation.class, name = "Dissertation"),
        @JsonSubTypes.Type(value = Magazine.class, name = "Magazine") }
)
public abstract class Publication {
    protected int id = new Random().nextInt(1000, 9999);
    protected String title;
    protected String author;
    protected int publicationYear;
    protected Type type;
    protected Status status;
    protected BorrowHistory borrowHistory;

    // <editor-fold desc="getters">
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public Type getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public BorrowHistory getBorrowHistory() {
        return borrowHistory;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setBorrowHistory(BorrowHistory borrowHistory) {
        this.borrowHistory = borrowHistory;
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
