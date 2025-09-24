package controller;

import controller.interfaces.IPublicationController;
import model.Publication;
import model.ResultDTO;
import model.enums.SortType;

abstract class PublicationController<T extends Publication> implements IPublicationController<T> {
    public ResultDTO display(T entity) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    public ResultDTO add(T entity) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    public ResultDTO update(T entity) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    public ResultDTO remove(T entity) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    public ResultDTO print(SortType st) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    public ResultDTO search(SortType st) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
