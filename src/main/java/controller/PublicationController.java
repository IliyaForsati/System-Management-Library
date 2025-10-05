package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.interfaces.IPublicationController;
import model.Publication;
import model.enums.SortType;
import services.interfaces.IPublicationService;
import settings.serviceProvider.ServiceProvider;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class PublicationController<T extends Publication> implements IPublicationController<T> {
    private final Class<T> clazz = getClazz();
    private final IPublicationService<T> service =ServiceProvider.mainScope
            .getService(model.enums.Type.getServiceByModelClass(clazz));
    private final ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("unchecked")
    protected Class<T> getClazz() {
        Type superClass = this.getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            Type actualType = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            return (Class<T>) actualType;
        }
        throw new RuntimeException("Cannot determine generic type for class " + getClass().getName());
    }


    public String add(String bodyJSON) {
        JsonNode node = mapper.valueToTree(bodyJSON);

        T entity = mapper.convertValue(node.get("entity"), clazz);
        if (service.add(entity)) {
            return "added successfully";
        }
        return null;
    }

    public String getById(String bodyJSON) throws JsonProcessingException {
        int id = mapper.valueToTree(bodyJSON).get("id").asInt();
        T entity = service.getById(id);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
    }

    public String getAll(String bodyJSON) throws JsonProcessingException {
        String stStr = mapper.valueToTree(bodyJSON).get("sortType").asText();
        SortType st;
        if (!stStr.isEmpty() && !stStr.equals("null")) {
            st = SortType.valueOf(stStr);
        } else st = null;
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(service.getAll(st));
    }

    public String search(String bodyJSON) throws JsonProcessingException {
        JsonNode node = mapper.valueToTree(bodyJSON);
        String key = node.get("key").asText();

        String stStr = node.get("sortType").asText();
        SortType st;
        if (!stStr.isEmpty() && !stStr.equals("null")) {
            st = SortType.valueOf(stStr);
        } else st = null;

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(service.search(key, st));
    }

    public String update(String bodyJSON) {
        JsonNode node = mapper.valueToTree(bodyJSON);

        int id = node.get("id").asInt();
        T entity = mapper.convertValue(node.get("entity"), clazz);

        if (service.update(id, entity)) {
            return "added successfully";
        }
        return null;
    }

    public String remove(String bodyJSON) {
        int id = mapper.valueToTree(bodyJSON).get("id").asInt();

        if (service.remove(id))
            return "removed successfully";
        return null;
    }
}
