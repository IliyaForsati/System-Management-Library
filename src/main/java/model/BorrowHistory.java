package model;

import java.time.LocalDate;
import java.util.Random;

public class BorrowHistory {
    private User user;
    private Publication publication;
    private LocalDate start;
    private LocalDate end;

    public BorrowHistory(User user, Publication publication) {
        this.user = user;
        this.publication = publication;
        this.start = LocalDate.now();
        this.end = null;
    }

    public User getUser() {
        return user;
    }

    public Publication getPublication() {
        return publication;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return  "user=" + user +
                ", publication=" + publication +
                ", start=" + start +
                ", end=" + end;
    }
}
