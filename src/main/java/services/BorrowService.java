package services;

import model.BorrowHistory;
import model.Publication;
import model.User;
import model.enums.Type;
import services.interfaces.IBorrowService;
import services.interfaces.IUserService;
import settings.serviceProvider.ServiceProvider;

public class BorrowService implements IBorrowService {
    public String borrow(Publication publication) {
        if (publication == null) {
            return "there is no publication with this id";
        }
        if (publication.getBorrowHistory() != null) {
            return "publication is already in used";
        }

        User loggedInUser = ServiceProvider.mainScope.getService(IUserService.class).getLoggedInUser();

        BorrowHistory borrowHistory = new BorrowHistory(loggedInUser, publication);
        loggedInUser.getBorrowHistories().add(borrowHistory);
        publication.setBorrowHistory(borrowHistory);

        ServiceProvider.mainScope.getService(IUserService.class).saveChanges();
        ServiceProvider.mainScope.getService(Type.getServiceByModelClass(publication.getType().getModel())).update(publication.getId(), publication);

        return "borrowed successfully";
    }

    public String return_(Publication publication) {
        if (publication == null) {
            return "there is no publication with this id";
        }
        if (publication.getBorrowHistory() == null) {
            return "publication is not already in used";
        }

        User loggedInUser = ServiceProvider.mainScope.getService(IUserService.class).getLoggedInUser();
        if (publication.getBorrowHistory().getUser() != loggedInUser) {
            return "return can be just by the user that borrow the publication";
        }

        loggedInUser.getBorrowHistories().remove(publication.getBorrowHistory());
        publication.setBorrowHistory(null);
        return "returned successfully";
    }

    public String showHistory() {
        StringBuilder sb = new StringBuilder();

        User loggedInUser = ServiceProvider.mainScope.getService(IUserService.class).getLoggedInUser();

        for (BorrowHistory h : loggedInUser.getBorrowHistories())
            sb.append(h.toString()).append('\n');

        return sb.toString();
    }
}
