package controller.interfaces;

import settings.interfaces.IController;

public interface IBorrowController extends IController {
    String borrow(int publicationI);
    String return_(int publicationI);
    String showHistory();
}
