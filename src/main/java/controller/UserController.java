package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import controller.interfaces.IUserController;
import model.UserDTO;
import services.interfaces.IUserService;
import settings.serviceProvider.ServiceProvider;

public class UserController implements IUserController {
    public final IUserService service = ServiceProvider.mainScope.getService(IUserService.class);

    public String register(String username, String password) {
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(password);
        return service.register(user);
    }

    public String login(String username, String password) {
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(password);
        return service.login(user);
    }

    public String logout() {
        return service.logout();
    }
}
