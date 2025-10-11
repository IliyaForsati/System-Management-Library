package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import util.Argon2id;

import java.util.ArrayList;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
public class User {
    private String username;
    private String password;
    private ArrayList<BorrowHistory> borrowHistories = new ArrayList<>();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = Argon2id.hashPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<BorrowHistory> getBorrowHistories() {
        return borrowHistories;
    }

    public void setBorrowHistories(ArrayList<BorrowHistory> borrowHistories) {
        this.borrowHistories = borrowHistories;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
