package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import controller.interfaces.IPublicationController;
import model.Publication;
import model.enums.SortType;
import model.enums.Status;
import services.interfaces.IPublicationService;
import settings.serviceProvider.ServiceProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class PublicationController<T extends Publication> implements IPublicationController<T> {
    private final Class<T> clazz = getClazz();
    private final IPublicationService<T> service =ServiceProvider.mainScope
            .getService(model.enums.Type.getServiceByModelClass(clazz));

    @SuppressWarnings("unchecked")
    protected Class<T> getClazz() {
        Type superClass = this.getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            Type actualType = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            return (Class<T>) actualType;
        }
        throw new RuntimeException("Cannot determine generic type for class " + getClass().getName());
    }

    public String add(String title, String author, int publicationYear, String type, String status)
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        T entity = clazz.getDeclaredConstructor().newInstance();
        entity.setTitle(title);
        entity.setAuthor(author);
        entity.setPublicationYear(publicationYear);
        entity.setType(model.enums.Type.valueOf(type));
        entity.setStatus(Status.valueOf(status));

        if (service.add(entity)) {
            return "added successfully";
        }
        return null;
    }

    public String getById(int id) {
        T entity = service.getById(id);

        return entity.toString();
    }

    public String getAll(String stStr) {
        SortType st;
        if (!stStr.isEmpty() && !stStr.equals("null")) {
            st = SortType.valueOf(stStr);
        } else st = null;
        return service.getAll(st).toString();
    }

    public String search(String key, String stStr) {
        SortType st;
        if (!stStr.isEmpty() && !stStr.equals("null")) {
            st = SortType.valueOf(stStr);
        } else st = null;

        return service.search(key, st).toString();
    }

    public String update(int id, String title, String author, int publicationYear, String type, String status)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T entity = clazz.getDeclaredConstructor().newInstance();
        entity.setTitle(title);
        entity.setAuthor(author);
        entity.setPublicationYear(publicationYear);
        entity.setType(model.enums.Type.valueOf(type));
        entity.setStatus(Status.valueOf(status));

        if (service.update(id, entity)) {
            return "added successfully";
        }
        return null;
    }

    public String remove(int id) {
        if (service.remove(id))
            return "removed successfully";
        return null;
    }
}
