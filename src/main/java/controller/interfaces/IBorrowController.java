package controller.interfaces;

import settings.interfaces.IController;

public interface IBorrowController extends IController {
    String borrow(String jsonBody);
    String return_(String jsonBody);
    String showHistory();
}
