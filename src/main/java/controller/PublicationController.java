package controller;

import controller.interfaces.IPublicationController;
import model.Publication;
import model.ResultDTO;
import model.enums.SortType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class PublicationController<T extends Publication> implements IPublicationController<T> {
    @SuppressWarnings("unchecked")
    private Class<T> getClazz() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            Type actualType = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            return (Class<T>) actualType;
        }
        return null; // todo: ex handling
    }

    public ResultDTO display(T entity) {
        return new ResultDTO(entity.createDisplayResult(), true);
    }
    public ResultDTO add(T entity) {
        entity.add();
        return new ResultDTO("added successfully", true);
    }
    public ResultDTO print(SortType st) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    public ResultDTO search(SortType st) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    public ResultDTO update(T dist, T src) {
        dist.update(src);
        return new ResultDTO("update successfully", true);
    }
    public ResultDTO remove(T entity) {
        entity.remove();
        return new ResultDTO("added successfully", true);
    }
}
