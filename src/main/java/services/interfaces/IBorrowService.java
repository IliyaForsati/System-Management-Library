package services.interfaces;

import model.Publication;

public interface IBorrowService {
    String Borrow(Publication publication);
    String return_(Publication publication);
    String showHistory();
}
