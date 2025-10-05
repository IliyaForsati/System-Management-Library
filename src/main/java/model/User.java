package model;

import util.Argon2id;

import java.util.ArrayList;

public class User {
    private final String username;
    private final String password;
    public final ArrayList<BorrowHistory> borrowHistories = new ArrayList<>();

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
}
