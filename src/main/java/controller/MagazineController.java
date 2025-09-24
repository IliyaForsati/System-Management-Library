package controller;

import controller.interfaces.IController;
import controller.interfaces.IMagazineController;
import model.Magazine;
import model.ResultDTO;

class MagazineController
        extends PublicationController<Magazine>
        implements IMagazineController, IController
{
    @Override
    public ResultDTO run(String[] args) {
        return null;
    }

    private static MagazineController instance = null;
    private MagazineController() {}
    public static MagazineController getInstance() {
        if (instance != null) return instance;

        instance = new MagazineController();
        return instance;
    }
}
