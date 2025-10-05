package services.interfaces;

import model.User;
import model.UserDTO;

public interface IUserService {
    String register(UserDTO user);
    String login(UserDTO user);
    String logout();
    User getLoggedInUser();
}
