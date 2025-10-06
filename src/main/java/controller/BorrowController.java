package controller;

import com.fasterxml.jackson.databind.JsonNode;
import controller.interfaces.IBorrowController;
import model.Publication;
import services.interfaces.IBorrowService;
import services.interfaces.IPublicationService;
import settings.serviceProvider.ServiceProvider;

public class BorrowController implements IBorrowController {
    private final IBorrowService service = ServiceProvider.mainScope.getService(IBorrowService.class);

    public String borrow(int publicationId) {
        Publication publication = IPublicationService.findPublicationById(publicationId);

        return service.Borrow(publication);
    }

    public String return_(int publicationId) {
        Publication publication = IPublicationService.findPublicationById(publicationId);

        return service.Borrow(publication);
    }

    public String showHistory() {
        return service.showHistory();
    }
}
