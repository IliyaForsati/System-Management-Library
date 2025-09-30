package services;

import model.Dissertation;
import services.interfaces.IDissertationService;

public class DissertationService
        extends PublicationService<Dissertation>
        implements IDissertationService
{
    private static DissertationService instance = null;
    private DissertationService() {}
    public static DissertationService getInstance() {
        if (instance != null) return instance;

        instance = new DissertationService();
        return instance;
    }


}
