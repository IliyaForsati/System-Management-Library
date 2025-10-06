package services;

import model.Publication;
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
    File dataFile;
    {
        loadFromFile();
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
    protected void loadFromFile() {
        Class<T> clazz = getClazz();
        String clazzName = clazz.getSimpleName();
        dataFile = new File("data/publications/" + clazzName + ".json");

        try {
            if (!dataFile.exists()) {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
                allData = new ArrayList<>();
            }
            else if (dataFile.length() == 0) {
                allData = new ArrayList<>();
            }
            else {
                @SuppressWarnings("unchecked")
                Class<T[]> arrayClazz = (Class<T[]>) java.lang.reflect.Array.newInstance(clazz, 0).getClass();
                T[] array = mapper.readValue(dataFile, arrayClazz);
                allData = new ArrayList<>(Arrays.asList(array));
            }
        } catch (IOException e) {
            System.err.println("Warning: " + dataFile.getName() + " is invalid JSON, resetting file.");
            allData = new ArrayList<>();
            updateJsonFile();
        }
    }
    protected void updateJsonFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(dataFile, allData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean add(T entity) {
        allData.add(entity);
        updateJsonFile();
        return true;
    }
    public T getById(int id) {
        for (T entity : allData) {
            if (entity.getId() == id)
                return entity;
        }
        return null;
    }
    public ArrayList<T> getAll(SortType st) {
        if (st != null)
            return sort(allData, st);
        return allData;
    }
    public ArrayList<T> search(String key, SortType st) {
        ArrayList<T> filteredData = new ArrayList<>();
        for (T entity : allData) {
            if (entity.getTitle().contains(key) || entity.getAuthor().contains(key) ||
                    entity.getType().toString().contains(key) || entity.getStatus().toString().contains(key))
                filteredData.add(entity);
        }

        if (st != null)
            filteredData = sort(filteredData, st);

        return filteredData;
    }
    public boolean update(int distId, T src) {
        T dist = getById(distId);
        if (dist == null)
            return false;
        copyFields(src, dist);
        updateJsonFile();
        return true;
    }
    public boolean remove(int entityId) {
        T entity = getById(entityId);
        if (entity == null)
            return false;
        allData.remove(entity);
        updateJsonFile();
        return true;
    }

    protected ArrayList<T> sort(ArrayList<T> src, SortType st) {
        ArrayList<T> copy = new ArrayList<>(src);
        if (st.equals(SortType.UPWARD)) {
            copy.sort(Comparator.comparingInt(Publication::getPublicationYear));
        } else {
            copy.sort(Comparator.comparingInt(a -> -1 * a.getPublicationYear()));
        }
        return copy;
    }
    protected static <T> void copyFields(T source, T target) {
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
