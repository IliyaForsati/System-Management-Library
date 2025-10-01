package controller.interfaces;

import model.Publication;

public interface IPublicationController<T extends Publication> {
    String run(String command, Object bodyJSON);
}
