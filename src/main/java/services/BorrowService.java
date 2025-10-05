package services;

import model.BorrowHistory;
import model.Publication;
import model.User;
import services.interfaces.IBorrowService;
import services.interfaces.IUserService;
import settings.serviceProvider.ServiceProvider;

public class BorrowService implements IBorrowService {
    public String Borrow(Publication publication) {
        if (publication == null) {
            return "there is no book with this id";
        }
        if (publication.getBorrowHistory() != null) {
            return "book is already in used";
        }

        User loggedInUser = ServiceProvider.mainScope.getService(IUserService.class).getLoggedInUser();

        BorrowHistory borrowHistory = new BorrowHistory(loggedInUser, publication);
        loggedInUser.borrowHistories.add(borrowHistory);
        publication.setBorrowHistory(borrowHistory);
        return "borrowed successfully";
    }

    public String return_(Publication publication) {
        if (publication == null) {
            return "there is no book with this id";
        }
        if (publication.getBorrowHistory() == null) {
            return "book is not already in used";
        }

        User loggedInUser = ServiceProvider.mainScope.getService(IUserService.class).getLoggedInUser();
        loggedInUser.borrowHistories.remove(publication.getBorrowHistory());
        publication.setBorrowHistory(null);
        return "returned successfully";
    }

    public String showHistory() {
        StringBuilder sb = new StringBuilder();

        User loggedInUser = ServiceProvider.mainScope.getService(IUserService.class).getLoggedInUser();

        for (BorrowHistory h : loggedInUser.borrowHistories)
            sb.append(h.toString()).append('\n');

        return sb.toString();
    }
}
