package controller.interfaces;

public interface IBorrowController {
    String borrow(String jsonBody);
    String return_(String jsonBody);
    String showHistory();
}
