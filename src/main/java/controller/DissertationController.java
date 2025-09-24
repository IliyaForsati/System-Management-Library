package controller;

import controller.interfaces.IController;
import controller.interfaces.IDissertationController;
import model.Dissertation;
import model.ResultDTO;

class DissertationController
        extends PublicationController<Dissertation>
        implements IDissertationController, IController
{
    @Override
    public ResultDTO run(String[] args) {
        return null;
    }

    private static DissertationController instance = null;
    private DissertationController() {}
    public static DissertationController getInstance() {
        if (instance != null) return instance;

        instance = new DissertationController();
        return instance;
    }
}
