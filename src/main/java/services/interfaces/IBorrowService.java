package services.interfaces;

import model.Publication;
import settings.interfaces.IService;

public interface IBorrowService extends IService {
    String Borrow(Publication publication);
    String return_(Publication publication);
    String showHistory();
}
