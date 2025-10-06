package controller.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import settings.interfaces.IController;

public interface IUserController extends IController {
    String register(String username, String password);
    String login(String username, String password);
    String logout();
}
