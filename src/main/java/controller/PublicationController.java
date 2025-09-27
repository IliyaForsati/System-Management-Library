package controller;

import controller.interfaces.IPublicationController;
import model.Publication;
import model.ResultDTO;
import model.enums.SortType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

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

    public ResultDTO add(T entity) {
        boolean isSuccessful = entity.add();
        return new ResultDTO(isSuccessful ? "added successfully" : "can not add entity. please try again."
                , isSuccessful);
    }
    public ResultDTO display(T entity) {
        return new ResultDTO(entity.createDisplayResult(), true);
    }
    public ResultDTO print(SortType st) {
        ArrayList<Publication> allEntity = Publication.allPublications;
        ArrayList<Publication> all = new ArrayList<>();
        Class<T> clazz = getClazz();

        for (Publication entity : allEntity) {
            assert clazz != null;
            if (clazz.isInstance(entity)) {
                all.add(entity);
            }
        }

        if (st != null)
            all = Publication.sort(all, st);

        StringBuilder sb = new StringBuilder();
        for (Publication entity : all)
            sb.append(entity.toString()).append('\n');

        return new ResultDTO(sb.toString(), true);
    }
    public ResultDTO search(String key, SortType st) {
        ArrayList<Publication> allEntity = Publication.allPublications;
        ArrayList<Publication> all = new ArrayList<>();
        Class<T> clazz = getClazz();

        for (Publication entity : allEntity) {
            assert clazz != null;
            if (clazz.isInstance(entity)) {
                if (entity.getTitle().contains(key) || entity.getAuthor().contains(key))
                    all.add(entity);
            }
        }

        if (st != null)
            all = Publication.sort(all, st);

        StringBuilder sb = new StringBuilder();
        for (Publication entity : all)
            sb.append(entity.toString()).append('\n');

        return new ResultDTO(sb.toString(), true);
    }
    public ResultDTO update(T dist, T src) {
        dist.update(src);
        return new ResultDTO("updated successfully", true);
    }
    public ResultDTO remove(T entity) {
        entity.remove();
        return new ResultDTO("deleted successfully", true);
    }
}
