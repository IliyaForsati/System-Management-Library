package model;

import model.enums.Status;
import model.enums.Type;
import java.util.Random;

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
