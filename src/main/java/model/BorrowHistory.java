package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.time.LocalDate;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class BorrowHistory {
    private User user;
    private Publication publication;
    private LocalDate start;
    private LocalDate end;

    public BorrowHistory() {}

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

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return  "user=" + user.getUsername() +
                ", publication=" + publication +
                ", start=" + start +
                ", end=" + end;
    }
}
