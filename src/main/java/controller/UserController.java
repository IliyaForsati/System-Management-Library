package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import controller.interfaces.IUserController;
import model.UserDTO;
import services.interfaces.IUserService;
import settings.serviceProvider.ServiceProvider;

public class UserController implements IUserController {
    public final IUserService service = ServiceProvider.mainScope.getService(IUserService.class);

    public String register(String jsonBody) throws JsonProcessingException {
        UserDTO user = mapper.readValue(jsonBody, UserDTO.class);
        return service.login(user);
    }

    public String login(String jsonBody) throws JsonProcessingException {
        UserDTO user = mapper.readValue(jsonBody, UserDTO.class);
        return service.login(user);
    }

    public String logout() {
        return service.logout();
    }
}
