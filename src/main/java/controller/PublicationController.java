package controller;

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

    public String run(String command, Object bodyJSON) {
        try {
            switch (command) {
                case "add" -> {
                    JsonNode node = mapper.valueToTree(bodyJSON);

                    T entity = mapper.convertValue(node.get("entity"), clazz);
                    System.out.println(entity);
                    if (service.add(entity)) {
                        return "added successfully";
                    }
                    return null;
                }
                case "get" -> {
                    int id = mapper.valueToTree(bodyJSON).get("id").asInt();
                    T entity = service.getById(id);

                    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
                }
                case "getAll" -> {
                    String stStr = mapper.valueToTree(bodyJSON).get("sortType").asText();
                    SortType st;
                    if (!stStr.isEmpty() && !stStr.equals("null")) {
                        st = SortType.valueOf(stStr);
                    } else st = null;
                    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(service.getAll(st));
                }
                case "search" -> {
                    JsonNode node = mapper.valueToTree(bodyJSON);
                    String key = node.get("key").asText();

                    String stStr = node.get("sortType").asText();
                    SortType st;
                    if (!stStr.isEmpty() && !stStr.equals("null")) {
                        st = SortType.valueOf(stStr);
                    } else st = null;

                    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(service.search(key, st));
                }
                case "update" -> {
                    JsonNode node = mapper.valueToTree(bodyJSON);

                    int id = node.get("id").asInt();
                    T entity = mapper.convertValue(node.get("entity"), clazz);

                    if (service.update(id, entity)) {
                        return "added successfully";
                    }
                    return null;
                }
                case "remove" -> {
                    int id = mapper.valueToTree(bodyJSON).get("id").asInt();

                    if (service.remove(id))
                        return "removed successfully";
                    return null;
                }
                default -> {
                    return null;
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getClazz() {
        Type superClass = this.getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            Type actualType = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            return (Class<T>) actualType;
        }
        throw new RuntimeException("Cannot determine generic type for class " + getClass().getName());
    }
}
