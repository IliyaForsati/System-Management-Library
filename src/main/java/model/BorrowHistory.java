package model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import services.interfaces.IPublicationService;
import services.interfaces.IUserService;
import settings.serviceProvider.ServiceProvider;

import java.time.LocalDate;

public class BorrowHistory {
    @JsonIgnore
    private User user;
    private String username;
    @JsonIgnore
    private Publication publication;
    private int publicationId;
    private LocalDate start;
    private LocalDate end;

    public BorrowHistory() {}

    public BorrowHistory(User user, Publication publication) {
        this.user = user;
        this.username = user.getUsername();
        this.publication = publication;
        this.publicationId = publication.getId();
        this.start = LocalDate.now();
        this.end = null;
    }

    // <editor-fold desc="getters & setters"

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
//        User user = ServiceProvider.mainScope.getService(IUserService.class).getUserByUsername(username);
//        if (user == null)
//            throw new IllegalArgumentException("no user with this username");

//        this.user = user;
        this.username = username;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        Publication publication = IPublicationService.findPublicationById(publicationId);
        if (publication == null)
            throw new IllegalArgumentException("no publication with this id");

        this.publication = publication;
        this.publicationId = publicationId;
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

    // </editor-fold>

    @Override
    public String toString() {
        return  "user=" + user.getUsername() +
                ", publication=" + publication +
                ", start=" + start +
                ", end=" + end;
    }
}
