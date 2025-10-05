package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import model.UserDTO;
import services.interfaces.IUserService;
import util.Argon2id;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class UserService implements IUserService {
    private ObjectMapper mapper = new ObjectMapper();
    private File dataFile;
    private User loggedInUser;
    private ArrayList<User> users;
    {
        loadData();
    }

    private void loadData() {
        try {
            dataFile = new File("data/user.json");
            if (!dataFile.exists()) {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
                users = new ArrayList<>();
            } else {
                users = mapper.readValue(
                        dataFile,
                        new TypeReference<>() {}
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void saveData() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(dataFile, users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String register(UserDTO user) {
        if (loggedInUser != null) {
            return "some one is logged in";
        }
        User usr = getUserByUsername(user.getUsername());
        if (usr != null) {
            return "there is a user with this user name";
        }

        User newUser = new User(user.getUsername(), user.getPassword());
        users.add(newUser);
        saveData();
        loggedInUser = newUser;
        return "register successfully";
    }

    public String login(UserDTO user) {
        User usr = getUserByUsername(user.getUsername());

        if (usr == null) {
            return "no such user";
        }
        if (loggedInUser != null) {
            return "some one is logged in";
        }
        if (!Argon2id.verifyPassword(user.getPassword(), usr.getPassword())) {
            return "password is false";
        }

        loggedInUser = usr;
        return "logged in successfully";
    }

    public String logout() {
        if (loggedInUser  == null) {
            return "no one logged in yet";
        }

        loggedInUser = null;
        return "logout successfully";
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    private User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
