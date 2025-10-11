package services.interfaces;

import model.User;
import model.UserDTO;
import settings.interfaces.IService;

public interface IUserService extends IService {
    String register(UserDTO user);
    String login(UserDTO user);
    String logout();
    User getLoggedInUser();
    void saveChanges();
    public User getUserByUsername(String username);
}
