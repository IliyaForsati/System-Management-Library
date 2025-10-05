package controller.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import settings.interfaces.IController;

public interface IUserController extends IController {
    String register(String jsonBody) throws JsonProcessingException;
    String login(String jsonBody) throws JsonProcessingException;
    String logout();
}
