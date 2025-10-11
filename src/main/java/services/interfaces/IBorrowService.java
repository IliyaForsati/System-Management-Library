package services.interfaces;

import model.Publication;
import settings.interfaces.IService;

public interface IBorrowService extends IService {
    String borrow(Publication publication);
    String return_(Publication publication);
    String showHistory();
}
