package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Publication;
import model.ResultDTO;
import model.enums.SortType;
import services.interfaces.IPublicationService;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

abstract class PublicationService<T extends Publication> implements IPublicationService<T> {
    // for caching
    protected ArrayList<T> allData;
    protected ObjectMapper mapper = new ObjectMapper();
    File dataFile;
    {
        loadFromFile();
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getClazz() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            Type actualType = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            return (Class<T>) actualType;
        }
        throw new RuntimeException("Cannot determine generic type for class " + getClass().getName());
    }
    protected void loadFromFile() {
        Class<T> clazz = getClazz();
        String clazzName = clazz.getSimpleName();
        dataFile = new File("data/" + clazzName + ".json");

        try {
            if (!dataFile.exists()) {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
                allData = new ArrayList<>();
            }
            else {
                @SuppressWarnings("unchecked")
                Class<T[]> arrayClazz = (Class<T[]>) java.lang.reflect.Array.newInstance(clazz, 0).getClass();
                T[] array = mapper.readValue(dataFile, arrayClazz);
                allData = new ArrayList<>(Arrays.asList(array));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected void updateJsonFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(dataFile, allData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultDTO add(T entity) {
        allData.add(entity);
        updateJsonFile();
        return new ResultDTO("successfully added", true);
    }
    protected T findById(int id) {
        for (T entity : allData) {
            if (entity.getId() == id)
                return entity;
        }
        return null;
    }
    public ResultDTO display(int entityId) {
        T entity = findById(entityId);
        if (entity == null)
            return new ResultDTO("dist does not exist!", false);
        return new ResultDTO(entity.toString(), true);
    }
    public ResultDTO print(SortType st) {
        if (st != null)
            return new ResultDTO(printResult(sort(allData, st)), true);
        return new ResultDTO(printResult(allData), true);
    }
    public ResultDTO search(String key, SortType st) {
        ArrayList<T> filteredData = new ArrayList<>();
        for (T entity : allData) {
            if (entity.getTitle().contains(key) || entity.getAuthor().contains(key) ||
                    entity.getType().toString().contains(key) || entity.getStatus().toString().contains(key))
                filteredData.add(entity);
        }

        if (st != null)
            filteredData = sort(filteredData, st);

        return new ResultDTO(printResult(filteredData), true);
    }
    public ResultDTO update(int distId, T src) {
        T dist = findById(distId);
        if (dist == null)
            return new ResultDTO("dist does not exist!", false);
        copyFields(src, dist);
        updateJsonFile();
        return new ResultDTO("updated successfully.", true);
    }
    public ResultDTO remove(int entityId) {
        T entity = findById(entityId);
        if (entity == null)
            return new ResultDTO("dist does not exist!", false);
        allData.remove(entity);
        updateJsonFile();
        return new ResultDTO("removed successfully.", true);
    }

    protected String printResult(ArrayList<T> src) {
        StringBuilder sb = new StringBuilder();
        for (T entity : src)
            sb.append(entity.toString()).append('\n');
        return sb.toString();
    }
    protected ArrayList<T> sort(ArrayList<T> src, SortType st) {
        ArrayList<T> copy = new ArrayList<>(src);
        if (st.equals(SortType.UPWARD)) {
            copy.sort(Comparator.comparingInt(a -> a.getPublicationYear().getValue()));
        } else {
            copy.sort(Comparator.comparingInt(a -> -1 * a.getPublicationYear().getValue()));
        }
        return copy;
    }
    public static <T> void copyFields(T source, T target) {
        if (source == null || target == null) throw new IllegalArgumentException("Source and target cannot be null");
        Class<?> clazz = source.getClass();
        if (!clazz.equals(target.getClass())) {
            throw new IllegalArgumentException("Source and target must be of the same class");
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(source);
                field.set(target, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to copy field: " + field.getName(), e);
            }
        }
    }
}
