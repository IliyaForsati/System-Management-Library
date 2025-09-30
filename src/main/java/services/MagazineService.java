package services;

import model.Magazine;
import services.interfaces.IMagazineService;

public class MagazineService
        extends PublicationService<Magazine>
        implements IMagazineService
{
    private static MagazineService instance = null;
    private MagazineService() {}
    public static MagazineService getInstance() {
        if (instance != null) return instance;

        instance = new MagazineService();
        return instance;
    }


}
